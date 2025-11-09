package javasnes.util.logic;

import java.util.List;
import java.util.Map;

import javasnes.instruction.SnesInstruction;
import javasnes.util.types.vars.abstracts.SnesType;

/**
 * Represents a switch statement in the SNES logic.
 * 
 * This class holds the variable being switched on, the cases with their
 * corresponding instructions, and the default case instructions.
 * 
 */
public class SnesSwitch extends SnesInstruction {

    /**
     * The variable being switched on.
     * The name of this variable will be used in the switch statement.
     * 
     * Be careful to ensure that tis variable is in scope where the switch
     * statement is used.
     */
    public SnesType switchVar;

    /**
     * The Map of case values to their corresponding List of instructions.
     * 
     * The keys of the Map are the case values, and the values are the List
     * of instructions to execute in that case.
     * 
     * For example, a case for value "1" with two instructions would be
     * represented as:
     * 
     * "1" -> [instruction1, instruction2]
     */
    public Map<String, List<SnesInstruction>> cases;

    /**
     * The List of instructions to execute in the default case.
     */
    public List<SnesInstruction> defaultCase;

    /**
     * Constructor for SnesSwitch.
     * 
     * This constructor initializes the switch variable and the cases.
     * If you want to add a default case, use the other constructor or
     * use the addDefaultCase method.
     * 
     * @param switchVar
     * @param cases
     */
    public SnesSwitch(
        SnesType switchVar,
        Map<String, List<SnesInstruction>> cases
    ) {
        this.switchVar = switchVar;
        this.cases = cases;
    }

    /**
     * Constructor for SnesSwitch with default case.
     * 
     * This constructor initializes the switch variable, the cases, and
     * the default case.
     * 
     * @param switchVar
     * @param cases
     * @param defaultCase
     */
    public SnesSwitch(
        SnesType switchVar,
        Map<String, List<SnesInstruction>> cases,
        List<SnesInstruction> defaultCase
    ) {
        this.switchVar = switchVar;
        this.cases = cases;
        this.defaultCase = defaultCase;
    }

    /**
     * Adds a case to the switch.
     * 
     * This method adds a new case to the switch with the given value and
     * instructions. The case value must be a string representation of an
     * integer (e.g. "1", "42", etc.).
     * 
     * @param caseValue the value of the case
     * @param instructions the instructions to execute when the case is matched
     */
    public void addCase(
        String caseValue,
        List<SnesInstruction> instructions
    ) {
        this.cases.put(caseValue, instructions);
    }

    /**
     * Adds a default case to the switch.
     * 
     * This method adds a default case to the switch with the given instructions.
     * The default case will be executed when no other case matches the switch
     * variable value.
     * 
     * @param instructions the instructions to execute when the default case is matched
     */
    public void addDefaultCase(List<SnesInstruction> instructions) {
        this.defaultCase = instructions;
    }

    /**
     * Removes a case from the switch.
     * 
     * This method removes the case with the given value from the switch.
     * If the case does not exist, nothing is done.
     * 
     * @param caseValue the value of the case to remove
     */
    public void removeCase(String caseValue) {
        this.cases.remove(caseValue);
    }

    /**
     * Removes the default case from the switch.
     * 
     * This method sets the default case to null, effectively removing it from the switch.
     * If the default case was already null, nothing is done.
     */
    public void removeDefaultCase() {
        this.defaultCase = null;
    }

    /**
     * Generates the source code representation of the switch statement.
     * 
     * <p>This will generate code in the format of:</p>
     * 
     * <pre>
     * switch (variable name) {
     *    case value1:
     *        instruction1;
     *        instruction2;
     *        ...
     *        break; // if missing, a warning is printed
     *    case value2:
     *        instruction1;
     *        instruction2;
     *        ...
     *        break; // if missing, a warning is printed
     *    ...
     * 
     *    default:
     *        instruction1;
     *        instruction2;
     *        ...
     *        break; // if missing, a warning is printed
     * }
     * </pre>
     * 
     * This method generates the source code for the switch statement,
     * including the cases and default case. It checks for missing break
     * statements in each case and the default case, and prints a warning if
     * any are found.
     * 
     * @throws IllegalStateException if the cases are null or empty
     */
    public void generateSourceCode() throws IllegalStateException {

        StringBuilder sb = new StringBuilder();
        sb.append("switch (").append(this.switchVar.name).append(") {\n");

        if (this.cases == null) {

            throw new IllegalStateException("Cases are required");

        }

        if (this.cases.isEmpty()) {

            throw new IllegalStateException("At least one case is required");

        }

        for (Map.Entry<String, List<SnesInstruction>> entry : this.cases.entrySet()) {

            sb.append("\tcase ").append(entry.getKey()).append(":\n");

            for (SnesInstruction instruction : entry.getValue()) {

                sb.append("\t\t").append(instruction.sourceCode).append("\n");

            }

            if (
                !entry.getValue().get(
                    entry.getValue().size() - 1
                ).sourceCode.endsWith("break;")
            ) {
                System.err.println(
                    "Warning: Missing break statement in case " + entry.getKey()
                );
            }

        }

        if (this.defaultCase != null) {

            sb.append("\tdefault:\n");

            for (SnesInstruction instruction : this.defaultCase) {

                sb.append("\t\t").append(instruction.sourceCode).append("\n");

            }

            if (
                !defaultCase.get(
                    defaultCase.size() - 1
                ).sourceCode.endsWith("break;")
            ) {
                System.err.println(
                    "Warning: Missing break statement in default case"
                );
            }

        }

        sb.append("}");
        this.sourceCode = sb.toString();

    }
    
}
