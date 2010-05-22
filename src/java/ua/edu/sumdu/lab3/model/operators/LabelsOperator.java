package ua.edu.sumdu.lab3.model.operators;

import ua.edu.sumdu.lab3.model.exceptions.*;
import ua.edu.sumdu.lab3.model.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;

public class LabelsOperator extends MainOperator {
    
    private static final String ADD_NEW_LABEL = 
            "INSERT INTO LABEL VALUES (SQ_LABEL.nextval, ?, ?, ?, ?)";
            
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
            
    /**
     * Adds new label to the specified storage.
     * @param album new instanse of the Label that should be added.
     * @throws OracleDataAccessObjectException if problems while adding the data.
     */ 
    public void addLabel(Label label) 
            throws OracleDataAccessObjectException {        
        try {
            getConnection();
            if (connection != null) {
                statement = connection.prepareStatement(
                        ADD_NEW_LABEL);
                
                int major = label.getMajor();
                if (major == 0) {       
                    statement.setNull(1, label.getMajor());
                } else {
                    statement.setInt(1, label.getMajor());
                }
                statement.setString(2, label.getName());
                statement.setString(3, label.getLogo());
                statement.setString(4, label.getInfo());
                connection.setAutoCommit(false);
                executeUpdateQuery();
                connection.commit();
            } else {
                throw new OracleDataAccessObjectException("Unable to add label");
            } 
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException exc) {
                throw new OracleDataAccessObjectException(exc);
            }
            throw new OracleDataAccessObjectException(e);
        } finally {
            try {
                connection.setAutoCommit(true);
                closeConnection();
            } catch (SQLException e) {
                throw new OracleDataAccessObjectException(e);
            }
        }
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
            
            if (connection == null){
                throw new OracleDataAccessObjectException("Connection is not created");
            }
            
            statement = connection.prepareStatement(
                    SELECT_LABEL_BY_ID);
            
            statement.setInt(1, id);
            
            ResultSet set = executeResultQuery();
            if(set.next()) {
                label = fillLabelBean(set,FULL_MODE);
            }
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
            
            if (connection == null){
                throw new OracleDataAccessObjectException("Connection is not created");
            }
            
            statement = connection.prepareStatement(
                    SELECT_ALL_LABELS);
            
            ResultSet set = executeResultQuery();
            
            while(set.next()){
                currLabel = fillLabelBean(set,SHORT_MODE);
                labels.add(currLabel);
            }
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
            
            if (connection == null){
                throw new OracleDataAccessObjectException("Connection is not created");
            }
            
            statement = connection.prepareStatement(FIND_LABEL);
            statement.setString(1, name);
            
            ResultSet set = executeResultQuery();
            while(set.next()){
                lid = set.getInt(1);
            }
            
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
            
            if (connection == null){
                throw new OracleDataAccessObjectException("Connection is not created");
            }
            statement = connection.prepareStatement(DELETE_LABEL);
            
            statement.setInt(1, id);
            connection.setAutoCommit(false);
            executeUpdateQuery();
            connection.commit();
        
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException exc) {
                throw new OracleDataAccessObjectException(exc);
            }
            throw new OracleDataAccessObjectException(e);
        } finally {
            try {
                connection.setAutoCommit(true);
                closeConnection();
            } catch (SQLException e) {
                throw new OracleDataAccessObjectException(e);
            }
        }
    }

    /**
     * Edits specified label. Replaces found (by id) label by specified.
     * 
     * @param label to edit/change.
     * @throws OracleDataAccessObjectException if problems while editting data.
     */ 
    public void editLabel(Label label) 
            throws OracleDataAccessObjectException {
        try {
            Label lbl = getLabel(label.getId());
            
            getConnection();
            
            if (connection == null){
                throw new OracleDataAccessObjectException("Connection is not created");
            }
            
            if (lbl == null) {
                throw new OracleDataAccessObjectException("No label with specified id found");
            } 
            
            statement = connection.prepareStatement(EDIT_LABEL);
        
            if (label.getMajor() == 0) {
                statement.setNull(1, label.getMajor());
            } else {
                statement.setInt(1, label.getMajor());
            }
            statement.setString(2, label.getName());
            statement.setString(3, label.getInfo());
            statement.setString(4, label.getLogo());
            statement.setInt(5, label.getId());
            
            connection.setAutoCommit(false);
            executeUpdateQuery();
            connection.commit();
            
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException exc) {
                throw new OracleDataAccessObjectException(e);
            }
            throw new OracleDataAccessObjectException(e);
        } finally {
            try {
                connection.setAutoCommit(true);
                closeConnection();
            } catch (SQLException e) {
                throw new OracleDataAccessObjectException(e);
            } 
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
            
            if (connection == null){
                throw new OracleDataAccessObjectException("Connection is not created");
            }
            
            statement = connection.prepareStatement(
                    GET_MAJOR_LABELS);
            statement.setInt(1,lastRow);
            statement.setInt(2,firstRow);
            
            ResultSet set = executeResultQuery();
            
            while(set.next()){
                currLabel = fillLabelBean(set,SHORT_MODE);
                labels.add(currLabel);
            }
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
            
            if (connection == null){
                throw new OracleDataAccessObjectException("Connection is not created");
            }
            
            statement = connection.prepareStatement(
                    GET_CHILD_LABELS);
            statement.setInt(1,id);
            
            ResultSet set = executeResultQuery();
            
            while(set.next()){
                currLabel = fillLabelBean(set, SHORT_MODE);
                labels.add(currLabel);
            }
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
            
            if (connection == null){
                throw new OracleDataAccessObjectException("Connection is not created");
            }

            statement = 
                connection.prepareStatement(
                        LABEL_GET_PATH);
            statement.setInt(1, id);

            ResultSet set = executeResultQuery();
            
            while(set.next()){
                currLabel = fillLabelBean(set,SHORT_MODE);
                labels.add(currLabel);
            }
            
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
            
            if (connection == null){
                throw new OracleDataAccessObjectException("Connection is not created");
            }
            
            statement = connection.prepareStatement(LABEL_MAX_ROW);
            
            ResultSet set = executeResultQuery();
            set.next();
            
            result = set.getInt(1);
            
        } catch (SQLException e) {
            throw new OracleDataAccessObjectException(e);
        } finally {
            closeConnection();
        }
        return result;
    }
}

