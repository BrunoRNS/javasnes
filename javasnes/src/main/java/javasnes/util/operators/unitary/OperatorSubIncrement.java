package javasnes.util.operators.unitary;

import javasnes.util.operators.SnesOperator;
import javasnes.util.types.vars.abstracts.scalar.number.SnesScalarNumber;

/**
 * Represents a negative increment operator in SNES C code.
 * 
 * A negative increment operator is used to decrement a value by a specified amount.
 * 
 * @see OperatorIncrement
 */
public class OperatorSubIncrement extends SnesOperator {

    {
        this.requiredVars = 1;
    }

    /**
     * The value to subtract, it must be a scalar number.
     * 
     * @see SnesScalarNumber
     */
    public SnesScalarNumber value;

    /**
     * The direction of the subtraction, true for post-subtract, false for pre-subtract.
     */
    public boolean direction;

    /**
     * Creates a new negative increment operator.
     * 
     * @param value the value to subtract
     * @param direction true for post-subtract, false for pre-subtract
     */
    public OperatorSubIncrement(SnesScalarNumber value, boolean direction) {
        this.value = value;
        this.direction = direction;
    }

    /**
     * Gets the source code for the subtraction operator.
     * 
     * If the direction is true, the source code will be in the format "value--".
     * Otherwise, the source code will be in the format "--value".
     * 
     * @return the source code for the subtraction operator
     */
    @Override
    public final String getSourceCode() {
        
        if (this.direction) {
            return this.value.name + "--";
        } else {
            return "--" + this.value.name;
        }

    }
    
}
