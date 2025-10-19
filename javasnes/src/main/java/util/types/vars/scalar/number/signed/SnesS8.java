package util.types.vars.scalar.number.signed;

import util.types.vars.abstracts.scalar.number.signed.SnesScalarSignedNumber;

/**
 * SnesU16 class to represent S8 values in SNES development context.
 * It extends SnesScalarSignedNumber.
 * It defines BITS_COUNT to 8 and MIN_VALUE to -128 and MAX_VALUE to 127.
 */
public class SnesS8 extends SnesScalarSignedNumber {

    {
        this.BITS_COUNT = 8;

        this.MIN_VALUE = -(long) Math.pow(2, this.BITS_COUNT - 1);
        this.MAX_VALUE = (long) Math.pow(2, this.BITS_COUNT - 1) - 1;

        this.type = "s8";

        this.validateMinMaxBitCount();

    }

    /**
     * Constructor for SnesS8 with name only.
     * @param name name of the variable
     */
    public SnesS8(String name) {

        this.name = name;
        this.generateSourceCode();

    }

    /**
     * Constructor for SnesS8 with name and default value.
     * @param name name of the variable
     * @param defaultValue default value of the variable
     */
    public SnesS8(String name, String defaultValue) {

        this.name = name;
        this.defaultValue = defaultValue;
        this.generateSourceCode();

    }
    
}
