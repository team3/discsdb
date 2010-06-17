/**
* This interface describes all posible operation with albums.
* @author Andrey Parhomenko
* @author Sergiy Stetsyun
* @version 2.0
* @date 11.06.2010
*/

package ua.edu.sumdu.lab3.dao;

import ua.edu.sumdu.lab3.exceptions.*;
import ua.edu.sumdu.lab3.ejbModule.label.*;
import ua.edu.sumdu.lab3.ejbModule.album.*;
import ua.edu.sumdu.lab3.ejbModule.Allocator;
import ua.edu.sumdu.lab3.dao.operators.*;
import ua.edu.sumdu.lab3.model.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.sql.*;
import javax.sql.*;
import javax.ejb.FinderException;
import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.RemoveException;
import java.rmi.RemoteException;
import javax.naming.NamingException;

import org.apache.log4j.Logger;
import org.apache.log4j.BasicConfigurator;

public class OracleDAO implements OperableDAO {
        
    private Logger log = null;

    private static OracleDAO instance = null;
    private AlbumsOperator albumsOperator = null;
    private ArtistsOperator artistsOperator = null;
    private LabelsOperator labelsOperator = null;
    private MainOperator mainOperator = null;
    
    protected OracleDAO() {
        log = Logger.getLogger(OracleDAO.class);
        albumsOperator = new AlbumsOperator();
        artistsOperator = new ArtistsOperator();
        labelsOperator = new LabelsOperator();
        mainOperator = new MainOperator();
    }

    /**
     * Returns new instance of this class if it does not exist.
     * @return new instance of this class.
     */
    public static OracleDAO getInstance() {
        if (instance == null) {
            instance = new OracleDAO();
        }
        return instance;
    }

    /**
    * Returns list of genres of the specified artist.
    * @param artist artist of the genre.
    * @return list of genres of the specified artist.
    * @throws OracleDataAccessObjectException if problems while getting data.
    */
    public List getGenres(Artist artist) 
            throws OracleDataAccessObjectException {
		//return albumsOperator.getGenresByArtist(artist);
		List genres = null;
		try {
			genres = (List)Allocator.getAlbumHomeItf().getGenresByArtist(
					new Integer(artist.getId()));
		} catch (EJBException e){
			throw new OracleDataAccessObjectException(e);
		} catch (RemoteException e){
			throw new OracleDataAccessObjectException(e);
		} catch (NamingException e){
			throw new OracleDataAccessObjectException(e);
		}
		return genres;
    }

    /**
    * Returns list of genres of the specified label.
    * @param label label of the genre.
    * @return list of genres of the specified label.
    * @throws OracleDataAccessObjectException if problems while getting data.
    */
    public List getGenres(Label label) 
            throws OracleDataAccessObjectException {
        //return albumsOperator.getGenresByLabel(label);
        List genres = null;
		try {
			genres = (List)Allocator.getAlbumHomeItf().getGenresByLabel(
					new Integer(label.getId()));
		} catch (EJBException e){
			throw new OracleDataAccessObjectException(e);
		} catch (RemoteException e){
			throw new OracleDataAccessObjectException(e);
		} catch (NamingException e){
			throw new OracleDataAccessObjectException(e);
		}
		return genres;
    }

    /**
    * Returns list of all dates.
    * @return list of all dates.
    * @throws OracleDataAccessObjectException if problems while getting data.
    */
    public List getDates() 
            throws OracleDataAccessObjectException {
        //return albumsOperator.getDates();
        List dates = null;
		try {
			dates = (List)Allocator.getAlbumHomeItf().getDates();
		} catch (EJBException e){
			throw new OracleDataAccessObjectException(e);
		} catch (RemoteException e){
			throw new OracleDataAccessObjectException(e);
		} catch (NamingException e){
			throw new OracleDataAccessObjectException(e);
		}
		return dates;
    }

    /**
     * Adds new album to the specified storage.
     * @param album new instanse of the Album that should be added.
     * @throws OracleDataAccessObjectException if problems while adding the data.
     */ 
    public void addAlbum(Album album) 
            throws OracleDataAccessObjectException {
        try {
			AlbumHome albumHome = Allocator.getAlbumHomeItf();
			albumHome.create(album.getName(), album.getType(), album.getRelease(),
					album.getGenre(), album.getCover(), album.getArtistName(),
					album.getLabelName(), album.getReview(), 
					new Integer(album.getArtist()), new Integer(album.getLabel()));
		} catch (CreateException e){
			throw new OracleDataAccessObjectException(e);
		} catch (RemoteException e){
			throw new OracleDataAccessObjectException(e);
		} catch (NamingException e){
			throw new OracleDataAccessObjectException(e);
		}
    }

