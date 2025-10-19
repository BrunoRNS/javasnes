package util.types.vars.pointer.data;

import util.types.vars.abstracts.pointer.data.SnesPointerVoid;

/**
 * Class representing a SNES void pointer variable.
 * Extends the SnesPointerVoid abstract class.
 */
public class SnesVoidPointer extends SnesPointerVoid {

    {
        this.type = "void";
    }

     /**
     * Constructor for SnesVoidPointer.
     * 
     * @param name The name of the pointer variable.
     */
    public SnesVoidPointer(String name) {
        
        this.name = name;
        this.generateSourceCode();
        
    }

    /**
     * Constructor for SnesVoidPointer with default value.
     * @param name The name of the pointer variable.
     * @param defaultValue The default value for the pointer variable.
     */
    public SnesVoidPointer(String name, String defaultValue) {
        
        this.name = name;
        this.defaultValue = defaultValue;
        this.generateSourceCode();
        
    }
    
}
