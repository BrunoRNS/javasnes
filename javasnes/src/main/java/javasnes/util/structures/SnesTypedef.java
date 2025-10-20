package javasnes.util.structures;

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

    {
        this.type = 3;
    }

    /**
     * Creates a new type with the given fields.
     * 
     * The fields should be a map of field names to field types.
     * 
     * Example:
     * 
     * <pre>
     * Map<String, String> fields = new HashMap<>();
     * fields.put("u8", "field1");
     * fields.put("s16", "field2");
     * SnesTypedef myType = new SnesTypedef(fields);
     * </pre>
     * @param fields
     */
    public SnesTypedef(String name, Map<String, String> fields) {
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
     * <p>Where type1, type2, ... are the types of the fields in the typedef struct, and field1, field2, ... are the names of the fields.</p>
     */
    @Override
    public final void generateSourceCode() {
        StringBuilder sb = new StringBuilder();
        sb.append("typedef struct {\n");

        for (Map.Entry<String, String> field : this.fields.entrySet()) {
            sb.append("\t").append(field.getKey());
            sb.append(" ").append(field.getValue()).append(";\n");
        }

        sb.append("} ").append(this.name).append(";\n");

        this.sourceCode = sb.toString();
    }
    
}

