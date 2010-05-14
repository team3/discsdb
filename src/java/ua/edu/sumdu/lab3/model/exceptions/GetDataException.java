package ua.edu.sumdu.lab3.model.exceptions;

public class GetDataException extends Exception {
    
    public GetDataException (String s) {
        super(s);
    }
    
    public GetDataException (Throwable t) {
        super(t);
    }
}
