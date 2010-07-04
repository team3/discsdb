/**
* This interface describes all posible operation with discs.
* @author Andrey Parhomenko
* @author Sergiy Stetsyun
* @version 1.0
* @date 11.05.2010
*/ 

package ua.edu.sumdu.lab3.model;

import ua.edu.sumdu.lab3.exceptions.*;
import java.util.Date;
import java.util.List;
import java.util.Map;


public interface OperableDAO {

    /**
     * Adds new album to the specified storage.
     * @param album new instanse of the Album that should be added.
     * @throws OracleDataAccessObjectException if problems while adding the data.
     */ 
    public void addAlbum(Album album) 
            throws OracleDataAccessObjectException;

    /**
     * Adds new label to the specified storage.
     * @param album new instanse of the Label that should be added.
     * @throws OracleDataAccessObjectException if problems while adding the data.
     */ 
    public void addLabel(Label label) 
            throws OracleDataAccessObjectException;

    /**
     * Adds new artist to the specified storage.
     * @param album new instanse of the Artist that should be added.
     * @throws OracleDataAccessObjectException if problems while adding the data.
     */ 
    public void addArtist(Artist artist) 
            throws OracleDataAccessObjectException;

    /**
     * Returns the album by the specified id.
     * 
     * @param id id of the album that should be return.
     * @return album by the specified id.
     * @throws OracleDataAccessObjectException if problems while getting data.
     */ 
    public Album getAlbum(int id) 
        throws OracleDataAccessObjectException;
    
    /**
     * Returns the label by the specified id.
     * 
     * @param id id of the label that should be return.
     * @return label by the specified id.
     * @throws OracleDataAccessObjectException if problems while getting data.
     */ 
    public Label getLabel(int id) 
            throws OracleDataAccessObjectException;
    
    /**
     * Returns the artist by the specified id.
     * 
     * @param id id of the artist that should be return.
     * @return artist by the specified id.
     * @throws OracleDataAccessObjectException if problems while getting data.
     */ 
    public Artist getArtist(int id) 
            throws OracleDataAccessObjectException;

    /**
     * Returns list of albums of the specified genre.
     * @param genre genre of the album.
     * @return list of albums of the specified genre.
     * @throws OracleDataAccessObjectException if problems while getting data.
     */ 
    public List getAlbums(String genre, int firstRow, int lastRow) 
            throws OracleDataAccessObjectException;

    /**
     * Returns list of albums of the specified name.
     * @param name name of the album.
     * @return list of albums of the specified name.
     * @throws OracleDataAccessObjectException if problems while getting data.
     */ 
    public List getAlbumsByName(String name, int firstRow, int lastRow) 
            throws OracleDataAccessObjectException;
    
    /**
     * Returns list of albums of the specified date.
     * @param date date of the album.
     * @return list of albums of the specified date.
     * @throws OracleDataAccessObjectException if problems while getting data.
     */ 
    public List getAlbums(Date date, int firstRow, int lastRow) 
            throws OracleDataAccessObjectException;
    
     /**
     * Returns list of albums of the specified artist.
     * @param artist artist of the album.
     * @return list of albums of the specified artist.
     * @throws OracleDataAccessObjectException if problems while getting data.
     */ 
    public List getAlbums(Artist artist, int firstRow, int lastRow) 
            throws OracleDataAccessObjectException;
    
    /**
    * Returns list of albums of the specified label.
    * @param label label of the album.
    * @return list of albums of the specified label.
    * @throws OracleDataAccessObjectException if problems while getting data.
    */ 
    public List getAlbums(Label label, int firstRow, int lastRow)  
            throws OracleDataAccessObjectException;

    /**
    * Returns list of artists of the specified label.
    * @param label label of the artist.
    * @return list of artists of the specified label.
    * @throws OracleDataAccessObjectException if problems while getting data.
    */
    public List getArtists(Label label) 
            throws OracleDataAccessObjectException;

    /**
    * Returns list of artists of the specified country.
    * @param country country of the artist.
    * @return list of artists of the specified country.
    * @throws OracleDataAccessObjectException if problems while getting data.
    */
    public List getArtists(String country, int firstRow, int lastRow) 
            throws OracleDataAccessObjectException;

    /**
    * Returns list of genres of the specified artist.
    * @param artist artist of the genre.
    * @return list of genres of the specified artist.
    * @throws OracleDataAccessObjectException if problems while getting data.
    */
    public List getGenres(Artist artist) 
            throws OracleDataAccessObjectException;

    /**
    * Returns list of genres of the specified label.
    * @param label label of the genre.
    * @return list of genres of the specified label.
    * @throws OracleDataAccessObjectException if problems while getting data.
    */
    public List getGenres(Label label) 
            throws OracleDataAccessObjectException;

    /**
    * Returns list of all labels.
    * @return list of all labels.
    * @throws OracleDataAccessObjectException if problems while getting data.
    */
    public List getLabels() 
            throws OracleDataAccessObjectException;

    public List getMajorLabels(int firstRow, int lastRow)
            throws OracleDataAccessObjectException;
    
    public List getChildLabels(int id) 
            throws OracleDataAccessObjectException;
    
    /**
    * Returns list of all dates.
    * @return list of all dates.
    * @throws OracleDataAccessObjectException if problems while getting data.
    */
    public List getDates() throws OracleDataAccessObjectException;

