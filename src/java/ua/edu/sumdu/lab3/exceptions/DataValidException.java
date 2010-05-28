package ua.edu.sumdu.lab3.exceptions;

public class DataValidException extends Exception {
    
    public DataValidException (String s) {
        super(s);
    }
    
    public DataValidException (Throwable t) {
        super(t);
    }
}
