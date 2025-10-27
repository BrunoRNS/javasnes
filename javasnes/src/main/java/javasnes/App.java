package javasnes;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javasnes.boot.Boot;
import javasnes.hdr.MemoryMapping;
import javasnes.instruction.SnesInstruction;
import javasnes.makefile.Make;
import javasnes.util.macros.SnesMacro;
import javasnes.util.types.AppData;
import javasnes.util.types.AsmProcess;
import javasnes.util.types.Processor;
import javasnes.util.types.SnesProcess;

/**
 * Main application class for generating SNES projects.
 * 
 * <p>This class handles the complete build process including generating source files,
 * copying resources, creating Makefiles, and setting up the project structure.</p>
 *
 * <p><b>Usage Examples:</b></p>
 * <pre>
 * {@code
 * 
 * // Basic usage with builder pattern
 * App app = new App.Builder()
 *     .setAppData(appData)
 *     .setProcessor(processor)
 *     .setDestination("/path/to/project")
 *     .setMakefile(makefile)
 *     .setBoot(boot)
 *     .setMemoryMapping(hdr)
 *     .setGlobalInstructions(globalInstructions)
 *     .setSnesProcesses(snesProcesses)
 *     .setAsmProcesses(asmProcesses)
 *     .setSnesMacros(snesMacros)
 *     .setDataToCopy(dataToCopy)
 *     .build();
 * 
 * // Advanced usage with builder pattern
 * App app = new App.Builder()
 *     .setAppData(appData)
 *     .setProcessor(processor)
 *     .setDestination("/path/to/project")
 *     .setMakefile(makefile)
 *     .setBoot(boot)
 *     .setMemoryMapping(hdr)
 *     .setGlobalInstructions(globalInstructions)
 *     .addGlobalInstruction(new SnesInstruction(...))
 *     .addGlobalInstructions(new SnesInstruction[] { ... })
 *     .setSnesProcesses(snesProcesses)
 *     .addSnesProcess(new SnesProcess(...))
 *     .addSnesProcesses(new SnesProcess[] { ... })
 *     .setAsmProcesses(asmProcesses)
 *     .addAsmProcess(new AsmProcess(...))
 *     .addAsmProcesses(new AsmProcess[] { ... })
 *     .setSnesMacros(snesMacros)
 *     .addSnesMacro(new SnesMacro(...))
 *     .addSnesMacros(new SnesMacro[] { ... })
 *     .setDataToCopy(dataToCopy)
 *     .addDataToCopy("/path/to/data/file")
 *     .addDataToCopy(new String[] { "/path/to/data/file", "/path/to/other/data/file" })
 *     .removeDataToCopy("/path/to/data/file")
 *     .removeDataToCopy(new String[] { "/path/to/data/file", "/path/to/other/data/file" })
 *     .removeSnesProcess(new SnesProcess(...))
 *     .removeSnesProcesses(new SnesProcess[] { ... })
 *     .removeAsmProcess(new AsmProcess(...))
 *     .removeAsmProcesses(new AsmProcess[] { ... })
 *     .removeSnesMacro(new SnesMacro(...))
 *     .removeSnesMacros(new SnesMacro[] { ... })
 *     .removeGlobalInstruction(new SnesInstruction(...))
 *     .removeGlobalInstructions(new SnesInstruction[] { ... })
 *     .build();
 * 
 * // Using getters
 * 
 * App.Builder appBuilder = new App.Builder()
 * 
 * appBuilder = appBuilder.setAppData(appData)
 *     .setProcessor(processor)
 *     .setDestination("/path/to/project")
 *     .setMakefile(makefile)
 *     .setBoot(boot)
 *     .setMemoryMapping(hdr);
 * 
 * // Get the values in the builder
 * Processor processor = appBuilder.getProcessor();
 * Destination destination = appBuilder.getDestination();
 * Makefile makefile = appBuilder.getMakefile();
 * Boot boot = appBuilder.getBoot();
 * MemoryMapping hdr = appBuilder.getMemoryMapping();
 * 
 * // Finally, build the application
 * App app = appBuilder.build();
 * 
 * }
 * </pre>
 */
