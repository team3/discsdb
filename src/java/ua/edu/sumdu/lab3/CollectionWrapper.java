package ua.edu.sumdu.lab3;

import java.util.Collection;

public class CollectionWrapper {

    private Collection collection;
    
    public CollectionWrapper(Collection collection) {
        this.collection = collection;
    }
    
    public Collection getCollection(){
        return this.collection;
    }
    
    public int getSize() {
        return this.collection.size();
    }
}
