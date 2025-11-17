package javasnes.util.structures;

import java.util.Map;

import javasnes.instruction.SnesInstruction;

/**
 * Represents an enum structure in SNES C code.
 */
public class SnesEnum extends SnesInstruction {

    @SuppressWarnings("FieldMayBeFinal")
    private Map<String, String> fields;

    public String name = null;

    /**
     * Creates a new enum with the given name and fields.
     * @param name name of the enum
     * @param fields fields of the enum
     */
    public SnesEnum(String name, Map<String, String> fields) {
        this.name = name;
        this.fields = fields;
        this.generateSourceCode();
    }

    public SnesEnum(Map<String, String> fields) {
        this.fields = fields;
        this.generateSourceCode();
    }

    /**
     * Sets the value of a field in the enum.
     * 
     * <p>If the field value is not an integer, a warning message will be printed 
     * to the console and the value will not be set.</p>
     * 
     * @param fieldName name of the field to set
     * @param fieldValue value to set the field to
     * @throws NumberFormatException if the field value is null
     */
    @SuppressWarnings("UnnecessaryTemporaryOnConversionFromString")
    public void setField(String fieldName, Object fieldValue) {

        try {
            fieldValue = Integer.parseInt(String.valueOf(fieldValue));
        } catch (NumberFormatException e) {
            System.err.println(
                "Warning: Enum field value must be an integer." +
                " Exiting setField without setting value."
            );
            return;
        }

        this.fields.put(fieldName, String.valueOf(fieldValue));

    }

    /**
     * Generates the source code for the enum.
     * 
     * <p>This will generate code in the format of:</p>
     * 
     * <pre>
     * enum {
     *     FieldName1 = 1,
     *     FieldName2 = 2,
     *     ...
     * };
     * </pre>
     * 
     * <p>Where FieldName1, FieldName2, ... are the names of the fields in the enum,
     * and 1, 2, ... are the values of the fields.</p>
     * 
     * @throws NumberFormatException if any of the field values are null.
     */
    public final void generateSourceCode() throws NumberFormatException {

        StringBuilder sb = new StringBuilder();

        sb.append("enum ").append(this.name == null ? "" : (this.name + " ")).append("{\n");

        for (String fieldName : this.fields.keySet()) {

            String fieldValue = this.fields.get(fieldName);

            if (fieldValue == null) {
                throw new NumberFormatException("Enum field value cannot be null.");
            }

            sb.append("\t").append(fieldName);
            sb.append(" = ").append(Integer.parseInt(fieldValue));
            sb.append(",\n");
            
        }

        sb.append("};\n");

        this.sourceCode = sb.toString();

    }
    
}
