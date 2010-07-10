/*
 * Serhiy Stetsyun and Parhomenko Andrey
 * Copyright (c) 2010 Sumy State University. Thre are no rights. 
 * 
 * This software is free and anyone can use it (for his own risk ).
 */

package ua.edu.sumdu.lab3.model;

import java.util.Map;
import ua.edu.sumdu.lab3.dao.*;

/**
 * Implementation of Factory pattern for creating objects for working with different data storages
 * @version   9.03 8 June
 * @author    Serhiy Stetsyun
 * @author    Parhomenko Andrey
 */
public class DaoFactory {

    public static final int TYPE_ORA = 0;
    public static final int TYPE_EJB = 1;
    /**
    * Create or return (singleton was used)  instance of DAO realization by given paramerter
    * @param type of DAO
    * @return  DAO instance
    */
    public static OperableDAO getDao(int type) {
        
        if(type == TYPE_ORA) {
            OracleDAO oracledao = OracleDAO.getInstance();
            return oracledao;
        } else 
        if(type == TYPE_EJB) {
            EjbDAO ejbdao = EjbDAO.getInstance();
            return ejbdao;
        }
        
        return null;
    }
}
