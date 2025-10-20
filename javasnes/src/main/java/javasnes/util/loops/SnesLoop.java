package javasnes.util.loops;

import java.util.ArrayList;
import java.util.List;

import javasnes.instruction.SnesInstruction;
import javasnes.util.operators.SnesOperator;

/**
 * Represents a loop structure in the SNES logic.
 * 
 * This class is abstract and must be extended to create a loop.
 */
public abstract class SnesLoop extends SnesInstruction {

    /**
     * The List of inner instructions that make up this loop statement.
     */
    public List<SnesInstruction> innerInstructions = new ArrayList<>();

    /**
     * The condition that must be met for the loop to continue.
     * 
     * @see javasnes.util.operators.SnesOperator
     */
    public SnesOperator condition;

    /**
     * Adds an instruction to the list of inner instructions of this loop statement.
     * 
     * @param instruction the instruction to add
     */
    public void addInstruction(SnesInstruction instruction) {
        this.innerInstructions.add(instruction);
    }

    /**
     * Removes an instruction from the list of inner instructions of this loop statement.
     * 
     * @param instruction the instruction to remove
     */
    public void removeInstruction(SnesInstruction instruction) {
        this.innerInstructions.remove(instruction);
    }

    /**
     * Clears the list of inner instructions of this loop statement.
     * 
     * This method can be used to remove all instructions from the loop
     * statement, effectively resetting it to its initial state.
     */
    public void clearInstructions() {
        this.innerInstructions.clear();
    }

    /**
     * Sets the condition for the loop statement.
     * 
     * The condition is an operator that must be met for the loop to continue.
     * 
     * @param condition the condition to set
     */
    public void setCondition(SnesOperator condition) {
        this.condition = condition;
    }

    /**
     * Returns the condition of the loop statement.
     * 
     * The condition is an operator that must be met for the loop to continue.
     * 
     * @return the condition of the loop statement
    */
    public SnesOperator getCondition() {
        return this.condition;
    }

    /**
     * Generate the source code for the loop statement.
     * 
     * This method must be implemented by all subclasses.
     */
    public abstract void generateSourceCode();
    
}
