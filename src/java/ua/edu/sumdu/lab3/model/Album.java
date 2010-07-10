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
    
    /**
     * Returnes album's id.
     * @return album's id.
     */ 
    public int getId() {
        return this.id;
    }
    
    /**
     * Returnes album's name.
     * @return album's name.
     */ 
    public String getName() {
        return this.name;
    }
    
    /**
     * Returnes album's type.
     * @return album's type.
     */ 
    public String getType() {
        return this.type;
    }

    /**
     * Returnes album's date.
     * @return album's date.
     */ 
    public Date getRelease() {
        return this.release;
    }

    /**
     * Returnes album's genre.
     * @return album's genre.
     */ 
    public String getGenre() {
        return this.genre;
    }

    /**
     * Returnes album's cover.
     * @return album's cover.
     */ 
    public String getCover() {
        return this.cover;
    }

    /**
     * Returnes album's artist id.
     * @return album's artist id.
     */ 
    public int getArtist() {
        return this.artist;
    }

    /**
     * Returnes album's label id.
     * @return album's label id.
     */ 
    public int getLabel() {
        return this.label;
    }

    /**
     * Returnes album's artist name.
     * @return album's artist name.
     */ 
    public String getArtistName() {
        return this.artistName;
    }

    /**
     * Returnes album's label name.
     * @return album's label name.
     */ 
    public String getLabelName() {
        return this.labelName;
    }

    /**
     * Returnes album's review.
     * @return album's review.
     */ 
    public String getReview() {
        return this.review;
    }
    
    /**
     * Sets album's id.
     * @param album's id.
     */ 
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Sets album's name.
     * @param album's name.
     */ 
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets album's type.
     * @param album's type.
     */ 
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Sets album's date.
     * @param album's date.
     */ 
    public void setRelease(Date release) {
        this.release = release;
    }

    /**
     * Sets album's genre.
     * @param album's genre.
     */ 
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * Sets album's cover.
     * @param album's cover.
     */ 
    public void setCover(String cover) {
        this.cover = cover;
    }

    /**
     * Sets album's artist.
     * @param album's iartist.
     */ 
    public void setArtist(int artist) {
        this.artist = artist;
    }

    /**
     * Sets album's label.
     * @param album's label.
     */ 
    public void setLabel(int label) {
        this.label = label;
    }

    /**
     * Sets album's artist name.
     * @param album's artist name.
     */ 
    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    /**
     * Sets album's label name.
     * @param album's label name.
     */ 
    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }
    
    /**
     * Sets album's review.
     * @param album's review.
     */ 
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
