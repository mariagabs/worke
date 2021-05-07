package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class PopUpImpressaoController implements Initializable {
    @FXML
    private ImageView btnSair;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        btnSair.setPickOnBounds(true);
        btnSair.setOnMouseClicked((MouseEvent e) -> {
            Stage stage = (Stage) btnSair.getScene().getWindow();
            stage.close();
        });
    }
}
