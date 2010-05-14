package ua.edu.sumdu.lab3.model.exceptions;

public class ExecuteQueryException extends Exception {
    
    public ExecuteQueryException (String s) {
        super(s);
    }
    
    public ExecuteQueryException (Throwable t) {
        super(t);
    }
}
