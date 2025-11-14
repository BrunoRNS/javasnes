package javasnes.util.macros;

import javasnes.instruction.SnesInstruction;

public abstract class SnesMacro extends SnesInstruction {

    /**
     * The type of the macro,
     * Can be:
     * 
     * #define
     * or
     * #include
     */
    public String type;

    /**
     * The definition of the macro,
     * Can be:
     * 
     * "<snes.h>" if type is #include for example
     * "PI 3.14159" if type is #define
     * 
     */
    public String definition;

    /**
     * Generates the source code for the macro.
     * 
     * <p>This will generate code in the format of:</p>
     * 
     * <pre>
     * #define/#include definition
     * </pre>
     * 
     * <p>Where #define/#include is the type of the macro, and definition is the definition of the macro.</p>
     * 
     * <p>This method first calls the validate method to ensure that the macro is valid.</p>
     * 
     * <p>Then it constructs the source code string by concatenating the type and definition.</p>
     */
    public final void generateSourceCode() {

        this.validate();
        this.sourceCode = this.type + " " + this.definition;

    }

    /**
     * Validates the SnesMacro instance.
     * 
     * This method checks that the type and definition are not null, not empty, and that the type is #define or #include.
     * If the type is #define, it also checks that the definition is in the format "name value".
     * If the type is #include, it also checks that the definition is in the format "<*.h>" or "\"*.h\"".
     * 
     * @throws IllegalArgumentException if the SnesMacro is invalid.
     */
    public void validate() throws IllegalArgumentException {

        if (this.type == null || this.definition == null) {
            throw new IllegalArgumentException("SnesMacro must have type and definition defined.");
        }
        if (this.type.isEmpty() || this.definition.isEmpty()) {
            throw new IllegalArgumentException("SnesMacro's type and definition can't be empty.");
        }
        if (!(this.type.equals("#define")) && !(this.type.equals("#include"))) {
            throw new IllegalArgumentException("SnesMacro's type must be #define or #include.");
        }
        if (this.type.equals("#define") && this.definition.split(" ").length != 2) {
            throw new IllegalArgumentException("SnesMacro's definition must be in the format \"name value\".");
        }
        if (
            this.type.equals("#include") && (
                !this.definition.contains("\"") && 
                (!this.definition.contains("<") && !this.definition.contains(">"))
            )
        ) {
            throw new IllegalArgumentException("SnesMacro's definition must be in the format \"<.*.h>\". or \"\".*.h\"\".");
        }

    }
    
}
