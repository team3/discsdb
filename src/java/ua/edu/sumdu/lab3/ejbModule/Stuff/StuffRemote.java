package ua.edu.sumdu.lab3.ejbModule.stuff;

import javax.ejb.EJBObject;
import java.rmi.RemoteException;
import java.util.Collection;

/**
 * This class realize the remote interface of the Stuff-bean.
 */ 
public interface StuffRemote extends EJBObject {
    
    /**
     * Returns all dates of the albums.
     * @return all dates of the albums.
     * @throws RemoteException.
     */
    public Collection getDates() throws RemoteException;
    
    /**
     * Returns all genres of the artist.
     * @param aid id of the artist.
     * @return all genres of the artist.
     * @throws RemoteException.
     */
    public Collection getGenresByArtist(Integer aid) 
        throws RemoteException;
    
    /**
     * Returns all genres of the label.
     * @param lid id of the label.
     * @return all genres of the label.
     * @throws RemoteException.
     */
    public Collection getGenresByLabel(Integer lid) 
        throws RemoteException;
}
