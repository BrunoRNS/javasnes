package javasnes.util.logic;

import java.util.List;

import javasnes.instruction.SnesInstruction;

/**
 * Represents an else statement in the SNES logic.
 * 
 * This class holds the inner instructions that make up the else statement.
 */
public class SnesElse extends SnesLogic {

    /**
     * Constructor for SnesElse.
     * Initializes the inner instructions of the else statement.
     * 
     * Does not take any conditions, as else statements are unconditional, and
     * the conditions are implied by the preceding if and else-if statements.
     * @param innerInstructions
     */
    public SnesElse(List<SnesInstruction> innerInstructions) {

        this.innerInstructions = innerInstructions;

    }
    
}
