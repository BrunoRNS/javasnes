package javasnes.util.operators.unitary;

import javasnes.util.operators.SnesOperator;
import javasnes.util.types.vars.abstracts.scalar.number.SnesScalarNumber;

public class OperatorIncrement extends SnesOperator {

    {
        this.requiredVars = 1;
    }

    /**
     * The value to increment, it must be a scalar number.
     * 
     * @see SnesScalarNumber
     */
    public SnesScalarNumber value;

    /**
     * The direction of the increment, true for post-increment, false for pre-increment.
     */
    public boolean direction;

    /**
     * Creates a new increment operator.
     * 
     * @param value the value to increment
     * @param direction true for post-increment, false for pre-increment
     */
    public OperatorIncrement(SnesScalarNumber value, boolean direction) {
        this.value = value;
        this.direction = direction;
    }

    /**
     * Gets the source code for the increment operator.
     * 
     * If the direction is true, the source code will be in the format "value++".
     * Otherwise, the source code will be in the format "++value".
     * 
     * @return the source code for the increment operator
     */
    @Override
    public final String getSourceCode() {
        
        if (this.direction) {
            return this.value.name + "++";
        } else {
            return "++" + this.value.name;
        }

    }
    
}
