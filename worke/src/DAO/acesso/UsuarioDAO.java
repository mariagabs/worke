package DAO.acesso;

import DAO.basis.MySQLDAO;
import comuns.acesso.Usuario;
import comuns.basis.Entidade;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuarioDAO <E extends Entidade> extends MySQLDAO {
    public UsuarioDAO() {
        super(Usuario.class);
        setTabela("Usuario");
    }

    @Override
    protected E preencheEntidade(ResultSet rs) {
        Usuario entidade = new Usuario();
        try {
            entidade.setEmail(rs.getString("Login"));
            entidade.setSenha(rs.getString("Senha"));
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (E)entidade;
    }

    @Override
    public Entidade seleciona(int id) {
        // Não há retorno por id
        return null;
    }

    @Override
    protected String getLocalizaCommand() {
        return "select * from Usuario where Login = ?";
    }
}