    /**
     * Adds new label to the specified storage.
     * @param album new instanse of the Label that should be added.
     * @throws OracleDataAccessObjectException if problems while adding the data.
     */ 
    public void addLabel(Label label) 
            throws OracleDataAccessObjectException {
		try {
			LabelHome labelHome = Allocator.getLabelHomeItf();
			labelHome.create(
				label.getMajor(), label.getName(), label.getInfo(),
				label.getLogo(), label.getMajorName()
			);
		} catch (CreateException e){
			throw new OracleDataAccessObjectException(e);
		} catch (RemoteException e){
			throw new OracleDataAccessObjectException(e);
		} catch (NamingException e){
			throw new OracleDataAccessObjectException(e);
		}
    }

    /**
     * Adds new artist to the specified storage.
     * @param album new instanse of the Artist that should be added.
     * @throws OracleDataAccessObjectException if problems while adding the data.
     */ 
    public void addArtist(Artist artist) 
            throws OracleDataAccessObjectException {
        artistsOperator.addArtist(artist);
    }

    /**
     * Returns the album by the specified id.
     * 
     * @param id id of the album that should be return.
     * @return album by the specified id.
     * @throws OracleDataAccessObjectException if problems while getting data.
     */ 
    public Album getAlbum(int id) 
            throws OracleDataAccessObjectException {
        Album album = null;
        try {
			album = new Album();
			AlbumHome albumHome = Allocator.getAlbumHomeItf();
			AlbumRemote albumRemote = albumHome.findByPrimaryKey(new Integer(id));
			
			album.setId(albumRemote.getId());
			album.setName(albumRemote.getName());
			album.setType(albumRemote.getType());
			album.setRelease(albumRemote.getRelease());
			album.setGenre(albumRemote.getGenre());
			album.setCover(albumRemote.getCover());
			album.setArtist(albumRemote.getArtist());
			album.setLabel(albumRemote.getLabel());
			album.setArtistName(albumRemote.getArtistName());
			album.setLabelName(albumRemote.getLabelName());
			album.setReview(albumRemote.getReview());
           
		} catch (FinderException e){
			throw new OracleDataAccessObjectException(e.getMessage());
		} catch (RemoteException e){
			throw new OracleDataAccessObjectException(e.getMessage());
		} catch (NamingException e){
			throw new OracleDataAccessObjectException(e.getMessage());
		}
		return album;
    }
    
    /**
     * Returns the label by the specified id.
     * 
     * @param id id of the label that should be return.
     * @return label by the specified id.
     * @throws OracleDataAccessObjectException if problems while getting data.
     */ 
    public Label getLabel(int id) 
            throws OracleDataAccessObjectException {
        Label label = null;
        try {
			label = new Label();
			LabelHome labelHome = Allocator.getLabelHomeItf();
			LabelRemote labelRemote = labelHome.findByPrimaryKey(new Integer(id));
			
			label.setId(labelRemote.getId().intValue());
			label.setName(labelRemote.getName());
			label.setInfo(labelRemote.getInfo());
			label.setLogo(labelRemote.getLogo());
			label.setMajor(labelRemote.getMajor().intValue());
			label.setMajorName(labelRemote.getMajorName());
           
		} catch (FinderException e){
			throw new OracleDataAccessObjectException(e.getMessage());
		} catch (RemoteException e){
			throw new OracleDataAccessObjectException(e.getMessage());
		} catch (NamingException e){
			throw new OracleDataAccessObjectException(e.getMessage());
		}
		return label;
	}
    
    /**
     * Returns the artist by the specified id.
     * 
     * @param id id of the artist that should be return.
     * @return artist by the specified id.
     * @throws OracleDataAccessObjectException if problems while getting data.
     */ 
    public Artist getArtist(int id) 
            throws OracleDataAccessObjectException {
        return artistsOperator.getArtist(id);
    }

