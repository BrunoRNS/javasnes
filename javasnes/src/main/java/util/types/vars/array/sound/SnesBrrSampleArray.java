package util.types.vars.array.sound;

import util.types.vars.abstracts.array.sound.SnesArraySound;

/**
 * Class representing an array of SNES BRR samples.
 */
public class SnesBrrSampleArray extends SnesArraySound {

    {
        this.type = "brrsamples";
    }

    /**
     * Constructor for single-dimensional arrays
     * 
     * @param name the name of the array
     * @param length the length of the array
     */
    public SnesBrrSampleArray(String name, short length) {

        this.name = name;
        this.length = new Short[1]; this.length[0] = length;

        this.generateSourceCode();

    }

    /**
     * Constructor for single-dimensional arrays with default value
     * 
     * @param name the name of the array
     * @param length the length of the array
     * @param defaultValue the default value of the array
     */
    public SnesBrrSampleArray(String name, short length, String defaultValue) {

        this.name = name;
        this.length = new Short[1]; this.length[0] = length;
        this.defaultValue = defaultValue;

        this.generateSourceCode();

    }

    /**
     * Constructor for multi-dimensional arrays
     * 
     * @param name the name of the array
     * @param length the lengths of each dimension
     * @param dimensions the number of dimensions
     */
    public SnesBrrSampleArray(String name, Short[] length, byte dimensions) {
        
        this.name = name;
        this.length = length;
        this.dimensions = dimensions;

        this.generateSourceCode();

    }

    /**
     * Constructor for multi-dimensional arrays with default value
     * 
     * @param name the name of the array
     * @param length the lengths of each dimension
     * @param dimensions the number of dimensions
     * @param defaultValue the default value of the array
     */
    public SnesBrrSampleArray(String name, Short[] length, byte dimensions, String defaultValue) {
        
        this.name = name;
        this.length = length;
        this.dimensions = dimensions;
        this.defaultValue = defaultValue;

        this.generateSourceCode();

    }
    
}