public class App {

    /**
     * Protected fields for storing scalar configuration and build data.
     */
    protected Processor processor;
    protected String destination;
    protected MemoryMapping hdr;
    protected AppData appData;
    protected Make makefile;
    protected Boot boot;

    /**
     * Protected fields for storing lists of build data.
     */
    protected List<SnesInstruction> globalInstructions;
    protected List<SnesProcess> snesProcesses;
    protected List<AsmProcess> asmProcesses;
    protected List<SnesMacro> snesMacros;
    protected List<String> dataToCopy;

    /**
     * Private constructor for building the application.
     * 
     * @param builder The builder instance
     * @throws IOException If an error occurs during the build process
     */
    private App(App.Builder builder) throws IOException {
        
        this.destination = builder.destination;
        this.processor = builder.processor;
        this.makefile = builder.makefile;
        this.appData = builder.appData;
        this.boot = builder.boot;
        this.hdr = builder.hdr;

        this.globalInstructions = new ArrayList<>(builder.globalInstructions);
        this.snesProcesses = new ArrayList<>(builder.snesProcesses);
        this.asmProcesses = new ArrayList<>(builder.asmProcesses);
        this.snesMacros = new ArrayList<>(builder.snesMacros);
        this.dataToCopy = new ArrayList<>(builder.dataToCopy);

        this.destination = Paths.get(this.destination).toAbsolutePath().toString();
        
        try {

            this.executeBuildProcess();

        } catch (Exception e) {

            System.err.println("An error occurred during the build process: " + e.getMessage());
            throw new IOException(e);

        }

    }


    /**
     * Executes the build process, this method is called by the constructor.
     * It generates the main C source file content, ensures the destination structure,
     * copies resources, copies additional data files, generates source files, generates
     * assembly files, generates the data file, generates the Makefile and generates the
     * header file.
     * 
     * @throws Exception if an error occurs during the build process
     */
    private void executeBuildProcess() throws Exception {

        this.ensureDestinationStructure();
        this.copyAdditionalData();
        this.copyResources();

        this.generateSourceFiles(this.generateMain());
        this.generateAssemblyFiles();
        this.generateHeaderFile();
        this.generateDataFile();
        this.generateMakefile();

    }

    /**
     * Generates the main C source file content.
     * 
     * @return The complete main.c source code as a string
     */
    private String generateMain() {

        StringBuilder sb = new StringBuilder();

        sb.append("#include <snes.h>\n\n");

        for (SnesMacro macro : this.snesMacros) {

            sb.append(macro.sourceCode).append("\n");

        }

        sb.append("\n");

        for (SnesInstruction instruction : this.globalInstructions) {

            sb.append(instruction.sourceCode).append("\n");

        }

        sb.append("\n");

        for (SnesProcess process : this.snesProcesses) {

            sb.append(process.getSourceCode()).append("\n");

        }

        sb.append(this.processor.generateSourceCode()).append("\n");

        sb.append("int main(void) {\n");
        
        sb.append("\twhile (1) {\n");
        sb.append("\t\tprocessor();\n");
        sb.append("\t\tWaitForVBlank();\n");
        sb.append("\t}\n");
        sb.append("\treturn 0;\n");
        sb.append("}\n");

        return sb.toString();

    }

    /**
     * Ensures the destination directory structure exists.
     * Creates destination, res, and src directories if they don't exist.
     */
    private void ensureDestinationStructure() {

        createDirectoryIfNotExists(this.destination);

    }

