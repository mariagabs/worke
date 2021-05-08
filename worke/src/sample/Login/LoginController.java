package sample.Login;

import DAO.acesso.UsuarioDAO;
import DAO.auditoria.AuditoriaTest;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
        sair.setPickOnBounds(true);
        sair.setOnMouseClicked((MouseEvent e) -> {
            Stage stage = (Stage) sair.getScene().getWindow();
            stage.close();
        });

        entrar.setOnAction(entrarEvent);

    }

    EventHandler<ActionEvent> entrarEvent = new EventHandler<ActionEvent>() {
        public void handle(ActionEvent e)
        {
            try {
                AuditoriaTest.getInstance().StartThread("Login");

                Usuario user = usuarioDAO.consultar(email.getText(), senha.getText());

                if(user == null || user.getId() == 0){
                    dadosIncorretos.setVisible(true);
                }else{
                    dadosIncorretos.setVisible(false);
                    Scene scene;

                    if(user.isAdmEmpresa()){
                        scene = new Scene(FXMLLoader.load(getClass().getResource("/sample/DashboardEmpresa/dashboardEmpresa.fxml")));
                    } else if(user.getSenha().equals("Trocar123*")){
                        scene = new Scene(FXMLLoader.load(getClass().getResource("/sample/CriarSenha/criarSenha.fxml")));
                    }else{
                        scene = new Scene(FXMLLoader.load(getClass().getResource("/sample/DashboardFuncionario/dashboardFuncionario.fxml")));
                    }

                    Stage stage = new Stage();
                    stage.setUserData(user);
                    usuarioLogado = user;
                    stage.initStyle(StageStyle.UNDECORATED);
                    stage.setScene(scene);
                    stage.show();

                    stage = (Stage) sair.getScene().getWindow();
                    stage.close();
                }


            } catch (IOException | InterruptedException ioException) {
                ioException.printStackTrace();
            }
        }
    };



}
