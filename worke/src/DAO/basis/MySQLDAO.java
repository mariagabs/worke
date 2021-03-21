package DAO.basis;

import java.sql.*;


public class MySQLDAO {
    final String STRING_CONEXAO = "";
    final String USUARIO = "root";
    final String SENHA = "";
    private String tabela;

    protected void setTabela(String value){
        tabela = value;
    }

    /** método para retornar dados do banco, sem retorno
    definido pq ainda nao temos as classes */
    /*public void List (String codigo) throws SQLException {

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

    }*/

}
