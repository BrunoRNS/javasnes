package javasnes.util.operators.logical;

import javasnes.util.operators.SnesOperator;
import javasnes.util.types.vars.abstracts.SnesType;
import javasnes.util.types.vars.scalar.data.SnesChar;

/**
 * The OperatorSmaller class represents the Smaller operation in the SNES development
 * context. It is used to check if the first value is smaller than the second value.
 * This class extends the SnesOperator to provide specific functionality for the Smaller operation.
 */
public class OperatorSmaller extends SnesOperator {

    /**
     * The values to compare for the Smaller operation.
     * These values are of type SnesType, which allows for various data types
     * to be compared. The comparison is based on the names of these values.
     */
    public SnesType value1;
    public SnesType value2;

    /**
     * Constructor for OperatorSmaller that initializes the operator with two SnesType values.
     * This constructor allows the user to create an instance of OperatorSmaller using
     * existing SnesType objects, which will be compared during the operation.
     * 
     * @param value1 The first value to compare, of type SnesType.
     * @param value2 The second value to compare, of type SnesType.
     */
    public OperatorSmaller(SnesType value1, SnesType value2) {
        this.value1 = value1;
        this.value2 = value2;
    }

    /**
     * Constructor for OperatorSmaller that initializes the operator with two String values.
     * This constructor converts the provided String values into SnesChar objects, which
     * are then used for comparison. This allows for easy initialization using string literals.
     * 
     * @param value1 The first value to compare, provided as a String.
     * @param value2 The second value to compare, provided as a String.
     */
    public OperatorSmaller(String value1, String value2) {
        this.value1 = new SnesChar(value1, value1);
        this.value2 = new SnesChar(value2, value2);
    }

    /**
     * Gets the source code representation for the Smaller operator.
     * This method generates a string that represents the comparison operation
     * in the format of "(value1 < value2)", which can be used in the generated
     * source code for the SNES development context.
     * 
     * @return A string representing the source code for the Smaller operator.
     */
    @Override
    public final String getSourceCode() {
        return "(" + this.value1.name + " < " + this.value2.name + ")";
    }

}
