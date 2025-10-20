package javasnes.util.operators.binary;

import javasnes.util.operators.SnesOperator;
import javasnes.util.types.vars.abstracts.SnesType;

/**
 * The OperatorBinXor class represents the Bitwise XOR operation in the SNES development
 * context. It is used to perform a bitwise XOR operation between two values.
 * This class extends the SnesOperator to provide specific functionality for the XOR operation.
 */
public class OperatorBinXor extends SnesOperator {

    /**
     * The first value for the XOR operation.
     * This value is expected to be of a type that supports bitwise operations.
     */
    public SnesType value1;

    /**
     * The second value for the XOR operation.
     * This value is expected to be of a type that supports bitwise operations.
     */
    public SnesType value2;

    /**
     * Constructor for OperatorBinXor that initializes the operator with two values.
     * This constructor allows the user to create an instance of OperatorBinXor using
     * existing values, which will be used in the bitwise XOR operation.
     * 
     * @param value1 The first value for the XOR operation.
     * @param value2 The second value for the XOR operation.
     */
    public OperatorBinXor(SnesType value1, SnesType value2) {
        this.value1 = value1;
        this.value2 = value2;
    }

    /**
     * Gets the source code representation for the Bitwise XOR operator.
     * This method generates a string that represents the bitwise XOR operation
     * in the format of "(value1 ^ value2)", which can be used in the generated
     * source code for the SNES development context.
     * 
     * @return A string representing the source code for the Bitwise XOR operator.
     */
    @Override
    public final String getSourceCode() {
        return "(" + this.value1.name + " ^ " + this.value2.name + ")";
    }

}
