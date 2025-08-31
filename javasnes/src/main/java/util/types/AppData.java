package util.types;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import appconifg.PvsneslibHome;
import datatypes.Data;
import datatypes.DataIT;

public class AppData {

    public Map<Byte, Data[]> banks = new TreeMap<>();

    public Set<Byte> possibleBanks = new HashSet<>();

    /**
     * Constructs an AppData object with a default bank configuration.
     * 
     * This constructor initializes the banks map with a special entry for the
     * -1 bank, which is used for rodata free bank(0-4 banks), and initializes all other banks
     * from 5 to 32 to null.
     */
    {
        /* 
         * -1 refers to rodata free bank, which is where the code is stored by default, only use it
         * if the Data object is very lightweight and doesn't need a dedicated bank.
         * This Data Bank kinda mix banks from 0-4, I'm not sure what exactily happens, but you can search
         * for it in some snes documentations that explain the SNES memory map especially the LOROM.
        */
        this.banks.put((byte) -1, new Data[32768]);

        this.possibleBanks.add((byte) -1);

        /**
         * Here all the banks since 5 until 32 are initialized to null.
         * This is done to ensure that all banks are available for use.
         * If you need to use a specific bank, you can assign a Data object to it
         * and it will be stored in the corresponding bank.
         */
        for (byte i = 5; i <= 32; i++) {

            this.banks.put(i, new Data[32768]);

            this.possibleBanks.add(i);

        }

    }

    /**
     * Registers a Data object in a specified bank at a specified position.
     * 
     * @param data The Data object to be registered.
     * @param bank The bank in which the Data object will be registered.
     * @param position The position in the bank where the Data object will be registered.
     * 
     * @throws IllegalArgumentException If the bank is not available, the data size exceeds 32KiB, the bank is full, or the position is out of bounds.
     */
    public void registerData(Data data, byte bank, int position) throws IllegalArgumentException {
        
        /**
         * Check if the data is null, the bank is not available, or the data size exceeds 32KiB.
         * If any of these conditions are met, an IllegalArgumentException is thrown.
         * This ensures that the data is valid and can be registered in the specified bank.
         */
        if (data == null) {
            
            throw new IllegalArgumentException("Data cannot be null.");
            
        } else if (!this.possibleBanks.contains(bank)) {
            
            throw new IllegalArgumentException("Bank " + bank + " is not available.");
            
        }

        /**
         * If the data is an instance of DataIT, it is converted to SNES bank format.
         * This is done to ensure that the data can be registered in the SNES bank correctly.
         * The conversion uses the smconv command from pvsneslib, which must be set in the PVSNESLIB_HOME variable.
         */
        if (data instanceof DataIT) {

            /**
             * If the data is an instance of DataIT, it is converted to SNES bank format.
             * This is done to ensure that the data can be registered in the SNES bank correctly.
             *
             */
            try {

                DataIT.toBnk(

                    PvsneslibHome.path,

                    data.folder.getPath() + data.path,
                    Paths.get(data.folder.getPath() + data.path).getParent().toString()

                );
                
            } catch (IOException | IllegalArgumentException e) {

                throw new IllegalArgumentException("Error converting IT data to SNES bank format: " + e.getMessage());
            
            }

        }

        /**
         * If the data size is zero, it is calculated based on the file size of the data.
         * This ensures that the data size is set correctly before registering it in the bank.
         * If the size is not set, it will be determined by checking the file size of the data.
         */
        if (data.size == 0) {

            data.size = Paths.get(data.folder.getPath() + data.path).toFile().length();

        }
        
        /**
         * Check if the data size exceeds 32KiB (32768 bytes).
         * If it does, an IllegalArgumentException is thrown to prevent registering oversized data.
         * This ensures that the data can fit within the bank's capacity.
         */
        if (data.size > 32768) {
            
            throw new IllegalArgumentException("Data size exceeds 32KiB, cannot register in bank " + bank + ".");
            
        }

        /**
         * Check if the bank is full by calculating the total size of all Data objects in the bank.
         * If the total size plus the size of the new data exceeds 32KiB, an IllegalArgumentException is thrown.
         * This ensures that the bank does not exceed its maximum capacity.
         */
        short bank_size = 0;

        for (Data d : this.banks.get(bank)) {

            /**
             * Check if the Data object is null before accessing its size.
             * If it is null, continue to the next Data object in the bank.
             * This prevents a NullPointerException from being thrown when trying to access the size of a
             * null Data object.
             */
            if (d == null) {  continue;  }

            /**
             * If the Data object is not null, add its size to the total bank size.
             * This is done to calculate the total size of all Data objects in the bank.
             * If the size of the Data object is not zero, it is added to the bank_size.
             * 
             * This is important to ensure that the bank does not exceed its maximum capacity
             * of 32KiB (32768 bytes).
             */
            if (d.size != 0) {  bank_size += d.size;  }

        }

        /**
         * Check if the bank is full by comparing the total size of the data in the bank
         * with the maximum allowed size of 32KiB (32768 bytes).
         * If the bank is full or the position is out of bounds, an IllegalArgumentException
         * is thrown.
         * This ensures that the data can be registered in the specified position without exceeding the bank's
         * capacity.
         * If the position is out of bounds or already occupied, an IllegalArgumentException is thrown
         * to prevent overwriting existing data or accessing invalid positions.
         */
        if (bank_size + data.size > 32768) {

            /**
             * If the bank is full, remove it from the possible banks set to prevent further registrations.
             * This ensures that no more data can be registered in a full bank.
             * An IllegalArgumentException is thrown to indicate that the bank is full and cannot accept more data.
             */
            this.possibleBanks.remove(bank);
            
            throw new IllegalArgumentException("Bank " + bank + " is full, cannot register more data.");
            
        } else if (position < 0 || position >= this.banks.get(bank).length || this.banks.get(bank)[position] != null) {
            
            throw new IllegalArgumentException("Position " + position + " is out of bounds for bank " + bank + ".");
            
        }

        /**
         * If all checks pass, the Data object is registered in the specified bank at the specified position.
         * This allows the Data object to be stored and accessed later using the bank and position.
         */

        if (!(data instanceof DataIT)) {

            this.banks.get(bank)[position] = data;
            
        }

        /*
         * TODO:
         *  Need to implement a logic to handle DataIT objects, since they are converted to SNES bank format,
         * they should not be directly registered in the banks map. Instead, their converted SNES
         * bank files should be registered. This requires additional logic to manage the conversion
         * and registration process for DataIT objects.
         */
        

    }
    
}