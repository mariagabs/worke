package sample.DashboardFuncionario;

import DAO.acesso.ExercicioDAO;
import DAO.acesso.FuncionarioDAO;
import DAO.acesso.UsuarioDAO;
import DAO.auditoria.AuditoriaTest;
import comuns.acesso.Empresa;
import comuns.acesso.Funcionario;
import comuns.acesso.Usuario;
import comuns.conteudo.Exercicio;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.Main;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class Dashboard implements Initializable {
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
    public Pane exerciseScreen;
    @FXML
    public Pane configScreen;
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
    @FXML
    private GridPane duracao_1;
    @FXML
    private GridPane duracao_2;
    @FXML
    private GridPane duracao_3;
    @FXML
    private GridPane duracao_4;
    @FXML
    private GridPane duracao_5;
    @FXML
    private GridPane duracao_6;
    @FXML
    private GridPane intervalo_30;
    @FXML
    private GridPane intervalo_1;
    @FXML
    private GridPane intervalo_13;
    @FXML
    private GridPane intervalo_2;
    @FXML
    private GridPane intervalo_23;
    @FXML
    private GridPane intervalo_3;
    @FXML
    private GridPane duracaoPane;
    @FXML
    private Label lblDuracao1;
    @FXML
    private Label lblDuracao2;
    @FXML
    private Label lblDuracao3;
    @FXML
    private Label lblDuracao4;
    @FXML
    private Label lblDuracao5;
    @FXML
    private Label lblDuracao6;
    @FXML
    private Label lblIntervalo_1;
    @FXML
    private Label lblIntervalo_2;
    @FXML
    private Label lblIntervalo_3;
    @FXML
    private Label lblIntervalo_13;
    @FXML
    private Label lblIntervalo_23;
    @FXML
    private Label lblIntervalo_30;
    @FXML
    private GridPane testeIniciar;
    @FXML
    private GridPane intervaloPane;
    @FXML
    private GridPane exerciciosDoDia;
    @FXML
    private GridPane exercicio1;
    @FXML
    private GridPane exercicio2;
    @FXML
    private GridPane exercicio3;
    @FXML
    private GridPane exercicio4;
    @FXML
    private GridPane exercicio5;
    @FXML
    private GridPane exercicio7;
    @FXML
    private GridPane exercicio8;
    @FXML
    private GridPane exercicio9;
    @FXML
    private GridPane exercicio10;
    @FXML
    private GridPane exercicio11;
    @FXML
    private GridPane exercicio12;
    @FXML
    private GridPane exercicio13;
    @FXML
    private GridPane exercicio14;
    @FXML
    private GridPane exercicio15;
    @FXML
    private GridPane exercicio16;
    @FXML
    private GridPane exercicio6;
    @FXML
    private Label aviso;
    @FXML
    private Label avisoIntervalo;
    @FXML
    private Label avisoDuracao;
    @FXML
    private Label avisoHorario;


    private Funcionario func = Funcionario.getInstance();
    ExercicioDAO exercicioDAO = new ExercicioDAO();
    List<Exercicio> exercicioList = exercicioDAO.listar();

    DecimalFormat df = new DecimalFormat("#");

    Image imagePause = new Image(getClass().getResource("/resources/img/simbolo-de-pausa.png").toExternalForm());
    Image imagePlay = new Image(getClass().getResource("/resources/img/botao-play-ponta-de-seta.png").toExternalForm());

    static boolean pause;

    public void getExercicioEscolhido(GridPane selectedPane) {

        Exercicio exercicio = new Exercicio();
        List<Exercicio> exercicioList = func.getExercicios();
        int id = Integer.parseInt(selectedPane.getId().split("_")[1]);
        Boolean selected = false;

        for (Node node : selectedPane.getChildren()) {
            if (node instanceof ImageView) {

                Image image = ((ImageView) node).getImage();
                int index = image.getUrl().lastIndexOf('/');
                String imageName = image.getUrl().substring(index + 1);

                if (image.getUrl().contains("White")) {
                    image = new Image(getClass().getResource("/resources/img/" + imageName.replace("White", "Dark")).toExternalForm());

                    exercicioList.removeIf(ex -> ex.getId() == id);

                } else {
                    selected = true;

                    image = new Image(getClass().getResource("/resources/img/" + imageName.replace("Dark", "White")).toExternalForm());

                    exercicio.setId(id);
                    exercicioList.add(exercicio);
                }

                ((ImageView) node).setImage(image);
            }

            if (node instanceof Label) {
                if (selected) {
                    ((Label) node).setTextFill(Color.WHITE);
                } else {
                    ((Label) node).setTextFill(Paint.valueOf("#0B1F38"));
                }
            }
        }

        if (selected) {
            selectedPane.setStyle("-fx-background-color: #A3D3E4");
        } else {
            selectedPane.setStyle("-fx-background-color: #F6F6F6");
        }


        func.setExercicios(exercicioList);
    }

    public void listExerciciosDoDia() {
        try {

            int aux = 0;

            for (Node pane : exerciciosDoDia.getChildren()) {
                if (aux < 16) {

                    if (pane instanceof GridPane) {
                        Exercicio ex = exercicioList.get(aux);

                        ((GridPane) pane).setId(pane.getId() + "_" + String.valueOf(ex.getId()));

                        for (Node child : ((GridPane) pane).getChildren()) {
                            if (child instanceof ImageView) {
                                Image img = new Image(getClass().getResource("/resources/img/" + ex.getImagem() + "Dark.png").toExternalForm());
                                ((ImageView) child).setImage(img);
                            }
                            if (child instanceof Label) {
                                ((Label) child).setText(ex.getNome());
                            }

                        }
                        aux++;
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

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
        public void handle(ActionEvent e) {
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

    public void getIntervaloExercicios() {
        String selectedInterval = String.valueOf(func.getIntervaloExercicios()).replace(".0", "");

        if (!selectedInterval.equals("0")) {
            GridPane botao = (GridPane) intervaloPane.lookup("#intervalo_" + selectedInterval);
            Label lblBotao = (Label) botao.lookup("#lblIntervalo_" + selectedInterval);

            //Muda para o estilo de botão selecionado
            changeSelectStyle(true, lblBotao, botao);
        }
    }

    public void setIntervaloExercicios(GridPane newSelectedPane) {
        String oldIntervalo = String.valueOf(func.getIntervaloExercicios()).replace(".0", "");
        String newIntervalo = newSelectedPane.getId().split("_")[1];
        Label lblSelected;

        if (!oldIntervalo.equals("0")) {
            GridPane botao = (GridPane) intervaloPane.lookup("#intervalo_" + oldIntervalo);
            lblSelected = (Label) botao.lookup("#lblIntervalo_" + oldIntervalo);

            //Muda para o estilo de botão não selecionado
            changeSelectStyle(false, lblSelected, botao);
        }

        lblSelected = (Label) newSelectedPane.lookup("#lblIntervalo_" + newIntervalo);

        //Muda para o estilo de botão selecionado
        changeSelectStyle(true, lblSelected, newSelectedPane);

        Double selectedIntervalo = Double.valueOf(newIntervalo);
        func.setIntervaloExercicios(selectedIntervalo);
    }

    public void getDuracaoExercicios() {

        String selectedDuracao = String.valueOf(df.format(func.getDuracaoExercicios()));

        if (!selectedDuracao.equals("0")) {
            GridPane botao = (GridPane) duracaoPane.lookup("#duracao_" + selectedDuracao);
            Label lblBotao = (Label) botao.lookup("#lblDuracao_" + selectedDuracao);

            //Muda para o estilo de botão selecionado
            changeSelectStyle(true, lblBotao, botao);
        }
    }

    public void setDuracaoExercicios(GridPane newSelectedPane) {

        String oldDuracao = String.valueOf(df.format(func.getDuracaoExercicios()));
        String newDuracao = newSelectedPane.getId().split("_")[1];
        Label lblSelected;

        if (!oldDuracao.equals("0")) {
            GridPane botao = (GridPane) duracaoPane.lookup("#duracao_" + oldDuracao);
            lblSelected = (Label) botao.lookup("#lblDuracao_" + oldDuracao);

            //Muda para o estilo de botão não selecionado
            changeSelectStyle(false, lblSelected, botao);
        }

        lblSelected = (Label) newSelectedPane.lookup("#lblDuracao_" + newDuracao);

        //Muda para o estilo de botão selecionado
        changeSelectStyle(true, lblSelected, newSelectedPane);

        Double selectedDuracao = Double.valueOf(newDuracao);
        func.setDuracaoExercicios(selectedDuracao);
    }

    public void changeSelectStyle(Boolean selected, Label lblSelected, Pane paneSelected) {

        if (selected) {
            paneSelected.setStyle("-fx-background-color: #A3D3E4");
            lblSelected.setTextFill(Color.WHITE);
        } else {
            paneSelected.setStyle("-fx-background-color: #F6F6F6");
            lblSelected.setTextFill(Paint.valueOf("#071c35"));
        }
    }

    public void getHorario(){
        horaInicial.setText(String.valueOf(func.getHoraInicio()).replace(".",""));
        horaFinal.setText(String.valueOf(func.getHoraTermino()).replace(".",""));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        UsuarioDAO dao = new UsuarioDAO();
        FuncionarioDAO daoFunc = new FuncionarioDAO();
        Empresa empresa = dao.listarDadosEmpresa();
        getDuracaoExercicios();
        getIntervaloExercicios();
        listExerciciosDoDia();
        getHorario();

        nomeUsuario.setText(func.getNome());

        exercicio1.setOnMouseClicked((MouseEvent e) -> {
            getExercicioEscolhido(exercicio1);
        });
        exercicio2.setOnMouseClicked((MouseEvent e) -> {
            getExercicioEscolhido(exercicio2);
        });
        exercicio3.setOnMouseClicked((MouseEvent e) -> {
            getExercicioEscolhido(exercicio3);
        });
        exercicio4.setOnMouseClicked((MouseEvent e) -> {
            getExercicioEscolhido(exercicio4);
        });
        exercicio5.setOnMouseClicked((MouseEvent e) -> {
            getExercicioEscolhido(exercicio5);
        });
        exercicio6.setOnMouseClicked((MouseEvent e) -> {
            getExercicioEscolhido(exercicio6);
        });
        exercicio7.setOnMouseClicked((MouseEvent e) -> {
            getExercicioEscolhido(exercicio7);
        });
        exercicio8.setOnMouseClicked((MouseEvent e) -> {
            getExercicioEscolhido(exercicio8);
        });
        exercicio9.setOnMouseClicked((MouseEvent e) -> {
            getExercicioEscolhido(exercicio9);
        });
        exercicio10.setOnMouseClicked((MouseEvent e) -> {
            getExercicioEscolhido(exercicio10);
        });
        exercicio11.setOnMouseClicked((MouseEvent e) -> {
            getExercicioEscolhido(exercicio11);
        });
        exercicio12.setOnMouseClicked((MouseEvent e) -> {
            getExercicioEscolhido(exercicio12);
        });
        exercicio13.setOnMouseClicked((MouseEvent e) -> {
            getExercicioEscolhido(exercicio13);
        });
        exercicio14.setOnMouseClicked((MouseEvent e) -> {
            getExercicioEscolhido(exercicio14);
        });
        exercicio15.setOnMouseClicked((MouseEvent e) -> {
            getExercicioEscolhido(exercicio15);
        });
        exercicio16.setOnMouseClicked((MouseEvent e) -> {
            getExercicioEscolhido(exercicio16);
        });

        duracao_1.setOnMouseClicked((MouseEvent e) -> {
            setDuracaoExercicios(duracao_1);
        });
        duracao_2.setOnMouseClicked((MouseEvent e) -> {
            setDuracaoExercicios(duracao_2);
        });
        duracao_3.setOnMouseClicked((MouseEvent e) -> {
            setDuracaoExercicios(duracao_3);
        });
        duracao_4.setOnMouseClicked((MouseEvent e) -> {
            setDuracaoExercicios(duracao_4);
        });
        duracao_5.setOnMouseClicked((MouseEvent e) -> {
            setDuracaoExercicios(duracao_5);
        });
        duracao_6.setOnMouseClicked((MouseEvent e) -> {
            setDuracaoExercicios(duracao_6);
        });

        intervalo_1.setOnMouseClicked((MouseEvent e) -> {
            setIntervaloExercicios(intervalo_1);
        });
        intervalo_2.setOnMouseClicked((MouseEvent e) -> {
            setIntervaloExercicios(intervalo_2);
        });
        intervalo_3.setOnMouseClicked((MouseEvent e) -> {
            setIntervaloExercicios(intervalo_3);
        });
        intervalo_13.setOnMouseClicked((MouseEvent e) -> {
            setIntervaloExercicios(intervalo_13);
        });
        intervalo_23.setOnMouseClicked((MouseEvent e) -> {
            setIntervaloExercicios(intervalo_23);
        });
        intervalo_30.setOnMouseClicked((MouseEvent e) -> {
            setIntervaloExercicios(intervalo_30);
        });

        if (func.getLembrete() == null || func.getLembrete().isEmpty()) {
            lembrete.setText("Clique aqui para adicionar um lembrete!");
        } else {
            lembreteEdit.setVisible(false);
            lembrete.setVisible(true);
            lembrete.setText(func.getLembrete());
        }

        if (empresa.getFraseMotivacional() == null || empresa.getFraseMotivacional().isEmpty()) {
            lembrete.setText("O seu maior projeto deve ser você!");
        } else {
            fraseMotivacional.setText(empresa.getFraseMotivacional());
        }

        lembreteEdit.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getEventType() == KeyEvent.KEY_PRESSED) {
                if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                    func.setLembrete(lembreteEdit.getText().trim());
                    daoFunc.alterarFuncionario(func);
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
                stage.getIcons().add(new Image(Main.class.getResourceAsStream("/resources/img/w!.png")));
                //stage.initStyle(StageStyle.UNDECORATED);
                stage.setResizable(false);
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

            if (func.getExercicios().size() == 0) {
                aviso.setVisible(true);
            } else if (func.getIntervaloExercicios() == 0) {
                avisoIntervalo.setVisible(true);
            } else if (func.getDuracaoExercicios() == 0) {
                avisoDuracao.setVisible(true);
            } else if (func.getHoraInicio() == 0 || func.getHoraTermino() == 0) {
                avisoHorario.setVisible(true);
            } else {

                aviso.setVisible(false);
                avisoDuracao.setVisible(false);
                avisoIntervalo.setVisible(false);
                avisoHorario.setVisible(false);

                exercicioDAO.escolherExercicio(func);
                daoFunc.alterarFuncionario(func);

                try {
                    AuditoriaTest.getInstance().StartThread("Initialize");
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
                configScreen.setVisible(false);
                exerciseScreen.setVisible(true);
                homeGrayScreen.setVisible(false);
                homeWhiteScreen.setVisible(false);
            }
        });

        btnIniciar.setPickOnBounds(true);
        btnIniciar.setOnMouseClicked((MouseEvent e) -> {

            exercicioDAO.escolherExercicio(func);
            daoFunc.alterarFuncionario(func);

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

        testeIniciar.setPickOnBounds(true);
        testeIniciar.setOnMouseClicked((MouseEvent e) -> {

            aviso.setVisible(false);
            avisoDuracao.setVisible(false);
            avisoIntervalo.setVisible(false);
            avisoHorario.setVisible(false);

            if (func.getExercicios().size() == 0) {
                aviso.setVisible(true);
            }
            if (func.getIntervaloExercicios() == 0) {
                avisoIntervalo.setVisible(true);
            }
            if (func.getDuracaoExercicios() == 0) {
                avisoDuracao.setVisible(true);
            }
             else {


                func.setHoraInicio(Double.valueOf(horaInicial.getText().replace(":","")));
                func.setHoraTermino(Double.valueOf(horaFinal.getText().replace(":","")));
                if (func.getHoraInicio() == 0 || func.getHoraTermino() == 0) {
                    avisoHorario.setVisible(true);
                }

                //exercicioDAO.escolherExercicio(func);
                daoFunc.alterarFuncionario(func);

                try {
                    AuditoriaTest.getInstance().StartThread("Initialize");
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
                configScreen.setVisible(false);
                exerciseScreen.setVisible(true);
                homeGrayScreen.setVisible(false);
                homeWhiteScreen.setVisible(false);
            }
        });


    }


}
