package javasnes.data;

/**
 * Data class representing a data file to be loaded in C source code.
 */
public class Data {

    /**
     * Name of Data file to be loaded in C source code.
     */
    public String name;

    /**
     * The relative path to the data file.
     * Usually is in the root folder but can be in a subfolder depending
     * in the Makefile(makefile.Make) configuration.
     */
    public String path;

    /**
     * If it requires a end tag to be added to the end of the data call in data.asm.
     * For example:
     *     mysound:
     *        reads data.brr
     *     mysoundend: -> end tag
     */     
    public boolean requiresEnd;

    /**
     * Constructor for Data object.
     * @param name the name of the data to be loaded in C.
     * @param path the relative path to the data file.
     * @param requiresEnd if it requires an end tag.
     */
    public Data(String name, String path, boolean requiresEnd) {
        this.name = name;
        this.path = path;
        this.requiresEnd = requiresEnd;
    }

}
