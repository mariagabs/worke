package sample;

import javafx.beans.DefaultProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private ImageView Home;
    @FXML
    private ImageView ExerciseDetails;
    @FXML
    private ImageView playPause;
    @FXML
    private Pane homeGrayScreen;
    @FXML
    private Pane homeWhiteScreen;
    @FXML
    private Pane exerciseScreen;
    @FXML
    private GridPane playGrid;

    Image imagePause = new Image(getClass().getResource("/resources/img/simbolo-de-pausa.png").toExternalForm());
    Image imagePlay = new Image(getClass().getResource("/resources/img/botao-play-ponta-de-seta.png").toExternalForm());

    static boolean pause;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Home.setPickOnBounds(true);
        Home.setOnMouseClicked((MouseEvent e) -> {
            homeGrayScreen.setVisible(true);
            homeWhiteScreen.setVisible(true);
            exerciseScreen.setVisible(false);
        });
        ExerciseDetails.setPickOnBounds(true);
        ExerciseDetails.setOnMouseClicked((MouseEvent e) -> {
            exerciseScreen.setVisible(true);
            homeGrayScreen.setVisible(false);
            homeWhiteScreen.setVisible(false);
        });


        playGrid.setOnMouseClicked((MouseEvent e) -> {

            if (pause) {
                playPause.setImage(imagePlay);
                pause = false;
            } else {
                pause = true;
                playPause.setImage(imagePause);

            }

        });

    }
}
