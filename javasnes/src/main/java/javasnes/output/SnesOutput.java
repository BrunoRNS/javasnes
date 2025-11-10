package javasnes.output;

import javasnes.instruction.SnesRawInstruction;
import javasnes.util.types.vars.abstracts.scalar.SnesTypeScalar;

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

    /**
     * Returns a SnesRawInstruction that sets the background color of a given palette entry.
     * 
     * @param palleteEntry the palette entry to set the background color for
     * @param bgColor the background color to set
     * @return a SnesRawInstruction that sets the background color of the given palette entry
     */
    public static final SnesRawInstruction setPalleteColor(
        String palleteEntry, SnesTypeScalar bgColor
    ) {
        return new SnesRawInstruction(
            "setPalleteColor(" + palleteEntry + ", " + bgColor.name + ");"
        );
    }
    
}