    /**
     * Returns list of albums of the specified genre.
     * @param genre genre of the album.
     * @return list of albums of the specified genre.
     * @throws OracleDataAccessObjectException if problems while getting data.
     */ 
    public List getAlbums(String genre, int firstRow, int lastRow) 
            throws OracleDataAccessObjectException {
        //return albumsOperator.getAlbums(genre, firstRow, lastRow);
        List albums = null;
        try {
			albums = (List)Allocator.getAlbumHomeItf().getByGenre(
					genre,
					new Integer(firstRow), 
					new Integer(lastRow));
		} catch (EJBException e){
			throw new OracleDataAccessObjectException(e);
		} catch (RemoteException e){
			throw new OracleDataAccessObjectException(e);
		} catch (NamingException e){
			throw new OracleDataAccessObjectException(e);
		}
		return albums;
    }

    /**
     * Returns list of albums of the specified name.
     * @param name name of the album.
     * @return list of albums of the specified name.
     * @throws OracleDataAccessObjectException if problems while getting data.
     */ 
    public List getAlbumsByName(String name, int firstRow, int lastRow) 
            throws OracleDataAccessObjectException {
        //return albumsOperator.getAlbumsByName(name, firstRow, lastRow);
        List albums = null;
        try {
			albums = (List)Allocator.getAlbumHomeItf().getByName(
					name,
					new Integer(firstRow), 
					new Integer(lastRow));
		} catch (EJBException e){
			throw new OracleDataAccessObjectException(e);
		} catch (RemoteException e){
			throw new OracleDataAccessObjectException(e);
		} catch (NamingException e){
			throw new OracleDataAccessObjectException(e);
		}
		return albums;
    }
    
    /**
     * Returns list of albums of the specified date.
     * @param date date of the album.
     * @return list of albums of the specified date.
     * @throws OracleDataAccessObjectException if problems while getting data.
     */ 
    public List getAlbums(Date date, int firstRow, int lastRow) 
            throws OracleDataAccessObjectException {
        //return albumsOperator.getAlbums(date, firstRow, lastRow);
        List albums = null;
        try {
			albums = (List)Allocator.getAlbumHomeItf().getByDate(
					date,
					new Integer(firstRow), 
					new Integer(lastRow));
		} catch (EJBException e){
			throw new OracleDataAccessObjectException(e);
		} catch (RemoteException e){
			throw new OracleDataAccessObjectException(e);
		} catch (NamingException e){
			throw new OracleDataAccessObjectException(e);
		}
		return albums;
    }
    
     /**
     * Returns list of albums of the specified artist.
     * @param artist artist of the album.
     * @return list of albums of the specified artist.
     * @throws OracleDataAccessObjectException if problems while getting data.
     */ 
    public List getAlbums(Artist artist, int firstRow, int lastRow) 
            throws OracleDataAccessObjectException {
        //return  albumsOperator.getAlbums(artist, firstRow, lastRow);
        List albums = null;
        try {
			albums = (List)Allocator.getAlbumHomeItf().getByArtist(
					new Integer(artist.getId()), 
					new Integer(firstRow), 
					new Integer(lastRow));
		} catch (EJBException e){
			throw new OracleDataAccessObjectException(e);
		} catch (RemoteException e){
			throw new OracleDataAccessObjectException(e);
		} catch (NamingException e){
			throw new OracleDataAccessObjectException(e);
		}
		return albums;
    }
        
    
    /**
    * Returns list of albums of the specified label.
    * @param label label of the album.
    * @return list of albums of the specified label.
    * @throws OracleDataAccessObjectException if problems while getting data.
    */ 
    public List getAlbums(Label label, int firstRow, int lastRow)  
            throws OracleDataAccessObjectException {
        //return albumsOperator.getAlbums(label, firstRow, lastRow);
        List albums = null;
        try {
			albums = (List)Allocator.getAlbumHomeItf().getByLabel(
					new Integer(label.getId()),
					new Integer(firstRow), 
					new Integer(lastRow));
		} catch (EJBException e){
			throw new OracleDataAccessObjectException(e);
		} catch (RemoteException e){
			throw new OracleDataAccessObjectException(e);
		} catch (NamingException e){
			throw new OracleDataAccessObjectException(e);
		}
		return albums;
    }

    /**
    * Returns list of artists of the specified label.
    * @param label label of the artist.
    * @return list of artists of the specified label.
    * @throws OracleDataAccessObjectException if problems while getting data.
    */
    public List getArtists(Label label) 
            throws OracleDataAccessObjectException {
        return artistsOperator.getArtists(label);
        
    }

    /**
    * Returns list of artists of the specified country.
    * @param country country of the artist.
    * @return list of artists of the specified country.
    * @throws OracleDataAccessObjectException if problems while getting data.
    */
    public List getArtists(String country, int firstRow, int lastRow) 
            throws OracleDataAccessObjectException {
        return artistsOperator.getArtists(country, firstRow, lastRow);
    }

