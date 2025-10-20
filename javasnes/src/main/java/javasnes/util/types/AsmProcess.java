package javasnes.util.types;

import javasnes.instruction.SnesInstruction;

public class AsmProcess extends SnesInstruction {
    
    /**
     * Source code of the assembly process.
     * 
     * Useful for generating assembly code from higher-level constructs.
     * This AsmProcess can then be integrated into the final assembly output.
     * 
     * @param sourceCode
     */
    public AsmProcess(String sourceCode) {
        
        this.sourceCode = sourceCode;

    }

}
