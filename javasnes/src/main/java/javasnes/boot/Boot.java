package javasnes.boot;

import java.util.Map;

/**
 *
 * Default boot configuration source code
 *
 * <pre>
 * spcBoot();
 * 
 * dmaClearVram();
 * bgInitTileSet(0, &javasnes_patterns, &javasnes_palette, 0, (&javasnes_patterns_end - &javasnes_patterns), (&javasnes_palette_end - &javasnes_palette), BG_16COLORS, 0x4000);
 * bgInitMapSet(0, &javasnes_map, (&javasnes_map_end - &javasnes_map), SC_32x32, 0x0000);
 *
 * setMode(BG_MODE1, 0);
 * bgSetDisable(1);
 * bgSetDisable(2);
 * setScreenOn();
 *
 * WaitForVBlank();
 *
 * // Wait 120 frames (2 seconds) to init the game
 * for (int i = 0; i < 120; i++) {
 *     WaitForVBlank();
 * }
 *
 * </pre>
 */
public class Boot {

    /**
     * SnesBootCommand: Structure to represent a boot command.
     * 
     * It has a command name and an array of arguments and a method to get the 
     * command as a string with its arguments enclosed in parentheses.
     */
    protected class SnesBootCommand {

        public String command;
        public String[] arguments;

        /**
         * Constructs a new SnesBootCommand object with the specified command and 
         * arguments.
         * @param command the command name
         * @param arguments the command arguments as Strings
         */
        public SnesBootCommand(String command, String[] arguments) {
            this.command = command;
            this.arguments = arguments;
        }

        /**
         * Returns the command as a string, with its arguments enclosed in parentheses, separated by commas.
         * If the command has no arguments, the string will end with a closing parenthesis.
         * 
         * Example: "dmaCopy(0x02000000, 0x02000000, 0x2000);"
         * 
         * @return the command as a string with its arguments enclosed in parentheses
        */
        public String getCommand() {

            StringBuilder sb = new StringBuilder();
            
            sb.append(this.command);
            sb.append('(');

            if (this.arguments == null) {
                sb.append(");");
                return sb.toString();
            }

            for (int i = 0; i < this.arguments.length; i++) {

                sb.append(this.arguments[i]);

                if (i < this.arguments.length - 1) {
                    sb.append(", ");
                }

            }

            sb.append(");");

            return sb.toString();
            
        }

    }

    /**
     * How many frames to wait showing the logo.
     * In NTSC mode(60Hz), 120 frames = 2 seconds
     * 
     * Default: 120 Frames = 2 seconds
     */
    public int showLogo = 120;

    /**
     * Mode to use.
     * 
     * - NAME: colors for each bg
     * - MODE      : BG0  BG1  BG2  BG3
     * 
     * - "BG_MODE0": 004  04   04   04
     * - "BG_MODE1": 016  16   04   --
     * - "BG_MODE3": 256  16   --   --
     * - "BG_MODE5": 016  04   --   --
     * 
     * Default: "BG_MODE1"
     */
    public String MODE = "BG_MODE1";

    /**
     * Enable each bg
     * 
     * - Default:    T    F    F    F
     * 
     * - "BG_MODE0": 004  04   04   04
     * - "BG_MODE1": 016  16   04   --
     * - "BG_MODE3": 256  16   --   --
     * - "BG_MODE5": 016  04   --   --
     * 
     * Default: {true, false, false, false}
     */
    public boolean[] bgsEnabled = {true, false, false, false};

    /**
     * ======================
     * SPC COMMANDS
     * ======================
     */

    /**
     * SPC boot command.
     * It inits the SPC700 audio processor.
     */
    public SnesBootCommand spcBoot = new SnesBootCommand(
        "spcBoot", null
    );

    /**
     * ======================
     * VRAM COMMANDS
     * ======================
     */

    /**
     * Clears the VRAM.
     * It avoids any VRAM corruption before editing it.
     */
    public SnesBootCommand dmaClearVram = new SnesBootCommand(
        "dmaClearVram", null
    );

    /**
     * Initializes the tileset for the background.
     */
    public SnesBootCommand bgInitTileSet = new SnesBootCommand(
        "bgInitTileSet",
        new String[] {
            "0",
            "&javasnes_patterns",
            "&javasnes_palette",
            "0",
            "(&javasnes_patterns_end - &javasnes_patterns)",
            "(&javasnes_palette_end - &javasnes_palette)",
            "BG_16COLORS",
            "0x4000"
        }
    );

    /**
     * Initializes the mapset for the background.
     */
    public SnesBootCommand bgInitMapSet = new SnesBootCommand(
        "bgInitMapSet",
        new String[] {
            "0",
            "&javasnes_map",
            "(&javasnes_map_end - &javasnes_map)",
            "SC_32x32",
            "0x0000"
        }
    );

