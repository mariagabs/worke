package sample;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CriarSenhaController implements Initializable {

    @FXML
    private Button entrar;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        entrar.setPickOnBounds(true);
        entrar.setOnMouseClicked((MouseEvent e) -> {
            Scene scene = null;
            try {
                scene = new Scene(FXMLLoader.load(getClass().getResource("sample.fxml")));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.show();

            stage = (Stage) entrar.getScene().getWindow();
            stage.close();
        });

    }


}
