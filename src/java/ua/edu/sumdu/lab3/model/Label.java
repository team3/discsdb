package ua.edu.sumdu.lab3.model;

public class Label {

    private int id;
    private int major;
    private String name;
    private String info;
    private String logo;
    private String majorName;

    /**
     * Returns label's id.
     * @return label's id.
     */ 
    public int getId() {
        return this.id;
    }

    /**
     * Returns label's major id.
     * @return label's major id.
     */ 
    public int getMajor() {
        return this.major;
    }

    /**
     * Returns label's name.
     * @return label's name.
     */ 
    public String getName() {
        return this.name;
    }
    
    /**
     * Returns label's info.
     * @return label's info.
     */ 
    public String getInfo() {
        return this.info;
    }

    /**
     * Returns label's logo.
     * @return label's logo.
     */ 
    public String getLogo() {
        return this.logo;
    }
    
    /**
     * Returns label's major name.
     * @return label's major name.
     */ 
    public String getMajorName() {
        return this.majorName;
    }

    /**
     * Sets id of the label.
     * @param id label's id.
     */ 
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * Sets id of the major label.
     * @param major id of the major label.
     */ 
    public void setMajor(int major) {
        this.major = major;
    }

    /**
     * Sets name of the label.
     * @param name name of the label.
     */ 
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets label's info.
     * @param info label's info.
     */ 
    public void setInfo(String info) {
        this.info = info;
    }
    
    /**
     * Sets logo of the label.
     * @param loho label's logo.
     */ 
    public void setLogo(String logo) {
        this.logo = logo;
    }
    
    /**
     * Sets name of the major label.
     * @param majorName name of the major label. 
     */ 
    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }
}
