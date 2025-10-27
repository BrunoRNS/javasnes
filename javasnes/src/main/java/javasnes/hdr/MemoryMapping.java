package javasnes.hdr;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * Memory mapping configuration for the game.
 * 
 * Below there's the default assembly hdr mapping from pvsneslib
 * it supports LoROM | HiROM and SlowROM | FastROM

.define HIROM 1 ; If you want HiROM, comment this line for LoROM
.define FASTROM 1 ; If you want FastROM, comment this line for SlowROM

.ifndef HIROM                     ;==LoRom==

.MEMORYMAP                      ; Begin describing the system architecture.
  SLOTSIZE $8000                ; The slot is $8000 bytes in size. More details on slots later.
  DEFAULTSLOT 0                 ; There's only 1 slot in SNES, there are more in other consoles.
  SLOT 0 $8000                  ; Defines Slot 0's starting address.
  SLOT 1 $0 $2000
  SLOT 2 $2000 $E000
  SLOT 3 $0 $10000
.ENDME          ; End MemoryMap definition

.ROMBANKSIZE $8000              ; Every ROM bank is 32 KBytes in size

.else                           ;==HiRom==

.MEMORYMAP                      ; Begin describing the system architecture.
  SLOTSIZE $10000               ; The slot is $10000 bytes in size. More details on slots later.
  DEFAULTSLOT 0                 ; There's only 1 slot in SNES, there are more in other consoles.
  SLOT 0 $0000                  ; Defines Slot 0's starting address.
  SLOT 1 $0 $2000
  SLOT 2 $2000 $E000
  SLOT 3 $0 $10000
  SLOT 4 $6000                  ; Used for direct SRAM access.
.ENDME          ; End MemoryMap definition

.ROMBANKSIZE $10000              ; Every ROM bank is 32 KBytes in size

.endif

.ROMBANKS 8                     ; 2 Mbits - Tell WLA we want to use 8 ROM Banks

.SNESHEADER
  ID "SNES"                     ; 1-4 letter string, just leave it as "SNES"

  NAME "LIBSNES HIROM MEM MAP"  ; Program Title - can't be over 21 bytes,
  ;    "123456789012345678901"  ; use spaces for unused bytes of the name.

.ifdef FASTROM
  FASTROM
.else
  SLOWROM
.endif

.ifdef HIROM
  HIROM
.else
  LOROM
.endif

  CARTRIDGETYPE $00             ; $00=ROM, $01=ROM+RAM, $02=ROM+SRAM, $03=ROM+DSP1, $04=ROM+RAM+DSP1, $05=ROM+SRAM+DSP1, $13=ROM+Super FX
  ROMSIZE $08                   ; $08=2 Megabits, $09=4 Megabits,$0A=8 Megabits,$0B=16 Megabits,$0C=32 Megabits
  SRAMSIZE $00                  ; $00=0 kilobits, $01=16 kilobits, $02=32 kilobits, $03=64 kilobits
  COUNTRY $01                   ; $01= U.S., $00=Japan, $02=Europe, $03=Sweden/Scandinavia, $04=Finland, $05=Denmark, $06=France, $07=Netherlands, $08=Spain, $09=Germany, $0A=Italy, $0B=China, $0C=Indonesia, $0D=Korea
  LICENSEECODE $00              ; Just use $00
  VERSION $00                   ; $00 = 1.00, $01 = 1.01, etc.
.ENDSNES

.SNESNATIVEVECTOR               ; Define Native Mode interrupt vector table
  COP EmptyHandler
  BRK EmptyHandler
  ABORT EmptyHandler
  NMI VBlank
  IRQ EmptyHandler
.ENDNATIVEVECTOR

.SNESEMUVECTOR                  ; Define Emulation Mode interrupt vector table
  COP EmptyHandler
  ABORT EmptyHandler
  NMI EmptyHandler
  RESET tcc__start                   ; where execution starts
  IRQBRK EmptyHandler
.ENDEMUVECTOR

