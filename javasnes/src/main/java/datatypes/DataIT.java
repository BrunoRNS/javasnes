package datatypes;

import java.nio.file.Paths;

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
     * 
     * @deprecated Is deprecated because this verification is a bit useless in context that you need
     * to compile in soundbank it before putting in the ROM, in the process size may vary, and the
     * process is manually done, therefore this verification is not needed
    */
    @Deprecated
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
     * 
     * @deprecated This method is deprecated because it is not used anymore. The purpose is to set 
     * the value of isHigherThan32K, which is a deprecated field.
     */
    @Deprecated
    public void calculateSize(String bnkPath) {
        
        /*
         * Calculate the size of the IT data file specified by the given bank path
        */
        this.size = (long) Paths.get(bnkPath).toFile().length();

        /* 
         * Check if the size is greater than 32KiB (32768 bytes)
        */
        this.isHigherThan32K = this.size > 32768;

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
