package sample.CriarSenha;

import DAO.acesso.UsuarioDAO;
import DAO.auditoria.AuditoriaTest;
import application.UsuarioApp;
import comuns.acesso.Usuario;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.DashboardEmpresa.DashboardEmpresaController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CriarSenhaController implements Initializable {

    @FXML
    private Button entrar;
    @FXML
    private TextField novaSenha;
    @FXML
    private TextField confirmarSenha;
    @FXML
    private Label senhaIncorreta;
    @FXML
    private Label senhaDiferente;

    public Usuario usuarioLogado;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        entrar.setPickOnBounds(true);
        entrar.setOnMouseClicked((MouseEvent e) -> createSenha());

    }

    private void showPopUp(){
        try {
            AuditoriaTest.getInstance().StartThread("New Password");
            DashboardEmpresaController.popUpSucessoMensagem("Senha alterada", "Sua senha foi alterada com sucesso!");
        } catch (InterruptedException | IOException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

    private void createSenha() {

        senhaDiferente.setVisible(!UsuarioApp.isValidSenha(novaSenha.getText(), confirmarSenha.getText()));
        senhaIncorreta.setVisible(UsuarioApp.isFirstSenha(novaSenha.getText()));

        if(UsuarioApp.isValidSenha(novaSenha.getText(), confirmarSenha.getText()) && !UsuarioApp.isFirstSenha(novaSenha.getText())) {

            Stage stage = (Stage) entrar.getScene().getWindow();
            usuarioLogado = (Usuario) stage.getUserData();
            UsuarioApp.saveSenha(novaSenha.getText(), usuarioLogado);

            Scene scene = null;
            try {
                scene = new Scene(FXMLLoader.load(getClass().getResource("/sample/DashboardFuncionario/dashboardFuncionario.fxml")));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            Stage stage1 = new Stage();
            stage1.setScene(scene);
            stage1.show();

            showPopUp();

            stage = (Stage) entrar.getScene().getWindow();
            stage.setResizable(false);
            stage.close();
        }
    }


}
