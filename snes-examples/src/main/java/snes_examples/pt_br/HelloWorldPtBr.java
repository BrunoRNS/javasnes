package snes_examples.pt_br;

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

public class HelloWorldPtBr {

    final static SnesVoid VOID = new SnesVoid();
    final static SnesChar CHAR = new SnesChar("char");

    public static void main(String[] args) throws Exception {

        App.Builder jogo = new App.Builder();

        MemoryMapping mapaMemoriaRom = new MemoryMapping(configuracao());
        jogo.setMemoryMapping(mapaMemoriaRom);

        AppData dadosRom = new AppData();
        dadosRom.registerData(new Data("tilfont", "pvsneslibfont.pic", false), (byte) 2);
        dadosRom.registerData(new Data("palfont", "pvsneslibfont.pal", false), (byte) 2);

        jogo.setAppData(dadosRom);

        Boot sequenciaDeBoot = new Boot(configuracaoDeBoot());
        jogo.setBoot(sequenciaDeBoot);

        SnesInstruction[] definicoesGlobais = new SnesInstruction[1];
        String[] labelsGlobais = {"tilfont", "palfont"};

        definicoesGlobais[0] = new SnesLoadExtern(labelsGlobais, CHAR);

        jogo.setGlobalInstructions(definicoesGlobais);

        Processor processador = new Processor();

        SnesProcess[] processos = new SnesProcess[1];
        processos[0] = imprimirHelloWorld();

        processador.addProcess(processos[0], null);
        
        jogo
            .setProcessor(processador)
            .setSnesProcesses(processos);
        
        
        Path pastaAtual = Paths.get(
            HelloWorldPtBr.class.getProtectionDomain().getCodeSource().getLocation().toURI()
        ).normalize().toAbsolutePath().getParent();

        Path pastaDados = pastaAtual.resolve("data").resolve("pvsneslibfont.png");
        Path pastaSaida = pastaAtual.resolve("output");

        jogo.addDataToCopy(pastaDados.toString());
        jogo.setDestination(pastaSaida.toString());

        Make makefile = new Make();
        jogo.setMakefile(makefile);

        jogo.build();

    }

    public static Map<String, String> configuracao() {
        
        Map<String, String> config = new HashMap<>();

        //                            123456789012345678901
        config.put("name", "Hello World JavaSnes ");

        return config;

    }

    public static Map<String, Map<String, String[]>> configuracaoDeBoot() {

        Map<String, Map<String, String[]>> boot = new HashMap<>();

        boot.put("betweenSPCVRAMLoadCommands", new LinkedHashMap<>());

        boot.get("betweenSPCVRAMLoadCommands")
            .put("consoleSetTextMapPtr", new String[] { "0x6800" });

        boot.get("betweenSPCVRAMLoadCommands")
            .put("consoleSetTextGfxPtr", new String[] { "0x3000" });

        boot.get("betweenSPCVRAMLoadCommands")
            .put("consoleSetTextOffset", new String[] { "0x0100" });

        boot.get("betweenSPCVRAMLoadCommands")
            .put("consoleInitText", new String[] { 
                "0", "16 * 2", "&tilfont", "&palfont" 
            });
        
        boot.get("betweenSPCVRAMLoadCommands")
            .put("bgSetGfxPtr", new String[] { 
                "0", "0x2000"
            });
        
        boot.get("betweenSPCVRAMLoadCommands")
            .put("bgSetMapPtr", new String[] { 
                "0", "0x6800", "SC_32x32"
            });

        return boot;

    }

    public static SnesProcess imprimirHelloWorld() {

        SnesInstruction[] comandos = new SnesInstruction[1];

        comandos[0] = SnesOutput.consoleDrawText(10, 10, "Hello World do JavaSnes!");

        return new SnesProcess(
            "imprimirHelloWorld",
            (byte) 0, comandos, VOID
        );

    }
    
}
