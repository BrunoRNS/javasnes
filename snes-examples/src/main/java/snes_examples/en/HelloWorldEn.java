package snes_examples.en;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javasnes.App;
import javasnes.boot.Boot;
import javasnes.data.Data;
import javasnes.hdr.MemoryMapping;
import javasnes.instruction.SnesInstruction;
import javasnes.makefile.Make;
import javasnes.output.SnesOutput;
import javasnes.util.structures.SnesLoadExtern;
import javasnes.util.types.AppData;
import javasnes.util.types.Processor;
import javasnes.util.types.SnesProcess;
import javasnes.util.types.vars.scalar.data.SnesChar;
import javasnes.util.types.vars.scalar.data.SnesVoid;

public class HelloWorldEn {

    final static SnesVoid VOID = new SnesVoid();
    final static SnesChar CHAR = new SnesChar("char");

    /**
     * Main entry point for the application.
     *
     * This method configures an App object and builds the application.
     *
     * @param args Command line arguments.
     * @throws Exception If there is an error when building the application.
     */
    public static void main(String[] args) throws Exception {

        App.Builder game = new App.Builder();

        MemoryMapping memoryMap = new MemoryMapping(configuration());
        game.setMemoryMapping(memoryMap);

        AppData romData = new AppData();
        romData.registerData(new Data("tilfont", "pvsneslibfont.pic", false), (byte) 2);
        romData.registerData(new Data("palfont", "pvsneslibfont.pal", false), (byte) 2);

        game.setAppData(romData);

        Boot bootSequence = new Boot(configurationForBoot());
        game.setBoot(bootSequence);

        SnesInstruction[] globalInstructions = new SnesInstruction[1];
        String[] globalLabels = {"tilfont", "palfont"};

        globalInstructions[0] = new SnesLoadExtern(globalLabels, CHAR);

        game.setGlobalInstructions(globalInstructions);

        Processor processor = new Processor();

        SnesProcess[] processes = new SnesProcess[1];
        processes[0] = printHelloWorld();

        processor.addProcess(processes[0], null);

        game
                .setProcessor(processor)
                .setSnesProcesses(processes);

        Path currentDir = Paths.get(
                HelloWorldEn.class.getProtectionDomain().getCodeSource().getLocation().toURI()
        ).normalize().toAbsolutePath().getParent();

        Path dataPath = currentDir.resolve("data").resolve("pvsneslibfont.png");
        Path outputPath = currentDir.resolve("output");

        game.addDataToCopy(dataPath.toString());
        game.setDestination(outputPath.toString());

        Make makefile = new Make();
        game.setMakefile(makefile);

        game.build();

    }

    /**
     * Returns a configuration map for the application.
     *
     * <p>
     * This map should contain the following structure:</p>
     *
     * <pre>
     * {
     *     "name": "Hello World from JavaSnes"
     * }
     * </pre>
     *
     * @return The configuration map for the application
     */
    public static Map<String, String> configuration() {

        Map<String, String> config = new HashMap<>();

        //                            123456789012345678901
        config.put("name", "Hello World from JavaSnes");

        return config;

    }

    /**
     * Configuration for the boot process.
     *
     * <p>
     * This method should return a map with the following structure:</p>
     *
     * <pre>
     * {
     *     "betweenSPCVRAMLoadCommands": {
     *         "consoleSetTextMapPtr": ["0x6800"],
     *         "consoleSetTextGfxPtr": ["0x3000"],
     *         "consoleSetTextOffset": ["0x0100"],
     *         "consoleInitText": ["0", "16 * 2", "&tilfont", "&palfont"],
     *         "bgSetGfxPtr": ["0", "0x2000"],
     *         "bgSetMapPtr": ["0", "0x6800", "SC_32x32"]
     *     }
     * }
     * </pre>
     *
     * @return The configuration map for the boot process.
     */
    public static Map<String, Map<String, String[]>> configurationForBoot() {

        Map<String, Map<String, String[]>> boot = new HashMap<>();

        boot.put("betweenSPCVRAMLoadCommands", new LinkedHashMap<>());

        boot.get("betweenSPCVRAMLoadCommands")
                .put("consoleSetTextMapPtr", new String[]{"0x6800"});

        boot.get("betweenSPCVRAMLoadCommands")
                .put("consoleSetTextGfxPtr", new String[]{"0x3000"});

        boot.get("betweenSPCVRAMLoadCommands")
                .put("consoleSetTextOffset", new String[]{"0x0100"});

        boot.get("betweenSPCVRAMLoadCommands")
                .put("consoleInitText", new String[]{
            "0", "16 * 2", "&tilfont", "&palfont"
        });

        boot.get("betweenSPCVRAMLoadCommands")
                .put("bgSetGfxPtr", new String[]{
            "0", "0x2000"
        });

        boot.get("betweenSPCVRAMLoadCommands")
                .put("bgSetMapPtr", new String[]{
            "0", "0x6800", "SC_32x32"
        });

        return boot;

    }

    /**
     * Prints "Hello World from JavaSnes!" to the screen.
     *
     * @return A SnesProcess containing the instructions to print the message
     */
    public static SnesProcess printHelloWorld() {

        SnesInstruction[] commands = new SnesInstruction[1];

        commands[0] = SnesOutput.consoleDrawText(10, 10, "Hello World from JavaSnes!");

        return new SnesProcess(
                "printHelloWorld",
                (byte) 0, commands, VOID
        );

    }

}
