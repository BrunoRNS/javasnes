package util.types.vars.scalar.data;

import util.types.vars.abstracts.scalar.data.SnesScalarVoid;

/**
 * SnesVoid class to represent void values in SNES development context.
 * It extends SnesScalarVoid and add more validation methods and some constructors.
 * 
 * @see SnesScalarVoid
 */
public class SnesVoid extends SnesScalarVoid {

    {
        this.type = "void";
    }

    /**
     * Default constructor for the SnesVoid class.
     * 
     * This constructor sets the default value to "void".
     * It also sets the sourceCode field to an empty string.
     * 
     * @see SnesScalarVoid
     */
    public SnesVoid() {

        this.defaultValue = "void";
        this.sourceCode = "";

    }

    /**
     * Default constructor for the SnesVoid class.
     * 
     * This constructor sets the default value to "void".
     * It also sets the sourceCode field to an empty string.
     * 
     * @see SnesScalarVoid
     * 
     * @deprecated This constructor is deprecated because the name parameter is not useful for void
     * type, it's only here for compatibility with other classes that could require it, will be removed
     * in the future.
     */
    @Deprecated
    public SnesVoid(String name) {

        this.name = name;
        this.defaultValue = "void";
        this.sourceCode = "";

    }
    
}