    /**
     * Creates a directory if it doesn't exist.
     * 
     * @param path The directory path to create
     * @throws RuntimeException if directory creation fails
     */
    private void createDirectoryIfNotExists(String path) {

        if (!Files.exists(Paths.get(path))) {

            try {

                Files.createDirectories(Paths.get(path));

            } catch (IOException e) {

                throw new RuntimeException(
                    "Failed to create directory: " + path + " - " + e.getMessage(), e
                );
            
            }

        }

    }

    
    /**
     * Copies all resources from the classpath to the filesystem.
     * 
     * This method copies all resources from the classpath to the filesystem.
     * Currently, it only copies the javasnes_logo.png image file.
     */
    private void copyResources() {

        String separator = getFileSeparator();

        copyResource(
            "javasnes" + separator + "javasnes_logo.bmp", 
            this.destination + "javasnes_logo.bmp"
        );
    
    }

    /**
     * Copies a resource from the classpath to the filesystem.
     * 
     * @param resourcePath The classpath resource path
     * @param destinationPath The filesystem destination path
     * @throws RuntimeException if resource copying fails
     */
    private void copyResource(String resourcePath, String destinationPath) {
        try {

            Files.copy(
                getClass().getResourceAsStream(resourcePath),
                Paths.get(destinationPath).toAbsolutePath()
            );

        } catch (IOException e) {

            throw new RuntimeException(
                "Failed to copy resource: " + resourcePath + " - " + e.getMessage(), e
            );
        
        }

    }

    /**
     * Copies additional data files to the destination.
     */
    private void copyAdditionalData() {

        for (String dataPath : this.dataToCopy) {

            copyFileToDestination(
                Paths.get(dataPath).toAbsolutePath().toString(), this.destination
            );

        }

    }

    /**
     * Copies a file to the destination directory.
     * 
     * @param sourcePath The source file path
     * @param destinationDir The destination directory
     * @throws RuntimeException if file copying fails
     */
    private void copyFileToDestination(String sourcePath, String destinationDir) {

        try {

            String fileName = Paths.get(sourcePath).getFileName().toString();

            Files.copy(
                Paths.get(sourcePath),
                Paths.get(destinationDir, fileName)
            );

        } catch (IOException e) {

            throw new RuntimeException(
                "Failed to copy file: " + sourcePath + " - " + e.getMessage(), e
            );

        }

    }

    /**
     * Generates all source files including main.c.
     * 
     * @param mainSource The main.c source code content
     */
    private void generateSourceFiles(String mainSource) {

        writeToFile(mainSource, this.destination, "main.c");

    }

    /**
     * Generates assembly process files.
     */
    private void generateAssemblyFiles() {

        for (AsmProcess asmProcess : this.asmProcesses) {

            writeToFile(
                asmProcess.sourceCode, this.destination, asmProcess.name + ".asm"
            );

        }

    }

    /**
     * Generates the data.asm file.
     * 
     * @throws IllegalStateException if appData is null
     */
    private void generateDataFile() throws IllegalStateException {

        if (this.appData == null) {

            throw new IllegalStateException("AppData is required");

        }

        String dataSource = this.appData.generateSourceCode();
        writeToFile(dataSource, this.destination, "data.asm");

    }

    /**
     * Generates the Makefile.
     * 
     * @throws IllegalStateException if makefile is null
     */
    private void generateMakefile() {

        if (this.makefile == null) {

            throw new IllegalStateException("Makefile is required");

        }

        String[] makefileContent = this.makefile.getMakefile();
        StringBuilder content = new StringBuilder();

        for (String line : makefileContent) {

            content.append(line).append("\n");

        }

        writeToFile(content.toString(), this.destination, "Makefile");

    }

    /**
     * Generates the header file (hdr.asm).
     */
    private void generateHeaderFile() {

        String separator = getFileSeparator();
        this.hdr.generateHDR(this.destination + separator + "hdr.asm");

    }

