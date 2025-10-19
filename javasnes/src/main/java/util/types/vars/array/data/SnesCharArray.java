package util.types.vars.array.data;

import util.types.vars.abstracts.array.data.SnesArrayData;

/**
 * Class representing a SNES char array variable.
 */
public class SnesCharArray extends SnesArrayData {

    {
        this.type = "char";
    }

    /**
     * Constructor for single-dimensional char array
     * @param name the name of the array
     * @param length the length of the array
     */
    public SnesCharArray(String name, short length) {
        
        this.name = name;
        this.length = new Short[1]; this.length[0] = length;

        this.generateSourceCode();

    }

    /**
     * Constructor for single-dimensional char array with default value
     * @param name the name of the array
     * @param length the length of the array
     * @param defaultValue the default value of the array
     */
    public SnesCharArray(String name, short length, String defaultValue) {
        
        this.name = name;
        this.length = new Short[1]; this.length[0] = length;
        this.defaultValue = defaultValue;

        this.generateSourceCode();

    }

    /**
     * Constructor for multi-dimensional char array
     * @param name the name of the array
     * @param length the lengths of each dimension
     * @param dimensions the number of dimensions
     */
    public SnesCharArray(String name, Short[] length, byte dimensions) {
        
        this.name = name;
        this.length = length;
        this.dimensions = dimensions;

        this.generateSourceCode();

    }

    /**
     * Constructor for multi-dimensional char array with default value
     * @param name the name of the array
     * @param length the lengths of each dimension
     * @param dimensions the number of dimensions
     * @param defaultValue the default value of the array
     */
    public SnesCharArray(String name, Short[] length, byte dimensions, String defaultValue) {
        
        this.name = name;
        this.length = length;
        this.dimensions = dimensions;
        this.defaultValue = defaultValue;

        this.generateSourceCode();

    }
    
}
