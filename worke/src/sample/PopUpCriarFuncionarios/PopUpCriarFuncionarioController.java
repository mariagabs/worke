package sample.PopUpCriarFuncionarios;

import application.UsuarioApp;
import comuns.acesso.Usuario;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.DashboardEmpresa.DashboardEmpresaController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PopUpCriarFuncionarioController implements Initializable {

    @FXML
    private Button btnSalvar;

    @FXML
    private TextField nomeCompleto;

    @FXML
    private TextField email;

    @FXML
    private Label msgAviso;

    private Usuario usuario;

    public void setUser(Usuario user) {
        this.usuario = user;
    }

    public Usuario getUser() {
        return usuario;
    }

    public TableView tableTeste;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        checkIfIsEdit(usuario);

        btnSalvar.setPickOnBounds(true);
        btnSalvar.setOnMouseClicked((MouseEvent e) -> saveUser());
    }

    private void checkIfIsEdit(Usuario usuario) {
        if (usuario != null && !usuario.getNome().isEmpty() && !usuario.getEmail().isEmpty()) {
            nomeCompleto.setText(usuario.getNome());
            email.setText(usuario.getEmail());
        }
    }

    private void saveUser() {
        msgAviso.setVisible(UsuarioApp.isInvalid(nomeCompleto.getText(), email.getText()));

        if(!UsuarioApp.isInvalid(nomeCompleto.getText(), email.getText())){
            UsuarioApp.saveUser(nomeCompleto.getText(), email.getText(), usuario, getUser());
            redirect();
        }
    }

    private void redirect(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample/DashboardEmpresa/dashboardEmpresa.fxml"));
        try {
            Parent root = fxmlLoader.load();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        DashboardEmpresaController controller = fxmlLoader.getController();
        controller.loadUsuarios(tableTeste);
        try {
            if (usuario == null || usuario.getId() == 0) {
                DashboardEmpresaController.popUpSucessoMensagem("Usuário criado!", "O usuário " + nomeCompleto.getText() + " foi criado com sucesso!");
            } else {
                DashboardEmpresaController.popUpSucessoMensagem("Usuário alterado!", "O usuário " + nomeCompleto.getText() + " foi alterado com sucesso!");
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        Stage stage = (Stage) btnSalvar.getScene().getWindow();
        stage.close();
    }
}
