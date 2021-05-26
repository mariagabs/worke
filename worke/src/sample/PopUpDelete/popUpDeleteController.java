package sample.PopUpDelete;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import sample.DashboardEmpresa.DashboardEmpresaController;
import sample.DashboardFuncionario.Dashboard;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class popUpDeleteController implements Initializable {

    @FXML
    private Label title;
    @FXML
    private Label message;
    @FXML
    private GridPane btnSim;
    @FXML
    private GridPane btnNao;

    public static Dashboard controller;
    public static DashboardEmpresaController controllerEmpresa;
    public String titulo;
    public String mensagem;

    private void setLabels() {
        title.setText(titulo);
        message.setText(mensagem);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        setLabels();

        btnSim.setOnMouseClicked((MouseEvent e) -> {
            try {
                if(controller != null) {
                    controller.confirmarCancelarExercicio(true);
                } else if (controllerEmpresa != null) {
                    controllerEmpresa.confirmarCancelarUsuario(true);
                }
            } catch (InterruptedException | IOException interruptedException) {
                interruptedException.printStackTrace();
            }
            Stage stage = (Stage) btnSim.getScene().getWindow();
            stage.close();
        });

        btnNao.setOnMouseClicked((MouseEvent e) -> {

            try {
                if(controller != null) {
                    controller.confirmarCancelarExercicio(false);
                } else if (controllerEmpresa != null) {
                    controllerEmpresa.confirmarCancelarUsuario(false);
                }
            } catch (InterruptedException | IOException interruptedException) {
                interruptedException.printStackTrace();
            }
            Stage stage = (Stage) btnNao.getScene().getWindow();
            stage.close();
        });
    }
}
