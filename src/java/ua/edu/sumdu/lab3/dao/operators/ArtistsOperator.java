package ua.edu.sumdu.lab3.dao.operators;

import ua.edu.sumdu.lab3.exceptions.*;
import ua.edu.sumdu.lab3.model.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;

public class ArtistsOperator extends MainOperator {
    
    private static final String ADD_NEW_ARTIST = 
            "INSERT INTO ARTIST VALUES (?, ?, ?, ?)";
    
    private static final String ID_GEN =
            "SELECT SQ_ARTIST.nextval FROM dual";
    
    private static final String EDIT_ARTIST = 
            "UPDATE ARTIST SET aid = ?, name = ?, country = ?, info = ? where aid = ?";
    
    private static final String DELETE_ARTIST =
            "DELETE FROM artist WHERE aid = ?";
    
    private static final String FIND_ARTIST = 
            "SELECT aid FROM artist WHERE (INSTR(LOWER(name), LOWER(?)) != 0)";
    
    private static final String SELECT_ARTIST_BY_ID = 
            "SELECT * FROM ARTIST WHERE aid = ?";
    
    private static final String SELECT_ARTISTS_BY_LABEL = 
            "SELECT DISTINCT artist.aid,artist.name,artist.country,artist.info FROM artist JOIN album ON(artist.aid = album.art) WHERE album.lbl = ?";
            
    private static final String SELECT_ARTISTS_BY_COUNTRY = 
            "SELECT * FROM (SELECT a.*, rownum rnum FROM(SELECT * FROM artist WHERE country like ?) a WHERE ROWNUM <=?) WHERE rnum >=?";
    
    private static final String ARTIST_MAX_ROW =
            "SELECT MAX(ROWNUM) FROM artist";
    
    private static final String ARTIST_BY_COUNTRY_MAX_ROW =
            "SELECT MAX(ROWNUM) FROM artist WHERE country LIKE ?";
                    
    private static final String SELECT_ALL_ARTISTS = 
            "SELECT c.aid,c.name,c.country,c.info FROM (SELECT a.*, rownum rnum FROM (SELECT * FROM artist ORDER BY aid) a WHERE rownum <= ?) c where rnum >= ?";
            
    private static final String SELECT_GENRES_BY_ARTIST =
            "SELECT DISTINCT genre FROM ALBUM WHERE art = ?";
    
    private int getNewId() throws OracleDataAccessObjectException {
        try {
            
            getConnection();
            
            statement = connection.prepareStatement(
                    ID_GEN);
            ResultSet set = statement.executeQuery();
            int id = -1;
            if(set.next()) {
                id = set.getInt(1);
            } else {
                throw new OracleDataAccessObjectException("Id gen error");
            }
            return id;
        } catch (SQLException e) {
            throw new OracleDataAccessObjectException(e);
        }
    }
    
    /**
     * Adds new artist to the specified storage.
     * @param album new instanse of the Artist that should be added.
     * @throws OracleDataAccessObjectException if problems while adding the data.
     */ 
    public int addArtist(Artist artist) 
            throws OracleDataAccessObjectException {
        try {
            
            int id = getNewId();
            
            getConnection();
        
            statement = connection.prepareStatement(
                    ADD_NEW_ARTIST);
            statement.setInt(1, id);
            statement.setString(2, artist.getName());
            statement.setString(3, artist.getCountry());
            statement.setString(4, artist.getInfo());
            statement.executeUpdate();
            return id;
        } catch (SQLException e) {
            throw new OracleDataAccessObjectException(e);
        } finally {
            closeConnection();
        }
    }

    /**
     * Removes artist from the storage.
     * @param id artist's id.
     * @throws OracleDataAccessObjectException if problems while editing data.
     */
    public void deleteArtist(int id) 
            throws OracleDataAccessObjectException {
        try {
            getConnection();
            
            statement = connection.prepareStatement(DELETE_ARTIST);
            
            statement.setInt(1, id);
            statement.executeUpdate();
            
        }   catch (SQLException e) {
            throw new OracleDataAccessObjectException(e);
        } finally {
            closeConnection();
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
            Artist art = getArtist(artist.getId());
            
            getConnection();
            
            if (art == null) {
                throw new OracleDataAccessObjectException(
                        "No artist with specified id found");
            } 
            
            statement = connection.prepareStatement(EDIT_ARTIST);
            
            statement.setInt(1, artist.getId());
            statement.setString(2, artist.getName());
            statement.setString(3, artist.getCountry());
            statement.setString(4, artist.getInfo());
            statement.setInt(5, artist.getId());
            statement.executeUpdate();
            
        } catch (SQLException e) {
            throw new OracleDataAccessObjectException(e);
        } finally {
            closeConnection();
        }
    }

