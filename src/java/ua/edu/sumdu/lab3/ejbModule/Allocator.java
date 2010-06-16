package ua.edu.sumdu.lab3.ejbModule;

import ua.edu.sumdu.lab3.ejbModule.label.LabelHome;
import ua.edu.sumdu.lab3.ejbModule.album.AlbumHome;
import javax.rmi.PortableRemoteObject;
import javax.naming.*;

public class Allocator {
	
	public static LabelHome getLabelHomeItf() throws NamingException{
		Context iContext = new InitialContext();
		Object obj = iContext.lookup("Label");		
		return (LabelHome)PortableRemoteObject.narrow(obj, LabelHome.class);
	}
	/*
	public static ArtistHome getArtistHomeItf() throws NamingException{
		Context iContext = new InitialContext();
		Object obj = iContext.lookup("Artist");		
		return (ArtistHome)PortableRemoteObject.narrow(obj, ArtistHome.class);
	}
	* */
	
	public static AlbumHome getAlbumHomeItf() throws NamingException{
		Context iContext = new InitialContext();
		Object obj = iContext.lookup("Album");		
		return (AlbumHome)PortableRemoteObject.narrow(obj, AlbumHome.class);
	}
}