.ifdef FASTROM
.ifdef HIROM
.BASE $C0
.else
.BASE $80
.endif
.else
.ifdef HIROM
.BASE $40
.endif
.endif

 * This class is a placeholder for memory mapping configurations.
 * The App class handle the static configuration of the memory mapping.
 * You can directly edit the assembly configuration above in a more "java" style way.
 * When the application builds, the configuration will be written to the assembly file.
 * If you need a more complex memory mapping, you can edit the assembly configuration directly.
 * Make sure to keep the configuration in sync with the App class.

 * The constructor method of this class will take the static configuration from the App class
 * and write it to the assembly file when the application builds if the configuration is set to be
 * generated from the static class configuration, otherwise it will use the assembly configuration directly.

 * For more information on memory mapping, refer to the pvsneslib documentation:
 * https://github.com/alekmaul/pvsneslib/wiki/HiRom-and-FastRom
 * https://github.com/alekmaul/pvsneslib/wiki/SNES-ROM-Header

*/
public class MemoryMapping {


    /**
     * This field holds the assembly memory mapping configuration as a string, which will be written
     * in the hdr.asm file. This attribute can be used if you want to use a custom assembly memory 
     * mapping configuration. If you want to use the static configuration from the App class, do not
     * set this attribute and leave it as null.
     */
    public String assemblyMapping = null;


    /*
     * ======================================================================
     * The following fields are used to configure the memory mapping.
     * You can edit these fields to set the desired memory mapping for 
     * your ROM. Make sure to follow the SNES memory mapping specifications.
     * ======================================================================
     */


    /**
     * If false, the memory mapping will use the LoROM structure. If true, it will use the HiROM structure.
     * The default is false (LoROM), which is the most common memory mapping for SNES games, and is the
     * most used by the pvsneslib library examples, I highly recommend you to use LoROM unless you have a
     * specific reason to use HiROM.
     */
    public boolean HiROM = false;

    /**
     * If false, the memory mapping will use the SlowROM structure. If true, it will use the FastROM structure.
     * The default is false (SlowROM), which is the most common memory mapping for SNES games, and is the
     * most used by the pvsneslib library examples, I highly recommend you to use SlowROM unless you have a
     * specific reason to use FastROM.
     */
    public boolean FastROM = false;

    /**
     * The number of ROM banks to be used in the game. The default in pvsneslib is 8, which is 2 Megabits.
     * You can set this value to any number between 4 and 32, but keep in mind that the maximum
     * size of a SNES ROM is 32 Megabits (4 MB), which is 32 banks of 32 KiB each.
     * 
     * Note: The number of ROM banks must be a power of 2 (4, 8, 16, 32).
     * If you set a value that is not a power of 2, the application will throw an exception
     * when building the project.
     * 
     * Working with javasnes you should not change 32 banks to any other value because of AppData class
     * which is configured to work with 32 banks, if you need to use less banks, you must modify the 
     * AppData class to work with the number of banks you need. Even if you use less banks, I still strongly
     * recommend you to keep the value as 32 to avoid any type of issues with the AppData class.
     */
    public char rombanks = 32;


    /*
     * =================================================================
     * The following fields are used to configure the SNES ROM header.
     * You can edit these fields to set the desired values for your ROM.
     * Make sure to follow the SNES ROM header specifications.
     * =================================================================
    */


    /**
     * The ID field in the SNES ROM header. This field is a 4 character string that identifies the ROM.
     * The default value is "SNES", which is the standard ID for SNES ROMs. You can just leave it as is.
     * If you want to change it, make sure to use a 4 character string, otherwise an exception will be thrown.
     */
    public String ID = "SNES";

    /**
     * The Name field in SNES ROM header. This field is a 21 character string that represents 
     * the name of the game. Make sure to use spaces for unused bytes of the name. The default value is 
     * "                     " (21 spaces). You should change it to the name of your game, but make sure
     * it does not exceed or complete 21 characters, otherwise an exception will be thrown.
     */
    public String name = "                     ";

    /**
     * The Cartridge Type field in the SNES ROM header. This field is a value that indicates the type of
     * cartridge used by the game. The default value is "$00", which indicates a standard ROM cartridge.
     * Other common values include:
     * - $01: ROM + RAM
     * - $02: ROM + SRAM
     * - $03: ROM + DSP1
     * - $04: ROM + RAM + DSP1
     * - $05: ROM + SRAM + DSP1
     * - $13: ROM + Super FX
     * 
     * For a complete list of cartridge types, refer to the SNES documentation.
     */
    public String cartridgeType = "$00";

