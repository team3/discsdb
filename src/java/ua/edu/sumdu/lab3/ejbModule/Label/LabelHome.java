package ua.edu.sumdu.lab3.ejbModule.label;

import javax.ejb.*;
import java.rmi.RemoteException;
import java.util.*;
import ua.edu.sumdu.lab3.model.*;

/**
 * This class realize the home interface of the Label-bean.
 */ 
public interface LabelHome extends EJBHome {

    /**
     * Creates new instance.
     * @param major id of the major label.
     * @param name name of th label.
     * @param info label's info.
     * @param logo label's logo.
     * @param majorName name of the major label.
     * @throws CreateException.
     * @throws RemoteException.
     */
    public LabelRemote create(int major, String name, 
            String info, String logo, String majorName) 
            throws CreateException, RemoteException;

    /**
     * Findes instance by specified identifier.
     * @param id id of the label's bean. 
     * @throws FinderException.
     * @throws RemoteException.
     */ 
    public LabelRemote findByPrimaryKey(Integer id) 
            throws FinderException, RemoteException;
    
    /**
    * Returns collection of all major labels in specified restrictions.
    * @param firstIndex lower bound.
    * @param lastRow upper bound.
    * @return collection of all labels in specified restrictions.
    * @throws EJBException if problems while getting data.
    * @throws RemoteException.
    */
    public Collection getMajorLabels(int firstRow, int lastRow)
            throws RemoteException, EJBException;
    
    /**
     * Returns the label by the specified name.
     * 
     * @param name name of the label that should be return.
     * @return label by the specified name.
     * @throws EJBException if problems while getting data.
     * @throws RemoteException.
     */
    public LabelRemote getByName(String name) 
            throws RemoteException, EJBException;
    
    /**
     * Removes current instance of the bean.
     * @param id id of the label's bean instance.
     * @throws RemoteException.
     * @throws EJBException.
     */   
    public void remove(Integer id) 
            throws RemoteException, EJBException;
    
    /**
     * Returns maximal id of the label in storage.
     * @return maximal id of the label in storage.
     * @throws RemoteException.
     * @throws EJBException if problems while getting data.
     */
    public int getLabelNumber()
           throws RemoteException, EJBException;
    
    /**
     * Returns the path to the specified label in the hierarchy of labels.
     * @param id id of the label.
     * @return path to the specified label in the hierarchy of labels.
     * @throws RemoteException.
     * @throws OracleDataAccessObjectException if problems while getting data.
     */ 
    public Collection getLabelPath(int id)
            throws RemoteException, EJBException;
    
    /**
    * Returns collection of all labels.
    * @return collection of all labels.
    * @throws RemoteException.
    * @throws EJBException if problems while getting data.
    */
    public Collection getLabels()
            throws RemoteException, EJBException;
    
    /**
    * Returns list of child labels od the lable with specified id.
    * @param id label's id.
    * @return list of child labels od the lable with specified id.
    * @throws RemoteException.
    * @throws OracleDataAccessObjectException if problems while getting data.
    */
    public Collection getChildLabels(int id)
            throws RemoteException, EJBException;
}
