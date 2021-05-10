package DAO.acesso;

import DAO.basis.AbstractDAO;
import DAO.basis.ConnectionDAO;
import DAO.basis.MySQLDAO;
import comuns.acesso.Empresa;
import comuns.acesso.Funcionario;
import comuns.acesso.Usuario;
import comuns.basis.Entidade;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuarioDAO implements AbstractDAO<Usuario> {

    private MySQLDAO connection;

    public UsuarioDAO() {
        this.connection = new MySQLDAO();
    }

    @Override
    public void inserir(Usuario objt) {

        String sql = "INSERT INTO Usuario (Login, Senha, Nome, AdmEmpresa, PossuiPremio) VALUES (?,?,?,?,?)";

        try {

            if (this.connection.connection()) {

                PreparedStatement sentenca = this.connection.getConnection().prepareStatement(sql);

                sentenca.setString(1, objt.getEmail());
                sentenca.setString(2, "Trocar123*");
                sentenca.setString(3, objt.getNome());
                sentenca.setBoolean(4, objt.isAdmEmpresa());
                sentenca.setInt(5, 0);


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
        String sql = "";

        if (objt.getSenha() != null && !objt.getSenha().isEmpty()) {
            sql = "UPDATE Usuario SET Login = ? , Nome = ?, Senha = ? WHERE id = ?";
        } else {
            sql = "UPDATE Usuario SET Login = ? , Nome = ? WHERE id = ?";
        }

        try {

            if (this.connection.connection()) {

                PreparedStatement sentenca = this.connection.getConnection().prepareStatement(sql);

                sentenca.setString(1, objt.getEmail());
                sentenca.setString(2, objt.getNome());


                if (objt.getSenha() != null && !objt.getSenha().isEmpty()) {
                    sentenca.setString(3, objt.getSenha());
                    sentenca.setInt(4, objt.getId());
                } else {
                    sentenca.setInt(3, objt.getId());
                }


                sentenca.execute();
                sentenca.close();
                this.connection.getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void alterarFuncionario(Funcionario objt) {
        String sql = "UPDATE Usuario SET Login = ? , Nome = ?, Lembrete = ? WHERE id = ?";

        try {

            if (this.connection.connection()) {

                PreparedStatement sentenca = this.connection.getConnection().prepareStatement(sql);

                sentenca.setString(1, objt.getEmail());
                sentenca.setString(2, objt.getNome());
                sentenca.setString(3, objt.getLembrete());
                sentenca.setInt(4, objt.getId());


                sentenca.execute();
                sentenca.close();
                this.connection.getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void alterarEmpresa(Empresa objt) {
        String sql = "UPDATE Usuario SET Login = ? , Nome = ?, FraseMotivacional = ? WHERE id = ?";

        try {

            if (this.connection.connection()) {

                PreparedStatement sentenca = this.connection.getConnection().prepareStatement(sql);

                sentenca.setString(1, objt.getEmail());
                sentenca.setString(2, objt.getNome());
                sentenca.setString(3, objt.getFraseMotivacional());
                sentenca.setInt(4, objt.getId());

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

    public ArrayList<Usuario> listarFiltro(String nome) {
        ArrayList<Usuario> listaUsuario = new ArrayList<Usuario>();
        String sql = "SELECT Id, Nome, Login FROM Usuario WHERE Nome LIKE ? AND AdmEmpresa = 0 ORDER BY Nome";

        try {
            if (this.connection.connection()) {
                PreparedStatement sentenca = this.connection.getConnection().prepareStatement(sql);
                sentenca.setString(1, nome + '%');

                ResultSet resultadoSentenca = sentenca.executeQuery();

                while (resultadoSentenca.next()) {

                    Usuario usuario = new Usuario();

                    usuario.setId(resultadoSentenca.getInt("Id"));
                    usuario.setNome(resultadoSentenca.getString("Nome"));
                    usuario.setEmail(resultadoSentenca.getString("Login"));

                    listaUsuario.add(usuario);
                }

                sentenca.close();
                this.connection.getConnection().close();
            }

            return listaUsuario;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }

    @Override
    public ArrayList<Usuario> listar() {
        ArrayList<Usuario> listaUsuario = new ArrayList<Usuario>();

        String sql = "SELECT * FROM Usuario WHERE AdmEmpresa = 0 ORDER BY Nome";

        try {
            if (this.connection.connection()) {
                PreparedStatement sentenca = this.connection.getConnection().prepareStatement(sql);

                ResultSet resultadoSentenca = sentenca.executeQuery();

                while (resultadoSentenca.next()) {

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
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }

    public Empresa listarDadosEmpresa() {

        Empresa userEmpresa = Empresa.getInstance();
        String sql = "SELECT * FROM Usuario WHERE AdmEmpresa = 1";

        try {
            if (this.connection.connection()) {
                PreparedStatement sentenca = this.connection.getConnection().prepareStatement(sql);
                ResultSet rs = sentenca.executeQuery();

                if (rs != null) {
                    while (rs.next()) {

                        userEmpresa.setId(rs.getInt("Id"));
                        userEmpresa.setNome(rs.getString("Nome"));
                        userEmpresa.setFraseMotivacional(rs.getString("FraseMotivacional"));
                        userEmpresa.setPremio(rs.getBoolean("PossuiPremio"));

                    }
                }

                sentenca.close();
                this.connection.getConnection().close();

                return userEmpresa;
            }

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

        return null;
    }

    @Override
    public <T> T consultar(String login, String senha) {
        T user = null;

        String sql = "SELECT * FROM Usuario WHERE Login = ? AND Senha = ?";

        try {
            if (this.connection.connection()) {
                PreparedStatement sentenca = this.connection.getConnection().prepareStatement(sql);

                sentenca.setString(1, login);
                sentenca.setString(2, senha);

                ResultSet rs = sentenca.executeQuery();

                if (rs != null) {
                    while (rs.next()) {
                        boolean adm = rs.getBoolean("AdmEmpresa");

                        if (adm) {
                            Empresa userEmpresa = Empresa.getInstance();
                            userEmpresa.setId(rs.getInt("Id"));
                            userEmpresa.setNome(rs.getString("Nome"));
                            userEmpresa.setEmail(rs.getString("Login"));
                            userEmpresa.setSenha(rs.getString("Senha"));
                            userEmpresa.setFraseMotivacional(rs.getString("FraseMotivacional"));
                            userEmpresa.setPremio(rs.getBoolean("PossuiPremio"));

                            user = (T) userEmpresa;

                        } else {
                            Funcionario userFunc = Funcionario.getInstance();
                            userFunc.setId(rs.getInt("Id"));
                            userFunc.setNome(rs.getString("Nome"));
                            userFunc.setEmail(rs.getString("Login"));
                            userFunc.setSenha(rs.getString("Senha"));
                            userFunc.setLembrete(rs.getString("Lembrete"));
                            userFunc.setRanking(rs.getInt("Ranking"));

                            user = (T) userFunc;

                        }
                    }
                }

                sentenca.close();
                this.connection.getConnection().close();

                return user;
            }

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

        return null;
    }


}
