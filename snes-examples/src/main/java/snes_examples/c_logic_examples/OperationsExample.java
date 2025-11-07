package snes_examples.c_logic_examples;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javasnes.App; // App construct the application
import javasnes.boot.Boot; // Boot defines the boot sequence of the application
import javasnes.data.Data; // The Data to be added in AppData class
import javasnes.hdr.MemoryMapping; // The ROM memory mapping and definitions
import javasnes.instruction.SnesInstruction; // An abstract class for all instructions in SNES
import javasnes.makefile.Make; // Generates the makefile, which will build the C code in ROM
import javasnes.output.SnesOutput;
import javasnes.util.operators.SnesOperator; // Output definitions, in this example, only consoleDrawText
import javasnes.util.operators.assign.OperatorAssign;
import javasnes.util.operators.math.OperatorAdd; // Load extern definitions, from AppData for example
import javasnes.util.structures.SnesLoadExtern; // The data container, will generate data.asm which collects the data from files
import javasnes.util.types.AppData; // The processor method, which will execute 60 times per second, and run other processes
import javasnes.util.types.Processor; // The process class represents the method in C/SNES development
import javasnes.util.types.SnesProcess; // To create Char variables/methods or use its type def
import javasnes.util.types.vars.scalar.data.SnesChar; // To create void variables/methods or use its type def
import javasnes.util.types.vars.scalar.data.SnesVoid;
import javasnes.util.types.vars.scalar.number.unsigned.SnesU8;

public class OperationsExample {

    final static SnesVoid VOID = new SnesVoid();
    final static SnesChar CHAR = new SnesChar("char");
    final static SnesU8 INT = new SnesU8("int");
    
    public static void main(String[] args) throws Exception {

        // App Builder(constroi o aplicativo/rom)
        App.Builder operationsExample = Config.generateApp();

        // Memory Map
        HashMap<String, String> memMapConfig = new HashMap<>();
        memMapConfig.put("name", "TempOperationsExample");
        MemoryMapping memMap = Config.generateMemoryMapping(memMapConfig);

        operationsExample.setMemoryMapping(memMap);

        // Importar as Fontes das Letras
        AppData appData = Config.generateAppData();
        appData.registerData(new Data("tilfont", "pvsneslibfont.pic", false), (byte) 2);
        appData.registerData(new Data("palfont", "pvsneslibfont.pal", false), (byte) 2);

        operationsExample.setAppData(appData);

        // Iniciar o App
        Boot boot = Config.generateBoot();

        operationsExample.setBoot(boot);

        // Ler as Fontes do App Data
        SnesInstruction[] globalDefs = new SnesInstruction[1];
        String[] loadExtern = {"tilfont", "palfont"};
        globalDefs[0] = new SnesLoadExtern(loadExtern, CHAR);

        operationsExample.setGlobalInstructions(globalDefs);

        // Processos

        Processor processor = new Processor();
        SnesProcess[] processes = new SnesProcess[1];

        processes[0] = soma();
        processor.addProcess(processes[0], null);

        operationsExample.setSnesProcesses(processes);
        operationsExample.setProcessor(processor);

        //Makefile

        Make makefile = Config.generateMakefile();
        makefile.setRomName("JavaSnes_OperationsExample");
        Config.addMakeRules(makefile);

        operationsExample.setMakefile(makefile);

        //Buildar o App
        Config.build(operationsExample);

    }
    
