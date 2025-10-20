package javasnes.util.types;

import javasnes.instruction.SnesAsmInstruction;

/**
 * The ASMprocess class provides a constructor for creating assembly process
 * based on SnesAsmInstruction. With this class, you can create assembly processes
 * that will generate assembly files for each process.
 */
public class AsmProcess extends SnesAsmInstruction {
    
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
