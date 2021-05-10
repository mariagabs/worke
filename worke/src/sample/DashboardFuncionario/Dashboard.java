package sample.DashboardFuncionario;

import DAO.acesso.UsuarioDAO;
import DAO.auditoria.AuditoriaTest;
import comuns.acesso.Empresa;
import comuns.acesso.Funcionario;
import comuns.acesso.Usuario;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class Dashboard implements Initializable{
    @FXML
    private ImageView Home;
    @FXML
    private ImageView ExerciseDetails;
    @FXML
    private Button playPause;
    @FXML
    private ImageView Config;
    @FXML
    private Pane homeGrayScreen;
    @FXML
    private Pane homeWhiteScreen;
    @FXML
    private Pane exerciseScreen;
    @FXML
    private Pane configScreen;
    @FXML
    private GridPane playGrid;
    @FXML
    private Button btnImpress;
    @FXML
    private ImageView Logout;
    @FXML
    private ImageView play;
    @FXML
    private Label timer;
    @FXML
    private Image btnImagePlay;
    @FXML
    private Button btnIniciar;
    @FXML
    private Label nomeUsuario;
    @FXML
    private Label lembrete;
    @FXML
    private TextArea lembreteEdit;
    @FXML
    private Label fraseMotivacional;
    @FXML
    private TextField horaInicial;
    @FXML
    private TextField horaFinal;

    Image imagePause = new Image(getClass().getResource("/resources/img/simbolo-de-pausa.png").toExternalForm());
    Image imagePlay = new Image(getClass().getResource("/resources/img/botao-play-ponta-de-seta.png").toExternalForm());

    static boolean pause;

    private void teste() {
        ScheduledExecutorService exec = Executors.newScheduledThreadPool(1);

        final int[] secondsToWait = {180};
        Runnable task = new Runnable() {
            @Override
            public void run() {
                secondsToWait[0]--;
                timer.setText(secondsToWait[0] + "");
                if (secondsToWait[0] == 0) {
                    exec.shutdown();
                }
            }
        };

        timer.setText(secondsToWait[0] + "");
        exec.scheduleAtFixedRate(task, 1, 1, TimeUnit.SECONDS);

    }

    EventHandler<ActionEvent> playPauseEvent = new EventHandler<ActionEvent>() {
        public void handle(ActionEvent e)
        {
            try {
                if (pause) {
                    btnImagePlay = imagePlay;
                    pause = false;
                    AuditoriaTest.getInstance().StartThread("Play");

                    //teste();
                } else {
                    pause = true;
                    btnImagePlay = imagePause;
                    AuditoriaTest.getInstance().StartThread("Pause");
                }
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        }
    };

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Funcionario func = Funcionario.getInstance();
        UsuarioDAO dao = new UsuarioDAO();
        Empresa empresa = dao.listarDadosEmpresa();

        nomeUsuario.setText(func.getNome());

        if(func.getLembrete() == null || func.getLembrete().isEmpty()){
            lembrete.setText("Clique aqui para adicionar um lembrete!");
        }else {
            lembreteEdit.setVisible(false);
            lembrete.setVisible(true);
            lembrete.setText(func.getLembrete());
        }

        if(empresa.getFraseMotivacional() == null || empresa.getFraseMotivacional().isEmpty()){
            lembrete.setText("O seu maior projeto deve ser você!");
        }else {
            fraseMotivacional.setText(empresa.getFraseMotivacional());
        }

        lembreteEdit.setOnKeyPressed(keyEvent -> {
            if(keyEvent.getEventType() == KeyEvent.KEY_PRESSED){
                if(keyEvent.getCode().equals(KeyCode.ENTER)){
                    func.setLembrete(lembreteEdit.getText());
                    dao.alterarFuncionario(func);
                    lembrete.setText(func.getLembrete());
                    lembreteEdit.setVisible(false);
                    lembrete.setVisible(true);
                }
            }
        });

        //playPause.setOnAction(playPauseEvent);

        lembrete.setPickOnBounds(true);
        lembrete.setOnMouseClicked((MouseEvent e) -> {
            lembreteEdit.setText(func.getLembrete());
            lembreteEdit.setVisible(true);
            lembrete.setVisible(false);
        });

        Home.setPickOnBounds(true);
        Home.setOnMouseClicked((MouseEvent e) -> {
            try {
                AuditoriaTest.getInstance().StartThread("Home");
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
            homeGrayScreen.setVisible(true);
            homeWhiteScreen.setVisible(true);
            exerciseScreen.setVisible(false);
            configScreen.setVisible(false);
        });

        /*play.setPickOnBounds(true);
        play.setOnMouseClicked((MouseEvent e) -> {
            if (pause) {
                btnImagePlay = imagePlay;
                pause = false;
            } else {
                pause = true;
                btnImagePlay = imagePause;

            }
        });*/

        ExerciseDetails.setPickOnBounds(true);
        ExerciseDetails.setOnMouseClicked((MouseEvent e) -> {
            try {
                AuditoriaTest.getInstance().StartThread("Exercise Details");
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
            exerciseScreen.setVisible(true);
            homeGrayScreen.setVisible(false);
            homeWhiteScreen.setVisible(false);
            configScreen.setVisible(false);
        });
        Config.setPickOnBounds(true);
        Config.setOnMouseClicked((MouseEvent e) -> {
            try {
                AuditoriaTest.getInstance().StartThread("Settings");
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
            configScreen.setVisible(true);
            exerciseScreen.setVisible(false);
            homeGrayScreen.setVisible(false);
            homeWhiteScreen.setVisible(false);
        });

        Logout.setPickOnBounds(true);
        Logout.setOnMouseClicked((MouseEvent e) -> {
            try {
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/sample/Login/login.fxml")));
                Stage stage = new Stage();
                stage.initStyle(StageStyle.UNDECORATED);
                stage.setScene(scene);
                stage.show();

                stage = (Stage) Logout.getScene().getWindow();
                stage.close();

                AuditoriaTest.getInstance().StartThread("Logout");
            } catch (IOException | InterruptedException ioException) {
                ioException.printStackTrace();
            }
        });

        btnIniciar.setPickOnBounds(true);
        btnIniciar.setOnMouseClicked((MouseEvent e) -> {
            try {
                AuditoriaTest.getInstance().StartThread("Initialize");
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
            configScreen.setVisible(false);
            exerciseScreen.setVisible(true);
            homeGrayScreen.setVisible(false);
            homeWhiteScreen.setVisible(false);
        });

    }


}
