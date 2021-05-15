package DAO.acesso;

import DAO.basis.MySQLDAO;
import comuns.acesso.Funcionario;
import comuns.acesso.Usuario;
import comuns.conteudo.Exercicio;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;

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
        String sql = "INSERT INTO rotina_exercicios (QuantidadeExercicios, UsuarioId, DataCriacao) VALUES (?,?,?)";

        int rotinaId = 0;

        /*LocalTime horaInicio = LocalTime.parse(objt.getHoraInicio());
        LocalTime horaFinal = LocalTime.parse(objt.getHoraTermino());
        double teste = (double) ((objt.getIntervaloExercicios().getHours() * 60) + objt.getIntervaloExercicios().getMinutes()) / 60;

        int quantidade = Math.round((int) ((int) (((Duration.between(horaInicio, horaFinal).toHours()) - 1)) / teste));*/

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

}
