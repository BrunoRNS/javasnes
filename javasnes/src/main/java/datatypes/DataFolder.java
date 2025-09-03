package datatypes;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * A class representing a folder in the javasnes project data directory.
 * It is used to organize data files such as Brr, IT, Map, Pic, or Pal.
 * 
 * You can use it to declare DataFolder objects that represent the folder
 * where data files are stored.
 * 
 * For example:
 * 
 *      DataFolder myDataFolder = new DataFolder("/home/user/myproject/data");
 */
public class DataFolder {

    /**
     * The path to the data folder.
     * This is used to organize data files in the data directory.
     * 
     * for example:
     *      
     * . . .   DataFolder myDataFolder = new DataFolder("/home/user/myproject/data");
     *      
     * . . .   Or in Windows Systems:
     *      
     * . . .   DataFolder myDataFolder = new DataFolder("C:\\Users\\User\\myproject\\data");
     */
    public String path;

    /**
     * Constructs a DataFolder with the specified path.
     * 
     * @param path A String representing the absolute path to the data folder.
     * @throws IllegalArgumentException if the path is null, empty, not absolute, or not a directory.
     */
    public DataFolder(String path) throws IllegalArgumentException {

        // Validate the path to ensure it is not null, not empty, is absolute, and is a directory.
        // If any of these conditions are not met, an IllegalArgumentException is thrown.
        if (path == null) {

            throw new IllegalArgumentException("Path cannot be null.");

        }

        if (path.isEmpty()) {

            throw new IllegalArgumentException("Path cannot be empty.");

        }
        
        if ( !(Paths.get(path).isAbsolute()) ) {

            throw new IllegalArgumentException("Path must be absolute."
                    + "\n\n For example: /home/user/myproject/data or C:\\Users\\User\\myproject\\data");

        } 
        
        if ( !(Paths.get(path).toFile().isDirectory()) ) {

            throw new IllegalArgumentException("Path must be a directory."
                    + "\n\n For example: /home/user/myproject/data or C:\\Users\\User\\myproject\\data");

        }

        // If the path is valid, assign it to the path field.
        this.path = path;
    
    }

    /**
     * Constructs a DataFolder with the specified Path.
     * 
     * @param path A Path object representing the absolute path to the data folder.
     * @throws IllegalArgumentException if the path is null, empty, not absolute, or not a directory.
     */
    public DataFolder(Path path) throws IllegalArgumentException {

        // Validate the path to ensure it is not null, not empty, is absolute, and is a directory.
        // If any of these conditions are not met, an IllegalArgumentException is thrown.
        if (path == null) {

            throw new IllegalArgumentException("Path cannot be null or empty.");

        } 
        
        if (path.toString().isEmpty()) {

            throw new IllegalArgumentException("Path cannot be null or empty.");
            
        } 
        
        if ( !(path.isAbsolute()) ) {

            throw new IllegalArgumentException("Path must be absolute."
                    + "\n\n For example: /home/user/myproject/data or C:\\Users\\User\\myproject\\data");

        }

        if ( !(path.toFile().isDirectory()) ) {

            throw new IllegalArgumentException("Path must be a directory."
                    + "\n\n For example: /home/user/myproject/data or C:\\Users\\User\\myproject\\data");

        }

        // If the path is valid, assign it to the path field.
        // Convert the Path to a String for consistency with the String constructor.
        this.path = path.toString();

    }

    /**
     * Returns the path of the data folder.
     * 
     * @return A String representing the absolute path to the data folder.
     */
    public String getPath() {

        return this.path;

    }

}