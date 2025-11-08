package javasnes.util.keywords;

import javasnes.instruction.SnesRawInstruction;

public class KeyWords {

    public static final SnesRawInstruction snesBreak = new SnesRawInstruction("break;");
    public static final SnesRawInstruction snesContinue = new SnesRawInstruction("continue;");
    public static final SnesRawInstruction snesReturn = new SnesRawInstruction("return;");
    public static final SnesRawInstruction waitvbl = new SnesRawInstruction("WaitForVBlank();");
}
