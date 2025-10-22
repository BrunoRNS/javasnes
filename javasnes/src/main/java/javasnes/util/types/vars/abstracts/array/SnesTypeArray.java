package javasnes.util.types.vars.abstracts.array;

import javasnes.util.types.vars.abstracts.SnesType;

/**
 * Abstract base class for all SNES array types.
 * This class provides a common foundation for various array implementations.
 */
public abstract class SnesTypeArray extends SnesType {

    /**
     * The size of the array.
     * 
     * It is fixed, because malloc is not fully implemented in tcc-816 compiler,
     * but you still can try to use malloc(or calloc) using a RawInstruction but is not recommended.
     * 
     * It cannot exceed the SNES random access memory limit, otherwise it will cause malfunction.
     */
    public Short[] length = null; 

    /**
     * The number of dimensions of the array.
     * 
     * Default is 1 for a one-dimensional array.
     * 
     * This field can be extended in the future to support multi-dimensional arrays.
     */
    public Byte dimensions = 1;

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
     * 
     * With default value:
     * - "u8 myArray[10] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};"
     * - "s16 values[1] = {0};"
     * - "char buffer[5] = {'a', 'b', 'c', 'd', 'e'};"
     * 
     * With multiple dimensions:
     * - "u8 matrix[3][4];"
     * - "s16 tensor[2][2][2];"
     * 
     * With multiple dimensions and default value:
     * - "u8 matrix[2][2] = {{0, 1}, {2, 3}};"
     * - "s16 tensor[2][2][2] = {{{0, 1}, {2, 3}}, {{4, 5}, {6, 7}}};"
     * 
     */
    @Override
    public void generateSourceCode() {

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s %s", this.type, this.name));

        for (int i = 0; i < this.dimensions; i++) {

            sb.append(String.format("[%d]", this.length[i]));

        }

        if (this.defaultValue != null && !this.defaultValue.isEmpty()) {

            sb.append(String.format(" = %s", this.defaultValue));
            
        }

        sb.append(";");

        this.sourceCode = sb.toString();

    }

    public SnesTypeArray() {}
    
}
