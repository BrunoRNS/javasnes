package javasnes.util.types.vars.scalar.number.unsigned;

import javasnes.util.types.vars.abstracts.scalar.number.unsigned.SnesScalarUnsignedNumber;

/**
 * SnesU32 class to represent U32 values in SNES development context.
 * It extends SnesScalarUnsignedNumber.
 * It defines BITS_COUNT to 32 and MAX_VALUE to 4.294.967.295.
 */
public class SnesU32 extends SnesScalarUnsignedNumber {

    {
        this.BITS_COUNT = 32;

        // MIN_VALUE always 0, defined by {@link SnesScalarUnsignedNumber}
        this.MAX_VALUE = (2 * (long) Math.pow(2, this.BITS_COUNT)) - 1;

        this.type = "u32";

        this.validateMinMaxBitCount();

    }

    /**
     * Constructor for SnesU32 with name only.
     * @param name name of the variable
     */
    public SnesU32(String name) {

        this.name = name;
        this.generateSourceCode();

    }

    /**
     * Constructor for SnesU32 with name and default value.
     * @param name name of the variable
     * @param defaultValue default value of the variable
     */
    public SnesU32(String name, String defaultValue) {

        this.name = name;
        this.defaultValue = defaultValue;
        this.generateSourceCode();

    }
    
}
