package javasnes.util.types.vars.pointer.sound;

import javasnes.util.types.vars.abstracts.pointer.sound.SnesPointerSound;

/**
 * Represents a SNES BRR sample pointer variable.
 * 
 * @deprecated This class is deprecated and may be removed in future versions.
 */
@Deprecated
public class SnesBrrSamplePointer extends SnesPointerSound {

    {
        this.type = "brrsamples";
    }

    /**
     * Constructs a SnesBrrSamplePointer with the specified name.
     * @param name the name of the BRR sample pointer
     */
    public SnesBrrSamplePointer(String name) {

        this.name = name;
        this.generateSourceCode();

    }

    /**
     * Constructs a SnesBrrSamplePointer with name and default value.
     * 
     * @param name the name of the BRR sample pointer
     * @param defaultValue the default value of the BRR sample pointer
     */
    public SnesBrrSamplePointer(String name, String defaultValue) {

        this.name = name;
        this.defaultValue = defaultValue;
        this.generateSourceCode();

    }
    
}
