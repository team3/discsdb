package ua.edu.sumdu.lab3.ejbModule.album;

import javax.ejb.*;
import java.util.Date;
import java.rmi.RemoteException;

public interface AlbumRemote extends EJBObject {
	
	public int getId() throws RemoteException;
 
    public String getName() throws RemoteException;

    public String getType() throws RemoteException;

    public Date getRelease() throws RemoteException;

    public String getGenre() throws RemoteException;

    public String getCover() throws RemoteException;

    public int getArtist() throws RemoteException;

    public int getLabel() throws RemoteException;

    public String getArtistName() throws RemoteException;

    public String getLabelName() throws RemoteException;

    public String getReview() throws RemoteException;
    
    public void setId(Integer id) throws RemoteException;

    public void setName(String name) throws RemoteException;

    public void setType(String type) throws RemoteException;
	
	public void setRelease(Date release)  throws RemoteException;

    public void setGenre(String genre)  throws RemoteException;

    public void setCover(String cover)  throws RemoteException;

    public void setArtist(Integer artist) throws RemoteException;

    public void setLabel(Integer label)  throws RemoteException;

    public void setArtistName(String artistName)  throws RemoteException;

    public void setLabelName(String labelName) throws RemoteException ;
    
    public void setReview(String review) throws RemoteException;
}
