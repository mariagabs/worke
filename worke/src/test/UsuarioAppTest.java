package test;

import DAO.acesso.UsuarioDAO;
import application.UsuarioApp;
import comuns.acesso.Usuario;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.junit.Test;
import sample.Main;

import java.io.IOException;

public class UsuarioAppTest {
    @Test
    public void redirect() throws IOException {
        Scene scene;
        Usuario user = null;

        if(user != null) {
            if (user.isAdmEmpresa()) {
                scene = new Scene(FXMLLoader.load(UsuarioApp.class.getResource("/sample/DashboardEmpresa/dashboardEmpresa.fxml")));
            } else if (user.getSenha().equals("Trocar123*")) {
                scene = new Scene(FXMLLoader.load(UsuarioApp.class.getResource("/sample/CriarSenha/criarSenha.fxml")));
            } else {
                scene = new Scene(FXMLLoader.load(UsuarioApp.class.getResource("/sample/DashboardFuncionario/dashboardFuncionario.fxml")));
            }


            Stage stage = new Stage();
            stage.getIcons().add(new Image(Main.class.getResourceAsStream("/resources/img/w!.png")));
            stage.setUserData(user);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        }
    }

    @Test
    public void consultarUsuario() {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        String email = "", senha = "";
        usuarioDAO.consultar(email, senha);
    }

    @Test
    public void isValidSenha() {
        String novaSenha = "", confirmaSenha = "";
        novaSenha.equals(confirmaSenha);
    }

    @Test
    public void isFirstSenha() {
        String senha = "Trocar123*";
        boolean isFirstSenha = senha == "Trocar123*";
    }

    @Test
    public void saveSenha() {
        String novaSenha = "";
        Usuario user = new Usuario();
        UsuarioDAO dao = new UsuarioDAO();
        user.setSenha(novaSenha);
        try {
            dao.alterar(user);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void isInvalid() {
        String nomeCompleto = "", email = "";
        boolean isInvalid = nomeCompleto.trim().isEmpty() || email.trim().isEmpty();
    }

    @Test
    public void saveUser() {
        String nomeCompleto = "", email = "";
        Usuario user = new Usuario();
        Usuario usuario = new Usuario();
        Usuario usuarioLogado = new Usuario();
        user.setAdmEmpresa(false);
        user.setNome(nomeCompleto);
        user.setEmail(email);

        UsuarioDAO dao = new UsuarioDAO();
        if (usuario != null && usuario.getId() > 0) {
            user.setId(usuarioLogado.getId());
            dao.alterar(user);
        } else {
            dao.inserir(user);
        }
    }
}
