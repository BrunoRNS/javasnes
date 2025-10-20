package javasnes.util.types.vars.pointer.data;

import javasnes.util.types.vars.abstracts.pointer.data.SnesPointerData;

/**
 * Class representing a SNES character pointer variable.
 * Extends the SnesPointerData abstract class.
 */
public class SnesCharPointer extends SnesPointerData {

    {
        this.type = "char";
    }

    /**
     * Constructor for SnesCharPointer.
     * 
     * @param name The name of the pointer variable.
     */
    public SnesCharPointer(String name) {

        this.name = name;
        this.generateSourceCode();

    }

    /**
     * Constructor for SnesCharPointer with default value.
     * @param name The name of the pointer variable.
     * @param defaultValue The default value for the pointer variable.
     */
    public SnesCharPointer(String name, String defaultValue) {

        this.name = name;
        this.defaultValue = defaultValue;
        this.generateSourceCode();

    }

}
