package javasnes.util.types.vars.array.number.unsigned;

import javasnes.util.types.vars.abstracts.array.number.unsigned.SnesArrayUnsignedNumber;

/**
 * Class representing an array of SNES unsigned 16-bit numbers.
 */
public class SnesU16Array extends SnesArrayUnsignedNumber {

    {
        this.type = "u16";
    }

    /**
     * Constructor for single-dimensional arrays
     * @param name the name of the array
     * @param length the length of the array
     */
    public SnesU16Array(String name, short length) {

        this.name = name;
        this.length = new Short[1]; this.length[0] = length;

        this.generateSourceCode();

    }

    /**
     * Constructor for single-dimensional arrays with default value
     * @param name the name of the array
     * @param length the length of the array
     * @param defaultValue the default value of the array
     */
    public SnesU16Array(String name, short length, String defaultValue) {

        this.name = name;
        this.length = new Short[1]; this.length[0] = length;
        this.defaultValue = defaultValue;

        this.generateSourceCode();

    }

    /**
     * Constructor for multi-dimensional arrays
     * @param name the name of the array
     * @param length the lengths of each dimension
     * @param dimensions the number of dimensions
     */
    public SnesU16Array(String name, Short[] length, byte dimensions) {
        
        this.name = name;
        this.length = length;
        this.dimensions = dimensions;

        this.generateSourceCode();

    }
    
    /**
     * Constructor for multi-dimensional arrays with default value
     * @param name the name of the array
     * @param length the lengths of each dimension
     * @param dimensions the number of dimensions
     * @param defaultValue the default value of the array
     */
    public SnesU16Array(String name, Short[] length, byte dimensions, String defaultValue) {
        
        this.name = name;
        this.length = length;
        this.dimensions = dimensions;
        this.defaultValue = defaultValue;

        this.generateSourceCode();

    }

}
