package util.types.vars.abstracts.pointer;

import util.types.vars.abstracts.SnesType;

/**
 * Abstract base class for all SNES pointer types. This class provides a common
 * foundation for various pointer implementations.
 */
public abstract class SnesTypePointer extends SnesType {

    /**
     * The type of the SNES pointer, represented as a String.
     * This field can be used to specify the data type or category associated with the pointer.
     * It may be null if the type is not specified.
     */
    public String type = null;

    /**
     * Returns a constant identifier for the subclasses of SnesTypePointer.
     *
     * This method is an override of the IDENTIFIER method in the SnesType
     * class. It returns the string "pointer" to indicate that this type is a
     * pointer type.
     *
     * All pointer types will return this identifier.
     */
    @Override
    public String IDENTIFIER() {

        return "pointer";

    }

    /**
     * Generates the C source code representation for this SNES pointer
     * variable. This method constructs a C declaration statement for a pointer
     * variable, using the type and defaultValue fields. If defaultValue is not
     * null, it initializes the pointer; otherwise, it only declares the
     * pointer.
     *
     * Example output: int* ptr = NULL; char* buffer;
     */
    @Override
    public void generateSourceCode() throws IllegalArgumentException {

        /**
         * Ensure type and name are set.
         * An exception is thrown if one of them are null or empty.
         */

        if (type == null || name == null ) {

            throw new IllegalArgumentException("Pointer's type and name must not be null");

        }

        if (type.isEmpty() || name.isEmpty()) {

            throw new IllegalArgumentException("Pointer's type and name must not be empty");

        }

        StringBuilder sb = new StringBuilder();
        sb.append(type).append("* ");


        sb.append(this.name);

        if (defaultValue != null && !defaultValue.isEmpty()) {

            sb.append(" = ").append(defaultValue);

        }

        sb.append(";");

        this.sourceCode = sb.toString();

    }

}
