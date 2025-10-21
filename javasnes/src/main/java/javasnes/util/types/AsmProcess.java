package javasnes.util.types;

import javasnes.instruction.SnesAsmInstruction;

/**
 * The ASMprocess class provides a constructor for creating assembly process
 * based on SnesAsmInstruction. With this class, you can create assembly processes
 * that will generate assembly files for each process.
 */
public class AsmProcess extends SnesAsmInstruction {

    public String name;
    
    /**
     * Source code of the assembly process.
     * 
     * Useful for generating assembly code from higher-level constructs.
     * This AsmProcess can then be integrated into the final assembly output.
     * 
     * @param name
     * @param sourceCode
     */
    public AsmProcess(String name, String sourceCode) {
        this.name = name;
        this.sourceCode = sourceCode;
    }

}
