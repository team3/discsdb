package ua.edu.sumdu.lab3.model.operators;

import ua.edu.sumdu.lab3.model.exceptions.*;
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
            "INSERT INTO ARTIST VALUES (SQ_ARTIST.nextval, ?, ?, ?)";
    
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
    
    /**
     * Adds new artist to the specified storage.
     * @param album new instanse of the Artist that should be added.
     * @throws OracleDataAccessObjectException if problems while adding the data.
     */ 
    public void addArtist(Artist artist) 
            throws OracleDataAccessObjectException {
        try {
            getConnection();
            
            if (connection == null) {
                throw new OracleDataAccessObjectException("Connection is not created");
            }
            
            statement = connection.prepareStatement(
                    ADD_NEW_ARTIST);
            
            statement.setString(1, artist.getName());
            statement.setString(2, artist.getCountry());
            statement.setString(3, artist.getInfo());
            connection.setAutoCommit(false);
            executeUpdateQuery();
            connection.commit();
            
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException exc) {
                throw new OracleDataAccessObjectException(e);
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
     * Removes artist from the storage.
     * @param id artist's id.
     * @throws OracleDataAccessObjectException if problems while editing data.
     */
    public void deleteArtist(int id) 
            throws OracleDataAccessObjectException {
        try {
            
            getConnection();
            
            if (connection == null){
                throw new OracleDataAccessObjectException("Connection is not created");
            }
            
            statement = connection.prepareStatement(DELETE_ARTIST);
            
            statement.setInt(1, id);
            connection.setAutoCommit(false);
            executeUpdateQuery();
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
            
            if (connection == null){
                throw new OracleDataAccessObjectException("Connection is not created");
            }
            
            if (art == null) {
                throw new OracleDataAccessObjectException("No artist with specified id found");
            } 
            
            statement = connection.prepareStatement(EDIT_ARTIST);
            
            statement.setInt(1, artist.getId());
            statement.setString(2, artist.getName());
            statement.setString(3, artist.getCountry());
            statement.setString(4, artist.getInfo());
            statement.setInt(5, artist.getId());
            connection.setAutoCommit(false);
            executeUpdateQuery();
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
            
            if (connection == null){
                throw new OracleDataAccessObjectException("Connection is not created");
            }
            
            statement = connection.prepareStatement(FIND_ARTIST);
            statement.setString(1, name);
            
            ResultSet set = executeResultQuery();
            while(set.next()){
                aid = set.getInt(1);
            }
            
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
            
            if (connection == null){
                throw new OracleDataAccessObjectException("Connection is not created");
            }
            
            statement = connection.prepareStatement(
                    SELECT_ARTIST_BY_ID);
            
            statement.setInt(1, id);
            
            ResultSet set = executeResultQuery();
            if(set.next()) {
                artist = (Artist)fillBean(set,ARTIST,FULL_MODE);
            }
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
            
            if (connection == null){
                throw new OracleDataAccessObjectException("Connection is not created");
            }
            
            statement = connection.prepareStatement(
                    SELECT_ARTISTS_BY_LABEL);
            
            statement.setInt(1,label.getId());
            
            ResultSet set = executeResultQuery();
            while(set.next()){
                currArtist = (Artist)fillBean(set,ARTIST,FULL_MODE);
                artists.add(currArtist);
            }
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
            
            if (connection == null){
                throw new OracleDataAccessObjectException("Connection is not created");
            }
            
            statement = connection.prepareStatement(
                    SELECT_ARTISTS_BY_COUNTRY);
            
            statement.setString(1, country);
            statement.setInt(2,lastRow);
            statement.setInt(3,firstRow);
            
            ResultSet set = executeResultQuery();
            while(set.next()){
                currArtist = (Artist)fillBean(set, ARTIST, FULL_MODE);
                artists.add(currArtist);
            }
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
            
            if (connection == null){
                throw new OracleDataAccessObjectException("Connection is not created");
            }
            
            statement = connection.prepareStatement(ARTIST_MAX_ROW);
            
            ResultSet set = executeResultQuery();
            set.next();
            
            result = set.getInt(1);
            
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
            
            if (connection == null){
                throw new OracleDataAccessObjectException("Connection is not created");
            }
            
            statement = connection.prepareStatement(ARTIST_BY_COUNTRY_MAX_ROW);
            
            statement.setString(1,country);
            
            ResultSet set = executeResultQuery();
            set.next();
            
            result = set.getInt(1);
            
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
            
            if (connection == null){
                throw new OracleDataAccessObjectException("Connection is not created");
            }
            
            statement = connection.prepareStatement(
                    SELECT_ALL_ARTISTS);
            statement.setInt(1,lastRow);
            statement.setInt(2,firstRow);
            
            ResultSet set = executeResultQuery();
            while(set.next()){
                currArtist = (Artist)fillBean(set,ARTIST,FULL_MODE);
                artists.add(currArtist);
            }
        } catch (SQLException e) {
            throw new OracleDataAccessObjectException(e);
        } finally {
             closeConnection();
        }
        return artists; 
    }



}
