package util.types.vars.scalar.data;

import util.types.vars.abstracts.scalar.data.SnesScalarData;

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
     * It requires a name and global flag.
     * 
     * With these parameters, the source code is generated.
     * 
     * @param name String, the name of the variable
     * @param global boolean, whether the variable is global
     */
    public SnesChar(

        String name, boolean global

    ) {

        this.name = name;
        this.global = global;

        this.generateSourceCode();

    }

    /**
     * A constructor for SnesChar.
     * It requires a name, global flag, and default value.
     * 
     * With these parameters, the source code is generated.
     *
     * @param name String, the name of the variable
     * @param global boolean, whether the variable is global
     * @param defaultValue String, the default value of the variable, warning: is not checked, silent
     * errors may occur if the default value is not valid.
     * 
     * @deprecated The use of default value in SnesChar is deprecated, because it is rarely used in SNES 
     * development. You can use SnesU8 if you need an 8-bit data type, or SnesU16 if you need a 16-bit 
     * data type with a default value. In general you will only use SnesChar of Scalar types for
     * raw binary data representation, usually used with SnesLoadExtern.
     */
    @Deprecated
    public SnesChar(

        String name, boolean global,
        String defaultValue

    ) {

        this.name = name;
        this.global = global;
        this.defaultValue = defaultValue;

        this.generateSourceCode();

    }
    
}
