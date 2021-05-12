
package DAO.acesso;

        import DAO.basis.AbstractDAO;
        import DAO.basis.MySQLDAO;
        import comuns.acesso.Empresa;
        import comuns.acesso.Funcionario;
        import comuns.acesso.Premio;
        import comuns.acesso.Usuario;

        import java.sql.PreparedStatement;
        import java.sql.ResultSet;
        import java.sql.SQLException;
        import java.sql.Statement;
        import java.util.ArrayList;

public class PremioDAO implements AbstractDAO<Premio> {

    private MySQLDAO connection;

    public PremioDAO()  {
        this.connection = new MySQLDAO();
    }


    public Integer inserirPremio(String premio) {

        String sql = "INSERT INTO Premio (Descricao) VALUES (?)";

        int premioId = 0;

        try {

            if (this.connection.connection()) {

                PreparedStatement sentenca = this.connection.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

                sentenca.setString(1, premio);

                int affectedRows = sentenca.executeUpdate();

                if (affectedRows == 0) {
                    throw new SQLException("Creating reward failed, no rows affected.");
                }

                try (ResultSet generatedKeys = sentenca.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        premioId = generatedKeys.getInt(1);
                    }
                    else {
                        throw new SQLException("Creating reward failed, no ID obtained.");
                    }
                }
                sentenca.close();
                this.connection.getConnection().close();
            }
            return premioId;

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }
    @Override
    public void inserir(Premio objt) {

    }

    @Override
    public void alterar(Premio objt) {

    }

    @Override
    public void excluir(int id) {

    }

    public <T> T consultar(Integer id) {
        T pre = null;

        String sql = "SELECT * FROM Premio WHERE Id = ?";

        try {
            if (this.connection.connection()) {
                PreparedStatement sentenca = this.connection.getConnection().prepareStatement(sql);

                sentenca.setInt(1, id);

                ResultSet rs = sentenca.executeQuery();

                if (rs != null) {
                    while (rs.next()) {
                        Premio premio = Premio.getInstance();
                        premio.setId(rs.getInt("Id"));
                        premio.setDescricao(rs.getString("Descricao"));
                        pre = (T) premio;
                    }
                }

                sentenca.close();
                this.connection.getConnection().close();

                return pre;
            }

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

        return null;

    }
    @Override
    public <T> T consultar(String login, String senha) {
        return null;
    }

    @Override
    public ArrayList<Premio> listar() {
        return null;
    }
}
