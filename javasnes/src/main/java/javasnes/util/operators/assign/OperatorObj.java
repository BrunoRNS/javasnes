package javasnes.util.operators.assign;

import javasnes.util.operators.SnesOperator;

/**
 * The OperatorObj class represents the assignment of a value to an attribute of an object
 * in the SNES development context. It is used to assign a value to an attribute of an 
 * object.
 */
public class OperatorObj extends SnesOperator {

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
     * The increment signal being used to assign the value to the attribute.
     */
    public Character incrementSignal = null;

    /**
     * Constructs a new OperatorObj object with the specified object name, attribute name,
     * and value.
     * @param objectName the name of the object
     * @param attributeName the name of the attribute
     * @param value the value to be assigned
     */
    public OperatorObj(String objectName, String attributeName, String value) {

        this.objectName = objectName;
        this.attributeName = attributeName;
        this.value = value;

        this.generateSourceCode();

    }

    /**
     * Constructs a new OperatorObj object with the specified object name, attribute name,
     * value, and increment signal.
     * @param objectName the name of the object
     * @param attributeName the name of the attribute
     * @param value the value to be assigned
     * @param incrementSignal the increment signal
     */
    public OperatorObj(
        String objectName, String attributeName, String value, char incrementSignal
    ) {

        this.objectName = objectName;
        this.attributeName = attributeName;
        this.value = value;
        this.incrementSignal = incrementSignal;

        this.generateSourceCode();

    }

    /**
     * Generates the source code for the object assignment operator.
     * 
     * This method simply calls the getSourceCode method and assigns the result to the 
     * sourceCode field.
     */
    public final void generateSourceCode() {

        this.sourceCode = this.getSourceCode();
        
    }

    /**
     * Generates the source code for the object assignment operator.
     * 
     * If the incrementSignal field is not null, the source code will be in the format of
     * "objectName.attributeName incrementSignal = value;". Otherwise, the source code 
     * will be in the format of "objectName.attributeName = value;".
     * 
     * @return the source code for the object assignment operator
     */
    @Override
    public final String getSourceCode() {
        
        if (this.incrementSignal != null) {

            return (
                this.objectName + "." + this.attributeName + " " +
                this.incrementSignal + "= " + this.value + ";"
            );
        
        } else {
            
            return (
                this.objectName + "." + this.attributeName + " = " + this.value + ";"
            );
        
        }

    }
}