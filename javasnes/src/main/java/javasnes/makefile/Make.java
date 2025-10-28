package javasnes.makefile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a Makefile with dynamic rule management and configuration.
 * 
 * <p>This class provides a structured way to create, read, update, and delete
 * Makefile components including rules, variables, phony targets, and header content.
 * It replaces hardcoded String arrays with a dynamic, object-oriented approach.</p>
 *
 * <p><b>Usage Examples:</b></p>
 * <pre>
 * {@code
 * // Create a new Makefile with default configuration
 * Make makefile = new Make();
 * 
 * // Update basic configuration
 * makefile.setRomName("MyAwesomeGame");
 * makefile.setSoundBank("game_soundbank");
 * 
 * // Create and add a new rule
 * MakeRule compileRule = new MakeRule("compile", "src/*.c", 
 *     "@echo Compiling source files...",
 *     "@gcc -c $< -o $@"
 * );
 * makefile.addRule(compileRule);
 * 
 * // Read and update an existing rule
 * MakeRule allRule = makefile.getRule("all");
 * if (allRule != null) {
 *     allRule.setPrerequisites("compile logo");
 *     allRule.addRecipeLine("@echo Build complete!");
 * }
 * 
 * // Add phony targets
 * makefile.addPhonyTarget("test");
 * makefile.addPhonyTarget("deploy");
 * 
 * // Set custom variables
 * makefile.addVariable("CUSTOM_FLAGS", "-O2 -Wall");
 * 
 * // Remove unnecessary components
 * makefile.removeRule("cleanGfxLogo");
 * makefile.removePhonyTarget("bitmaps");
 * 
 * // Get the final Makefile content
 * String[] makefileContent = makefile.getMakefile();
 * }
 * </pre>
 *
 */
public class Make {
    
    /**
     * The content of the header section of the Makefile.
     */
    protected List<String> headerContent;

    /**
     * The collection of rules in the Makefile.
     */
    protected Map<String, MakeRule> rules;

    /**
     * The list of phony targets in the Makefile.
     */
    protected List<String> phonyTargets;

    /**
     * The collection of variables in the Makefile.
     */
    protected Map<String, String> variables;

    /**
     * The path to include in the Makefile.
     */
    protected String includePath;

    /**
     * The name of the ROM being built.
     */
    protected String romName;
    
    /**
     * The name of the soundbank being used.
     */
    protected String soundBank;
    
    /**
     * Constructs a new Make instance with default configuration.
     * 
     * <p>The default configuration includes:
     * <ul>
     *   <li>Basic PVSNESLIB environment checks</li>
     *   <li>Standard audio and graphics configuration</li>
     *   <li>Common build rules (all, clean, musics, etc.)</li>
     *   <li>Default ROM name "JavasnesGame"</li>
     * </ul>
     * </p>
     */
    public Make() {

        this.headerContent = new ArrayList<>();
        this.rules = new HashMap<>();
        this.phonyTargets = new ArrayList<>();
        this.variables = new HashMap<>();
        
        initializeDefaults();

    }
    
    /**
     * Initializes the Makefile with default configuration values.
     * This method is called automatically during construction.
     */
    private void initializeDefaults() {

        this.headerContent.add("ifeq ($(strip $(PVSNESLIB_HOME)),)");
        this.headerContent.add("$(error \"Please create an environment variable PVSNESLIB_HOME by following this guide: https://github.com/alekmaul/pvsneslib/wiki/Installation\")");
        this.headerContent.add("endif");

        this.setInclude("${PVSNESLIB_HOME}/devkitsnes/snes_rules");
        this.setRomName("                     "); // 21 characters length
        
        this.addPhonyTarget("all");
        this.addPhonyTarget("clean");
        this.addPhonyTarget("logo");

        this.addRule(
            new MakeRule(
                "all",
                "logo",
                ""
            )
        );

        this.addRule(
            new MakeRule(
                "clean",
                "cleanBuildRes cleanRom cleanGfx cleanAudio",
                ""
            )
        );

        this.addRule(
            new MakeRule(
                "javasnes_logo.pic",
                "javasnes_logo.bmp",
                "@echo convert javasnes_logo to map/pic/pal... $(notdir $@)",
                "$(GFXCONV) -s 8 -o 16 -u 16 -e 0 -p -m -t bmp -i $<"      
        ));

        this.addRule(new MakeRule(
            "logo",
            "javasnes_logo.pic javasnes_logo.map javasnes_logo.pal",
            ""
        ));

    }
    
    
    /**
     * Creates a new rule in the Makefile.
     * 
     * @param rule The rule to add to the Makefile
     * @throws IllegalArgumentException if a rule with the same target already exists
     */
    public void addRule(MakeRule rule) throws IllegalArgumentException {

        if (this.rules.containsKey(rule.getTarget())) {

            throw new IllegalArgumentException(
                "Rule with target '" + rule.getTarget() + "' already exists"
            );
        
        }

        this.rules.put(rule.getTarget(), rule);

    }
    
