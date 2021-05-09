package sample.PopUpCriarFuncionarios;

import DAO.acesso.UsuarioDAO;
import comuns.acesso.Usuario;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.DashboardEmpresa.DashboardEmpresaController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PopUpCriarFuncionarioController implements Initializable {

    @FXML
    private Button btnSalvar;

    @FXML
    private ImageView btnSair;

    @FXML
    private TextField nomeCompleto;

    @FXML
    private TextField email;

    @FXML
    private Label msgAviso;

    private Usuario usuario;

    public void setUser(Usuario user){
        this.usuario = user;
    }

    public Usuario getUser(){
        return usuario;
    }

    public TableView tableTeste;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        if(usuario != null && !usuario.getNome().isEmpty() && !usuario.getEmail().isEmpty()){
            nomeCompleto.setText(usuario.getNome());
            email.setText(usuario.getEmail());
        }


        btnSalvar.setPickOnBounds(true);
        btnSalvar.setOnMouseClicked((MouseEvent e) -> {

            if(nomeCompleto.getText().isEmpty() || email.getText().isEmpty()){
                msgAviso.setVisible(true);
            }else {
                msgAviso.setVisible(false);
                Usuario user = new Usuario();
                user.setAdmEmpresa(false);
                user.setNome(nomeCompleto.getText());
                user.setEmail(email.getText());

                UsuarioDAO dao = new UsuarioDAO();

                if(usuario != null && usuario.getId() > 0){
                    user.setId(getUser().getId());
                    dao.alterar(user);
                }else{

                    dao.inserir(user);
                }


                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample/DashboardEmpresa/dashboardEmpresa.fxml"));
                try {
                    Parent root = (Parent) fxmlLoader.load();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                DashboardEmpresaController controller = fxmlLoader.getController();
                    controller.loadUsuarios(tableTeste);


                Stage stage = (Stage) btnSalvar.getScene().getWindow();
                stage.close();
            }
        });

        btnSair.setPickOnBounds(true);
        btnSair.setOnMouseClicked((MouseEvent e) -> {
            System.out.println(getUser());
            Stage stage = (Stage) btnSalvar.getScene().getWindow();
            stage.close();


        });
    }
}
