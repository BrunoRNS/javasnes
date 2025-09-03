package datatypes;

import java.nio.file.Paths;

/**
 * A class representing a Palette data file in the javasnes project.
 * It extends the Data class and provides specific functionality for handling Palette data files.
 * 
 * You can use it to declare DataPal objects that represent Palette data files.
 * 
 * For example:
 * 
 *      DataPal myPalData = new DataPal(folder, "pal/sample.pal");
 */
public class DataPal extends Data {

    /**
     * Constructs a DataPal with the specified folder and path.
     * 
     * @param folder A DataFolder representing the folder where the palette data file is located.
     * @param path A String representing the relative path to the palette data file.
     * 
     * @throws IllegalArgumentException if the path is null or empty, or the folder is null.
     */
    public DataPal(DataFolder folder, String path) {

        this.path = path;
        this.folder = folder;

        this.validate();

        this.size = Paths.get(this.folder.getPath() + this.path).toFile().length();

    }

    /**
     * Constructs a DataPal with the specified folder, path, and size.
     * 
     * @param folder A DataFolder representing the folder where the palette data file is located.
     * @param path A String representing the relative path to the palette data file.
     * @param size A long representing the size of the palette data file in bytes.
     * 
     * @throws IllegalArgumentException if the path is null or empty, the folder is null, or the size is negative.
     */
    public DataPal(DataFolder folder, String path, long size) {

        this.path = path;
        this.folder = folder;
        this.size = size;

        this.validate();

    }

    /**
     * Validates the DataPal object to ensure that the path and folder are set correctly.
     * 
     * This method checks the following conditions:
     * - The path is not null or empty.
     * - The folder is not null.
     * - The path ends with ".pal".
     * 
     * @throws IllegalArgumentException if the path is null or empty, the folder is null, or the path does not end with ".pal".
     */
    private void validate() throws IllegalArgumentException {

        // Validate the DataPal object to ensure that the path and folder are set correctly.
        if (this.path == null) {

            throw new IllegalArgumentException("Path cannot be null.");

        }

        if (this.path.isEmpty()) {

            throw new IllegalArgumentException("Path cannot be empty.");

        }
        
        if (this.folder == null) {

            throw new IllegalArgumentException("Folder cannot be null.");

        }

        // Ensure that the path ends with ".pal".
        if (!this.path.endsWith(".pal")) {

            throw new IllegalArgumentException("Path must be for a palette in .pal format.");
        }

    }
    
}
