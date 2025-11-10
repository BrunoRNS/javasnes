package javasnes.sneslib;

import javasnes.instruction.SnesRawInstruction;

public final class SnesUtilities {
    
    /**
     * Returns a SnesRawInstruction that copies the contents of the source string into the 
     * destination. This function is used to copy the contents of a string from the sram 
     * into another string in the sram.
     * 
     * @param src the source string to copy from
     * @param dest the destination string to copy into
     * @return a SnesRawInstruction that copies the contents of the source string into the
     * destination
     */
    public static final SnesRawInstruction consoleCopySram(String src, String dest) {

        return new SnesRawInstruction("consoleCopySram(" + src + ", " + dest + ")");
        
    }

    /**
     * Returns a SnesRawInstruction that loads the contents of the source string into the
     * destination.
     * This function is used to load the contents of a string from the ROM into the sram.
     * 
     * @param dest the destination string to load the contents into
     * @param src the source string to load from
     * @return a SnesRawInstruction that loads the contents of the source string into the 
     * destination
     */    
    public static final SnesRawInstruction consoleLoadSram(String dest, String src) {

        return new SnesRawInstruction("consoleLoadSram(" + dest + ", " + src + ")");

    }

    /**
     * Returns a SnesRawInstruction that calls the sprintf function with the given destination,
     * format string, and arguments. The format string should contain placeholders for the
     * arguments in the form of %c, %s, %d, etc. The arguments should be strings or numeric
     * values that can be interpreted as the corresponding type required by the format string.
     * 
     * Example:
     * <pre>
     * {@code
     * String dest = "myString";
     * String format = "This is a string: %s";
     * String arg = "Hello, world!";
     * SnesRawInstruction instruction = SnesUtilities.sprintf(dest, format, arg);
     * }
     * </pre>
     * 
     * @param dest the destination string for sprintf
     * @param format the format string for sprintf
     * @param args the arguments to pass to sprintf
     * @return a SnesRawInstruction that calls sprintf with the given arguments
     */
    public static final SnesRawInstruction sprintf(String dest, String format, String... args) {

        return new SnesRawInstruction(
            "sprintf(" + dest + ", \"" + format + "\", " + String.join(", ", args) + ");"
        );

    }

}
