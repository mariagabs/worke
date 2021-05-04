package DAO.acesso;

import DAO.basis.AbstractDAO;
import DAO.basis.ConnectionDAO;
import DAO.basis.MySQLDAO;
import comuns.acesso.Usuario;
import comuns.basis.Entidade;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuarioDAO implements AbstractDAO<Usuario> {

    private MySQLDAO connection;

    public UsuarioDAO()  {
        this.connection = new MySQLDAO();
    }

    @Override
    public void inserir(Usuario objt) {

        String sql = "INSERT INTO Usuario (Login, Senha, Nome, AdmEmpresa) VALUES (?,?,?,?)";

        try {

            if (this.connection.connection()) {

                PreparedStatement sentenca = this.connection.getConnection().prepareStatement(sql);

                sentenca.setString(1, objt.getEmail());
                sentenca.setString(2, objt.getSenha());
                sentenca.setString(3, objt.getNome());
                sentenca.setBoolean(4, objt.isAdmEmpresa());


                sentenca.execute();
                sentenca.close();
                this.connection.getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }

    @Override
    public void alterar(Usuario objt) {
        String sql = "UPDATE Usuario SET Login = ? , Senha = ? , Nome = ? , AdmEmpresa = ? WHERE id = ?";

        try {

            if (this.connection.connection()) {

                PreparedStatement sentenca = this.connection.getConnection().prepareStatement(sql);

                sentenca.setString(1, objt.getEmail());
                sentenca.setString(2, objt.getSenha());
                sentenca.setString(3, objt.getNome());
                sentenca.setBoolean(4, objt.isAdmEmpresa());
                sentenca.setInt(5, objt.getId());


                sentenca.execute();
                sentenca.close();
                this.connection.getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void excluir(int id) {

        String sql = "DELETE FROM Usuario WHERE id = ?";

        try {

            if (this.connection.connection()) {

                PreparedStatement sentenca = this.connection.getConnection().prepareStatement(sql);

                sentenca.setInt(1, id);


                sentenca.execute();
                sentenca.close();
                this.connection.getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public ArrayList<Usuario> listar() {
        ArrayList<Usuario> listaUsuario = new ArrayList<Usuario>();

        String sql = "SELECT * FROM Usuario ORDER BY id";

        try
        {
            if(this.connection.connection())
            {
                PreparedStatement sentenca = this.connection.getConnection().prepareStatement(sql);

                ResultSet resultadoSentenca = sentenca.executeQuery();

                while(resultadoSentenca.next())
                {

                    Usuario usuario = new Usuario();

                    usuario.setId(resultadoSentenca.getInt("id"));
                    usuario.setNome(resultadoSentenca.getString("Nome"));
                    usuario.setEmail(resultadoSentenca.getString("Login"));
                    usuario.setSenha(resultadoSentenca.getString("Senha"));
                    usuario.setAdmEmpresa(resultadoSentenca.getBoolean("AdmEmpresa"));

                    listaUsuario.add(usuario);
                }

                sentenca.close();
                this.connection.getConnection().close();
            }

            return listaUsuario;
        }
        catch(SQLException ex)
        {
            throw new RuntimeException(ex);
        }

    }

    public Usuario consultar(String login, String senha) {

        Usuario usuario = new Usuario();

        String sql = "SELECT * FROM Usuario WHERE Login = ? AND Senha = ?";

        try {
            if(this.connection.connection()) {
                PreparedStatement sentenca = this.connection.getConnection().prepareStatement(sql);

                sentenca.setString(1, login);
                sentenca.setString(2, senha);

                ResultSet rs = sentenca.executeQuery();

                if(rs != null) {
                    while(rs.next()) {
                        usuario.setId(rs.getInt("Id"));
                        usuario.setEmail(rs.getString("Login"));
                        usuario.setSenha(rs.getString("Senha"));
                        usuario.setAdmEmpresa(rs.getBoolean("AdmEmpresa"));
                        usuario.setNome(rs.getString("Nome"));
                    }
                }

                sentenca.close();
                this.connection.getConnection().close();

                return usuario;
            }

        } catch(Exception ex) {
            throw new RuntimeException(ex);
        }

        return null;
    }

}
