package ua.edu.sumdu.lab3.model.exceptions;

public class OracleDataAccessObjectException extends Exception {
    
    public OracleDataAccessObjectException (String s) {
        super(s);
    }
    
    public OracleDataAccessObjectException (Throwable t) {
        super(t);
    }
}
