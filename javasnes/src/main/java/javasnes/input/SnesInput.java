package javasnes.input;

import javasnes.instruction.SnesInstruction;
import javasnes.instruction.SnesRawInstruction;
import javasnes.util.operators.SnesOperator;
import javasnes.util.operators.binary.OperatorBinAnd;
import javasnes.util.types.vars.scalar.number.unsigned.SnesU16;

public final class SnesInput {

    /**
     * Generates a SnesRawInstruction that calls the padsCurrent function with the given pad 
     * value. This function is used to get the current state of a pad.
     * The generated instruction is in the form of "padsCurrent(X)" where X is the pad value.
     * 
     * @param pad the value of the pad (0-4)
     * @return a SnesRawInstruction that calls the padsCurrent function with the given pad 
     * value
     */
    public static final SnesRawInstruction padsCurrent(byte pad) {

        return new SnesRawInstruction("padsCurrent(" + pad + ")");

    }

    /**
     * Generates a SnesRawInstruction that calls the snes_mplay5 definiton. 
     * This definition is used to know whether 5 players are connected or not.
     * 
     * @return a SnesRawInstruction that calls the snes_mplay5 definiton
     */
    public static final SnesRawInstruction snesMplay5() {
    
        return new SnesRawInstruction("snes_mplay5");
    
    }

    /**
     * A collection of SnesInstruction objects that represent the keys on the SNES controller.
     * 
     * @see SnesInstruction
     * @see SnesRawInstruction
     */
    public static final class keys {
        
        public static final SnesInstruction KEY_A = new SnesRawInstruction("KEY_A");
        public static final SnesInstruction KEY_B = new SnesRawInstruction("KEY_B");
        public static final SnesInstruction KEY_X = new SnesRawInstruction("KEY_X");
        public static final SnesInstruction KEY_Y = new SnesRawInstruction("KEY_Y");
        public static final SnesInstruction KEY_SELECT = new SnesRawInstruction("KEY_SELECT");
        public static final SnesInstruction KEY_START = new SnesRawInstruction("KEY_START");
        public static final SnesInstruction KEY_UP = new SnesRawInstruction("KEY_UP");
        public static final SnesInstruction KEY_DOWN = new SnesRawInstruction("KEY_DOWN");
        public static final SnesInstruction KEY_LEFT = new SnesRawInstruction("KEY_LEFT");
        public static final SnesInstruction KEY_RIGHT = new SnesRawInstruction("KEY_RIGHT");
        public static final SnesInstruction KEY_L = new SnesRawInstruction("KEY_L");
        public static final SnesInstruction KEY_R = new SnesRawInstruction("KEY_R");

    }

    /**
     * Returns a SnesOperator that checks if the A key is pressed.
     * The generated operator is in the form of "(padsCurrent(0) & KEY_A)".
     * This operator can be used in conditionals to check if the A key is pressed.
     * @return a SnesOperator that checks if the A key is pressed
     */
    public static final SnesOperator keyAPressed() {
        return new OperatorBinAnd(
            new SnesU16(SnesInput.padsCurrent((byte) 0).sourceCode),
            new SnesU16(SnesInput.keys.KEY_A.sourceCode)
        );
    }

    /**
     * Returns a SnesOperator that checks if the B key is pressed.
     * The generated operator is in the form of "(padsCurrent(0) & KEY_B)".
     * This operator can be used in conditionals to check if the B key is pressed.
     * @return a SnesOperator that checks if the B key is pressed
     */
    public static final SnesOperator keyBPressed() {
        return new OperatorBinAnd(
            new SnesU16(SnesInput.padsCurrent((byte) 0).sourceCode),
            new SnesU16(SnesInput.keys.KEY_B.sourceCode)
        );
    }
    
    /**
     * Returns a SnesOperator that checks if the X key is pressed.
     * The generated operator is in the form of "(padsCurrent(0) & KEY_X)".
     * This operator can be used in conditionals to check if the X key is pressed.
     * @return a SnesOperator that checks if the X key is pressed
     */
    public static final SnesOperator keyXPressed() {
        return new OperatorBinAnd(
            new SnesU16(SnesInput.padsCurrent((byte) 0).sourceCode),
            new SnesU16(SnesInput.keys.KEY_X.sourceCode)
        );
    }

    /**
     * Returns a SnesOperator that checks if the Y key is pressed.
     * The generated operator is in the form of "(padsCurrent(0) & KEY_Y)".
     * This operator can be used in conditionals to check if the Y key is pressed.
     * @return a SnesOperator that checks if the Y key is pressed
     */
    public static final SnesOperator keyYPressed() {
        return new OperatorBinAnd(
            new SnesU16(SnesInput.padsCurrent((byte) 0).sourceCode),
            new SnesU16(SnesInput.keys.KEY_Y.sourceCode)
        );
    }

