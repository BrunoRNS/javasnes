package snes_examples.sprite;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javasnes.App;
import javasnes.boot.Boot;
import javasnes.data.Data;
import javasnes.hdr.MemoryMapping;
import javasnes.input.SnesInput;
import javasnes.instruction.SnesInstruction;
import javasnes.makefile.Make;
import javasnes.util.keywords.KeyWords;
import javasnes.util.logic.SnesSwitch;
import javasnes.util.macros.SnesDefine;
import javasnes.util.operators.assign.OperatorAssign;
import javasnes.util.operators.assign.OperatorObj;
import javasnes.util.operators.logical.OperatorAnd;
import javasnes.util.operators.logical.OperatorGreaterOrEqual;
import javasnes.util.operators.logical.OperatorSmallerOrEqual;
import javasnes.util.operators.ternary.OperatorTernary;
import javasnes.util.structures.SnesEnum;
import javasnes.util.structures.SnesLoadExtern;
import javasnes.util.structures.SnesTypedef;
import javasnes.util.types.AppData;
import javasnes.util.types.Processor;
import javasnes.util.types.SnesProcess;
import javasnes.util.types.vars.array.data.SnesCharArray;
import javasnes.util.types.vars.scalar.data.SnesChar;
import javasnes.util.types.vars.scalar.data.SnesVoid;
import javasnes.util.types.vars.scalar.number.unsigned.SnesU16;
import snes_examples.background.ChangeBackgroundExample;

public class MoveSpriteExample {

    final static SnesChar CHAR = new SnesChar("char");

    public static void main(String[] args) throws Exception {
        
        App.Builder moveSpriteExample = Config.generateApp();

        Map<String, String> memMapConfig = new HashMap<>();
        memMapConfig.put("name", "MoveSpriteExample    ");

        MemoryMapping memMap = Config.generateMemoryMapping(memMapConfig);

        moveSpriteExample.setMemoryMapping(memMap);

        AppData appData = Config.generateAppData();
        
        appData.registerData(new Data("gfxsprite", "sprites.pic", true), (byte) 2);
        appData.registerData(new Data("palsprite", "sprites.pal", true), (byte) 2);

        Boot boot = Config.generateBoot();
        moveSpriteExample.setBoot(boot);

        moveSpriteExample.addSnesMacro(new SnesDefine("FRAMES_PER_ANIMATION 3"));

        SnesInstruction[] globalInstructions = new SnesInstruction[9];

        String[] loadExtern = {
            "gfxsprite", "palsprite", "gfxsprite_end", "palsprite_end"
        };

        globalInstructions[0] = new SnesLoadExtern(loadExtern, CHAR);

        Map<String, String> typedefFields = new LinkedHashMap<>();

        typedefFields.put("s16", "x");
        typedefFields.put("s16", "y");
        typedefFields.put("u16", "gfx_frame");
        typedefFields.put("u16", "anim_frame");
        typedefFields.put("u8", "state");
        typedefFields.put("u8", "flipx");

        SnesTypedef monster = new SnesTypedef(
            "Monster",
            typedefFields
        );

        globalInstructions[1] = monster;

        HashMap<String, String> enumFields1 = new HashMap<>();

        enumFields1.put("W_DOWN", "0");
        enumFields1.put("W_UP", "1");
        enumFields1.put("W_LEFT", "2");
        enumFields1.put("W_RIGHT", "2");

        SnesEnum spriteState = new SnesEnum(
            enumFields1
        );

        globalInstructions[2] = spriteState;

        HashMap<String, String> enumFields2 = new HashMap<>();

        enumFields2.put("SCREEN_TOP", "-16");
        enumFields2.put("SCREEN_BOTTOM", "224");
        enumFields2.put("SCREEN_LEFT", "-16");
        enumFields2.put("SCREEN_RIGHT", "256");

        SnesEnum screenLimits = new SnesEnum(
            enumFields2
        );

        globalInstructions[3] = screenLimits;

        SnesCharArray sprTiles = new SnesCharArray(
            "sprTiles", (short) 9, "{0, 2, 4, 6, 8, 10, 12, 14, 32}"
        );

        globalInstructions[4] = sprTiles;
        globalInstructions[5] = new SnesU16("pad0", "0");
        globalInstructions[6] = new Monster("monster");

        globalInstructions[7] = new OperatorObj("monster", "x", "100");
        globalInstructions[8] = new OperatorObj("monster", "y", "100");

        moveSpriteExample.setGlobalInstructions(globalInstructions);

        Processor processor = new Processor();
        SnesProcess[] processes = new SnesProcess[1];

        processes[0] = updateSprite();
        processor.addProcess(processes[0], null);

        moveSpriteExample.setProcessor(processor);
        moveSpriteExample.setSnesProcesses(processes);

        Make makefile = Config.generateMakefile();
        makefile.setRomName("JavaSnes_MoveSpriteExample");

        Config.addMakeRules(makefile);

    }

