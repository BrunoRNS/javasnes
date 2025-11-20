package javasnes.output;

import javasnes.instruction.SnesInstruction;
import javasnes.instruction.SnesRawInstruction;
import javasnes.util.keywords.KeyWords;
import javasnes.util.types.vars.abstracts.scalar.SnesTypeScalar;

public final class SnesOutput {
    
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
    public static final SnesRawInstruction consoleDrawText(
        int x, int y, String text, String args
    ) {
        return new SnesRawInstruction(
            "consoleDrawText(" + x + ", " + y + ", \"" + text + "\"" +
            (args == null ? "" : args) + ");"
        );
    }

    /**
     * Returns a SnesRawInstruction that sets the background color of a given palette entry.
     * 
     * @param palleteEntry the palette entry to set the background color for
     * @param bgColor the background color to set
     * @return a SnesRawInstruction that sets the background color of the given palette entry
     */
    public static final SnesRawInstruction setPaletteColor(
        String palleteEntry, SnesTypeScalar bgColor
    ) {
        return new SnesRawInstruction(
            "setPaletteColor(" + palleteEntry + ", " + bgColor.name + ");"
        );
    }

    /**
     * Returns a SnesRawInstruction that turns the screen on.
     * This function should be used to enable the screen for the duration of the program.
     * It should be called at the beginning of the program.
     * 
     * @return a SnesRawInstruction that turns the screen on
     */
    public static final SnesRawInstruction setScreenOn() {
        return new SnesRawInstruction("setScreenOn();");
    }

    /**
     * Returns a SnesRawInstruction that turns the screen off.
     *
     * @return a SnesRawInstruction that turns the screen off
     */
    public static final SnesRawInstruction setScreenOff() {
        return new SnesRawInstruction("setScreenOff();");
    }

    /**
     * Returns a SnesInstruction that waits for a vertical blank.
     * This function should be used to synchronize the program with the vertical blank
     * of the screen. This is useful for games and animations that need to be
     * rendered at a constant frame rate.
     * 
     * @return a SnesInstruction that waits for a vertical blank
     */
    public static final SnesInstruction waitForVblank() {
        return KeyWords.waitvbl;
    }

    /**
     * Returns a SnesRawInstruction that disables a background layer.
     * 
     * The background layer is specified by its index, which is an integer
     * between 0 and 3. A value of 0 corresponds to the first background layer,
     * a value of 1 corresponds to the second background layer, and so on.
     * 
     * Disabling a background layer prevents it from being rendered to the screen.
     * This is useful for games and animations that need to have a dynamic
     * background that changes over time.
     * 
     * @param bgIndex the index of the background layer to disable
     * @return a SnesRawInstruction that disables the background layer
     */
    public static final SnesRawInstruction bgSetDisable(int bgIndex) {
        return new SnesRawInstruction("bgSetDisable(" + bgIndex + ");");
    }

    /**
     * Returns a SnesRawInstruction that sets the graphics pointer for the background layer 
     * with the given index.
     * 
     * The graphics pointer is a string that represents the memory address of the graphics 
     * data.
     * 
     * @param bgIndex the index of the background layer to set the graphics pointer for
     * @param gfxPtr the memory address of the graphics data
     * @return a SnesRawInstruction that sets the graphics pointer for the background layer
     */
    public static final SnesRawInstruction bgSetGfxPtr(int bgIndex, String gfxPtr) {
        return new SnesRawInstruction("bgSetGfxPtr(" + bgIndex + ", " + gfxPtr + ");");
    }

    /**
     * Returns a SnesRawInstruction that sets the map pointer for the background layer with 
     * the given index.
     * 
     * The map pointer is a string that represents the memory address of the map data.
     * 
     * @param bgIndex the index of the background layer to set the map pointer for
     * @param mapPtr the memory address of the map data
     * @return a SnesRawInstruction that sets the map pointer for the background layer
     */
    public static final SnesRawInstruction bgSetMapPtr(int bgIndex, String mapPtr) {
        return new SnesRawInstruction("bgSetMapPtr(" + bgIndex + ", " + mapPtr + ");");
    }