    /**
     * ======================
     * VARIOUS COMMANDS
     * ======================
     */

    /**
     * Wait for vblank is a command that makes the software wait for the end of the 
     * vblank period before executing the next command.
     * It can be used outside the boot sequence for your game logic synchronization.
     */
    public SnesBootCommand waitForVBlank = new SnesBootCommand(
        "WaitForVBlank", null
    );

    public SnesBootCommand setMode;
    public SnesBootCommand setScreenOn = new SnesBootCommand(
        "setScreenOn", null
    );

    /**
     * ======================
     * BOOT SEQUENCE COMMANDS
     * ======================
     */

    /**
     * <h3>Boot sequence commands</h3>
     * <br><br>
     * <p>
     * <pre>
     * preSPCLoadCommands[]... ------ COMMANDS OF THIS ARRAY HERE
     * 
     * spcBoot();
     * 
     * betweenSPCVRAMLoadCommands[]...
     * 
     * dmaClearVram();
     * bgInitTileSet(0, &javasnes_patterns, &javasnes_palette, 0, (&javasnes_patterns_end - &javasnes_patterns), (&javasnes_palette_end - &javasnes_palette), BG_16COLORS, 0x4000);
     * bgInitMapSet(0, &javasnes_map, (&javasnes_map_end - &javasnes_map), SC_32x32, 0x0000);
     *
     * postVRAMLoadCommands[]...
     * 
     * setMode(BG_MODE(N), 0);
     * ...
     * bgSetDisable(N);
     * ...
     * setScreenOn();
     * 
     * postBootCommands[]...
     * 
     * for (int i = 0; i < N; i++) {
     *     WaitForVBlank();
     * }
     * 
     * postLogoCommands[]...
     * </pre>
     * </p>
     */
    public SnesBootCommand[] preSPCLoadCommands;

    /**
     * <h3>Boot sequence commands</h3>
     * <br><br>
     * <p>
     * <pre>
     * preSPCLoadCommands[]...
     * 
     * spcBoot();
     * 
     * betweenSPCVRAMLoadCommands[]... ------ COMMANDS OF THIS ARRAY HERE
     * 
     * dmaClearVram();
     * bgInitTileSet(0, &javasnes_patterns, &javasnes_palette, 0, (&javasnes_patterns_end - &javasnes_patterns), (&javasnes_palette_end - &javasnes_palette), BG_16COLORS, 0x4000);
     * bgInitMapSet(0, &javasnes_map, (&javasnes_map_end - &javasnes_map), SC_32x32, 0x0000);
     *
     * postVRAMLoadCommands[]...
     * 
     * setMode(BG_MODE(N), 0);
     * ...
     * bgSetDisable(N);
     * ...
     * setScreenOn();
     * 
     * postBootCommands[]...
     * 
     * for (int i = 0; i < N; i++) {
     *     WaitForVBlank();
     * }
     * 
     * postLogoCommands[]...
     * </pre>
     * </p>
     */
    public SnesBootCommand[] betweenSPCVRAMLoadCommands;

    /**
     * <h3>Boot sequence commands</h3>
     * <br><br>
     * <p>
     * <pre>
     * preSPCLoadCommands[]...
     * 
     * spcBoot();
     * 
     * betweenSPCVRAMLoadCommands[]...
     * 
     * dmaClearVram();
     * bgInitTileSet(0, &javasnes_patterns, &javasnes_palette, 0, (&javasnes_patterns_end - &javasnes_patterns), (&javasnes_palette_end - &javasnes_palette), BG_16COLORS, 0x4000);
     * bgInitMapSet(0, &javasnes_map, (&javasnes_map_end - &javasnes_map), SC_32x32, 0x0000);
     *
     * postVRAMLoadCommands[]... ------ COMMANDS OF THIS ARRAY HERE
     * 
     * setMode(BG_MODE(N), 0);
     * ...
     * bgSetDisable(N);
     * ...
     * setScreenOn();
     * 
     * postBootCommands[]...
     * 
     * for (int i = 0; i < N; i++) {
     *     WaitForVBlank();
     * }
     * 
     * postLogoCommands[]...
     * </pre>
     * </p>
     */
    public SnesBootCommand[] postVRAMLoadCommands;

    /**
     * <h3>Boot sequence commands</h3>
     * <br><br>
     * <p>
     * <pre>
     * preSPCLoadCommands[]...
     * 
     * spcBoot();
     * 
     * betweenSPCVRAMLoadCommands[]...
     * 
     * dmaClearVram();
     * bgInitTileSet(0, &javasnes_patterns, &javasnes_palette, 0, (&javasnes_patterns_end - &javasnes_patterns), (&javasnes_palette_end - &javasnes_palette), BG_16COLORS, 0x4000);
     * bgInitMapSet(0, &javasnes_map, (&javasnes_map_end - &javasnes_map), SC_32x32, 0x0000);
     *
     * postVRAMLoadCommands[]...
     * 
     * setMode(BG_MODE(N), 0);
     * ...
     * bgSetDisable(N);
     * ...
     * setScreenOn();
     * 
     * postBootCommands[]... ------ COMMANDS OF THIS ARRAY HERE
     * 
     * for (int i = 0; i < N; i++) {
     *     WaitForVBlank();
     * }
     * 
     * postLogoCommands[]...
     * </pre>
     * </p>
     */
    public SnesBootCommand[] postBootCommands;

