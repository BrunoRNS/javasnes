package util.types.vars.scalar.number.unsigned;

import util.types.vars.abstracts.scalar.number.unsigned.SnesScalarUnsignedNumber;

/**
 * SnesU8 class to represent U8 values in SNES development context.
 * It extends SnesScalarUnsignedNumber.
 * It defines BITS_COUNT to 8 and MAX_VALUE to 255.
 */
public class SnesU8 extends SnesScalarUnsignedNumber {

    {
        this.BITS_COUNT = 8;

        // MIN_VALUE always 0, defined by {@link SnesScalarUnsignedNumber}
        this.MAX_VALUE = (2 * (long) Math.pow(2, this.BITS_COUNT)) - 1;

        this.type = "u8";

        this.validateMinMaxBitCount();

    }

    /**
     * Constructor for SnesU8 with name only.
     * @param name name of the variable
     */
    public SnesU8(String name) {

        this.name = name;
        this.generateSourceCode();

    }

    /**
     * Constructor for SnesU8 with name and default value.
     * @param name name of the variable
     * @param defaultValue default value of the variable
     */
    public SnesU8(String name, String defaultValue) {

        this.name = name;
        this.defaultValue = defaultValue;
        this.generateSourceCode();

    }
    
}
