package javasnes.util.operators.math;

import javasnes.util.operators.SnesOperator;
import javasnes.util.types.vars.abstracts.scalar.number.SnesScalarNumber;
import javasnes.util.types.vars.scalar.number.signed.SnesS16;
import javasnes.util.types.vars.scalar.number.signed.SnesS8;

/**
 * The OperatorSub class represents the Subtraction operation in the SNES development 
 * context. It is used to Sub two values together and store the result in a variable.
 */
public class OperatorSub extends SnesOperator {

    {
        this.requiredVars = 2;
    }

    /**
     * The two values to Sub, represented as SnesScalarNumber objects.
     */
    public SnesScalarNumber value1;
    public SnesScalarNumber value2;

    /**
     * Constructs a new OperatorSub object with the specified values.
     * This is the default constructor for the OperatorSub class, and
     * gives the option to Sub values as SnesScalarNumber objects.
     * 
     * Their names are used in the getSourceCode() method.
     * 
     * @param value1 the first value (SnesScalarNumber)
     * @param value2 the second value (SnesScalarNumber)
     */
    public OperatorSub(SnesScalarNumber value1, SnesScalarNumber value2) {
        this.value1 = value1;
        this.value2 = value2;
    }

    /**
     * Constructs a new OperatorSub object with the specified values.
     * This constructor is used to Sub values as shorts(Signed 16-bit integers).
     * 
     * Their values are directly used in the getSourceCode() method.
     * @param value1 the first value (short)
     * @param value2 the second value (short)
     */
    public OperatorSub(short value1, short value2) {
        this.value1 = new SnesS16(String.valueOf(value1), String.valueOf(value1));
        this.value2 = new SnesS16(String.valueOf(value2), String.valueOf(value2));
    }

    /**
     * Constructs a new OperatorSub object with the specified values.
     * This constructor is used to Sub values as bytes(Signed 8-bit integers).
     * 
     * Their values are directly used in the getSourceCode() method.
     * @param value1 the first value (byte)
     * @param value2 the second value (byte)
     */
    public OperatorSub(byte value1, byte value2) {
        this.value1 = new SnesS8(String.valueOf(value1), String.valueOf(value1));
        this.value2 = new SnesS8(String.valueOf(value2), String.valueOf(value2));
    }

    /**
     * Gets the source code for the Subtraction operator.
     * 
     * The generated source code is in the format of "value1 + value2".
     * 
     * @return the source code for the Subtraction operator
     */
    @Override
    public String getSourceCode() {
        return value1.name + " - " + value2.name;
    }
    
}
