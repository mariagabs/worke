package application;

import DAO.acesso.EmpresaDAO;
import DAO.acesso.UsuarioDAO;
import DAO.auditoria.AuditoriaTest;
import comuns.acesso.Usuario;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import sample.DashboardEmpresa.DashboardEmpresaController;
import sample.Main;

import java.io.IOException;

public class UsuarioApp {

    public static void redirect(Usuario user) throws IOException {
        Scene scene;
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
    
    public static Usuario consultarUsuario(String email, String senha){
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        return usuarioDAO.consultar(email, senha);
    }

    public static boolean isValidSenha(String novaSenha, String confirmaSenha){
        return novaSenha.equals(confirmaSenha);
    }

    public static boolean isFirstSenha(String senha){
        return senha == "Trocar123*";
    }

    public static void saveSenha(String novaSenha, Usuario user){
        UsuarioDAO dao = new UsuarioDAO();
        user.setSenha(novaSenha);
        try {
            dao.alterar(user);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static boolean isInvalid(String nomeCompleto, String email){
        return nomeCompleto.trim().isEmpty() || email.trim().isEmpty();
    }

    public static void saveUser(String nomeCompleto, String email, Usuario usuario, Usuario usuarioLogado){
        Usuario user = new Usuario();
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
