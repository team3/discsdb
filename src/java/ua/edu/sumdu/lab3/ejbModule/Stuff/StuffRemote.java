package ua.edu.sumdu.lab3.ejbModule.stuff;

import javax.ejb.EJBObject;
import java.rmi.RemoteException;
import java.util.Collection;

public interface StuffRemote extends EJBObject {
    public Collection getDates() throws RemoteException;
    
    public Collection getGenresByArtist(Integer aid) 
        throws RemoteException;
    
    public Collection getGenresByLabel(Integer lid) 
        throws RemoteException;
}
