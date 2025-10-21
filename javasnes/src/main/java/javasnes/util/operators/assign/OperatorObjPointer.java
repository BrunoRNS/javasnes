package javasnes.util.operators.assign;

import javasnes.util.operators.SnesOperator;

/**
 * The OperatorObjPointer class represents the assignment of a value to an attribute of an object
 * in the SNES development context. It is used to assign a value to an attribute of an 
 * object pointer.
 */
public class OperatorObjPointer extends SnesOperator {

    /**
     * The name of the object to which the attribute is being assigned.
     */
    public String objectName;

    /**
     * The name of the attribute being assigned.
     */
    public String attributeName;

    /**
     * The value being assigned to the attribute.
     */
    public String value;

    /**
     * Constructor for the OperatorObjPointer class.
     * 
     * @param objectName the name of the object to which the attribute is being assigned
     * @param attributeName the name of the attribute being assigned
     * @param value the value being assigned to the attribute
     */
    public OperatorObjPointer(String objectName, String attributeName, String value) {
    
        this.objectName = objectName;
        this.attributeName = attributeName;
        this.value = value;
    
        this.generateSourceCode();
    
    }

    /**
     * Generates the source code for the object pointer assignment operator.
     * 
     * This method simply calls the getSourceCode method and assigns the result to the 
     * sourceCode field.
     */
    public final void generateSourceCode() {

        this.sourceCode = this.getSourceCode();
        
    }

    /**
     * Generates the source code for the object pointer assignment operator.
     * 
     * @return the source code for the object pointer assignment operator
     */
    @Override
    public final String getSourceCode() {
        
        return (
            this.objectName + "->" + this.attributeName + " = " + this.value + ";"
        );
        
    }
    
}
