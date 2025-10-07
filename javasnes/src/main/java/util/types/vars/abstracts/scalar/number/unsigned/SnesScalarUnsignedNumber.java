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
     * The minimum value for unsigned scalar numbers (always zero).
     */
    {
        this.MIN_VALUE = (long) 0;
    }
    
}