    /**
     * Initializes the text console with the given palette number, palette size, tile font 
     * address, and palette font address.
     * 
     * The text console is a utility that allows you to draw text strings to the screen.
     * It is useful for debugging and for displaying text in games and animations.
     * 
     * The text console uses the given palette number and palette size to determine which 
     * colors to use when drawing text.
     * It uses the given tile font address and palette font address to determine how to render 
     * the text.
     * 
     * @param palnum the palette number to use
     * @param pals the size of the palette
     * @param tilfontAdress the memory address of the tile font data
     * @param palfontAdress the memory address of the palette font data
     * @return a SnesRawInstruction that initializes the text console
     */
    public static final SnesRawInstruction consoleInitText(
        int palnum, int palsize, String tilfontAdress, String palfontAdress
    ) {
        return new SnesRawInstruction(
            "consoleInitText(" + palnum + ", " + palsize + ", " + tilfontAdress + ", " + palfontAdress + ");"
        );
    }

    /**
     * Returns a SnesRawInstruction that sets the text map pointer for the text console.
     * 
     * The text map pointer is a string that represents the memory address of the text map data.
     * 
     * @param offsetfont the memory address of the text map data
     * @return a SnesRawInstruction that sets the text map pointer for the text console
     */
    public static final SnesRawInstruction consoleSetTextMapPtr(String offsetfont) {
        return new SnesRawInstruction("consoleSetTextMapPtr(" + offsetfont + ");");
    }

    /**
     * Returns a SnesRawInstruction that sets the text graphics pointer for the text console.
     * 
     * The text graphics pointer is a string that represents the memory address of the text 
     * graphics data.
     * 
     * @param vramfont the memory address of the text graphics data
     * @return a SnesRawInstruction that sets the text graphics pointer for the text console
     */
    public static final SnesRawInstruction consoleSetTextGfxPtr(String vramfont) {
        return new SnesRawInstruction("consoleSetTextGfxPtr(" + vramfont + ");");
    }

    /**
     * Returns a SnesRawInstruction that sets the text offset for the text console.
     * 
     * The text offset is a string that represents the memory address of the text offset data.
     * 
     * @param offsetfont the memory address of the text offset data
     * @return a SnesRawInstruction that sets the text offset for the text console
     */
    public static final SnesRawInstruction consoleSetTextOffset(String offsetfont) {
        return new SnesRawInstruction("consoleSetTextOffset(" + offsetfont + ");");
    }

    /**
     * Returns a SnesRawInstruction that sets the mode for the SNES to the given mode with the 
     * given size.
     * 
     * @param mode the mode to set, can be "BG_MODE0", "BG_MODE1", "BG_MODE2", "BG_MODE3", 
     * "BG_MODE4", "BG_MODE5", "TEXT_MODE"
     * @param size the size of the mode, can be 0, 1, 2, 3, 4, 5, or 8
     * @return a SnesRawInstruction that sets the mode for the SNES to the given mode with 
     * the given size
     */
    public static final SnesRawInstruction setMode(String mode, int size) {
        return new SnesRawInstruction("setMode(" + mode + ", " + size + ");");
    }

    /**
     * Returns a SnesRawInstruction that initializes a background layer with the given
     * map pointer, map size, size mode, and address.
     * 
     * This method initializes a background layer with the given map pointer, map size,
     * size mode, and address. The map pointer is the memory address of the map data,
     * the map size is the size of the map data in bytes, the size mode is the size of
     * the map data in tiles, and the address is the memory address to store the map
     * data at.
     * 
     * @param bgNumber the number of the background layer to initialize
     * @param mapPtr the memory address of the map data
     * @param mapSize the size of the map data in bytes
     * @param sizeMode the size of the map data in tiles
     * @param address the memory address to store the map data at
     * @return a SnesRawInstruction that initializes a background layer with the given
     * map pointer, map size, size mode, and address
     */
    public static final SnesRawInstruction bgInitMapSet(
        int bgNumber, String mapPtr, String mapSize, String sizeMode, String address
    ) {
        return new SnesRawInstruction(
            "bgInitMapSet(" + bgNumber + ", " + mapPtr + ", " + mapSize + ", " + sizeMode + ", " + address + ");"
        );
    }

    /**
     * Returns a SnesRawInstruction that initializes a background layer with the given
     * tile pointer, palette pointer, palette entry, color mode, and address.
     * 
     * This method initializes a background layer with the given tile pointer, palette
     * pointer, palette entry, color mode, and address. The tile pointer is the memory
     * address of the tile data, the palette pointer is the memory address of the palette
     * data, the palette entry is the entry in the palette to use, the color mode is the color
     * mode to use, and the address is the memory address to store the tile data at.
     * 
     * @param bgNumber the number of the background layer to initialize
     * @param tilePtr the memory address of the tile data
     * @param palettePtr the memory address of the palette data
     * @param paletteEntry the palette entry to use
     * @param tileSize the size of the tile data
     * @param paletteSize the size of the palette data
     * @param colorMode the color mode to use
     * @param address the memory address to store the tile data at
     * @return a SnesRawInstruction that initializes a background layer with the given
     * tile pointer, palette pointer, palette entry, color mode, and address
     */
    public static final SnesRawInstruction bgInitTileSet(
        int bgNumber, String tilePtr, String palettePtr, String paletteEntry, String tileSize,
        String paletteSize, String colorMode, String address
    ) {
        return new SnesRawInstruction(
            "bgInitTileSet(" + bgNumber + ", " + tilePtr + ", " + 
            palettePtr + ", " + paletteEntry + ", " + tileSize + ", " + 
            paletteSize + ", " + colorMode + ", " + address + ");"
        );
    }

