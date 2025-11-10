package javasnes.sneslib;

import javasnes.instruction.SnesRawInstruction;

public final class SnesSound {

    /**
     * Returns a SnesRawInstruction that calls the spcPlaySound() function.
     * spcPlaySound() is a function provided by the SNES pvsnelib that plays
     * the sound stored at the given index in the sound table.
     * 
     * @param index the index of the sound to play
     * @return a SnesRawInstruction that calls spcPlaySound()
     */
    public static final SnesRawInstruction spcPlaySound(int index) {
        return new SnesRawInstruction("spcPlaySound(" + index + ");");
    }

    /**
     * Returns a SnesRawInstruction that calls the spcPlaySound() function.
     * spcPlaySound() is a function provided by the SNES pvsnelib that plays
     * the sound stored at the given index in the sound table.
     * 
     * @param index the index of the sound to play
     * @return a SnesRawInstruction that calls spcPlaySound()
     */
    public static final SnesRawInstruction spcPlaySound(short index) {
        return new SnesRawInstruction("spcPlaySound(" + index + ");");
    }

    /**
     * Returns a SnesRawInstruction that calls the spcPlaySound() function.
     * spcPlaySound() is a function provided by the SNES pvsnelib that plays
     * the sound stored at the given index in the sound table.
     * 
     * @param index the index of the sound to play
     * @return a SnesRawInstruction that calls spcPlaySound()
     */
    public static final SnesRawInstruction spcPlaySound(byte index) {
        return new SnesRawInstruction("spcPlaySound(" + index + ");");
    }
    
    /**
     * Returns a SnesRawInstruction that calls the spcProcess() function.
     * spcProcess() is a function provided by the SNES pvsnelib that processes
     * all queued messages and updates the sound table.
     * 
     * @return a SnesRawInstruction that calls spcProcess()
     */
    public static final SnesRawInstruction spcProcess() {
        return new SnesRawInstruction("spcProcess();");
    }
    
}
