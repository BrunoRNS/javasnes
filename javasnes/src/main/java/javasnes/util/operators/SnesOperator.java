package javasnes.util.operators;

import javasnes.instruction.SnesInstruction;

public abstract class SnesOperator extends SnesInstruction {

    /*
     * Define how much variables are required for this operator
     * null = not defined
     * 
     * 0 = no variables required - for e.g. void functions
     * 1 = one variable required - for e.g. !, ~, ++, --, <<, >>
     * 2 = two variables required - for e.g. >, <, +, -, *, /, %, &, |, ^
     * 3 = three variables required - for e.g. a ? b : c
     */
    public Byte requiredVars = null; 
}
