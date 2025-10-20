package javasnes.util.logic;

import java.util.List;

import javasnes.instruction.SnesInstruction;
import javasnes.util.operators.SnesOperator;

/**
 * Represents an else-if statement in the SNES logic.
 * 
 * This class holds the condition (operation) and the inner instructions
 * that make up the else-if statement.
 */
public class SnesElseIf extends SnesLogic {

    /**
     * Constructor for SnesElseIf.
     * 
     * Initializes the operation and inner instructions of the else-if statement.
     * Else-if statements require a condition, represented by the operation.
     * @param operation the condition for the else-if statement
     * @param innerInstructions the instructions to execute if the condition is met
     */
    public SnesElseIf(SnesOperator operation, List<SnesInstruction> innerInstructions) {

        this.operation = operation;
        this.innerInstructions = innerInstructions;

    }
    
}