    /**
     * The ROM Size field in the SNES ROM header. This field indicates the size of the ROM in megabits.
     * The value is represented as a hexadecimal value, where each increment represents a doubling of the
     * size. The default value is "$0C", which corresponds to 32 megabits (4 MiB).
     * 
     * Common values include:
     * 
     * - $08: 2 megabits (256 KiB)
     * - $09: 4 megabits (512 KiB)
     * - $0A: 8 megabits (1 MiB)
     * - $0B: 16 megabits (2 MiB)
     * - $0C: 32 megabits (4 MiB)
     * 
     * If you change the number of ROM banks, make sure to update this field accordingly, if not,
     * an exception will be thrown when building the project, or the ROM may not work as expected.
     * For example, if you set the number of ROM banks to 16, you should set this field to "$0B".
     * You should not change rombanks count to any other value than 32 because of AppData class,
     * so you shouldn't change this field either.
     */
    public String romsize = "$0C";

    /**
     * The SRAM Size field in the SNES ROM header. This field indicates the size of the SRAM in kilobits.
     * The value is represented as a hexadecimal value, where each increment represents a doubling of the
     * size. The default value is "$00", which corresponds to 0 kilobits (no SRAM).
     * 
     * Common values include:
     * 
     * - $00: 0 kilobits (no SRAM)
     * - $01: 16 kilobits (2 KiB)
     * - $02: 32 kilobits (4 KiB)
     * - $03: 64 kilobits (8 KiB)
     * - $04: 128 kilobits (16 KiB)
     * - $05: 256 kilobits (32 KiB)
     * 
     * If your game does not use SRAM, you can leave this field as "$00". If your game uses SRAM,
     * make sure to set this field to the appropriate value based on the size of the SRAM used by your game.
     */
    public String sramsize = "$00";

    /**
     * The Country field in the SNES ROM header. This field indicates the country for which the game
     * was released. The value is represented as a hexadecimal value. The default value is "$01",
     * which corresponds to the United States. Other common values include:
     * 
     * - $00: Japan
     * - $01: U.S.
     * - $02: Europe
     * - $03: Sweden/Scandinavia
     * - $04: Finland
     * - $05: Denmark
     * - $06: France
     * - $07: Netherlands
     * - $08: Spain
     * - $09: Germany
     * - $0A: Italy
     * - $0B: China
     * - $0C: Indonesia
     * - $0D: Korea
     * 
     * For a complete list of country codes, refer to the SNES documentation.
     * 
     * You should be careful when changing this field, because it can affect the compatibility NTSC/PAL
     * of your game. For example, if you set this field to "$02" (Europe) works better with PAL consoles,
     * but your game may not work properly on NTSC consoles (U.S. and Japan), and vice versa.
     * 
     * The modern emulators usually handle this correctly, but the original hardware may not. But it depends
     * not only on this field, but also on the cartridge you are using and many other factors.
     * 
     * By default, you should leave this field as "$01" (U.S.) unless you have a specific reason to change it.
     */
    public String country = "$01";

    /**
     * The Licensee Code field in the SNES ROM header. This field indicates the licensee of the game.
     * The value is represented as a hexadecimal value. The default value is "$00", which corresponds
     * to no specific licensee. Other common values include:
     * 
     * - $01: Nintendo
     * - $08: Capcom
     * - $13: Electronic Arts
     * - $18: Hudson Soft
     * - $19: b-ai
     * - $1A: KSS
     * - $1D: Coconuts Japan
     * - $1F: Virgin Interactive
     * - $24: PCM Complete
     * - $25: San-X
     * - $28: Kotobuki Systems
     * - $29: Seta
     * - $30: Infogrames
     * - $31: Nintendo (old)
     * - $32: Bandai
     * - $33: Ocean/Acclaim
     * - $34: Konami
     * - $35: Hector
     * - $38: Capcom (old)
     * - $39: Banpresto
     * - $3C: Entertainment i
     * - $3E: Gremlin Graphics
     * 
     * For a complete list of licensee codes, refer to the SNES documentation.
     * 
     * You can usually leave this field as "$00" unless you have a specific reason to change it.
     */
    public String licenseeCode = "$00";

