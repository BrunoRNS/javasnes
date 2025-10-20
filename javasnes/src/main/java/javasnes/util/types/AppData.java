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
     * banks from 1 to 32 to new Data arrays.
     */
    {

        /**
         * Here all the banks since 1 until 32 are initialized to new Stack<>().
         * This is done to ensure that all banks are available for use. If you need to
         * use a specific bank, you can assign a Data object to it and it will
         * be stored in the corresponding bank.
         */
        for (byte i = 1; i <= 32; i++) {

            this.banks.put(i, new Stack<>());

            this.possibleBanks.add(i);

        }

    }


    /**
     * Registers a Data object in the specified bank at the given position.
     * 
     * This method adds the provided Data object to the specified bank at
     * the given position. If the bank is not available or if the Data
     * object is null, an IllegalArgumentException is thrown.
     * 
     * @param data     the Data object to be registered.
     * @param bank     the bank number where the Data object will be stored.
     * @param position the position within the bank to store the Data object.
     * @throws IllegalArgumentException if the Data object is null or if
     *                                  the bank is not available.
     */
    public void registerData(Data data, byte bank, int position) throws IllegalArgumentException {

        
        if (data == null) {

            throw new IllegalArgumentException("Data cannot be null.");

        } else if (!this.possibleBanks.contains(bank)) {

            throw new IllegalArgumentException("Bank " + bank + " is not available.");

        }

        this.banks.get(bank).add(data);

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
     * patterns:
     * .incbin "pvsneslib.pic"
     * patterns_end:
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
     * The map and palette sections are only included if the Data object
     * is an instance of DataIT. The section .rodata1 is used for the
     * patterns and .rodata2 is used for the map and palette. The .ends
     * directive is used to mark the end of a section.
     * 
     * If a Data object has a null name, an IllegalArgumentException is thrown.
     * 
     * @return the ASM source code representation of this AppData object.
     * @throws IllegalArgumentException if a Data object has a null name.
     */
    @SuppressWarnings("")
    public String generateSourceCode() throws IllegalArgumentException {
        /**
         * For example: 
         .include "hdr.asm"

         .section ".rodata1" superfree

         patterns:
         .incbin "pvsneslib.pic"
         patterns_end:

         .ends

         .section ".rodata2" superfree
         
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

        for (Byte bank : this.banks.keySet()) {

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

            sb.append("\n.ends\n");

        }

        return sb.toString();

    }

}
