package DAO.acesso;

import DAO.basis.MySQLDAO;
import comuns.acesso.ExercicioEscolhido;
import comuns.acesso.Funcionario;
import comuns.acesso.Rotina;
import comuns.acesso.Usuario;
import comuns.conteudo.Exercicio;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ExercicioDAO {

    private MySQLDAO connection;

    public ExercicioDAO() {
        this.connection = new MySQLDAO();
    }

    public ArrayList<Exercicio> listar() {
        ArrayList<Exercicio> listaExercicio = new ArrayList<Exercicio>();

        String sql = "SELECT * FROM exercicio";

        try {
            if (this.connection.connection()) {
                PreparedStatement sentenca = this.connection.getConnection().prepareStatement(sql);

                ResultSet resultadoSentenca = sentenca.executeQuery();

                while (resultadoSentenca.next()) {

                    Exercicio exercicio = new Exercicio();

                    exercicio.setId(resultadoSentenca.getInt("id"));
                    exercicio.setNome(resultadoSentenca.getString("Nome"));
                    exercicio.setDescricao(resultadoSentenca.getString("Descricao"));
                    exercicio.setImagem(resultadoSentenca.getString("Imagem"));

                    listaExercicio.add(exercicio);
                }

                sentenca.close();
                this.connection.getConnection().close();
            }

            return listaExercicio;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }

    public ArrayList<ExercicioEscolhido> listarTotalExercicios() {
        ArrayList<Rotina> rotinaList = new ArrayList<Rotina>();
        ArrayList<ExercicioEscolhido> listaExercicio = new ArrayList<ExercicioEscolhido>();

        listarRotinaUsuario(rotinaList);
        listarExerciciosUsuario(rotinaList, listaExercicio);

        return listaExercicio;
    }

    public void listarRotinaUsuario(ArrayList<Rotina> rotinaList) {
        String sql = "SELECT * FROM rotina_exercicios WHERE UsuarioId = ?";

        try {
            if (this.connection.connection()) {
                PreparedStatement sentenca = this.connection.getConnection().prepareStatement(sql);
                sentenca.setInt(1, Funcionario.getInstance().getId());

                ResultSet resultadoSentenca = sentenca.executeQuery();

                while (resultadoSentenca.next()) {

                    Rotina rotina = new Rotina();

                    rotina.setId(resultadoSentenca.getInt("id"));
                    rotina.setUsuarioId(resultadoSentenca.getInt("UsuarioId"));
                    rotina.setQuantidadeExercicios(resultadoSentenca.getInt("QuantidadeExercicios"));
                    rotina.setDuracaoExercicios(resultadoSentenca.getDouble("DuracaoTotalExercicios"));
                    rotina.setDataCriacao(resultadoSentenca.getDate("DataCriacao"));

                    rotinaList.add(rotina);
                }

                sentenca.close();
                this.connection.getConnection().close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public ArrayList<ExercicioEscolhido> listarExerciciosUsuario(ArrayList<Rotina> rotinaList, ArrayList<ExercicioEscolhido> listaExercicio) {
        String sql = "SELECT * FROM exercicio_escolhido WHERE RotinaId = ? ORDER BY QntRealizado DESC";
        try {
            if (this.connection.connection()) {
                for (Rotina rotina : rotinaList) {
                    PreparedStatement sentenca = this.connection.getConnection().prepareStatement(sql);
                    sentenca.setInt(1, rotina.getId());

                    ResultSet resultadoSentenca = sentenca.executeQuery();

                    while (resultadoSentenca.next()) {

                        ExercicioEscolhido exercicioEscolhido = new ExercicioEscolhido();

                        exercicioEscolhido.setId(resultadoSentenca.getInt("id"));
                        exercicioEscolhido.setExercicioId(resultadoSentenca.getInt("ExercicioId"));
                        exercicioEscolhido.setQntRealizado(resultadoSentenca.getInt("QntRealizado"));
                        exercicioEscolhido.setDuracao(resultadoSentenca.getInt("Duracao"));
                        exercicioEscolhido.setDataExecucao(resultadoSentenca.getDate("DataExecucao"));

                        listaExercicio.add(exercicioEscolhido);
                    }

                    sentenca.close();
                }

                this.connection.getConnection().close();
            }
            return listaExercicio;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    public void escolherExercicio(Funcionario objt) {

        String sql = "INSERT INTO exercicio_escolhido (ExercicioId, Duracao, RotinaId) VALUES (?,?,?)";

        int rotinaId = createRotina(objt);

        try {

            if (this.connection.connection()) {

                PreparedStatement sentenca = this.connection.getConnection().prepareStatement(sql);

                for (Exercicio ex : objt.getExercicios()) {
                    sentenca.setInt(1, ex.getId());
                    sentenca.setDouble(2, objt.getDuracaoExercicios());
                    sentenca.setInt(3, rotinaId);

                    sentenca.execute();
                }

                sentenca.close();
                this.connection.getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public Integer createRotina(Funcionario objt) {
        String sql = "INSERT INTO rotina_exercicios (QuantidadeExercicios, UsuarioId, DataCriacao, DuracaoTotalExercicios) VALUES (?,?,?,0)";

        int rotinaId = 0;

        try {

            if (this.connection.connection()) {

                PreparedStatement sentenca = this.connection.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

                java.util.Date data = new java.util.Date();
                java.sql.Timestamp date = new java.sql.Timestamp(data.getTime());

                sentenca.setInt(1, objt.getExercicios().size());
                sentenca.setInt(2, objt.getId());
                sentenca.setTimestamp(3, date);

                int affectedRows = sentenca.executeUpdate();

                if (affectedRows == 0) {
                    throw new SQLException("Creating reward failed, no rows affected.");
                }

                try (ResultSet generatedKeys = sentenca.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        rotinaId = generatedKeys.getInt(1);
                        Rotina rotina = Rotina.getInstance();
                        rotina.setId(rotinaId);
                    } else {
                        throw new SQLException("Creating reward failed, no ID obtained.");
                    }
                }

                sentenca.close();
                this.connection.getConnection().close();

            }

            return rotinaId;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void updateRotina() {
        String sql = "UPDATE rotina_exercicios SET DuracaoTotalExercicios = DuracaoTotalExercicios + ? WHERE Id = ?";

        try {

            if (this.connection.connection()) {

                PreparedStatement sentenca = this.connection.getConnection().prepareStatement(sql);

                sentenca.setInt(1, Funcionario.getInstance().getDuracaoExercicios());
                sentenca.setInt(2, Rotina.getInstance().getId());

                sentenca.execute();
                sentenca.close();
                this.connection.getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void exercicioFeito(Exercicio exercicio) {
        String sql = "UPDATE exercicio_escolhido SET DataExecucao = CURRENT_DATE, QntRealizado = QntRealizado + 1 WHERE ExercicioId = ? AND RotinaId = ?";

        try {

            if (this.connection.connection()) {

                PreparedStatement sentenca = this.connection.getConnection().prepareStatement(sql);

                sentenca.setInt(1, exercicio.getId());
                sentenca.setInt(2, Rotina.getInstance().getId());

                sentenca.execute();
                sentenca.close();
                this.connection.getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        updateRotina();
    }

    public List<String> listarInstrucoes(int id) {
        ArrayList<String> listaInstrucoes = new ArrayList<String>();

        String sql = "SELECT Descricao FROM instrucao WHERE ExercicioId = ? ORDER BY Sequencia";

        try {
            if (this.connection.connection()) {
                PreparedStatement sentenca = this.connection.getConnection().prepareStatement(sql);

                sentenca.setInt(1, id);

                ResultSet resultadoSentenca = sentenca.executeQuery();

                while (resultadoSentenca.next()) {

                    listaInstrucoes.add(resultadoSentenca.getString("Descricao"));
                }

                sentenca.close();
                this.connection.getConnection().close();
            }

            return listaInstrucoes;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }

}
