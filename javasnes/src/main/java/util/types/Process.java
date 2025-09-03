package util.types;

public class Process {

    /*
     * This refers to the return type of the process.
     * 
     * null if it is not initialized.
     * 
     * 0 if it is a void process.
     * 1 if it returns an unsigned 8-bit integer.
     * 2 if it returns an unsigned 16-bit integer.
     * 3 if it returns an unsigned 32-bit integer.
     * 4 if it returns a signed 8-bit integer.
     * 5 if it returns a signed 16-bit integer.
     * 6 if it returns a signed 32-bit integer.
     * 7 if it returns a BrrSample.
     * 8 if it returns a t_objs.
     * 
     * 11 if it returns an unsigned 8-bit pointer.
     * 12 if it returns an unsigned 16-bit pointer.
     * 13 if it returns an unsigned 32-bit pointer.
     * 14 if it returns a signed 8-bit pointer.
     * 15 if it returns a signed 16-bit pointer.
     * 16 if it returns a signed 32-bit pointer.
     * 17 if it returns a BrrSample pointer.
     * 18 if it returns a t_objs pointer.
     * 
     */
    public Byte type = null;
    
}
