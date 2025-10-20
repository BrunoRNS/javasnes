package javasnes.util.operators.ternary;

import javasnes.util.operators.SnesOperator;
import javasnes.util.types.vars.abstracts.SnesType;
import javasnes.util.types.vars.scalar.data.SnesChar;

/**
 * Represents a ternary operator in SNES C code.
 * A ternary operator is used to choose between two values based on a condition.
 */
public class OperatorTernary extends SnesOperator {

    {
        this.requiredVars = 3;
    }

    /**
     * The condition of the ternary operator
     */
    public SnesOperator condition;

    /**
     * The true branch of the ternary operator
     */
    public SnesType trueBranch;

    /**
     * The false branch of the ternary operator
     */
    public SnesType falseBranch;

    /**
     * Constructor for OperatorTernary. Default constructor.
     * This constructor is used to create a ternary operator with the given parameters.
     * The condition, trueBranch and falseBranch are set to the given parameters as SnesType.
     * Their names are used to generate the source code.
     * 
     * @param condition the condition of the ternary operator
     * @param trueBranch the true branch of the ternary operator
     * @param falseBranch the false branch of the ternary operator
     */
    public OperatorTernary(
        SnesOperator condition, SnesType trueBranch, SnesType falseBranch
    ) {
        this.condition = condition;
        this.trueBranch = trueBranch;
        this.falseBranch = falseBranch;
    }

    /**
     * Constructor for OperatorTernary using String parameters instead of SnesType.
     * the trueBranch and falseBranch are converted to SnesChar using the provided String.
     * this make the given String accessible as default value for the ternary operator.
     * 
     * @param condition the condition of the ternary operator
     * @param trueBranch the true branch of the ternary operator
     * @param falseBranch the false branch of the ternary operator
     */
    public OperatorTernary(
        SnesOperator condition, String trueBranch, String falseBranch
    ) {
        this.condition = condition;
        this.trueBranch = new SnesChar(trueBranch);
        this.falseBranch = new SnesChar(falseBranch);
    }

    /**
     * Gets the source code for the ternary operator.
     * 
     * The generated source code is in the format of "condition ? trueBranch : falseBranch".
     * 
     * @return the source code for the ternary operator
     */
    @Override
    public final String getSourceCode() {
        return this.condition.getSourceCode() + " ? " + this.trueBranch.name + " : " + this.falseBranch.name;
    }
    
}
