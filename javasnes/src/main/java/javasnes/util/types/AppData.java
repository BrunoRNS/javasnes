package javasnes.util.types;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;

import javasnes.data.Data;

public class AppData {

    public Map<Byte, Stack<Data>> banks = new TreeMap<>();

    public Set<Byte> possibleBanks = new HashSet<>();

    /**
     * Constructs an AppData object with a default bank configuration.
     *
     * This constructor initializes the banks map with empty Data arrays for
     * banks from 2 to 32 to new Data arrays.
     */
    {

        /**
         * Here all the banks since 2 until 32 are initialized to new Stack<>().
         * This is done to ensure that all banks are available for use. If you need to
         * use a specific bank, you can assign a Data object to it and it will
         * be stored in the corresponding bank.
         */
        for (byte i = 2; i <= 32; i++) {

            this.banks.put(i, new Stack<>());

            this.possibleBanks.add(i);

        }

    }

    /**
     * Constructs an AppData object with a default bank configuration.
     */
    public AppData() {

        this.initialize();

    }

    /**
     * Constructs an AppData object with the given data and banks.
     * 
     * @param data An array of Data objects
     * @param banks An array of bank numbers
     */
    public AppData(Data[] data, byte[] banks) {

        if (data != null && banks != null) {

            if (data.length != banks.length) {

                throw new IllegalArgumentException(
                    "Data and banks arrays must have the same length."
                );

            }

            for (int i = 0; i < data.length; i++) {

                this.registerData(data[i], banks[i]);

            }
            
        }

        this.initialize();

    }

    /**
     * Initializes the AppData object by generating the default source code.
     * This method is called by the constructors to ensure that the AppData object
     * is initialized correctly.
     */
    private void initialize() {

        this.generateSourceCode();
        
    }

    /**
     * Registers a Data object with the given bank and position.
     * 
     * This method checks if the Data object is not null and if the bank is
     * available for use. If either of these conditions is not met, an
     * IllegalArgumentException is thrown.
     * 
     * @param data The Data object to register
     * @param bank The bank to register the Data object with
     * @throws IllegalArgumentException If the Data object is null or if the bank is not available
     */
    public final void registerData(

        Data data, byte bank

    ) throws IllegalArgumentException {

        if (data == null) {

            throw new IllegalArgumentException("Data cannot be null.");

        } else if (!this.possibleBanks.contains(bank)) {

            throw new IllegalArgumentException("Bank " + bank + " is not available.");

        }

        this.banks.get(bank).add(data);

    }

    /**
     * Generates the default configuration for the AppData object.
     * 
     * The default configuration includes the following:
     * 
     * <pre>
     * javasnes_patterns:
     * .incbin "javasnes_logo.pic"
     * javasnes_patterns_end:
     * .ends
     * 
     * javasnes_map:
     * .incbin "javasnes_logo.map"
     * javasnes_map_end:
     * 
     * javasnes_palette:
     * .incbin "javasnes_logo.pal"
     * javasnes_palette_end:
     * </pre>
     * 
     * @return The default configuration as a String.
     */
    public String generateDefaultConfiguration() {

        /**
         * Default configuration:
         * 
         * javasnes_patterns:
         * .incbin "javasnes_logo.pic"
         * javasnes_patterns_end:
         * 
         * javasnes_map:
         * .incbin "javasnes_logo.map"
         * javasnes_map_end:
         *
         * javasnes_palette:
         * .incbin "javasnes_logo.pal"
         * javasnes_palette_end:
         * 
         * -----------------------------------------------------
         * C source code:
         * 
         * extern char javasnes_patterns, javasnes_patterns_end;
         * extern char javasnes_palette, javasnes_palette_end;
         * extern char javasnes_map, javasnes_map_end;
         * ------------------------------------------------------
         * 
         */

        StringBuilder sb = new StringBuilder();

        sb.append("javasnes_patterns:\n");
        sb.append(".incbin \"javasnes_logo.pic\"\n");
        sb.append("\n");
        sb.append("javasnes_patterns_end:\n");
        sb.append("\n\n");
        sb.append("javasnes_map:\n");
        sb.append(".incbin \"javasnes_logo.map\"\n");
        sb.append("\n");
        sb.append("javasnes_map_end:\n");
        sb.append("\n\n");
        sb.append("javasnes_palette:\n");
        sb.append(".incbin \"javasnes_logo.pal\"\n");
        sb.append("\n");
        sb.append("javasnes_palette_end:\n");

        return sb.toString();

    }

    /**
     * Generates the C source code representation of this AppData object.
     * 
     * This method constructs the C source code for the AppData object,
     * which includes the paths to the files and the names of the Data
     * objects. The generated source code is returned as a string.
     * 
     * The structure of the generated source code is as follows:
     * 
     * .include "hdr.asm"
     * 
     * .section ".rodata1" superfree
     * 
     * ...Defaults objects
     * 
     * .ends
     * 
     * .section ".rodata2" superfree
     * 
     * map:
     * .incbin "pvsneslib.map"
     * map_end:
     * 
     * palette:
     * .incbin "pvsneslib.pal"
     * palette_end:
     * 
     * .ends
     * 
     * If a Data object has a null name, an IllegalArgumentException is thrown.
     * 
     * @return the ASM source code representation of this AppData object.
     * @throws IllegalArgumentException if a Data object has a null name.
     */
    public String generateSourceCode() throws IllegalArgumentException {
        /**
         * For example: 
         .include "hdr.asm"

         .section ".rodata1" superfree

         ...Defaults objects

         .ends

         .section ".rodata2" superfree

         patterns:
         .incbin "pvsneslib.pic"
         patterns_end:

         .ends

         .section ".rodata3" superfree
         
         map:
         .incbin "pvsneslib.map"
         map_end:

         palette:
         .incbin "pvsneslib.pal" 
         palette_end:

         .ends
         */

        StringBuilder sb = new StringBuilder();

        sb.append(".include \"hdr.asm\"\n");

        sb.append("\n");

        sb.append(".section \".rodata").append(1).append("\" superfree\n");
        sb.append(this.generateDefaultConfiguration());
        sb.append("\n.ends\n");
        sb.append("\n");

        for (Byte bank : this.banks.keySet()) {

            if (this.banks.get(bank) == null) {
                continue;
            }

            if (this.banks.get(bank).isEmpty()) {
                continue;
            }

            sb.append(".section \".rodata").append(bank).append("\" superfree\n");

            for (Data data : this.banks.get(bank)) {

                if (data == null) {
                    continue;
                }

                if (data.name == null) {
                    throw new IllegalArgumentException("Data name cannot be null.");
                }

                sb.append(data.name).append(":\n");

                sb.append(".incbin \"");
                sb.append(data.path);
                sb.append("\"");
                sb.append("\n");

                if (data.requiresEnd) {

                    sb.append(data.name);
                    sb.append("_end").append(":\n");

                }

                sb.append("\n");

            }

            sb.append("\n.ends\n\n");

        }

        return sb.toString();

    }

}
