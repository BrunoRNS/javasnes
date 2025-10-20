package javasnes.util.loops;

import java.util.List;

import javasnes.instruction.SnesInstruction;
import javasnes.util.operators.SnesOperator;
import javasnes.util.types.vars.abstracts.SnesType;

public class SnesFor extends SnesLoop {

    /**
     * The iterator variable for the loop.
     * Can be u8 i, for example, which can be defined as new SnesU8("i", "0").
     * 
     * @see javasnes.util.types.vars.SnesU8
     */
    public SnesType iterator;

    /**
     * The incrementer that modifies the iterator each loop iteration.
     * 
     * @see javasnes.util.operators.SnesOperator
     */
    public SnesOperator incrementer;

    /**
     * Constructor for SnesFor.
     * 
     * This constructor initializes the iterator, condition, and incrementer of the for
     * loop. If you want to add inner instructions, use the other constructor.
     * @param iterator the iterator variable for the loop
     * @param condition the condition that must be met for the loop to continue
     * @param incrementer the incrementer that modifies the iterator each loop iteration
     */
    public SnesFor(
        SnesType iterator,
        SnesOperator condition,
        SnesOperator incrementer
    ) {
        this.iterator = iterator;
        this.condition = condition;
        this.incrementer = incrementer;

        this.generateSourceCode();
    }

    /**
     * Constructor for SnesFor.
     * 
     * This constructor initializes the iterator, condition, incrementer,
     * and inner instructions of the for loop.
     * @param iterator the iterator variable for the loop
     * @param condition the condition that must be met for the loop to continue
     * @param incrementer the incrementer that modifies the iterator each loop iteration
     * @param innerInstructions the instructions to execute within the loop
     */
    public SnesFor(
        SnesType iterator,
        SnesOperator condition,
        SnesOperator incrementer,
        List<SnesInstruction> innerInstructions
    ) {
        this.iterator = iterator;
        this.condition = condition;
        this.incrementer = incrementer;

        this.generateSourceCode();
    }

    /**
     * Generates the source code representation of the for loop.
     * 
     * This method generates the C source code for the for loop, including
     * the iterator declaration, the condition, the incrementer, and the inner
     * instructions.
     */
    @Override
    public final void generateSourceCode() {
        
        StringBuilder sb = new StringBuilder();

        sb.append("for (");
        sb.append(this.iterator.type).append(" ").append(this.iterator.name);

        if (this.iterator.defaultValue != null) {

            sb.append(" = ").append(this.iterator.defaultValue);

        }
        
        sb.append("; ");
        sb.append(this.condition.getSourceCode()).append("; ");
        sb.append(this.incrementer.getSourceCode());
        sb.append(") {\n");

        for (SnesInstruction instruction : this.innerInstructions) {
            sb.append("\t").append(instruction.sourceCode).append("\n");
        }

        sb.append("}\n");

        this.sourceCode = sb.toString();

    }
    
}
