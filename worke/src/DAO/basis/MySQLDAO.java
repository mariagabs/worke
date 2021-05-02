package DAO.basis;

import comuns.basis.Entidade;

import java.sql.*;
import java.util.ArrayList;
import java.lang.*;


public class MySQLDAO {
    final String STRING_CONEXAO = "jdbc:mysql://localhost:3306/worke";
    final String USUARIO = "root";
    final String SENHA = "";
    private Connection connection;


    public boolean connection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.connection = DriverManager.getConnection(STRING_CONEXAO,USUARIO,SENHA);
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    public Connection getConnection() {
        return connection;
    }

}
