package javasnes.output;

import javasnes.instruction.SnesRawInstruction;

public class SnesOutput {
    
    public static final SnesRawInstruction consoleDrawText(int x, int y, String text) {
        return new SnesRawInstruction(
            "consoleDrawText(" + x + ", " + y + ", \"" + text + "\");"
        );
    }
    
}
