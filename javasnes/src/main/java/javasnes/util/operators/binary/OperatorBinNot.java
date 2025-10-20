package javasnes.util.operators.binary;

import javasnes.util.operators.SnesOperator;
import javasnes.util.types.vars.abstracts.SnesType;

/**
 * The OperatorBinNot class represents the Bitwise NOT operation in the SNES development
 * context. It is used to perform a bitwise NOT operation on a value, effectively inverting its bits.
 * This class extends the SnesOperator to provide specific functionality for the NOT operation.
 */
public class OperatorBinNot extends SnesOperator {

    /**
     * The value to perform the NOT operation on.
     * This value is expected to be of a type that supports bitwise operations.
     */
    public SnesType value;

    /**
     * Constructor for OperatorBinNot that initializes the operator with a value.
     * This constructor allows the user to create an instance of OperatorBinNot using
     * an existing value, which will be inverted in the bitwise NOT operation.
     * 
     * @param value The value for which to perform the NOT operation.
     */
    public OperatorBinNot(SnesType value) {
        this.value = value;
    }

    /**
     * Gets the source code representation for the Bitwise NOT operator.
     * This method generates a string that represents the bitwise NOT operation
     * in the format of "(~value)", which can be used in the generated
     * source code for the SNES development context.
     * 
     * @return A string representing the source code for the Bitwise NOT operator.
     */
    @Override
    public final String getSourceCode() {
        return "(~" + this.value.name + ")";
    }

}
