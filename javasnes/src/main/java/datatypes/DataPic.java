package datatypes;

import java.nio.file.Path;

/**
 * A class representing a Picture data file in the javasnes project.
 * It extends the Data class and provides specific functionality for handling Picture data files.
 * 
 * You can use it to declare DataPic objects that represent Picture data files.
 * 
 * For example:
 * 
 *      DataPic myPicData = new DataPic(folder, "pic/sample.pic");
 */
public class DataPic extends Data {

    /**
     * Constructs a DataPic with the specified folder and path.
     * 
     * @param folder A DataFolder representing the folder where the picture data file is located.
     * @param path A String representing the relative path to the picture data file.
     * 
     * @throws IllegalArgumentException if the path is null or empty, or the folder is null.
     */
    public DataPic(DataFolder folder, String path) {

        this.path = path;
        this.folder = folder;

        this.validate();

        this.size = Path.of(this.folder.getPath() + this.path).toFile().length();

    }

    /**
     * Constructs a DataPic with the specified folder, path, and size.
     * 
     * @param folder A DataFolder representing the folder where the picture data file is located.
     * @param path A String representing the relative path to the picture data file.
     * @param size A long representing the size of the picture data file in bytes.
     * 
     * @throws IllegalArgumentException if the path is null or empty, the folder is null, or the size is negative.
     */
    public DataPic(DataFolder folder, String path, long size) {

        this.path = path;
        this.folder = folder;
        this.size = size;

        this.validate();

    }

    /**
     * Validates the DataPic object to ensure that the path and folder are set correctly.
     * 
     * This method checks the following conditions:
     * - The path is not null or empty.
     * - The folder is not null.
     * - The path ends with ".pic".
     * 
     * @throws IllegalArgumentException if the path is null or empty, the folder is null, or the path does not end with ".pic".
     */
    private void validate() throws IllegalArgumentException {

        // Validate the DataPic object to ensure that the path and folder are set correctly.
        if (this.path == null || this.path.isEmpty()) {

            throw new IllegalArgumentException("Path cannot be null or empty.");

        } else if (this.folder == null) {

            throw new IllegalArgumentException("Folder cannot be null.");

        }

        // Ensure that the path ends with ".pic".
        if (!this.path.endsWith(".pic")) {

            throw new IllegalArgumentException("Path must be for a picture in snes pic format.");
        }

    }
    
}
