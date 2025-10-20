package javasnes.util.operators.logical;

import javasnes.util.operators.SnesOperator;
import javasnes.util.types.vars.abstracts.SnesType;
import javasnes.util.types.vars.scalar.data.SnesChar;

/**
 * The OperatorDiferent class represents the Diferent operation in the SNES development
 * context. It is used to Diferent two values together and store the result in a variable.
 */
public class OperatorDiferent extends SnesOperator {

    /**
     * The values to compare.
     * SnesType values.
     */
    public SnesType value1;
    public SnesType value2;

    /**
     * Constructor for OperatorDiferent.
     * Use the value1 and value2 as SnesType
     * Their names will be used when comparing
     * 
     * OperatorDiferent(SnesType value1, SnesType value2);
     * value1.name != value2.name
     * 
     * @param value1
     * @param value2
     */
    public OperatorDiferent(SnesType value1, SnesType value2) {
        this.value1 = value1;
        this.value2 = value2;
    }

    /**
     * Constructor for OperatorDiferent.
     * Give the value1 and value2 as Strings
     * They are interpreted as they are so for example:
     * 
     * OperatorDiferent("A", "B");
     * A != B
     * 
     * OperatorDiferent("2", "3");
     * 
     * 2 != 3
     * 
     * @param value1
     * @param value2
     */
    public OperatorDiferent(String value1, String value2) {
        this.value1 = new SnesChar(value1, value1);
        this.value2 = new SnesChar(value2, value2);
    }

    /**
     * Gets the source code for the Diferent operator.
     * 
     * The generated source code is in the format of "(value1 != value2)".
     * 
     * @return the source code for the Diferent operator
     */
    @Override
    public final String getSourceCode() {
        return "(" + this.value1.name + " != " + this.value2.name + ")";
    }
    
}
