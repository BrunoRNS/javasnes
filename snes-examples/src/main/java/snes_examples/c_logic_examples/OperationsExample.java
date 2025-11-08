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
import javasnes.util.operators.binary.OperatorBinSHL;
import javasnes.util.operators.binary.OperatorBinSHR;
import javasnes.util.operators.math.OperatorAdd; // Load extern definitions, from AppData for example
import javasnes.util.operators.math.OperatorDivision;
import javasnes.util.operators.math.OperatorMod;
import javasnes.util.operators.math.OperatorPlus;
import javasnes.util.operators.math.OperatorSub;
import javasnes.util.operators.unitary.OperatorCast;
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
    
    public static void main(String[] args) throws Exception {

        App.Builder operationsExample = Config.generateApp();

        HashMap<String, String> memMapConfig = new HashMap<>();
        memMapConfig.put("name", "OperationsExample    ");
        MemoryMapping memMap = Config.generateMemoryMapping(memMapConfig);

        operationsExample.setMemoryMapping(memMap);

        AppData appData = Config.generateAppData();
        appData.registerData(new Data("tilfont", "pvsneslibfont.pic", false), (byte) 2);
        appData.registerData(new Data("palfont", "pvsneslibfont.pal", false), (byte) 2);

        operationsExample.setAppData(appData);

        Boot boot = Config.generateBoot();

        operationsExample.setBoot(boot);

        SnesInstruction[] globalDefs = new SnesInstruction[1];
        String[] loadExtern = {"tilfont", "palfont"};
        globalDefs[0] = new SnesLoadExtern(loadExtern, CHAR);

        operationsExample.setGlobalInstructions(globalDefs);

        Processor processor = new Processor();
        SnesProcess[] processes = new SnesProcess[6];

        processes[0] = addTwoNumbers();
        processor.addProcess(processes[0], null);

        processes[1] = subTwoNumbers();
        processor.addProcess(processes[1], null);

        processes[2] = plusTwoNumbers();
        processor.addProcess(processes[2], null);

        processes[3] = divideTwoNumbers();
        processor.addProcess(processes[3], null);

        processes[4] = modTwoNumbers();
        processor.addProcess(processes[4], null);

        processes[5] = shiftTwoNumbers();
        processor.addProcess(processes[5], null);

        operationsExample.setSnesProcesses(processes);
        operationsExample.setProcessor(processor);

        Make makefile = Config.generateMakefile();
        makefile.setRomName("JavaSnes_OperationsExample");
        Config.addMakeRules(makefile);

        operationsExample.setMakefile(makefile);

        Config.build(operationsExample);

    }
    
    public static SnesProcess addTwoNumbers() {

        // void type can be used as a placeholder for unregistered types, like int
        final SnesVoid INT = new SnesVoid();
        INT.type = "int";

        SnesInstruction[] comands = new SnesInstruction[5];

        SnesU8 num1 = new SnesU8("num1", "4");
        SnesU8 num2 = new SnesU8("num2", "5");
        SnesU8 result = new SnesU8("result");

        comands[0] = num1;
        comands[1] = num2;
        comands[2] = result;

        SnesOperator add = new OperatorAdd(num1, num2);
        SnesOperator assign = new OperatorAssign(result.name, add.getSourceCode());

        SnesOperator castNum1 = new OperatorCast(INT, num1);
        SnesOperator castNum2 = new OperatorCast(INT, num2);
        SnesOperator castResult = new OperatorCast(INT, result);

        comands[3] = assign;

        comands[4] = SnesOutput.consoleDrawText(
            3, 1, "%d + %d = %d", ", " + 
            castNum1.getSourceCode() +
            ", " +
            castNum2.getSourceCode() +
            ", " +
            castResult.getSourceCode()
        );

        SnesProcess process = new SnesProcess("addTwoNumbers", comands, VOID);
        return process;

    }

    public static SnesProcess subTwoNumbers() {

        // void type can be used as a placeholder for unregistered types, like int
        final SnesVoid INT = new SnesVoid();
        INT.type = "int";

        SnesInstruction[] comands = new SnesInstruction[5];

        SnesU8 num1 = new SnesU8("num1", "6");
        SnesU8 num2 = new SnesU8("num2", "3");
        SnesU8 result = new SnesU8("result");

        comands[0] = num1;
        comands[1] = num2;
        comands[2] = result;

        SnesOperator sub = new OperatorSub(num1, num2);
        SnesOperator assign = new OperatorAssign(result.name, sub.getSourceCode());

        SnesOperator castNum1 = new OperatorCast(INT, num1);
        SnesOperator castNum2 = new OperatorCast(INT, num2);
        SnesOperator castResult = new OperatorCast(INT, result);

        comands[3] = assign;

        comands[4] = SnesOutput.consoleDrawText(
            3, 4, "%d - %d = %d", ", " + 
            castNum1.getSourceCode() +
            ", " +
            castNum2.getSourceCode() +
            ", " +
            castResult.getSourceCode()
        );

        SnesProcess process = new SnesProcess("subTwoNumbers", comands, VOID);
        return process;

    }

    public static SnesProcess plusTwoNumbers() {

        // void type can be used as a placeholder for unregistered types, like int
        final SnesVoid INT = new SnesVoid();
        INT.type = "int";

        SnesInstruction[] comands = new SnesInstruction[5];

        SnesU8 num1 = new SnesU8("num1", "4");
        SnesU8 num2 = new SnesU8("num2", "5");
        SnesU8 result = new SnesU8("result");

        comands[0] = num1;
        comands[1] = num2;
        comands[2] = result;

        SnesOperator plus = new OperatorPlus(num1, num2);
        SnesOperator assign = new OperatorAssign(result.name, plus.getSourceCode());

        SnesOperator castNum1 = new OperatorCast(INT, num1);
        SnesOperator castNum2 = new OperatorCast(INT, num2);
        SnesOperator castResult = new OperatorCast(INT, result);

        comands[3] = assign;

        comands[4] = SnesOutput.consoleDrawText(
            3, 7, "%d * %d = %d", ", " + 
            castNum1.getSourceCode() +
            ", " +
            castNum2.getSourceCode() +
            ", " +
            castResult.getSourceCode()
        );

        SnesProcess process = new SnesProcess("plusTwoNumbers", comands, VOID);
        return process;

    }

    public static SnesProcess divideTwoNumbers() {

        // void type can be used as a placeholder for unregistered types, like int
        final SnesVoid INT = new SnesVoid();
        INT.type = "int";

        SnesInstruction[] comands = new SnesInstruction[5];

        SnesU8 num1 = new SnesU8("num1", "8");
        SnesU8 num2 = new SnesU8("num2", "2");
        SnesU8 result = new SnesU8("result");

        comands[0] = num1;
        comands[1] = num2;
        comands[2] = result;

        SnesOperator division = new OperatorDivision(num1, num2);
        SnesOperator assign = new OperatorAssign(result.name, division.getSourceCode());

        SnesOperator castNum1 = new OperatorCast(INT, num1);
        SnesOperator castNum2 = new OperatorCast(INT, num2);
        SnesOperator castResult = new OperatorCast(INT, result);

        comands[3] = assign;

        comands[4] = SnesOutput.consoleDrawText(
            3, 10, "%d / %d = %d", ", " + 
            castNum1.getSourceCode() +
            ", " +
            castNum2.getSourceCode() +
            ", " +
            castResult.getSourceCode()
        );

        SnesProcess process = new SnesProcess("divideTwoNumbers", comands, VOID);
        return process;

    }

    public static SnesProcess modTwoNumbers() {

        // void type can be used as a placeholder for unregistered types, like int
        final SnesVoid INT = new SnesVoid();
        INT.type = "int";

        SnesInstruction[] comands = new SnesInstruction[5];

        SnesU8 num1 = new SnesU8("num1", "26");
        SnesU8 num2 = new SnesU8("num2", "5");
        SnesU8 result = new SnesU8("result");

        comands[0] = num1;
        comands[1] = num2;
        comands[2] = result;

        SnesOperator mod = new OperatorMod(num1, num2);
        SnesOperator assign = new OperatorAssign(result.name, mod.getSourceCode());

        SnesOperator castNum1 = new OperatorCast(INT, num1);
        SnesOperator castNum2 = new OperatorCast(INT, num2);
        SnesOperator castResult = new OperatorCast(INT, result);

        comands[3] = assign;

        comands[4] = SnesOutput.consoleDrawText(
            3, 13, "%d %% %d = %d", ", " + 
            castNum1.getSourceCode() +
            ", " +
            castNum2.getSourceCode() +
            ", " +
            castResult.getSourceCode()
        );

        SnesProcess process = new SnesProcess("modTwoNumbers", comands, VOID);
        return process;

    }

    public static SnesProcess shiftTwoNumbers() {

        // void type can be used as a placeholder for unregistered types, like int
        final SnesVoid INT = new SnesVoid();
        INT.type = "int";

        SnesInstruction[] comands = new SnesInstruction[8];

        SnesU8 num1 = new SnesU8("num1", "4");
        SnesU8 num2 = new SnesU8("num2", "16");
        SnesU8 result1 = new SnesU8("result1");
        SnesU8 result2 = new SnesU8("result2");

        comands[0] = num1;
        comands[1] = num2;
        comands[2] = result1;
        comands[3] = result2;

        SnesOperator shl = new OperatorBinSHL(num1, 3);
        SnesOperator assign1 = new OperatorAssign(result1.name, shl.getSourceCode());

        SnesOperator shr = new OperatorBinSHR(num2, 2);
        SnesOperator assign2 = new OperatorAssign(result2.name, shr.getSourceCode());

        comands[4] = assign1;
        comands[5] = assign2;

        SnesOperator castNum1 = new OperatorCast(INT, num1);
        SnesOperator castNum2 = new OperatorCast(INT, num2);
        SnesOperator castResult1 = new OperatorCast(INT, result1);
        SnesOperator castResult2 = new OperatorCast(INT, result2);

        comands[6] = SnesOutput.consoleDrawText(
            3, 16, "%d << 3 = %d", ", " + 
            castNum1.getSourceCode() +
            ", " +
            castResult1.getSourceCode()
        );

        comands[7] = SnesOutput.consoleDrawText(
            3, 19, "%d >> 2 = %d", ", " + 
            castNum2.getSourceCode() +
            ", " +
            castResult2.getSourceCode()
        );

        SnesProcess process = new SnesProcess("shiftTwoNumbers", comands, VOID);
        return process;

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
