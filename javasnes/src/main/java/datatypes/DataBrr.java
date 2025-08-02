package datatypes;

/**
 * A class representing a Brr data file in the javasnes project.
 * It extends the Data class and provides specific functionality for handling Brr data files.
 * 
 * You can use it to declare DataBrr objects that represent Brr data files.
 * 
 * For example:
 * 
 *      DataBrr myBrrData = new DataBrr(folder, "brr/sample.brr");
 */
public class DataBrr extends Data {

    /**
     * Constructs a DataBrr with the specified folder and path.
     * 
     * @param folder A DataFolder representing the folder where the brr data file is located.
     * @param path A String representing the relative path to the brr data file.
     * 
     * @throws IllegalArgumentException if the path is null or empty, the folder is null, or the size is negative.
     * 
     */
    public DataBrr(DataFolder folder, String path) throws IllegalArgumentException {

        // Initialize the DataBrr object with the specified folder and path.
        // The size is set to 0 by default, indicating that the size is not known.
        this.path = path;
        this.folder = folder;

        // Make validation of the DataBrr object.
        // This will ensure that the path, folder, and size are set correctly.
        this.validate();
        this.checkIfBrr();

    }

    /**
     * Constructs a DataBrr with the specified folder, path, and size.
     * 
     * @param folder A DataFolder representing the folder where the brr data file is located.
     * @param path A String representing the relative path to the brr data file.
     * @param size A long representing the size of the brr data file in bytes.
     * 
     * @throws IllegalArgumentException if the path is null or empty, the folder is null, or the size is negative.
     * 
     */
    public DataBrr(DataFolder folder, String path, long size) throws IllegalArgumentException {

        // Initialize the DataBrr object with the specified folder, path, and size.
        // The size is set to the specified value, which must be a non-negative long.
        this.path = path;
        this.size = (long) size;
        this.folder = folder;

        // Make validation of the DataBrr object.
        // This will ensure that the path, folder, and size are set correctly.
        this.validate();
        this.checkIfBrr();

    }

    /**
     * Validates the DataBrr object to ensure that the path, folder, and size are set correctly.
     * 
     * This method checks the following conditions:
     * - The path is not null or empty.
     * - The folder is not null and is a valid DataFolder.
     * - The size is not negative.
     * 
     * @throws IllegalArgumentException if the path is null or empty, the folder is null, or the size is negative.
     */
    private void validate() throws IllegalArgumentException {

        // Validate the DataBrr object to ensure that the path, folder, and size are set correctly.
        // If any of these conditions are not met, an IllegalArgumentException is thrown.
        if (this.path == null || this.path.isEmpty()) {

            throw new IllegalArgumentException("Path cannot be null or empty.");

        }

        // Ensure that the folder is not null and is a valid DataFolder.
        // The folder must be an instance of DataFolder and must not be null.
        if (this.folder == null) {

            throw new IllegalArgumentException("Folder cannot be null.");

        }

        // Ensure that the size is not negative.
        // The size must be a non-negative long value.
        if (this.size < 0) {

            throw new IllegalArgumentException("Size cannot be negative.");

        }

    }

    /**
     * Checks if the path is for a Brr file.
     * 
     * This method checks if the path ends with ".brr" (case-insensitive).
     * If it does not, an IllegalArgumentException is thrown.
     * 
     * @throws IllegalArgumentException if the path is not for a Brr file.
     */
    private void checkIfBrr() throws IllegalArgumentException {

        // Check if the path ends with ".brr" (case-insensitive).
        // If it does not, throw an IllegalArgumentException.
        // This ensures that the path is for a Brr file.
        if (!this.path.toLowerCase().endsWith(".brr")) {

            throw new IllegalArgumentException("Path must be for a Brr file."
                    + "\n\n For example: '/home/user/myproject/data/brr/sample.brr' or 'C:\\Users\\User\\myproject\\data\\brr\\sample.brr'");
        
        }
        
    }
    
}
