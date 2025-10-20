package javasnes.util.types.vars.pointer.number.signed;

import javasnes.util.types.vars.abstracts.pointer.number.signed.SnesPointerSignedNumber;

/**
 * Class representing an signed 16-bit pointer variable in SNES.
 * Extends the SnesPointerSignedNumber abstract class.
 */
public class SnesS16Pointer extends SnesPointerSignedNumber {

    {
        this.type = "s16";
    }

    /**
     * Constructor for SnesS16Pointer.
     * @param name The name of the pointer variable.
     */
    public SnesS16Pointer(String name) {

        this.name = name;
        this.generateSourceCode();

    }

    /**
     * Constructor for SnesS16Pointer with default value.
     * @param name The name of the pointer variable.
     * @param defaultValue The default value for the pointer variable.
     */
    public SnesS16Pointer(String name, String defaultValue) {

        this.name = name;
        this.defaultValue = defaultValue;
        this.generateSourceCode();

    }
    
}