    /**
     * The Version field in the SNES ROM header. This field indicates the version of the game.
     * The value is represented as a hexadecimal value, where "$00" corresponds to version 1.0,
     * "$01" to version 1.1, and so on. The default value is "$00".
     * 
     * You should update this field if you release a new version of your game.
     * For example, if you release a bug fix or an update, you should increment this value
     * by 1. If you release a major update or a new edition of your game, you might want
     * to increment this value by more than 1.
     * 
     * However, keep in mind that many emulators and flash cartridges do not check this field,
     * so changing it may not have any practical effect on the game's behavior.
     * But it's still a good practice to keep it updated for version tracking purposes.
     */
    public String version = "$00";

    public MemoryMapping(Map<String, String> config) {

        this.setConfig(config);

        try {

            this.validateConfig();

        } catch (IllegalArgumentException e) {

            System.err.println("Memory mapping configuration error: " + e.getMessage());
            System.exit(1);

        }

        this.generateASM();

    }

    /**
     * Sets the configuration of the MemoryMapping class from a Map of String, String pairs.
     * 
     * The following keys are recognized and set the corresponding fields:
     * 
     * - "rombanks": The number of ROM banks to use.
     * - "ID": The ID of the game.
     * - "name": The name of the game.
     * - "cartridgeType": The type of cartridge to use.
     * - "romsize": The size of the ROM banks in kilobits.
     * - "sramsize": The size of the SRAM in kilobits.
     * - "country": The country for which the game was released.
     * - "licenseeCode": The licensee code of the game.
     * - "version": The version of the game.
     * 
     * If any of the keys are not recognized, an IllegalArgumentException is thrown.
     * 
     * @param config The Map of String, String pairs to use for configuration.
     */
    public final void setConfig(Map<String, String> config) {

        if (config == null) {
            return;
        }

        for (Map.Entry<String, String> entry : config.entrySet()) {

            String key = entry.getKey();
            String value = entry.getValue();

            switch (key) {

                case "rombanks":
                    this.rombanks = (char) (Integer.parseInt(value));
                    break;
                
                case "ID":
                    this.ID = value;
                    break;
                
                case "name":
                    this.name = value;
                    break;
                
                case "cartridgeType":
                    this.cartridgeType = value;
                    break;
                
                case "romsize":
                    this.romsize = value;
                    break;
                
                case "sramsize":
                    this.sramsize = value;
                    break;
                
                case "country":
                    this.country = value;
                    break;
                
                case "licenseeCode":
                    this.licenseeCode = value;
                    break;
                
                case "version":
                    this.version = value;
                    break;
                
                default:
                    throw new IllegalArgumentException("Invalid config key: " + key);
            }

        }

    }

