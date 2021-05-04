package sample;

import DAO.acesso.UsuarioDAO;
import DAO.auditoria.AuditoriaTest;
import comuns.acesso.Usuario;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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

    public Usuario usuarioLogado;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        entrar.setPickOnBounds(true);
        entrar.setOnMouseClicked((MouseEvent e) -> {

                if (novaSenha.getText().equals(confirmarSenha.getText())) {
                    if (novaSenha.getText().equals("Trocar123*")) {
                        senhaIncorreta.setVisible(true);
                    } else {
                        senhaIncorreta.setVisible(false);

                        Node node = (Node) e.getSource();
                        Stage stage = (Stage) entrar.getScene().getWindow();
                        usuarioLogado = (Usuario) stage.getUserData();
                        UsuarioDAO dao = new UsuarioDAO();
                        usuarioLogado.setSenha(novaSenha.getText());
                        try {
                            dao.alterar(usuarioLogado);
                        } catch (Exception ex){
                            ex.printStackTrace();
                        }

                        Scene scene = null;
                        try {
                            scene = new Scene(FXMLLoader.load(getClass().getResource("sample.fxml")));
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                        Stage stage1 = new Stage();
                        stage1.initStyle(StageStyle.UNDECORATED);
                        stage1.setScene(scene);
                        stage1.show();

                        try {
                            AuditoriaTest.getInstance().StartThread("New Password");
                        } catch (InterruptedException interruptedException) {
                            interruptedException.printStackTrace();
                        }

                        stage = (Stage) entrar.getScene().getWindow();
                        stage.close();
                    }
                }



        });

    }


}
