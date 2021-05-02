package DAO.basis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDAO {
    private String servidor;
    private String banco;
    private String usuario;
    private String senha;
    private Connection conexao;

    public ConnectionDAO() {
        this.servidor = "";
        this.banco = "";
        this.usuario = "";
        this.senha = "";
    }

    public boolean connect() {
        try {
            this.conexao = DriverManager.getConnection("jdbc:mysql://" + this.servidor +
                    "/" + this.banco, this.usuario, this.senha);
            return true;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }

    public Connection getConnection() {
        return conexao;
    }
}