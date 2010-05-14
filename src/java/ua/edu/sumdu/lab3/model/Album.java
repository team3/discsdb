package ua.edu.sumdu.lab3.model;

import java.util.Date;

public class Album {

    private int id = -1;
    private String name;
    private String type;
    private Date release;
    private String genre;
    private String cover;
    private String artistName;
    private String labelName;
    private String review;
    private int artist;
    private int label;

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getType() {
        return this.type;
    }

    public Date getRelease() {
        return this.release;
    }

    public String getGenre() {
        return this.genre;
    }

    public String getCover() {
        return this.cover;
    }

    public int getArtist() {
        return this.artist;
    }

    public int getLabel() {
        return this.label;
    }

    public String getArtistName() {
        return this.artistName;
    }

    public String getLabelName() {
        return this.labelName;
    }

    public String getReview() {
        return this.review;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setRelease(Date release) {
        this.release = release;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public void setArtist(int artist) {
        this.artist = artist;
    }

    public void setLabel(int label) {
        this.label = label;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }
    
    public void setReview(String review) {
        this.review = review;
    }
    
    public String toString() {
        StringBuffer str = new StringBuffer();
        
        str.append("ID: ").append(this.id).append(" ").
            append("Name: ").append(this.name).append(" ").
            append("Type: ").append(this.type).append(" ").
            append("Release: ").append(this.release).append(" ").
            append("Genre: ").append(this.genre).append(" ").
            append("Cover: ").append(this.cover).append(" ").
            append("Artist: ").append(this.artist).append(" ").
            append("Label: ").append(this.label);
        return str.toString();
    }
}
