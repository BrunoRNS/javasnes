package javasnes.util.operators.logical;

import javasnes.util.operators.SnesOperator;

/**
 * The OperatorOr class represents the Or operation in the SNES development context.
 * It is used to Or two values together and store the result in a variable.
 */
public class OperatorOr extends SnesOperator {

    {
        this.requiredVars = 2;
    }

    /**
     * Strings to represent the values to be compared
     */
    public String value1;
    public String value2;

    /**
     * Constructs a new OperatorOr object.
     * It uses Strings to represent the values to be compared.
     * The Strings should return a boolean value, to be compared with the Or operator.
     * This is not verified, so verify by yourself the value before using it. 
     * @param value1 the first value
     * @param value2 the second value
     */
    public OperatorOr(String value1, String value2) {
        this.value1 = value1;
        this.value2 = value2;
    }

    /**
     * Returns the source code for the Or operator.
     * 
     * The generated source code is in the format of "(value1 || value2)".
     * 
     * @return the source code for the Or operator
     */
    @Override
    public String getSourceCode() {
        return "(" + this.value1 + " || " + this.value2 + ")";
    }
    
}
