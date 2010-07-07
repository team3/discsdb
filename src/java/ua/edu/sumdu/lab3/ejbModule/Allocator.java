package ua.edu.sumdu.lab3.ejbModule;

import ua.edu.sumdu.lab3.ejbModule.label.LabelHome;
import ua.edu.sumdu.lab3.ejbModule.album.AlbumHome;
import ua.edu.sumdu.lab3.ejbModule.artist.ArtistHome;
import ua.edu.sumdu.lab3.ejbModule.stuff.StuffHome;
import javax.rmi.PortableRemoteObject;
import javax.naming.*;
import java.util.Properties;

public class Allocator {

    public static LabelHome getLabelHomeItf() throws NamingException{
        Context iContext = new InitialContext();
        Object obj = iContext.lookup("Label");      
        return (LabelHome)PortableRemoteObject.narrow(obj, LabelHome.class);
    }
    
    public static ArtistHome getArtistHomeItf() throws NamingException{
        Context iContext = new InitialContext();
        Object obj = iContext.lookup("Artist");
        return (ArtistHome)PortableRemoteObject.narrow(obj, ArtistHome.class);
    }

    public static AlbumHome getAlbumHomeItf() throws NamingException{
        Context iContext = new InitialContext();
        Object obj = iContext.lookup("Album");      
        return (AlbumHome)PortableRemoteObject.narrow(obj, AlbumHome.class);
    }
    
    public static StuffHome getStuffHomeItf() throws NamingException {
        Context ctx = new InitialContext();
        Object obj  = ctx.lookup("Stuff");
        return (StuffHome) PortableRemoteObject.narrow(obj, StuffHome.class);
    }
}
