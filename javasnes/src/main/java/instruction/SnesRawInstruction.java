package instruction;

/**
 * Represents a raw C source code instruction to be included in the ROM.
 * 
 * This class allows you to add any raw C source code as an instruction in your ROM,
 * providing flexibility to include code that may not be covered by existing instruction classes.
 * 
 * It extends the SnesInstruction class, inheriting its properties and methods.
 * 
 * Example usage:
 * *     SnesRawInstruction rawInst = new SnesRawInstruction("int myGlobalVar = 0;", true);
 * *     SnesRawInstruction localInst = new SnesRawInstruction("myGlobalVar++;", false);
 * 
 * These instructions can then be added to the appropriate collections in your ROM structure.
 * 
 */
public class SnesRawInstruction extends SnesInstruction {
    
    /**
     * This method constructs a SnesRawInstruction instance with the provided source code and global flag.
     * 
     * Many times, you may want to add raw C source code instructions to your ROM that are not covered by
     * the existing instruction classes. In such cases, you can use this class to create a raw instruction.
     * 
     * Its a simple wrapper around the SnesInstruction class that allows you to add any C source code, and
     * give you freedom to use any C code you want, even if there's no specific class for it.
     * 
     * The `code` parameter is the C source code you want to add, and the `global` parameter indicates
     * whether the instruction is a global declaration (true) or a local declaration (false).
     * 
     * The constructor validates the input to ensure that the source code is not null, not empty, and
     * does not contain new lines, and that the global flag is not null. If any of these conditions are not met,
     * an IllegalArgumentException is thrown with a descriptive message.
     * 
     * Usage example:
     * *     SnesRawInstruction rawInst = new SnesRawInstruction("int myGlobalVar = 0;", true);
     * *     SnesRawInstruction localInst = new SnesRawInstruction("myGlobalVar++;", false);
     * 
     * These instructions can then be added to the appropriate collections in your ROM structure.
     * 
     * @param code the raw C source code for the instruction.
     * @param global true if the instruction is a global declaration, false if local.
     * 
     * @throws IllegalArgumentException if the code is null, empty, contains new lines, or if global is null.
     */
    public SnesRawInstruction(String code) throws IllegalArgumentException {

        this.sourceCode = code.trim();

        try {

            this.validate();
            
        } catch (IllegalArgumentException e) {

            throw new IllegalArgumentException("Invalid SnesRawInstruction: " + e.getMessage());

        }

    }

    /**
     * Validates the SnesRawInstruction instance.
     * 
     * This method checks that the sourceCode is not null, not empty, does not contain new lines,
     * and that the global field is not null. If any of these conditions are not met,
     * an IllegalArgumentException is thrown with a descriptive message.
     * 
     * @throws IllegalArgumentException if the SnesRawInstruction is invalid.
     */
    public final void validate() throws IllegalArgumentException {

        if (this.sourceCode == null) {

            throw new IllegalArgumentException("SnesRawInstruction must have sourceCode defined.");

        }

        if (this.sourceCode.isEmpty()) {

            throw new IllegalArgumentException("SnesRawInstruction's sourceCode can't be empty.");

        }

        if (this.sourceCode.contains("\n")) {

            throw new IllegalArgumentException("SnesRawInstruction's sourceCode can't contain new lines.");

        }

    }


}