    /**
     * Returns a SnesOperator that checks if the Select key is pressed.
     * The generated operator is in the form of "(padsCurrent(0) & KEY_SELECT)".
     * This operator can be used in conditionals to check if the Select key is pressed.
     * @return a SnesOperator that checks if the Select key is pressed
     */
    public static final SnesOperator keySelectPressed() {
        return new OperatorBinAnd(
            new SnesU16(SnesInput.padsCurrent((byte) 0).sourceCode),
            new SnesU16(SnesInput.keys.KEY_SELECT.sourceCode)
        );
    }

    /**
     * Returns a SnesOperator that checks if the Start key is pressed.
     * The generated operator is in the form of "(padsCurrent(0) & KEY_START)".
     * This operator can be used in conditionals to check if the Start key is pressed.
     * @return a SnesOperator that checks if the Start key is pressed
     */
    public static final SnesOperator keyStartPressed() {
        return new OperatorBinAnd(
            new SnesU16(SnesInput.padsCurrent((byte) 0).sourceCode),
            new SnesU16(SnesInput.keys.KEY_START.sourceCode)
        );
    }

    /**
     * Returns a SnesOperator that checks if the Up key is pressed.
     * The generated operator is in the form of "(padsCurrent(0) & KEY_UP)".
     * This operator can be used in conditionals to check if the Up key is pressed.
     * @return a SnesOperator that checks if the Up key is pressed
     */
    public static final SnesOperator keyUpPressed() {
        return new OperatorBinAnd(
            new SnesU16(SnesInput.padsCurrent((byte) 0).sourceCode),
            new SnesU16(SnesInput.keys.KEY_UP.sourceCode)
        );
    }

    /**
     * Returns a SnesOperator that checks if the Down key is pressed.
     * The generated operator is in the form of "(padsCurrent(0) & KEY_DOWN)".
     * This operator can be used in conditionals to check if the Down key is pressed.
     * @return a SnesOperator that checks if the Down key is pressed
     */
    public static final SnesOperator keyDownPressed() {
        return new OperatorBinAnd(
            new SnesU16(SnesInput.padsCurrent((byte) 0).sourceCode),
            new SnesU16(SnesInput.keys.KEY_DOWN.sourceCode)
        );
    }

    /**
     * Returns a SnesOperator that checks if the Left key is pressed.
     * The generated operator is in the form of "(padsCurrent(0) & KEY_LEFT)".
     * This operator can be used in conditionals to check if the Left key is pressed.
     * @return a SnesOperator that checks if the Left key is pressed
     */
    public static final SnesOperator keyLeftPressed() {
        return new OperatorBinAnd(
            new SnesU16(SnesInput.padsCurrent((byte) 0).sourceCode),
            new SnesU16(SnesInput.keys.KEY_LEFT.sourceCode)
        );
    }

    /**
     * Returns a SnesOperator that checks if the Right key is pressed.
     * The generated operator is in the form of "(padsCurrent(0) & KEY_RIGHT)".
     * This operator can be used in conditionals to check if the Right key is pressed.
     * @return a SnesOperator that checks if the Right key is pressed
     */
    public static final SnesOperator keyRightPressed() {
        return new OperatorBinAnd(
            new SnesU16(SnesInput.padsCurrent((byte) 0).sourceCode),
            new SnesU16(SnesInput.keys.KEY_RIGHT.sourceCode)
        );
    }

    /**
     * Returns a SnesOperator that checks if the L key is pressed.
     * The generated operator is in the form of "(padsCurrent(0) & KEY_L)".
     * This operator can be used in conditionals to check if the L key is pressed.
     * @return a SnesOperator that checks if the L key is pressed
     */
    public static final SnesOperator keyLPressed() {
        return new OperatorBinAnd(
            new SnesU16(SnesInput.padsCurrent((byte) 0).sourceCode),
            new SnesU16(SnesInput.keys.KEY_L.sourceCode)
        );
    }

    /**
     * Returns a SnesOperator that checks if the R key is pressed.
     * The generated operator is in the form of "(padsCurrent(0) & KEY_R)".
     * This operator can be used in conditionals to check if the R key is pressed.
     * @return a SnesOperator that checks if the R key is pressed
     */
    public static final SnesOperator keyRPressed() {
        return new OperatorBinAnd(
            new SnesU16(SnesInput.padsCurrent((byte) 0).sourceCode),
            new SnesU16(SnesInput.keys.KEY_R.sourceCode)
        );
    }

}
