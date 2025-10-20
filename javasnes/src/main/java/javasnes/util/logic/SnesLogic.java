package javasnes.util.logic;

import java.util.ArrayList;
import java.util.List;

import javasnes.instruction.SnesInstruction;
import javasnes.util.operators.SnesOperator;

/**
 * Represents a logical operation in the SNES logic.
 * 
 * This class holds the operation being performed and the inner instructions
 * that make up this logic statement.
 * 
 */
public abstract class SnesLogic extends SnesInstruction {

    /**
     * The logical operation being performed.
     */
    public SnesOperator operation;

    /**
     * The List of inner instructions that make up this logic statement.
     */
    public List<SnesInstruction> innerInstructions = new ArrayList<>();
    
}
