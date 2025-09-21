package appconifg;

/**
 * Class to set the pvsneslib home directory path.
 * This class contains a static field to hold the path to the pvsneslib home directory.
 * The path should be set before using any functionality that depends on pvsneslib.
 */
public class PvsneslibHome {
    
    /**
     * Path to the pvsneslib home directory.
     * This path is used to locate the pvsneslib resources and tools.
     * It should be set before using any functionality that depends on pvsneslib.
     * 
     * Examples
     * 
     * In Windows:
     * "C:\\path\\to\\pvsneslib"
     * 
     * Note: Use double backslashes (\\) in Windows paths to escape the backslash character.
     * 
     * In Linux or Mac OS:
     * "/path/to/pvsneslib"
     * 
     * If the path is not set, functionalities that depend on pvsneslib may not work correctly.
     * Using SNES-IDE, this path is set automatically by the App class, otherwise you have to say 
     * to the App class the hardcoded absolute path.
     */
    public static String path = null;
    
}
