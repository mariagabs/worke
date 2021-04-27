package DAO.basis;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class  AbstractDAO<T> {

    /*protected ResultSet findbyId(String tablename, Integer id){
        ResultSet rs= null;
        try {
            // the following lines are not working
            //pStmt = cn.prepareStatement("SELECT * FROM "+ tablename+ "WHERE id = ?");
            //pStmt.setInt(1, id);
            //rs = pStmt.executeQuery();
            int x = 1;

        } catch (SQLException ex) {
            System.out.println("ERROR in findbyid " +ex.getMessage() +ex.getCause());
            ex.printStackTrace();
        }finally{
            return rs;
        }

    }*/

}