package javasnes.util.types.vars.abstracts.scalar.data;

import javasnes.util.types.vars.abstracts.scalar.SnesTypeScalar;

/**
 * SnesScalarVoid is a subclass of SnesTypeScalar that represents the "void" type in SNES programming.
 * 
 * This is an abstract class, so it cannot be instantiated directly, but it serves as a base for other classes.
 * You can use this class as interface for SnesVoid class, which extends this class.
 * 
 * This class has no utility on its own, but it is useful for type checking and code organization. You can just
 * use the SnesVoid class directly if you want to represent a void type.
 * 
 * Represents a scalar variable of type "void" in SNES programming.
 * 
 * The "void" type is used to indicate that a function does not return a value.
 * It is not used for variable declarations, as variables cannot be of type void.
 * 
 * Example usage:
 * 
 *     SnesScalarVoid voidType = new SnesVoid();
 *     System.out.println(voidType.type); // Outputs: void
 * 
 * Note: Since "void" cannot be used as a variable type, this class is primarily useful
 * for representing function return types in SNES programming contexts.
 */
public abstract class SnesScalarVoid extends SnesTypeScalar { }
