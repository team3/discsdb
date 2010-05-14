package ua.edu.sumdu.lab3.model.exceptions;

public class ConnectionException extends Exception {
    
    public ConnectionException (String s) {
        super(s);
    }
    
    public ConnectionException (Throwable t) {
        super(t);
    }
}
