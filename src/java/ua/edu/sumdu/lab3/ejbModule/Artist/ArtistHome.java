package ua.edu.sumdu.lab3.ejbModule.artist;

import javax.ejb.*;
import java.rmi.RemoteException;
import java.util.*;
import ua.edu.sumdu.lab3.model.*;

public interface ArtistHome extends EJBHome {
    
    public ArtistRemote findByPrimaryKey(Integer id) throws FinderException,
            RemoteException;
    
    public ArtistRemote create(String name, String country, String info) 
            throws CreateException, RemoteException;
             
    public Collection findArtists(Integer firtsrow, Integer lastrow) 
            throws FinderException, RemoteException;
    
    public Collection findArtistsByLabel(Label lbl) throws FinderException, RemoteException;
    
    public Collection findArtistsByCountry(String country, Integer firstrow, Integer lastrow) 
            throws FinderException, RemoteException;
            
    public Integer getArtistNumber() throws EJBException, RemoteException;
    
    public Integer getArtistNumber(String country) throws EJBException, 
            RemoteException;
    
    public void remove(Integer id) throws EJBException, 
            RemoteException;
    
    public Collection getGenres(Artist art) throws EJBException, RemoteException;
    
}
