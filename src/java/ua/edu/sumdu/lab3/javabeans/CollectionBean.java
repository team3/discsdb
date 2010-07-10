/*
 * Serhiy Stetsyun & Parhomenko Andrey 
 * Copyright (c) 2010 Sumy State University. Thre are no rights. 
 * 
 * This software is free and anyone can use it (for his own risk ).
 */
package ua.edu.sumdu.lab3.javabeans;

import java.util.Collection;

/**
 * Class for wrapping standart java Collection objects. It helps when you are using old versions JSTL and EL.
 * @version   9.03 8 June
 * @author    Serhiy Stetsyun
 * @author    Parhomenko Andrey
 */
public class CollectionBean {

    private Collection collection;
    
    /**
        * Costructor - wrapper.
        * @param collection for wrapping
    */
    public CollectionBean(Collection collection) {
        this.collection = collection;
    }
    
    /**
    * Gets wrapped java collection
    * @return wrapped collection
    */
    public Collection getCollection(){
        return this.collection;
    }

    /**
    * Gets size of collection by invoking size() method from interface Collection
    * @return collection size
    */
    public int getSize() {
        return this.collection.size();
    }
}
