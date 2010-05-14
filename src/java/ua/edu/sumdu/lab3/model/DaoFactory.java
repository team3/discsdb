package ua.edu.sumdu.lab3.model;

import java.util.Map;

public class DaoFactory {

    public static OperableDAO getDao(String name) {
        
        if("oracle".equals(name)) {
            OracleDAO oracledao = OracleDAO.getInstance();
            return oracledao;
        }
        
        return null;
    }
}
