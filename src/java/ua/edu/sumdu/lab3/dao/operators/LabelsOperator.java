package ua.edu.sumdu.lab3.dao.operators;

import ua.edu.sumdu.lab3.exceptions.*;
import ua.edu.sumdu.lab3.model.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;
import javax.transaction.*;

public class LabelsOperator extends MainOperator {
    
    private static final String ADD_NEW_LABEL = 
            "INSERT INTO LABEL VALUES (?, ?, ?, ?, ?)";
            
    private static final String SELECT_LABEL_BY_ID = 
            "SELECT a.lid,a.major,a.name,a.logo,a.info,a.major_name FROM (SELECT d.lid,d.major,d.name,d.logo,d.info,e.name as major_name FROM label d, label e WHERE d.major = e.lid UNION SELECT d.lid,d.major,d.name,d.logo,info,null as major_name FROM label d WHERE d.major IS NULL) a where a.lid = ?";
    
    private static final String SELECT_ALL_LABELS = 
            "SELECT lid,name,logo from label";
            
    private static final String ALBUM_MAX_ROW =
            "SELECT MAX(ROWNUM) FROM album";      
              
    private static final String DELETE_LABEL = 
            "DELETE FROM label WHERE lid = ?";        
            
    private static final String FIND_LABEL = 
            "SELECT lid FROM label WHERE (INSTR(LOWER(name), LOWER(?)) != 0)";
    
    private static final String EDIT_LABEL = 
            "UPDATE LABEL SET major = ?, name = ?, info = ?, logo = ? where lid = ?";
    
    private static final String GET_CHILD_LABELS = 
            "SELECT lid,name,logo FROM label where major = ?";

    private static final String GET_MAJOR_LABELS = 
            "SELECT b.lid,b.name,b.logo FROM (SELECT a.*, rownum rnum FROM (SELECT d.lid,d.name,d.logo FROM label d WHERE d.major IS NULL ORDER BY lid) a WHERE rownum <= ?) b WHERE rnum >=?";
    
    private static final String LABEL_GET_PATH = 
            "SELECT * FROM (SELECT lid,name,logo FROM label START WITH lid = ? CONNECT BY PRIOR major = lid) ORDER BY lid";
    
    private static final String LABEL_MAX_ROW =
            "SELECT MAX(ROWNUM) FROM label WHERE major IS NULL";
    
    private static final String RESERVE_NEW_ID = 
            "SELECT SQ_LABEL.nextval FROM dual";
            
    
    /**
     * Returns the new value of the labels counter.
     * 
     * @return the new value of the labels counter.
     */ 
    public int getNewId() throws OracleDataAccessObjectException {
        int id = 0;
        try {
            getConnection();
            statement = connection.prepareStatement(RESERVE_NEW_ID);
            ResultSet set = statement.executeQuery();
            if(set.next()){
                id = set.getInt(1);
            }
        } catch (java.sql.SQLException e) {
            throw new OracleDataAccessObjectException(e);
        } finally {
            closeConnection();
        }
        return id;
    }
    
    /**
     * Adds new label to the specified storage.
     * @param album new instanse of the Label that should be added.
     * @throws OracleDataAccessObjectException if problems while adding the data.
     */ 
    public int addLabel(int major, String name, 
            String info, String logo, String majorName) 
            throws OracleDataAccessObjectException {        
        int result;
        try {
            int id = getNewId();
            getConnection();
            statement = connection.prepareStatement(
                    ADD_NEW_LABEL);

            statement.setInt(1, id);
            if (major == -1) {       
                statement.setNull(2, java.sql.Types.NULL);
            } else {
                statement.setInt(2, major);
            }
            statement.setString(3, name);
            statement.setString(4, logo);
            statement.setString(5, info);
            
            statement.executeUpdate();
            
            result = id;
        } catch (SQLException e) {
            throw new OracleDataAccessObjectException(e);
        } finally {
            closeConnection();
        }
        return result;
    }

    /**
     * Returns the label by the specified id.
     * 
     * @param id id of the label that should be return.
     * @return label by the specified id.
     * @throws OracleDataAccessObjectException if problems while getting data.
     */ 
    public Label getLabel(int id) 
            throws OracleDataAccessObjectException {
        Label label = null;
        try {
            getConnection();
            
            statement = connection.prepareStatement(
                    SELECT_LABEL_BY_ID);
            statement.setInt(1, id);
            
            ResultSet set = statement.executeQuery();
            if(set.next()) {
                label = fillLabelBean(set, FULL_MODE);
            }
            set.close();
        } catch (SQLException e) {
            throw new OracleDataAccessObjectException(e);
        } finally {
            closeConnection();
        }
        return label;    
    }
    
    /**
    * Returns list of all labels.
    * @return list of all labels.
    * @throws OracleDataAccessObjectException if problems while getting data.
    */
    public List getLabels() 
            throws OracleDataAccessObjectException {
        List labels = null;
        try {
            labels = new LinkedList();
            Label currLabel;
            getConnection();
            
            statement = connection.prepareStatement(
                    SELECT_ALL_LABELS);
            
            ResultSet set = statement.executeQuery();
            
            while(set.next()){
                currLabel = fillLabelBean(set,SHORT_MODE);
                labels.add(currLabel);
            }
            set.close();
        } catch (SQLException e) {
            throw new OracleDataAccessObjectException(e);
        } finally {
            closeConnection();
        }
        return labels;
    }

