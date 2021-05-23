package sample.Login;

import DAO.acesso.UsuarioDAO;
import DAO.auditoria.AuditoriaTest;
import application.NotificationApp;
import comuns.acesso.Usuario;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    UsuarioDAO usuarioDAO = new UsuarioDAO();

    @FXML
    private ImageView sair;

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

    EventHandler<ActionEvent> entrarEvent = new EventHandler<ActionEvent>() {
        public void handle(ActionEvent e) {
            try {
                AuditoriaTest.getInstance().StartThread("Login");

                Usuario user = usuarioDAO.consultar(email.getText(), senha.getText());

                if (user == null || user.getId() == 0) {
                    dadosIncorretos.setVisible(true);
                } else {
                    dadosIncorretos.setVisible(false);
                    Scene scene;

                    if (user.isAdmEmpresa()) {
                        scene = new Scene(FXMLLoader.load(getClass().getResource("/sample/DashboardEmpresa/dashboardEmpresa.fxml")));
                    } else if (user.getSenha().equals("Trocar123*")) {
                        scene = new Scene(FXMLLoader.load(getClass().getResource("/sample/CriarSenha/criarSenha.fxml")));
                    } else {
                        scene = new Scene(FXMLLoader.load(getClass().getResource("/sample/DashboardFuncionario/dashboardFuncionario.fxml")));
                    }

                    Stage stage = new Stage();
                    stage.getIcons().add(new Image(Main.class.getResourceAsStream("/resources/img/w!.png")));
                    stage.setUserData(user);
                    usuarioLogado = user;
                    //stage.initStyle(StageStyle.UNDECORATED);
                    stage.setResizable(false);
                    stage.setScene(scene);
                    stage.show();

                    stage = (Stage) entrar.getScene().getWindow();
                    stage.close();
                }


            } catch (IOException | InterruptedException ioException) {
                ioException.printStackTrace();
            }
        }
    };


}
