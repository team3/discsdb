package ua.edu.sumdu.lab3.ejbModule.artist;

import javax.ejb.*;
import java.rmi.RemoteException;
import java.util.*;
import ua.edu.sumdu.lab3.model.*;

public interface ArtistHome extends EJBHome {
    
    /**
     * Finds instance by primary key.
     * @param id key.
     * @return primary key of the album.
     * @throws FinderException.
     * @throws RemoteException.
     */ 
    public ArtistRemote findByPrimaryKey(Integer id) 
            throws FinderException, RemoteException;
    
    /**
     * Creates the new instance.
     * @param name name of the artist.
     * @param country country of the artist.
     * @param info info of the artist.
     * @throws CreateException.
     * @throws RemoteException.
     */ 
    public ArtistRemote create(String name, String country, String info) 
            throws CreateException, RemoteException;
             
    /**
     * Return collection of all artists.
     * @param firstRow from.
     * @param lastRow to.
     * @return collection of all artists.
     * @throws FinderException. 
     * @throws RemoteException.
     */ 
    public Collection findArtists(Integer firtsrow, Integer lastrow) 
            throws FinderException, RemoteException;
    
    /**
     * Returnes collection of artists by specified label.
     * @param lbl label.
     * @return collection of artists by specified label.
     * @throws FinderException. 
     * @throws RemoteException.
     */ 
    public Collection findArtistsByLabel(Label lbl) 
            throws FinderException, RemoteException;
    
    /**
     * Returnes collection of artists by specified county.
     * @param country country of the artist.
     * @param firstRow from.
     * @param lastRow to.
     * @return collection of artist by specified county.
     * @throws FinderException. 
     * @throws RemoteException.
     */ 
    public Collection findArtistsByCountry(String country, 
            Integer firstrow, Integer lastrow) 
            throws FinderException, RemoteException;
            
    /**
     * Returnes maximal number of all the artists.
     * @return maximal number of all the artists.
     * @throws EJBException.
     * @throws RemoteException.
     */ 
    public Integer getArtistNumber() 
            throws EJBException, RemoteException;
    
    /**
     * Returnes maximal number of all the artists by specified country.
     * @param country country of the artist.
     * @return maximal number of all the artists by specified country.
     * @throws EJBException.
     * @throws RemoteException.
     */ 
    public Integer getArtistNumber(String country) 
            throws EJBException, RemoteException;
    
    /**
     * Removes instance of the bean with the specified id.
     * @param id id if the instance.
     * @throws EJBException.
     * @throws RemoteException.
     */ 
    public void remove(Integer id) 
            throws EJBException, RemoteException;
}
