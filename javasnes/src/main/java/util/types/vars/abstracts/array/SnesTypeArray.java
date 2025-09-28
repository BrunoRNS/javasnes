package util.types.vars.abstracts.array;

import util.types.vars.abstracts.SnesType;

/**
 * Abstract base class for all SNES array types.
 * This class provides a common foundation for various array implementations.
 */
public abstract class SnesTypeArray extends SnesType {

    /**
     * The C type of the array variable.
     * 
     * It must be a valid C type.
     * 
     * Examples:
     * 
     * "u8" for an array of unsigned 8-bit integer
     * "s16" for an array of signed 16-bit integer
     * "char" for an array of data byte values
     * [...]
     * 
     * This field must be set by the specific array type subclass.
     */
    public String type = null;

    /**
     * The size of the array.
     * 
     * It is fixed, because malloc is not fully implemented in tcc-816 compiler,
     * but you still can try to use malloc(or calloc) using a RawInstruction but is not recommended.
     * 
     * It cannot exceed the SNES random access memory limit, otherwise it will cause malfunction.
     */
    public Short length = null; 

    /**
     * Returns a constant identifier for the subclasses of SnesTypeArray.
     * 
     * This method is an override of the IDENTIFIER method in the SnesType class.
     * It returns the string "array" to indicate that this type is an array type.
     * 
     * All array types will return this identifier.
     */
    @Override
    public String IDENTIFIER() {

        return "array";

    }

    /**
     * Generates the source code representation for the array variable.
     * The generated code is assigned to the {@code sourceCode} field in the format:
     * "{type} {name}[{length}];"
     * where {@code type} is the data type, {@code name} is the variable name,
     * and {@code length} is the size of the array.
     *
     * Examples of generated C source code:
     * - "u8 myArray[10];"
     * - "s16 values[256];"
     * - "char buffer[128];"
     */
    public void generateSourceCode() {

        this.sourceCode = String.format("%s %s[%d];", this.type, this.name, this.length);

    }
    
}
