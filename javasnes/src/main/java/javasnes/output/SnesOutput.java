package javasnes.output;

import javasnes.instruction.SnesRawInstruction;

public class SnesOutput {
    
    /**
     * Returns a SnesRawInstruction that draws a text string at the given (x, y)
     * coordinates.
     * 
     * @param x the x coordinate of the text
     * @param y the y coordinate of the text
     * @param text the text string to draw
     * @return a SnesRawInstruction that draws the text string at the given coordinates
     */
    public static final SnesRawInstruction consoleDrawText(int x, int y, String text) {
        return new SnesRawInstruction(
            "consoleDrawText(" + x + ", " + y + ", \"" + text + "\");"
        );
    }

    /**
     * For example: consoleSetTextCol(RGB15(31,0,0), RGB15(0,0,0));
     * 
     * @param color
     * @param bgColor
     * @return
     */
    public static final SnesRawInstruction consoleSetTextCol(String color, String bgColor) {
        return new SnesRawInstruction(
            "consoleSetTextCol(" + color + ", " + bgColor + ");"
        );
    }
    
}
