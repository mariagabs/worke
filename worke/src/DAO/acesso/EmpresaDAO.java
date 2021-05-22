package DAO.acesso;

import DAO.basis.AbstractDAO;
import DAO.basis.MySQLDAO;
import comuns.acesso.Empresa;
import comuns.acesso.Rotina;
import comuns.acesso.Usuario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class EmpresaDAO implements AbstractDAO<Empresa> {

    private MySQLDAO connection;

    public EmpresaDAO()  {
        this.connection = new MySQLDAO();
    }


    @Override
    public void inserir(Empresa objt) {

    }

    @Override
    public void alterar(Empresa objt) {

    }

    @Override
    public void excluir(int id) {

    }

    @Override
    public <T> T consultar(String login, String senha) {
        return null;
    }

    @Override
    public ArrayList<Empresa> listar() {
        return null;
    }

}
