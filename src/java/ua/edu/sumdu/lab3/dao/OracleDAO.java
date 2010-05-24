/**
* This interface describes all posible operation with albums.
* @author Andrey Parhomenko
* @author Sergiy Stetsyun
* @version 1.0
* @date 18.05.2010
*/

package ua.edu.sumdu.lab3.dao;

import ua.edu.sumdu.lab3.exceptions.*;
import ua.edu.sumdu.lab3.dao.operators.*;
import ua.edu.sumdu.lab3.model.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;

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
       return mainOperator.getGenres(artist);
    }

    /**
    * Returns list of genres of the specified label.
    * @param label label of the genre.
    * @return list of genres of the specified label.
    * @throws OracleDataAccessObjectException if problems while getting data.
    */
    public List getGenres(Label label) 
            throws OracleDataAccessObjectException {
        return mainOperator.getGenres(label);
    }

    /**
    * Returns list of all dates.
    * @return list of all dates.
    * @throws OracleDataAccessObjectException if problems while getting data.
    */
    public List getDates() 
            throws OracleDataAccessObjectException {
        return mainOperator.getDates();
    }

    /**
     * Adds new album to the specified storage.
     * @param album new instanse of the Album that should be added.
     * @throws OracleDataAccessObjectException if problems while adding the data.
     */ 
    public void addAlbum(Album album) 
            throws OracleDataAccessObjectException {
        albumsOperator.addAlbum(album);
    }

    /**
     * Adds new label to the specified storage.
     * @param album new instanse of the Label that should be added.
     * @throws OracleDataAccessObjectException if problems while adding the data.
     */ 
    public void addLabel(Label label) 
            throws OracleDataAccessObjectException {
        labelsOperator.addLabel(label);
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
        return albumsOperator.getAlbum(id);
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
        return labelsOperator.getLabel(id);
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
        return albumsOperator.getAlbums(genre, firstRow, lastRow);
    }

    /**
     * Returns list of albums of the specified name.
     * @param name name of the album.
     * @return list of albums of the specified name.
     * @throws OracleDataAccessObjectException if problems while getting data.
     */ 
    public List getAlbumsByName(String name, int firstRow, int lastRow) 
            throws OracleDataAccessObjectException {
        return albumsOperator.getAlbumsByName(name, firstRow, lastRow);
    }
    
    /**
     * Returns list of albums of the specified date.
     * @param date date of the album.
     * @return list of albums of the specified date.
     * @throws OracleDataAccessObjectException if problems while getting data.
     */ 
    public List getAlbums(Date date, int firstRow, int lastRow) 
            throws OracleDataAccessObjectException {
        return albumsOperator.getAlbums(date, firstRow, lastRow);
    }
    
     /**
     * Returns list of albums of the specified artist.
     * @param artist artist of the album.
     * @return list of albums of the specified artist.
     * @throws OracleDataAccessObjectException if problems while getting data.
     */ 
    public List getAlbums(Artist artist, int firstRow, int lastRow) 
            throws OracleDataAccessObjectException {
        return  albumsOperator.getAlbums(artist, firstRow, lastRow);
    }
        
    
    /**
    * Returns list of albums of the specified label.
    * @param label label of the album.
    * @return list of albums of the specified label.
    * @throws OracleDataAccessObjectException if problems while getting data.
    */ 
    public List getAlbums(Label label, int firstRow, int lastRow)  
            throws OracleDataAccessObjectException {
        return albumsOperator.getAlbums(label, firstRow, lastRow);
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
        return labelsOperator.getLabels();
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
        return labelsOperator.getMajorLabels(firstRow, lastRow);
    }
    
    /**
    * Returns list of child labels od the lable with specified id.
    * @param id label's id.
    * @return list of child labels od the lable with specified id.
    * @throws OracleDataAccessObjectException if problems while getting data.
    */
    public List getChildLabels(int id) 
            throws OracleDataAccessObjectException {
        return labelsOperator.getChildLabels(id);
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
        return albumsOperator.getAlbums(firstRow, lastRow);
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
     * Edits specified album. Replaces found (by id) album by specified.
     * 
     * @param album to edit/change.
     * @throws OracleDataAccessObjectException if problems while editting data.
     */ 
    public void editAlbum(Album album) 
            throws OracleDataAccessObjectException {
        albumsOperator.editAlbum(album);
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
        labelsOperator.editLabel(label);
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
        return albumsOperator.getAlbumNumber();
    }
    
    /**
     * Returns maximal id of the album in storage by specified date.
     * @return maximal id of the album in storage by specified date.
     * @throws OracleDataAccessObjectException if problems while getting data.
     */
    public int getAlbumNumber(Date date) 
            throws OracleDataAccessObjectException {
        return albumsOperator.getAlbumNumber(date);
    }
    
    /**
     * Returns maximal id of the album in storage by specified genre.
     * @return maximal id of the album in storage by specified genre.
     * @throws OracleDataAccessObjectException if problems while getting data.
     */
    public int getAlbumNumber(String genre) 
            throws OracleDataAccessObjectException {
        return albumsOperator.getAlbumNumber(genre);
    }
    
    /**
     * Returns maximal id of the label in storage.
     * @return maximal id of the label in storage.
     * @throws OracleDataAccessObjectException if problems while getting data.
     */
    public int getLabelNumber() 
            throws OracleDataAccessObjectException {
        return labelsOperator.getLabelNumber();
    }
    
    /**
     * Returns the random album from the storage.
     * @return random album from the storage.
     * @throws OracleDataAccessObjectException if problems while getting data.
     */ 
    public Album getRandomAlbum() 
            throws OracleDataAccessObjectException {
        return albumsOperator.getRandomAlbum();
    }
    
    /**
     * Removes album from the storage.
     * @param id album's id.
     * @throws OracleDataAccessObjectException if problems while getting data.
     */ 
    public void deleteAlbum(int id) 
            throws OracleDataAccessObjectException {
        albumsOperator.deleteAlbum(id);
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
        labelsOperator.deleteLabel(id);
    }

    /**
     * Returns the path to the specified label in the hierarchy of labels.
     * @param id id of the label.
     * @return path to the specified label in the hierarchy of labels.
     * @throws OracleDataAccessObjectException if problems while getting data.
     */ 
    public List getLabelPath(int id) 
            throws OracleDataAccessObjectException {
        return labelsOperator.getLabelPath(id);
    }       
}
