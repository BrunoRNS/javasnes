package javasnes.util.types.vars.abstracts.scalar.sound;

import javasnes.util.types.vars.abstracts.scalar.SnesTypeScalar;

/**
 * SnesScalarSound - Abstract class representing a scalar sound type in SNES.
 * This class serves as a base for sound-related scalar types in SNES programming, as well as
 * SnesBrrSample class, which extends this class.
 * 
 * This class has no utility on its own, but it is useful for type checking and code organization.
 * You can just use the SnesBrrSample class directly if you want to represent a sound sample.
 * But if you want to use it as an interface, you can use this class, for example:
 * 
 *   SnesScalarSound soundVariable = new SnesBrrSample("mySound");
 *
 * The "brrsamples" type in SNES programming is used to represent sound samples in BRR format.
 * BRR (Bit Rate Reduction) is a compressed audio format used by the SNES to store sound samples efficiently.
 * The SNES sound hardware can decode and play BRR samples directly, making it a popular choice for sound 
 * effects and music in SNES games, and is widely supported by lots of SNES development libraries, 
 * including pvsneslib.
 * 
 * BRRs use a 9/32 compression when working with 16 bit samples and 9/16 compression when working 
 * with 8 bit samples. Calculate the size of your BRRs and think before putting a hundred of them in SNES' 
 * Audio RAM which has a 60 KB limit, and using about 50 KB it can crash your samples.
 * 
 * For sounds longer then 5 seconds I already highly recommend you to use Impulse Tracker to control your
 * instruments sequence. Be very careful when working with sounds and compression and test them in an emulator
 * before taking any risk with the original hardware. Bad configured sounds in most cases wont silent the 
 * channel, it will case MALFUNCTION.
 * 
 * You can look into the pvsneslib documentation which explains it in a detailed way. If it doesn't solve your
 * issue, ask in pvsneslib's discord community, where they will support you solving the issue.
 * 
 * Below, there's an example from pvsneslib of using brrsample scalar variable in pure C code.
 *
#include <snes.h>

extern char soundbrr, soundbrrend;

brrsamples tadasound; // Usage of a brrsample type variable - it will hold the sound data

extern char snesfont, snespal;

unsigned short bgcolor = 128;
u8 keyapressed = 0;

int main(void)
{

    // Initialize sound engine (take some time)
    spcBoot();

    // Initialize text console with our font
    consoleSetTextMapPtr(0x6800);
    consoleSetTextGfxPtr(0x3000);
    consoleSetTextOffset(0x0100);
    consoleInitText(0, 16 * 2, &snesfont, &snespal);

    // allocate around 10K of sound ram (39 256-byte blocks)
    spcAllocateSoundRegion(39);

    // Init background
    bgSetGfxPtr(0, 0x2000);
    bgSetMapPtr(0, 0x6800, SC_32x32);

    // Now Put in 16 color mode and disable Bgs except current
    setMode(BG_MODE1, 0);
    bgSetDisable(1);
    bgSetDisable(2);

    // Draw a wonderful text :P
    consoleDrawText(5, 10, "Press A to play effect !");

    // Wait for nothing :P
    setScreenOn();

    // Load effect
    spcSetSoundEntry(15, 15, 4, &soundbrrend - &soundbrr, &soundbrr, &tadasound);

    // Wait for nothing :D !
    while (1)
    {
        // Test key a (without repeating sound if still pressed)
        if (padsCurrent(0) & KEY_A)
        {
            if (keyapressed == 0)
            {
                keyapressed = 1;
                // Play effect
                spcPlaySound(0);

                // change background color
                bgcolor += 16;
                setPaletteColor(0x00, bgcolor);
            }
        }
        else
            keyapressed = 0;

        // Update music / sfx stream and wait vbl
        spcProcess();
        WaitForVBlank();
    }
    return 0;
}
 * The example above would use the following structure in java of brrsample
 * 
 *    SnesScalarSound mySound = new SnesBrrSample("tadasound"); // Here you could have not used the interface
 *    mySound.global = true;
 *    
 *    mySound.generateSourceCode();
 *    
 *    System.out.println(mySound.sourceCode); // outputs: brrsamples tadasound;
 * 
 */
public abstract class SnesScalarSound extends SnesTypeScalar {

    public SnesScalarSound() {}

}
