package ua.edu.sumdu.lab3.model.exceptions;

public class EditDataException extends Exception {
    
    public EditDataException (String s) {
        super(s);
    }
    
    public EditDataException (Throwable t) {
        super(t);
    }
}