    /**
    * Returns list of all artists.
    * @return list of all artists.
    * @throws OracleDataAccessObjectException if problems while getting data.
    */
    public List getArtists(int firstRow, int lastRow) 
            throws OracleDataAccessObjectException;
    
    /**
    * Returns list of all albums.
    * @return list of all albums.
    * @throws OracleDataAccessObjectException if problems while getting data.
    */
    public List getAlbums(int firstRow, int lastRow) 
            throws OracleDataAccessObjectException;

    /**
     * Findes and returns list of albums by the specified params.
     * @param params parameters to find by.
     * @return list of albums by the specified params.
     * @throws OracleDataAccessObjectException if problems while getting data.
     */ 
    public List findAlbums(Map params, int firstRow, int lastRow) 
            throws OracleDataAccessObjectException;

    /**
     * Edits specified album. Replaces found (by id) album by specified.
     * 
     * @param album to edit/change.
     * @throws OracleDataAccessObjectException if problems while editting data.
     */ 
    public void editAlbum(Album album) 
            throws OracleDataAccessObjectException;
    
    /**
     * Edits specified artist. Replaces found (by id) label by specified.
     * 
     * @param artist to edit/change.
     * @throws OracleDataAccessObjectException if problems while editting data.
     */ 
    public void editArtist(Artist artist) 
            throws OracleDataAccessObjectException;

    /**
     * Edits specified label. Replaces found (by id) label by specified.
     * 
     * @param label to edit/change.
     * @throws OracleDataAccessObjectException if problems while editting data.
     */ 
    public void editLabel(Label label) 
            throws OracleDataAccessObjectException;
    
    /**
     * Returns list of the <code>number</code> lastest albums.
     * @param number number of albums to return.
     * @return list of the <code>number</code> lastest albums.
     * @throws OracleDataAccessObjectException if problems while getting data.
     */ 
    public List getLatestAlbums(int number) 
            throws OracleDataAccessObjectException;
    
    /**
     * Returns id of the artist by specified name.
     * @param name name of the artist.
     * @return id of the artist by specified name.
     * @throws OracleDataAccessObjectException if problems while getting data.
     */ 
    public int findArtist(String name) 
            throws OracleDataAccessObjectException;
    
    /**
     * Returns id of the label by specified name.
     * @param name name of the label.
     * @return id of the label by specified name.
     * @throws OracleDataAccessObjectException if problems while getting data.
     */
    public int findLabel(String name) 
            throws OracleDataAccessObjectException;
    
    /**
     * Returns maximal id of the artist in storage.
     * @return maximal id of the artist in storage.
     * @throws OracleDataAccessObjectException if problems while getting data.
     */
    public int getArtistNumber() 
            throws OracleDataAccessObjectException;
    
    /**
     * Returns maximal id of the artist in storage by specified country.
     * @return maximal id of the artist in storage by specified country.
     * @throws OracleDataAccessObjectException if problems while getting data.
     */
    public int getArtistNumber(String country) 
            throws OracleDataAccessObjectException;
    
    /**
     * Returns maximal id of the album in storage.
     * @return maximal id of the album in storage.
     * @throws OracleDataAccessObjectException if problems while getting data.
     */
    public int getAlbumNumber() throws OracleDataAccessObjectException;
    
    /**
     * Returns maximal id of the album in storage by specified date.
     * @return maximal id of the album in storage by specified date.
     * @throws OracleDataAccessObjectException if problems while getting data.
     */
    public int getAlbumNumber(Date date) 
            throws OracleDataAccessObjectException;
    
    /**
     * Returns maximal id of the album in storage by specified genre.
     * @return maximal id of the album in storage by specified genre.
     * @throws OracleDataAccessObjectException if problems while getting data.
     */
    public int getAlbumNumber(String genre) 
            throws OracleDataAccessObjectException;
    
    /**
     * Returns maximal id of the label in storage.
     * @return maximal id of the label in storage.
     * @throws OracleDataAccessObjectException if problems while getting data.
     */
    public int getLabelNumber() 
            throws OracleDataAccessObjectException;
    
    /**
     * Returns the random album from the storage.
     * @return random album from the storage.
     * @throws OracleDataAccessObjectException if problems while getting data.
     */ 
    public Album getRandomAlbum() 
            throws OracleDataAccessObjectException;
    
    /**
     * Removes album from the storage.
     * @param id album's id.
     * @throws OracleDataAccessObjectException if problems while getting data.
     */ 
    public void deleteAlbum(int id) 
            throws OracleDataAccessObjectException;
    
    /**
     * Removes artist from the storage.
     * @param id artist's id.
     * @throws OracleDataAccessObjectException if problems while getting data.
     */
    public void deleteArtist(int id) 
            throws OracleDataAccessObjectException;
    
    /**
     * Removes label from the storage.
     * @param id label's id.
     * @throws OracleDataAccessObjectException if problems while getting data.
     */
    public void deleteLabel(int id) 
            throws OracleDataAccessObjectException;

    /**
     * Returns the path to the specified label in the hierarchy of labels.
     * @param id id of the label.
     * @return path to the specified label in the hierarchy of labels.
     * @throws OracleDataAccessObjectException if problems while getting data.
     */ 
    public List getLabelPath(int id) 
            throws OracleDataAccessObjectException;        
}
