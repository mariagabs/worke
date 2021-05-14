package DAO.acesso;

import DAO.basis.MySQLDAO;
import comuns.acesso.Funcionario;
import comuns.conteudo.Exercicio;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
                sentenca.setDouble(5, objt.getIntervaloExercicios());
                sentenca.setString(6, String.valueOf(objt.getHoraInicio()));
                sentenca.setString(7, String.valueOf(objt.getHoraTermino()));
                sentenca.setInt(8, objt.getId());


                sentenca.execute();
                sentenca.close();
                this.connection.getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }


}
