package javasnes.util.operators.logical;

import javasnes.util.operators.SnesOperator;

/**
 * The OperatorAnd class represents the AND operation in the SNES development context.
 * It is used to AND two values together and store the result in a variable.
 */
public class OperatorAnd extends SnesOperator {

    {
        this.requiredVars = 2;
    }

    /**
     * Strings to represent the values to be ANDed
     */
    public String value1;
    public String value2;

    /**
     * Constructs a new OperatorAnd object.
     * It uses Strings to represent the values to be ANDed.
     * The Strings should return a boolean value, to be compared with the AND operator.
     * This is not verified, so verify by yourself the value before using it. 
     * @param value1 the first value
     * @param value2 the second value
     */
    public OperatorAnd(String value1, String value2) {
        this.value1 = value1;
        this.value2 = value2;
    }

    /**
     * Returns the source code for the AND operator.
     * 
     * The generated source code is in the format of "(value1 && value2)".
     * 
     * @return the source code for the AND operator
     */
    @Override
    public String getSourceCode() {
        return "(" + this.value1 + " && " + this.value2 + ")";
    }
    
}
