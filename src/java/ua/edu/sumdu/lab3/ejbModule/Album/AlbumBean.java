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
    public Integer ejbCreate(Album album)
            throws CreateException, RemoteException {
        int id = 0;
        
        try {
            id = albumsOperator.addAlbum(album);
            
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
            throw new CreateException(e.getMessage());
        }
        return new Integer(id);
    }
    
    /**
    * 
    * 
    * 
    */
    
    public void ejbPostCreate(Album album) 
            throws CreateException, RemoteException {
        
    }
    
    /**
    * Finds bean by primary key
    * @param id id of the album.
    * @return primary key of the album.
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
    * Sets entity context.
    * @param ectx entity context.
    * 
    */ 
    
    public void setEntityContext(EntityContext ectx){
        this.context = ectx;
    }
    
    /**
    * Unsets entity context.
    * 
    */ 
    
    public void unsetEntityContext(){
        this.context = null;
    }
    
    /**
    * Removes current instanse of the bean;
    * @throws RemoteException
    * @throws RemoveException
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
    * Realization of the loading the bean;
    * @throws EJBException
    * @throws RemoteException
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
    * Realization of storing the bean.
    * @throws EJBException.
    * @throws RemoteException.
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
    
    public Collection ejbFindAll(Integer firstRow, Integer lastRow) 
            throws FinderException {
        Collection albums = null;
        Collection remotes = null;
        try {
            remotes = new ArrayList();
            
            albums = albumsOperator.getAlbums(
                    firstRow.intValue(), 
                    lastRow.intValue()
                    );
            Iterator itr = albums.iterator();
            while(itr.hasNext()){
                Album album = (Album)itr.next();
                remotes.add(new Integer(album.getId()));
            }
        } catch (OracleDataAccessObjectException e){
            throw new FinderException(e.getMessage());
        }
        return remotes;
    }

    public Collection ejbFindLatest(Integer number) 
            throws FinderException {
        Collection albums = null;
        Collection remotes = null;
        try {
            remotes = new ArrayList();
            
            albums = albumsOperator.getLatestAlbums(number.intValue());
            
            Iterator itr = albums.iterator();
            System.out.println("Latest");
            while(itr.hasNext()){
                Album album = (Album)itr.next();
                System.out.println(album.getId());
                remotes.add(new Integer(album.getId()));
            }
        } catch (OracleDataAccessObjectException e){
            throw new FinderException(e.getMessage());
        }
        return remotes;
    }

    /**
    * Findes and returns list of albums by the specified params.
    * @param params parameters to find by.
     * @return list of albums by the specified params.
     * @throws EJBException.
    */ 
    public Collection ejbFindByParams(Map params, 
            Integer firstRow, Integer lastRow) 
            throws FinderException {
        
        Collection albums = null;
        Collection remotes = null;
        
        try {
            remotes = new ArrayList();
            
            albums = albumsOperator.findAlbums(
                    params,
                    firstRow.intValue(), 
                    lastRow.intValue());
            Iterator itr = albums.iterator();
            while(itr.hasNext()){
                Album album = (Album)itr.next();
                remotes.add(new Integer(album.getId()));
            }
        } catch (OracleDataAccessObjectException e){
            throw new FinderException(e.getMessage());
        }
        return remotes;
    }
    
    /**
    * Returns maximal id of the album in storage.
     * @return maximal id of the album in storage.
     * @throws EJBException.
     */ 
    public Integer ejbHomeGetAlbumNumber() throws EJBException {
        int number = 0;
        try {
            number = albumsOperator.getAlbumNumber();
        } catch (OracleDataAccessObjectException e){
            throw new EJBException(e.getMessage());
        }
        return new Integer(number);
    }

    /**
    * Returns maximal id of the album in storage by specified date.
     * @return maximal id of the album in storage by specified date.
     * @throws EJBException.
     */ 
    public Integer ejbHomeGetAlbumNumber(Date date) throws EJBException {
        int number = 0;
        try {
            number = albumsOperator.getAlbumNumber(date);
        } catch (OracleDataAccessObjectException e){
            throw new EJBException(e.getMessage());
        }
        return new Integer(number);
    }
    
    /**
    * Returns maximal id of the album in storage by specified genre.
     * @return maximal id of the album in storage by specified genre.
     * @throws EJBException.
     */ 
    public Integer ejbHomeGetAlbumNumber(String genre) throws EJBException {
        int number = 0;
        try {
            number = albumsOperator.getAlbumNumber(genre);
        } catch (OracleDataAccessObjectException e){
            throw new EJBException(e.getMessage());
        }
        return new Integer(number);
    }
    
    /**
     * Returns the random album from the storage.
     * @return random album from the storage.
     * @throws EJBException
     */ 
    public Album ejbHomeGetRandom() throws EJBException {
        Album album = null;
        try {
            album = albumsOperator.getRandomAlbum();
        } catch (OracleDataAccessObjectException e){
            throw new EJBException(e.getMessage());
        }
        return album;
    }
    
    /**
     * Returns list of albums of the specified genre.
     * @param genre genre of the album.
     * @param fisrtRow from.
     * @param lastRow to.
     * @return list of albums of the specified genre.
     * @throws EJBException
     */ 
    public Collection ejbFindByGenre(String genre, Integer firstRow, 
            Integer lastRow)
            throws FinderException {
        Collection albums = null;
        Collection remotes = null;
        
        try {
            remotes = new ArrayList();
            albums = albumsOperator.getAlbumsByGenre(
                    genre,
                    firstRow.intValue(), 
                    lastRow.intValue());
            Iterator itr = albums.iterator();
            while(itr.hasNext()){
                Album album = (Album)itr.next();
                remotes.add(new Integer(album.getId()));
            }
        } catch (OracleDataAccessObjectException e){
            throw new FinderException(e.getMessage());
        }
        return remotes;
    }
    
    /**
     * Returns list of albums of the specified name.
     * @param name name of the album.
     * @param fisrtRow from.
     * @param lastRow to.
     * @return list of albums of the specified name.
     * @throws EJBException
     */
    public Collection ejbFindByName(String name, Integer firstRow,
            Integer lastRow)
            throws FinderException {
        Collection albums = null;
        Collection remotes = null;
        
        try {
            remotes = new ArrayList();
            albums = albumsOperator.getAlbumsByName(
                    name, 
                    firstRow.intValue(),
                    lastRow.intValue());
                    
            Iterator itr = albums.iterator();
            while(itr.hasNext()){
                Album album = (Album)itr.next();
                remotes.add(new Integer(album.getId()));
            }
        } catch (OracleDataAccessObjectException e){
            throw new EJBException(e.getMessage());
        }
        return remotes;
    }

    /**
     * Returns list of albums of the specified date.
     * @param date date of the album.
     * @param fisrtRow from.
     * @param lastRow to.
     * @return list of albums of the specified date.
     * @throws EJBException
     */
    public Collection ejbFindByDate(Date date, Integer firstRow,
            Integer lastRow)
            throws FinderException {
        Collection albums = null;
        Collection remotes = null;
        
        try {
            remotes = new ArrayList();
            albums = albumsOperator.getAlbumsByDate(
                    date, 
                    firstRow.intValue(),
                    lastRow.intValue());
            Iterator itr = albums.iterator();
            while(itr.hasNext()){
                Album album = (Album)itr.next();
                remotes.add(new Integer(album.getId()));
            }
        } catch (OracleDataAccessObjectException e){
            throw new EJBException(e.getMessage());
        }
        return remotes;
    }
    
    /**
     * Returns list of albums of the specified artist id.
     * @param aid artist of the album.
     * @param fisrtRow from.
     * @param lastRow to.
     * @return list of albums of the specified artist id.
     * @throws EJBException
     */	
    public Collection ejbFindByArtist(Integer aid, Integer firstRow,
            Integer lastRow)
            throws FinderException {
        Collection albums = null;
        Collection remotes = null;
        
        try {
            remotes = new ArrayList();
            albums = albumsOperator.getAlbumsByArtist(
                    aid.intValue(), 
                    firstRow.intValue(),
                    lastRow.intValue());
                    
            Iterator itr = albums.iterator();
            while(itr.hasNext()){
                Album album = (Album)itr.next();
                remotes.add(new Integer(album.getId()));
            }
        } catch (OracleDataAccessObjectException e){
            throw new EJBException(e.getMessage());
        }
        return remotes;
    }

    /**
     * Returns list of albums of the specified label id.
     * @param lid label of the album.
     * @param fisrtRow from.
     * @param lastRow to.
     * @return list of albums of the specified label id.
     * @throws EJBException
     */	
    public Collection ejbFindByLabel(Integer lid, Integer firstRow,
            Integer lastRow)
            throws FinderException {
        Collection albums = null;
        Collection remotes = null;
        
        try {
            remotes = new ArrayList();
            albums = albumsOperator.getAlbumsByLabel(
                    lid.intValue(), 
                    firstRow.intValue(),
                    lastRow.intValue());
                    
            Iterator itr = albums.iterator();
            while(itr.hasNext()){
                Album album = (Album)itr.next();
                remotes.add(new Integer(album.getId()));
            }
        } catch (OracleDataAccessObjectException e){
            throw new EJBException(e.getMessage());
        }
        return remotes;
    }
        
    public Collection ejbHomeGetGenresByArtist(Integer aid)
            throws EJBException {
        Collection genres = null;
        try {
            genres = albumsOperator.getGenresByArtist(aid.intValue());
        } catch (OracleDataAccessObjectException e){
            throw new EJBException(e.getMessage());
        }
        return genres;
    }
    
    public Collection ejbHomeGetGenresByLabel(Integer lid)
            throws EJBException {
        Collection genres = null;
        try {
            genres = albumsOperator.getGenresByLabel(lid.intValue());
        } catch (OracleDataAccessObjectException e){
            throw new EJBException(e.getMessage());
        }
        return genres;
    }
    
    public Collection ejbHomeGetDates()
            throws EJBException {
        Collection dates = null;
        try {
            dates = albumsOperator.getDates();
        } catch (OracleDataAccessObjectException e){
            throw new EJBException(e.getMessage());
        }
        return dates;
    }
    
    private int id;
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
    
    public void setId(Integer id) {
        this.id = id.intValue();
        this.needToStore = true;
    }

    public void setName(String name) {
        this.name = name;
        this.needToStore = true;
    }

    public void setType(String type) {
        this.type = type;
        this.needToStore = true;
    }

    public void setRelease(Date release) {
        this.release = release;
        this.needToStore = true;
    }

    public void setGenre(String genre) {
        this.genre = genre;
        this.needToStore = true;
    }

    public void setCover(String cover) {
        this.cover = cover;
        this.needToStore = true;
    }

    public void setArtist(Integer artist) {
        this.artist = artist.intValue();
        this.needToStore = true;
    }

    public void setLabel(Integer label) {
        this.label = label.intValue();
        this.needToStore = true;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
        this.needToStore = true;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
        this.needToStore = true;
    }
    
    public void setReview(String review) {
        this.review = review;
        this.needToStore = true;
    }
}
