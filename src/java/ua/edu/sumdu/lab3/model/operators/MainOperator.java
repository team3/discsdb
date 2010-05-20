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


public class MainOperator {
    protected Connection connection = null;
    protected PreparedStatement statement = null;
    
    protected static final int ALBUM = 0;
    protected static final int ARTIST = 1;
    protected static final int LABEL = 2;
        
    protected static final int FULL_MODE = 10;
    protected static final int SHORT_MODE = 9;
    
        
    private static final String SELECT_GENRES_BY_ARTIST =
            "SELECT DISTINCT genre FROM ALBUM WHERE art = ?";

    private static final String SELECT_GENRES_BY_LABEL =
            "SELECT DISTINCT genre FROM ALBUM WHERE lbl = ?";
            
    private static final String SELECT_ALL_DATES =
            "SELECT DISTINCT TO_CHAR(release, 'YYYY') as year FROM album order by year";
            
    
    /**
     * Setups the connection with database by specified parameters.
     */ 
    protected void getConnection() 
            throws OracleDataAccessObjectException {
        try {
            InitialContext iContext = new InitialContext();
            Context context = (Context) iContext.lookup("java:comp/env");
            DataSource datasource = (DataSource)context.lookup("jdbc/DiscsDB");
            connection = datasource.getConnection();
        } catch (javax.naming.NamingException e){
            throw new OracleDataAccessObjectException(e);
        } catch (SQLException e) {
            throw new OracleDataAccessObjectException(e);
        }
    }
    
    /**
     * This method execute result query of the defined statement.
     */ 
    protected ResultSet executeResultQuery() 
            throws OracleDataAccessObjectException {
        ResultSet result = null;
        try {
            if (this.connection != null) {
                result = this.statement.executeQuery();
            } else {
                throw new OracleDataAccessObjectException("Unable to execute query");
            }
        } catch (SQLException e) {
            throw new OracleDataAccessObjectException(e);
        }
        return result;
    }

    /**
     * This method execute update query of the defined statement.
     */ 
    protected int executeUpdateQuery() 
            throws OracleDataAccessObjectException {
        int result = -1;
        try {
            if (this.connection == null) {
                throw new OracleDataAccessObjectException(
                        "Connection is not created");                
            } 
            if (this.statement == null) {
                throw new OracleDataAccessObjectException("Statement is null"); 
            }
            result = this.statement.executeUpdate();
        } catch (SQLException e) {
            throw new OracleDataAccessObjectException(e);
        }
        return result;
    }
    
    /**
     * Closes connection with database. 
     */
    protected void closeConnection() 
            throws OracleDataAccessObjectException {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new OracleDataAccessObjectException(e);
            }
        }
        
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new OracleDataAccessObjectException(e);
            }
        }
    }
    
    protected Object fillBean(ResultSet set,int beanType,int mode) 
            throws OracleDataAccessObjectException {
        Object bean = null;
        try {
            switch(beanType) {
                case ALBUM:
                    Album alb = new Album();
                    if(mode == FULL_MODE) {
                        alb.setId(set.getInt(1));
                        alb.setName(set.getString(2));
                        alb.setType(set.getString(3));
                        alb.setRelease(new java.util.Date(set.getDate(4).getTime()));
                        alb.setGenre(set.getString(5));
                        alb.setCover(set.getString(6));
                        alb.setReview(set.getString(7));
                        alb.setArtist(set.getInt(8));
                        alb.setLabel(set.getInt(9));
                        alb.setArtistName(set.getString(10));
                        alb.setLabelName(set.getString(11));
                    } else {
                        alb.setId(set.getInt(1));
                        alb.setName(set.getString(2));
                    }
                    bean = alb;
                break;
                
                case ARTIST:
                    Artist art = new Artist();
                    if(mode == FULL_MODE) {
                        art.setId(set.getInt(1));
                        art.setName(set.getString(2));
                        art.setCountry(set.getString(3));
                        art.setInfo(set.getString(4));
                    } else {
                        art.setId(set.getInt(1));
                        art.setName(set.getString(2));
                    }
                    bean = art;
                break;
                
                case LABEL:
                    Label lbl = new Label();
                    if(mode == FULL_MODE) {
                        lbl.setId(set.getInt(1));
                        lbl.setMajor(set.getInt(2));
                        lbl.setName(set.getString(3));
                        lbl.setLogo(set.getString(4));
                        lbl.setInfo(set.getString(5));
                        lbl.setMajorName(set.getString(6));
                    } else {
                        lbl.setId(set.getInt(1));
                        lbl.setName(set.getString(2));
                        lbl.setLogo(set.getString(3));
                    }
                    bean = lbl;
                break;
            }
        } catch (SQLException e) {
            throw new OracleDataAccessObjectException(e + "sql error");
        }
        return bean;
    }

            
    /**
    * Returns list of genres of the specified artist.
    * @param artist artist of the genre.
    * @return list of genres of the specified artist.
    * @throws OracleDataAccessObjectException if problems while getting data.
    */
    public List getGenres(Artist artist) 
            throws OracleDataAccessObjectException {
        List genres = null;
        try {
            genres = new LinkedList();

            this.getConnection();

            if (this.connection == null){
                throw new OracleDataAccessObjectException("Connection is not created");
            }

            this.statement = this.connection.prepareStatement(
                    SELECT_GENRES_BY_ARTIST);

            this.statement.setInt(1, artist.getId());

            ResultSet set = this.executeResultQuery();
            while(set.next()){
                genres.add(set.getString(1));
            }
        } catch (SQLException e){
            throw new OracleDataAccessObjectException(e);
        } finally {
            closeConnection();
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
        List genres = null;
        try {
            genres = new LinkedList();

            this.getConnection();

            if (this.connection == null){
                throw new OracleDataAccessObjectException("Connection is not created");
            }

            this.statement = this.connection.prepareStatement(
                    SELECT_GENRES_BY_LABEL);

            this.statement.setInt(1, label.getId());

            ResultSet set = this.executeResultQuery();
            while(set.next()){
                genres.add(set.getString(1));
            }
        } catch (SQLException e){
            throw new OracleDataAccessObjectException(e);
        } finally {
            closeConnection();
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
            dates = new LinkedList();

            getConnection();

            if (this.connection == null){
                throw new OracleDataAccessObjectException("Connection is not created");
            }

            this.statement = this.connection.prepareStatement(
                    SELECT_ALL_DATES);

            ResultSet set = this.executeResultQuery();
            while(set.next()){
                dates.add(set.getString(1));
            }
        }   catch (SQLException e) {
            throw new OracleDataAccessObjectException(e);
        } finally {
            closeConnection();
        }
        return dates;
    }
}
