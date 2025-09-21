package util.types.vars.abstracts;

import instruction.SnesInstruction;

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
     * Returns a constant identifier for the subclasses of SnesType.
     *
     * This method must be overridden by each subclass to provide a unique identifier.
     * It is used to identify if the SnesType is a scalar type, pointer type, array type,
     * or an abstract type which isn't any of the above.
     *
     * If not overridden, it will default to "abstract".
     *
     * This identifier is not used for generating the C source code, but is used for type checking
     * before the C source code generation. If type checking fails, the generation might fail as well.
     */
    public String IDENTIFIER() {

        return "abstract";

    }
    
}
