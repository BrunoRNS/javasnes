package javasnes.instruction;

/**
 * Represents a raw C instruction to be inserted into the generated code.
 * This class allows you to insert arbitrary C code into the instruction list,
 * preserving the sequence of instructions.
 * 
 * @example
 * <pre>
 * List<SnesInstruction> instructions = new ArrayList<>();
 * instructions.add(new SnesU8("myVariable", 255));
 * instructions.add(new SnesRawInstruction("myFunctionCall();"));
 * instructions.add(new SnesU16("myVariable2", 1024));
 * </pre>
 * This will insert the raw C code <pre>myFunctionCall();</pre> into the generated C code.
 * The sequence of instructions will be preserved.
 * In this example, the generated C code will look like:
 * <pre>
 * u8 myVariable = 255;
 * myFunctionCall();
 * u16 myVariable2 = 1024;
 * </pre>
 * 
 * @see SnesInstruction
 */
public class SnesRawInstruction extends SnesInstruction {
    
    /**
     * Creates a new SnesRawInstruction instance with the source code
     * of the C instruction, which will be validated.
     * 
     * You can use this class to insert raw C code into the instructions list.
     * 
     * For example:
     * <pre>
     * List<SnesInstruction> instructions = new ArrayList<>();
     * instructions.add(new SnesU8("myVariable", 255));
     * instructions.add(new SnesRawInstruction("myFunctionCall();"));
     * instructions.add(new SnesU16("myVariable2", 1024));
     * </pre>
     * This will insert the raw C code <pre>myFunctionCall();</pre> into the generated C code.
     * The sequence of instructions will be preserved.
     * In this example, the generated C code will look like:
     * <pre>
     * u8 myVariable = 255;
     * myFunctionCall();
     * u16 myVariable2 = 1024;
     * </pre>
     * 
     * The C code provided in the SnesRawInstruction will be inserted as-is, and must
     * not contain new lines.
     * 
     * @param code the raw C code to insert.
     * @throws IllegalArgumentException if the provided code is invalid.
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
