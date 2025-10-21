package javasnes;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javasnes.hdr.MemoryMapping;
import javasnes.instruction.SnesInstruction;
import javasnes.makefile.Make;
import javasnes.util.macros.SnesMacro;
import javasnes.util.types.AppData;
import javasnes.util.types.AsmProcess;
import javasnes.util.types.Processor;
import javasnes.util.types.SnesProcess;

public class App {

    /**
     * Constructs a new App object.
     * @param appData
     * @param processor
     * @param snesProcesses
     * @param makefile
     * @param globalInstructions
     * @param snesMacros
     * @param destination
     */
    public App(
        AppData appData, Processor processor,
        List<SnesProcess> snesProcesses, Make makefile,
        List<SnesInstruction> globalInstructions,
        List<SnesMacro> snesMacros,
        String destination
    ) {

        String mainSrc = this.generateMain(
            snesMacros, snesProcesses, globalInstructions, processor
        );

        this.checkPath(destination);

        this.copyToDestination(destination);
        this.copyMain(mainSrc, destination);
        this.generateData(appData, destination);
        this.generateMakefile(makefile, destination);
        this.generateHDR(destination);

    }

    /**
     * Constructs a new App object.
     * @param appData
     * @param processor
     * @param snesProcesses
     * @param makefile
     * @param globalInstructions
     * @param snesMacros
     * @param asmProcesses
     * @param destination
     */
    public App(
        AppData appData, Processor processor,
        List<SnesProcess> snesProcesses, Make makefile,
        List<SnesInstruction> globalInstructions,
        List<SnesMacro> snesMacros,
        List<AsmProcess> asmProcesses,
        String destination
    ) {

        String mainSrc = this.generateMain(
            snesMacros, snesProcesses, globalInstructions, processor
        );

        this.checkPath(destination);

        this.copyToDestination(destination);
        this.copyMain(mainSrc, destination);
        this.generateASMProcesses(asmProcesses, destination);
        this.generateData(appData, destination);
        this.generateMakefile(makefile, destination);
        this.generateHDR(destination);

    }

    /**
     * Checks if the destination path exists and if not, creates it.
     * Also checks if the "res" and "src" directories exist in the destination path
     * and if not, creates them.
     * 
     * @param destination the destination path to check
     */
    public final void checkPath(String destination) {
        
        String separator = System.getProperty("os.name").toLowerCase().contains("win") ? "\\" : "/";

        if (!Files.exists(Paths.get(destination))) {
            try {
                Files.createDirectories(Paths.get(destination));
            } catch (IOException e) {
                System.err.println(
                    "Failed to create destination directory: " + e.getMessage()
                );
                System.exit(-1);
            }
        }

        if (!Files.exists(Paths.get(destination + separator + "res"))) {
            try {
                Files.createDirectories(Paths.get(destination + separator + "res"));
            } catch (IOException e) {
                System.err.println(
                    "Failed to create destination directory: " + e.getMessage()
                );
                System.exit(-1);
            }
        }

        if (!Files.exists(Paths.get(destination + separator + "src"))) {
            try {
                Files.createDirectories(Paths.get(destination + separator + "src"));
            } catch (IOException e) {
                System.err.println(
                    "Failed to create destination directory: " + e.getMessage()
                );
                System.exit(-1);
            }
        }

    }

    /**
     * Generate the main function, which is the entry point of the program.
     * 
     * <pre>
     * #include <snes.h>
     * #include "logo.h"
     *
     * int main(void) {
     *      // Initialize sound engine (take some time)
     *      spcBoot();
     *
     *      dmaClearVram();
     *
     *      initPVSnesLibLogo();
     *
     *      setFadeEffectEx(FADE_IN, 8);
     *      WaitForVBlank();
     *
     *      while (1) {
     *         if (updatePVSnesLibLogo() == 1) {
     *             // Here the processes called in the processor are executed
     *
     *         }
     *
     *          // Wait for vblank
     *          WaitForVBlank();
     *
     *      }
     *
     *      return 0;
     *  }
     * 
     * </pre>
     * 
     */
    public final String generateMain(
        List<SnesMacro> snesMacros,
        List<SnesProcess> processes,
        List<SnesInstruction> globalInstructions,
        Processor processor
    ) {

        StringBuilder sb = new StringBuilder();
        sb.append("#include <snes.h>\n");
        sb.append("#include \"logo.h\"\n");

        for (SnesMacro macro : snesMacros) {
            sb.append(macro.sourceCode).append("\n");
        }

        sb.append("\n");

        for (SnesInstruction instruction : globalInstructions) {
            sb.append(instruction.sourceCode).append("\n");
        }

        sb.append("\n");

        for (SnesProcess process : processes) {
            sb.append(process.getSourceCode()).append("\n");
        }

        sb.append(processor.generateSourceCode());

        sb.append("\n");
        sb.append("int main(void) {\n");
        sb.append("\tspcBoot();\n");
        sb.append("\tdmaClearVram();\n");
        sb.append("\tinitPVSnesLibLogo();\n");
        sb.append("\tsetFadeEffectEx(FADE_IN, 8);\n");
        sb.append("\tWaitForVBlank();\n");
        sb.append("\twhile (1) {\n");
        sb.append("\t\tif (updatePVSnesLibLogo() == 1) {\n");
        
        sb.append("\t\t\tprocessor();\n");

        sb.append("\t\t}\n");
        sb.append("\t\tWaitForVBlank();\n");
        sb.append("\t}\n");
        sb.append("\treturn 0;\n");
        sb.append("}\n");

        return sb.toString();

    }

