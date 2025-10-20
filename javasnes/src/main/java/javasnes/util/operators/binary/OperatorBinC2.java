package javasnes.util.operators.binary;

import javasnes.util.operators.SnesOperator;
import javasnes.util.types.vars.abstracts.SnesType;

/**
 * The OperatorBinC2 class represents the Two's Complement operation in the SNES development
 * context. It is used to compute the two's complement of a value, effectively negating it.
 * This class extends the SnesOperator to provide specific functionality for the Two's Complement operation.
 */
public class OperatorBinC2 extends SnesOperator {

    /**
     * The value to compute the two's complement for.
     * This value is expected to be of a type that supports bitwise operations.
     */
    public SnesType value;

    /**
     * Constructor for OperatorBinC2 that initializes the operator with a value.
     * This constructor allows the user to create an instance of OperatorBinC2 using
     * an existing value, which will be negated in the two's complement operation.
     * 
     * @param value The value for which to compute the two's complement.
     */
    public OperatorBinC2(SnesType value) {
        this.value = value;
    }

    
    /**
     * Gets the source code representation of the Two's Complement operation.
     * 
     * This method generates a string that represents the Two's Complement operation
     * in the format of "((~value) + 1)", which can be used in the generated
     * source code for the SNES development context.
     * 
     * @return A string representing the source code for the Two's Complement operator.
     */    
    @Override
    public final String getSourceCode() {
        return "((~" + this.value.name + ") + 1)";
    }

}
