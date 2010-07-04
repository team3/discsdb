package ua.edu.sumdu.lab3.dao.oradao.operators;

import ua.edu.sumdu.lab3.exceptions.*;
import ua.edu.sumdu.lab3.model.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;

public class AlbumsOperator extends MainOperator {
    
    private static final String ADD_NEW_ALBUM =
            "INSERT INTO ALBUM VALUES (SQ_ALBUM.nextval, ?, ?, ?, ?, ?, ?, ?, ?)";
            
    private static final String EDIT_ALBUM =
            "UPDATE ALBUM SET alid = ?, name = ?, type = ?, release = ?, genre = ?, cover = ?, art = ?,review = ?, lbl = ? WHERE alid = ?";

    private static final String SELECT_ALBUM_BY_ID =
            "SELECT album.alid, album.name, album.type, album.release, album.genre, album.cover, album.review, NVL(album.art,-1), NVL(album.lbl,-1), NVL(artist.name,'unknown'), NVL(label.name,'unknown') FROM album FULL JOIN artist ON (album.art = artist.aid) FULL JOIN label ON (album.lbl = label.lid) WHERE album.alid = ?";

    private static final String SELECT_ALBUMS_BY_GENRE =
            "SELECT b.alid,b.name,b.type,b.release,b.genre,b.cover,b.review,b.art_id,b.lbl_id,b.artist_name,b.label_name FROM (SELECT a.*, rownum rnum FROM (SELECT album.alid, album.name, album.type, album.release, album.genre, album.cover, album.review, NVL(album.art,-1) as art_id, NVL(album.lbl,-1) as lbl_id, NVL(artist.name,'unknown') as artist_name, NVL(label.name,'unknown') as label_name from album full join artist on (album.art = artist.aid) full join label on (album.lbl = label.lid) WHERE INSTR(LOWER(album.genre),LOWER(?)) != 0 order by album.alid) a WHERE rownum <= ?) b where rnum >= ?";

    private static final String SELECT_ALBUMS_BY_NAME =
            "SELECT b.alid,b.name,b.type,b.release,b.genre,b.cover,b.review,b.art_id,b.lbl_id,b.artist_name,b.label_name FROM (SELECT a.*, rownum rnum FROM (SELECT album.alid, album.name, album.type, album.release, album.genre, album.cover, album.review, NVL(album.art,-1) as art_id, NVL(album.lbl,-1) as lbl_id ,NVL(artist.name,'unknown') as artist_name, NVL(label.name,'unknown') as label_name from album full join artist on (album.art = artist.aid) full join label on (album.lbl = label.lid) WHERE INSTR(LOWER(album.name),LOWER(?)) != 0 order by album.alid) a WHERE rownum <= ?) b where rnum >= ?";

    private static final String SELECT_ALBUMS_BY_DATE =
            "SELECT b.alid,b.name,b.type,b.release,b.genre,b.cover,b.review,b.art_id,b.lbl_id,b.artist_name,b.label_name FROM (SELECT a.*, rownum rnum FROM (SELECT album.alid, album.name, album.type, album.release, album.genre, album.cover, album.review, NVL(album.art,-1) as art_id, NVL(album.lbl,-1) as lbl_id ,NVL(artist.name,'unknown') as artist_name, NVL(label.name,'unknown') as label_name from album full join artist on (album.art = artist.aid) full join label on (album.lbl = label.lid) WHERE TO_CHAR(album.release,'YYYY') LIKE ? order by album.alid) a WHERE rownum <= ?) b where rnum >= ?";

    private static final String SELECT_ALBUMS_BY_ARTIST =
            "SELECT b.alid,b.name,b.type,b.release,b.genre,b.cover,b.review,b.art_id,b.lbl_id,b.artist_name,b.label_name FROM (SELECT a.*, rownum rnum FROM (SELECT album.alid, album.name, album.type, album.release, album.genre, album.cover, album.review, NVL(album.art,-1) as art_id, NVL(album.lbl,-1) as lbl_id ,NVL(artist.name,'unknown') as artist_name, NVL(label.name,'unknown') as label_name from album full join artist on (album.art = artist.aid) full join label on (album.lbl = label.lid) where album.art = ? order by album.alid) a WHERE rownum <= ?) b where rnum >= ?";

    private static final String SELECT_ALL_ALBUMS =
            "SELECT b.alid, b.name, b.type, b.release, b.genre, b.cover, b.review, b.ch_art ,b.ch_lbl, b.artist_name, b.label_name FROM ( SELECT a.*, rownum rnum FROM (SELECT album.alid, album.name,album.type,album.release,album.genre,album.cover,album.review,NVL(album.art,-1) as ch_art,NVL(album.lbl,-1) as ch_lbl ,NVL(artist.name,'unknown') as artist_name, NVL(label.name,'unknown') as label_name from album full join artist on (album.art = artist.aid) full join label on (album.lbl = label.lid) where album.alid is not null order by album.alid) a WHERE rownum <= ?) b where rnum >= ?";

