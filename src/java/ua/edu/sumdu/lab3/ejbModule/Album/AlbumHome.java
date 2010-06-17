package ua.edu.sumdu.lab3.ejbModule.album;

import javax.ejb.*;
import java.rmi.RemoteException;
import java.util.*;
import ua.edu.sumdu.lab3.model.*;

public interface AlbumHome extends EJBHome {
	
	public AlbumRemote create(String name, String type, 
			Date release, String genre,	String cover, 
			String artistName, String labelName,
			String review, Integer artist, Integer label) 
			throws CreateException, RemoteException;
	
	public AlbumRemote findByPrimaryKey(Integer id) 
			throws FinderException, RemoteException;
	
	public Collection searchAlbums(Map params, Integer firstRow, 
			Integer lastRow) throws EJBException, RemoteException;

	public Integer getAlbumNumber() 
			throws EJBException, RemoteException;
			
	public Integer getAlbumNumber(Date date)
			throws EJBException, RemoteException;
	
	public Integer getAlbumNumber(String genre)
			throws EJBException, RemoteException;
			
	public Album getRandom() 
			throws EJBException, RemoteException;
			
	public Collection getByGenre(String genre, Integer firstRow, 
			Integer lastRow)
			throws EJBException, RemoteException;
			
	public Collection getByName(String name, Integer firstRow, 
			Integer lastRow)
			throws EJBException, RemoteException;
	
	public Collection getByDate(Date date, Integer firstRow, 
			Integer lastRow)
			throws EJBException, RemoteException;
			
	public Collection getByArtist(Integer aid, Integer firstRow, 
			Integer lastRow)
			throws EJBException, RemoteException;
	
	public Collection getByLabel(Integer lid, Integer firstRow, 
			Integer lastRow)
			throws EJBException, RemoteException;
			
	public Collection getAll(Integer firstRow,
			Integer lastRow)
			throws EJBException, RemoteException;
}
