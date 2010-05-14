package ua.edu.sumdu.lab3.model;

import java.util.Map;

public class DaoFactory {

    public static OperableDAO getDao(Map dbProps) {
        
        if("oracle".equals(dbProps.get("name"))) {
            OracleDAO oracledao = OracleDAO.getInstance();
            oracledao.init(
                    (String)dbProps.get("url"), 
                    (String)dbProps.get("user"), 
                    (String)dbProps.get("pass"));
            return oracledao;
        }
        
        return null;
    }
}
