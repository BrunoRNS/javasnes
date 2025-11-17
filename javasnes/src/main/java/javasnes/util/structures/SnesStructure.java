package javasnes.util.structures;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javasnes.instruction.SnesInstruction;

/**
 * Represents a structure in SNES C source code.
 * 
 * This class is abstract and must be extended to create a structure.
 * The generated source code will depend on the type of structure.
 * The types of structures are:
 * 
 * Struct
 * Function Prototype
 * Enum
 * Typedef
 * Load Extern
 */
public abstract class SnesStructure extends SnesInstruction {

    /**
     * Name of the structure.
     */
    public String name;

    /**
     * Fields of the structure.
     */
    public Map<String, List<String>> fields;

    /**
     * Sets the value of a field in the structure.
     * 
     * @param fieldName name of the field to set
     * @param fieldValue value to set the field to
     */
    public void setField(String fieldName, Object fieldValue) {

        if (this.fields.get(fieldName) == null) {
            this.fields.put(fieldName, new ArrayList<>());
        }

        this.fields.get(fieldName).add(String.valueOf(fieldValue));

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
    public List<String> getField(String fieldName) {

        return this.fields.get(fieldName);

    }

    /**
     * All structures must implement source code generation.
     */
    public abstract void generateSourceCode();
    
}
