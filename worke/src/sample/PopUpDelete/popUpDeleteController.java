package sample.PopUpDelete;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import sample.DashboardFuncionario.Dashboard;

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

            if(controller != null) {
                try {
                    controller.confirmarCancelarExercicio(true);
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
            }

            Stage stage = (Stage) btnSim.getScene().getWindow();
            stage.close();
        });

        btnNao.setOnMouseClicked((MouseEvent e) -> {

            if(controller != null) {
                try {
                    controller.confirmarCancelarExercicio(false);
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
            }

            Stage stage = (Stage) btnNao.getScene().getWindow();
            stage.close();
        });
    }
}
