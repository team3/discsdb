package ua.edu.sumdu.lab3.ejbModule.label;

import javax.ejb.*;
import java.rmi.RemoteException;

public interface LabelRemote extends EJBObject {
    
    public Integer getId() throws RemoteException; 

    public Integer getMajor() throws RemoteException; 

    public String getName() throws RemoteException;

    public String getInfo() throws RemoteException; 

    public String getLogo() throws RemoteException; 
    
    public String getMajorName() throws RemoteException; 

    public void setId(Integer id) throws RemoteException; 

    public void setMajor(Integer major) throws RemoteException; 

    public void setName(String name) throws RemoteException; 

    public void setInfo(String info) throws RemoteException; 

    public void setLogo(String logo) throws RemoteException; 
    
    public void setMajorName(String majorName) throws RemoteException; 

}