    public static SnesProcess soma() {

        // Criar vetor de comandos
        SnesInstruction[] comandos = new SnesInstruction[5];

        //Criar variaveis
        SnesU8 minhaVar1 = new SnesU8("variavel1", "4"); // criar variavel, tipo nome = new tipo("nome da variavel", "ValorDaVariavel(nãoObrigatorio)")
        SnesU8 minhaVar2 = new SnesU8("variavel2", "4");
        SnesU8 resultadoSoma = new SnesU8("resultadoDaSoma");

        comandos[0] = minhaVar1;
        comandos[1] = minhaVar2;
        comandos[2] = resultadoSoma;

        SnesOperator somar = new OperatorAdd(minhaVar1, minhaVar2);
        SnesOperator atribuir = new OperatorAssign(resultadoSoma.name, somar.getSourceCode());

        comandos[3] = atribuir;

        comandos[4] = SnesOutput.consoleDrawText(3, 10, "%d + %d = %d", ", variavel1, variavel2, resultadoDaSoma");


        SnesProcess processo = new SnesProcess("meuMetodo", (byte) 0, comandos, VOID);  // nome do metodo/processo, tipo de retorno (0 - nada), lista de comandos, retorno (contante VOID/vazio/void)
        return processo;

        /**
         * Saida:
         * 
         * u8 minhaVar = 15;
         * 
         * minhaVar = (minhaVar + 15);
         * 
         * ----------------------------
         * 
         * SnesU8 minhaVar
         * 
         * Operator assign
         *         |
         *         |
         * "minhaVar.name" = "OperatorAdd.getSourceCode()"
         * 
         * OperatorAdd (var + outraVar)
         * pega o .name da var
         * se minhaVar.nome = "minhaVar"
         * se outraVar.nome = "15"
         * 
         * (minhaVar + 15)
         */

    }



}


class Config {

    public static App.Builder generateApp() {
        return new App.Builder();
    }

    public static MemoryMapping generateMemoryMapping(Map<String, String> config) {

        MemoryMapping memMap = new MemoryMapping(config);
        return memMap;

    }

    public static AppData generateAppData() {
        return new AppData();
    }

    public static Boot generateBoot() {

        Boot boot = new Boot(postLogoCommands());
        return boot;
        
    }

    private static Map<String, Map<String, String[]>> postLogoCommands() {

        Map<String, Map<String, String[]>> boot = new HashMap<>();

        boot.put("postLogoCommands", new LinkedHashMap<>());

        boot.get("postLogoCommands")
            .put("setScreenOff", null);

        boot.get("postLogoCommands")
            .put("consoleSetTextMapPtr", new String[] { "0x6800" });

        boot.get("postLogoCommands")
            .put("consoleSetTextGfxPtr", new String[] { "0x3000" });

        boot.get("postLogoCommands")
            .put("consoleSetTextOffset", new String[] { "0x0100" });

        boot.get("postLogoCommands")
            .put("consoleInitText", new String[] { 
                "0", "16 * 2", "&tilfont", "&palfont" 
            });
        
        boot.get("postLogoCommands")
            .put("bgSetGfxPtr", new String[] { 
                "0", "0x2000"
            });
        
        boot.get("postLogoCommands")
            .put("bgSetMapPtr", new String[] { 
                "0", "0x6800", "SC_32x32"
            });

        boot.get("postLogoCommands")
            .put("setScreenOn", null);

        return boot;

    }

    public static Make generateMakefile() {

        return new Make();

    }

    public static void addMakeRules(Make makefile) {

        Make.MakeRule textFont = new Make.MakeRule(
            "pvsneslibfont.pic",
            "pvsneslibfont.png",
            "$(GFXCONV) -s 8 -o 16 -u 16 -p -e 0 -i $<"
        );

        Make.MakeRule bitmaps = new Make.MakeRule(
            "bitmaps",
            "pvsneslibfont.pic pvsneslibfont.pal",
            ""
        );

        makefile.addRule(textFont);
        makefile.addRule(bitmaps);
        makefile.addPhonyTarget("bitmaps");

        makefile.getRule("all").setPrerequisites(
            makefile.getRule("all").getPrerequisites() + " bitmaps $(ROMNAME).sfc"
        );

    }

    public static void build(App.Builder app) throws Exception {

        Path actualPath = Paths.get(
            OperationsExample.class.getProtectionDomain().getCodeSource().getLocation().toURI()
        ).normalize().toAbsolutePath().getParent();

        Path dataPath = actualPath.resolve("data").resolve("pvsneslibfont.png");
        Path ouptutPath = actualPath.resolve("output");
        
        cleanBuild(ouptutPath);
        
        app.addDataToCopy(dataPath.toString());
        app.setDestination(ouptutPath.toString());

        app.build();

    }

    private static void cleanBuild(Path directory) throws IOException {

        if (Files.exists(directory)) {
            
            Files.walk(directory)
                .sorted(Comparator.reverseOrder())
                .map(Path::toFile)
                .forEach(File::delete);
        
            Files.createDirectories(directory);

        }

    }

}