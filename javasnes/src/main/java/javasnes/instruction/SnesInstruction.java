package javasnes.instruction;

/**
 * Base class for all SNES pvsnelib C source code instructions.
 * Each instruction must extend this class and provide the C source code
 * in the `sourceCode` field.
 * 
 * If an instruction does not provide the C source code, an exception
 * will be thrown when generating the final C source code for the ROM.
 * 
 * You can use this abstract class as type for collections of instructions.
 * 
 * For example:
 * 
 *     List<SnesInstruction> instructions = new ArrayList<>();
 * 
 */
public abstract class SnesInstruction {

    /**
     * All instructions must have SNES pvsneslib C source code associated with them.
     * This is used when generating the final C source code for the ROM.
     * 
     * It is null by default and must be set by the specific instruction subclass.
     * If it is not set, an exception will be thrown when generating the C source code.
     */
    public String sourceCode = null;

    public SnesInstruction() {}
        
}