    /**
     * Returns a SnesRawInstruction that initializes a background layer with the given
     * tile pointer, palette pointer, palette entry, color mode, and address, but with
     * LZ77 compression.
     * 
     * This method is similar to bgInitTileSet, but it uses LZ77 compression to store the
     * tile data in a compressed format.
     * 
     * @param bgNumber the number of the background layer to initialize
     * @param tilePtr the memory address of the tile data
     * @param palettePtr the memory address of the palette data
     * @param paletteEntry the palette entry to use
     * @param paletteSize the size of the palette
     * @param colorMode the color mode to use
     * @param address the memory address to store the tile data at
     * @return a SnesRawInstruction that initializes a background layer with the given
     * tile pointer, palette pointer, palette entry, color mode, and address, but with
     * LZ77 compression
     */
    public static final SnesRawInstruction bgInitTileSetLz(
        int bgNumber, String tilePtr, String palettePtr, String paletteEntry,
        String paletteSize, String colorMode, String address
    ) {
        return new SnesRawInstruction(
            "bgInitTileSetLz(" + bgNumber + ", " + tilePtr 
            + ", " + palettePtr + ", " + paletteEntry + ", " +
            paletteSize + ", " + colorMode + ", " + address + ");"
        );
    }

    /**
     * Returns a SnesRawInstruction that clears the Video RAM (VRAM).
     * 
     * This method clears the contents of the Video RAM (VRAM), which is used by the
     * SNES to store graphics data.
     * 
     * @return a SnesRawInstruction that clears the Video RAM (VRAM)
     */
    public static final SnesRawInstruction dmaClearVram() {
        return new SnesRawInstruction("dmaClearVram();");
    }

    /**
     * Initializes the OAM (Object Attribute Memory) by setting all the OAM
     * values to their default values.
     * 
     * The OAM is a region of memory that stores information about the sprites
     * currently being displayed on the screen. This information includes the
     * sprite's position, size, priority, and other attributes.
     * 
     * @return a SnesRawInstruction that initializes the OAM
     */
    public static final SnesRawInstruction oamInit() {
        return new SnesRawInstruction("oamInit();");
    }

    /**
     * Returns a SnesRawInstruction that updates the OAM (Object Attribute Memory).
     * 
     * This method updates the contents of the OAM, which is used by the SNES to store
     * information about the sprites currently being displayed on the screen.
     * 
     * @return a SnesRawInstruction that updates the OAM
     */
    public static final SnesRawInstruction oamUpdate() {
        return new SnesRawInstruction("oamUpdate();");
    }

    /**
     * Returns a SnesRawInstruction that sets the OAM (Object Attribute Memory) values
     * for the given sprite ID.
     * 
     * The OAM is a region of memory that stores information about the sprites
     * currently being displayed on the screen. This information includes the
     * sprite's position, size, priority, and other attributes.
     * 
     * @param id the ID of the sprite to set the OAM values for
     * @param xspr the horizontal sprite offset
     * @param yspr the vertical sprite offset
     * @param priority the priority of the sprite
     * @param hflip whether to flip the sprite horizontally
     * @param vflip whether to flip the sprite vertically
     * @param gfxoffset the graphics offset of the sprite
     * @param palleteoffset the palette offset of the sprite
     * @return a SnesRawInstruction that sets the OAM values for the given sprite ID
     */
    public static final SnesRawInstruction oamSet(
        int id, String xspr, String yspr, String priority, String hflip, String vflip,
        String gfxoffset, String palleteoffset
    ) {
        return new SnesRawInstruction(
            "oamSet(" + id + ", " + xspr + ", " + yspr + ", " + priority + ", " + 
            hflip + ", " + vflip + ", " + gfxoffset + ", " + palleteoffset + ");"
        );
    }

