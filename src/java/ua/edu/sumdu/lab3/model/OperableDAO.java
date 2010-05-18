/**
* This interface describes all posible operation with discs.
* @author Andrey Parhomenko
* @author Sergiy Stetsyun
* @version 1.0
* @date 11.05.2010
*/ 

package ua.edu.sumdu.lab3.model;

import ua.edu.sumdu.lab3.model.exceptions.*;
import java.util.Date;
import java.util.List;
import java.util.Map;


public interface OperableDAO {

    /**
     * Adds new album to the specified storage.
     * @param album new instanse of the Album that should be added.
     * @throws AddDataException if problems while adding the data.
     */ 
    public void addAlbum(Album album) throws AddDataException;

    /**
     * Adds new label to the specified storage.
     * @param album new instanse of the Label that should be added.
     * @throws AddDataException if problems while adding the data.
     */ 
    public void addLabel(Label label) throws AddDataException;

    /**
     * Adds new artist to the specified storage.
     * @param album new instanse of the Artist that should be added.
     * @throws AddDataException if problems while adding the data.
     */ 
    public void addArtist(Artist artist) throws AddDataException;

    /**
     * Returns the album by the specified id.
     * 
     * @param id id of the album that should be return.
     * @return album by the specified id.
     * @throws GetDataException if problems while getting data.
     */ 
    public Album getAlbum(int id) throws GetDataException;
    
    /**
     * Returns the label by the specified id.
     * 
     * @param id id of the label that should be return.
     * @return label by the specified id.
     * @throws GetDataException if problems while getting data.
     */ 
    public Label getLabel(int id) throws GetDataException;
    
    /**
     * Returns the artist by the specified id.
     * 
     * @param id id of the artist that should be return.
     * @return artist by the specified id.
     * @throws GetDataException if problems while getting data.
     */ 
    public Artist getArtist(int id) throws GetDataException;

    /**
     * Returns list of albums of the specified genre.
     * @param genre genre of the album.
     * @return list of albums of the specified genre.
     * @throws GetDataException if problems while getting data.
     */ 
    public List getAlbums(String genre, int firstRow, int lastRow) 
            throws GetDataException;

    /**
     * Returns list of albums of the specified name.
     * @param name name of the album.
     * @return list of albums of the specified name.
     * @throws GetDataException if problems while getting data.
     */ 
    public List getAlbumsByName(String name, int firstRow, int lastRow) 
            throws GetDataException;
    
    /**
     * Returns list of albums of the specified date.
     * @param date date of the album.
     * @return list of albums of the specified date.
     * @throws GetDataException if problems while getting data.
     */ 
    public List getAlbums(Date date, int firstRow, int lastRow) 
            throws GetDataException;
    
     /**
     * Returns list of albums of the specified artist.
     * @param artist artist of the album.
     * @return list of albums of the specified artist.
     * @throws GetDataException if problems while getting data.
     */ 
    public List getAlbums(Artist artist, int firstRow, int lastRow) 
            throws GetDataException;
    
    /**
    * Returns list of albums of the specified label.
    * @param label label of the album.
    * @return list of albums of the specified label.
    * @throws GetDataException if problems while getting data.
    */ 
    public List getAlbums(Label label, int firstRow, int lastRow)  
            throws GetDataException;

    /**
    * Returns list of artists of the specified label.
    * @param label label of the artist.
    * @return list of artists of the specified label.
    * @throws GetDataException if problems while getting data.
    */
    public List getArtists(Label label) throws GetDataException;

    /**
    * Returns list of artists of the specified country.
    * @param country country of the artist.
    * @return list of artists of the specified country.
    * @throws GetDataException if problems while getting data.
    */
    public List getArtists(String country, int firstRow, int lastRow) 
            throws GetDataException;

    /**
    * Returns list of genres of the specified artist.
    * @param artist artist of the genre.
    * @return list of genres of the specified artist.
    * @throws GetDataException if problems while getting data.
    */
    public List getGenres(Artist artist) throws GetDataException;

    /**
    * Returns list of genres of the specified label.
    * @param label label of the genre.
    * @return list of genres of the specified label.
    * @throws GetDataException if problems while getting data.
    */
    public List getGenres(Label label) throws GetDataException;

    /**
    * Returns list of all labels.
    * @return list of all labels.
    * @throws GetDataException if problems while getting data.
    */
    public List getLabels() 
            throws GetDataException;

    public List getLabels(int firstRow, int lastRow)
            throws GetDataException;
    
    public List getLabels(int id) throws GetDataException;
    
