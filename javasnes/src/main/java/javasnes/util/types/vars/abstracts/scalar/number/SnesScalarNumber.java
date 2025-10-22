package javasnes.util.types.vars.abstracts.scalar.number;

import javasnes.util.types.vars.abstracts.scalar.SnesTypeScalar;


/**
 * Abstract base class representing a scalar numeric type in the SNES type system.
 * 
 * This class provides common fields for SNES scalar numbers, such as the number of bits,
 * minimum value, and maximum value. Subclasses should define the specific numeric behavior.
 *
 * - {@code BITS_COUNT}: The number of bits used to represent the value.
 * - {@code MIN_VALUE}: The minimum value that can be represented.
 * - {@code MAX_VALUE}: The maximum value that can be represented.
 *
 * All fields are nullable and should be initialized by subclasses.
 */
public abstract class SnesScalarNumber extends SnesTypeScalar {

    /**
     * Byte BITS_COUNT - number of bits used to represent the value.
     */
    public Byte BITS_COUNT = null;

    /**
     * Long MIN_VALUE - minimum value that can be represented by the variable.
     */
    public Long MIN_VALUE = null;

    /**
     * Long MAX_VALUE - maximum value that can be represented by the variable.
     */
    public Long MAX_VALUE = null;

    /**
     * Validates the BITS_COUNT, MIN_VALUE, and MAX_VALUE fields.
     * 
     * - BITS_COUNT must be a power of two, greater than or equal to 8.
     * - MIN_VALUE must be a power of two.
     * - MAX_VALUE + 1 must be a power of two.
     *
     * @throws IllegalArgumentException if any of the constraints are violated or if any field is null.
     */
    public void validateMinMaxBitCount() throws IllegalArgumentException {

        /**
         * Check if any of the values are null
         */
        if (this.BITS_COUNT == null || this.MIN_VALUE == null || this.MAX_VALUE == null) {
            throw new IllegalArgumentException("BITS_COUNT, MIN_VALUE, and MAX_VALUE must not be null.");
        }

        /** 
         * Check if BITS_COUNT is a power of two and at least 8
         */
        if (!(this.BITS_COUNT > 0 && ((this.BITS_COUNT & (this.BITS_COUNT - 1)) == 0) && (this.BITS_COUNT >= 8))) {

            throw new IllegalArgumentException("BITS_COUNT must be a power of two and at least 8.");

        }

        /**
         * Check if MIN_VALUE is a power of two
         */
        if (!((this.MIN_VALUE & (this.MIN_VALUE - 1)) == 0)) {

            throw new IllegalArgumentException("MIN_VALUE must be a power of two.");

        }

        /**
         * Check if MAX_VALUE + 1 is a power of two
         */
        if (!(((this.MAX_VALUE + 1) & this.MAX_VALUE) == 0)) {

            throw new IllegalArgumentException("MAX_VALUE + 1 must be a power of two.");

        }

    }

    public SnesScalarNumber() {}

}
