package javasnes.util.macros;

/**
 * Class representing a SNES include macro.
 * Extends the SnesMacro abstract class.
 */
public class SnesInclude extends SnesMacro {

    /**
     * Constructor for SnesInclude class.
     * 
     * @param definition the definition of the macro
     */
    public SnesInclude(String definition) {

        this.type = "#include";
        this.definition = definition;

        this.generateSourceCode();

    }
    
}
