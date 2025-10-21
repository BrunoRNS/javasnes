package snes_examples;

import javasnes.App;
import javasnes.util.types.Processor;
import javasnes.hdr.MemoryMapping;
import javasnes.instruction.SnesInstruction;
import javasnes.util.types.AppData;
import javasnes.data.Data;
import javasnes.output.SnesOutput;
import javasnes.util.types.SnesProcess;
import javasnes.util.types.vars.scalar.data.SnesVoid;
import javasnes.util.types.vars.scalar.data.SnesChar;
import javasnes.util.structures.SnesLoadExtern;
import javasnes.util.types.vars.abstracts.SnesType;
import javasnes.makefile.Make;

import java.util.ArrayList;
import java.util.List;

import javasnes.util.macros.SnesMacro;

public class Main {

    public static final SnesVoid voidReturnType = new SnesVoid();
    public static final SnesType charType = new SnesChar("font");

    public static void main(String[] args) {

        App myGame;

        AppData appData = new AppData();
        Data tilfont = new Data("tilfont", "pvsneslibfont.pic", false);
        Data palfont = new Data("palfont", "pvsneslibfont.pal", false);

        appData.registerData(tilfont, (byte) 1, 0);
        appData.registerData(palfont, (byte) 1, 1);

        List<SnesInstruction> globalInstructions = new ArrayList<>();

        String[] toLoad = {"tilfont", "palfont"};

        globalInstructions.add(new SnesLoadExtern(
            toLoad, charType 
        ));

        Processor processor = new Processor();

        List<SnesInstruction> instructions = new ArrayList<>();

        SnesInstruction showHello = SnesOutput.consoleDrawText(0, 0, "Hello World!");

        instructions.add(showHello);

        SnesProcess mainProcess = new SnesProcess(
            "show_hello", (byte) 0, null, null, instructions, voidReturnType
        );

        processor.addProcess(mainProcess, null);

        List<SnesMacro> snesMacros = new ArrayList<>();

        MemoryMapping.name = "HELLO WORLD JAVASNES "; // 21 characters

        String[] dataToCopy = {"pvsneslibfont.png"};
        Make makefile = new Make();

        String[] newMakefile = {

"ifeq ($(strip $(PVSNESLIB_HOME)),)",
"$(error \"Please create an environment variable PVSNESLIB_HOME by following this guide: https://github.com/alekmaul/pvsneslib/wiki/Installation\")",
"endif",
"# BEFORE including snes_rules :",
"# list in AUDIOFILES all your .it files in the right order. It will build to generate soundbank file",
"AUDIODIR :=\tres",
"export AUDIOFILES :=\t$(foreach dir, $(AUDIODIR), \\",
"\t$(dir)/*.it)",
"# then define the path to generate soundbank data. The name can be different but do not forget to update your include in .c file !",
"export SOUNDBANK := soundbank",
"include ${PVSNESLIB_HOME}/devkitsnes/snes_rules",
".PHONY: bitmaps all",
"#---------------------------------------------------------------------------------",
"# ROMNAME is used in snes_rules file",
"export ROMNAME := JavasnesGame",
"# to build musics, define SMCONVFLAGS with parameters you want",
"SMCONVFLAGS\t:= -s -o $(SOUNDBANK) -V -b 5",
"musics: $(SOUNDBANK).obj",
"# build all including generated bitmaps and ROM",
"all: musics bitmaps $(ROMNAME).sfc",
"cleanGfxLogo:",
"\t@echo clean logo graphics data",
"\t@rm -f res/*.pic res/*.pal",
"clean: cleanBuildRes cleanRom cleanGfx cleanGfxLogo cleanAudio",
"#---------------------------------------------------------------------------------",
"# convert project logo (if present)",
"logo.pic: res/logo.bmp",
"\t@echo convert logo with no tile reduction ... $(notdir $@)",
"\t$(GFXCONV) -s 8 -o 32 -u 16 -p -e 1 -m -R -t bmp -i $<",
"logo : logo.pic;",
"#---------------------------------------------------------------------------------",
"# convert pvsneslib image (support png in project root or in res/)",
"pvsneslib.pic: pvsneslib.png",
"\t@echo convert pvsneslib image ... $(notdir $@)",
"\t$(GFXCONV) -s 8 -o 32 -u 16 -p -e 1 -m -R -t png -i $<",
"res/pvsneslib.pic: res/pvsneslib.png",
"\t@echo convert pvsneslib image in res/ ... $(notdir $@)",
"\t$(GFXCONV) -s 8 -o 32 -u 16 -p -e 1 -m -R -t png -i $<",
"#---------------------------------------------------------------------------------",
"# convert pvsneslib font (support png in project root or in res/)",
"pvsneslibfont.pic: pvsneslibfont.png",
"\t@echo convert font with no tile reduction ... $(notdir $@)",
"\t$(GFXCONV) -s 8 -o 16 -u 16 -p -e 0 -t png -i $<",
"pvsneslibfont.pal: pvsneslibfont.png",
"\t@echo generate palette for font ... $(notdir $@)",
"\t$(GFXCONV) -s 8 -o 16 -u 16 -p -e 0 -t png -i $<",
"res/pvsneslibfont.pic: res/pvsneslibfont.png",
"\t@echo convert font in res/ with no tile reduction ... $(notdir $@)",
"\t$(GFXCONV) -s 8 -o 16 -u 16 -p -e 0 -t png -i $<",
"res/pvsneslibfont.pal: res/pvsneslibfont.png",
"\t@echo generate palette for font in res/ ... $(notdir $@)",
"\t$(GFXCONV) -s 8 -o 16 -u 16 -p -e 0 -t png -i $<",
"#---------------------------------------------------------------------------------",
"# bitmaps target depends on generated picture files used by the project",
"bitmaps: pvsneslib.pic pvsneslibfont.pic",
"\t@echo bitmaps ready",
"# also allow res/ versions to satisfy bitmaps target if pngs are under res/",
"bitmaps: res/pvsneslib.pic res/pvsneslibfont.pic",
"\t@echo bitmaps (res/) ready"

};
        
        makefile.setMakefile(newMakefile);

        myGame = new App(appData, processor, instructions, makefile, globalInstructions, snesMacros, dataToCopy, "/home/brunorns/Downloads/");
        
        
    }
    
}
