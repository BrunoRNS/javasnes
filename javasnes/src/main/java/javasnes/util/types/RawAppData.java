package javasnes.util.types;

/**
 * Represents a raw AppData object.
 * 
 * If you need to store raw ASM source code in the AppData object, you can use this class.
 */
public class RawAppData extends AppData {

    /**
     * You should access this field directly.
     * 
     * myAppData.sourceCode = "some asm code";
     * 
     * System.out.println(myAppData.generateSourceCode());
     * 
     * Useful for debugging and testing purposes.
     */
    public String sourceCode;

    /**
     * Generates the ASM source code representation of this AppData object.
     * 
     * This method simply returns the sourceCode field of this AppData object.
     * 
     * @return the ASM source code representation of this AppData object.
     */
    @Override
    public String generateSourceCode() {

        return this.sourceCode;

    }
    
}
