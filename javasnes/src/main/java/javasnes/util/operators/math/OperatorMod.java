package javasnes.util.operators.math;

import javasnes.util.operators.SnesOperator;
import javasnes.util.types.vars.abstracts.scalar.number.SnesScalarNumber;
import javasnes.util.types.vars.scalar.number.signed.SnesS16;
import javasnes.util.types.vars.scalar.number.signed.SnesS8;

/**
 * The OperatorMod class represents the Mod operation in the SNES development 
 * context. It is used to Mod two values together and store the result in a variable.
 */
public class OperatorMod extends SnesOperator {

    {
        this.requiredVars = 2;
    }

    /**
     * The two values to Mod, represented as SnesScalarNumber objects.
     */
    public SnesScalarNumber value1;
    public SnesScalarNumber value2;

    /**
     * Constructs a new OperatorMod object with the specified values.
     * This is the default constructor for the OperatorMod class, and
     * gives the option to Mod values as SnesScalarNumber objects.
     * 
     * Their names are used in the getSourceCode() method.
     * 
     * @param value1 the first value (SnesScalarNumber)
     * @param value2 the second value (SnesScalarNumber)
     */
    public OperatorMod(SnesScalarNumber value1, SnesScalarNumber value2) {
        this.value1 = value1;
        this.value2 = value2;
    }

    /**
     * Constructs a new OperatorMod object with the specified values.
     * This constructor is used to Mod values as shorts(Signed 16-bit integers).
     * 
     * Their values are directly used in the getSourceCode() method.
     * @param value1 the first value (short)
     * @param value2 the second value (short)
     */
    public OperatorMod(short value1, short value2) {
        this.value1 = new SnesS16(String.valueOf(value1), String.valueOf(value1));
        this.value2 = new SnesS16(String.valueOf(value2), String.valueOf(value2));
    }

    /**
     * Constructs a new OperatorMod object with the specified values.
     * This constructor is used to Mod values as bytes(Signed 8-bit integers).
     * 
     * Their values are directly used in the getSourceCode() method.
     * @param value1 the first value (byte)
     * @param value2 the second value (byte)
     */
    public OperatorMod(byte value1, byte value2) {
        this.value1 = new SnesS8(String.valueOf(value1), String.valueOf(value1));
        this.value2 = new SnesS8(String.valueOf(value2), String.valueOf(value2));
    }

    /**
     * Gets the source code for the Mod operator.
     * 
     * The generated source code is in the format of "(value1 % value2)".
     * 
     * @return the source code for the Mod operator
     */
    @Override
    public String getSourceCode() {
        return "(" + this.value1.name + " % " + this.value2.name + ")";
    }
    
}