    /**
     * Returns id of the artist by specified name.
     * @param name name of the artist.
     * @return id of the artist by specified name.
     * @throws OracleDataAccessObjectException if problems while getting data.
     */ 
    public int findArtist(String name) 
            throws OracleDataAccessObjectException {
        int aid = -1;
        try {
            getConnection();
            
            statement = connection.prepareStatement(FIND_ARTIST);
            statement.setString(1, name);
            
            ResultSet set = statement.executeQuery();
            while(set.next()){
                aid = set.getInt(1);
            }
            set.close();
        } catch (SQLException e) {
            throw new OracleDataAccessObjectException(e);
        } finally {
            closeConnection();
        }
        return aid;
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
        Artist artist = null;
        try {
            getConnection();
            
            statement = connection.prepareStatement(
                    SELECT_ARTIST_BY_ID);
            
            statement.setInt(1, id);
            
            ResultSet set = statement.executeQuery();
            if(set.next()) {
                artist = fillArtistBean(set,FULL_MODE);
            }
            set.close();
        } catch (SQLException e) {
            throw new OracleDataAccessObjectException(e);
        } finally {
             closeConnection();
        }
        return artist;    
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
        try {
            artists = new ArrayList();
            Artist currArtist;
            
            getConnection();
            
            statement = connection.prepareStatement(
                    SELECT_ARTISTS_BY_LABEL);
            
            statement.setInt(1,label.getId());
            
            ResultSet set = statement.executeQuery();
            while(set.next()){
                currArtist = fillArtistBean(set,FULL_MODE);
                artists.add(currArtist);
            }
            set.close();
        } catch (SQLException e){
            throw new OracleDataAccessObjectException(e);
        } finally {
             closeConnection();
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
        try {
            artists = new LinkedList();
            Artist currArtist;
            getConnection();
            
            statement = connection.prepareStatement(
                    SELECT_ARTISTS_BY_COUNTRY);
            
            statement.setString(1, country);
            statement.setInt(2,lastRow);
            statement.setInt(3,firstRow);
            
            ResultSet set = statement.executeQuery();
            while(set.next()){
                currArtist = fillArtistBean(set, FULL_MODE);
                artists.add(currArtist);
            }
            set.close();
        } catch (SQLException e){
            throw new OracleDataAccessObjectException(e);
        } finally {
            closeConnection();
        }
        return artists;
    }

    /**
     * Returns maximal id of the artist in storage.
     * @return maximal id of the artist in storage.
     * @throws OracleDataAccessObjectException if problems while getting data.
     */
    public int getArtistNumber() 
            throws OracleDataAccessObjectException {
         int result = -1;
        try {
            
            getConnection();
            
            statement = connection.prepareStatement(ARTIST_MAX_ROW);
            
            ResultSet set = statement.executeQuery();
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
     * Returns maximal id of the artist in storage by specified country.
     * @return maximal id of the artist in storage by specified country.
     * @throws OracleDataAccessObjectException if problems while getting data.
     */
    public int getArtistNumber(String country) 
            throws OracleDataAccessObjectException {
         int result = -1;
        try {
            getConnection();
    
            statement = connection.prepareStatement(ARTIST_BY_COUNTRY_MAX_ROW);
            
            statement.setString(1,country);
            
            ResultSet set = statement.executeQuery();
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
    * Returns list of all artists.
    * @return list of all artists.
    * @throws OracleDataAccessObjectException if problems while getting data.
    */
    public List getArtists(int firstRow, int lastRow) 
            throws OracleDataAccessObjectException {
        List artists = null;
        try {
            artists = new LinkedList();
            Artist currArtist;
            
            getConnection();
            
            statement = connection.prepareStatement(
                    SELECT_ALL_ARTISTS);
            statement.setInt(1,lastRow);
            statement.setInt(2,firstRow);
            
            ResultSet set = statement.executeQuery();
            while(set.next()){
                currArtist = fillArtistBean(set,FULL_MODE);
                artists.add(currArtist);
            }
            set.close();
        } catch (SQLException e) {
            throw new OracleDataAccessObjectException(e);
        } finally {
             closeConnection();
        }
        return artists; 
    }
    
    public List getGenres(Artist artist) 
            throws OracleDataAccessObjectException {
        List genres = null;
        try {
            genres = new LinkedList();

            this.getConnection();

            this.statement = this.connection.prepareStatement(
                    SELECT_GENRES_BY_ARTIST);

            this.statement.setInt(1, artist.getId());

            ResultSet set = this.statement.executeQuery();;
            while(set.next()){
                genres.add(set.getString(1));
            }
            set.close();
        } catch (SQLException e){
            throw new OracleDataAccessObjectException(e);
        } finally {
            closeConnection();
        }
        return genres;
    }
}