    /**
    * Returns list of all labels.
    * @return list of all labels.
    * @throws OracleDataAccessObjectException if problems while getting data.
    */
    public List getLabels() 
            throws OracleDataAccessObjectException {
        List labels = null;
        try {
			labels = (List)Allocator.getLabelHomeItf().getLabels();
		} catch (RemoteException e){
			throw new OracleDataAccessObjectException(e.getMessage());
		} catch (NamingException e){
			throw new OracleDataAccessObjectException(e.getMessage());
		}
		return labels;
    }
    
    /**
    * Returns list of all major labels in specified restrictions.
    * @param firstIndex lower bound.
    * @param lastRow upper bound.
    * @return list of all labels in specified restrictions.
    * @throws OracleDataAccessObjectException if problems while getting data.
    */
    public List getMajorLabels(int firstRow, int lastRow)
            throws OracleDataAccessObjectException {
        List labels = null;
        try {
			labels = (List)Allocator.getLabelHomeItf().getMajorLabels(
					firstRow, lastRow);
		} catch (RemoteException e){
			throw new OracleDataAccessObjectException(e.getMessage());
		} catch (NamingException e){
			throw new OracleDataAccessObjectException(e.getMessage());
		}
		return labels;
    }
    
    /**
    * Returns list of child labels od the lable with specified id.
    * @param id label's id.
    * @return list of child labels od the lable with specified id.
    * @throws OracleDataAccessObjectException if problems while getting data.
    */
    public List getChildLabels(int id) 
            throws OracleDataAccessObjectException {
        List labels = null;
        try {
			labels = (List)Allocator.getLabelHomeItf().getChildLabels(id);
		} catch (RemoteException e){
			throw new OracleDataAccessObjectException(e);
		} catch (NamingException e){
			throw new OracleDataAccessObjectException(e);
		}
		return labels;
    }
    
    /**
    * Returns list of all artists.
    * @return list of all artists.
    * @throws OracleDataAccessObjectException if problems while getting data.
    */
    public List getArtists(int firstRow, int lastRow) 
            throws OracleDataAccessObjectException {
        return artistsOperator.getArtists(firstRow, lastRow);
    }
    
    /**
    * Returns list of all albums.
    * @return list of all albums.
    * @throws OracleDataAccessObjectException if problems while getting data.
    */
    public List getAlbums(int firstRow, int lastRow) 
            throws OracleDataAccessObjectException {
        //return albumsOperator.getAlbums(firstRow, lastRow);
        
        List albums = null;
        try {
			albums = (List)Allocator.getAlbumHomeItf().getAll(
					new Integer(firstRow), 
					new Integer(lastRow));
		} catch (EJBException e){
			throw new OracleDataAccessObjectException(e);
		} catch (RemoteException e){
			throw new OracleDataAccessObjectException(e);
		} catch (NamingException e){
			throw new OracleDataAccessObjectException(e);
		}
		return albums;
    }

    /**
     * Findes and returns list of albums by the specified params.
     * @param params parameters to find by.
     * @return list of albums by the specified params.
     * @throws OracleDataAccessObjectException if problems while getting data.
     */ 
    
    public List findAlbums(Map params, int firstRow, int lastRow) 
            throws OracleDataAccessObjectException {
        return albumsOperator.findAlbums(params, firstRow, lastRow);
        
    }
   
   /**
    * 
    * 
    */ 
   public void editAlbum(int id, String name, String type, Date release,
			String genre, String cover, String artistName, 
			String labelName, String review, int artist, int label) 
			throws OracleDataAccessObjectException {
		try {
		
			AlbumHome albumHome = Allocator.getAlbumHomeItf();
			AlbumRemote albumRemote = albumHome.findByPrimaryKey(new Integer(id));
			System.out.println("DAO id = " + id);
			albumRemote.setId(new Integer(id));
			albumRemote.setName(name);
			albumRemote.setType(type);
			albumRemote.setRelease(release);
			albumRemote.setGenre(genre);
			albumRemote.setCover(cover);
			albumRemote.setArtist(new Integer(artist));
			albumRemote.setLabel(new Integer(label));
			albumRemote.setArtistName(artistName);
			albumRemote.setLabelName(labelName);
			albumRemote.setReview(review);

		} catch (RemoteException e){
			throw new OracleDataAccessObjectException(e.getMessage());
		} catch (NamingException e){
			throw new OracleDataAccessObjectException(e.getMessage());
		} catch (FinderException e){
			throw new OracleDataAccessObjectException(e.getMessage());
		}
	}
    
    
    /**
     * Edits specified artist. Replaces found (by id) label by specified.
     * 
     * @param artist to edit/change.
     * @throws OracleDataAccessObjectException if problems while editting data.
     */ 
    public void editArtist(Artist artist) 
            throws OracleDataAccessObjectException {
        artistsOperator.editArtist(artist);
    }

