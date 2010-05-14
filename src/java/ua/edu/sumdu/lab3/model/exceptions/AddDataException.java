package ua.edu.sumdu.lab3.model.exceptions;

public class AddDataException extends Exception {
    
    public AddDataException (String s) {
        super(s);
    }
    
    public AddDataException (Throwable t) {
        super(t);
    }
}
