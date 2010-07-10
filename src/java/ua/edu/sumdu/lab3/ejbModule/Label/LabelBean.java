package ua.edu.sumdu.lab3.ejbModule.label;

import javax.ejb.*;
import java.util.*;
import java.rmi.RemoteException;

import ua.edu.sumdu.lab3.dao.operators.*;
import ua.edu.sumdu.lab3.exceptions.*;
import ua.edu.sumdu.lab3.model.*;

/**
 * This class provides realization of the label bean.
 */ 
public class LabelBean implements EntityBean {
    
    private LabelsOperator labelsOperator = null;
    private AlbumsOperator albumsOperator = null;
    private ArtistsOperator artistsOperator = null;
    
    private EntityContext context = null;
    
    private boolean needToStore = false;
    
    /**
     * Constructor.
     */ 
    public LabelBean() {
        labelsOperator = new LabelsOperator();
        albumsOperator = new AlbumsOperator();
        artistsOperator = new ArtistsOperator();
    }
    
    /**
     * Creates new instance.
     * @param major id of the major label.
     * @param name name of th label.
     * @param info label's info.
     * @param logo label's logo.
     * @param majorName name of the major label.
     * @throws CreateException.
     * @throws RemoteException.
     */
    public Integer ejbCreate(Integer major, String name, 
            String info, String logo, String majorName) 
            throws CreateException, RemoteException {
        int id = 0;
        try {
            id = labelsOperator.addLabel(
                major.intValue(), name, info, logo, majorName
            );
            this.id = id;
            this.major = major.intValue();
            this.name = name;
            this.info = info;
            this.logo = logo;
            this.majorName = majorName;
               
        } catch (OracleDataAccessObjectException e){
            throw new CreateException(e.getMessage());
        }
        return new Integer(id);
    }

    /**
     * Post create.
     * @param major id of the major label.
     * @param name name of th label.
     * @param info label's info.
     * @param logo label's logo.
     * @param majorName name of the major label.
     * @throws CreateException.
     * @throws RemoteException.
     */
    public void ejbPostCreate(Integer major, String name, 
            String info, String logo, String majorName) 
            throws CreateException, RemoteException {
    }
    
    /**
     * Findes instance by specified identifier.
     * @param id id of the label's bean. 
     * @throws FinderException.
     * @throws RemoteException.
     */ 
    public Integer ejbFindByPrimaryKey(Integer id) 
            throws FinderException, RemoteException {
        
        Label label = null;
        try{
            label = labelsOperator.getLabel(id.intValue());
        } catch (OracleDataAccessObjectException e){
            throw new FinderException(e.getMessage());
        }
        if(label == null) {
            throw new FinderException("No label found for this id");
        }
        
        return new Integer(label.getId());
    }
    
    /**
     * Sets the entity context.
     * @param ectx entity context of the bean. 
     */ 
    public void setEntityContext(EntityContext ectx){
        this.context = ectx;
    }
    
     /**
     * Unsets the entity context.
     */ 
    public void unsetEntityContext(){
        this.context = null;
    }
    
    /**
     * Removes instance with the specified id.
     * @param id id of the instance.
     * @throws RemoteException.
     */     
    public void ejbHomeRemove(Integer id) 
            throws EJBException {
        try {
            labelsOperator.deleteLabel(id.intValue());
        } catch (OracleDataAccessObjectException e){
            throw new EJBException(e.getMessage());
        }
    }
    
    /**
     * Removes current instance of the bean.
     * @throws RemoteException.
     */   
    public void ejbRemove() 
            throws RemoveException, RemoteException {
        ejbHomeRemove(new Integer(this.id));
    }
    
    /**
     * Activates current instance of the bean.
     */ 
    public void ejbActivate(){
        this.id = ((Integer) context.getPrimaryKey()).intValue();
    }
    
    /**
     * Pasivates current instance of the bean.
     */
    public void ejbPassivate() {}
    
    /**
    * Realization of the ejb-load method for loading data from the database.
    * @throws EJBException if an ejb-error arose.
    * @throws RemoteException if an connection exception arose;
    */ 
    public void ejbLoad() throws EJBException, RemoteException {
        Label label = null;
        try {
            label = labelsOperator.getLabel(this.id);
            this.name = label.getName();
            this.info = label.getInfo();
            this.logo = label.getLogo();
            this.major = label.getMajor();
            this.majorName = label.getMajorName();
            
            this.needToStore = true;
        } catch (OracleDataAccessObjectException e){
            throw new EJBException(e.getMessage());
        }
    }
    
    /**
    * Returns collection of all major labels in specified restrictions.
    * @param firstIndex lower bound.
    * @param lastRow upper bound.
    * @return collection of all labels in specified restrictions.
    * @throws EJBException if problems while getting data.
    */
    public Collection ejbHomeGetMajorLabels(Integer firstRow, Integer lastRow) 
            throws EJBException {
        Collection labels = null;
        try {
            labels = labelsOperator.getMajorLabels(firstRow.intValue(), lastRow.intValue());
        } catch (OracleDataAccessObjectException e){
            throw new EJBException(e.getMessage());
        }
        return labels;
    }
    
