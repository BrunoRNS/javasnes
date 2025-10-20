package javasnes.util.types.vars.scalar.number.unsigned;

import javasnes.util.types.vars.abstracts.scalar.number.unsigned.SnesScalarUnsignedNumber;

/**
 * SnesU16 class to represent U16 values in SNES development context.
 * It extends SnesScalarUnsignedNumber.
 * It defines BITS_COUNT to 16 and MAX_VALUE to 65.535.
 */
public class SnesU16 extends SnesScalarUnsignedNumber {

    {
        this.BITS_COUNT = 16;

        // MIN_VALUE always 0, defined by {@link SnesScalarUnsignedNumber}
        this.MAX_VALUE = (2 * (long) Math.pow(2, this.BITS_COUNT)) - 1;

        this.type = "u16";

        this.validateMinMaxBitCount();

    }

    /**
     * Constructor for SnesU16 with name only.
     * @param name name of the variable
     */
    public SnesU16(String name) {

        this.name = name;
        this.generateSourceCode();

    }

    /**
     * Constructor for SnesU16 with name and default value.
     * @param name name of the variable
     * @param defaultValue default value of the variable
     */
    public SnesU16(String name, String defaultValue) {

        this.name = name;
        this.defaultValue = defaultValue;
        this.generateSourceCode();

    }
    
}
