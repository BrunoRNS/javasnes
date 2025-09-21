package util.types.vars.abstracts.scalar.data;

import util.types.vars.abstracts.scalar.SnesTypeScalar;

/**
 * SnesScalarData is a subclass of SnesTypeScalar that represents scalar data types in SNES programming.
 * 
 * This is an abstract class, so it cannot be instantiated directly, but it serves as a base for other classes.
 * The only class that extends this class is the SnesChar class, which represents the C's "char" type.
 * 
 * Don't confuse SnesChar with the Java's char type which is meant to hold character values, while SnesChar
 * is meant to hold data byte values, which are represented as "char" in C.
 * 
 * You can use SnesChar to hold character values as well, but it is not recommended, as it is not its primary 
 * purpose, and it might lead to confusion.
 * 
 * The meaning of "data" in this context is that it is used to hold raw byte values, which can be used
 * to represent the binary data of the game, such as graphics, sound, level data, palletes and sprites for e.g.
 * 
 * It is commonly used with SnesLoadExtern, which is an interface of extern keyword in C, to load binary data
 * from the assembly data section into the C program. So it is very useful to use with the subclasses of Data
 * class in datatypes package, such as DataPal, DataMap and DataPic.
 * 
 * Here is an example of how the data is loaded in the C source code from pvsneslib examples:

#include <snes.h>

extern char patterns, patterns_end; // Load the binary data from assembly
extern char palette, palette_end; // Another loading
extern char map, map_end; // And another one

int main(void)
{
    // Copy tiles to VRAM
    bgInitTileSet(0, &patterns, &palette, 0, (&patterns_end - &patterns), (&palette_end - &palette), BG_16COLORS, 0x4000);

    // Copy Map to VRAM
    bgInitMapSet(0, &map, (&map_end - &map), SC_32x32, 0x0000);

    // Now Put in 16 color mode and disable other BGs except 1st one
    setMode(BG_MODE1, 0);
    bgSetDisable(1);
    bgSetDisable(2);
    setScreenOn();

    // Wait for nothing :P
    while (1)
    {
        WaitForVBlank();
    }
    return 0;
}

 * That's SnesScalarData which represents the "char" type in C, which is used to hold
 * raw byte data, and is commonly used with extern keyword to load binary data from assembly.
 * 
 * As it is an abstract class, you cannot use it directly, but you can use the SnesChar class
 * which extends this class, and use this one as interface if you want.
 */
public abstract class SnesScalarData extends SnesTypeScalar { }
