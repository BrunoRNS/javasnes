package javasnes.util.structures;

import java.util.Stack;

import javasnes.util.types.vars.abstracts.SnesType;

public class SnesFunctionPrototype extends SnesStructure {

    {
        this.type = 1;
    }

    /**
     * Return type of the function.
     */
    public SnesType returnType;

    /**
     * Parameters of the function.
     */
    public Stack<String> parameters;

    /**
     * Creates a new function prototype.
     * @param name name of the function
     * @param returnType return type of the function
     */
    public SnesFunctionPrototype(String name, SnesType returnType) {
        this.name = name;
        this.parameters = new Stack<>();

        this.returnType = returnType;
        this.generateSourceCode();
    }

    /**
     * Creates a new function prototype.
     * @param name name of the function
     * @param returnType return type of the function
     * @param parameters stack of parameters of the function
     */
    public SnesFunctionPrototype(String name, SnesType returnType, Stack<String> parameters) {
        this.name = name;
        this.parameters = parameters;

        this.returnType = returnType;
        this.generateSourceCode();
    }

    /**
     * Adds a parameter to the function prototype.
     * 
     * <p>Example:</p>
     * 
     * <pre>
     * SnesFunctionPrototype prototype = new SnesFunctionPrototype("myFunction", SnesType.UINT8);
     * prototype.addParameter("u8 a");
     * prototype.addParameter("char* str");
     * </pre>
     * 
     * <p>After calling addParameter() twice, the function prototype will have two parameters: "u8 a" and "char* str".</p>
     * 
     * @param parameter the parameter to add
     */
    public void addParameter(String parameter) {
        /**
         * Example parameter: "u8 a", "char* str"
         */
        this.parameters.push(parameter);
    }

    /**
     * Removes the last parameter added to the function prototype.
     * 
     * <p>Example:</p>
     * 
     * <pre>
     * SnesFunctionPrototype prototype = new SnesFunctionPrototype("myFunction", SnesType.UINT8);
     * prototype.addParameter("u8 a");
     * prototype.addParameter("char* str");
     * prototype.removeParameter();
     * </pre>
     * 
     * <p>After calling removeParameter(), the function prototype will have only one parameter, "u8 a".</p>
     */
    public void removeParameter() {
        this.parameters.pop();
    }

    /**
     * Generates the source code for the function prototype.
     * 
     * <p>This will generate code in the format of:</p>
     * 
     * <pre>
     * return_type function_name(param1, param2, ...);
     * </pre>
     * 
     * <p>Where return_type is the return type of the function,
     * function_name is the name of the function, and param1, param2, ... are
     * the parameters of the function.</p>
     */
    @Override
    public final void generateSourceCode() {

        StringBuilder sb = new StringBuilder();
        
        sb.append(this.returnType.type).append(" ");
        sb.append(this.name).append("(");

        for (int i = 0; i < this.parameters.size(); i++) {

            sb.append(this.parameters.get(i));

            if (i < this.parameters.size() - 1) {
                sb.append(", ");
            }

        }

        sb.append(");");

        this.sourceCode = sb.toString();

    }
    
}