    /**
    * Returns list of all dates.
    * @return list of all dates.
    * @throws GetDataException if problems while getting data.
    */
    public List getDates() throws GetDataException;

    /**
    * Returns list of all artists.
    * @return list of all artists.
    * @throws GetDataException if problems while getting data.
    */
    public List getArtists(int firstRow, int lastRow) 
            throws GetDataException;
    
    /**
    * Returns list of all albums.
    * @return list of all albums.
    * @throws GetDataException if problems while getting data.
    */
    public List getAlbums(int firstRow, int lastRow) throws GetDataException;

    /**
     * Findes and returns list of albums by the specified params.
     * @param params parameters to find by.
     * @return list of albums by the specified params.
     * @throws GetDataException if problems while getting data.
     */ 
    public List findAlbums(Map params, int firstRow, int lastRow) 
            throws GetDataException;

    /**
     * Edits specified album. Replaces found (by id) album by specified.
     * 
     * @param album to edit/change.
     * @throws EditDataException if problems while editting data.
     */ 
    public void editAlbum(Album album) throws EditDataException;
    
    /**
     * Edits specified artist. Replaces found (by id) label by specified.
     * 
     * @param artist to edit/change.
     * @throws EditDataException if problems while editting data.
     */ 
    public void editArtist(Artist artist) throws EditDataException;

    /**
     * Edits specified label. Replaces found (by id) label by specified.
     * 
     * @param label to edit/change.
     * @throws EditDataException if problems while editting data.
     */ 
    public void editLabel(Label label) throws EditDataException;
    
    /**
     * Returns list of the <code>number</code> lastest albums.
     * @param number number of albums to return.
     * @return list of the <code>number</code> lastest albums.
     * @throws GetDataException if problems while getting data.
     */ 
    public List getLatestAlbums(int number) throws GetDataException;
    
    /**
     * Returns id of the artist by specified name.
     * @param name name of the artist.
     * @return id of the artist by specified name.
     * @throws GetDataException if problems while getting data.
     */ 
    public int findArtist(String name) throws GetDataException;
    
    /**
     * Returns id of the label by specified name.
     * @param name name of the label.
     * @return id of the label by specified name.
     * @throws GetDataException if problems while getting data.
     */
    public int findLabel(String name) throws GetDataException;
    
    /**
     * Returns maximal id of the artist in storage.
     * @return maximal id of the artist in storage.
     * @throws GetDataException if problems while getting data.
     */
    public int getArtistNumber() throws GetDataException;
    
    /**
     * Returns maximal id of the artist in storage by specified country.
     * @return maximal id of the artist in storage by specified country.
     * @throws GetDataException if problems while getting data.
     */
    public int getArtistNumber(String country) throws GetDataException;
    
    /**
     * Returns maximal id of the album in storage.
     * @return maximal id of the album in storage.
     * @throws GetDataException if problems while getting data.
     */
    public int getAlbumNumber() throws GetDataException;
    
    /**
     * Returns maximal id of the album in storage by specified date.
     * @return maximal id of the album in storage by specified date.
     * @throws GetDataException if problems while getting data.
     */
    public int getAlbumNumber(Date date) throws GetDataException;
    
    /**
     * Returns maximal id of the album in storage by specified genre.
     * @return maximal id of the album in storage by specified genre.
     * @throws GetDataException if problems while getting data.
     */
    public int getAlbumNumber(String genre) throws GetDataException;
    
    /**
     * Returns maximal id of the label in storage.
     * @return maximal id of the label in storage.
     * @throws GetDataException if problems while getting data.
     */
    public int getLabelNumber() throws GetDataException;
    
    /**
     * Returns the random album from the storage.
     * @return random album from the storage.
     * @throws GetDataException if problems while getting data.
     */ 
    public Album getRandomAlbum() throws GetDataException;
    
    /**
     * Removes album from the storage.
     * @param id album's id.
     * @throws EditDataException if problems while getting data.
     */ 
    public void deleteAlbum(int id) throws EditDataException;
    
    /**
     * Removes artist from the storage.
     * @param id artist's id.
     * @throws EditDataException if problems while getting data.
     */
    public void deleteArtist(int id) throws EditDataException;
    
    /**
     * Removes label from the storage.
     * @param id label's id.
     * @throws EditDataException if problems while getting data.
     */
    public void deleteLabel(int id) throws EditDataException;

    /**
     * Returns the path to the specified label in the hierarchy of labels.
     * @param id id of the label.
     * @return path to the specified label in the hierarchy of labels.
     * @throws GetDataException if problems while getting data.
     */ 
    public List getLabelPath(int id) throws GetDataException;        
}
