package ua.edu.sumdu.lab3.ejbModule.artist;

import java.rmi.RemoteException;
import javax.ejb.RemoveException;
import javax.ejb.EJBObject;

public interface ArtistRemote extends EJBObject {
    
    public void setId(Integer id) throws RemoteException;
    public void setName(String name) throws RemoteException;
    public void setCountry(String country) throws RemoteException;
    public void setInfo(String info) throws RemoteException;
    public Integer getId() throws RemoteException;
    public String getName() throws RemoteException;
    public String getCountry() throws RemoteException;
    public String getInfo() throws RemoteException;
}
