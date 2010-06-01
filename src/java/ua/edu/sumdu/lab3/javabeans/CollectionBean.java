package ua.edu.sumdu.lab3.javabeans;

import java.util.Collection;

public class CollectionBean {

    private Collection collection;
    
    public CollectionBean(Collection collection) {
        this.collection = collection;
    }
    
    public Collection getCollection(){
        return this.collection;
    }
    
    public int getSize() {
        return this.collection.size();
    }
}
