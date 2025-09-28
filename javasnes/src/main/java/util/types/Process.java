package util.types;

import java.util.List;

import instruction.SnesInstruction;
import util.types.vars.abstracts.SnesType;
import util.types.vars.abstracts.pointer.SnesTypePointer;
import util.types.vars.abstracts.scalar.SnesTypeScalar;

public class Process {

    /*
     * The name of the process.
     * 
     * null if it is not initialized.
     * 
     * It must be a valid C identifier.
     * 
     * It must not be a C reserved keyword.
     * 
     * It must not be the same as any other process or variable name in the same scope.
     * 
     * Even using the sourceCode field directly, this field must be set, as it is used by other Processes and
     * by the Processor class when calling this process.
     */
    public String name = null;

    /*
     * This refers to the return type of the process.
     * 
     * null if it is not initialized.
     * 
     * 0 if it is a void process.
     * 
     * 1 if it returns an unsigned 8-bit integer.
     * 2 if it returns an unsigned 16-bit integer.
     * 3 if it returns an unsigned 32-bit integer.
     * 4 if it returns a signed 8-bit integer.
     * 5 if it returns a signed 16-bit integer.
     * 6 if it returns a signed 32-bit integer.
     * 7 if it returns a BrrSample.
     * 8 if it returns a char.
     * 
     * 11 if it returns an unsigned 8-bit pointer.
     * 12 if it returns an unsigned 16-bit pointer.
     * 13 if it returns an unsigned 32-bit pointer.
     * 14 if it returns a signed 8-bit pointer.
     * 15 if it returns a signed 16-bit pointer.
     * 16 if it returns a signed 32-bit pointer.
     * 17 if it returns a BrrSample pointer.
     * 18 if it returns a char pointer.
     * 
     */
    public Byte returnType = null;

    /**
     * List of scalar arguments (by value)
     * List of pointer arguments (by reference)
     * 
     * null if not initialized
     * 
     * If the process has no arguments, these lists must be null.
     * 
     * If the process has arguments, these lists must be initialized with the appropriate types.
     */
    public List<SnesTypeScalar> args = null;
    public List<SnesTypePointer> pointerArgs = null;
    
    /**
     * List of instructions that make up the process body.
     * 
     * If you dont provide the source code directly, this list must be initialized, otherwise
     * an exception will be thrown when generating the C source code for the ROM.
     * 
     * The instructions must be in the correct order, as they will be concatenated in the same order.
     * 
     * for example:
     * 
     * {
     *      new SnesU8("myVariable", 0), 
     *      new ConsoleDrawText("myVariable", other args)
     * };
     * 
     * Considerating that the returnType is void and returnVar is a SnesVoid type, and there are no args, nor
     * pointerArgs, and the name of the process is myProcess this will generate the following C source code:
     * 
     * #include <snes.h> // always included by default by the App class that generates the final C source code
     * 
     * void myProcess() {
     *     u8 myVariable = 0; // from new SnesU8("myVariable", 0)
     *     consoleDrawText(myVariable, other args); // from new ConsoleDrawText("myVariable", other args)
     *     return; // from the returnVar field, which is a SnesVoid type
     * }
     * 
     * The return instruction cant be included in this list, it must be provided separately
     * in the returnVar field.
     */
    public List<SnesInstruction> instructions = null;

    /**
     * The variable that will be returned by the process.
     * If the returnType is void, this must be a SnesVoid type.
     * 
     * If the returnType is not void, this must be of the appropriate type.
     * 
     * If you dont provide the source code directly, this field must be initialized, otherwise
     * an exception will be thrown when generating the C source code for the ROM.
     */
    public SnesType returnVar = null;

    /**
     * The C source code of the process.
     * 
     * If you provide the source code directly, this field must be initialized, otherwise
     * an exception will be thrown when generating the C source code for the ROM.
     * 
     * If you dont provide the source code directly, this field will be generated from
     * the other fields when calling the getSourceCode() method.
     * 
     * It is null by default and will be generated when calling the getSourceCode() method.
     */
    public String sourceCode = null;

    /**
     * Constructor for the Process class.
     * 
     * This constructor allows you to provide the C source code directly.
     * It requires the name, returnType, args, pointerArgs and sourceCode fields to be initialized.
     * If you provide the source code directly, the instructions and returnVar fields will be ignored.
     * 
     * This is useful for complex processes that are easier to write directly in C.
     * 
     * @param name
     * @param returnType
     * @param args
     * @param pointerArgs
     * @param sourceCode
     */
    public Process(
        String name, byte returnType,
        List<SnesTypeScalar> args, List<SnesTypePointer> pointerArgs,
        String sourceCode
    ) {

        this.name = name;

        this.returnType = returnType;

        this.args = args;
        this.pointerArgs = pointerArgs;

        this.sourceCode = sourceCode;

    }

    /**
     * Constructor for the Process class.
     * 
     * This constructor allows you to provide the instructions and returnVar fields,
     * and the C source code will be generated from these fields when calling the getSourceCode() method.
     * It requires the name, returnType, args, pointerArgs, instructions and returnVar fields to be initialized.
     * 
     * This is useful for simple processes that can be easily constructed from existing instructions.
     * 
     * @param name
     * @param returnType
     * @param args
     * @param pointerArgs
     * @param instructions
     * @param returnVar
     */
    public Process(
        String name, byte returnType,
        List<SnesTypeScalar> args, List<SnesTypePointer> pointerArgs,
        List<SnesInstruction> instructions, SnesType returnVar
    ) {

        this.name = name;

        this.returnType = returnType;

        this.args = args;
        this.pointerArgs = pointerArgs;

        this.instructions = instructions;
        this.returnVar = returnVar;

    }

    private String generateSourceCode() {
        // TODO: implement this method to generate the C source code for the process.
        return null;
    }

    /**
     * A getter for the sourceCode field.
     * 
     * If the sourceCode field is null, it will be generated from the other fields.
     * If it is not null, it will be returned as is.
     * 
     * @return the C source code of the process.
     */
    public String getSourceCode() {

        if (this.sourceCode == null) {

            this.sourceCode = this.generateSourceCode();

        }

        return this.sourceCode;

    }
    
}