    /**
     * Writes content to a file in the specified destination path.
     * 
     * @param content The content to write
     * @param basePath The base destination path
     * @param subPaths The subdirectory and file name path components
     * @throws RuntimeException if file writing fails
     */
    private void writeToFile(String content, String basePath, String... subPaths) {

        String fullPath = Paths.get(basePath, subPaths).toString();
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fullPath))) {

            for (String line : content.split("\n")) {

                writer.write(line);
                writer.newLine();

            }

        } catch (IOException e) {

            throw new RuntimeException(
                "Failed to write file: " + fullPath + " - " + e.getMessage(), e
            );
        
        }

    }

    /**
     * Gets the appropriate file separator for the current operating system.
     * 
     * @return The file separator string
     */
    private String getFileSeparator() {

        return System.getProperty("os.name").toLowerCase().contains("win") ? "\\" : "/";

    }

    /**
     * Builder class for the App class.
     */
    public static class Builder {

        private Processor processor = null;
        private String destination = null;
        private MemoryMapping hdr = null;
        private AppData appData = null;
        private Make makefile = null;
        private Boot boot = null;

        /**
         * Sets the processor configuration.
         * 
         * @param processor The processor configuration
         * @return This builder instance for method chaining
         */
        public Builder setProcessor(Processor processor) {

            this.processor = processor;
            return this;

        }

        /**
         * Gets the processor configuration.
         * 
         * @return The processor configuration
         */
        public Processor getProcessor() {

            return this.processor;
            
        }

        /**
         * Sets the destination directory for the build output.
         * 
         * @param destination The destination directory path
         * @return This builder instance for method chaining
         */
        public Builder setDestination(String destination) {

            this.destination = destination;
            return this;

        }

        /**
         * Gets the destination directory for the build output.
         * 
         * @return The destination directory path
         */
        public String getDestination() {

            return this.destination;
            
        }

        /**
         * Sets the memory mapping configuration.
         * 
         * @param hdr The memory mapping configuration
         * @return This builder instance for method chaining
         */
        public Builder setMemoryMapping(MemoryMapping hdr) {

            this.hdr = hdr;
            return this;

        }

        /**
         * Gets the memory mapping configuration.
         * 
         * @return The memory mapping configuration
         */
        public MemoryMapping getMemoryMapping() {

            return this.hdr;
            
        }

        /**
         * Sets the application data configuration.
         * 
         * @param appData The application data configuration
         * @return This builder instance for method chaining
         */
        public Builder setAppData(AppData appData) {

            this.appData = appData;
            return this;

        }

        /**
         * Gets the application data configuration.
         * 
         * @return The application data configuration
         */
        public AppData getAppData() {

            return this.appData;
            
        }

        /**
         * Sets the Makefile configuration.
         * 
         * @param makefile The Makefile configuration
         * @return This builder instance for method chaining
         */
        public Builder setMakefile(Make makefile) {

            this.makefile = makefile;
            return this;

        }

        /**
         * Gets the Makefile configuration.
         * 
         * @return The Makefile configuration
         */
        public Make getMakefile() {

            return this.makefile;
            
        }

        /**
         * Sets the boot configuration.
         * 
         * @param boot The boot configuration
         * @return This builder instance for method chaining
         */
        public Builder setBoot(Boot boot) {

            this.boot = boot;
            return this;

        }

        /**
         * Gets the boot configuration.
         * 
         * @return The boot configuration
         */
        public Boot getBoot() {

            return this.boot;
            
        }

        private List<SnesInstruction> globalInstructions = new ArrayList<>();
        private List<SnesProcess> snesProcesses = new ArrayList<>();
        private List<AsmProcess> asmProcesses = new ArrayList<>();
        private List<SnesMacro> snesMacros = new ArrayList<>();
        private List<String> dataToCopy = new ArrayList<>();

        /**
         * Sets the global instructions in the application.
         * 
         * Global instructions are inserted before the main loop of the application.
         * They are typically used to initialize variables, set up hardware, or perform
         * other tasks that need to be done before the main loop is entered.
         * 
         * @param instructions The list of global instructions to set
         * @return This builder instance for method chaining
         */
        public Builder setGlobalInstructions(List<SnesInstruction> instructions) {

            this.globalInstructions = instructions;
            return this;
            
        }

        /**
         * Sets the global instructions in the application.
         * 
         * Global instructions are inserted before the main loop of the application.
         * They are typically used to initialize variables, set up hardware, or perform
         * other tasks that need to be done before the main loop is entered.
         * 
         * @param instructions The array of global instructions to set
         * @return This builder instance for method chaining
         */
        public Builder setGlobalInstructions(SnesInstruction[] instructions) {

            this.globalInstructions = new ArrayList<>(Arrays.asList(instructions));
            return this;
            
        }

        /**
         * Adds a global instruction to the application.
         * 
         * @param instruction The global instruction to add
         * @return This builder instance for method chaining
         */
        public Builder addGlobalInstruction(SnesInstruction instruction) {

            this.globalInstructions.add(instruction);
            return this;

        }

        /**
         * Adds multiple global instructions to the application.
         * 
         * Global instructions are inserted before the main loop of the application.
         * They are typically used to initialize variables, set up hardware, or perform
         * other tasks that need to be done before the main loop is entered.
         * 
         * @param instructions The global instructions to add
         * @return This builder instance for method chaining
         */
        public Builder addGlobalInstructions(SnesInstruction... instructions) {

            this.globalInstructions.addAll(Arrays.asList(instructions));
            return this;
            
        }

        /**
         * Removes a global instruction from the application.
         * 
         * @param instruction The global instruction to remove
         * @return This builder instance for method chaining
         */
        public Builder removeGlobalInstruction(SnesInstruction instruction) {

            this.globalInstructions.remove(instruction);
            return this;

        }

        /**
         * Removes multiple global instructions from the application.
         * 
         * @param instructions The global instructions to remove
         * @return This builder instance for method chaining
         */
        public Builder removeGlobalInstructions(SnesInstruction... instructions) {

            this.globalInstructions.removeAll(Arrays.asList(instructions));
            return this;
            
        }

        /**
         * Gets all global instructions in the application.
         * 
         * @return A list of all global instructions
         */
        public List<SnesInstruction> getGlobalInstructions() {

            return new ArrayList<>(this.globalInstructions);

        }

        /**
         * Gets all global instructions in the application as an array.
         * 
         * This method returns all global instructions in the application as an array.
         * The array is a copy of the internal list, so modifying the array will not
         * affect the application.
         * 
         * @return An array of all global instructions
         */
        public SnesInstruction[] getGlobalInstructionsArray() {

            return this.globalInstructions.toArray(new SnesInstruction[0]);
            
        }

        /**
         * Sets the list of SNES processes in the application.
         * 
         * This method sets the list of SNES processes in the application to the given 
         * list. The SNES processes are responsible for handling the SNES logic.
         * 
         * @param processes The list of SNES processes to set
         * @return This builder instance for method chaining
         */
        public Builder setSnesProcesses(List<SnesProcess> processes) {

            this.snesProcesses = processes;
            return this;
            
        }

        /**
         * Sets the list of SNES processes in the application.
         * 
         * This method sets the list of SNES processes in the application to the given 
         * array of processes. The SNES processes are responsible for handling the 
         * SNES logic.
         * 
         * @param processes The array of SNES processes to set
         * @return This builder instance for method chaining
         */
        public Builder setSnesProcesses(SnesProcess[] processes) {

            this.snesProcesses = new ArrayList<>(Arrays.asList(processes));
            return this;
            
        }

        /**
         * Adds a SNES process to the application.
         * 
         * @param process The SNES process to add
         * @return This builder instance for method chaining
         */
        public Builder addSnesProcess(SnesProcess process) {

            this.snesProcesses.add(process);
            return this;

        }

        /**
         * Adds multiple SNES processes to the application.
         * 
         * @param processes The SNES processes to add
         * @return This builder instance for method chaining
         */
        public Builder addSnesProcesses(SnesProcess... processes) {

            this.snesProcesses.addAll(Arrays.asList(processes));
            return this;
            
        }

        /**
         * Removes a SNES process from the application.
         * 
         * @param process The SNES process to remove
         * @return This builder instance for method chaining
         */
        public Builder removeSnesProcess(SnesProcess process) {

            this.snesProcesses.remove(process);
            return this;

        }

        /**
         * Removes multiple SNES processes from the application.
         * 
         * @param processes The SNES processes to remove
         * @return This builder instance for method chaining
         */
        public Builder removeSnesProcesses(SnesProcess... processes) {

            this.snesProcesses.removeAll(Arrays.asList(processes));
            return this;
            
        }

        /**
         * Gets all SNES processes in the application.
         * 
         * @return A list of all SNES processes
         */
        public List<SnesProcess> getSnesProcesses() {

            return new ArrayList<>(this.snesProcesses);

        }

        /**
         * Gets all SNES processes in the application as an array.
         * 
         * This method returns all SNES processes in the application as an array.
         * The array is a copy of the internal list, so modifying the array will not
         * affect the application.
         * 
         * @return An array of all SNES processes
         */
        public SnesProcess[] getSnesProcessesArray() {

            return this.snesProcesses.toArray(new SnesProcess[0]);
            
        }

        /**
         * Sets the list of assembly processes in the application.
         * 
         * This method sets the list of assembly processes in the application to the given 
         * list. The assembly processes are responsible for handling the assembly logic.
         * 
         * @param processes The list of assembly processes to set
         * @return This builder instance for method chaining
         */
        public Builder setAsmProcesses(List<AsmProcess> processes) {

            this.asmProcesses = processes;
            return this;
            
        }

        /**
         * Sets the list of assembly processes in the application.
         * 
         * This method sets the list of assembly processes in the application to the given 
         * array. The assembly processes are responsible for handling the assembly logic.
         * 
         * @param processes The array of assembly processes to set
         * @return This builder instance for method chaining
         */
        public Builder setAsmProcesses(AsmProcess[] processes) {

            this.asmProcesses = new ArrayList<>(Arrays.asList(processes));
            return this;
            
        }
        
        /**
         * Adds an assembly process to the application.
         * 
         * @param asmProcess The assembly process to add
         * @return This builder instance for method chaining
         */
        public Builder addAsmProcess(AsmProcess asmProcess) {

            this.asmProcesses.add(asmProcess);
            return this;

        }

        /**
         * Adds multiple assembly processes to the application.
         * 
         * @param asmProcesses The assembly processes to add
         * @return This builder instance for method chaining
         */
        public Builder addAsmProcesses(AsmProcess... asmProcesses) {

            this.asmProcesses.addAll(Arrays.asList(asmProcesses));
            return this;
            
        }

        /**
         * Removes an assembly process from the application.
         * 
         * @param asmProcess The assembly process to remove
         * @return This builder instance for method chaining
         */
        public Builder removeAsmProcess(AsmProcess asmProcess) {

            this.asmProcesses.remove(asmProcess);
            return this;

        }

        /**
         * Removes multiple assembly processes from the application.
         * 
         * @param asmProcesses The assembly processes to remove
         * @return This builder instance for method chaining
         */
        public Builder removeAsmProcesses(AsmProcess... asmProcesses) {

            this.asmProcesses.removeAll(Arrays.asList(asmProcesses));
            return this;
            
        }

        /**
         * Gets all assembly processes in the application.
         * 
         * @return A list of all assembly processes
         */
        public List<AsmProcess> getAsmProcesses() {

            return new ArrayList<>(this.asmProcesses);

        }

        /**
         * Gets all assembly processes in the application as an array.
         * 
         * This method returns all assembly processes in the application as an array.
         * The array is a copy of the internal list, so modifying the array will not
         * affect the application.
         * 
         * @return An array of all assembly processes
         */
        public AsmProcess[] getAsmProcessesArray() {

            return this.asmProcesses.toArray(new AsmProcess[0]);
            
        }

        /**
         * Sets the list of SNES macros in the application.
         * 
         * This method sets the list of SNES macros in the application to the given 
         * list. The SNES macros are responsible for handling the SNES logic.
         * 
         * @param macros The list of SNES macros to set
         * @return This builder instance for method chaining
         */
        public Builder setSnesMacros(List<SnesMacro> macros) {

            this.snesMacros = macros;
            return this;
            
        }

        /**
         * Sets the list of SNES macros in the application.
         * 
         * This method sets the list of SNES macros in the application to the given 
         * array. The SNES macros are responsible for handling the SNES logic.
         * 
         * @param macros The array of SNES macros to set
         * @return This builder instance for method chaining
         */
        public Builder setSnesMacros(SnesMacro[] macros) {

            this.snesMacros = new ArrayList<>(Arrays.asList(macros));
            return this;
            
        }

        /**
         * Adds a SNES macro to the application.
         * 
         * @param macro The SNES macro to add
         * @return This builder instance for method chaining
         */
        public Builder addSnesMacro(SnesMacro macro) {

            this.snesMacros.add(macro);
            return this;

        }

        /**
         * Adds multiple SNES macros to the application.
         * 
         * @param macros The SNES macros to add
         * @return This builder instance for method chaining
         */
        public Builder addSnesMacros(SnesMacro... macros) {

            this.snesMacros.addAll(Arrays.asList(macros));
            return this;
            
        }

        /**
         * Removes a SNES macro from the application.
         * 
         * @param macro The SNES macro to remove
         * @return This builder instance for method chaining
         */
        public Builder removeSnesMacro(SnesMacro macro) {

            this.snesMacros.remove(macro);
            return this;

        }

        /**
         * Removes multiple SNES macros from the application.
         * 
         * @param macros The SNES macros to remove
         * @return This builder instance for method chaining
         */
        public Builder removeSnesMacros(SnesMacro... macros) {

            this.snesMacros.removeAll(Arrays.asList(macros));
            return this;
            
        }

        /**
         * Gets all SNES macros in the application.
         * 
         * @return A list of all SNES macros
         */
        public List<SnesMacro> getSnesMacros() {

            return new ArrayList<>(this.snesMacros);

        }

        /**
         * Gets all SNES macros in the application as an array.
         * 
         * This method returns all SNES macros in the application as an array.
         * The array is a copy of the internal list, so modifying the array will not
         * affect the application.
         * 
         * @return An array of all SNES macros
         */
        public SnesMacro[] getSnesMacrosArray() {

            return this.snesMacros.toArray(new SnesMacro[0]);
            
        }

        /**
         * Sets the list of data files to copy to the destination.
         * 
         * @param dataPaths The list of data file paths to set
         * @return This builder instance for method chaining
         */
        public Builder setDataToCopy(List<String> dataPaths) {

            this.dataToCopy = dataPaths;
            return this;
            
        }

        /**
         * Sets the list of data files to copy to the destination.
         * 
         * @param dataPaths The array of data file paths to set
         * @return This builder instance for method chaining
         */
        public Builder setDataToCopy(String[] dataPaths) {

            this.dataToCopy = new ArrayList<>(Arrays.asList(dataPaths));
            return this;
            
        }

        /**
         * Adds a data file to copy to the destination.
         * 
         * @param dataPath The path to the data file
         * @return This builder instance for method chaining
         */
        public Builder addDataToCopy(String dataPath) {

            this.dataToCopy.add(dataPath);
            return this;

        }

        /**
         * Adds multiple data files to copy to the destination.
         * 
         * @param dataPaths The paths to the data files
         * @return This builder instance for method chaining
         */
        public Builder addDataToCopy(String... dataPaths) {

            this.dataToCopy.addAll(Arrays.asList(dataPaths));
            return this;
            
        }

        /**
         * Removes a data file from the copy list.
         * 
         * @param dataPath The path to the data file to remove
         * @return This builder instance for method chaining
         */
        public Builder removeDataToCopy(String dataPath) {

            this.dataToCopy.remove(dataPath);
            return this;

        }

        /**
         * Removes multiple data files from the copy list.
         * 
         * @param dataPaths The paths to the data files to remove
         * @return This builder instance for method chaining
         */
        public Builder removeDataToCopy(String... dataPaths) {

            this.dataToCopy.removeAll(Arrays.asList(dataPaths));
            return this;
            
        }

        /**
         * Gets all data files to be copied.
         * 
         * @return A list of all data file paths
         */
        public List<String> getDataToCopy() {

            return new ArrayList<>(this.dataToCopy);

        }

        /**
         * Gets all data files to be copied as an array.
         * 
         * This method returns all data files to be copied as an array.
         * The array is a copy of the internal list, so modifying the array will not
         * affect the application.
         * 
         * @return An array of all data file paths
         */
        public String[] getDataToCopyArray() {

            return this.dataToCopy.toArray(new String[0]);
            
        }


        /**
         * Validates the application configuration.
         * 
         * This method checks if the application configuration is valid. If any of the
         * required fields are missing or invalid, an IllegalStateException is thrown.
         * 
         * The required fields are:
         * 
         * <ul>
         * <li>AppData</li>
         * <li>Processor</li>
         * <li>Destination path</li>
         * <li>MemoryMapping</li>
         * <li>SnesProcesses</li>
         * <li>Makefile</li>
         * <li>Boot</li>
         * <li>DataToCopy</li>
         * </ul>
         * 
         * @throws IllegalStateException If any of the required fields are missing or invalid
         */
        private void validate() throws IllegalStateException {

            if (this.appData == null) {

                throw new IllegalStateException(
                    "AppData is required, and it cannot be null"
                );

            }

            if (this.processor == null) {

                throw new IllegalStateException(
                    "Processor is required, and it cannot be null"
                );

            }

            if (this.destination == null) {

                throw new IllegalStateException(
                    "Destination path is required, and it cannot be null"
                );

            }

            if (this.destination.trim().isEmpty()) {

                throw new IllegalStateException(
                    "Destination path is required, and it cannot be empty"
                );

            }

            if (
                !Paths.get(this.destination).getParent().toAbsolutePath()
                    .toFile().exists()
            ) {

                throw new IllegalStateException(
                    "Destination path '" + this.destination + "' does not exist"
                );

            }

            if (this.hdr == null) {

                throw new IllegalStateException(
                    "MemoryMapping is required, and it cannot be null"
                );

            }

            if (this.hdr.assemblyMapping.isEmpty()) {

                throw new IllegalStateException(
                    "MemoryMapping.assemblyMapping is required, and it cannot be empty"
                );

            }

            if (this.snesProcesses == null) {

                throw new IllegalStateException(
                    "SnesProcesses is required, and it cannot be null"
                );

            }

            if (this.snesProcesses.isEmpty()) {

                throw new IllegalStateException(
                    "SnesProcesses is required, and it cannot be empty"
                );

            }

            if (this.makefile == null) {

                throw new IllegalStateException(
                    "Makefile is required, and it cannot be null"
                );

            }

            if (this.boot == null) {

                throw new IllegalStateException(
                    "Boot is required, and it cannot be null"
                );

            }

            if (this.dataToCopy != null) {

                for (int i = 0; i < this.dataToCopy.size(); i++) {

                    if (this.dataToCopy.get(i) == null) {

                        throw new IllegalStateException(
                            "DataToCopy[" + i + "] is required, and it cannot be null"
                        );

                    }

                    if (this.dataToCopy.get(i).trim().isEmpty()) {

                        throw new IllegalStateException(
                            "DataToCopy[" + i + "] is required, and it cannot be empty"
                        );

                    }

                    if (
                        !Paths.get(this.dataToCopy.get(i)).toAbsolutePath()
                            .toFile().exists()
                    ) {

                        throw new IllegalStateException(
                            "DataToCopy[" + i + "] '" + this.dataToCopy.get(i) + 
                            "' does not exist"
                        );

                    }

                }

            }

        }

        /**
         * Builds and returns the App instance.
         * 
         * @return A new App instance with the configured settings
         * @throws IllegalStateException if required fields are not set
         * @throws IOException if an I/O error occurs
         */
        public App build() throws IllegalStateException, IOException {

            try {

                this.validate();

            } catch (IllegalStateException e) {

                throw new IllegalStateException(
                    "Failed to build App, some required fields are missing: " + 
                    e.getMessage()
                );

            }

            return new App(this);

        }

    }

}
