package util.types.vars.abstracts.scalar.number.unsigned;

import util.types.vars.abstracts.scalar.number.SnesScalarNumber;

/**
 * Abstract base class representing an unsigned scalar number type for the SNES system.
 * 
 * This class extends {@link SnesScalarNumber} and provides a minimum value implementation
 * suitable for unsigned numbers (i.e., zero).
 * 
 */
public abstract class SnesScalarUnsignedNumber extends SnesScalarNumber {

    /**
     * Returns the minimum value for this unsigned scalar number type.
     * 
     * For unsigned numbers, this is always zero.
     *
     * @return the minimum value (0) for this unsigned number type
     */
    @Override
    public Integer getMinValue() {

        return 0;

    }
    
}
