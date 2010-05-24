package ua.edu.sumdu.lab3.model;

import java.util.Map;
import ua.edu.sumdu.lab3.dao.OracleDAO;
public class DaoFactory {

    public static OperableDAO getDao(String name) {
        
        if("oracle".equals(name)) {
            OracleDAO oracledao = OracleDAO.getInstance();
            return oracledao;
        }
        
        return null;
    }
}
