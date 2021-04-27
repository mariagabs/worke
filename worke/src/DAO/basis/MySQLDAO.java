package DAO.basis;

import comuns.basis.Entidade;

import java.sql.*;
import java.util.ArrayList;


public abstract class MySQLDAO <E extends Entidade> {
    final String STRING_CONEXAO = "jdbc:mysql://localhost/worke?useTimezone=true&serverTimezone=UTC";
    final String USUARIO = "root";
    final String SENHA = "";
    private String tabela;

    protected Class<E> entityClass;

    public MySQLDAO(Class<E> entityClass){
        this.entityClass = entityClass;
    }

    protected void setTabela(String value){
        tabela = value;
    }

    public abstract E seleciona(int id);

    protected E getInstanceOfE()
    {
        try
        {
            return entityClass.newInstance();
        }
        catch (IllegalAccessException | InstantiationException e)
        {
            // Oops, no default constructor
            throw new RuntimeException(e);
        }
    }
    /** método para retornar dados do banco, sem retorno
    definido pq ainda nao temos as classes */

    public E localiza (String codigo) throws SQLException {
        E entidade = null;
        try (Connection conexao = DriverManager.getConnection(STRING_CONEXAO, USUARIO, SENHA )) {
            String SQL = getLocalizaCommand();
            try (PreparedStatement stmt = conexao.prepareStatement(SQL)) {
                stmt.setString(1, codigo);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.first()){
                        entidade = preencheEntidade(rs);
                    }
                }
            }
        }
        return entidade;

    }

    public ArrayList<E> lista() throws SQLException {
        ArrayList<E> entidades = new ArrayList();
        try (Connection conexao = DriverManager.getConnection(STRING_CONEXAO, USUARIO, SENHA)) {
            String SQL = getListaCommand();
            try (PreparedStatement stmt = conexao.prepareStatement(SQL)) {
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()){
                        E entidade = preencheEntidade(rs);
                        entidades.add(entidade);
                    }
                }
            }
        }

        return entidades;
    }

    protected abstract String getLocalizaCommand();
    protected abstract E preencheEntidade(ResultSet rs);

    protected String getListaCommand() {
        return "select * from " + tabela;
    }

}
