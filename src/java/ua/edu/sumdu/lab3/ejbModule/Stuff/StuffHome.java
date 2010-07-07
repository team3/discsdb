package ua.edu.sumdu.lab3.ejbModule.stuff;

import javax.ejb.EJBHome;
import javax.ejb.CreateException;
import java.rmi.RemoteException;

public interface StuffHome extends EJBHome {
    StuffRemote create() throws RemoteException, CreateException;
}
