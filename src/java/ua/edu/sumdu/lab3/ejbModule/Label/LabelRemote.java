package ua.edu.sumdu.lab3.ejbModule.label;

import javax.ejb.*;
import java.rmi.RemoteException;

/**
 * This class realize the remote interface of the label-bean.
 */ 
public interface LabelRemote extends EJBObject {
    
    /**
     * Returns label's id.
     * @return label's id.
     * @throws RemoteException.
     */ 
    public Integer getId() throws RemoteException; 
    
    /**
     * Returns label's major id.
     * @return label's major id.
     * @throws RemoteException.
     */ 
    public Integer getMajor() throws RemoteException; 
    
    /**
     * Returns label's name.
     * @return label's name.
     * @throws RemoteException.
     */
    public String getName() throws RemoteException;
    
    /**
     * Returns label's info.
     * @return label's info.
     * @throws RemoteException.
     */
    public String getInfo() throws RemoteException; 

    /**
     * Returns label's logo.
     * @return label's logo.
     * @throws RemoteException.
     */
    public String getLogo() throws RemoteException; 
    
    /**
     * Returns label's major name.
     * @return label's major name.
     * @throws RemoteException.
     */
    public String getMajorName() throws RemoteException; 
    
    /**
     * Sets id of the label.
     * @param id label's id.
     * @throws RemoteException.
     */ 
    public void setId(Integer id) throws RemoteException; 
    
    /**
     * Sets id of the major label.
     * @param major id of the major label.
     * @throws RemoteException.
     */ 
    public void setMajor(Integer major) throws RemoteException; 
    
    /**
     * Sets name of the label.
     * @param name name of the label.
     * @throws RemoteException.
     */ 
    public void setName(String name) throws RemoteException; 
    
    /**
     * Sets label's info.
     * @param info label's info.
     * @throws RemoteException.
     */
    public void setInfo(String info) throws RemoteException; 

    /**
     * Sets logo of the label.
     * @param loho label's logo.
     * @throws RemoteException.
     */ 
    public void setLogo(String logo) throws RemoteException; 
    
    /**
     * Sets name of the major label.
     * @param majorName name of the major label. 
     * @throws RemoteException.
     */ 
    public void setMajorName(String majorName) throws RemoteException; 

}
