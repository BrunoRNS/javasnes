package javasnes.util.loops;

import java.util.List;

import javasnes.instruction.SnesInstruction;
import javasnes.util.operators.SnesOperator;

/**
 * Represents a while loop structure in the SNES logic.
 * 
 * This class holds the condition that must be met for the loop to continue,
 * as well as the inner instructions that make up the while loop.
 */
public class SnesWhile extends SnesLoop {

    /**
     * Constructor for SnesWhile.
     * 
     * This constructor initializes the condition of the loop.
     * 
     * @param condition the condition that must be met for the loop to continue.
     */
    public SnesWhile(
        SnesOperator condition
    ) {
        this.condition = condition;
        this.generateSourceCode();
    }

    /**
     * Constructor for SnesWhile.
     * 
     * This constructor initializes the condition and inner instructions of the
     * loop.
     * 
     * @param condition the condition that must be met for the loop to continue.
     * @param innerInstructions the instructions to execute within the loop.
     */
    public SnesWhile(
        SnesOperator condition,
        List<SnesInstruction> innerInstructions
    ) {
        this.condition = condition;
        this.innerInstructions = innerInstructions;
        this.generateSourceCode();
    }

    /**
     * Generates the source code for the while loop statement.
     * 
     * This method constructs the C source code for the while loop statement,
     * which checks the condition and executes the inner instructions if the
     * condition is true.
     * 
     * The generated source code is a string in the format of
     * while (condition) {
     *     instruction1;
     *     instruction2;
     *     ...
     * }
     * 
     * The generated source code is returned as a string, and is stored in
     * the `sourceCode` field of the object.
     */
    @Override
    public final void generateSourceCode() {

        StringBuilder sb = new StringBuilder();

        sb.append("while (").append(this.condition.getSourceCode()).append(") {\n");

        for (SnesInstruction instruction : this.innerInstructions) {
            sb.append("\t").append(instruction.sourceCode).append("\n");
        }

        sb.append("}\n");

        this.sourceCode = sb.toString();
        
    }
    
}
