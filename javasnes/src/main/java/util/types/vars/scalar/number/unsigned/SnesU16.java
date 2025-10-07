package util.types.vars.scalar.number.unsigned;

import util.types.vars.abstracts.scalar.number.unsigned.SnesScalarUnsignedNumber;

/**
 * SnesU16 class to represent U16 values in SNES development context.
 * It extends SnesScalarUnsignedNumber and add more validation methods and some constructors.
 * It defines BITS_COUNT to 16 and MAX_VALUE to 65.535.
 */
public class SnesU16 extends SnesScalarUnsignedNumber {

    {
        this.BITS_COUNT = 16;

        // MIN_VALUE always 0, defined by {@link SnesScalarUnsignedNumber}
        this.MAX_VALUE = (2 * (long) Math.pow(2, this.BITS_COUNT)) - 1;

        this.type = "u16";

        this.validateMinMaxBitCount();

    }

    /**
     * Constructs a SnesU16 with the specified name, type, and global flag.
     *
     * @param name   the name of the unsigned int of 16 bits
     * @param global whether the number is global
     */
    public SnesU16(

        String name,
        boolean global
        
    ) {

        this.name = name;
        this.global = global;

        try {

            this.validate();
            
        } catch (IllegalArgumentException e) {

            throw new IllegalArgumentException(
                "Invalid argument sent to SnesU16 class: " + e.getMessage()
            );

        }

        this.generateSourceCode();

    }

    /**
     * Constructs a SnesU16 with the specified name, global flag, and default value.
     *
     * @param name         the name of the unsigned int of 16 bits
     * @param global       whether the number is global
     * @param defaultValue the default value for the number
     */
    public SnesU16(

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
                "Invalid argument sent to SnesU16 class: " + e.getMessage()
            );

        }

        this.generateSourceCode();

    }

    /**
     * Validates the fields of this SnesU16 instance.
     * <ul>
     *   <li>Checks that {@code name} is not null and not empty.</li>
     *   <li>Checks that {@code global} is not null.</li>
     *   <li>If {@code defaultValue} is present, allows it to be null, but not to be outside MIN and MAX limits.</li>
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

        this.validateDefaultValue(this.MIN_VALUE, this.MAX_VALUE);

    }
    
}
