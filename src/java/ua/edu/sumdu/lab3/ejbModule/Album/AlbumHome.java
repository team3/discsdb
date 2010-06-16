package ua.edu.sumdu.lab3.ejbModule.album;

import javax.ejb.*;
import java.rmi.RemoteException;
import java.util.*;

public interface AlbumHome extends EJBHome {
	
	public AlbumRemote create(String name, String type, 
			Date release, String genre,	String cover, 
			String artistName, String labelName,
			String review, Integer artist, Integer label) 
			throws CreateException, RemoteException;
	
	public AlbumRemote findByPrimaryKey(Integer id) 
			throws FinderException, RemoteException;
	
	

}