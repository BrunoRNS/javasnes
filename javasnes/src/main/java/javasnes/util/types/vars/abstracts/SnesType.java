package javasnes.util.types.vars.abstracts;

import javasnes.instruction.SnesInstruction;

/**
 * Base class for all SNES variable types.
 * Each variable type must extend this class.
 * 
 * You can use this abstract class as type for collections of variable types.
 * 
 * For example:
 * 
 *     List<SnesType> variables = new ArrayList<>();
 * 
 */
public abstract class SnesType extends SnesInstruction {

    /**
     * All variable types must have a name associated with them.
     * This is used when generating the final C source code for the ROM.
     * 
     * It is null by default and must be set by the specific variable type subclass.
     * If it is not set, an exception will be thrown when generating the C source code.
     * 
     * It must be a valid C identifier.
     * 
     * It must not be a C reserved keyword.
     * 
     * It must not be the same as any other process or variable name in the same scope.
     * 
     * For example:
     * 
     *    SnesU8 myVariable = new SnesU8("myVariable"); // valid name <br>
     *    SnesU8 int = new SnesU8("int"); // invalid name, "int" is a reserved keyword <br>
     *    SnesU8 my Variable = new SnesU8("my Variable"); // invalid name, contains space <br><br>
     * 
     *    // can be a valid or invalid name, if used in the same scope is invalid, otherwise is valid <br>
     *    SnesU8 myVariable = new SnesU8("myVariable"); <br>
     * 
     * Make sure to set a valid name for each variable type, otherwise an exception will be thrown
     * when generating the C source code for the ROM.
     */
    public String name = null;

    /**
     * The type of the variable.
     * This is used when generating the final C source code for the ROM.
     * 
     * Types can be:
     * - scalar types: u8, s8, u16, s16, u32, s32, char, brrsamples, void
     * - pointer types: u8*, s8*, u16*, s16*, u32*, s32*, char*, brrsamples*, void*
     * 
     * It is null by default and must be set by the specific variable type subclass.
     * If it is not set, an exception will be thrown when generating the C source code.
     */
    public String type = null;

    /**
     * The default value of the variable.
     * This is used when generating the final C source code for the ROM.
     * 
     * It is null by default and must be set by the specific variable type subclass.
     * If it is not set, an exception will be thrown when generating the C source code.
     */
    public String defaultValue = null;

    /**
     * Returns a constant identifier for the subclasses of SnesType.
     *
     * This method must be overridden by each subclass to provide a unique identifier.
     * It is used to identify if the SnesType is a scalar type, pointer type or array type.
     *
     * This identifier is not used for generating the C source code, but is used for type checking
     * before the C source code generation. If type checking fails, the generation might fail as well.
     */
    public abstract String IDENTIFIER();

    /**
     * Generates the C source code representation of this SNES variable type.
     * This method must be implemented by each subclass to output the correct
     * C code for the specific variable type.
     */
    public abstract void generateSourceCode();
    
}
