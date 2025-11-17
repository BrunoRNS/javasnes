package javasnes.util.structures;

import java.util.List;
import java.util.Map;

/**
 * Represents a typedef structure in SNES C source code.
 * 
 * A typedef is a type definition that allows you to create a new type based on an existing type.
 * In this implementation, a typedef is represented as a struct with a name and fields.
 * 
 * The generated source code will be in the format:
 * 
 * <pre>
 * typedef struct {
    *     type1 field1;
    *     type2 field2;
    *     ...
    * } name;
 * </pre>
 * 
 * Where type1, type2, ... are the types of the fields in the typedef struct, and field1, field2, ... are the names of the fields.
 */
public class SnesTypedef extends SnesStructure {

    /**
     * Creates a new SnesTypedef instance.
     * 
     * <p>The fields parameter is a map where the keys are the types of the fields and 
     * the values are lists of field names of that type.</p>
     * 
     * @param name name of the typedef
     * @param fields map of fields in the typedef
     */
    public SnesTypedef(String name, Map<String, List<String>> fields) {
        this.name = name;
        this.fields = fields;
        this.generateSourceCode();
    }

    /**
     * Generates the source code for the typedef.
     * 
     * <p>This will generate code in the format of:</p>
     * 
     * <pre>
     * typedef struct {
     *     type1 field1;
     *     type2 field2;
     *     ...
     * } name;
     * </pre>
     * 
     * <p>Where type1, type2, ... are the types of the fields in the typedef struct, and
     * field1, field2, ... are the names of the fields.</p>
     */
    @Override
    public final void generateSourceCode() {

        this.validate();

        StringBuilder sb = new StringBuilder();
        sb.append("typedef struct {\n");

        for (Map.Entry<String, List<String>> field : this.fields.entrySet()) {
            sb.append("\t").append(field.getKey());
            sb.append(" ").append(String.join(", ", field.getValue())).append(";\n");
        }

        sb.append("} ").append(this.name).append(";\n");

        this.sourceCode = sb.toString();
    }

    /**
     * Validates the SnesTypedef instance.
     * 
     * This method checks that the name and fields are not null, and that the fields are 
     * not empty.
     * If any of these conditions are not met, an IllegalArgumentException is thrown with a
     * descriptive message.
     * 
     * @throws IllegalArgumentException if the SnesTypedef is invalid.
     */
    private void validate() throws IllegalArgumentException {

        if (this.name == null) {
            throw new IllegalArgumentException("Name cannot be null");
        }

        if (this.fields == null) {
            throw new IllegalArgumentException("Fields cannot be null");
        }

        if (this.fields.isEmpty()) {
            throw new IllegalArgumentException("Fields cannot be empty");
        }
        
    }
    
}

