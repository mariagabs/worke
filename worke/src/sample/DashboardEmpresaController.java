package sample;

import DAO.auditoria.AuditoriaTest;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardEmpresaController implements Initializable {

    @FXML
    private Button btnImprimir;
    @FXML
    private Button btnSalvarConfig;
    @FXML
    private Button btnCriarUsuario;
    @FXML
    private ImageView btnUsuarios;
    @FXML
    private ImageView btnHistorico;
    @FXML
    private ImageView home;
    @FXML
    private ImageView usuarios;
    @FXML
    private ImageView historico;
    @FXML
    private ImageView config;
    @FXML
    private ImageView logout;
    @FXML
    private Pane UserPane;
    @FXML
    private Pane PremioPane;
    @FXML
    private Pane DashboardPane;
    @FXML
    private Pane ConfigPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        logout.setPickOnBounds(true);
        logout.setOnMouseClicked((MouseEvent e) -> {
            try {
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("login.fxml")));
                Stage stage = new Stage();
                stage.initStyle(StageStyle.UNDECORATED);
                stage.setScene(scene);
                stage.show();

                AuditoriaTest.getInstance().StartThread("Logout");
                stage = (Stage) logout.getScene().getWindow();
                stage.close();
            } catch (IOException | InterruptedException ioException) {
                ioException.printStackTrace();
            }
        });

        btnUsuarios.setPickOnBounds(true);
        btnUsuarios.setOnMouseClicked((MouseEvent e) -> {
            try {
                AuditoriaTest.getInstance().StartThread("Users");
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
            UserPane.setVisible(true);
            PremioPane.setVisible(false);
            DashboardPane.setVisible(false);
            ConfigPane.setVisible(false);
        });

        btnHistorico.setPickOnBounds(true);
        btnHistorico.setOnMouseClicked((MouseEvent e) -> {
            try {
                AuditoriaTest.getInstance().StartThread("History");
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
            UserPane.setVisible(false);
            PremioPane.setVisible(true);
            DashboardPane.setVisible(false);
            ConfigPane.setVisible(false);
        });

        home.setPickOnBounds(true);
        home.setOnMouseClicked((MouseEvent e) -> {
            try {
                AuditoriaTest.getInstance().StartThread("Home");
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
            UserPane.setVisible(false);
            PremioPane.setVisible(false);
            DashboardPane.setVisible(true);
            ConfigPane.setVisible(false);
        });

        historico.setPickOnBounds(true);
        historico.setOnMouseClicked((MouseEvent e) -> {
            try {
                AuditoriaTest.getInstance().StartThread("History");
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
            UserPane.setVisible(false);
            PremioPane.setVisible(true);
            DashboardPane.setVisible(false);
            ConfigPane.setVisible(false);
        });

        usuarios.setPickOnBounds(true);
        usuarios.setOnMouseClicked((MouseEvent e) -> {
            try {
                AuditoriaTest.getInstance().StartThread("Users");
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
            UserPane.setVisible(true);
            PremioPane.setVisible(false);
            DashboardPane.setVisible(false);
            ConfigPane.setVisible(false);
        });

        config.setPickOnBounds(true);
        config.setOnMouseClicked((MouseEvent e) -> {
            try {
                AuditoriaTest.getInstance().StartThread("Settings");
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
            UserPane.setVisible(false);
            PremioPane.setVisible(false);
            DashboardPane.setVisible(false);
            ConfigPane.setVisible(true);
        });

        btnSalvarConfig.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        UserPane.setVisible(false);
                        PremioPane.setVisible(false);
                        DashboardPane.setVisible(true);
                        ConfigPane.setVisible(false);
                        try {
                            AuditoriaTest.getInstance().StartThread("Save Settings");
                        } catch (InterruptedException interruptedException) {
                            interruptedException.printStackTrace();
                        }
                    }
                }
        );

        btnImprimir.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        try {
                            Parent root = FXMLLoader.load(getClass().getResource("PopUpImpressao.fxml"));

                            Stage dialog = new Stage();
                            dialog.setScene(new Scene(root));
                            dialog.initModality(Modality.APPLICATION_MODAL);
                            dialog.show();

                            AuditoriaTest.getInstance().StartThread("Open Pop-Up Printer");

                        } catch (IOException | InterruptedException e) {
                            e.printStackTrace();
                        }


                    }
                }
        );

        btnCriarUsuario.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        try {
                            Parent root = FXMLLoader.load(getClass().getResource("PopUpCriarFuncionario.fxml"));

                            Stage dialog = new Stage();
                            dialog.setScene(new Scene(root));
                            dialog.initModality(Modality.APPLICATION_MODAL);
                            dialog.show();

                            AuditoriaTest auditoria = new AuditoriaTest();
                            auditoria.StartThread("Open Pop-Up New User");

                        } catch (IOException | InterruptedException e) {
                            e.printStackTrace();
                        }


                    }
                }
        );
    }
}
