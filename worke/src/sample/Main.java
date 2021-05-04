package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PopupControl;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {
    @FXML
    private GridPane PopUpImpress;


    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        primaryStage.setTitle("worke!");
        Scene scene = new Scene(root, 1200, 780);
        scene.getStylesheets().add(getClass().getResource("/resources/fonts/styles.css").toExternalForm());

        /* código que tira o foco dos textfields ao carregar a tela (antes focava e nao aparecia a placeholder */
        Platform.runLater( () -> root.requestFocus() );

        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(scene);
        primaryStage.show();

        //PopupControl popup = new PopupControl();
        //popup.getScene().setRoot(PopUpImpress);
        //popup.show(PopUpImpress.getScene().getWindow());
    }


    public static void main(String[] args) {
        launch(args);
    }
}
