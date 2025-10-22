package javasnes.instruction;

/**
 * Abstract class representing a SNES assembly instruction.
 * 
 * This class serves as a base for all SNES assembly instructions,
 * providing sourceCode String, which is the assembly code that can 
 * be extended by specific instruction implementations.
 */
public abstract class SnesAsmInstruction {

    /**
     * The source code of the assembly instruction.
     * 
     * This instruction will contain the assembly code of an entire file as a String.
     * For example:
     * 
     * <pre>
     * .include "hdr.asm"
     * .section ".rodata1" superfree
     * patterns:
     * .incbin "pvsneslib.pic"
     * patterns_end:
     * .ends
     * </pre>
     * 
     * This Example is a data.asm file content.
     */
    public String sourceCode;

    public SnesAsmInstruction() {}
    
}
