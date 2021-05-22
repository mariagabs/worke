package DAO.acesso;

import DAO.basis.MySQLDAO;
import comuns.acesso.Funcionario;
import comuns.acesso.Rotina;
import comuns.conteudo.Exercicio;

import java.sql.*;

public class FuncionarioDAO {

    private MySQLDAO connection;

    public FuncionarioDAO() {
        this.connection = new MySQLDAO();
    }

    public void alterarFuncionario(Funcionario objt) {
        String sql = "UPDATE Usuario SET Login = ? , Nome = ?, Lembrete = ?, DuracaoExercicio = ?, IntervaloExercicio = ?, " +
                "HorarioInicio = ?, HorarioTermino = ? WHERE id = ?";

        try {

            if (this.connection.connection()) {

                PreparedStatement sentenca = this.connection.getConnection().prepareStatement(sql);

                sentenca.setString(1, objt.getEmail());
                sentenca.setString(2, objt.getNome());
                sentenca.setString(3, objt.getLembrete());
                sentenca.setDouble(4, objt.getDuracaoExercicios());
                sentenca.setTime(5, objt.getIntervaloExercicios());
                sentenca.setString(6, objt.getHoraInicio());
                sentenca.setString(7, objt.getHoraTermino());
                sentenca.setInt(8, objt.getId());


                sentenca.execute();
                sentenca.close();
                this.connection.getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public Date getLastDateDone() {
        String sql = "SELECT DataExecucao FROM exercicio_escolhido WHERE RotinaId = ? ORDER BY DataExecucao DESC LIMIT 1";

        Date lastData = null;

        try {

            if (this.connection.connection()) {

                PreparedStatement sentenca = this.connection.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

                sentenca.setInt(1, Rotina.getInstance().getId());

                ResultSet rs = sentenca.executeQuery();

                if (rs != null) {
                    while (rs.next()) {
                        lastData = rs.getDate("DataExecucao");
                    }
                }


                sentenca.close();
                this.connection.getConnection().close();
            }

            return lastData;
        } catch (
                SQLException ex) {
            throw new RuntimeException(ex);
        }
    }


}
