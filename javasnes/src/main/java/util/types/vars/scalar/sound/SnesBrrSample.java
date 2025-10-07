package util.types.vars.scalar.sound;

import util.types.vars.abstracts.scalar.sound.SnesScalarSound;

/**
 * Represents a SNES BRR (Bit Rate Reduction) sample variable for use in SNES sound projects.
 * <p>
 * This class extends {@code SnesScalarSound} and encapsulates the properties and validation logic
 * for a BRR sample, including its name, type, global scope, and optional default value.
 * </p>
 *
 * <p>
 * The class provides two constructors:
 * <ul>
 *   <li>
 *     {@link #SnesBrrSample(String, boolean)}: Initializes a BRR sample with a name, and global flag.
 *   </li>
 *   <li>
 *     {@link #SnesBrrSample(String, boolean, String)}: Initializes a BRR sample with a name, global flag, and a default value.
 *   </li>
 * </ul>
 * </p>
 *
 * <p>
 * Upon construction, the input parameters are validated to ensure they are not null or empty.
 * If validation fails, an {@code IllegalArgumentException} is thrown with a descriptive message.
 * After successful validation, the source code for the sample is generated.
 * </p>
 *
 * <h2>Fields</h2>
 * <ul>
 *   <li>{@code name}: The identifier for the BRR sample. Must not be null or empty.</li>
 *   <li>{@code global}: Indicates whether the sample is global. Must not be null.</li>
 *   <li>{@code defaultValue}: (Optional) The default value for the sample.</li>
 * </ul>
 *
 * <h2>Usage Example</h2>
 * <pre>
 * SnesBrrSample sample = new SnesBrrSample("kick", true);
 * SnesBrrSample sampleWithDefault = new SnesBrrSample("snare", false, "default_snare");
 * </pre>
 *
 * @see SnesScalarSound
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
    public SnesBrrSample(

        String name,
        boolean global
        
    ) {

        this.name = name;
        this.global = global;

        try {

            this.validate();
            
        } catch (IllegalArgumentException e) {

            throw new IllegalArgumentException(
                "Invalid argument sent to SnesBrrSample class: " + e.getMessage()
            );

        }

        this.generateSourceCode();

    }

    /**
     * Constructs a SnesBrrSample with the specified name, global flag, and default value.
     *
     * @param name         the name of the BRR sample
     * @param global       whether the sample is global
     * @param defaultValue the default value for the sample
     * 
     * @deprecated Use {@link #SnesBrrSample(String, boolean, String)}, deprecated because in
     * pvsneslib examples, the default value is never used, so you shouldn't use it either.
     */
    @Deprecated
    public SnesBrrSample(

        String name,
        boolean global,
        String defaultValue
        
    ) {

        this.name = name;
        this.global = global;
        this.defaultValue = defaultValue;

        try {

            this.validate();
            
        } catch (IllegalArgumentException e) {

            throw new IllegalArgumentException(
                "Invalid argument sent to SnesBrrSample class: " + e.getMessage()
            );

        }

        this.generateSourceCode();

    }

    /**
     * Validates the fields of this SnesBrrSample instance.
     * <ul>
     *   <li>Checks that {@code name} is not null and not empty.</li>
     *   <li>Checks that {@code global} is not null.</li>
     *   <li>If {@code defaultValue} is present, allows it to be null or empty (no restriction).</li>
     * </ul>
     * @throws IllegalArgumentException with a descriptive message if any validation fails.
     */
    private void validate() throws IllegalArgumentException {

        if (this.name == null) {

            throw new IllegalArgumentException("Name cannot be null");

        }

        if (this.name.isEmpty()) {

            throw new IllegalArgumentException("Name cannot be empty");
                
        }

        if (this.global == null) {

            throw new IllegalArgumentException("Global cannot be null");

        }

    }
    
}
