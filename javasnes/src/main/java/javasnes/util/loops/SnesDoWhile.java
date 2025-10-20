package javasnes.util.loops;

import java.util.List;

import javasnes.instruction.SnesInstruction;
import javasnes.util.operators.SnesOperator;

/**
 * Represents a do-while loop structure in the SNES logic.
 * 
 * This class holds the condition that must be met for the loop to continue,
 * as well as the inner instructions that make up the do-while loop.
 */
public class SnesDoWhile extends SnesLoop {

    /**
     * Constructor for SnesDoWhile.
     * 
     * This constructor initializes the condition of the do-while loop.
     * If you want to add inner instructions, use the other constructor.
     * @param condition the condition that must be met for the loop to continue
     */
    public SnesDoWhile(
        SnesOperator condition
    ) {
        this.condition = condition;
        this.generateSourceCode();
    }

    /**
     * Constructor for SnesDoWhile.
     * 
     * This constructor initializes the condition and inner instructions of the 
     * do-while loop.
     * @param condition the condition that must be met for the loop to continue
     * @param innerInstructions the instructions to execute within the loop
     */
    public SnesDoWhile(
        SnesOperator condition,
        List<SnesInstruction> innerInstructions
    ) {
        this.condition = condition;
        this.innerInstructions = innerInstructions;
        this.generateSourceCode();
    }

    /**
     * Generates the source code for the do-while loop statement.
     * 
     * This method constructs the C source code for the do-while loop statement,
     * which calls all the instructions provided in the constructor, and checks
     * the condition after running the instructions.
     * 
     * For example, if the condition is "i < 10" and the inner instructions are
     * "i++;" and "sum += i;", the generated source code will be:
     * 
     * <pre>
     * do {
     *     i++;
     *     sum += i;
     * } while (i < 10);
     * </pre>
     * 
     * The generated source code is returned as a string, and is stored in
     * the `sourceCode` field of the object.
     */
    @Override
    public final void generateSourceCode() {
        StringBuilder sb = new StringBuilder();

        sb.append("do {\n");

        for (SnesInstruction instruction : this.innerInstructions) {
            sb.append("\t").append(instruction.sourceCode).append("\n");
        }

        sb.append("} while (");
        sb.append(this.condition.sourceCode);
        sb.append(");");

        this.sourceCode = sb.toString();
    }
    
}
