package ua.edu.sumdu.lab3.model;

public class Label {

    private int id;
    private int major;
    private String name;
    private String info;
    private String logo;
    private String majorName;

    public int getId() {
        return this.id;
    }

    public int getMajor() {
        return this.major;
    }

    public String getName() {
        return this.name;
    }

    public String getInfo() {
        return this.info;
    }

    public String getLogo() {
        return this.logo;
    }
    
    public String getMajorName() {
        return this.majorName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMajor(int major) {
        this.major = major;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
    
    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }
}
