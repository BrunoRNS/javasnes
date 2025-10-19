package util.structures;

import java.util.HashMap;
import java.util.HashSet;

import util.types.vars.abstracts.SnesType;

/**
 * Represents an extern load statement in SNES C code.
 */
public class SnesLoadExtern extends SnesStructure {

    {
        this.type = 4;
    }

    /**
     * Type of the extern statement.
     */
    public SnesType snesType;

    /**
     * Creates a new extern statement with multiple names and a single type.
     * @param names names of the extern variables
     * @param type type of the extern variables
     */
    public SnesLoadExtern(String[] names, SnesType type) {
        
        this.name = "extern";
        this.fields = new HashMap<>();

        this.snesType = type;

        for (String name_ : names) {
            this.fields.put(name_, type.type);
        }

        this.generateSourceCode();

    }

    /**
     * Creates a new extern statement with a single name and type.
     * @param name_ name of the extern variable
     * @param type type of the extern variable
     */
    public SnesLoadExtern(String name_, SnesType type) {

        this.name = "extern";
        this.fields = new HashMap<>();

        this.snesType = type;

        this.fields.put(name_, type.type);

        this.generateSourceCode();

    }

    /**
     * Generates the source code for the extern statement.
     * 
     * <p>This will generate code in the format of:</p>
     * 
     * <pre>
     * extern type name1, name2, ...;
     * </pre>
     * 
     * <p>Where type is the type of the extern statement, and name1, name2, ... are the names of the fields in the extern statement.</p>
     */
    @Override
    public final void generateSourceCode() {

        StringBuilder sb = new StringBuilder();

        sb.append("extern ").append(this.snesType.type);

        HashSet<String> fieldNames = new HashSet<>(this.fields.keySet());

        for (int i = 0; i < fieldNames.size(); i++) {

            sb.append(String.valueOf(fieldNames.toArray()[i]));

            if (i < fieldNames.size() - 1) {
                sb.append(", ");
            }

        }

        sb.append(";");

        this.sourceCode = sb.toString();
    }
    
}
