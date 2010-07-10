package ua.edu.sumdu.lab3.ejbModule.artist;

import javax.ejb.*;
import java.util.*;
import java.rmi.RemoteException;

import ua.edu.sumdu.lab3.dao.operators.*;
import ua.edu.sumdu.lab3.exceptions.*;
import ua.edu.sumdu.lab3.model.Label;
import ua.edu.sumdu.lab3.model.Artist;

/**
 * This class provides realization of the artist bean.
 */ 
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
    
    /**
     * Creates the new instance.
     * @param name name of the artist.
     * @param country country of the artist.
     * @param info info of the artist.
     * @throws CreateException.
     */ 
    public Integer ejbCreate(String name, String country, String info)
            throws CreateException {
            int id = -1;
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
    
    /**
     * All thing after createion the new instance.
     * @param name name of the artist.
     * @param country country of the artist.
     * @param info info of the artist.
     * @throws CreateException.
     */ 
    public void ejbPostCreate(String name, String country, String info)
            throws CreateException {
    }
    
    /**
     * Finds instance by primary key.
     * @param id key.
     * @return primary key of the album.
     * @throws FinderException.
     */ 
    public Integer ejbFindByPrimaryKey(Integer id) 
            throws FinderException {
            Artist art = null;
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
    
    /**
     * Sets entity context.
     * @param ectx entity context.
     */ 
    public void setEntityContext(EntityContext ectx) {
        this.context = ectx;
    }
    
    /**
     * Unsets entity context.
     */
    public void unsetEntityContext() {
        this.context = null;
    }
    
    /**
     * Removes current instance of the bean.
     * @throws RemoveException.
     */  
    public void ejbRemove() 
            throws RemoveException {
        try {
            aOperator.deleteArtist(getId().intValue());
            
        } catch (OracleDataAccessObjectException e) {
            throw new RemoveException(e.getMessage());
        }
    }
    
    /**
     * Activates instance.
     * @throws EJBException.
     */ 
    public void ejbActivate() throws EJBException {
        this.id = (Integer) context.getPrimaryKey();
    }
    
    /**
     * Passivates instance.
     * @throws EJBException.
     */ 
    public void ejbPassivate() throws EJBException {
    }
    
    /**
     * Load intance of the artist-bean.
     * @throws EJBException.
     */ 
    public void ejbLoad() throws EJBException {
        try {
            Artist art = aOperator.getArtist(getId().intValue());
            setName(art.getName());
            setCountry(art.getCountry());
            setInfo(art.getInfo());
            
        } catch (OracleDataAccessObjectException e) {
            throw new EJBException(e.getMessage());
        }
    }
    
    /**
     * Stores intance of the artist-bean.
     * @throws EJBException.
     */ 
    public void ejbStore() throws EJBException {
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
    
    /**
     * Return collection of all artists.
     * @param firstRow from.
     * @param lastRow to.
     * @return collection of all artists.
     * @throws FinderException. 
     */ 
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
    
    /**
     * Returnes collection of artists by specified label.
     * @param lbl label.
     * @return collection of artists by specified label.
     * @throws FinderException. 
     */ 
    public Collection ejbFindArtistsByLabel(Label lbl) 
            throws FinderException {
        Collection artists = new LinkedList();
        List list = null;
        try {
            list = aOperator.getArtists(lbl);
            Iterator iter = list.iterator();
            while(iter.hasNext()) {
                artists.add(new Integer(((Artist)iter.next()).getId()));
            }
            if(artists.size() == 0) {
                throw new FinderException("Artist not found!");
            }
            
            return artists;
        } catch (OracleDataAccessObjectException e) {
            throw new FinderException(e.getMessage());
        }
    }
    
    /**
     * Returnes collection of artists by specified county.
     * @param country country of the artist.
     * @param firstRow from.
     * @param lastRow to.
     * @return collection of artist by specified county.
     * @throws FinderException. 
     */ 
    public Collection ejbFindArtistsByCountry(String country, 
            Integer firstrow, Integer lastrow) 
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
                throw new FinderException("Artists not found!");
            }
            
            return artists;
        } catch (OracleDataAccessObjectException e) {
            throw new EJBException(e.getMessage());
        }
    } 
    
    /**
     * Returnes maximal number of all the artists.
     * @return maximal number of all the artists.
     * @throws EJBException.
     */ 
    public Integer ejbHomeGetArtistNumber() 
            throws EJBException {
        int number = 0;
        try {
            number = aOperator.getArtistNumber();
            
            return new Integer(number);
            
        } catch (OracleDataAccessObjectException e) {
            throw new EJBException(e.getMessage());
        }
    }
    
    /**
     * Returnes maximal number of all the artists by specified country.
     * @param country country of the artist.
     * @return maximal number of all the artists by specified country.
     * @throws EJBException.
     */ 
    public Integer ejbHomeGetArtistNumber(String country) 
            throws EJBException {
        int number = 0;
        try {
            number = aOperator.getArtistNumber(country);
            
            return new Integer(number);
            
        } catch (OracleDataAccessObjectException e) {
            throw new EJBException(e.getMessage());
        }
    }
    
    /**
     * Removes instance of the bean with the specified id.
     * @param id id if the instance.
     * @throws EJBException.
     */ 
    public void ejbHomeRemove(Integer id) 
            throws EJBException {
        try {
            aOperator.deleteArtist(id.intValue());
            
        } catch (OracleDataAccessObjectException e) {
            throw new EJBException(e.getMessage());
        }
    }
    
    /**
    * Find Artist by name
    * @param artist name
    * @return id number of artist
    * @throws FinderException throws when search was failed
    */
    public Integer ejbHomeGetIdByName(String name) throws EJBException {
        int result = 0;
        try {
            result = aOperator.findArtist(name);
        } catch (OracleDataAccessObjectException e) {
            throw new EJBException(e.getMessage());
        }
        return new Integer(result);
    }
    
    /**
     * Returnes artist id.
     * @return artist id.
     */ 
    public Integer getId() {
        return this.id;
    }

    /**
     * Returnes artist name.
     * @return artist name.
     */ 
    public String getName() {
        return this.name;
    }

    /**
     * Returnes artist country.
     * @return artist country.
     */ 
    public String getCountry(){
        return this.country;
    }

    /**
     * Returnes artist info.
     * @return artist info.
     */ 
    public String getInfo(){
        return this.info;
    }

    /**
     * Sets artist id.
     * @param artist id.
     */ 
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Sets artist name.
     * @param artist name.
     */ 
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets artist country.
     * @param artist country.
     */ 
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Sets artist info.
     * @param artist info.
     */ 
    public void setInfo(String info) {
        this.info = info;
    }
}
