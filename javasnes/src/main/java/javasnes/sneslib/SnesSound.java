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
    
    /**
     * Returns a SnesRawInstruction that calls the spcSetBank() function.
     * spcSetBank() is a function provided by the SNES pvsnelib that sets the sound bank 
     * to the given memory address.
     * 
     * @param bankMemAdress the memory address of the sound bank
     * @return a SnesRawInstruction that calls spcSetBank()
     */
    public static final SnesRawInstruction spcSetBank(String bankMemAdress) {
        return new SnesRawInstruction("spcSetBank(" + bankMemAdress + ");");
    }

    /**
     * Returns a SnesRawInstruction that calls the spcLoad() function.
     * spcLoad() is a function provided by the SNES pvsnelib that loads the sound module
     * at the given memory address.
     * 
     * @param mod the memory address of the sound module
     * @return a SnesRawInstruction that calls spcLoad()
     */
    public static final SnesRawInstruction spcLoad(String mod) {
        return new SnesRawInstruction("spcLoad(" + mod + ");");
    }

    /**
     * Returns a SnesRawInstruction that calls the spcAllocateSoundRegion() function.
     * spcAllocateSoundRegion() is a function provided by the SNES pvsnelib that allocates
     * a region of memory for sound samples. The size of the region is given in
     * 256 byte blocks and is limited to a total of 60 KBytes.
     * 
     * @param size the size of the region in 256 byte blocks
     * @return a SnesRawInstruction that calls spcAllocateSoundRegion()
     * @throws IllegalArgumentException if the size is less than 0 or greater than 0x1000
     */
    public static final SnesRawInstruction spcAllocateSoundRegion(int size) {

        // The region is limited to 60 KBytes, and the size is given in 256 byte blocks
        if (size < 0 || (size * 256) > 60000) {
            throw new IllegalArgumentException("size must be between 0 and 0x1000");
        }

        return new SnesRawInstruction("spcAllocateSoundRegion(" + size + ");");
    }

    /**
     * Returns a SnesRawInstruction that calls the spcSetSoundEntry() function.
     * spcSetSoundEntry() is a function provided by the SNES pvsnelib that sets the
     * parameters of a sound entry in the sound table.
     * 
     * @param vol the volume of the sound entry
     * @param panning the panning of the sound entry
     * @param pitch the pitch of the sound entry
     * @param length the length of the sound entry
     * @param sampleAddr the memory address of the sample data
     * @param brrSampleAddr the memory address of the BRRsample variable
     * @return a SnesRawInstruction that calls spcSetSoundEntry()
     */
    public static final SnesRawInstruction spcSetSoundEntry(
        int vol, int panning, int pitch, String length, String sampleAddr, String brrSampleAddr
    ) {
        return new SnesRawInstruction(
            "spcSetSoundEntry(" + vol + ", " + panning + ", " + pitch + ", " + length + ", " + sampleAddr + ", " + brrSampleAddr + ");"
        );
    }

    /**
     * Returns a SnesRawInstruction that calls the spcPlay() function.
     * spcPlay() is a function provided by the SNES pvsnelib that starts playing
     * all sound channels from the given start position.
     * 
     * @param startPos the start position of the sound channels to play
     * @return a SnesRawInstruction that calls spcPlay()
     */
    public static final SnesRawInstruction spcPlay(int startPos) {
        return new SnesRawInstruction("spcPlay(" + startPos + ");");
    }

    /**
     * Returns a SnesRawInstruction that calls the spcPauseMusic() function.
     * spcPauseMusic() is a function provided by the SNES pvsnelib that pauses
     * all sound channels. The sound channels can be resumed with spcResumeMusic().
     * 
     * @return a SnesRawInstruction that calls spcPauseMusic()
     */
    public static final SnesRawInstruction spcPauseMusic() {
        return new SnesRawInstruction("spcPauseMusic();");
    }

    /**
     * Returns a SnesRawInstruction that calls the spcResumeMusic() function.
     * spcResumeMusic() is a function provided by the SNES pvsnelib that resumes
     * all sound channels previously paused by spcPauseMusic().
     * 
     * @return a SnesRawInstruction that calls spcResumeMusic()
     */
    public static final SnesRawInstruction spcResumeMusic() {
        return new SnesRawInstruction("spcResumeMusic();");
    }

}
