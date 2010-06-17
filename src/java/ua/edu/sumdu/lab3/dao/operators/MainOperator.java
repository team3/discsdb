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
import javax.transaction.*;

public class MainOperator {
    protected Connection connection = null;
    protected PreparedStatement statement = null;
    
    protected static final int FULL_MODE = 2;
    protected static final int SHORT_MODE = 1;
    protected static final int NORMAL_MODE = 0;
            
	
    /**
     * Setups the connection with database by specified parameters.
     */ 
    protected void getConnection() 
            throws OracleDataAccessObjectException {
        try {
            InitialContext iContext = new InitialContext();
            DataSource  datasource = (DataSource) iContext.lookup("java:jdbc/DiscsDB");
            connection = datasource.getConnection();
            if (connection == null) {
                    throw new OracleDataAccessObjectException(
                            "Connection has not created");
            }
            
        } catch (javax.naming.NamingException e){
            throw new OracleDataAccessObjectException(e);
        } catch (SQLException e) {
            throw new OracleDataAccessObjectException(e);
        } 
    }
    
    /**
     * Closes connection with database. 
     */
    protected void closeConnection() 
            throws OracleDataAccessObjectException {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new OracleDataAccessObjectException(e);
            }
        }
        try {
            if(connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new OracleDataAccessObjectException(e);
        }
    }
    
    protected Artist fillArtistBean(ResultSet set,int mode)
            throws OracleDataAccessObjectException {
        Artist art = new Artist();
        try {
            switch(mode) {
                case FULL_MODE :
                    art.setId(set.getInt(1));
                    art.setName(set.getString(2));
                    art.setCountry(set.getString(3));
                    art.setInfo(set.getString(4));
                break;
            
                case SHORT_MODE:
                    art.setId(set.getInt(1));
                    art.setName(set.getString(2));
                break;
        
                case NORMAL_MODE:
                break;
            }
        } catch (SQLException e) {
            throw new OracleDataAccessObjectException(e);
        }
        return art;
    }
    
    protected Label fillLabelBean(ResultSet set,int mode) 
            throws OracleDataAccessObjectException {
        Label lbl = new Label();
        try {
            switch (mode) {
                case FULL_MODE :
                    lbl.setId(set.getInt(1));
                    lbl.setMajor(set.getInt(2));
                    lbl.setName(set.getString(3));
                    lbl.setLogo(set.getString(4));
                    lbl.setInfo(set.getString(5));
                    lbl.setMajorName(set.getString(6));
                break;
            
                case SHORT_MODE:
                    lbl.setId(set.getInt(1));
                    lbl.setName(set.getString(2));
                    lbl.setLogo(set.getString(3));
                break;
            
                case NORMAL_MODE:
                break;
            }
        } catch (SQLException e) {
            throw new OracleDataAccessObjectException(e);
        } 
        return lbl;
    }
    
    protected Album fillAlbumBean(ResultSet set,int mode) 
            throws OracleDataAccessObjectException {
        Album alb = new Album();
        try {
            switch(mode) {
                case FULL_MODE:
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
                break;
                
                case SHORT_MODE:
                    alb.setId(set.getInt(1));
                    alb.setName(set.getString(2));
                break;
                
                case NORMAL_MODE:
                break;
            }
        } catch (SQLException e) {
            throw new OracleDataAccessObjectException(e);
        }
        return alb;
    }

            
}
