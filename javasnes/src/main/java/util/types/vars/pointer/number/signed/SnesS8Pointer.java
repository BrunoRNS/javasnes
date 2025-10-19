package util.types.vars.pointer.number.signed;

import util.types.vars.abstracts.pointer.number.signed.SnesPointerSignedNumber;

/**
 * Class representing an signed 8-bit pointer variable in SNES.
 * Extends the SnesPointerSignedNumber abstract class.
 */
public class SnesS8Pointer extends SnesPointerSignedNumber {

    {
        this.type = "s8";
    }

    /**
     * Constructor for SnesS8Pointer.
     * @param name The name of the pointer variable.
     */
    public SnesS8Pointer(String name) {

        this.name = name;
        this.generateSourceCode();

    }

    /**
     * Constructor for SnesS8Pointer with default value.
     * @param name The name of the pointer variable.
     * @param defaultValue The default value for the pointer variable.
     */
    public SnesS8Pointer(String name, String defaultValue) {

        this.name = name;
        this.defaultValue = defaultValue;
        this.generateSourceCode();

    }
    
}
