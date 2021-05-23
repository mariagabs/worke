package DAO.acesso;

import DAO.basis.AbstractDAO;
import DAO.basis.MySQLDAO;
import comuns.acesso.Empresa;
import comuns.acesso.Rotina;
import comuns.acesso.Usuario;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class EmpresaDAO implements AbstractDAO<Empresa> {

    private MySQLDAO connection;

    public EmpresaDAO()  {
        this.connection = new MySQLDAO();
    }


    @Override
    public void inserir(Empresa objt) {

    }

    @Override
    public void alterar(Empresa objt) {
        String sql = "UPDATE Usuario SET PossuiPremio = ?, PremioId = ? WHERE Id = ?";

        try {

            if (this.connection.connection()) {

                PreparedStatement sentenca = this.connection.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                sentenca.setBoolean(1, objt.isPossuiPremio());
                if (objt.getPremioId() == null){
                    sentenca.setNull(2, Types.INTEGER);
                } else {
                    sentenca.setInt(2, objt.getPremioId());
                }
                sentenca.setInt(3, objt.getId());

                sentenca.executeUpdate();

                sentenca.close();
                this.connection.getConnection().close();
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void excluir(int id) {

    }

    @Override
    public <T> T consultar(String login, String senha) {
        return null;
    }

    @Override
    public ArrayList<Empresa> listar() {
        return null;
    }

}
