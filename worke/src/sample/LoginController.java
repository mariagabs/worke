package sample;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private ImageView sair;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sair.setPickOnBounds(true);
        sair.setOnMouseClicked((MouseEvent e) -> {
            Stage stage = (Stage) sair.getScene().getWindow();
            stage.close();
        });


    }
}
