package javasnes.util.operators.binary;

import javasnes.util.operators.SnesOperator;
import javasnes.util.types.vars.abstracts.SnesType;

/**
 * The OperatorBinSHL class represents the Shift Logical Left operation in the SNES development
 * context. It is used to shift the bits of a value to the left, effectively multiplying it by two.
 * This class extends the SnesOperator to provide specific functionality for the Shift Logical Left operation.
 */
public class OperatorBinSHL extends SnesOperator {

    /**
     * The value to shift left.
     * This value is expected to be of a type that supports bitwise operations.
     */
    public SnesType value;

    /**
     * The number of positions to shift the value to the left.
     * This determines how many bits will be shifted during the operation.
     */
    public int shiftAmount;

    /**
     * Constructor for OperatorBinSHL that initializes the operator with a value and shift amount.
     * This constructor allows the user to create an instance of OperatorBinSHL using
     * an existing value and specifying how many positions to shift left.
     * 
     * @param value The value to shift left.
     * @param shiftAmount The number of positions to shift the value.
     */
    public OperatorBinSHL(SnesType value, int shiftAmount) {
        this.value = value;
        this.shiftAmount = shiftAmount;
    }

    /**
     * Gets the source code representation for the Shift Logical Left operator.
     * This method generates a string that represents the shift operation
     * in the format of "(value << shiftAmount)", which can be used in the generated
     * source code for the SNES development context.
     * 
     * @return A string representing the source code for the Shift Logical Left operator.
     */
    @Override
    public final String getSourceCode() {
        return "(" + this.value.name + " << " + this.shiftAmount + ")";
    }

}
