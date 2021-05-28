package sample.Login;

import DAO.auditoria.AuditoriaTest;
import application.UsuarioApp;
import comuns.acesso.Usuario;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private TextField email;

    @FXML
    private TextField senha;

    @FXML
    private Label dadosIncorretos;

    @FXML
    private Button entrar;

    public static Usuario usuarioLogado;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        entrar.setOnAction(entrarEvent);

        senha.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getEventType() == KeyEvent.KEY_PRESSED) {
                if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                    entrar.fire();
                }
            }
        });
    }

    private boolean isInvalid(Usuario user) {
        return user == null || user.getId() == 0;
    }

    private void login() {
        try {
            AuditoriaTest.getInstance().StartThread("Login");
            Usuario user = UsuarioApp.consultarUsuario(email.getText(), senha.getText());
            dadosIncorretos.setVisible(isInvalid(user));
            if (!isInvalid(user)) {
                usuarioLogado = user;
                UsuarioApp.redirect(user);
                Stage stage = (Stage) entrar.getScene().getWindow();
                stage.close();
            }
        } catch (InterruptedException | IOException ioException) {
            ioException.printStackTrace();
        }
    }

    EventHandler<ActionEvent> entrarEvent = e -> login();


}
