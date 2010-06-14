package ua.edu.sumdu.lab3.dao;


import javax.naming.*;
import javax.transaction.*;
import ua.edu.sumdu.lab3.exceptions.*;

public class UserTransactionManager {
	public static UserTransaction transaction = null;
	    
	public static void transBegin() throws OracleDataAccessObjectException {
		try {
			Context iContext = new InitialContext();
			transaction = (UserTransaction) iContext.lookup("UserTransaction");
			System.out.println(transaction);
			System.out.println("TRansaction found");
			transaction.begin();
			System.out.println("TRansaction began");
		} catch (NotSupportedException e) {
            throw new OracleDataAccessObjectException(e);
        } catch (SystemException e){
            throw new OracleDataAccessObjectException(e);
        } catch (NamingException e) {
			throw new OracleDataAccessObjectException(e);
		}
	}
	
	public static void transCommit() throws OracleDataAccessObjectException {
		try {
			transaction.commit();
		} catch (RollbackException e) {
            throw new OracleDataAccessObjectException(e);
        } catch (SystemException e){
            throw new OracleDataAccessObjectException(e);
        } catch (HeuristicMixedException e){
            throw new OracleDataAccessObjectException(e);
		} catch (HeuristicRollbackException e){
			throw new OracleDataAccessObjectException(e);
		}
	}
    
    
    public static void transRollback() throws OracleDataAccessObjectException {
		try {
			transaction.rollback();
		} catch (SystemException e){
            throw new OracleDataAccessObjectException(e);
        }
	}
}
