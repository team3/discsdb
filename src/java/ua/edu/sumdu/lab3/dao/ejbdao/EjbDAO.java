/**
* This interface describes all posible operation with albums.
* @author Andrey Parhomenko
* @author Sergiy Stetsyun
* @version 2.0
* @date 11.06.2010
*/

package ua.edu.sumdu.lab3.dao.ejbdao;

import ua.edu.sumdu.lab3.exceptions.*;
import ua.edu.sumdu.lab3.ejbModule.label.*;
import ua.edu.sumdu.lab3.ejbModule.album.*;
import ua.edu.sumdu.lab3.ejbModule.artist.*;
import ua.edu.sumdu.lab3.ejbModule.Allocator;
import ua.edu.sumdu.lab3.dao.ejbdao.operators.*;
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

public class EjbDAO implements OperableDAO {
        
    private Logger log = null;

    private static EjbDAO instance = null;
    
    private AlbumsOperator albumsOperator = null;
    private ArtistsOperator artistsOperator = null;
    private LabelsOperator labelsOperator = null;
    private MainOperator mainOperator = null;
    
    protected EjbDAO() {
        log = Logger.getLogger(EjbDAO.class);
        
        albumsOperator = new AlbumsOperator();
        artistsOperator = new ArtistsOperator();
        labelsOperator = new LabelsOperator();
        mainOperator = new MainOperator();
    }

