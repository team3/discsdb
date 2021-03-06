package ua.edu.sumdu.lab3.javabeans;

import java.io.*;

public class ExceptionBean implements Serializable {

    private Throwable t;

    public Throwable getThrowable() { return this.t; }
    public void setThrowable(Throwable t) { this.t = t; }

    public String toString() {
        if (this.t == null) {
            return "throwable is null";
        } else {
            return t.getMessage();
        }
    }
}
