package ua.edu.sumdu.lab3.ejbModule.artist;

import javax.ejb.*;
import java.util.*;
import java.rmi.RemoteException;

import ua.edu.sumdu.lab3.dao.operators.*;
import ua.edu.sumdu.lab3.exceptions.*;
import ua.edu.sumdu.lab3.model.Label;
import ua.edu.sumdu.lab3.model.Artist;

public class ArtistBean implements EntityBean {
    
    private Integer id;
    private String name;
    private String country;
    private String info;
    
    private ArtistsOperator aOperator;
    private EntityContext context = null;
    
    public ArtistBean() {
        aOperator = new ArtistsOperator();
    }
    
    public Integer ejbCreate(String name, String country, String info)
            throws CreateException {
            int id = -1;
            System.out.println("ejbCreate() ");
            try {
                Artist art = new Artist();
                art.setName(name);
                art.setCountry(country);
                art.setInfo(info);
                id = aOperator.addArtist(art);
                setId(new Integer(id));
                setName(name);
                setCountry(country);
                setInfo(info);
            } catch (OracleDataAccessObjectException e) {
                throw new CreateException(e.getMessage());
            }
        return getId();
    }
    
    public void ejbPostCreate(String name, String country, String info)
            throws CreateException {
            System.out.println("ejbPostCreate() ");
    }
    
    public Integer ejbFindByPrimaryKey(Integer id) 
            throws FinderException {
            Artist art = null;
            System.out.println("ejbFindByPrimaryKey() ");
            try {
                art = aOperator.getArtist(id.intValue());
            } catch (OracleDataAccessObjectException e) {
                throw new FinderException(e.getMessage());
            }
            if(art == null) {
                throw new FinderException("No artist found for this id");
            }
            return new Integer(art.getId());
    }
    
    public void setEntityContext(EntityContext ectx) {
        System.out.println("setEntityContext()");
        this.context = ectx;
    }
    
    public void unsetEntityContext() {
        System.out.println("unsetEntityContext()");
        this.context = null;
    }
    
    public void ejbRemove() 
            throws RemoveException {
        System.out.println("ejbRemove() ");
        try {
            aOperator.deleteArtist(getId().intValue());
            
        } catch (OracleDataAccessObjectException e) {
            throw new RemoveException(e.getMessage());
        }
    }
    
    public void ejbActivate() throws EJBException {
        System.out.println("ejbActivate() ");
        this.id = (Integer) context.getPrimaryKey();
    }
    
    public void ejbPassivate() throws EJBException {
        System.out.println("ejbPasivate() ");
    }
    
    public void ejbLoad() throws EJBException {
        System.out.println("ejbLoad() ");
        try {
            Artist art = aOperator.getArtist(getId().intValue());
            setName(art.getName());
            setCountry(art.getCountry());
            setInfo(art.getInfo());
            
        } catch (OracleDataAccessObjectException e) {
            throw new EJBException(e.getMessage());
        }
    }
    
    public void ejbStore() throws EJBException {
        System.out.println("ejbStore() ");
        try {
            Artist art = new Artist();
            art.setId(getId().intValue());
            art.setName(getName());
            art.setCountry(getCountry());
            art.setInfo(getInfo());
            aOperator.editArtist(art);
        } catch (OracleDataAccessObjectException e) {
            throw new EJBException(e.getMessage());
        }
    }
    
    public Collection ejbFindArtists(Integer firstrow, Integer lastRow) 
            throws FinderException {
        Collection artists = new LinkedList();
        List list = null;
        try {
            list = aOperator.getArtists(firstrow.intValue(),lastRow.intValue());
            Iterator itr = list.iterator();
            while(itr.hasNext()) {
                artists.add(new Integer(((Artist) itr.next()).getId()));
            }
            if(artists.size() == 0) {
                throw new FinderException("not found!");
            }
            
            return artists;
            
        } catch (OracleDataAccessObjectException e) {
            throw new FinderException(e.getMessage());
        }
    }
    
    public Collection ejbFindArtistsByLabel(Label lbl) throws FinderException {
        Collection artists = new LinkedList();
        List list = null;
        try {
            list = aOperator.getArtists(lbl);
            Iterator iter = list.iterator();
            while(iter.hasNext()) {
                artists.add(new Integer(((Artist)iter.next()).getId()));
            }
            if(artists.size() == 0) {
                throw new FinderException("not found!");
            }
            
            return artists;
        } catch (OracleDataAccessObjectException e) {
            throw new FinderException(e.getMessage());
        }
    }
    
    public Collection ejbFindArtistsByCountry(String country, Integer firstrow, Integer lastrow) 
            throws FinderException {
        Collection artists = new LinkedList();
        List list = null;
        try {
            list = aOperator.getArtists(country,firstrow.intValue(),lastrow.intValue());
            Iterator iter = list.iterator();
            while(iter.hasNext()) {
                artists.add(new Integer(((Artist)iter.next()).getId()));
            }
            if(artists.size() == 0) {
                throw new FinderException("not found!");
            }
            
            return artists;
        } catch (OracleDataAccessObjectException e) {
            throw new EJBException(e.getMessage());
        }
    } 
    
    public Integer ejbHomeGetArtistNumber() throws EJBException {
        int number = 0;
        try {
            number = aOperator.getArtistNumber();
            
            return new Integer(number);
            
        } catch (OracleDataAccessObjectException e) {
            throw new EJBException(e.getMessage());
        }
    }
    
    public Integer ejbHomeGetArtistNumber(String country) throws EJBException {
        int number = 0;
        try {
            number = aOperator.getArtistNumber(country);
            
            return new Integer(number);
            
        } catch (OracleDataAccessObjectException e) {
            throw new EJBException(e.getMessage());
        }
    }
    
    public void ejbHomeRemove(Integer id) throws EJBException {
        try {
            aOperator.deleteArtist(id.intValue());
            
        } catch (OracleDataAccessObjectException e) {
            throw new EJBException(e.getMessage());
        }
    }
    
    public Collection ejbHomeGetGenres(Artist art) throws EJBException {
        List genres = null;
        try {
            genres = aOperator.getGenres(art);
            Iterator iter = genres.iterator();
            System.out.println("Genres:");
            while(iter.hasNext()) {
                System.out.println(iter.next());
            }
        } catch (OracleDataAccessObjectException e) {
            throw new EJBException(e.getMessage());
        }
        return genres;
    }
    
    public Integer getId() {
        return this.id;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getCountry() {
        return this.country;
    }
    
    public String getInfo() {
        return this.info;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setCountry(String country) {
        this.country = country;
    }
    
    public void setInfo(String info) {
        this.info = info;
    }
}