    /**
     * <h3>Boot sequence commands</h3>
     * <br><br>
     * <p>
     * <pre>
     * preSPCLoadCommands[]...
     * 
     * spcBoot();
     * 
     * betweenSPCVRAMLoadCommands[]...
     * 
     * dmaClearVram();
     * bgInitTileSet(0, &javasnes_patterns, &javasnes_palette, 0, (&javasnes_patterns_end - &javasnes_patterns), (&javasnes_palette_end - &javasnes_palette), BG_16COLORS, 0x4000);
     * bgInitMapSet(0, &javasnes_map, (&javasnes_map_end - &javasnes_map), SC_32x32, 0x0000);
     *
     * postVRAMLoadCommands[]...
     * 
     * setMode(BG_MODE(N), 0);
     * ...
     * bgSetDisable(N);
     * ...
     * setScreenOn();
     * 
     * postBootCommands[]...
     * 
     * for (int i = 0; i < N; i++) {
     *     WaitForVBlank();
     * }
     * 
     * postLogoCommands[]... ------ COMMANDS OF THIS ARRAY HERE
     * </pre>
     * </p>
     */
    public SnesBootCommand[] postLogoCommands;

    /**
     * Default boot configuration,
     * which does not edit the default boot sequence.
     */
    public Boot() {

        this.preSPCLoadCommands = null;
        this.betweenSPCVRAMLoadCommands = null;
        this.postVRAMLoadCommands = null;
        this.postBootCommands = null;
        this.postLogoCommands = null;

    }

    /**
     * Boot configuration from a map of boot sequence commands.
     * 
     * The Map follow the following structure:
     * 
     * key: String => {
     *      "preSPCLoadCommands" ||
     *      "betweenSPCVRAMLoadCommands" ||
     *      "postVRAMLoadCommands" ||
     *      "postBootCommands" ||
     *      "postLogoCommands"
     * }
     * 
     * value: Map<String, String[]> => {
     * 
     *      {   key          : value          
     *          "CommandName": ["CommandArgument1", "CommandArgument2", ...]
     *          ...
     *      }
     *    
     * }
     * 
     * @param commands a map of boot sequence commands
     */
    public Boot(
        Map<String, Map<String, String[]>> commands
    ) {

        if (commands.get("preSPCLoadCommands") != null) {

            this.preSPCLoadCommands = new SnesBootCommand[
                commands.get("preSPCLoadCommands").size()
            ];

            for (int i = 0; i < this.preSPCLoadCommands.length; i++) {
                this.preSPCLoadCommands[i] = new SnesBootCommand(
                    String.valueOf(commands.keySet().toArray()[i]),
                    commands.get("preSPCLoadCommands").get(String.valueOf(i))
                );
            }
            
        }

        if (commands.get("betweenSPCVRAMLoadCommands") != null) {

            this.betweenSPCVRAMLoadCommands = new SnesBootCommand[
                commands.get("betweenSPCVRAMLoadCommands").size()
            ];

            for (int i = 0; i < this.betweenSPCVRAMLoadCommands.length; i++) {
                this.betweenSPCVRAMLoadCommands[i] = new SnesBootCommand(
                    String.valueOf(commands.keySet().toArray()[i]),
                    commands.get("betweenSPCVRAMLoadCommands").get(String.valueOf(i))
                );
            }
            
        }

        if (commands.get("postVRAMLoadCommands") != null) {

            this.postVRAMLoadCommands = new SnesBootCommand[
                commands.get("postVRAMLoadCommands").size()
            ];

            for (int i = 0; i < this.postVRAMLoadCommands.length; i++) {
                this.postVRAMLoadCommands[i] = new SnesBootCommand(
                    String.valueOf(commands.keySet().toArray()[i]),
                    commands.get("postVRAMLoadCommands").get(String.valueOf(i))
                );
            }
            
        }

        if (commands.get("postBootCommands") != null) {

            this.postBootCommands = new SnesBootCommand[
                commands.get("postBootCommands").size()
            ];

            for (int i = 0; i < this.postBootCommands.length; i++) {
                this.postBootCommands[i] = new SnesBootCommand(
                    String.valueOf(commands.keySet().toArray()[i]),
                    commands.get("postBootCommands").get(String.valueOf(i))
                );
            }
            
        }

        if (commands.get("postLogoCommands") != null) {

            this.postLogoCommands = new SnesBootCommand[
                commands.get("postLogoCommands").size()
            ];

            for (int i = 0; i < this.postLogoCommands.length; i++) {
                this.postLogoCommands[i] = new SnesBootCommand(
                    String.valueOf(commands.keySet().toArray()[i]),
                    commands.get("postLogoCommands").get(String.valueOf(i))
                );
            }
            
        }
        
    }

