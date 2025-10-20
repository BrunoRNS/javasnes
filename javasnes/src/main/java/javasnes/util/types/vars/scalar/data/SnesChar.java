package javasnes.util.types.vars.scalar.data;

import javasnes.util.types.vars.abstracts.scalar.data.SnesScalarData;

/**
 * SnesChar class to represent char values in SNES development context.
 * It extends SnesScalarData and add some constructors.
 */
public class SnesChar extends SnesScalarData {

    {
        this.type = "char";
    }

    /**
     * The default constructor for SnesChar.
     * With name parameter, the source code is generated.
     * 
     * @param name String, the name of the variable
     */
    public SnesChar(String name) {

        this.name = name;
        this.generateSourceCode();

    }

    /**
     * The constructor for SnesChar with name and default value.
     * 
     * @param name String, the name of the variable
     * @param defaultValue String, the default value of the variable
     */
    public SnesChar(String name, String defaultValue) {

        this.name = name;
        this.defaultValue = defaultValue;
        this.generateSourceCode();

    }
    
}