    /**
     * Reads a rule from the Makefile by its target name.
     * 
     * @param target The target name of the rule to retrieve
     * @return The MakeRule object, or null if no rule with the specified target exists
     */
    public MakeRule getRule(String target) {

        return this.rules.get(target);

    }
    
    /**
     * Updates an existing rule in the Makefile.
     * 
     * @param rule The updated rule object
     * @throws IllegalArgumentException if no rule with the specified target exists
     */
    public void setRule(MakeRule rule) throws IllegalArgumentException {

        if (!this.rules.containsKey(rule.getTarget())) {

            throw new IllegalArgumentException(
                "Rule with target '" + rule.getTarget() + "' does not exist"
            );
        
        }

        this.rules.put(rule.getTarget(), rule);

    }
    
    /**
     * Deletes a rule from the Makefile by its target name.
     * 
     * @param target The target name of the rule to remove
     * @return true if the rule was removed, false if no rule with the specified 
     * target exists
     */
    public boolean removeRule(String target) {

        return this.rules.remove(target) != null;

    }
    
    /**
     * Creates a new phony target in the Makefile.
     * 
     * @param target The phony target to add
     * @throws IllegalArgumentException if the phony target already exists
     */
    public void addPhonyTarget(String target) throws IllegalArgumentException {

        if (this.phonyTargets.contains(target)) {

            throw new IllegalArgumentException(
                "Phony target '" + target + "' already exists"
            );
        
        }

        this.phonyTargets.add(target);

    }
    
    /**
     * Reads all phony targets from the Makefile.
     * 
     * @return A list of all phony targets
     */
    public List<String> getPhonyTargets() {

        return new ArrayList<>(this.phonyTargets);

    }
    
    /**
     * Updates a phony target by replacing the old target with a new one.
     * 
     * @param oldTarget The existing phony target to replace
     * @param newTarget The new phony target
     * @throws IllegalArgumentException if the old target doesn't exist or the new target already exists
     */
    public void setPhonyTarget(

        String oldTarget, String newTarget

    ) throws IllegalArgumentException {

        if (!this.phonyTargets.contains(oldTarget)) {

            throw new IllegalArgumentException(
                "Phony target '" + oldTarget + "' does not exist"
            );
        
        }

        if (this.phonyTargets.contains(newTarget) && !oldTarget.equals(newTarget)) {

            throw new IllegalArgumentException(
                "Phony target '" + newTarget + "' already exists"
            );
        
        }
        
        int index = this.phonyTargets.indexOf(oldTarget);
        this.phonyTargets.set(index, newTarget);

    }
    
    /**
     * Deletes a phony target from the Makefile.
     * 
     * @param target The phony target to remove
     * @return true if the phony target was removed, false if it didn't exist
     */
    public boolean removePhonyTarget(String target) {

        return this.phonyTargets.remove(target);

    }
    
    /**
     * Creates a new variable in the Makefile.
     * 
     * @param name The variable name
     * @param value The variable value
     * @throws IllegalArgumentException if a variable with the same name already exists
     */
    public void addVariable(String name, String value) throws IllegalArgumentException {

        if (this.variables.containsKey(name)) {
            throw new IllegalArgumentException(
                "Variable '" + name + "' already exists"
            );
        }

        this.variables.put(name, value);

    }
    
    /**
     * Reads a variable value from the Makefile.
     * 
     * @param name The variable name to retrieve
     * @return The variable value, or null if the variable doesn't exist
     */
    public String getVariable(String name) {

        return this.variables.get(name);

    }
    
    /**
     * Updates an existing variable in the Makefile.
     * 
     * @param name The variable name to update
     * @param value The new variable value
     * @throws IllegalArgumentException if the variable doesn't exist
     */
    public void setVariable(String name, String value) throws IllegalArgumentException {

        if (!this.variables.containsKey(name)) {

            throw new IllegalArgumentException(
                "Variable '" + name + "' does not exist"
            );

        }

        this.variables.put(name, value);

    }
    
