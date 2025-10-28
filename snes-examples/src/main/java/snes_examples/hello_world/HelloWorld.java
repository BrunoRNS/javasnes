package snes_examples.hello_world;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
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

public class HelloWorld {

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
            HelloWorld.class.getProtectionDomain().getCodeSource().getLocation().toURI()
        ).normalize().toAbsolutePath().getParent();

        Path pastaDados = pastaAtual.resolve("data").resolve("pvsneslibfont.png");
        Path pastaSaida = pastaAtual.resolve("output");
        
        limparDiretorio(pastaSaida);
        
        jogo.addDataToCopy(pastaDados.toString());
        jogo.setDestination(pastaSaida.toString());

        Make makefile = new Make();
        makefile.setRomName("javasnes_helloworld");

        Make.MakeRule fonteDoTexto = new Make.MakeRule(
            "pvsneslibfont.pic",
            "pvsneslibfont.png",
            "$(GFXCONV) -s 8 -o 16 -u 16 -p -e 0 -i $<"
        );

        Make.MakeRule bitmaps = new Make.MakeRule(
            "bitmaps",
            "pvsneslibfont.pic pvsneslibfont.pal",
            ""
        );

        makefile.addRule(fonteDoTexto);
        makefile.addRule(bitmaps);
        makefile.addPhonyTarget("bitmaps");

        // nao teve jeito e coloquei o $(ROMNAME).sfc manualmente no final do all no 
        // makefile, mas em futuras versões poderia ser feito automaticamente
        makefile.getRule("all").setPrerequisites(
            makefile.getRule("all").getPrerequisites() + " bitmaps $(ROMNAME).sfc"
        );

        jogo.setMakefile(makefile);

        jogo.build();

    }

    public static void limparDiretorio(Path diretorio) throws IOException {

        if (Files.exists(diretorio)) {
            
            Files.walk(diretorio)
                .sorted(Comparator.reverseOrder())
                .map(Path::toFile)
                .forEach(File::delete);
        
            Files.createDirectories(diretorio);

        }

    }

    public static Map<String, String> configuracao() {
        
        Map<String, String> config = new HashMap<>();

        //                            123456789012345678901
        config.put("name", "Hello World JavaSnes ");

        return config;

    }

    public static Map<String, Map<String, String[]>> configuracaoDeBoot() {

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

    public static SnesProcess imprimirHelloWorld() {

        SnesInstruction[] comandos = new SnesInstruction[1];

        comandos[0] = SnesOutput.consoleDrawText(5, 10, "Hello World do JavaSnes!");

        return new SnesProcess(
            "imprimirHelloWorld",
            (byte) 0, comandos, VOID
        );

    }
    
}
