package ua.edu.sumdu.lab3.ejbModule.artist;

import java.rmi.RemoteException;
import javax.ejb.RemoveException;
import javax.ejb.EJBObject;

public interface ArtistRemote extends EJBObject {
    
    /**
     * Returnes artist id.
     * @return artist id.
     */ 
    public void setId(Integer id) throws RemoteException;
    
    /**
     * Returnes artist name.
     * @return artist name.
     */ 
    public void setName(String name) throws RemoteException;
    
    /**
     * Returnes artist country.
     * @return artist country.
     */
    public void setCountry(String country) throws RemoteException;
    
    /**
     * Returnes artist info.
     * @return artist info.
     */
    public void setInfo(String info) throws RemoteException;
    
    /**
     * Sets artist id.
     * @param artist id.
     */
    public Integer getId() throws RemoteException;
    
    /**
     * Sets artist name.
     * @param artist name.
     */
    public String getName() throws RemoteException;
    
    /**
     * Sets artist country.
     * @param artist country.
     */
    public String getCountry() throws RemoteException;
    
    /**
     * Sets artist info.
     * @param artist info.
     */
    public String getInfo() throws RemoteException;
}
