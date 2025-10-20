package javasnes.util.structures;

import java.util.Map;

public class SnesStruct extends SnesStructure {

    {
        this.type = 0;
    }

    /**
     * Creates a new struct with the given fields.
     * 
     * The fields should be a map of field names to field types.
     * 
     * Example:
     * 
     * <pre>
     * Map<String, String> fields = new HashMap<>();
     * fields.put("u8", "field1");
     * fields.put("s16", "field2");
     * SnesStruct myStruct = new SnesStruct(fields);
     * </pre>
     * @param fields
     */
    public SnesStruct(String name, Map<String, String> fields) {
        this.fields = fields;
        this.generateSourceCode();
    }

    /**
     * Generates the source code for the struct.
     * 
     * <p>This will generate code in the format of:</p>
     * 
     * <pre>
     * struct name {
     *     type1 field1,
     *     type2 field2,
     *     ...
     * };
     * </pre>
     * 
     * <p>Where type1, type2, ... are the types of the fields in the struct, and field1, field2, ... are the names of the fields.</p>
     */
    @Override
    public final void generateSourceCode() {
        StringBuilder sb = new StringBuilder();
        sb.append("struct ").append(this.name).append(" {\n");

        for (Map.Entry<String, String> field : this.fields.entrySet()) {
            sb.append("\t").append(field.getKey());
            sb.append(" ").append(field.getValue()).append(";\n");
        }

        sb.append("};\n");

        this.sourceCode = sb.toString();
    }
    
}
