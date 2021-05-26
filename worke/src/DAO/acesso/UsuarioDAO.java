package DAO.acesso;

import DAO.basis.AbstractDAO;
import DAO.basis.ConnectionDAO;
import DAO.basis.MySQLDAO;
import comuns.acesso.*;
import comuns.basis.Entidade;
import comuns.conteudo.Exercicio;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
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

    public void excluirExercicios(List<Integer> idList) {

        String sql = "SELECT Id FROM exercicio_escolhido WHERE RotinaId = ?";
        String sqlDeleteExercicios = "DELETE FROM exercicio_escolhido WHERE id = ?";
        ArrayList<Integer> exercicioIdList = new ArrayList<Integer>();

        try {

            if (this.connection.connection()) {

                for (Integer id : idList) {

                    PreparedStatement sentenca = this.connection.getConnection().prepareStatement(sql);
                    sentenca.setInt(1, id);

                    ResultSet resultadoSentenca = sentenca.executeQuery();

                    while (resultadoSentenca.next()) {

                        exercicioIdList.add(resultadoSentenca.getInt("Id"));
                    }
                    sentenca.close();
                }
                for (Integer id : exercicioIdList) {

                    PreparedStatement sentenca = this.connection.getConnection().prepareStatement(sqlDeleteExercicios);
                    sentenca.setInt(1, id);
                    sentenca.execute();
                    sentenca.close();
                }
            }
            this.connection.getConnection().close();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    @Override
    public void excluir(int id) {

        String sqlRotina = "SELECT * FROM rotina_exercicios WHERE UsuarioId = ?";
        ArrayList<Integer> rotinaIdList = new ArrayList<Integer>();

        String sql = "DELETE FROM Usuario WHERE id = ?";

        try {

            if (this.connection.connection()) {
                PreparedStatement sentenca = this.connection.getConnection().prepareStatement(sqlRotina);
                sentenca.setInt(1, id);

                ResultSet resultadoSentenca = sentenca.executeQuery();

                while (resultadoSentenca.next()) {

                    rotinaIdList.add(resultadoSentenca.getInt("Id"));
                }
                sentenca.close();
            }
            excluirExercicios(rotinaIdList);
            excluirRotinas(rotinaIdList);
            if (this.connection.connection()) {

                PreparedStatement sentenca = this.connection.getConnection().prepareStatement(sql);
                sentenca.setInt(1, id);
                sentenca.execute();
            }
            this.connection.getConnection().close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void excluirRotinas(ArrayList<Integer> rotinaIdList) {
        String sqlDeleteExercicios = "DELETE FROM rotina_exercicios WHERE id = ?";

        try {

            if (this.connection.connection()) {

                for (Integer id : rotinaIdList) {

                    PreparedStatement sentenca = this.connection.getConnection().prepareStatement(sqlDeleteExercicios);
                    sentenca.setInt(1, id);
                    sentenca.execute();
                    sentenca.close();
                }
            }
            this.connection.getConnection().close();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public Usuario consultarUsuario(int id) {
        Usuario usuario = new Usuario();
        String sql = "SELECT Nome FROM Usuario WHERE Id = ?";

        try {
            if (this.connection.connection()) {
                PreparedStatement sentenca = this.connection.getConnection().prepareStatement(sql);
                sentenca.setInt(1, id);

                ResultSet resultadoSentenca = sentenca.executeQuery();

                while (resultadoSentenca.next()) {

                    usuario.setNome(resultadoSentenca.getString("Nome"));
                }

                sentenca.close();
                this.connection.getConnection().close();
            }

            return usuario;
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
                        userEmpresa.setPossuiPremio(rs.getBoolean("PossuiPremio"));
                        userEmpresa.setPremioId(rs.getInt("PremioId"));

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

                            PremioDAO premioDAO = new PremioDAO();
                            Premio premio;
                            premio = premioDAO.consultar(rs.getInt("PremioId"));

                            Empresa userEmpresa = Empresa.getInstance();
                            userEmpresa.setId(rs.getInt("Id"));
                            userEmpresa.setNome(rs.getString("Nome"));
                            userEmpresa.setEmail(rs.getString("Login"));
                            userEmpresa.setSenha(rs.getString("Senha"));
                            userEmpresa.setFraseMotivacional(rs.getString("FraseMotivacional"));
                            userEmpresa.setPossuiPremio(rs.getBoolean("PossuiPremio"));

                            if(premio != null)
                                userEmpresa.setNomePremio(premio.getDescricao());
                            userEmpresa.setPremioId(rs.getInt("PremioId"));

                            user = (T) userEmpresa;

                        } else {
                            Funcionario userFunc = Funcionario.getInstance();
                            userFunc.setId(rs.getInt("Id"));
                            userFunc.setNome(rs.getString("Nome"));
                            userFunc.setEmail(rs.getString("Login"));
                            userFunc.setSenha(rs.getString("Senha"));
                            userFunc.setLembrete(rs.getString("Lembrete"));
                            userFunc.setRanking(rs.getInt("Ranking"));
                            userFunc.setDuracaoExercicios(rs.getInt("DuracaoExercicio"));
                            userFunc.setIntervaloExercicios(rs.getTime("IntervaloExercicio"));
                            userFunc.setHoraInicio(rs.getString("HorarioInicio"));
                            userFunc.setHoraTermino(rs.getString("HorarioTermino"));

                            user = (T) userFunc;

                            getLastRotina(userFunc);

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

    public ArrayList<Exercicio> consultarExerciciosEscolhidos(int rotinaId) {
        ArrayList<Exercicio> listaExercicios = new ArrayList<Exercicio>();

        String sql = "SELECT exercicio_escolhido.ExercicioId, ex.Imagem AS Imagem, ex.Nome AS Nome FROM Exercicio_Escolhido \n" +
                "INNER JOIN exercicio AS ex ON ex.Id = exercicio_escolhido.ExercicioId WHERE exercicio_escolhido.RotinaId = ?";

        try {
            if (this.connection.connection()) {
                PreparedStatement sentenca = this.connection.getConnection().prepareStatement(sql);

                sentenca.setInt(1, rotinaId);

                ResultSet resultadoSentenca = sentenca.executeQuery();

                while (resultadoSentenca.next()) {

                    Exercicio exercicio = new Exercicio();

                    exercicio.setId(resultadoSentenca.getInt("ExercicioId"));
                    exercicio.setNome(resultadoSentenca.getString("Nome"));
                    exercicio.setImagem(resultadoSentenca.getString("Imagem"));

                    listaExercicios.add(exercicio);
                }

                sentenca.close();
                this.connection.getConnection().close();
            }

            return listaExercicios;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }

    public Integer getLastRotina(Funcionario objt) {
        String sql = "SELECT * FROM Rotina_Exercicios WHERE UsuarioId = ? ORDER BY DataCriacao DESC LIMIT 1";

        int rotinaId = 0;

        try {

            if (this.connection.connection()) {

                PreparedStatement sentenca = this.connection.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

                sentenca.setInt(1, objt.getId());

                ResultSet rs = sentenca.executeQuery();

                if (rs != null) {
                    while (rs.next()) {
                        rotinaId = rs.getInt("Id");
                        Rotina.getInstance().setQntDisponivelExercicios(rs.getInt("QntExerciciosDisponivel"));
                        Rotina.getInstance().setId(rotinaId);
                    }
                }


                sentenca.close();
                this.connection.getConnection().close();
            }

            return rotinaId;
        } catch (
                SQLException ex) {
            throw new RuntimeException(ex);
        }
    }


}
