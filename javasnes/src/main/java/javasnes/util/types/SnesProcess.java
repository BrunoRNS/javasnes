package javasnes.util.types;

import java.util.Arrays;
import java.util.List;

import javasnes.instruction.SnesInstruction;
import javasnes.util.types.vars.abstracts.SnesType;
import javasnes.util.types.vars.abstracts.pointer.SnesTypePointer;
import javasnes.util.types.vars.abstracts.scalar.SnesTypeScalar;
import javasnes.util.types.vars.scalar.data.SnesVoid;

public class SnesProcess {

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
     * Returns a string representation of the return type of the process.
     * 
     * The return type is represented by a string that is a valid C identifier.
     * 
     * The return type is determined by the value of the returnType field.
     * 
     * If the returnType field is null, this method throws a NullPointerException.
     * 
     * If the returnType field is not one of the valid return types, this method throws an
     * IllegalArgumentException with a descriptive message.
     * 
     * @return a string representation of the return type of the process.
     * @throws IllegalArgumentException if the returnType field is not one of the valid return types.
     * @throws NullPointerException if the returnType field is null.
     */
    private String getReturnTypeString() throws IllegalArgumentException, NullPointerException {

        if (this.returnType == null) {

            throw new NullPointerException("Return type is null");

        }

        switch (this.returnType) {

            case 0:
                return "void";
            
            case 1:
                return "u8";
            
            case 2:
                return "u16";
            
            case 3:
                return "u32";
            
            case 4:
                return "s8";
            
            case 5:
                return "s16";
            
            case 6:
                return "s32";
            
            case 7:
                return "brrsamples";
            
            case 8:
                return "char";
            
            case 11:
                return "u8*";
            
            case 12:
                return "u16*";
            
            case 13:
                return "u32*";
            
            case 14:
                return "s8*";
            
            case 15:
                return "s16*";
            
            case 16:
                return "s32*";
            
            case 17:
                return "brrsamples*";
            
            case 18:
                return "char*";
        
            default:
                throw new IllegalArgumentException("Invalid return type");
        }

    }

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
     * Generates a string representation of the arguments of the process.
     * 
     * The string is formatted as a C argument list, with each argument separated by a comma.
     * 
     * If the process has no arguments, the method returns "void".
     * 
     * If the process has arguments, the method returns a string in the format of 
     * "type1 name1, type2 name2, ...".
     * 
     * The order of the arguments in the string is the same as the order of the arguments in the lists.
     * 
     * @return a string representation of the arguments of the process.
     */
    @SuppressWarnings("StringConcatenationInsideStringBufferAppend")
    private String getArgsString() {

        StringBuilder sb = new StringBuilder();

        if (this.args == null && this.pointerArgs == null) {

            return "void";

        }

        if (this.args != null) {

            for (int i = 0; i < this.args.size(); i++) {

                sb.append(

                    this.args.get(i).type + " " + this.args.get(i).name + 
                    (i == this.args.size() - 1 ? "" : ", ")

                );

            }

        }

        if (this.pointerArgs != null) {

            for (int i = 0; i < this.pointerArgs.size(); i++) {

                sb.append(

                    this.pointerArgs.get(i).type + " " + this.pointerArgs.get(i).name + 
                    (i == this.args.size() - 1 ? "" : ", ")
                    
                );

            }

        }

        return sb.toString();
        
    }

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
    public SnesProcess(
        String name, byte returnType,
        List<SnesTypeScalar> args, List<SnesTypePointer> pointerArgs,
        String sourceCode
    ) {

        if (name == null) {

            throw new RuntimeException(
                "The name field of the Process class cannot be null."
            );

        }

        if (name.equals("main") || name.equals("processor")) {

            throw new RuntimeException(
                "The name field of the Process class cannot be \"main\" or \"processor\"."
            );
        
        }

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
    public SnesProcess(
        String name, byte returnType,
        List<SnesTypeScalar> args, List<SnesTypePointer> pointerArgs,
        List<SnesInstruction> instructions, SnesType returnVar
    ) {

        if (name == null) {

            throw new RuntimeException("The name field of the Process class cannot be null.");

        }

        if (name.equals("main") || name.equals("processor")) {

            throw new RuntimeException("The name field of the Process class cannot be \"main\" or \"processor\".");
        
        }

        this.name = name;

        this.returnType = returnType;

        this.args = args;
        this.pointerArgs = pointerArgs;

        this.instructions = instructions;
        this.returnVar = returnVar;

    }

    /**
     * Constructor for the Process class.
     * 
     * This constructor allows you to provide the instructions and pointerArgs fields as
     * lists, and doesn't require the args field to be initialized.
     * 
     * The C source code will be generated from these fields when calling the
     * getSourceCode() method.
     * 
     * @param name
     * @param returnType
     * @param pointerArgs
     * @param instructions
     * @param returnVar
     */
    public SnesProcess(
        String name, byte returnType,
        List<SnesTypePointer> pointerArgs,
        List<SnesInstruction> instructions, SnesType returnVar
    ) {

        if (name == null) {

            throw new RuntimeException("The name field of the Process class cannot be null.");

        }

        if (name.equals("main") || name.equals("processor")) {

            throw new RuntimeException("The name field of the Process class cannot be \"main\" or \"processor\".");
        
        }

        this.name = name;

        this.returnType = returnType;

        this.args = null;
        this.pointerArgs = pointerArgs;

        this.instructions = instructions;
        this.returnVar = returnVar;

    }

    /**
     * Constructor for the Process class.
     * 
     * This constructor allows you to provide the instructions and args fields as lists.
     * 
     * This constructor doesn't require the pointerArgs field to be initialized.
     * 
     * The C source code will be generated from these fields when calling the 
     * getSourceCode() method.
     * 
     * @param name
     * @param returnType
     * @param instructions
     * @param returnVar
     * @param args
     */
    public SnesProcess(
        String name, byte returnType,
        List<SnesInstruction> instructions, SnesType returnVar,
        List<SnesTypeScalar> args
    ) {

        if (name == null) {

            throw new RuntimeException("The name field of the Process class cannot be null.");

        }

        if (name.equals("main") || name.equals("processor")) {

            throw new RuntimeException("The name field of the Process class cannot be \"main\" or \"processor\".");
        
        }

        this.name = name;

        this.returnType = returnType;

        this.args = args;
        this.pointerArgs = null;

        this.instructions = instructions;
        this.returnVar = returnVar;

    }

    /**
     * Constructor for the Process class.
     * 
     * This constructor allows you to provide the instructions field as list, and does
     * not require args or pointerArgs field to be initialized.
     * 
     * The C source code will be generated from the instructions field when calling 
     * the getSourceCode() method.
     * 
     * @param name
     * @param returnType
     * @param instructions
     * @param returnVar
     */
    public SnesProcess(
        String name, byte returnType,
        List<SnesInstruction> instructions, SnesType returnVar
    ) {

        if (name == null) {

            throw new RuntimeException("The name field of the Process class cannot be null.");

        }

        if (name.equals("main") || name.equals("processor")) {

            throw new RuntimeException("The name field of the Process class cannot be \"main\" or \"processor\".");
        
        }

        this.name = name;

        this.returnType = returnType;

        this.args = null;
        this.pointerArgs = null;

        this.instructions = instructions;
        this.returnVar = returnVar;

    }


    /**
     * Constructor for the Process class.
     *
     * This constructor allows you to provide the instructions, args and pointerArgs
     * fields as arrays, and the C source code will be generated from these fields when 
     * calling the getSourceCode() method.
     * 
     * @param name
     * @param returnType
     * @param args
     * @param pointerArgs
     * @param instructions
     * @param returnVar
     */
    public SnesProcess(
        String name, byte returnType,
        SnesTypeScalar[] args, SnesTypePointer[] pointerArgs,
        SnesInstruction[] instructions, SnesType returnVar
    ) {

        if (name == null) {

            throw new RuntimeException("The name field of the Process class cannot be null.");

        }

        if (name.equals("main") || name.equals("processor")) {

            throw new RuntimeException("The name field of the Process class cannot be \"main\" or \"processor\".");
        
        }

        this.name = name;

        this.returnType = returnType;

        this.args = Arrays.asList(args);
        this.pointerArgs = Arrays.asList(pointerArgs);

        this.instructions = Arrays.asList(instructions);
        this.returnVar = returnVar;

    }

    /**
     * Constructor for the Process class.
     * 
     * This constructor allows you to provide the instructions and args fields as arrays,
     * and does not require the pointerArgs field.
     * 
     * The C source code will be generated from these fields when calling the 
     * getSourceCode() method.
     * 
     * @param name
     * @param returnType
     * @param args
     * @param instructions
     * @param returnVar
     */
    public SnesProcess(
        String name, byte returnType,
        SnesTypeScalar[] args,
        SnesInstruction[] instructions, SnesType returnVar
    ) {

        if (name == null) {

            throw new RuntimeException("The name field of the Process class cannot be null.");

        }

        if (name.equals("main") || name.equals("processor")) {

            throw new RuntimeException("The name field of the Process class cannot be \"main\" or \"processor\".");
        
        }

        this.name = name;

        this.returnType = returnType;

        this.args = Arrays.asList(args);
        this.pointerArgs = null;

        this.instructions = Arrays.asList(instructions);
        this.returnVar = returnVar;

    }

    /**
     * Constructor for the Process class.
     * 
     * This constructor allows you to use the instructions and pointerArgs fields 
     * as arrays, and does not require the args field.
     * 
     * The C source code will be generated from these fields when calling the 
     * getSourceCode() method.
     * 
     * @param name
     * @param returnType
     * @param pointerArgs
     * @param instructions
     * @param returnVar
     */
    public SnesProcess(
        String name, byte returnType,
        SnesTypePointer[] pointerArgs,
        SnesInstruction[] instructions, SnesType returnVar
    ) {

        if (name == null) {

            throw new RuntimeException("The name field of the Process class cannot be null.");

        }

        if (name.equals("main") || name.equals("processor")) {

            throw new RuntimeException("The name field of the Process class cannot be \"main\" or \"processor\".");
        
        }

        this.name = name;

        this.returnType = returnType;

        this.args = null;
        this.pointerArgs = Arrays.asList(pointerArgs);

        this.instructions = Arrays.asList(instructions);
        this.returnVar = returnVar;

    }

    /**
     * Constructor for the Process class.
     * 
     * This constructor allows you to use the instructions as an array, and does not
     * require the args and pointerArgs fields.
     * 
     * The C source code will be generated from these fields when calling the 
     * getSourceCode() method.
     * 
     * @param name
     * @param returnType
     * @param instructions
     * @param returnVar
     */
    public SnesProcess(
        String name, byte returnType,
        SnesInstruction[] instructions, SnesType returnVar
    ) {

        if (name == null) {

            throw new RuntimeException("The name field of the Process class cannot be null.");

        }

        if (name.equals("main") || name.equals("processor")) {

            throw new RuntimeException("The name field of the Process class cannot be \"main\" or \"processor\".");
        
        }

        this.name = name;

        this.returnType = returnType;

        this.args = null;
        this.pointerArgs = null;

        this.instructions = Arrays.asList(instructions);
        this.returnVar = returnVar;

    }

    /**
     * Generates the C source code representation of this process.
     * 
     * This method constructs the C source code for the process from the instructions and returnVar fields.
     * It throws a RuntimeException if any instruction has a null sourceCode field.
     * 
     * The generated source code is a string in the format of 
     * "returnType name(args) {\n\tinstruction1;\n\tinstruction2;\n\t...\n\treturn returnVar;\n}"
     * 
     * @return the C source code representation of this process.
     */
    private String generateSourceCode() {

        StringBuilder sb = new StringBuilder();

        sb.append(this.getReturnTypeString());
        sb.append(" ").append(this.name).append("(");
        sb.append(this.getArgsString()).append(") {\n");

        for (SnesInstruction instruction : this.instructions) {

            if (instruction.sourceCode == null) {
                
                throw new RuntimeException(
                    "There's a null instruction source code in the process " + this.name + 
                    " at index " + instructions.indexOf(instruction) +
                    " of the instructions list."
                );

            }

            sb.append("\t").append(instruction.sourceCode);
            sb.append("\n");
        
        }

        if (this.returnVar instanceof SnesVoid) {

            sb.append("\treturn;");
        
        } else {

            sb.append("\treturn ").append(this.returnVar.name).append(";");

        }
        
        sb.append("\n}\n");

        return sb.toString();

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
