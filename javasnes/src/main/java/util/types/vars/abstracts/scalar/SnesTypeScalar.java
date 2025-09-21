package util.types.vars.abstracts.scalar;

import util.types.vars.abstracts.SnesType;

/**
 * Base class for all scalar SNES variable types.
 * Each scalar variable type must extend this class.
 * 
 * You can use this abstract class as type for collections of scalar variable types.
 * 
 * For example:
 * 
 *     List<SnesTypeScalar> scalarVariables = new ArrayList<>();
 * 
 */
public abstract class SnesTypeScalar extends SnesType {

    /**
     * The C type of the scalar variable.
     * 
     * It must be a valid C type.
     * 
     * Examples:
     * 
     * "u8" for unsigned 8-bit integer
     * "s16" for signed 16-bit integer
     * "char" for data byte values
     * "void" for void type
     * "t_objs" for object type
     * [...]
     * 
     * This field must be set by the specific scalar type subclass.
     */
    public String type = null;

    public String defaultValue = null;

    /**
     * Returns a constant identifier for the subclasses of SnesTypeScalar.
     * 
     * This method is an override of the IDENTIFIER method in the SnesType class.
     * It returns the string "scalar" to indicate that this type is a scalar type.
     * 
     * All scalar types will return this identifier.
     */
    @Override
    public String IDENTIFIER() {

        return "scalar";

    }

    /**
     * Generates the C source code for the scalar variable.
     * 
     * This method constructs the C declaration for the scalar variable based on its type,
     * name, and default value (if provided). If the type is "void", it sets the sourceCode
     * field to an empty string, as void cannot be used as a variable type.
     * 
     * The generated source code is stored in the sourceCode field inherited from SnesInstruction.
     * If the type or name is not set, an exception will be thrown when generating the final
     * C source code for the ROM.
     * 
     * For example:
     * 
     *   SnesU8 myVar = new SnesU8("myVar");
     *   myVar.defaultValue = "0";
     *   myVar.generateSourceCode();
     * 
     *   System.out.println(myVar.sourceCode); // Outputs: u8 myVar = 0;
     * 
     */
    public void generateSourceCode() {

        if (type.equals("void")) {

            /**
             * Void type cannot be used as a variable.
             * It can only be used as a return type for functions.
             * 
             * If the variable type is void, the sourceCode field is set to an empty string.
             * So it will be ignored when generating the final C source code for the ROM.
             */
            sourceCode = "";

            return;

        }

        sourceCode = 

            defaultValue == null ?

                type + " " + name + ";" // for e.g. u8 myVar;

                :

                type + " " + name + " = " + defaultValue + ";"; // for e.g. u8 myVar = 0;

    }
    
}
