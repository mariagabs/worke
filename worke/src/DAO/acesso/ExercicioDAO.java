package DAO.acesso;

import DAO.basis.MySQLDAO;
import comuns.acesso.Funcionario;
import comuns.acesso.Usuario;
import comuns.conteudo.Exercicio;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
        String sql = "INSERT INTO rotina_exercicios (QuantidadeExercicios, Intervalo, UsuarioId) VALUES (?,?,?)";

        int rotinaId = 0;

        int quantidade = (int) (((objt.getHoraTermino() - objt.getHoraInicio()) - 1) / objt.getIntervaloExercicios());

        try {

            if (this.connection.connection()) {

                PreparedStatement sentenca = this.connection.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

                sentenca.setInt(1, objt.getExercicios().size());
                sentenca.setDouble(2, objt.getIntervaloExercicios());
                sentenca.setInt(3, objt.getId());

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

                sentenca.execute();
                sentenca.close();
                this.connection.getConnection().close();


            }

            return rotinaId;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

}