    private static String FIND_ALBUMS =
            "SELECT alid FROM ALBUM WHERE ";

    private static final String LATEST_ALBUMS =
            "select a.alid, a.name, a.type, a.release, a.genre, a.cover, a.review, NVL(a.art,-1), NVL(a.lbl,-1), NVL(artist.name,'unknown'), NVL(label.name,'unknown') from (select * from album order by alid desc) a FULL JOIN artist ON (a.art = artist.aid) FULL JOIN label ON (a.lbl = label.lid) where a.alid IS NOT NULL AND ROWNUM <= ?";

    private static final String SELECT_ALBUMS_BY_LABEL =
            "SELECT b.alid,b.name,b.type,b.release,b.genre,b.cover,b.review,b.art_id,b.lbl_id,b.artist_name,b.label_name FROM (SELECT a.*, rownum rnum FROM (SELECT album.alid, album.name, album.type, album.release, album.genre, album.cover, album.review, NVL(album.art,-1) as art_id, NVL(album.lbl,-1) as lbl_id ,NVL(artist.name,'unknown') as artist_name, NVL(label.name,'unknown') as label_name from album full join artist on (album.art = artist.aid) full join label on (album.lbl = label.lid) where album.lbl = ? order by album.alid) a WHERE rownum <= ?) b where rnum >= ?";

    private static final String ALBUM_BY_DATE_MAX_ROW =
            "SELECT MAX(ROWNUM) from album  WHERE TO_CHAR(release,'YYYY') LIKE ?";

    private static final String ALBUM_BY_GENRE_MAX_ROW =
            "SELECT MAX(ROWNUM) from album  WHERE INSTR(LOWER(genre),LOWER(?)) != 0";

    private static final String ALBUM_RANDOM =
            "SELECT a.alid, a.name, a.type, a.release, a.genre, a.cover, a.review, NVL(a.art,-1), NVL(a.lbl,-1), NVL(artist.name,'unknown'), NVL(label.name,'unknown') FROM ( SELECT * FROM album ORDER BY dbms_random.value ) a FULL JOIN artist ON (a.art = artist.aid) FULL JOIN label ON (a.lbl = label.lid) WHERE rownum = 1";

    private static final String DELETE_ALBUM =
            "DELETE FROM album WHERE alid = ?";
            
    private static final String ALBUM_MAX_ROW =
            "SELECT MAX(ROWNUM) FROM album";
            
