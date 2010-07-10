/*
 * Serhiy Stetsyun & Parhomenko Andrey 
 * Copyright (c) 2010 Sumy State University. Thre are no rights. 
 * 
 * This software is free and anyone can use it (for his own risk ).
 */
package ua.edu.sumdu.lab3.javabeans;

import java.io.*;

/**
 * Class for wrapping standart java Throwable objects. It helps when you are using old versions JSTL and EL.
 * @version   9.03 8 June
 * @author    Serhiy Stetsyun
 * @author    Parhomenko Andrey
 */
public class ExceptionBean implements Serializable {

    private Throwable t;
    
    /**
    * Gets throwable
    * @return throwable
    */
    public Throwable getThrowable() { return this.t; }
    
    /**
    * Sets throwable
    * @param Throwable
    */
    public void setThrowable(Throwable t) { this.t = t; }

    /**
    * Overides method from Object.
    * @return string reprsetation of exception
    */
    public String toString() {
        if (this.t == null) {
            return "throwable is null";
        } else {
            return t.getMessage();
        }
    }
}
