package javasnes.util.types.vars.pointer.number.unsigned;

import javasnes.util.types.vars.abstracts.pointer.number.unsigned.SnesPointerUnsignedNumber;

/**
 * Class representing an unsigned 16-bit pointer variable in SNES.
 * Extends the SnesPointerUnsignedNumber abstract class.
 */
public class SnesU16Pointer extends SnesPointerUnsignedNumber {

    {
        this.type = "u16";
    }

    /**
     * Constructor for SnesU16Pointer.
     * @param name The name of the pointer variable.
     */
    public SnesU16Pointer(String name) {

        this.name = name;
        this.generateSourceCode();

    }

    /**
     * Constructor for SnesU16Pointer with default value.
     * @param name The name of the pointer variable.
     * @param defaultValue The default value for the pointer variable.
     */
    public SnesU16Pointer(String name, String defaultValue) {

        this.name = name;
        this.defaultValue = defaultValue;
        this.generateSourceCode();

    }
    
}
