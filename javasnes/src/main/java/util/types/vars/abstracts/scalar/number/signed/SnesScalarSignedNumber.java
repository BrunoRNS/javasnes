package util.types.vars.abstracts.scalar.number.signed;

import util.types.vars.abstracts.scalar.number.SnesScalarNumber;

/**
 * Abstract base class representing a signed scalar number in the SNES context.
 *
 * This class extends {@link SnesScalarNumber} to provide a foundation for all signed numeric scalar types.
 * Subclasses should implement specific signed number representations and behaviors as needed.
 *
 */
public abstract class SnesScalarSignedNumber extends SnesScalarNumber {

    /**
     * Represents the signal (sign) of the number.
     * 
     * - '+' for positive values.
     * - '-' for negative values.
     * - ' ' (space) for positive decimal values or for binary/hexadecimal numbers that do not use a sign.
     * 
     * May be {@code null} if the signal is not set.
     */
    public Character signal = null;
    
}