    /**
     * Deletes a variable from the Makefile.
     * 
     * @param name The variable name to remove
     * @return true if the variable was removed, false if it didn't exist
     */
    public boolean removeVariable(String name) {

        return this.variables.remove(name) != null;

    }
    
    /**
     * Creates a new line in the header content.
     * 
     * @param line The line to add to the header content
     */
    public void addHeaderLine(String line) {

        this.headerContent.add(line);

    }
    
    /**
     * Reads the header content at the specified index.
     * 
     * @param index The index of the header line to retrieve
     * @return The header line at the specified index
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public String getHeaderLine(int index) throws IndexOutOfBoundsException {

        if (index < 0 || index >= this.headerContent.size()) {

            throw new IndexOutOfBoundsException(
                "Header line index " + index + " is out of bounds"
            );

        }

        return this.headerContent.get(index);

    }
    
    /**
     * Reads all header content.
     * 
     * @return A list of all header lines
     */
    public List<String> getHeaderContent() {

        return new ArrayList<>(this.headerContent);

    }
    
    /**
     * Updates a header line at the specified index.
     * 
     * @param index The index of the header line to update
     * @param line The new header line content
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public void setHeaderLine(int index, String line) throws IndexOutOfBoundsException {

        if (index < 0 || index >= this.headerContent.size()) {

            throw new IndexOutOfBoundsException(
                "Header line index " + index + " is out of bounds"
            );
        
        }

        this.headerContent.set(index, line);

    }
    
    /**
     * Deletes a header line at the specified index.
     * 
     * @param index The index of the header line to remove
     * @return The removed header line
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public String removeHeaderLine(int index) throws IndexOutOfBoundsException {

        if (index < 0 || index >= this.headerContent.size()) {

            throw new IndexOutOfBoundsException(
                "Header line index " + index + " is out of bounds"
            );
        
        }

        return this.headerContent.remove(index);

    }
    
    /**
     * Sets the include path for the Makefile.
     * 
     * @param includePath The path to include in the Makefile
     */
    public void setInclude(String includePath) {

        this.includePath = includePath;

    }
    
    /**
     * Gets the current include path.
     * 
     * @return The current include path
     */
    public String getInclude() {

        return this.includePath;

    }
    
    /**
     * Sets the ROM name and updates the corresponding ROMNAME variable.
     * 
     * @param romName The name of the ROM
     */
    public void setRomName(String romName) {

        this.romName = romName;

        if (this.variables.containsKey("ROMNAME")) {

            setVariable("ROMNAME", romName);

        } else {

            addVariable("ROMNAME", romName);

        }

    }
    
    /**
     * Gets the current ROM name.
     * 
     * @return The current ROM name
     */
    public String getRomName() {

        return this.romName;

    }
    
    /**
     * Sets the sound bank name and updates the corresponding SOUNDBANK variable.
     * 
     * @param soundBank The name of the sound bank
     */
    public void setSoundBank(String soundBank) {

        this.soundBank = soundBank;

        if (this.variables.containsKey("SOUNDBANK")) {

            setVariable("SOUNDBANK", soundBank);

        } else {

            addVariable("SOUNDBANK", soundBank);

        }

    }
    
    /**
     * Gets the current sound bank name.
     * 
     * @return The current sound bank name
     */
    public String getSoundBank() {

        return this.soundBank;

    }
    
    /**
     * Generates and returns the complete Makefile content as a string array.
     * 
     * <p>This method assembles all components (header content, variables,
     * include statements, phony targets, and rules) into the final Makefile
     * format.</p>
     * 
     * @return The complete Makefile as a string array, ready for writing to a file
     */
    public String[] getMakefile() {

        List<String> makefileContent = new ArrayList<>();
        
        makefileContent.addAll(headerContent);
        makefileContent.add("");
        
        if (this.soundBank != null && !this.variables.containsKey("SOUNDBANK")) {

            makefileContent.add("export SOUNDBANK := " + this.soundBank);

        }
        
        if (this.includePath != null) {

            makefileContent.add("include " + this.includePath);
            makefileContent.add("");

        }
        
        if (!this.phonyTargets.isEmpty()) {

            makefileContent.add(".PHONY: " + String.join(" ", this.phonyTargets));

        }
        
        if (!this.variables.isEmpty()) {

            for (Map.Entry<String, String> entry : this.variables.entrySet()) {

                if (
                    entry.getKey().equals("ROMNAME") || 
                    entry.getKey().equals("SOUNDBANK")
                ) {

                    makefileContent.add(
                        "export " + entry.getKey() + " := " + 
                        entry.getValue()
                    );
                
                } else {

                    makefileContent.add(entry.getKey() + " := " + entry.getValue());

                }

            }

            makefileContent.add("");

        }
        
        for (MakeRule rule : this.rules.values()) {

            makefileContent.addAll(rule.toMakefileLines());
            makefileContent.add("");

        }
        
        return makefileContent.toArray(new String[0]);

    }
    