    /**
     * Sets the horizontal and vertical sprite offset values for the sprite with the given ID.
     * 
     * The horizontal and vertical sprite offset values determine the position of the sprite
     * on the screen.
     * 
     * @param id the ID of the sprite to set the horizontal and vertical sprite offset 
     * values for
     * @param xspr the horizontal sprite offset
     * @param yspr the vertical sprite offset
     * @return a SnesRawInstruction that sets the horizontal and vertical sprite offset 
     * values for the given sprite ID
     */
    public static final SnesRawInstruction oamSetXY(
        int id, String xspr, String yspr
    ) {
        return new SnesRawInstruction(
            "oamSetXY(" + id + ", " + xspr + ", " + yspr + ");"
        );
    }

    /**
     * Returns a SnesRawInstruction that gets the horizontal sprite offset for the given 
     * sprite ID.
     * 
     * The horizontal sprite offset determines the x position of the sprite on the screen.
     * 
     * @param id the ID of the sprite to get the horizontal sprite offset for
     * @return a SnesRawInstruction that gets the horizontal sprite offset for the given 
     * sprite ID
     */
    public static final SnesRawInstruction oamGetX(int id) {
        return new SnesRawInstruction("oamGetX(" + id + ");");
    }

    /**
     * Returns a SnesRawInstruction that gets the vertical sprite offset for the given 
     * sprite ID.
     * 
     * The vertical sprite offset determines the y position of the sprite on the screen.
     * 
     * @param id the ID of the sprite to get the vertical sprite offset for
     * @return a SnesRawInstruction that gets the vertical sprite offset for the given 
     * sprite ID
     */
    public static final SnesRawInstruction oamGetY(int id) {
        return new SnesRawInstruction("oamGetY(" + id + ");");
    }

    /**
     * Sets the OAM (Object Attribute Memory) values for the given sprite ID.
     * 
     * The OAM is a region of memory that stores information about the sprites
     * currently being displayed on the screen. This information includes the
     * sprite's position, size, priority, and other attributes.
     * 
     * @param id the ID of the sprite to set the OAM values for
     * @param size the size of the sprite
     * @param hide whether to hide the sprite
     * @return a SnesRawInstruction that sets the OAM values for the given sprite ID
     */
    public static final SnesRawInstruction oamSetEx(
        int id, String size, String hide
    ) {
        return new SnesRawInstruction(
            "oamSetEx(" + id + ", " + size + ", " + hide + ");"
        );
    }

    /**
     * Sets the visibility of the sprite with the given ID.
     * 
     * @param id the ID of the sprite to set the visibility for
     * @param hidden whether the sprite is hidden
     * @return a SnesRawInstruction that sets the visibility of the sprite
     */
    public static final SnesRawInstruction oamSetVisible(
        int id, String hidden
    ) {
        return new SnesRawInstruction(
            "oamSetVisible(" + id + ", " + hidden + ");"
        );
    }

    /**
     * Sets the horizontal and vertical flip state of the sprite with the given ID.
     * 
     * @param id the ID of the sprite to set the flip state for
     * @param hflip whether to flip the sprite horizontally
     * @param vflip whether to flip the sprite vertically
     * @return a SnesRawInstruction that sets the flip state for the given sprite ID
     */
    public static final SnesRawInstruction oamFlip(
        int id, String hflip, String vflip
    ) {
        return new SnesRawInstruction(
            "oamFlip(" + id + ", " + hflip + ", " + vflip + ");"
        );
    }

    /**
     * Sets the graphics offset of the sprite with the given ID.
     * 
     * The graphics offset is used to determine which region of the sprite's graphics
     * data to display on the screen. A positive offset moves the sprite to the right
     * and down, while a negative offset moves the sprite to the left and up.
     * 
     * @param id the ID of the sprite to set the graphics offset for
     * @param offset the graphics offset to set
     * @return a SnesRawInstruction that sets the graphics offset for the given sprite ID
     */
    public static final SnesRawInstruction oamSetGfxOffset(int id, String offset) {
        return new SnesRawInstruction(
            "oamSetGfxOffset(" + id + ", " + offset + ");"
        );
    }

