package javasnes.util.operators.unitary;

import javasnes.util.operators.SnesOperator;
import javasnes.util.types.vars.abstracts.SnesType;

/**
 * Represents a memory address operator in SNES C code.
 * 
 * A memory address operator is used to get the memory address of a variable.
 */
public class OperatorMemAdress extends SnesOperator {

    {
        this.requiredVars = 1;
    }

    /**
     * The variable to get the memory address of
     */
    public SnesType var;

    /**
     * Creates a new memory address operator.
     * @param var the variable to get the memory address of
     */
    public OperatorMemAdress(SnesType var) {
        this.var = var;
    }

    /**
     * Returns the source code for the memory address operator.
     * 
     * This will generate code in the format of "&varName".
     * 
     * @return the source code for the memory address operator
     */
    @Override
    public final String getSourceCode() {
        return "&" + this.var.name;
    }
    
}