    /**
     * Returns new instance of this class if it does not exist.
     * @return new instance of this class.
     */
    public static EjbDAO getInstance() {
        if (instance == null) {
            instance = new EjbDAO();
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
        try {
            List genres = (List)Allocator.getArtistHomeItf().getGenres(artist);
            return genres;
        }catch (NamingException e) {
            throw new OracleDataAccessObjectException(e);
        } catch (RemoteException e){
            throw new OracleDataAccessObjectException(e);
        }
    }

    /**
    * Returns list of genres of the specified label.
    * @param label label of the genre.
    * @return list of genres of the specified label.
    * @throws OracleDataAccessObjectException if problems while getting data.
    */
    public List getGenres(Label label) 
            throws OracleDataAccessObjectException {
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
            
            albumHome.create(album);
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
                try {
            ArtistHome artHome = Allocator.getArtistHomeItf();
            artHome.create(artist.getName(), artist.getCountry(), artist.getInfo());
        } catch (CreateException e) {
            throw new OracleDataAccessObjectException(e);
        } catch (RemoteException e) {
            throw new OracleDataAccessObjectException(e);
        }catch (NamingException e) {
            throw new OracleDataAccessObjectException(e);
        }
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
            throw new OracleDataAccessObjectException(e);
        } catch (RemoteException e){
            throw new OracleDataAccessObjectException(e);
        } catch (NamingException e){
            throw new OracleDataAccessObjectException(e);
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
            throw new OracleDataAccessObjectException(e);
        } catch (RemoteException e){
            throw new OracleDataAccessObjectException(e);
        } catch (NamingException e){
            throw new OracleDataAccessObjectException(e);
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
        Artist artist = new Artist();
        try {
            ArtistHome artHome = Allocator.getArtistHomeItf();
            ArtistRemote artRemote = artHome.findByPrimaryKey(new Integer(id));
            artist.setId(artRemote.getId().intValue());
            artist.setName(artRemote.getName());
            artist.setCountry(artRemote.getCountry());
            artist.setInfo(artRemote.getInfo());
        } catch (FinderException e) {
            throw new OracleDataAccessObjectException(e);
        } catch (RemoteException e) {
            throw new OracleDataAccessObjectException(e);
        } catch (NamingException e) {
            throw new OracleDataAccessObjectException(e);
        }
        return artist;
    }

    /**
     * Returns list of albums of the specified genre.
     * @param genre genre of the album.
     * @return list of albums of the specified genre.
     * @throws OracleDataAccessObjectException if problems while getting data.
     */ 
    public List getAlbums(String genre, int firstRow, int lastRow) 
            throws OracleDataAccessObjectException {
                List albums = null;
        try {
            albums = new LinkedList();
            List aids = (List)Allocator.getAlbumHomeItf().findByGenre(
                    genre,
                    new Integer(firstRow), 
                    new Integer(lastRow));
            Iterator itr = aids.iterator();
            while(itr.hasNext()){
                albums.add(getAlbum(((AlbumRemote)itr.next()).getId()));
            }
        } catch (FinderException e){
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
                List albums = null;
        try {
            albums = new LinkedList();
            List aids = (List)Allocator.getAlbumHomeItf().findByName(
                    name,
                    new Integer(firstRow), 
                    new Integer(lastRow));
            Iterator itr = aids.iterator();
            while(itr.hasNext()){
                albums.add(getAlbum(((AlbumRemote)itr.next()).getId()));
            }
        } catch (FinderException e){
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
                List albums = null;
        try {
            albums = new LinkedList();
            List aids = (List)Allocator.getAlbumHomeItf().findByDate(
                    date,
                    new Integer(firstRow), 
                    new Integer(lastRow));
            Iterator itr = aids.iterator();
            while(itr.hasNext()){
                albums.add(getAlbum(((AlbumRemote)itr.next()).getId()));
            }
        } catch (FinderException e){
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
        List albums = null;
        try {
            albums = new LinkedList();
            List aids = (List)Allocator.getAlbumHomeItf().findByArtist(
                    new Integer(artist.getId()),
                    new Integer(firstRow), 
                    new Integer(lastRow));
            Iterator itr = aids.iterator();
            while(itr.hasNext()){
                albums.add(getAlbum(((AlbumRemote)itr.next()).getId()));
            }
        } catch (FinderException e){
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
        List albums = null;
        try {
            albums = new LinkedList();
            List aids = (List)Allocator.getAlbumHomeItf().findByLabel(
                    new Integer(label.getId()),
                    new Integer(firstRow), 
                    new Integer(lastRow));
            Iterator itr = aids.iterator();
            while(itr.hasNext()){
                albums.add(getAlbum(((AlbumRemote)itr.next()).getId()));
            }
        } catch (FinderException e){
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
        List artists = null;
        Artist currArtist = null;
        try {
            artists = new LinkedList();
            Iterator iter = Allocator.getArtistHomeItf().findArtistsByLabel(label).iterator();
            while(iter.hasNext()) {
                currArtist = artistTranslate((ArtistRemote)iter.next());
                artists.add(currArtist);
            }
        } catch (RemoteException e){
            throw new OracleDataAccessObjectException(e);
        } catch (NamingException e){
            throw new OracleDataAccessObjectException(e);
        } catch (FinderException e) {
            throw new OracleDataAccessObjectException(e);
        }
        return artists;
        
    }

    /**
    * Returns list of artists of the specified country.
    * @param country country of the artist.
    * @return list of artists of the specified country.
    * @throws OracleDataAccessObjectException if problems while getting data.
    */
    public List getArtists(String country, int firstRow, int lastRow) 
            throws OracleDataAccessObjectException {
        List artists = null;
        Artist currArtist = null;
        try {
            artists = new LinkedList();
            Iterator iter = Allocator.getArtistHomeItf().findArtistsByCountry(country,
                    new Integer(firstRow), new Integer(lastRow)).iterator();
            while(iter.hasNext()) {
                currArtist = artistTranslate((ArtistRemote)iter.next());
                artists.add(currArtist);
            }
            
        } catch (RemoteException e){
            throw new OracleDataAccessObjectException(e);
        } catch (NamingException e){
            throw new OracleDataAccessObjectException(e);
        } catch (FinderException e) {
            throw new OracleDataAccessObjectException(e);
        }
        return artists;
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
            throw new OracleDataAccessObjectException(e);
        } catch (NamingException e){
            throw new OracleDataAccessObjectException(e);
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
            throw new OracleDataAccessObjectException(e);
        } catch (NamingException e){
            throw new OracleDataAccessObjectException(e);
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
        List artists = null;
        Artist currArtist = null;
        try {
            artists = new LinkedList();
            Iterator iter = Allocator.getArtistHomeItf().findArtists(new Integer(firstRow), new Integer(lastRow)).iterator();
            while(iter.hasNext()) {
                currArtist = artistTranslate((ArtistRemote)iter.next());
                artists.add(currArtist);
            }
        } catch (RemoteException e){
            throw new OracleDataAccessObjectException(e);
        } catch (NamingException e){
            throw new OracleDataAccessObjectException(e);
        } catch (FinderException e) {
            throw new OracleDataAccessObjectException(e);
        }
        return artists;
    }
    
    /**
    * Returns list of all albums.
    * @return list of all albums.
    * @throws OracleDataAccessObjectException if problems while getting data.
    */
    public List getAlbums(int firstRow, int lastRow) 
            throws OracleDataAccessObjectException {
        
        List albums = null;
        try {
            albums = new LinkedList();
            List aids = (List)Allocator.getAlbumHomeItf().findAll(
                    new Integer(firstRow), 
                    new Integer(lastRow));
            Iterator itr = aids.iterator();
            while(itr.hasNext()){
                albums.add(getAlbum(((AlbumRemote)itr.next()).getId()));
            }
        } catch (FinderException e){
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
        List albums = null;
        try {
            albums = new LinkedList();
            List aids = (List)Allocator.getAlbumHomeItf().findByParams(
                    params,
                    new Integer(firstRow), 
                    new Integer(lastRow));
            Iterator itr = aids.iterator();
            while(itr.hasNext()){
                albums.add(getAlbum(((AlbumRemote)itr.next()).getId()));
            }
        } catch (FinderException e){
            throw new OracleDataAccessObjectException(e);
        } catch (RemoteException e){
            throw new OracleDataAccessObjectException(e);
        } catch (NamingException e){
            throw new OracleDataAccessObjectException(e);
        }
        return albums;

    }

   
   /**
    * Edites specified album.
    * 
    */ 
   public void editAlbum(Album album) 
            throws OracleDataAccessObjectException {
        try {
        
            AlbumHome albumHome = Allocator.getAlbumHomeItf();
            AlbumRemote albumRemote = albumHome.findByPrimaryKey(
                    new Integer(album.getId()));
            albumRemote.setName(album.getName());
            albumRemote.setType(album.getType());
            albumRemote.setRelease(album.getRelease());
            albumRemote.setGenre(album.getGenre());
            albumRemote.setCover(album.getCover());
            albumRemote.setArtist(new Integer(album.getArtist()));
            albumRemote.setLabel(new Integer(album.getLabel()));
            albumRemote.setArtistName(album.getArtistName());
            albumRemote.setLabelName(album.getLabelName());
            albumRemote.setReview(album.getReview());

        } catch (RemoteException e){
            throw new OracleDataAccessObjectException(e);
        } catch (NamingException e){
            throw new OracleDataAccessObjectException(e);
        } catch (FinderException e){
            throw new OracleDataAccessObjectException(e);
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
        try {
            ArtistHome artHome = Allocator.getArtistHomeItf();
            ArtistRemote artRemote = artHome.findByPrimaryKey(new Integer(artist.getId()));
            artRemote.setName(artist.getName());
            artRemote.setCountry(artist.getCountry());
            artRemote.setInfo(artist.getInfo());
        } catch (RemoteException e){
            throw new OracleDataAccessObjectException(e);
        } catch (NamingException e){
            throw new OracleDataAccessObjectException(e);
        } catch (FinderException e){
            throw new OracleDataAccessObjectException(e);
        }
    }

    /**
     * Edits specified label. Replaces found (by id) label by specified.
     * 
     * @param label to edit/change.
     * @throws OracleDataAccessObjectException if problems while editting data.
     */ 
    public void editLabel(Label label) 
            throws OracleDataAccessObjectException {
        try {
            LabelHome lblHome = Allocator.getLabelHomeItf();
            LabelRemote lblRemote = lblHome.findByPrimaryKey(new Integer(label.getId()));
            lblRemote.setMajor(new Integer(label.getMajor()));
            lblRemote.setName(label.getName());
            lblRemote.setInfo(label.getInfo());
            lblRemote.setLogo(label.getLogo());
            lblRemote.setMajorName(label.getMajorName());
        } catch(RemoteException e) {
             throw new OracleDataAccessObjectException(e);
        } catch (NamingException e){
            throw new OracleDataAccessObjectException(e);
        } catch (FinderException e){
            throw new OracleDataAccessObjectException(e);
        }
    }
    
    public void editLabel(
            int id, int major, String name, 
            String info, String logo, String majorName) 
            throws OracleDataAccessObjectException {
        try {
            LabelHome labelHome = Allocator.getLabelHomeItf();
            LabelRemote labelRemote = labelHome.findByPrimaryKey(new Integer(id));
            labelRemote.setMajor(new Integer(major));
            labelRemote.setName(name);
            labelRemote.setInfo(info);
            labelRemote.setLogo(logo);
            labelRemote.setMajorName(majorName);
        } catch (RemoteException e){
            throw new OracleDataAccessObjectException(e);
        } catch (NamingException e){
            throw new OracleDataAccessObjectException(e);
        } catch (FinderException e){
            throw new OracleDataAccessObjectException(e);
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
        List albums = null;
        try {
            albums = (List)Allocator.getAlbumHomeItf().findLatest(
                    new Integer(number));
        } catch (FinderException e){
            throw new OracleDataAccessObjectException(e);
        } catch (RemoteException e){
            throw new OracleDataAccessObjectException(e);
        } catch (NamingException e){
            throw new OracleDataAccessObjectException(e);
        }
        return albums;
    }
    
    /**
     * Returns id of the artist by specified name.
     * @param name name of the artist.
     * @return id of the artist by specified name.
     * @throws OracleDataAccessObjectException if problems while getting data.
     */ 
    public int findArtist(String name) 
            throws OracleDataAccessObjectException {
        return 1;
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
        int number = 0;
        try {
            number = Allocator.getArtistHomeItf().getArtistNumber().intValue();
        } catch (RemoteException e){
            throw new OracleDataAccessObjectException(e);
        } catch (NamingException e){
            throw new OracleDataAccessObjectException(e);
        }
        return number;
    }
    
    /**
     * Returns maximal id of the artist in storage by specified country.
     * @return maximal id of the artist in storage by specified country.
     * @throws OracleDataAccessObjectException if problems while getting data.
     */
    public int getArtistNumber(String country) 
            throws OracleDataAccessObjectException {
        int number = 0;
        try {
            number = Allocator.getArtistHomeItf().getArtistNumber(country).intValue();
        } catch (RemoteException e){
            throw new OracleDataAccessObjectException(e);
        } catch (NamingException e){
            throw new OracleDataAccessObjectException(e);
        }
        return number;
    }
    
    /**
     * Returns maximal id of the album in storage.
     * @return maximal id of the album in storage.
     * @throws OracleDataAccessObjectException if problems while getting data.
     */
    public int getAlbumNumber() 
            throws OracleDataAccessObjectException {
        int number = 0;
        try {
            number = Allocator.getAlbumHomeItf().getAlbumNumber().intValue();
        } catch (RemoteException e){
            throw new OracleDataAccessObjectException(e);
        } catch (NamingException e){
            throw new OracleDataAccessObjectException(e);
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
        int number = 0;
        try {
            number = Allocator.getAlbumHomeItf().getAlbumNumber(
                    date).intValue();
        } catch (RemoteException e){
            throw new OracleDataAccessObjectException(e);
        } catch (NamingException e){
            throw new OracleDataAccessObjectException(e);
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
        int number = 0;
        try {
            number = Allocator.getAlbumHomeItf().getAlbumNumber(
                    genre).intValue();
        } catch (RemoteException e){
            throw new OracleDataAccessObjectException(e);
        } catch (NamingException e){
            throw new OracleDataAccessObjectException(e);
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
            albumHome.remove(new Integer(id));
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
        try {
            ArtistHome artHome = Allocator.getArtistHomeItf();
            artHome.remove(new Integer(id));
        } catch (RemoteException e){
            throw new OracleDataAccessObjectException(e);
        } catch (NamingException e){
            throw new OracleDataAccessObjectException(e);
        }    
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
            labelHome.remove(new Integer(id));
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
            throw new OracleDataAccessObjectException(e);
        } catch (NamingException e){
            throw new OracleDataAccessObjectException(e);
        }
        return labels;
    }

    private Artist artistTranslate(ArtistRemote aRemote) throws RemoteException {
        Artist art = new Artist();
        art.setId(aRemote.getId().intValue());
        art.setName(aRemote.getName());
        art.setCountry(aRemote.getCountry());
        art.setInfo(aRemote.getInfo());
        return art;
    }
}
