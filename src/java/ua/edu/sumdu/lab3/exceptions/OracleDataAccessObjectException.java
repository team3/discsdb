/*
 * Serhiy Stetsyun & Parhomenko Andrey 
 * Copyright (c) 2010 Sumy State University. Thre are no rights. 
 * 
 * This software is free and anyone can use it (for his own risk ).
 */
package ua.edu.sumdu.lab3.exceptions;

/**
 * Main exception class. All exceptions are wrapped by it on hight levels.
 * @version   9.03 8 June
 * @author    Serhiy Stetsyun
 * @author    Parhomenko Andrey
 */
public class OracleDataAccessObjectException extends Exception {
    
    public OracleDataAccessObjectException (String s) {
        super(s);
    }
    
    public OracleDataAccessObjectException (Throwable t) {
        super(t);
    }
}
