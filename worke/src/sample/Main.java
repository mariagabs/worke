package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        primaryStage.setTitle("worke!");
        Scene scene = new Scene(root, 1200, 780);
        scene.getStylesheets().add(getClass().getResource("/resources/fonts/styles.css").toExternalForm());

        /* código que tira o foco dos textfields ao carregar a tela (antes focava e nao aparecia a placeholder */
        Platform.runLater( () -> root.requestFocus() );

        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
