package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PopUpCriarFuncionarioController implements Initializable {

    @FXML
    private Button btnSalvar;

    @FXML
    private ImageView btnSair;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnSalvar.setPickOnBounds(true);
        btnSalvar.setOnMouseClicked((MouseEvent e) -> {
            Stage stage = (Stage) btnSalvar.getScene().getWindow();
            stage.close();
        });

        btnSair.setPickOnBounds(true);
        btnSair.setOnMouseClicked((MouseEvent e) -> {
            Stage stage = (Stage) btnSalvar.getScene().getWindow();
            stage.close();
        });
    }
}
