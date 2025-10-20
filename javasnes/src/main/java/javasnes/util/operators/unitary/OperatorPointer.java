package javasnes.util.operators.unitary;

import javasnes.util.operators.SnesOperator;
import javasnes.util.types.vars.abstracts.SnesType;

/**
 * Represents a pointer operator in SNES C code.
 * 
 * A pointer operator is used to get the address of a variable.
 */
public class OperatorPointer extends SnesOperator {

    {
        this.requiredVars = 1;
    }

    /**
     * The variable to get the address of.
     */
    public SnesType var;

    /**
     * Creates a new pointer operator for the given variable.
     * @param var the variable to get the address of
     */
    public OperatorPointer(SnesType var) {
        this.var = var;
    }

    /**
     * Returns the source code for the pointer operator.
     * 
     * The generated source code is in the format "*varName", where varName is the name of the variable being referenced.
     * 
     * @return the source code for the pointer operator
     */
    @Override
    public String getSourceCode() {
        return "*" + this.var.name;
    }
    
}
