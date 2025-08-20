package datatypes;

import java.nio.file.Path;

/**
 * A class representing a Map data file in the javasnes project.
 * It extends the Data class and provides specific functionality for handling Map data files.
 * 
 * You can use it to declare DataMap objects that represent Map data files.
 * 
 * For example:
 * 
 *      DataMap myMapData = new DataMap(folder, "map/sample.map");
 */
public class DataMap extends Data {

    /**
     * Constructs a DataMap with the specified folder and path.
     * 
     * @param folder A DataFolder representing the folder where the map data file is located.
     * @param path A String representing the relative path to the map data file.
     * 
     * @throws IllegalArgumentException if the path is null or empty, or the folder is null.
     */
    public DataMap(DataFolder folder, String path) {

        this.path = path;
        this.folder = folder;

        this.validate();

        this.size = Path.of(this.folder.getPath() + this.path).toFile().length();

    }

    /**
     * Constructs a DataMap with the specified folder, path, and size.
     * 
     * @param folder A DataFolder representing the folder where the map data file is located.
     * @param path A String representing the relative path to the map data file.
     * @param size A long representing the size of the map data file in bytes.
     * 
     * @throws IllegalArgumentException if the path is null or empty, the folder is null, or the size is negative.
     */
    public DataMap(DataFolder folder, String path, long size) {

        this.path = path;
        this.folder = folder;
        this.size = size;

        this.validate();

    }

    /**
     * Validates the DataMap object to ensure that the path and folder are set correctly.
     * 
     * This method checks the following conditions:
     * - The path is not null or empty.
     * - The folder is not null.
     * - The path ends with ".map".
     * 
     * @throws IllegalArgumentException if the path is null or empty, the folder is null, or the path does not end with ".map".
     */
    private void validate() throws IllegalArgumentException {

        // Validate the DataMap object to ensure that the path and folder are set correctly.
        if (this.path == null || this.path.isEmpty()) {

            throw new IllegalArgumentException("Path cannot be null or empty.");

        } else if (this.folder == null) {

            throw new IllegalArgumentException("Folder cannot be null.");

        }

        // Ensure that the path ends with ".map".
        if (!this.path.endsWith(".map")) {

            throw new IllegalArgumentException("Path must be for a map in snes format.");
        }

    }

}