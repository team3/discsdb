package ua.edu.sumdu.lab3.ejbModule.stuff;

import javax.ejb.EJBHome;
import javax.ejb.CreateException;
import java.rmi.RemoteException;

/**
 * This class realize the home interface of the Stuff-bean.
 */ 
public interface StuffHome extends EJBHome {
    /**
     * Creates a new instance.
     * @throws RemoteException.
     * @throws CreateException.
     */ 
    StuffRemote create() 
            throws RemoteException, CreateException;
}
