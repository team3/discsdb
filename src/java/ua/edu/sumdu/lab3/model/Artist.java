package ua.edu.sumdu.lab3.model;

public class Artist {

    private int id;
    private String name;
    private String country;
    private String info;

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getCountry(){
        return this.country;
    }

    public String getInfo(){
        return this.info;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}