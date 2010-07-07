package ua.edu.sumdu.lab3.model;

import java.util.Map;
import ua.edu.sumdu.lab3.dao.*;

public class DaoFactory {

    public static final int TYPE_ORA = 0;
    public static final int TYPE_EJB = 1;

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
