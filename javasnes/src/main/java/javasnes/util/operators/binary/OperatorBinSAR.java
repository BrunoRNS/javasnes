package javasnes.util.operators.binary;

import javasnes.util.operators.SnesOperator;
import javasnes.util.types.vars.abstracts.SnesType;

/**
 * The OperatorBinSAR class represents the Shift Arithmetic Right operation in the SNES development
 * context. It is used to shift the bits of a value to the right, preserving the sign bit.
 * This class extends the SnesOperator to provide specific functionality for the Shift Arithmetic Right operation.
 */
public class OperatorBinSAR extends SnesOperator {

    /**
     * The value to shift right.
     * This value is expected to be of a type that supports bitwise operations.
     */
    public SnesType value;

    /**
     * The number of positions to shift the value to the right.
     * This determines how many bits will be shifted during the operation.
     */
    public int shiftAmount;

    /**
     * Constructor for OperatorBinSAR that initializes the operator with a value and shift amount.
     * This constructor allows the user to create an instance of OperatorBinSAR using
     * an existing value and specifying how many positions to shift right.
     * 
     * @param value The value to shift right.
     * @param shiftAmount The number of positions to shift the value.
     */
    public OperatorBinSAR(SnesType value, int shiftAmount) {
        this.value = value;
        this.shiftAmount = shiftAmount;
    }

    /**
     * Gets the source code representation for the Shift Arithmetic Right operator.
     * This method generates a string that represents the shift operation
     * in the format of "(value >> shiftAmount)", which can be used in the generated
     * source code for the SNES development context.
     * 
     * @return A string representing the source code for the Shift Arithmetic Right operator.
     */
    @Override
    public final String getSourceCode() {
        return "(" + this.value.name + " >> " + this.shiftAmount + ")";
    }

}