    /**
     * Checks if the current configuration is valid. If not, throws an 
     * IllegalArgumentException with a descriptive message.
     * 
     * This method checks the following:
     * - rombanks is a power of 2 between 4 and 32
     * - ID is exactly 4 characters
     * - name is exactly 21 characters
     * - cartridgeType is a valid hexadecimal value like "$00"
     * - romsize is a valid hexadecimal value like "$0C"
     * - sramsize is a valid hexadecimal value like "$00"
     * - country is a valid hexadecimal value like "$01"
     * - licenseeCode is a valid hexadecimal value like "$00"
     * - version is a valid hexadecimal value like "$00"
     * - romsize matches the number of rombanks
     * - sramsize is between $00 and $05
     * - if sramsize > $00, cartridgeType must support SRAM
     * 
     * If any of these checks fail, an IllegalArgumentException is thrown with a message
     * indicating the specific issue. This ensures that the configuration is valid before
     * generating the assembly file, preventing potential issues during ROM creation.
    */
    private void validateConfig() throws IllegalArgumentException {

        /*
         * Check if rombanks is a power of 2 between 4 and 32.
         */
        if (
            this.rombanks < 4 || this.rombanks > 32 || 
            (this.rombanks & (this.rombanks - 1)) != 0
        ) {

            throw new IllegalArgumentException(
                "Invalid number of ROM banks. Must be a power of 2 between 4 and 32."
            );
        
        }

        /*
         * Check if ID is exactly 4 characters.
         */
        if (this.ID.length() != 4) {

            throw new IllegalArgumentException(
                "Invalid ID length. Must be exactly 4 characters."
            );

        }

        /*
         * Check if name is exactly 21 characters.
         */
        if (this.name.length() != 21) {

            throw new IllegalArgumentException(
                "Invalid name length. Must be 21 characters."
            );

        }

        /*
         * Check if cartridgeType, romsize, sramsize, country, licenseeCode, and version 
         * are valid hexadecimal values.
         * The regex checks for a dollar sign followed by exactly two hexadecimal digits 
         * (0-9, A-F, a-f).
         */
        if (!this.cartridgeType.matches("\\$[0-9A-Fa-f]{2}")) {

            throw new IllegalArgumentException(
                "Invalid cartridge type. Must be a hexadecimal value like \"$00\"."
            );

        }

        /*
         * =============================================================================
         * Check if romsize, sramsize, country, licenseeCode, and version are valid 
         * hexadecimal values.
         * =============================================================================
         */

        if (!this.romsize.matches("\\$[0-9A-Fa-f]{2}")) {

            throw new IllegalArgumentException(
                "Invalid ROM size. Must be a hexadecimal value like \"$0C\"."
            );

        }

        if (!this.sramsize.matches("\\$[0-9A-Fa-f]{2}")) {

            throw new IllegalArgumentException(
                "Invalid SRAM size. Must be a hexadecimal value like \"$00\"."
            );

        }

        if (!this.country.matches("\\$[0-9A-Fa-f]{2}")) {

            throw new IllegalArgumentException(
                "Invalid country code. Must be a hexadecimal value like \"$01\"."
            );

        }

        if (!this.licenseeCode.matches("\\$[0-9A-Fa-f]{2}")) {

            throw new IllegalArgumentException(
                "Invalid licensee code. Must be a hexadecimal value like \"$00\"."
            );

        }

        if (!this.version.matches("\\$[0-9A-Fa-f]{2}")) {

            throw new IllegalArgumentException(
                "Invalid version. Must be a hexadecimal value like \"$00\"."
            );

        }

        /*
         * ============================================================================================
         * Check if romsize matches the number of rombanks.
         * $08 = 4 banks (2 Megabits)
         * $09 = 8 banks (4 Megabits)
         * $0A = 16 banks (8 Megabits)
         * $0B = 32 banks (16 Megabits)
         * $0C = 64 banks (32 Megabits)
         * 
         * If romsize is not in the range $08 to $0C, an exception is thrown.
         * If romsize is valid, the expected number of banks is calculated as:
         * expectedBanks = 2^(sizeValue - 8)
         * where sizeValue is the integer value of the hexadecimal part of romsize.
         * This is because $08 corresponds to 4 banks (2 Megabits), and each increment 
         * doubles the number of banks.
         * If the actual number of rombanks does not match the expected number, an exception is thrown.
         * ============================================================================================
         */

        if (this.romsize.charAt(1) == '0') {

            int sizeValue = Integer.parseInt(this.romsize.substring(2), 16);
            int expectedBanks = 1 << (sizeValue - 8); // Since $08 corresponds to 4 banks (2 Megabits)

            if (this.rombanks != expectedBanks) {

                throw new IllegalArgumentException(
                    "ROM size does not match the number of ROM banks. Expected " 
                    + expectedBanks + " banks for ROM size " + romsize + "."
                );

            }

        } else {

            throw new IllegalArgumentException(
                "Invalid ROM size format. Must be in the range $08 to $0C."
            );

        }

        /*
         * ============================================================================
         * Check if sramsize is between $00 and $05.
         * If sramsize is not in the range $00 to $05, an exception is thrown.
         * If sramsize is valid and greater than $00, check if cartridgeType supports
         * SRAM.
         * Cartridge types that support SRAM are: $01 (ROM + RAM), $02 (ROM + SRAM),
         * $04 (ROM + RAM + DSP1), $05 (ROM + SRAM + DSP1).
         * If sramsize > $00 and cartridgeType does not support SRAM, an exception is 
         * thrown.
         * ============================================================================
         */
        
        if (this.sramsize.charAt(1) == '0') {

            int sramValue = Integer.parseInt(this.sramsize.substring(2), 16);

            if (sramValue < 0 || sramValue > 5) {

                throw new IllegalArgumentException(
                    "Invalid SRAM size. Must be between $00 and $05."
                );

            }

            if  (
                    sramValue > 0 && (
                        this.cartridgeType.equals("$00") ||
                        this.cartridgeType.equals("$03") ||
                        this.cartridgeType.equals("$13")
                    )

                ) {

                    throw new IllegalArgumentException(
                        "SRAM size is set but cartridge type does not support SRAM. " +
                        "Please update the cartridge type."
                    );

                }

        } else {

            throw new IllegalArgumentException(
                "Invalid SRAM size format. Must be in the range $00 to $05."
            );

        }

    }

