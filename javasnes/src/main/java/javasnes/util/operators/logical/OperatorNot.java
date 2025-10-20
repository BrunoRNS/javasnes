package javasnes.util.operators.logical;

import javasnes.util.operators.SnesOperator;

/**
 * The OperatorNot class represents the NOT operation in the SNES development context.
 * It is used to invert a value and store the result in a variable.
 */
public class OperatorNot extends SnesOperator {

    {
        this.requiredVars = 1;
    }

    /**
     * String to represent the value to be inverted.
     */
    public String value1;


    /**
     * Constructor for the OperatorNot class.
     * 
     * @param value1 the value to be inverted
     */
    public OperatorNot(String value1) {
        this.value1 = value1;
    }

    /**
     * Gets the source code for the NOT operator.
     * 
     * The generated source code is in the format "!(value)".
     * 
     * @return the source code for the NOT operator
     */
    @Override
    public String getSourceCode() {
        return "!(" + this.value1 + ")";
    }
    
}
