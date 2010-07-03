package ua.edu.sumdu.lab3.ejbModule.label;

import javax.ejb.*;
import java.rmi.RemoteException;
import java.util.*;
import ua.edu.sumdu.lab3.model.*;

public interface LabelHome extends EJBHome {

    public LabelRemote create(int major, String name, 
            String info, String logo, String majorName) 
            throws CreateException, RemoteException;

    public LabelRemote findByPrimaryKey(Integer id) 
            throws FinderException, RemoteException;
    
    public Collection getMajorLabels(int firstRow, int lastRow)
            throws RemoteException, EJBException;
    
    public Label getByName(String name) 
            throws RemoteException, EJBException;
    
    public void remove(Integer id) 
            throws RemoteException, EJBException;
    
    public int getLabelNumber()
           throws RemoteException, EJBException;
    
    public Collection getLabelPath(int id)
            throws RemoteException, EJBException;
    
    public Collection getLabels()
            throws RemoteException, EJBException;
    
    public Collection getChildLabels(int id)
            throws RemoteException, EJBException;
}