    public static SnesProcess updateSprite() {

        SnesInstruction[] commands = new SnesInstruction[5];

        SnesU16 pad0 = new SnesU16("pad0");

        commands[0] = new OperatorAssign(pad0.name, SnesInput.padsCurrent((byte) 0).sourceCode);

        Map<String, List<SnesInstruction>> movements = new HashMap<>();

        List<SnesInstruction> left = new ArrayList<>();
        List<SnesInstruction> right = new ArrayList<>();
        List<SnesInstruction> up = new ArrayList<>();
        List<SnesInstruction> down = new ArrayList<>();

        left.add(new OperatorObj("monster", "x", new OperatorTernary(
            new OperatorGreaterOrEqual("monster.y", "SCREEN_LEFT"), "monster.x - 1", "monster.x"
        ).getSourceCode()));

        left.add(new OperatorObj("monster", "flipx", "1"));
        left.add(new OperatorObj("monster", "state", "W_LEFT"));

        right.add(new OperatorObj("monster", "x", new OperatorTernary(
            new OperatorSmallerOrEqual("monster.x", "SCREEN_RIGHT"), "monster.x + 1", "monster.x"
        ).getSourceCode()));

        right.add(new OperatorObj("monster", "flipx", "0"));
        right.add(new OperatorObj("monster", "state", "W_RIGHT"));

        up.add(new OperatorObj("monster", "y", new OperatorTernary(
            new OperatorGreaterOrEqual("monster.y", "SCREEN_TOP"), "monster.y - 1", "monster.y"
        ).getSourceCode()));

        up.add(new OperatorObj("monster", "flipx", "0"));
        up.add(new OperatorObj("monster", "state", "W_UP"));

        down.add(new OperatorObj("monster", "y", new OperatorTernary(
            new OperatorSmallerOrEqual("monster.y", "SCREEN_BOTTOM"), "monster.y + 1", "monster.y"
        ).getSourceCode()));

        down.add(new OperatorObj("monster", "flipx", "0"));
        down.add(new OperatorObj("monster", "state", "W_DOWN"));


        left.add(KeyWords.snesBreak);
        right.add(KeyWords.snesBreak);
        up.add(KeyWords.snesBreak);
        down.add(KeyWords.snesBreak);

        
        movements.put(SnesInput.keys.KEY_LEFT.sourceCode, left);
        movements.put(SnesInput.keys.KEY_RIGHT.sourceCode, right);
        movements.put(SnesInput.keys.KEY_UP.sourceCode, up);
        movements.put(SnesInput.keys.KEY_DOWN.sourceCode, down);

        SnesSwitch moveSprite = new SnesSwitch(
            pad0, movements
        );

        moveSprite.generateSourceCode();

        commands[1] = moveSprite;

        commands[2] = new OperatorObj("monster", "anim_frame", new OperatorTernary(
            new OperatorAnd(
                "pad0",
                new OperatorGreaterOrEqual("monster.anim_frame", "FRAMES_PER_ANIMATION").getSourceCode()
            ), "0", "pad0 ? (monster.anim_frame + 1) : monster.anim_frame"
        ).getSourceCode());

        
        
    }

    private static interface Config {

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

        public static Map<String, Map<String, String[]>> postLogoCommands() {

            Map<String, Map<String, String[]>> boot = new HashMap<>();

            boot.put("postLogoCommands", new LinkedHashMap<>());

            // Only need to set the screen on, as we don't use the text engine in this example
            boot.get("postLogoCommands")
                    .put("setScreenOn", null);

            return boot;

        }

        public static Make generateMakefile() {

            return new Make();

        }

        public static void addMakeRules(Make makefile) {

            Make.MakeRule bg0 = new Make.MakeRule(
                "map1.map",
                "map1.bmp",
                "$(GFXCONV) -s 8 -o 16 -u 16 -e 0 -p -m -t bmp -i $<"
            );

            Make.MakeRule bg1 = new Make.MakeRule(
                "map2.map",
                "map2.bmp",
                "$(GFXCONV) -s 8 -o 16 -u 16 -e 0 -p -m -t bmp -i $<"
            );

            Make.MakeRule bg2 = new Make.MakeRule(
                "map3.map",
                "map3.bmp",
                "$(GFXCONV) -s 8 -o 16 -u 16 -e 0 -p -m -t bmp -i $<"
            );


            Make.MakeRule bitmaps = new Make.MakeRule(
                "bitmaps",
                "map1.map map1.pic map1.pal map2.map map2.pic map2.pal map3.map map3.pic map3.pal",
                ""
            );

            makefile.addRule(bg0);
            makefile.addRule(bg1);
            makefile.addRule(bg2);

            makefile.addRule(bitmaps);
            makefile.addPhonyTarget("bitmaps");

            makefile.getRule("all").setPrerequisites(
                    makefile.getRule("all").getPrerequisites() + " bitmaps $(ROMNAME).sfc"
            );

        }

        public static void build(App.Builder app) throws Exception {

            Path actualPath = Paths.get(
                    ChangeBackgroundExample.class.getProtectionDomain().getCodeSource().getLocation().toURI()
            ).normalize().toAbsolutePath().getParent();

            Path dataPath = actualPath.resolve("data");
            Path ouptutPath = actualPath.resolve("output");

            Path bg0 = dataPath.resolve("map1.bmp");
            Path bg1 = dataPath.resolve("map2.bmp");
            Path bg2 = dataPath.resolve("map3.bmp");

            cleanBuild(ouptutPath);

            app.addDataToCopy(bg0.toString());
            app.addDataToCopy(bg1.toString());
            app.addDataToCopy(bg2.toString());

            app.setDestination(ouptutPath.toString());

            app.build();

        }

        public static void cleanBuild(Path directory) throws IOException {

            if (Files.exists(directory)) {

                Files.walk(directory)
                        .sorted(Comparator.reverseOrder())
                        .map(Path::toFile)
                        .forEach(File::delete);

                Files.createDirectories(directory);

            }

        }

    }
    
}

class Monster extends SnesVoid {

    {
        this.type = "Monster";
    }

    public Monster(String name) {
        this.name = name;
        this.generateSourceCode();
    }

    public Monster(String name, String defaultValue) {
        this.name = name;
        this.defaultValue = defaultValue;
        this.generateSourceCode();
    }

}