package javasnes.util.types.vars.scalar.number.signed;

import javasnes.util.types.vars.abstracts.scalar.number.signed.SnesScalarSignedNumber;

/**
 * SnesU16 class to represent S32 values in SNES development context.
 * It extends SnesScalarSignedNumber.
 * It defines BITS_COUNT to 32 and MIN_VALUE to -2.147.483.648 and MAX_VALUE to 2.147.483.647.
 */
public class SnesS32 extends SnesScalarSignedNumber {

    {
        this.BITS_COUNT = 32;

        this.MIN_VALUE = -(long) Math.pow(2, this.BITS_COUNT - 1);
        this.MAX_VALUE = (long) Math.pow(2, this.BITS_COUNT - 1) - 1;

        this.type = "s32";

        this.validateMinMaxBitCount();

    }

    /**
     * Constructor for SnesS32 with name only.
     * @param name name of the variable
     */
    public SnesS32(String name) {

        this.name = name;
        this.generateSourceCode();

    }

    /**
     * Constructor for SnesS32 with name and default value.
     * @param name name of the variable
     * @param defaultValue default value of the variable
     */
    public SnesS32(String name, String defaultValue) {

        this.name = name;
        this.defaultValue = defaultValue;
        this.generateSourceCode();

    }
    
}
