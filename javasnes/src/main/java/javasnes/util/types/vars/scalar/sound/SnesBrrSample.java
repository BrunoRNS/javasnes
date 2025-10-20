package javasnes.util.types.vars.scalar.sound;

import javasnes.util.types.vars.abstracts.scalar.sound.SnesScalarSound;

/**
 * Represents a SNES BRR (Bit Rate Reduction) sample sound variable.
 * This class extends the SnesScalarSound abstract class and provides
 * specific implementations for BRR samples used in SNES sound programming.
 */
public class SnesBrrSample extends SnesScalarSound {

    {
        this.type = "brrsamples";
    }

    /**
     * Constructs a SnesBrrSample with the specified name and global flag.
     *
     * @param name   the name of the BRR sample
     * @param global whether the sample is global
     */
    public SnesBrrSample(String name) {

        this.name = name;
        this.generateSourceCode();

    }

    /**
     * Constructs a SnesBrrSample with name and default value.
     * 
     * @param name the name of the BRR sample
     * @param defaultValue the default value of the BRR sample
     */
    public SnesBrrSample(String name, String defaultValue) {

        this.name = name;
        this.defaultValue = defaultValue;
        this.generateSourceCode();

    }
    
}
