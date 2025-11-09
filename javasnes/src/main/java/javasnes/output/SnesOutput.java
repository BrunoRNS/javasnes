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
     * @param args the arguments to the function, can be null
     * @return a SnesRawInstruction that draws the text string at the given coordinates
     */
    public static final SnesRawInstruction consoleDrawText(int x, int y, String text, String args) {
        return new SnesRawInstruction(
            "consoleDrawText(" + x + ", " + y + ", \"" + text + "\"" + (args == null ? "" : args) + ");"
        );
    }
    
}
