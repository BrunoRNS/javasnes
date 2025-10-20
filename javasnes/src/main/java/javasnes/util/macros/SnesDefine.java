package javasnes.util.macros;

/**
 * Class representing a SNES define macro.
 * Extends the SnesMacro abstract class.
 */
public class SnesDefine extends SnesMacro {

    /**
     * Constructor for the SnesDefine class.
     * 
     * @param definition The definition of the macro.
     */
    public SnesDefine(String definition) {
        
        this.type = "#define";
        this.definition = definition;
        
        this.generateSourceCode();

    }
    
}
