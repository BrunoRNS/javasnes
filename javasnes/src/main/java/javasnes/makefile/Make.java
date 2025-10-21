package javasnes.makefile;

/**
 * Represents a Makefile. The Makefile class holds the contents of the Makefile which will
 * be generated.
 */
public class Make {

    /**
     * The contents of the Makefile
     */
    public String[] makefile = {

"ifeq ($(strip $(PVSNESLIB_HOME)),)",
"$(error \"Please create an environment variable PVSNESLIB_HOME by following this guide: https://github.com/alekmaul/pvsneslib/wiki/Installation\")",
"endif",
"# BEFORE including snes_rules :",
"# list in AUDIOFILES all your .it files in the right order. It will build to generate soundbank file",
"AUDIODIR :=	res",
"export AUDIOFILES :=	$(foreach dir, $(AUDIODIR), \\",
"\t$(dir)/*.it)",
"# then define the path to generate soundbank data. The name can be different but do not forget to update your include in .c file !",
"export SOUNDBANK := soundbank",
"include ${PVSNESLIB_HOME}/devkitsnes/snes_rules",
".PHONY: bitmaps all",
"#---------------------------------------------------------------------------------",
"# ROMNAME is used in snes_rules file",
"export ROMNAME := JavasnesGame",
"# to build musics, define SMCONVFLAGS with parameters you want",
"SMCONVFLAGS	:= -s -o $(SOUNDBANK) -V -b 5",
"musics: $(SOUNDBANK).obj",
"all: musics logo $(ROMNAME).sfc",
"cleanGfxLogo:",
"\t@echo clean logo graphics data",
"\t@rm -f res/*.pic res/*.pal",
"clean: cleanBuildRes cleanRom cleanGfx cleanGfxLogo cleanAudio",
"#---------------------------------------------------------------------------------",
"logo.pic: res/logo.bmp",
"\t@echo convert font with no tile reduction ... $(notdir $@)",
"\t$(GFXCONV) -s 8 -o 32 -u 16 -p -e 1 -m -R -t bmp -i $<",
"logo : logo.pic;"

};

    /**
     * Returns the contents of the Makefile as a string array.
     * 
     * @return the contents of the Makefile
    */
    public String[] getMakefile() {
        return this.makefile;
    }

    /**
     * Sets the contents of the Makefile.
     * 
     * @param makefile the contents of the Makefile
     */
    public void setMakefile(String[] makefile) {
        this.makefile = makefile;
    }
    
}
