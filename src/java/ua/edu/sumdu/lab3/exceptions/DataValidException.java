/*
 * Serhiy Stetsyun & Parhomenko Andrey 
 * Copyright (c) 2010 Sumy State University. Thre are no rights. 
 * 
 * This software is free and anyone can use it (for his own risk ).
 */
 
package ua.edu.sumdu.lab3.exceptions;

/**
 * Exception class than connected with input data validation errors
 * @version   9.03 8 June
 * @author    Serhiy Stetsyun
 * @author    Parhomenko Andrey
 */
public class DataValidException extends Exception {
    
    public DataValidException (String s) {
        super(s);
    }
    
    public DataValidException (Throwable t) {
        super(t);
    }
}
