package util.types.vars.pointer.number.unsigned;

import util.types.vars.abstracts.pointer.number.unsigned.SnesPointerUnsignedNumber;

/**
 * Class representing an unsigned 32-bit pointer variable in SNES.
 * Extends the SnesPointerUnsignedNumber abstract class.
 */
public class SnesU32Pointer extends SnesPointerUnsignedNumber {

    {
        this.type = "u32";
    }

    /**
     * Constructor for SnesU32Pointer.
     * @param name The name of the pointer variable.
     */
    public SnesU32Pointer(String name) {

        this.name = name;
        this.generateSourceCode();

    }

    /**
     * Constructor for SnesU32Pointer with default value.
     * @param name The name of the pointer variable.
     * @param defaultValue The default value for the pointer variable.
     */
    public SnesU32Pointer(String name, String defaultValue) {

        this.name = name;
        this.defaultValue = defaultValue;
        this.generateSourceCode();

    }
    
}
