package ua.edu.sumdu.lab3.ejbModule.album;

import javax.ejb.*;
import java.util.*;
import java.rmi.RemoteException;

import ua.edu.sumdu.lab3.dao.operators.*;
import ua.edu.sumdu.lab3.exceptions.*;
import ua.edu.sumdu.lab3.model.*;

public class AlbumBean implements EntityBean {
	
	private AlbumsOperator albumsOperator = null;
	private boolean needToStore = false;
	private EntityContext context = null;
	
	public AlbumBean() {
		albumsOperator = new AlbumsOperator();
	}
	
	/**
	 * Creates album
	 * @param name name of the album.
	 */ 
	public Integer ejbCreate(
			String name, String type, Date release, String genre,
			String cover, String artistName, String labelName,
			String review, Integer artist, Integer label) 
			throws CreateException, RemoteException {
		int id = 0;
		
		try {
			id = albumsOperator.addAlbum(name, type, release, genre, 
					cover, artistName, labelName, review, 
					artist.intValue(), label.intValue());
			
			this.name = name;
			this.type = type;
			this.release = release;
			this.genre = genre;
			this.cover = cover;
			this.artistName = artistName;
			this.labelName = labelName;
			this.review = review;
			this.artist = artist.intValue();
			this.label = label.intValue();
			
		} catch (OracleDataAccessObjectException e){
			throw new CreateException(e.getMessage());
		}
		return new Integer(id);
	}
	
	/**
	 * 
	 * 
	 * 
	 */
	
	public void ejbPostCreate(
			String name, String type, Date release, String genre,
			String cover, String artistName, String labelName,
			String review, Integer artist, Integer label) 
			throws CreateException, RemoteException {
		
	}
	
	/**
	 * Finds bean by primary key
	 * 
	 */ 
	
	public Integer ejbFindByPrimaryKey(Integer id) 
			throws FinderException, RemoteException {
		Album album = null;
		try {
			album = albumsOperator.getAlbum(id.intValue());
		} catch (OracleDataAccessObjectException e){
			throw new FinderException(e.getMessage());
		}
		return new Integer(album.getId());
	}
	
	/**
	 * 
	 * 
	 */ 
	
	public void setEntityContext(EntityContext ectx){
		this.context = ectx;
	}
	
	/**
	 * 
	 * 
	 */ 
	
	public void unsetEntityContext(){
		this.context = null;
	}
	
	/**
	 * Removes current instanse of the bean;
	 * 
	 */ 
	
	public void ejbRemove() 
			throws RemoveException, RemoteException {
		try {
			albumsOperator.deleteAlbum(this.id);
		} catch (OracleDataAccessObjectException e){
			throw new RemoveException(e.getMessage());
		}
	}
	
	/**
	 * 
	 * 
	 */ 
	
	public void ejbActivate(){
		this.id = ((Integer) context.getPrimaryKey()).intValue();
	}
	
	/**
	 * 
	 * 
	 */ 
	
	public void ejbPassivate() {}
	
	/**
	 * 
	 * 
	 */
	 public void ejbLoad() throws EJBException, RemoteException {
		Album album = null;
		
		try {
			album = albumsOperator.getAlbum(this.id);
			
			this.id = album.getId();
			this.name = album.getName();
			this.type = album.getType();
			this.release = album.getRelease();
			this.genre = album.getGenre();
			this.cover = album.getCover();
			this.artistName = album.getArtistName();
			this.labelName = album.getLabelName();
			this.review = album.getReview();
			this.artist = album.getArtist();
			this.label = album.getLabel();
			
		} catch (OracleDataAccessObjectException e){
			throw new EJBException(e.getMessage());
		}
	} 
	
	/**
	 * 
	 * 
	 */
	public void ejbStore() throws EJBException, RemoteException {
		if (!needToStore) return;
		
		try {
			albumsOperator.editAlbum(this.id, this.name, this.type,
					this.release, this.genre, this.cover, this.artistName,
					this.labelName, this.review, this.artist, this.label);
			this.needToStore = false;
		} catch (OracleDataAccessObjectException e){
			throw new EJBException(e.getMessage());
		}
	} 
	
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
}