    /**
     * Adds new album to the specified storage.
     * @param album new instanse of the Album that should be added.
     * @throws OracleDataAccessObjectException if problems while adding the data.
     */
    public void addAlbum(Album album)
            throws OracleDataAccessObjectException {
        try {
            getConnection();
            statement = connection.prepareStatement(
                    ADD_NEW_ALBUM);
            java.util.Date alDate = (java.util.Date)album.getRelease();
            statement.setString(1,album.getName());
            statement.setString(2,album.getType());
            statement.setDate(3,
                    new java.sql.Date(alDate.getTime()));
            statement.setString(4,album.getGenre());
            statement.setString(5,album.getCover());
            statement.setString(6,album.getReview());
            statement.setInt(7,album.getArtist());
            statement.setInt(8,album.getLabel());
            connection.setAutoCommit(false);
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException exc) {
                throw new OracleDataAccessObjectException(exc);
            }
            throw new OracleDataAccessObjectException(e);
        } finally {
            closeConnection();;
        }
    }

    /**
     * Returns the album by the specified id.
     *
     * @param id id of the album that should be return.
     * @return album by the specified id.
     * @throws OracleDataAccessObjectException if problems while getting data.
     */
    public Album getAlbum(int id) throws OracleDataAccessObjectException {
        Album album = null;
        try {
            getConnection();

            statement = connection.prepareStatement(
                    SELECT_ALBUM_BY_ID);

            statement.setInt(1, id);

            ResultSet set = this.statement.executeQuery();
            if(set.next()) {
                album = fillAlbumBean(set,FULL_MODE);
            }
            set.close();
        }   catch (SQLException e) {
            throw new OracleDataAccessObjectException(e);
        } finally {
            closeConnection();;
        }
        return album;
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
            Album currAlbum;
            getConnection();

            statement = connection.prepareStatement(
                    SELECT_ALBUMS_BY_GENRE);

            statement.setString(1, genre);
            statement.setInt(2,lastRow);
            statement.setInt(3,firstRow);

            ResultSet set = this.statement.executeQuery();
            while(set.next()){
                currAlbum = fillAlbumBean(set,FULL_MODE);
                albums.add(currAlbum);
            }
            set.close();
        } catch (SQLException e){
            throw new OracleDataAccessObjectException(e);
        } finally {
             closeConnection();
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
            Album currAlbum;
            getConnection();

            statement = connection.prepareStatement(
                    SELECT_ALBUMS_BY_NAME);

            statement.setString(1, name);
            statement.setInt(2,lastRow);
            statement.setInt(3,firstRow);

            ResultSet set = this.statement.executeQuery();
            while(set.next()){
                currAlbum = fillAlbumBean(set,FULL_MODE);
                albums.add(currAlbum);
            }
            set.close();
        } catch (SQLException e){
            throw new OracleDataAccessObjectException(e);
        } finally {
             closeConnection();
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
        DateFormat df = new SimpleDateFormat("yyyy");
        List albums = null;
        try {
            albums = new LinkedList();
            Album currAlbum;

            getConnection();

            statement = connection.prepareStatement(
                    SELECT_ALBUMS_BY_DATE);

            statement.setString(1, df.format(date));
            statement.setInt(2,lastRow);
            statement.setInt(3,firstRow);

            ResultSet set = this.statement.executeQuery();

            while(set.next()){
                currAlbum = fillAlbumBean(set, FULL_MODE);
                albums.add(currAlbum);
            }
            set.close();
        } catch (SQLException e){
            throw new OracleDataAccessObjectException(e);
        } finally {
             closeConnection();
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
            Album currAlbum;
            getConnection();

            statement = connection.prepareStatement(
                    SELECT_ALBUMS_BY_ARTIST);

            statement.setInt(1, artist.getId());
            statement.setInt(2,lastRow);
            statement.setInt(3,firstRow);

            ResultSet set = this.statement.executeQuery();
            while(set.next()){
                currAlbum = fillAlbumBean(set,FULL_MODE);
                albums.add(currAlbum);
            }
            set.close();
        } catch (SQLException e){
            throw new OracleDataAccessObjectException(e);
        } finally {
             closeConnection();
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
            Album currAlbum;
            getConnection();

            statement = connection.prepareStatement(
                    SELECT_ALBUMS_BY_LABEL);

            statement.setInt(1, label.getId());
            statement.setInt(2,lastRow);
            statement.setInt(3,firstRow);

            ResultSet set = this.statement.executeQuery();
            while(set.next()){
                currAlbum = fillAlbumBean(set,FULL_MODE);
                albums.add(currAlbum);
            }
            set.close();
        } catch (SQLException e){
            throw new OracleDataAccessObjectException(e);
        } finally {
            closeConnection();
        }
        return albums;
    }

    /**
    * Returns list of all albums.
    * @return list of all albums.
    * @throws OracleDataAccessObjectException if problems while getting data.
    */
    public List getAlbums(int firstRow, int lastRow) throws OracleDataAccessObjectException {
        List albums = null;
        try {
            albums = new LinkedList();
            Album currAlbum;

            getConnection();

            statement = connection.prepareStatement(
                    SELECT_ALL_ALBUMS);
            statement.setInt(1,lastRow);
            statement.setInt(2,firstRow);

            ResultSet set = this.statement.executeQuery();
            while(set.next()){
                currAlbum = fillAlbumBean(set,FULL_MODE);
                albums.add(currAlbum);
            }
            set.close();
        } catch (SQLException e) {
            throw new OracleDataAccessObjectException(e);
        } finally {
            closeConnection();
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
            getConnection();

            Set s = params.entrySet();
            Iterator it = s.iterator();
            StringBuffer query = new StringBuffer();
            while(it.hasNext()) {

                Map.Entry m = (Map.Entry)it.next();

                String key = (String)m.getKey();

                String value = m.getValue().toString();
                if (!"".equals(value) && (value != null)) {
                    query.append(key).append(" = ").append(value).append(" ");
                    if (it.hasNext()) {
                        query.append(" OR ");
                    }
                }
            }
            FIND_ALBUMS += query.toString();

            statement =
                    connection.prepareStatement(FIND_ALBUMS);
            ResultSet set = this.statement.executeQuery();
            while(set.next()){
                albums.add(getAlbum(set.getInt(1)));
            }
            set.close();
        } catch (SQLException e) {
            throw new OracleDataAccessObjectException(e);
        } finally {
            closeConnection();
        }
        return albums;
    }

    /**
     * Edits specified album. Replaces found (by id) album by specified.
     *
     * @param album to edit/change.
     * @throws OracleDataAccessObjectException if problems while editting data.
     */
    public void editAlbum(Album album) 
            throws OracleDataAccessObjectException {
        try {
            Album alb = getAlbum(album.getId());

            getConnection();

            if (alb == null) {
                throw new OracleDataAccessObjectException(
                        "No album with specified id found");
            }

            statement = connection.prepareStatement(EDIT_ALBUM);

            statement.setInt(1, album.getId());
            statement.setString(2, album.getName());
            statement.setString(3, album.getType());
            statement.setDate(4,
                    new java.sql.Date(album.getRelease().getTime()));
            statement.setString(5, album.getGenre());
            statement.setString(6, album.getCover());
            statement.setInt(7, album.getArtist());
            statement.setString(8, album.getReview());
            statement.setInt(9, album.getLabel());
            statement.setInt(10, album.getId());
            connection.setAutoCommit(false);
            statement.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException exc) {
                throw new OracleDataAccessObjectException(exc);
            }
            throw new OracleDataAccessObjectException(e);
        } finally {
            try {
                connection.setAutoCommit(true);
                closeConnection();
            } catch (SQLException e) {
                throw new OracleDataAccessObjectException(e);
            }
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
            albums = new LinkedList();
            Album currAlbum;

            getConnection();

            statement = connection.prepareStatement(LATEST_ALBUMS);
            statement.setInt(1,number);

            ResultSet set = this.statement.executeQuery();
            while(set.next()){
                currAlbum = fillAlbumBean(set,FULL_MODE);
                albums.add(currAlbum);
            }
            set.close();
        } catch (SQLException e) {
            throw new OracleDataAccessObjectException(e);
        } finally {
            closeConnection();
        }
        return albums;
    }

    /**
     * Returns maximal id of the album in storage.
     * @return maximal id of the album in storage.
     * @throws OracleDataAccessObjectException if problems while getting data.
     */
    public int getAlbumNumber() 
            throws OracleDataAccessObjectException {
        int result = -1;
        try {
            getConnection();

            statement = connection.prepareStatement(ALBUM_MAX_ROW);

            ResultSet set = this.statement.executeQuery();
            set.next();

            result = set.getInt(1);
            set.close();
        } catch (SQLException e) {
            throw new OracleDataAccessObjectException(e);
        } finally {
            closeConnection();
        }
        return result;
    }

    /**
     * Returns maximal id of the album in storage by specified date.
     * @return maximal id of the album in storage by specified date.
     * @throws OracleDataAccessObjectException if problems while getting data.
     */
    public int getAlbumNumber(Date date) 
            throws OracleDataAccessObjectException {
        int result = -1;
        try {
            DateFormat df = new SimpleDateFormat("yyyy");
            getConnection();

            statement = connection.prepareStatement(ALBUM_BY_DATE_MAX_ROW);

            statement.setString(1,df.format(date));

            ResultSet set = this.statement.executeQuery();
            set.next();

            result = set.getInt(1);
            set.close();
        } catch (SQLException e) {
            throw new OracleDataAccessObjectException(e);
        } finally {
            closeConnection();
        }
        return result;
    }

    /**
     * Returns maximal id of the album in storage by specified genre.
     * @return maximal id of the album in storage by specified genre.
     * @throws OracleDataAccessObjectException if problems while getting data.
     */
    public int getAlbumNumber(String genre) 
            throws OracleDataAccessObjectException {
        int result = -1;
        try {
            getConnection();

            statement = connection.prepareStatement(ALBUM_BY_GENRE_MAX_ROW);

            statement.setString(1,genre);

            ResultSet set = this.statement.executeQuery();
            set.next();

            result = set.getInt(1);
            set.close();
        } catch (SQLException e) {
            throw new OracleDataAccessObjectException(e);
        } finally {
            closeConnection();
        }
        return result;
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
            album = new Album();

            getConnection();

            statement = connection.prepareStatement(
                    ALBUM_RANDOM);

            ResultSet set = this.statement.executeQuery();
            if(set.next()) {
                album = fillAlbumBean(set,FULL_MODE);
            }
            set.close();
        }   catch (SQLException e) {
            throw new OracleDataAccessObjectException(e);
        } finally {
             closeConnection();
        }
        return album;
    }

    /**
     * Removes album from the storage.
     * @param id album's id.
     * @throws OracleDataAccessObjectException if problems while editing data.
     */
    public void deleteAlbum(int id) 
            throws OracleDataAccessObjectException {
        try {
            getConnection();

            statement = connection.prepareStatement(DELETE_ALBUM);

            statement.setInt(1, id);
            connection.setAutoCommit(false);
            statement.executeUpdate();
            connection.commit();

        }   catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException exc) {
                throw new OracleDataAccessObjectException(exc);
            }
            throw new OracleDataAccessObjectException(e);
        } finally {
            try {
                connection.setAutoCommit(true);
                closeConnection();
            } catch (SQLException e) {
                throw new OracleDataAccessObjectException(e);
            } 
        }
    }
}