    /**
     * Represents a Makefile rule with target, prerequisites, and recipe.
     * 
     * <p>Each rule consists of:
     * <ul>
     *   <li>A target (the file or action to build)</li>
     *   <li>Prerequisites (files or other targets needed before building this target)</li>
     *   <li>A recipe (the commands to execute to build the target)</li>
     * </ul>
     * </p>
     */
    public static class MakeRule {
        protected String target;
        protected String prerequisites;
        protected List<String> recipe;
        
        /**
         * Constructs a new Makefile rule.
         * 
         * @param target The target of the rule
         * @param prerequisites The prerequisites for this rule, or empty string if none
         * @param recipeLines The recipe lines (commands) for this rule
         */
        public MakeRule(String target, String prerequisites, String... recipeLines) {

            this.target = target;
            this.prerequisites = prerequisites;
            this.recipe = new ArrayList<>();
            this.recipe.addAll(Arrays.asList(recipeLines));

        }
        
        /**
         * Gets the target of this rule.
         * 
         * @return The target name
         */
        public String getTarget() {

            return this.target;

        }
        
        /**
         * Sets the target of this rule.
         * 
         * @param target The new target name
         */
        public void setTarget(String target) {

            this.target = target;

        }
        
        /**
         * Gets the prerequisites of this rule.
         * 
         * @return The prerequisites
         */
        public String getPrerequisites() {

            return this.prerequisites;

        }
        
        /**
         * Sets the prerequisites of this rule.
         * 
         * @param prerequisites The new prerequisites
         */
        public void setPrerequisites(String prerequisites) {

            this.prerequisites = prerequisites;

        }
        
        /**
         * Gets the recipe lines of this rule.
         * 
         * @return A copy of the recipe lines list
         */
        public List<String> getRecipe() {

            return new ArrayList<>(this.recipe);

        }
        
        /**
         * Adds a recipe line to this rule.
         * 
         * @param line The recipe line to add
         */
        public void addRecipeLine(String line) {

            this.recipe.add(line);

        }
        
        /**
         * Gets a specific recipe line by index.
         * 
         * @param index The index of the recipe line to retrieve
         * @return The recipe line at the specified index
         * @throws IndexOutOfBoundsException if the index is out of range
         */
        public String getRecipeLine(int index) throws IndexOutOfBoundsException {

            if (index < 0 || index >= this.recipe.size()) {

                throw new IndexOutOfBoundsException(
                    "Recipe line index " + index + " is out of bounds"
                );

            }

            return this.recipe.get(index);

        }
        
        /**
         * Sets a specific recipe line at the given index.
         * 
         * @param index The index of the recipe line to update
         * @param line The new recipe line content
         * @throws IndexOutOfBoundsException if the index is out of range
         */
        public void setRecipeLine(

            int index, String line

        ) throws IndexOutOfBoundsException {

            if (index < 0 || index >= this.recipe.size()) {

                throw new IndexOutOfBoundsException(
                    "Recipe line index " + index + " is out of bounds"
                );
            
            }

            this.recipe.set(index, line);

        }
        
        /**
         * Removes a recipe line at the specified index.
         * 
         * @param index The index of the recipe line to remove
         * @return The removed recipe line
         * @throws IndexOutOfBoundsException if the index is out of range
         */
        public String removeRecipeLine(int index) throws IndexOutOfBoundsException {

            if (index < 0 || index >= this.recipe.size()) {

                throw new IndexOutOfBoundsException(
                    "Recipe line index " + index + " is out of bounds"
                );

            }

            return this.recipe.remove(index);

        }
        
        /**
         * Converts this rule to Makefile format lines.
         * 
         * @return A list of strings representing this rule in Makefile format
         */
        public List<String> toMakefileLines() {

            List<String> lines = new ArrayList<>();
            
            if (this.prerequisites == null || this.prerequisites.isEmpty()) {

                lines.add(target + ":");

            } else {

                lines.add(target + ": " + this.prerequisites);

            }
            
            for (String recipeLine : this.recipe) {

                if (!recipeLine.isEmpty()) {
                    lines.add("\t" + recipeLine);
                }

            }
            
            return lines;

        }

    }

}
