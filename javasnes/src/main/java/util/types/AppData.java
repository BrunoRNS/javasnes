package util.types;

import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import datatypes.Data;

public class AppData {

    public Map<Byte, Data[]> banks = new TreeMap<>();

    public Set<Byte> possibleBanks = new HashSet<>();

    /**
     * Constructs an AppData object with a default bank configuration.
     *
     * This constructor initializes the banks map with empty Data arrays for
     * banks from 2 to 32 to new Data arrays.
     */
    {

        /**
         * Here all the banks since 2 until 32 are initialized to null. This is
         * done to ensure that all banks are available for use. If you need to
         * use a specific bank, you can assign a Data object to it and it will
         * be stored in the corresponding bank.
         */
        for (byte i = 2; i <= 32; i++) {

            /**
             * Hopefully putting 32768 objects in your RAM, N times.
             */
            this.banks.put(i, new Data[32768]);

            this.possibleBanks.add(i);

        }

    }

    /**
     * Registers a Data object in a specified bank at a specified position.
     *
     * @param data The Data object to be registered.
     * @param bank The bank in which the Data object will be registered.
     * @param position The position in the bank where the Data object will be
     * registered.
     *
     * @throws IllegalArgumentException If the bank is not available, the data
     * size exceeds 32KiB, the bank is full, or the position is out of bounds.
     */
    public void registerData(Data data, byte bank, int position) throws IllegalArgumentException {

        /**
         * Check if the data is null, the bank is not available, or the data
         * size exceeds 32KiB. If any of these conditions are met, an
         * IllegalArgumentException is thrown. This ensures that the data is
         * valid and can be registered in the specified bank.
         */
        if (data == null) {

            throw new IllegalArgumentException("Data cannot be null.");

        } else if (!this.possibleBanks.contains(bank)) {

            throw new IllegalArgumentException("Bank " + bank + " is not available.");

        }

        /**
         * If the data size is zero, it is calculated based on the file size of
         * the data. This ensures that the data size is set correctly before
         * registering it in the bank. If the size is not set, it will be
         * determined by checking the file size of the data.
         */
        if (data.size == 0) {

            data.size = Paths.get(data.folder.getPath() + data.path).toFile().length();

        }

        /**
         * Check if the data size exceeds 32KiB (32768 bytes). If it does, an
         * IllegalArgumentException is thrown to prevent registering oversized
         * data. This ensures that the data can fit within the bank's capacity.
         */
        if (data.size > 32768) {

            throw new IllegalArgumentException("Data size exceeds 32KiB, cannot register in bank " + bank + ".");

        }

        /**
         * Check if the bank is full by calculating the total size of all Data
         * objects in the bank. If the total size plus the size of the new data
         * exceeds 32KiB, an IllegalArgumentException is thrown. This ensures
         * that the bank does not exceed its maximum capacity.
         */
        short bank_size = 0;

        for (Data d : this.banks.get(bank)) {

            /**
             * Check if the Data object is null before accessing its size. If it
             * is null, continue to the next Data object in the bank. This
             * prevents a NullPointerException from being thrown when trying to
             * access the size of a null Data object.
             */
            if (d == null) {
                continue;
            }

            /**
             * If the Data object is not null, add its size to the total bank
             * size. This is done to calculate the total size of all Data
             * objects in the bank. If the size of the Data object is not zero,
             * it is added to the bank_size.
             *
             * This is important to ensure that the bank does not exceed its
             * maximum capacity of 32KiB (32768 bytes).
             */
            if (d.size != 0) {
                bank_size += d.size;
            }

        }

        /**
         * Check if the bank is full by comparing the total size of the data in
         * the bank with the maximum allowed size of 32KiB (32768 bytes). If the
         * bank is full or the position is out of bounds, an
         * IllegalArgumentException is thrown. This ensures that the data can be
         * registered in the specified position without exceeding the bank's
         * capacity. If the position is out of bounds or already occupied, an
         * IllegalArgumentException is thrown to prevent overwriting existing
         * data or accessing invalid positions.
         */
        if (bank_size + data.size > 32768) {

            /**
             * If the bank is full, remove it from the possible banks set to
             * prevent further registrations. This ensures that no more data can
             * be registered in a full bank. An IllegalArgumentException is
             * thrown to indicate that the bank is full and cannot accept more
             * data.
             */
            this.possibleBanks.remove(bank);

            throw new IllegalArgumentException("Bank " + bank + " is full, cannot register more data.");

        } else if (position < 0 || position >= this.banks.get(bank).length || this.banks.get(bank)[position] != null) {

            throw new IllegalArgumentException("Position " + position + " is out of bounds for bank " + bank + ".");

        }

        /**
         * If all checks pass, the Data object is registered in the specified
         * bank at the specified position. This allows the Data object to be
         * stored and accessed later using the bank and position.
         */
        this.banks.get(bank)[position] = data;

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

            sb.append(".section \".rodata").append(Math.abs(bank)).append("\" superfree\n");

            for (int i = 0; i < this.banks.get(bank).length; i++) {

                if (this.banks.get(bank)[i] == null) {
                    continue;
                }

                if (this.banks.get(bank)[i].name == null) {
                    throw new IllegalArgumentException("Data name cannot be null.");
                }

                sb.append(this.banks.get(bank)[i].name).append(":\n");

                sb.append(".incbin \"");
                sb.append(this.banks.get(bank)[i].folder.path);
                sb.append(this.banks.get(bank)[i].path);
                sb.append("\"");
                sb.append("\n");

                if (this.banks.get(bank)[i].requiresEnd) {

                    sb.append(this.banks.get(bank)[i].name);
                    sb.append("_end").append(":\n");

                }

                sb.append("\n");

            }

            sb.append("\n.ends\n");

        }

        return sb.toString();

    }

}
