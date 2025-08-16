package datatypes;

import java.io.IOException;
import java.nio.file.Path;

/**
 * A class representing an IT (Impulse Tracker) data file in the javasnes project.
 * It extends the Data class and provides specific functionality for handling IT data files.
 * 
 * You can use it to declare DataIT objects that represent IT data files.
 * 
 * For example:
 * 
 *      DataIT myITData = new DataIT(folder, "it/music.it");
 */
public class DataIT extends Data {

    /** 
     * isHigherThan32K is used to determine if the IT data file is larger than 32KiB.
     * This is important for handling the data correctly in the SNES bank.
     * If the size is greater than 32KiB, it may require special handling or processing
     * to ensure that it fits within the SNES memory constraints.
     * This field is set to null by default, indicating that it has not been determined yet
     * whether the IT data file is larger than 32KiB.
     * It can be set to true or false based on the size of the IT data file
     * when the data is loaded or processed. 
    */
    public Boolean isHigherThan32K = null;


    /**
     * Calculates the size of the IT data file specified by the given bank path.
     * 
     * This method updates the size field with the length of the file at the specified path.
     * It also sets the isHigherThan32K field to true if the file size exceeds 32KiB, 
     * indicating that special handling may be required for SNES memory constraints.
     * 
     * @param bnkPath The path to the bank file whose size is to be calculated.
     *                This path must not be null or empty.
     */
    public void calculateSize(String bnkPath) {
        
        // Calculate the size of the IT data file specified by the given bank path
        this.size = (long) Path.of(bnkPath).toFile().length();

        // Check if the size is greater than 32KiB (32768 bytes)
        this.isHigherThan32K = this.size > 32768;

    }

    /**
     * Converts an IT data file to SNES bank format using the smconv command from pvsneslib.
     *
     * @param PVSNESLIB_HOME The home directory of the pvsneslib, which must not be null or empty.
     * @param itPath The path to the IT data file to be converted, which must not be null or empty.
     * @param ouputPath The path where the converted SNES bank file will be saved, which must not be null or empty.
     * @throws IOException If an error occurs during the conversion process or if the smconv process fails.
     * @throws IllegalArgumentException If any of the provided paths are null or empty.
    */
    public static void toBnk(

        final String PVSNESLIB_HOME, 

        String itPath, 
        String ouputPath

    ) throws IOException, IllegalArgumentException {

        /**
         * Check if the provided paths are valid
         * If the paths are null or empty, an IllegalArgumentException is thrown
         * This is important to ensure that the conversion process has valid input paths
         * and output paths, preventing potential errors during the conversion process.
         */
        if (itPath == null || itPath.isEmpty()) {

            throw new IllegalArgumentException("IT path cannot be null or empty.");

        } else if (ouputPath == null || ouputPath.isEmpty()) {
            
            throw new IllegalArgumentException("Output path cannot be null or empty.");
        
        } else if (PVSNESLIB_HOME == null || PVSNESLIB_HOME.isEmpty()) {

            throw new IllegalArgumentException("PVSNESLIB_HOME cannot be null or empty.");

        }

        // Create a ProcessBuilder to run the smconv command from pvsneslib

        ProcessBuilder processBuilder = new ProcessBuilder();

        processBuilder.directory();

        processBuilder.command(
            "smconv"
            );

        // Start the process to convert IT data to SNES bank format
        Process process = processBuilder.start();

        /** 
         * By default, the exit code is set to -1
         * This will be updated once the process completes
         */
        int exitCode = -1;
        
        /** 
         * Wait for the process to complete and get the exit code
         * This will block until the process finishes, allowing us to check if it was successful
         * If the process is interrupted, an IOException will be thrown
         * This is important to ensure that the conversion is completed before proceeding
         * with any further operations, such as reading the output or checking for errors.
         */
         try {

            exitCode = process.waitFor();
            
        } catch (InterruptedException e) {

            throw new IOException("Error while converting IT data to SNES bank format.", e);

        }

        /**
         * Check the exit code of the process
         * If the exit code is not 0, it indicates that the process failed
         * An IOException is thrown with a message indicating the failure
        */

        if (exitCode != 0) {

            throw new IOException("smconv process failed with exit code: " + exitCode);

        }

    }

    /**
     * Constructs a DataIT with the specified folder and path.
     * @param folder A DataFolder representing the folder where the IT data file is located.
     * @param path A String representing the relative path to the IT data file.
     * @throws IllegalArgumentException 
     */
    public DataIT(DataFolder folder, String path) throws IllegalArgumentException {

        this.folder = folder;
        this.path = path;

        checkIfIT();

    }

    /**
     * Checks if the path is for an IT file.
     * 
     * This method verifies if the path ends with ".it" (case-insensitive).
     * If it does not, an IllegalArgumentException is thrown.
     * 
     * @throws IllegalArgumentException if the path is not for an IT file.
    */
    private void checkIfIT() throws IllegalArgumentException {

        if (!this.path.toLowerCase().endsWith(".it")) {

            throw new IllegalArgumentException("Path must be for a IT file."
                    + "\n\n For example: '/home/user/myproject/data/it/music.it' or 'C:\\Users\\User\\myproject\\data\\it\\music.it'");
        
        }
        
    }
    
}
