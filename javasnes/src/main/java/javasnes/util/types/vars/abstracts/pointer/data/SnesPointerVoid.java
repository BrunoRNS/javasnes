package javasnes.util.types.vars.abstracts.pointer.data;

import javasnes.util.types.vars.abstracts.pointer.SnesTypePointer;

/**
 * Represents an abstract SNES pointer type with no specific data type.
 * 
 * This class serves as a base for pointer types that do not point to a concrete value,
 * providing a foundation for more specialized pointer implementations within the SNES
 * memory model.
 * 
 * @see SnesTypePointer
 */
public abstract class SnesPointerVoid extends SnesTypePointer {

    public SnesPointerVoid() {}

}
