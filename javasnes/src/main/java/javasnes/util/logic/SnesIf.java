package javasnes.util.logic;

import java.util.List;

import javasnes.instruction.SnesInstruction;
import javasnes.util.operators.SnesOperator;

/**
 * Represents an if statement in the SNES logic.
 * 
 * This class holds the condition (operation), the inner instructions
 * that make up the if statement, as well as optional else-if and else
 * statements.
 */
public class SnesIf extends SnesLogic {

    public List<SnesElseIf> elseIfStatements = null;
    public SnesElse elseStatement = null;

    /**
     * Constructor for SnesIf.
     * This constructor initializes the operation and inner instructions
     * of the if statement. It does not include else-if or else statements.
     * If you want to add else-if or else statements, use the other constructors.
     * @param operation the condition for the if statement
     * @param innerInstructions the instructions to execute if the condition is met
     */
    public SnesIf(
        SnesOperator operation, List<SnesInstruction> innerInstructions
    ) {
        this.operation = operation;
        this.innerInstructions = innerInstructions;
    }

    /**
     * Constructor for SnesIf with else-if statements.
     * This constructor initializes the operation, inner instructions,
     * and else-if statements of the if statement.
     * @param operation the condition for the if statement
     * @param innerInstructions the instructions to execute if the condition is met
     * @param elseIfStatements the list of else-if statements associated with this if statement
     */
    public SnesIf(
        SnesOperator operation,
        List<SnesInstruction> innerInstructions,
        List<SnesElseIf> elseIfStatements
    ) {
        this.operation = operation;
        this.innerInstructions = innerInstructions;
        this.elseIfStatements = elseIfStatements;
    }

    /**
     * Constructor for SnesIf with else statement.
     * This constructor initializes the operation, inner instructions,
     * and else statement of the if statement.
     * @param operation the condition for the if statement
     * @param innerInstructions the instructions to execute if the condition is met
     * @param elseStatement the else statement associated with this if statement
     */
    public SnesIf(
        SnesOperator operation,
        List<SnesInstruction> innerInstructions,
        SnesElse elseStatement
    ) {
        this.operation = operation;
        this.innerInstructions = innerInstructions;
        this.elseStatement = elseStatement;
    }

    /**
     * Constructor for SnesIf with else-if and else statements.
     * This constructor initializes the operation, inner instructions,
     * else-if statements, and else statement of the if statement.
     * @param operation the condition for the if statement
     * @param innerInstructions the instructions to execute if the condition is met
     * @param elseIfStatements the list of else-if statements associated with this if statement
     * @param elseStatement the else statement associated with this if statement
     */
    public SnesIf(
        SnesOperator operation,
        List<SnesInstruction> innerInstructions,
        List<SnesElseIf> elseIfStatements,
        SnesElse elseStatement
    ) {
        this.operation = operation;
        this.innerInstructions = innerInstructions;
        this.elseIfStatements = elseIfStatements;
        this.elseStatement = elseStatement;
    }

    /**
     * Generates the source code for the if statement.
     * 
     * This method generates the source code for the if statement, including
     * the condition, the inner instructions, the else-if statements, and the
     * else statement.
     * 
     * The generated source code is a string in the format of
     * if (condition) {
     *      instruction1;
     *      instruction2;
     *      ...
     * } else if (condition2) { // if any else-if statements
     *      instruction1;
     *      instruction2;
     *      ...
     * } ... {
     *      ...
     * } else { // if there is an else statement
     *      instruction1;
     *      instruction2;
     *      ...
     * }
     * 
     * @return the source code representation of the if statement.
     */
    public void generateSourceCode() {

        StringBuilder sb = new StringBuilder();

        sb.append("if (");
        sb.append(this.operation.getSourceCode());
        sb.append(") {\n");

        for (SnesInstruction instruction : this.innerInstructions) {
            sb.append("\t");
            sb.append(instruction.sourceCode);
            sb.append("\n");
        }

        sb.append("}");

        if (this.elseIfStatements != null) {

            for (SnesElseIf elseIfStatement : this.elseIfStatements) {

                sb.append(" else if (");
                sb.append(elseIfStatement.operation.getSourceCode());
                sb.append(") {\n");

                for (SnesInstruction instruction : elseIfStatement.innerInstructions) {
                    sb.append("\t");
                    sb.append(instruction.sourceCode);
                    sb.append("\n");
                }

                sb.append("}");

            }

        }

        if (this.elseStatement != null) {

            sb.append(" else {\n");
            
            for (SnesInstruction instruction : this.elseStatement.innerInstructions) {
                sb.append("\t");
                sb.append(instruction.sourceCode);
                sb.append("\n");
            }

            sb.append("}");

        }

        this.sourceCode = sb.toString();

    }
    
}
