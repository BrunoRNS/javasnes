package datatypes;

public class DataIT extends Data {

    /** 
     * isHigherThan32K is used to determine if the IT data file is larger than 32KB.
     * This is important for handling the data correctly in the SNES bank.
     * If the size is greater than 32KB, it may require special handling or processing
     * to ensure that it fits within the SNES memory constraints.
     * This field is set to null by default, indicating that it has not been determined yet
     * whether the IT data file is larger than 32KB.
     * It can be set to true or false based on the size of the IT data file
     * when the data is loaded or processed. 
    */
    public Boolean isHigherThan32K = null;


    
}
