package datatypes;

/**
 * A class representing any type of data that is not a Brr, IT, Map, Pic, or Pal.
 * You can use it to declare RawData objects that represent any type of data you need
 * to store in the javasnes project data directory.
 * 
 * For example:
 * 
 *      DataRaw myRawData = new DataRaw("mysample", "raw/sample.bin", 1024, myDataFolder, false);
 *      DataRaw myRawData = new DataRaw("mypic", "raw/pic.p8", 1024, myDataFolder, true);
 */
public class RawData extends Data {
    
    /**
     * Constructor for the RawData class.
     * 
     * @param name name of the data
     * @param path path to the data
     * @param size size of the data
     * @param folder folder containing the data
     * @param requiresEnd true if the data requires an end marker
     */
    public RawData(
        String name, String path, long size, DataFolder folder, boolean requiresEnd
    ) {
        
        this.name = name;
        this.path = path;
        this.size = size;
        this.folder = folder;
        this.requiresEnd = requiresEnd;

    }
    
}
