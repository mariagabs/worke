package DAO.basis;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface AbstractDAO<T> {

    public void inserir(T objt);

    public void alterar(T objt);

    public void excluir(int id);

    public ArrayList<T> listar();

}