package ua.edu.sumdu.lab3.model;

public class Artist {

    private int id;
    private String name;
    private String country;
    private String info;

    /**
     * Returnes artist id.
     * @return artist id.
     */ 
    public int getId() {
        return this.id;
    }

    /**
     * Returnes artist name.
     * @return artist name.
     */ 
    public String getName() {
        return this.name;
    }

    /**
     * Returnes artist country.
     * @return artist country.
     */ 
    public String getCountry(){
        return this.country;
    }

    /**
     * Returnes artist info.
     * @return artist info.
     */ 
    public String getInfo(){
        return this.info;
    }

    /**
     * Sets artist id.
     * @param artist id.
     */ 
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Sets artist name.
     * @param artist name.
     */ 
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets artist country.
     * @param artist country.
     */ 
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Sets artist info.
     * @param artist info.
     */ 
    public void setInfo(String info) {
        this.info = info;
    }
}
