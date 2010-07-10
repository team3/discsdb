package ua.edu.sumdu.lab3.ejbModule;

import ua.edu.sumdu.lab3.ejbModule.label.LabelHome;
import ua.edu.sumdu.lab3.ejbModule.album.AlbumHome;
import ua.edu.sumdu.lab3.ejbModule.artist.ArtistHome;
import ua.edu.sumdu.lab3.ejbModule.stuff.StuffHome;
import javax.rmi.PortableRemoteObject;
import javax.naming.*;
import java.util.Properties;

public class Allocator {

    /**
     * Returnes home intefrace of the label bean.
     * @return home intefrace of the label bean.
     * @throws NamingException.
     */ 
    public static LabelHome getLabelHomeItf() 
            throws NamingException {
        Context iContext = new InitialContext();
        Object obj = iContext.lookup("Label");      
        return (LabelHome)PortableRemoteObject.narrow(obj, LabelHome.class);
    }
    
    /**
     * Returnes home intefrace of the artist bean.
     * @return home intefrace of the artist bean.
     * @throws NamingException.
     */
    public static ArtistHome getArtistHomeItf() 
            throws NamingException {
        Context iContext = new InitialContext();
        Object obj = iContext.lookup("Artist");
        return (ArtistHome)PortableRemoteObject.narrow(obj, ArtistHome.class);
    }
    
    /**
     * Returnes home intefrace of the album bean.
     * @return home intefrace of the album bean.
     * @throws NamingException.
     */
    public static AlbumHome getAlbumHomeItf() 
            throws NamingException {
        Context iContext = new InitialContext();
        Object obj = iContext.lookup("Album");      
        return (AlbumHome)PortableRemoteObject.narrow(obj, AlbumHome.class);
    }
    
    /**
     * Returnes home intefrace of the stuff session bean.
     * @return home intefrace of the stuff session bean.
     * @throws NamingException.
     */
    public static StuffHome getStuffHomeItf() 
            throws NamingException {
        Context ctx = new InitialContext();
        Object obj  = ctx.lookup("Stuff");
        return (StuffHome) PortableRemoteObject.narrow(obj, StuffHome.class);
    }
}