    /**
     * Initializes the graphics portion of the OAM (Object Attribute Memory) with the given
     * tile source, tile size, palette source, palette size, tile palette number, address, and
     * OAM size.
     * 
     * This method initializes the graphics portion of the OAM with the given tile source,
     * tile size, palette source, palette size, tile palette number, address, and OAM size.
     * 
     * The tile source is the memory address of the tile data, the tile size is the size of the
     * tile data in bytes, the palette source is the memory address of the palette data, the
     * palette size is the size of the palette data in bytes, the tile palette number is the 
     * palette
     * number to use for the tile data, the address is the memory address to store the tile 
     * data at, and the OAM size is the size of the OAM in bytes.
     * 
     * @param tileSource the memory address of the tile data
     * @param tileSize the size of the tile data in bytes
     * @param paletteSource the memory address of the palette data
     * @param paletteSize the size of the palette data in bytes
     * @param tilePaletteNumber the palette number to use for the tile data
     * @param address the memory address to store the tile data at
     * @param oamSize the size of the OAM in bytes
     * @return a SnesRawInstruction that initializes the graphics portion of the OAM
     */
    public static final SnesRawInstruction oamInitGfxSet(
        String tileSource, String tileSize, String paletteSource, String paletteSize,
        String tilePaletteNumber, String address, String oamSize
    ) {
        return new SnesRawInstruction(
            "oamInitGfxSet(" + tileSource + ", " + tileSize + ", " + paletteSource + ", " + 
            paletteSize + ", " + tilePaletteNumber + ", " + address + ", " + oamSize + ");"
        );
    }

    /**
     * Object states in the SNES development context.
     * 
     * This class contains constants representing different object states in the SNES
     * development context.
     */
    public static final class ObjState {
        public static final String OBJ_SMALL = "OBJ_SMALL";
        public static final String OBJ_LARGE = "OBJ_LARGE";
        public static final String OBJ_HIDE = "OBJ_HIDE";
        public static final String OBJ_SHOW = "OBJ_SHOW";
    }

    /**
     * Object sizes in the SNES development context.
     * 
     * This class contains constants representing different object sizes in the SNES
     * development context.
     */
    public static final class ObjSize {
        public static final String OBJ_SIZE8_L16 = "OBJ_SIZE8_L16";
        public static final String OBJ_SIZE8_L32 = "OBJ_SIZE8_L32";
        public static final String OBJ_SIZE8_L64 = "OBJ_SIZE8_L64";
        public static final String OBJ_SIZE16_L32 = "OBJ_SIZE16_L32";
        public static final String OBJ_SIZE16_L64 = "OBJ_SIZE16_L64";
        public static final String OBJ_SIZE32_L64 = "OBJ_SIZE32_L64";
    }

    /**
     * Updates the map data stored in memory.
     * 
     * This method updates the map data stored in memory by updating the map data with
     * the changes made to the map since the last update.
     * 
     * @return a SnesRawInstruction that updates the map data stored in memory
     */
    public static final SnesRawInstruction mapUpdate() {
        return new SnesRawInstruction("mapUpdate();");
    }

    /**
     * Loads a map into the SNES memory.
     * 
     * This method loads a map into the SNES memory, using the map data stored at the given
     * memory address, the tile set definition data stored at the given memory address,
     * and the tile set attribute data stored at the given memory address.
     * 
     * @param mapPtr the memory address of the map data
     * @param tileSetDefPtr the memory address of the tile set definition data
     * @param tileSetAttPtr the memory address of the tile set attribute data
     * @return a SnesRawInstruction that loads a map into the SNES memory
     */
    public static final SnesRawInstruction mapLoad(
        String mapPtr, String tileSetDefPtr, String tileSetAttPtr
    ) {
        return new SnesRawInstruction(
            "mapLoad(" + mapPtr + ", " + tileSetDefPtr + ", " + tileSetAttPtr + ");"
        );
    }

    /**
     * Updates the camera position in the map.
     * 
     * This method updates the camera position in the map, using the given x and y coordinates.
     * 
     * @param xpos the x coordinate of the camera position
     * @param ypos the y coordinate of the camera position
     * @return a SnesRawInstruction that updates the camera position in the map
     */
    public static final SnesRawInstruction mapUpdateCamera(String xpos, String ypos) {
        return new SnesRawInstruction(
            "mapUpdateCamera(" + xpos + ", " + ypos + ");"
        );
    }

    /**
     * Waits for a vertical blank in the map.
     * 
     * This method waits for a vertical blank in the map, which is useful for synchronizing
     * the program with the vertical blank of the screen. This is useful for games and 
     * animations that need to be rendered at a constant frame rate.
     * 
     * @return a SnesRawInstruction that waits for a vertical blank in the map
     */
    public static final SnesRawInstruction mapVblank() {
        return new SnesRawInstruction("mapVblank();");
    }

    public static final SnesRawInstruction bgSetScroll(int id, String x, String y) {
        return new SnesRawInstruction(
            "bgSetScroll(" + id + ", " + x + ", " + y + ");"
        );
    }
    
}
