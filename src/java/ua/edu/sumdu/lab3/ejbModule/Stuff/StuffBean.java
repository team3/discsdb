package ua.edu.sumdu.lab3.ejbModule.stuff;

import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import java.util.Collection;

import ua.edu.sumdu.lab3.dao.operators.*;
import ua.edu.sumdu.lab3.exceptions.*;

import org.apache.log4j.Logger;
import org.apache.log4j.BasicConfigurator;

/**
 * This class designed to contain methods that not connected with any of
 * the entities, such as "Album", "Artist", "Label".
 */ 
public class StuffBean implements SessionBean {
    private Logger log = null;
        
    private SessionContext ctx;
    
    public StuffBean() {
        log = Logger.getLogger(StuffBean.class);
    }
    
    /**
     * Realization of the create method.
     */ 
    public void ejbCreate() {
    }
    
    /**
     * Realization of the remove method.
     */ 
    public void ejbRemove() {
    }

    /**
     * Realization of the activate method.
     */ 
    public void ejbActivate() {
    }
    
    /**
     * Realization of the passivate method.
     */ 
    public void ejbPassivate() {
    }
    
    /**
     * Settes the session context.
     * @param ctx session context of the bean. 
     */ 
    public void setSessionContext(SessionContext ctx) {
        this.ctx = ctx;
    }

    /**
     * Returns all dates of the albums.
     * @return all dates of the albums
     */ 
    public Collection getDates() {
        Collection dates = null;
        MainOperator mainOperator = null;
        try {
            mainOperator = new MainOperator();
            dates = mainOperator.getDates();
        } catch (OracleDataAccessObjectException e){
            System.out.println(e.getMessage());
        }
        return dates;
    }
    
    /**
     * Returns all genres of the artist.
     * @param aid id of the artist.
     * @return all genres of the artist.
     */ 
    public Collection getGenresByArtist(Integer aid) {
        Collection genres = null;
        MainOperator mainOperator = null;
        try {
            mainOperator = new MainOperator();
            genres = mainOperator.getGenresByArtist(aid.intValue());
        } catch (OracleDataAccessObjectException e){
            System.out.println(e.getMessage());
        }
        return genres;
    }
    
    /**
     * Returns all genres of the label.
     * @param lid id of the label.
     * @return all genres of the label.
     */ 
    public Collection getGenresByLabel(Integer lid) {
        Collection genres = null;
        MainOperator mainOperator = null;
        try {
            mainOperator = new MainOperator();
            genres = mainOperator.getGenresByLabel(lid.intValue());
        } catch (OracleDataAccessObjectException e){
            System.out.println(e.getMessage());
        }
        return genres;
    }
}