    /**
     * Edits specified label. Replaces found (by id) label by specified.
     * 
     * @param label to edit/change.
     * @throws OracleDataAccessObjectException if problems while editting data.
     */ 
    public void editLabel(Label label) 
            throws OracleDataAccessObjectException {
        editLabel(label.getId(), label.getMajor(), label.getName(), 
				label.getInfo(), label.getLogo(), label.getMajorName());
    }
    
    public void editLabel(
			int id, int major, String name, 
			String info, String logo, String majorName) 
			throws OracleDataAccessObjectException {
		try {
			LabelHome labelHome = Allocator.getLabelHomeItf();
			LabelRemote labelRemote = labelHome.findByPrimaryKey(new Integer(id));
			labelRemote.setId(new Integer(id));
			labelRemote.setMajor(new Integer(major));
			labelRemote.setName(name);
			labelRemote.setInfo(info);
			labelRemote.setLogo(logo);
			labelRemote.setMajorName(majorName);
		} catch (RemoteException e){
			throw new OracleDataAccessObjectException(e.getMessage());
		} catch (NamingException e){
			throw new OracleDataAccessObjectException(e.getMessage());
		} catch (FinderException e){
			throw new OracleDataAccessObjectException(e.getMessage());
		}
	}
    
    /**
     * Returns list of the <code>number</code> lastest albums.
     * @param number number of albums to return.
     * @return list of the <code>number</code> lastest albums.
     * @throws OracleDataAccessObjectException if problems while getting data.
     */ 
    public List getLatestAlbums(int number) 
            throws OracleDataAccessObjectException {
        return albumsOperator.getLatestAlbums(number);
    }
    
    /**
     * Returns id of the artist by specified name.
     * @param name name of the artist.
     * @return id of the artist by specified name.
     * @throws OracleDataAccessObjectException if problems while getting data.
     */ 
    public int findArtist(String name) 
            throws OracleDataAccessObjectException {
        return artistsOperator.findArtist(name);
    }
    
    /**
     * Returns id of the label by specified name.
     * @param name name of the label.
     * @return id of the label by specified name.
     * @throws OracleDataAccessObjectException if problems while getting data.
     */
    public int findLabel(String name) 
            throws OracleDataAccessObjectException {
        return labelsOperator.findLabel(name);
    }
    
    /**
     * Returns maximal id of the artist in storage.
     * @return maximal id of the artist in storage.
     * @throws OracleDataAccessObjectException if problems while getting data.
     */
    public int getArtistNumber() 
            throws OracleDataAccessObjectException {
        return artistsOperator.getArtistNumber();
    }
    
    /**
     * Returns maximal id of the artist in storage by specified country.
     * @return maximal id of the artist in storage by specified country.
     * @throws OracleDataAccessObjectException if problems while getting data.
     */
    public int getArtistNumber(String country) 
            throws OracleDataAccessObjectException {
        return artistsOperator.getArtistNumber(country);
    }
    
    /**
     * Returns maximal id of the album in storage.
     * @return maximal id of the album in storage.
     * @throws OracleDataAccessObjectException if problems while getting data.
     */
    public int getAlbumNumber() 
            throws OracleDataAccessObjectException {
        //return albumsOperator.getAlbumNumber();
        int number = 0;
        try {
			number = Allocator.getAlbumHomeItf().getAlbumNumber().intValue();
		} catch (RemoteException e){
			throw new OracleDataAccessObjectException(e.getMessage());
		} catch (NamingException e){
			throw new OracleDataAccessObjectException(e.getMessage());
		}
		return number;
    }
    
    /**
     * Returns maximal id of the album in storage by specified date.
     * @return maximal id of the album in storage by specified date.
     * @throws OracleDataAccessObjectException if problems while getting data.
     */
    public int getAlbumNumber(Date date) 
            throws OracleDataAccessObjectException {
        //return albumsOperator.getAlbumNumber(date);
        int number = 0;
        try {
			number = Allocator.getAlbumHomeItf().getAlbumNumber(
					date).intValue();
		} catch (RemoteException e){
			throw new OracleDataAccessObjectException(e.getMessage());
		} catch (NamingException e){
			throw new OracleDataAccessObjectException(e.getMessage());
		}
		return number;
    }
    