    /**
     * Returns an array of booleans indicating which backgrounds can be used in the given
     * mode.
     * 
     * @return an array of booleans, where each index corresponds to the BG number
     * (0, 1, 2, 3). The value at each index is true if the corresponding BG can be used
     * in the given mode, and false otherwise.
     */
    public boolean[] getPossibleBgs() {

        boolean[] possibleBgs = new boolean[4];

        switch (this.MODE) {

            case "BG_MODE0":
                
                possibleBgs[0] = true;
                possibleBgs[1] = true;
                possibleBgs[2] = true;
                possibleBgs[3] = true;
                break;

            case "BG_MODE1":
                possibleBgs[0] = true;
                possibleBgs[1] = true;
                possibleBgs[2] = true;
                possibleBgs[3] = false;
                break;

            case "BG_MODE3":
                possibleBgs[0] = true;
                possibleBgs[1] = true;
                possibleBgs[2] = false;
                possibleBgs[3] = false;
                break;
            
            case "BG_MODE5":
                possibleBgs[0] = true;
                possibleBgs[1] = true;
                possibleBgs[2] = false;
                possibleBgs[3] = false;
                break;

            default:

                throw new AssertionError("Unknown mode: " + this.MODE);
        }

        return possibleBgs;

    }

    /**
     * Generates the source code representation of the boot configuration.
     * 
     * This method generates the source code for the boot configuration, including
     * the pre-SPC load commands, the SPC boot command, the commands between the
     * SPC boot and VRAM load, the VRAM load commands, the post-VRAM load commands,
     * the post-boot commands, and the post-logo commands.
     * 
     * The generated source code is a string in the format of
     * preSPCLoadCommands
     * spcBoot
     * betweenSPCVRAMLoadCommands
     * dmaClearVram
     * bgInitTileSet
     * bgInitMapSet
     * postVRAMLoadCommands
     * setMode
     * bgSetDisable (if necessary)
     * setScreenOn
     * waitForVBlank
     * for loop (showLogo)
     *     waitForVBlank
     * postLogoCommands
     * 
     * @return the source code representation of the boot configuration
     */
    public String getSourceCode() {

        StringBuilder sb = new StringBuilder();

        if (this.preSPCLoadCommands != null) {
            for (SnesBootCommand command : this.preSPCLoadCommands) {
                sb.append(command.getCommand()).append("\n");
            }
        }

        sb.append("\n");
        sb.append(this.spcBoot.getCommand());
        sb.append("\n");

        if (this.betweenSPCVRAMLoadCommands != null) {
            for (SnesBootCommand command : this.betweenSPCVRAMLoadCommands) {
                sb.append(command.getCommand()).append("\n");
            }
        }

        sb.append("\n");
        sb.append(this.dmaClearVram.getCommand());
        sb.append(this.bgInitTileSet.getCommand());
        sb.append(this.bgInitMapSet.getCommand());
        sb.append("\n");

        if (this.postVRAMLoadCommands != null) {
            for (SnesBootCommand command : this.postVRAMLoadCommands) {
                sb.append(command.getCommand()).append("\n");
            }
        }

        sb.append("\n");
        sb.append(this.setMode.getCommand());

        for (int i = 0; i < 4; i++) {

            if (this.getPossibleBgs()[i] && !this.bgsEnabled[i]) {

                SnesBootCommand bgSetDisabled = new SnesBootCommand(
                    "bgSetDisable",
                    new String[] {
                        String.valueOf(i)
                    }
                );
                sb.append(bgSetDisabled.getCommand());

            }

        }

        sb.append(this.setScreenOn.getCommand());
        sb.append("\n");

        if (this.postBootCommands != null) {
            for (SnesBootCommand command : this.postBootCommands) {
                sb.append(command.getCommand()).append("\n");
            }
        }

        sb.append("\n");
        sb.append(this.waitForVBlank.getCommand());
        sb.append("\n");

        sb.append("for (int i = 0; i < ").append(this.showLogo).append("; i++) {\n");
        sb.append("\t").append(this.waitForVBlank.getCommand()).append("\n");
        sb.append("}");

        if (this.postLogoCommands != null) {
            for (SnesBootCommand command : this.postLogoCommands) {
                sb.append(command.getCommand()).append("\n");
            }
        }

        return sb.toString();

    }

}
