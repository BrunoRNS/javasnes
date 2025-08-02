package datatypes;

/**
 * An abstract class representing data types in the javasnes project.
 * It defines common properties for various data types such as Brr, IT, Map, Pic, or Pal.
 * You can use it to declare Data objects of different types of data files.
 * 
 * 
 * for example:
 * 
 *      Data myBrrData = new DataBrr(args);
 * 
 *      Data myITData = new DataIT(args);
 */
public class Data {

    /**
     * The Path to the data file, that can be Brr, IT, Map, Pic or Pal.
     * The Path is relative to the data directory.
     * For exemple, if the path is "data/brr/sample.brr",
     * the data directory is "data" and the file is "brr/sample.brr".
     */
    public String path;

    /**
     * The size of the data file in bytes.
     * This is used to determine how much data to read from the file.
     * And is also used to determine the size of the data in the 32KB SNES bank.
     */
    public long size;

    /**
     * The folder where the data file is located.
     * This is used to organize data files in the data directory.
     * For example, if the full path is "data/brr/sample.brr",
     * the folder could be "data",
     * and the path could be "brr/sample.brr".
     */
    public DataFolder folder;

    
}
