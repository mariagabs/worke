package sample.PopUpSucesso;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import sample.DashboardEmpresa.DashboardEmpresaController;
import sample.DashboardFuncionario.Dashboard;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class popUpSucessoController implements Initializable {

    @FXML
    private GridPane btnOK;

    @FXML
    private Label title;

    @FXML
    private Label message;

    public static Dashboard controller;

    public static DashboardEmpresaController controllerEmpresa;

    public String mensagem;

    public String titulo;

    private void setLabels() {
        title.setText(titulo);
        message.setText(mensagem);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        setLabels();

        btnOK.setOnMouseClicked((MouseEvent e) -> {

            if (controller != null){
                try {
                    controller.goToHome();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            } else if (controllerEmpresa != null){
                controllerEmpresa.voltarHomePopUpSucesso();
            }

            Stage stage = (Stage) btnOK.getScene().getWindow();
            stage.close();
        });
    }
}
