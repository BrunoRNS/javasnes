package util.types.vars.scalar.number.signed;

import util.types.vars.abstracts.scalar.number.signed.SnesScalarSignedNumber;

/**
 * SnesU16 class to represent S16 values in SNES development context.
 * It extends SnesScalarSignedNumber.
 * It defines BITS_COUNT to 16 and MIN_VALUE to -32768 and MAX_VALUE to 32767.
 */
public class SnesS16 extends SnesScalarSignedNumber {

    {
        this.BITS_COUNT = 16;

        this.MIN_VALUE = -(long) Math.pow(2, this.BITS_COUNT - 1);
        this.MAX_VALUE = (long) Math.pow(2, this.BITS_COUNT - 1) - 1;

        this.type = "s16";

        this.validateMinMaxBitCount();

    }

    /**
     * Constructor for SnesS16 with name only.
     * @param name name of the variable
     */
    public SnesS16(String name) {

        this.name = name;
        this.generateSourceCode();

    }

    /**
     * Constructor for SnesS16 with name and default value.
     * @param name name of the variable
     * @param defaultValue default value of the variable
     */
    public SnesS16(String name, String defaultValue) {

        this.name = name;
        this.defaultValue = defaultValue;
        this.generateSourceCode();

    }
    
}