    /**
     * Returns the label by the specified name.
     * 
     * @param name name of the label that should be return.
     * @return label by the specified name.
     * @throws EJBException if problems while getting data.
     */ 
    public Integer ejbHomeGetIdByName(String name) 
            throws EJBException{
        int id = 0;
        try {
            id = labelsOperator.findLabel(name);
        } catch (OracleDataAccessObjectException e){
            throw new EJBException(e.getMessage());
        }
        return new Integer(id);
    }
    
    /**
     * Returns maximal id of the label in storage.
     * @return maximal id of the label in storage.
     * @throws EJBException if problems while getting data.
     */
    public Integer ejbHomeGetLabelNumber() 
            throws EJBException {
        int id = 0;
        try {
            id = labelsOperator.getLabelNumber();
        } catch (OracleDataAccessObjectException e){
            throw new EJBException(e.getMessage());
        }
        return new Integer(id);
    }
    
    /**
     * Returns the path to the specified label in the hierarchy of labels.
     * @param id id of the label.
     * @return path to the specified label in the hierarchy of labels.
     * @throws OracleDataAccessObjectException if problems while getting data.
     */ 
    public Collection ejbHomeGetLabelPath(Integer id)
            throws EJBException {
        Collection labels = null;
        try {
            labels = labelsOperator.getLabelPath(id.intValue());
        } catch (OracleDataAccessObjectException e){
            throw new EJBException(e.getMessage());
        }
        return labels;
    }
    
    /**
    * Returns collection of all labels.
    * @return collection of all labels.
    * @throws EJBException if problems while getting data.
    */
    public Collection ejbHomeGetLabels()
            throws EJBException {
        Collection labels = null;
        try {
            labels = labelsOperator.getLabels();
        } catch (OracleDataAccessObjectException e){
            throw new EJBException(e.getMessage());
        }
        return labels;
    }
    
    /**
    * Returns list of child labels od the lable with specified id.
    * @param id label's id.
    * @return list of child labels od the lable with specified id.
    * @throws OracleDataAccessObjectException if problems while getting data.
    */
    public Collection ejbHomeGetChildLabels(Integer id)
            throws EJBException {
        Collection labels = null;
        try {
            labels = labelsOperator.getChildLabels(id.intValue());
        } catch (OracleDataAccessObjectException e){
            throw new EJBException(e.getMessage());
        }
        return labels;
    }

    /**
     * Stores data wich current instanjce of the bean uses.
     */ 
    public void ejbStore() {
        
        if (this.needToStore) {
            try {
                labelsOperator.editLabel(
                        this.id, this.major, this.name, this.info,
                        this.logo, this.majorName);
                
                this.needToStore = false;
            } catch (OracleDataAccessObjectException e){
                throw new EJBException(e.getMessage());
            }
        }
        
    }
    
    private int id;
    private int major;
    private String name;
    private String info;
    private String logo;
    private String majorName;

    /**
     * Returns label's id.
     * @return label's id.
     */ 
    public Integer getId() {
        return new Integer(this.id);
    }

    /**
     * Returns label's major id.
     * @return label's major id.
     */ 
    public Integer getMajor() {
        return new Integer(this.major);
    }

    /**
     * Returns label's name.
     * @return label's name.
     */ 
    public String getName() {
        return this.name;
    }

    /**
     * Returns label's info.
     * @return label's info.
     */ 
    public String getInfo() {
        return this.info;
    }
    
    /**
     * Returns label's logo.
     * @return label's logo.
     */
    public String getLogo() {
        return this.logo;
    }
    
    /**
     * Returns label's major name.
     * @return label's major name.
     */ 
    public String getMajorName() {
        return this.majorName;
    }
    
    /**
     * Sets id of the label.
     * @param id label's id.
     */
    public void setId(Integer id) {
        this.needToStore = true;
        this.id = id.intValue();
    }
    
    /**
     * Sets id of the major label.
     * @param major id of the major label.
     */
    public void setMajor(Integer major) {
        this.major = major.intValue();
        this.needToStore = true;
    }
    
    /**
     * Sets name of the label.
     * @param name name of the label.
     */
    public void setName(String name) {
        this.name = name;
        this.needToStore = true;
    }
    
    /**
     * Sets label's info.
     * @param info label's info.
     */ 
    public void setInfo(String info) {
        this.info = info;
        this.needToStore = true;
    }

    /**
     * Sets logo of the label.
     * @param loho label's logo.
     */ 
    public void setLogo(String logo) {
        this.logo = logo;
        this.needToStore = true;
    }
    
    /**
     * Sets name of the major label.
     * @param majorName name of the major label. 
     */ 
    public void setMajorName(String majorName) {
        this.majorName = majorName;
        this.needToStore = true;
    }
}