    /**
     * Generates a String containing the assembly memory mapping configuration
     * based on the static configuration fields defined in this class. This method constructs
     * the assembly code as a StringBuilder and assigns it to the assemblyMapping field.
     * 
     * This method is called in the constructor if the assemblyMapping field is null,
     * indicating that the user wants to generate the assembly configuration from the static
     * fields rather than providing a custom assembly configuration.
     * 
     * The generated assembly code includes definitions for HiROM/LoROM, FastROM/SlowROM,
     * memory map, ROM bank size, number of ROM banks, and the SNES ROM header with
     * the specified parameters such as ID, name, cartridge type, ROM size, SRAM size,
     * country, licensee code, and version.
     * 
     * It is based on the default assembly hdr mapping from pvsneslib and supports
     * both LoROM and HiROM configurations, as well as SlowROM and FastROM options.
     * 
     * For more information on memory mapping, refer to the pvsneslib documentation.
     */
    private void generateASM() {
        
        StringBuilder asm = new StringBuilder();

        asm.append(this.HiROM ? ".define HIROM 1 ; If you want HiROM, comment this line for LoROM\n" : "");
        asm.append(this.FastROM ? ".define FASTROM 1 ; If you want FastROM, comment this line for SlowROM\n" : "");
        asm.append("\n");
        asm.append(".ifndef HIROM                     ;==LoRom==\n");
        asm.append("\n");
        asm.append(".MEMORYMAP                      ; Begin describing the system architecture.\n");
        asm.append("  SLOTSIZE $8000                ; The slot is $8000 bytes in size. More details on slots later.\n");
        asm.append("  DEFAULTSLOT 0                 ; There's only 1 slot in SNES, there are more in other consoles.\n");
        asm.append("  SLOT 0 $8000                  ; Defines Slot 0's starting address.\n");
        asm.append("  SLOT 1 $0 $2000\n");
        asm.append("  SLOT 2 $2000 $E000\n");
        asm.append("  SLOT 3 $0 $10000\n");
        asm.append(".ENDME          ; End MemoryMap definition\n");
        asm.append("\n");
        asm.append(".ROMBANKSIZE $8000              ; Every ROM bank is 32 KBytes in size\n");
        asm.append("\n");
        asm.append(".else                           ;==HiRom==\n");
        asm.append("\n");
        asm.append(".MEMORYMAP                      ; Begin describing the system architecture.\n");
        asm.append("  SLOTSIZE $10000               ; The slot is $10000 bytes in size. More details on slots later.\n");
        asm.append("  DEFAULTSLOT 0                 ; There's only 1 slot in SNES, there are more in other consoles.\n");
        asm.append("  SLOT 0 $0000                  ; Defines Slot 0's starting address.\n");
        asm.append("  SLOT 1 $0 $2000\n");
        asm.append("  SLOT 2 $2000 $E000\n");
        asm.append("  SLOT 3 $0 $10000\n");
        asm.append("  SLOT 4 $6000                  ; Used for direct SRAM access.\n");
        asm.append(".ENDME          ; End MemoryMap definition\n");
        asm.append("\n");
        asm.append(".ROMBANKSIZE $10000              ; Every ROM bank is 32 KBytes in size\n");
        asm.append("\n");
        asm.append(".endif\n");
        asm.append("\n");
        asm.append(".ROMBANKS ").append(Integer.toString(this.rombanks)).append("                     ; ").append(Integer.toString(this.rombanks * 32)).append(" Mbits - Tell WLA we want to use ").append(Integer.toString(this.rombanks)).append(" ROM Banks\n");
        asm.append("\n");
        asm.append(".SNESHEADER\n");
        asm.append("  ID \"").append(this.ID).append("\"                     ; 1-4 letter string, just leave it as \"SNES\"\n");
        asm.append("\n");
        asm.append("  NAME \"").append(this.name).append("\"  ; Program Title - can't be over 21 bytes,\n");
        asm.append("  ;    \"123456789012345678901\"  ; use spaces for unused bytes of the name.\n");
        asm.append("\n");
        asm.append(this.FastROM ? "  FASTROM\n" : "  SLOWROM\n");
        asm.append("\n");
        asm.append(this.HiROM ? "  HIROM\n" : "  LOROM\n");
        asm.append("\n");
        asm.append("  CARTRIDGETYPE ").append(this.cartridgeType).append("             ; $00=ROM, $01=ROM+RAM, $02=ROM+SRAM, $03=ROM+DSP1, $04=ROM+RAM+DSP1, $05=ROM+SRAM+DSP1, $13=ROM+Super FX\n");
        asm.append("  ROMSIZE ").append(this.romsize).append("                   ; $08=2 Megabits, $09=4 Megabits,$0A=8 Megabits,$0B=16 Megabits,$0C=32 Megabits\n");
        asm.append("  SRAMSIZE ").append(this.sramsize).append("                  ; $00=0 kilobits, $01=16 kilobits, $02=32 kilobits, $03=64 kilobits\n");
        asm.append("  COUNTRY ").append(this.country).append("                   ; $01= U.S., $00=Japan, $02=Europe, $03=Sweden/Scandinavia, $04=Finland, $05=Denmark, $06=France, $07=Netherlands, $08=Spain, $09=Germany, $0A=Italy, $0B=China, $0C=Indonesia, $0D=Korea\n");
        asm.append("  LICENSEECODE ").append(this.licenseeCode).append("              ; Just use $00\n");
        asm.append("  VERSION ").append(this.version).append("                   ; $00 = 1.00, $01 = 1.01, etc.\n");
        asm.append(".ENDSNES\n");
        asm.append("\n");
        asm.append(".SNESNATIVEVECTOR               ; Define Native Mode interrupt vector table\n");
        asm.append("  COP EmptyHandler\n");
        asm.append("  BRK EmptyHandler\n");
        asm.append("  ABORT EmptyHandler\n");
        asm.append("  NMI VBlank\n");
        asm.append("  IRQ EmptyHandler\n");
        asm.append(".ENDNATIVEVECTOR\n");
        asm.append("\n");
        asm.append(".SNESEMUVECTOR                  ; Define Emulation Mode interrupt vector table\n");
        asm.append("  COP EmptyHandler\n");
        asm.append("  ABORT EmptyHandler\n");
        asm.append("  NMI EmptyHandler\n");
        asm.append("  RESET tcc__start                   ; where execution starts\n");
        asm.append("  IRQBRK EmptyHandler\n");
        asm.append(".ENDEMUVECTOR\n");
        asm.append("\n");
        asm.append(this.HiROM ? (this.FastROM ? ".BASE $C0\n" : ".BASE $80\n") : (this.FastROM ? ".BASE $40\n" : ""));
        asm.append("\n");

        this.assemblyMapping = asm.toString();

    }

    /**
     * Writes the assembly memory mapping configuration to the specified file.
     * This method takes the file path and the content as parameters and writes
     * the content to the file. If the file already exists, it will be overwritten.
     * 
     * The method uses a FileWriter and PrintWriter to write the content to the file.
     * 
     * If an IOException occurs during the file writing process, an error message is printed
     * to the standard error stream, and the application exits with a status code of 1.
     * 
     * @param filepath The path to the file where the memory mapping configuration will be written.
     */
    public void generateHDR(String filepath) {
        
        try (

            FileWriter filewriter = new FileWriter(filepath);
            PrintWriter writer = new PrintWriter(filewriter)

        ) {

            writer.println(this.assemblyMapping);
            
        } catch (

            IOException e

        ) {

            System.err.println(
                "Error writing memory mapping configuration to file: " + 
                e.getMessage()
            );

            System.exit(1);
        
        }

    }

}