    /**
     * Returns maximal id of the album in storage by specified genre.
     * @return maximal id of the album in storage by specified genre.
     * @throws OracleDataAccessObjectException if problems while getting data.
     */
    public int getAlbumNumber(String genre) 
            throws OracleDataAccessObjectException {
        //return albumsOperator.getAlbumNumber(genre);
        int number = 0;
        try {
			number = Allocator.getAlbumHomeItf().getAlbumNumber(
					genre).intValue();
		} catch (RemoteException e){
			throw new OracleDataAccessObjectException(e.getMessage());
		} catch (NamingException e){
			throw new OracleDataAccessObjectException(e.getMessage());
		}
		return number;
    }
    
    /**
     * Returns maximal id of the label in storage.
     * @return maximal id of the label in storage.
     * @throws OracleDataAccessObjectException if problems while getting data.
     */
    public int getLabelNumber() 
            throws OracleDataAccessObjectException {
        int number = 0;
        try {
			number = Allocator.getLabelHomeItf().getLabelNumber();
		} catch (RemoteException e){
			throw new OracleDataAccessObjectException(e);
		} catch (NamingException e){
			throw new OracleDataAccessObjectException(e);
		}
		return number;
    }
    
    /**
     * Returns the random album from the storage.
     * @return random album from the storage.
     * @throws OracleDataAccessObjectException if problems while getting data.
     */ 
    public Album getRandomAlbum() 
            throws OracleDataAccessObjectException {
        //return albumsOperator.getRandomAlbum();
        Album album = null;
        try {
			album = Allocator.getAlbumHomeItf().getRandom();
		} catch (RemoteException e){
			throw new OracleDataAccessObjectException(e);
		} catch (NamingException e){
			throw new OracleDataAccessObjectException(e);
		}
		return album;
    }
    
    /**
     * Removes album from the storage.
     * @param id album's id.
     * @throws OracleDataAccessObjectException if problems while getting data.
     */ 
    public void deleteAlbum(int id) 
            throws OracleDataAccessObjectException {
        try {
			AlbumHome albumHome = Allocator.getAlbumHomeItf();
			AlbumRemote remote = albumHome.findByPrimaryKey(new Integer(id));
			remote.remove();
		} catch (FinderException e){
			throw new OracleDataAccessObjectException(e);
		} catch (RemoveException e){
			throw new OracleDataAccessObjectException(e);
		} catch (RemoteException e){
			throw new OracleDataAccessObjectException(e);
		} catch (NamingException e){
			throw new OracleDataAccessObjectException(e);
		}

    }
    
    /**
     * Removes artist from the storage.
     * @param id artist's id.
     * @throws OracleDataAccessObjectException if problems while getting data.
     */
    public void deleteArtist(int id) 
            throws OracleDataAccessObjectException {
        artistsOperator.deleteArtist(id);        
    }
    
    /**
     * Removes label from the storage.
     * @param id label's id.
     * @throws OracleDataAccessObjectException if problems while getting data.
     */
    public void deleteLabel(int id) 
            throws OracleDataAccessObjectException {
        try {
			LabelHome labelHome = Allocator.getLabelHomeItf();
			LabelRemote remote = labelHome.findByPrimaryKey(new Integer(id));
			remote.remove();
		} catch (FinderException e){
			throw new OracleDataAccessObjectException(e);
		} catch (RemoveException e){
			throw new OracleDataAccessObjectException(e);
		} catch (RemoteException e){
			throw new OracleDataAccessObjectException(e);
		} catch (NamingException e){
			throw new OracleDataAccessObjectException(e);
		}
    }

    /**
     * Returns the path to the specified label in the hierarchy of labels.
     * @param id id of the label.
     * @return path to the specified label in the hierarchy of labels.
     * @throws OracleDataAccessObjectException if problems while getting data.
     */ 
    public List getLabelPath(int id) 
            throws OracleDataAccessObjectException {
        List labels = null;
        try {
			labels = (List)Allocator.getLabelHomeItf().getLabelPath(id);
		} catch (RemoteException e){
			throw new OracleDataAccessObjectException(e.getMessage());
		} catch (NamingException e){
			throw new OracleDataAccessObjectException(e.getMessage());
		}
		return labels;
    }       
}