    /**
     * Copies the main source code to the specified path.
     * 
     * @param content The source code to write to the file.
     * @param path The path where the file will be written.
     * @throws RuntimeException if an IOException occurs while writing the file.
     */
    public final void copyMain(String content, String path) {

        String separator = System.getProperty("os.name")
            .toLowerCase().contains("win") ? "\\" : "/";

        try {

            Files.write(
                Paths.get(path + separator + "src" + separator + "main.c"), 
                content.getBytes()
            );

        } catch (IOException e) {

            throw new RuntimeException(e.getMessage());
            
        }
        
    }

    /**
     * Writes the source code of all the assembly processes in the given list to
     * separate files in the given directory.
     * 
     * The file names of the generated files are the names of the assembly processes.
     * 
     * This method does not throw any exceptions if any errors occur during
     * the writing process. Instead, it wraps the exception in a RuntimeException
     * and re-throws it.
     * 
     * @param asmProcesses the list of assembly processes to be written to files
     * @param path the directory where the files will be written
     */
    public final void generateASMProcesses(List<AsmProcess> asmProcesses, String path) {

        for (AsmProcess asmProcess : asmProcesses) {

            String separator = System.getProperty("os.name").toLowerCase().contains("win") ? "\\" : "/";
        
            try (
                BufferedWriter writer = new BufferedWriter(
                    new FileWriter(path + separator + asmProcess.name)
                )
            ) {

                for (String line : asmProcess.sourceCode.split("\n")) {
                    writer.write(line);
                    writer.newLine();
                }

            } catch (IOException e) {

                throw new RuntimeException(e.getMessage());

            }
            
        }

    }

    /**
     * Writes the contents of the Makefile into a file at the specified path.
     * 
     * @param makefile the Makefile to write to the file
     * @param path the path to write the Makefile to
     * @throws RuntimeException if there is an IOException while writing to the file
     */
    public final void generateMakefile(Make makefile, String path) {

        String separator = System.getProperty("os.name").toLowerCase().contains("win") ? "\\" : "/";
        
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(path + separator + "Makefile")
            )
        ) {

            for (String line : makefile.getMakefile()) {
                writer.write(line);
                writer.newLine();
            }

        } catch (IOException e) {

            throw new RuntimeException(e.getMessage());

        }

    }

    /**
     * Writes the source code of the AppData object to a file at the specified path.
     * 
     * @param appData the AppData object to write to the file
     * @param path the directory where the file will be written
     * @throws RuntimeException if there is an IOException while writing to the file
     */
    public final void generateData(AppData appData, String path) {

        String out = appData.generateSourceCode();

        String separator = System.getProperty("os.name").toLowerCase().contains("win") ? "\\" : "/";
        
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(path + separator + "data.asm")
            )
        ) {

            for (String line : out.split("\n")) {
                writer.write(line);
                writer.newLine();
            }

        } catch (IOException e) {

            throw new RuntimeException(e.getMessage());

        }
        
    }

    /**
     * Writes the assembly memory mapping configuration to a file at the specified path.
     * The file is named "hdr.asm".
     * 
     * @param path the directory where the file will be written
     */
    @SuppressWarnings("unused")
    public final void generateHDR(String path) {
        
        String separator = System.getProperty("os.name").toLowerCase().contains("win") ? "\\" : "/";
        MemoryMapping mapping = new MemoryMapping(path + separator + "hdr.asm");

    }

    /**
     * Copies the logo resources from the JAR file to the specified directory.
     * The resources are copied to the "res" directory and the "src" directory.
     * 
     * The resources copied are:
     * 
     * - "logo.bmp"
     * - "logo.c"
     * - "logo.h"
     * - "logo.it"
     * - "logoScreen.bmp"
     * 
     * @param path the directory where the resources will be copied
     * @throws RuntimeException if there is an IOException while copying the resources
     */
    public final void copyToDestination(String path) {

        String separator = System.getProperty("os.name").toLowerCase().contains("win") ? "\\" : "/";
        
        try {
            Files.copy(
                getClass().getResourceAsStream("/logo.bmp"),
                Paths.get(path + separator + "res" + separator + "logo.bmp")
            );
            Files.copy(
                getClass().getResourceAsStream("/logo.c"),
                Paths.get(path + separator + "src" + separator + "logo.c")
            );
            Files.copy(
                getClass().getResourceAsStream("/logo.h"),
                Paths.get(path + separator + "src" + separator + "logo.h")
            );
            Files.copy(
                getClass().getResourceAsStream("/logo.it"),
                Paths.get(path + separator + "res" + separator + "logo.it")
            );
            Files.copy(
                getClass().getResourceAsStream("/logoScreen.bmp"),
                Paths.get(path + separator + "res" + separator + "logoScreen.bmp")
            );

        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }

    }
    
}
