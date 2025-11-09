package javasnes.input;

import javasnes.instruction.SnesInstruction;
import javasnes.instruction.SnesRawInstruction;

public class SnesInput {

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
    public static class keys {
        
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
    
}
