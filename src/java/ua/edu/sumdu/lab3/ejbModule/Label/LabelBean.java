package ua.edu.sumdu.lab3.ejbModule.label;

import javax.ejb.*;
import java.util.*;
import java.rmi.RemoteException;

import ua.edu.sumdu.lab3.dao.operators.*;
import ua.edu.sumdu.lab3.exceptions.*;
import ua.edu.sumdu.lab3.model.*;

public class LabelBean implements EntityBean {
	
	
	private LabelsOperator labelsOperator = null;
	private AlbumsOperator albumsOperator = null;
	private ArtistsOperator artistsOperator = null;
	
	private EntityContext context = null;
	
	private boolean needToStore = false;
	
	public LabelBean() {
		labelsOperator = new LabelsOperator();
		albumsOperator = new AlbumsOperator();
		artistsOperator = new ArtistsOperator();
	}
	
	public Integer ejbCreate(int major, String name, 
			String info, String logo, String majorName) 
			throws CreateException, RemoteException {
		int id = 0;
		try {
			id = labelsOperator.addLabel(
				major, name, info, logo, majorName
			);
			this.id = id;
			this.major = major;
			this.name = name;
			this.info = info;
			this.logo = logo;
			this.majorName = majorName;
			
		} catch (OracleDataAccessObjectException e){
			throw new CreateException(e.getMessage());
		}
		return new Integer(id);
	}
	
	public void ejbPostCreate(int major, String name, 
			String info, String logo, String majorName) 
			throws CreateException, RemoteException {
	}
	
	public Integer ejbFindByPrimaryKey(Integer id) 
			throws FinderException, RemoteException {
		
		Label label = null;
		try{
			label = labelsOperator.getLabel(id.intValue());
		} catch (OracleDataAccessObjectException e){
			throw new FinderException(e.getMessage());
		}
		return new Integer(label.getId());
	}
	
	
	public void setEntityContext(EntityContext ectx){
		this.context = ectx;
	}
	
	public void unsetEntityContext(){
		this.context = null;
	}
		
	public void ejbHomeRemove() 
			throws EJBException {
		try {
			labelsOperator.deleteLabel(this.id);
		} catch (OracleDataAccessObjectException e){
			throw new EJBException(e.getMessage());
		}
	}
	
	public void ejbRemove() 
			throws RemoveException, RemoteException {
		ejbHomeRemove();
	}
	
	public void ejbActivate(){
		this.id = ((Integer) context.getPrimaryKey()).intValue();
	}
	
	public void ejbPassivate() {}
	
	/**
	 * Realization of the ejb-load method for loading data from the database.
	 * @throws EJBException if an ejb-error arose.
	 * @threws RemoteException if an connection exception arose;
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
	
	public Collection ejbHomeGetMajorLabels(int firstRow, int lastRow) 
			throws EJBException {
		Collection labels = null;
		try {
			labels = labelsOperator.getMajorLabels(firstRow, lastRow);
		} catch (OracleDataAccessObjectException e){
			throw new EJBException(e.getMessage());
		}
		return labels;
	}
	
	public int ejbHomeGetByName(String name) 
			throws EJBException{
		int id = 0;
		try {
			id = labelsOperator.findLabel(name);
		} catch (OracleDataAccessObjectException e){
			throw new EJBException(e.getMessage());
		}
		return id;
	}
	
	public int ejbHomeGetLabelNumber() 
			throws EJBException {
		int id = 0;
		try {
			id = labelsOperator.getLabelNumber();
		} catch (OracleDataAccessObjectException e){
			throw new EJBException(e.getMessage());
		}
		return id;
	}
	
	public Collection ejbHomeGetLabelPath(int id)
			throws EJBException {
		Collection labels = null;
		try {
			labels = labelsOperator.getLabelPath(id);
		} catch (OracleDataAccessObjectException e){
			throw new EJBException(e.getMessage());
		}
		return labels;
	}
	
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
	
	public Collection ejbHomeGetChildLabels(int id)
			throws EJBException {
		Collection labels = null;
		try {
			labels = labelsOperator.getChildLabels(id);
		} catch (OracleDataAccessObjectException e){
			throw new EJBException(e.getMessage());
		}
		return labels;
	}
	
	public void ejbHomeEdit()
			throws EJBException {
		try {
			labelsOperator.editLabel(
					this.id, this.major, this.name, this.info,
					this.logo, this.majorName);
		} catch (OracleDataAccessObjectException e){
			throw new EJBException(e.getMessage());
		}
	}
	
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

    public Integer getId() {
        return new Integer(this.id);
    }

    public Integer getMajor() {
        return new Integer(this.major);
    }

    public String getName() {
        return this.name;
    }

    public String getInfo() {
        return this.info;
    }

    public String getLogo() {
        return this.logo;
    }
    
    public String getMajorName() {
        return this.majorName;
    }

    public void setId(Integer id) {
		this.needToStore = true;
        this.id = id.intValue();
    }

    public void setMajor(Integer major) {
        this.major = major.intValue();
        this.needToStore = true;
    }

    public void setName(String name) {
        this.name = name;
        this.needToStore = true;
    }

    public void setInfo(String info) {
        this.info = info;
        this.needToStore = true;
    }

    public void setLogo(String logo) {
        this.logo = logo;
        this.needToStore = true;
    }
    
    public void setMajorName(String majorName) {
        this.majorName = majorName;
        this.needToStore = true;
    }
	
}
