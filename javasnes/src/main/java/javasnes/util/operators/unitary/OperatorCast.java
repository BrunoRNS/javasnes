package javasnes.util.operators.unitary;

import javasnes.util.operators.SnesOperator;
import javasnes.util.types.vars.abstracts.SnesType;

/**
 * Represents a cast operator in SNES C code.
 * A cast operator is used to convert a value to a different type.
 */
public class OperatorCast extends SnesOperator {

    {
        this.requiredVars = 1;
    }

    /**
     * The type to convert the value to.
     * 
     * @see SnesType
     */
    public SnesType type;

    /**
     * The value to convert.
     * 
     * @see SnesType
     */
    public SnesType value;
    
    /**
     * Creates a new cast operator.
     * 
     * @param type type to convert the value to
     * @param value value to convert(the name will be used)
     */
    public OperatorCast(SnesType type, SnesType value) {
        this.type = type;
        this.value = value;
    }

    /**
     * Generates the source code representation of the cast operator.
     * 
     * The generated source code is in the format "(type) value", where type is the type of the cast, and value is the name of the value being cast.
     * 
     * @return the source code representation of the cast operator.
     */
    @Override
    public final String getSourceCode() {
        return String.format("(%s) %s", type.type, value.name);
    }
    
}
