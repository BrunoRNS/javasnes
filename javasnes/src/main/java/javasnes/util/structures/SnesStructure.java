package javasnes.util.structures;

import java.util.Map;

import javasnes.instruction.SnesInstruction;

/**
 * Represents a structure in SNES C source code.
 * 
 * This class is abstract and must be extended to create a structure.
 * The generated source code will depend on the type of structure.
 * The types of structures are:
 * 
 * 0 = Struct
 * 1 = Function Prototype
 * 2 = Enum
 * 3 = Typedef
 * 4 = Load Extern
 */
public abstract class SnesStructure extends SnesInstruction {

    /**
     * Type of the structure:
     *
     * 0 = Struct
     * 1 = Function Prototype
     * 2 = Enum
     * 3 = Typedef
     * 4 = Load Extern
     */
    public Byte type = null;

    /**
     * Name of the structure.
     */
    public String name;

    /**
     * Fields of the structure.
     */
    public Map<String, String> fields;

    /**
     * Sets the value of a field in the structure.
     * 
     * @param fieldName name of the field to set
     * @param fieldValue value to set the field to
     */
    public void setField(String fieldName, Object fieldValue) {

        this.fields.put(fieldName, String.valueOf(fieldValue));

    }

    /**
     * Removes a field from the structure.
     * 
     * @param fieldName name of the field to remove
     */
    public void removeField(String fieldName) {

        this.fields.remove(fieldName);

    }

    /**
     * Get the value of a field in the structure.
     * 
     * @param fieldName name of the field
     * @return value of the field, or null if the field does not exist
     */
    public String getField(String fieldName) {

        return this.fields.get(fieldName);

    }

    /**
     * All structures must implement source code generation.
     */
    public abstract void generateSourceCode();
    
}