    /**
     * Returns id of the label by specified name.
     * @param name name of the label.
     * @return id of the label by specified name.
     * @throws OracleDataAccessObjectException if problems while getting data.
     */
    public int findLabel(String name) 
            throws OracleDataAccessObjectException {
        int lid = -1;
        try {
            getConnection();
            
            statement = connection.prepareStatement(FIND_LABEL);
            statement.setString(1, name);
            
            ResultSet set = statement.executeQuery();
            while(set.next()){
                lid = set.getInt(1);
            }
            set.close();
        } catch (SQLException e) {
            throw new OracleDataAccessObjectException(e);
        } finally {
             closeConnection();
        }
        return lid;
    }

    /**
     * Removes label from the storage.
     * @param id label's id.
     * @throws OracleDataAccessObjectException if problems while editing data.
     */
    public void deleteLabel(int id) 
            throws OracleDataAccessObjectException {
        try {
            getConnection();
            
            statement = connection.prepareStatement(DELETE_LABEL);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new OracleDataAccessObjectException(e);
        } finally {
             closeConnection();
        }
    }

    /**
     * Edits specified label. Replaces found (by id) label by specified.
     * 
     * @param label to edit/change.
     * @throws OracleDataAccessObjectException if problems while editting data.
     */ 
    public void editLabel(int id, int major, String name, 
            String info, String logo, String majorName) 
            throws OracleDataAccessObjectException {
        try {
            getConnection();
            
            statement = connection.prepareStatement(EDIT_LABEL);
            if (major == 0) {
                statement.setNull(1, major);
            } else {
                statement.setInt(1, major);
            }
            statement.setString(2, name);
            statement.setString(3, info);
            statement.setString(4, logo);
            statement.setInt(5, id);
           
            statement.executeUpdate();
       
        } catch (SQLException e) {
            throw new OracleDataAccessObjectException(e);
        } finally {
            closeConnection();
        }
    }

    /**
    * Returns list of all major labels in specified restrictions.
    * @param firstIndex lower bound.
    * @param lastRow upper bound.
    * @return list of all labels in specified restrictions.
    * @throws OracleDataAccessObjectException if problems while getting data.
    */
    public List getMajorLabels(int firstRow, int lastRow)
            throws OracleDataAccessObjectException {
        List labels = null;
        try {
            labels = new LinkedList();
            Label currLabel;
            getConnection();
            
            statement = connection.prepareStatement(
                    GET_MAJOR_LABELS);
            statement.setInt(1,lastRow);
            statement.setInt(2,firstRow);
            
            ResultSet set = statement.executeQuery();
            
            while(set.next()){
                currLabel = fillLabelBean(set,SHORT_MODE);
                labels.add(currLabel);
            }
            set.close();
        } catch (SQLException e) {
            throw new OracleDataAccessObjectException(e);
        } finally {
            closeConnection();
        }
        return labels;
    }

    /**
    * Returns list of child labels od the lable with specified id.
    * @param id label's id.
    * @return list of child labels od the lable with specified id.
    * @throws OracleDataAccessObjectException if problems while getting data.
    */
    public List getChildLabels(int id) 
            throws OracleDataAccessObjectException {
        List labels = null;
        try {
            labels = new LinkedList();
            Label currLabel;
            getConnection();
            
            statement = connection.prepareStatement(
                    GET_CHILD_LABELS);
            statement.setInt(1,id);
            
            ResultSet set = statement.executeQuery();
            
            while(set.next()){
                currLabel = fillLabelBean(set, SHORT_MODE);
                labels.add(currLabel);
            }
            set.close();
        } catch (SQLException e) {
            throw new OracleDataAccessObjectException(e);
        } finally {
            closeConnection();
        }
        return labels;
    }

    /**
     * Returns the path to the specified label in the hierarchy of labels.
     * @param id id of the label.
     * @return path to the specified label in the hierarchy of labels.
     * @throws OracleDataAccessObjectException if problems while getting data.
     */ 
    public List getLabelPath(int id) 
            throws OracleDataAccessObjectException {
        
        List labels = null;
        
        try {
            Label currLabel;
            getConnection();
            labels = new LinkedList();

            statement = 
                connection.prepareStatement(
                        LABEL_GET_PATH);
            statement.setInt(1, id);

            ResultSet set = statement.executeQuery();
            
            while(set.next()){
                currLabel = fillLabelBean(set, SHORT_MODE);
                labels.add(currLabel);
            }
            set.close();
        } catch (SQLException e) {
            throw new OracleDataAccessObjectException(e);
        } finally {
            closeConnection();
        }
    return labels;
    }

    /**
     * Returns maximal id of the label in storage.
     * @return maximal id of the label in storage.
     * @throws OracleDataAccessObjectException if problems while getting data.
     */
    public int getLabelNumber() 
            throws OracleDataAccessObjectException {
        int result = -1;
        try {
            getConnection();
            
            statement = connection.prepareStatement(LABEL_MAX_ROW);
            
            ResultSet set = statement.executeQuery();
            set.next();
            
            result = set.getInt(1);
            set.close();
        } catch (SQLException e) {
            throw new OracleDataAccessObjectException(e);
        } finally {
            closeConnection();
        }
        return result;
    }
}

