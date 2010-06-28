package ua.edu.sumdu.lab3.ejbModule.album;

import javax.ejb.*;
import java.rmi.RemoteException;
import java.util.*;
import ua.edu.sumdu.lab3.model.*;

public interface AlbumHome extends EJBHome {
	/*
	public AlbumRemote create(String name, String type, 
			Date release, String genre,	String cover, 
			String artistName, String labelName,
			String review, Integer artist, Integer label) 
			throws CreateException, RemoteException;
	*/
	public AlbumRemote create(Album album) 
			throws CreateException, RemoteException;
	
	public AlbumRemote findByPrimaryKey(Integer id) 
			throws FinderException, RemoteException;
	
	public Collection findAll(Integer firstRow, Integer lastRow) 
			throws FinderException, RemoteException;
	
	public Collection findByParams(Map params, Integer firstRow, 
			Integer lastRow) throws FinderException, RemoteException;

	public Integer getAlbumNumber() 
			throws EJBException, RemoteException;
			
	public Integer getAlbumNumber(Date date)
			throws EJBException, RemoteException;
	
	public Integer getAlbumNumber(String genre)
			throws EJBException, RemoteException;
			
	public Album getRandom() 
			throws EJBException, RemoteException;
			
	public Collection findByGenre(String genre, Integer firstRow, 
			Integer lastRow)
			throws FinderException, RemoteException;
	
	public Collection findByName(String name, Integer firstRow, 
			Integer lastRow)
			throws FinderException, RemoteException;
	
	public Collection findByDate(Date date, Integer firstRow, 
			Integer lastRow)
			throws FinderException, RemoteException;
			
	public Collection findByArtist(Integer aid, Integer firstRow, 
			Integer lastRow)
			throws FinderException, RemoteException;
	
	public Collection findByLabel(Integer lid, Integer firstRow, 
			Integer lastRow)
			throws FinderException, RemoteException;

	public Collection getGenresByArtist(Integer aid) 
			throws EJBException, RemoteException;
	
	public Collection getGenresByLabel(Integer lid) 
			throws EJBException, RemoteException;
	
	public Collection getDates() 
			throws EJBException, RemoteException;
			
	public Collection findLatest(Integer number)
			throws FinderException, RemoteException;
}
