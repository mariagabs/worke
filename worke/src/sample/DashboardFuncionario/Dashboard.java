package sample.DashboardFuncionario;

import DAO.acesso.ExercicioDAO;
import DAO.acesso.FuncionarioDAO;
import DAO.acesso.PremioDAO;
import DAO.acesso.UsuarioDAO;
import DAO.auditoria.AuditoriaTest;
import application.EmpresaApp;
import application.FuncionarioApp;
import application.NotificationApp;
import comuns.acesso.*;
import comuns.conteudo.Exercicio;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
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
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import sample.Main;
import sample.PopUpCriarFuncionarios.PopUpCriarFuncionarioController;
import sample.PopUpDelete.popUpDeleteController;
import sample.PopUpSucesso.popUpSucessoController;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Repeatable;
import java.net.URL;
import java.sql.Array;
import java.sql.Time;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


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
    private ImageView btnImagePlay;
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
    private GridPane intervalo_3000;
    @FXML
    private GridPane intervalo_10000;
    @FXML
    private GridPane intervalo_13000;
    @FXML
    private GridPane intervalo_20000;
    @FXML
    private GridPane intervalo_23000;
    @FXML
    private GridPane intervalo_30000;
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
    private Label lblIntervalo_10000;
    @FXML
    private Label lblIntervalo_20000;
    @FXML
    private Label lblIntervalo_30000;
    @FXML
    private Label lblIntervalo_13000;
    @FXML
    private Label lblIntervalo_23000;
    @FXML
    private Label lblIntervalo_3000;
    @FXML
    private GridPane iniciarConfig;
    @FXML
    private GridPane intervaloPane;
    @FXML
    private GridPane exercicios;
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
    @FXML
    private Label premio;
    @FXML
    private GridPane exerciciosDoDia;
    @FXML
    private Label proxExercicio;
    @FXML
    private ImageView imgProxEx;
    @FXML
    private ImageView imgDetails;
    @FXML
    private Label nomeDetails;
    @FXML
    private Label tempoDetails;
    @FXML
    private Label timerDetails;
    @FXML
    private Label instrucaoDetails;
    @FXML
    private GridPane btnCancelar;
    @FXML
    private GridPane favoritos;
    @FXML
    private Label qntExercicios;
    @FXML
    private Label minutosTotal;
    @FXML
    private Label ranking;
    @FXML
    private Rectangle bloqExDoDia;
    @FXML
    private Pane bloqParabens;
    @FXML
    private GridPane semPremio;
    @FXML
    private Pane semMotivacao;
    @FXML
    private Pane semFavoritos;
    @FXML
    private Pane semExercicios;
    @FXML
    private Button btnConfigure;

    private Funcionario func = Funcionario.getInstance();
    FuncionarioDAO daoFunc = new FuncionarioDAO();
    ExercicioDAO exercicioDAO = new ExercicioDAO();
    List<Exercicio> exercicioList = exercicioDAO.listar();
    UsuarioDAO dao = new UsuarioDAO();
    Empresa empresa = dao.listarDadosEmpresa();
    Image imagePause = new Image(getClass().getResource("/resources/img/simbolo-de-pausa.png").toExternalForm());
    Image imagePlay = new Image(getClass().getResource("/resources/img/botao-play-ponta-de-seta.png").toExternalForm());
    static boolean pause;
    private Timeline timeline;
    private Timeline timelineInstrucoes;
    private static int timeSeconds;
    private static int qntExerciciosDisponivel;
    private static Exercicio exercicioExecutado;
    public static List<String> instrucoes;
    public static Integer[] rankingFuncionarios;
    private static List<Exercicio> novosExerciciosEscolhidos = new ArrayList<>();
    public Integer[] listarUsuariosEmpresa() {
        return EmpresaApp.listarUsuariosEmpresa();
    }

    // Mostra os dados de conquistas (horas e exercícios feitos) e favoritos (exercícios mais feitos)
    public void getConquistasFavoritos(Integer usuarioId) {
        ArrayList<Integer> exerciciosRealizadosChaves = new ArrayList<>();
        LinkedHashMap<Integer, Integer> reverseSortedMap = new LinkedHashMap<>();
        int totalExerciciosRealizados = FuncionarioApp.calcTotalExercicios(usuarioId, exerciciosRealizadosChaves, reverseSortedMap);
        qntExercicios.setText(String.valueOf(totalExerciciosRealizados));
        minutosTotal.setText(String.valueOf(exercicioDAO.consultarDuracaoTotalUsuario()));

        // Componente de vazio caso não existam exercícios realizados para mostrar nos favoritos
        semFavoritos.setVisible(totalExerciciosRealizados == 0);

        if (exerciciosRealizadosChaves.size() > 0) {
            int aux = 0;
            for (Node node : favoritos.getChildren()) {
                if (node instanceof GridPane) {

                    if (aux == 4 || aux == exerciciosRealizadosChaves.size()) break;
                    for (Node component : ((GridPane) node).getChildren()) {
                        int finalAux = aux;

                        List<Exercicio> exercicio = exercicioList.stream().filter(ex -> ex.getId() == exerciciosRealizadosChaves.get(finalAux)).collect(Collectors.toList());
                        int quantidade = reverseSortedMap.get(exerciciosRealizadosChaves.get(finalAux));

                        if (quantidade > 0) {
                            if (component instanceof ImageView) {
                                node.setVisible(true);

                                Image img = new Image(getClass().getResource("/resources/img/" + exercicio.get(0).getImagem() + "White.png").toExternalForm());
                                ((ImageView) component).setImage(img);
                            } else if (component instanceof Label) {
                                if (component.getId().contains("qntFav")) {

                                    ((Label) component).setText(String.valueOf(quantidade));

                                } else {
                                    ((Label) component).setText(exercicio.get(0).getNome());
                                }
                            }
                        }

                    }
                    aux++;
                }

            }
        }
    }

    // Click dos botões de escolher exercícios da tela de configurações (mudar as cores e colocar/tirar da lista)
    // selectedPane = botão do exercícios escolhido
    public void getExercicioEscolhido(GridPane selectedPane) {

        Exercicio exercicio = new Exercicio();
        List<Exercicio> exercicioList = novosExerciciosEscolhidos;
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

        novosExerciciosEscolhidos = exercicioList;
    }

    // Listagem exercícios tela de configurações
    public void listExerciciosConfig() {
        try {
            HashMap<Integer, Exercicio> exercicioHashMap = FuncionarioApp.exercicioHashMap(func.getExercicios());
            int aux = 0;
            for (Node pane : exercicios.getChildren()) {
                if (aux < 16) {

                    if (pane instanceof GridPane) {
                        Exercicio ex = exercicioList.get(aux);

                        pane.setId(pane.getId() + "_" + ex.getId());
                        Boolean exists = false;
                        for (Node child : ((GridPane) pane).getChildren()) {

                            if (child instanceof ImageView) {
                                Image img;
                                if (exercicioHashMap.containsKey(ex.getId())) {
                                    exists = true;
                                    pane.setStyle("-fx-background-color: #A3D3E4");
                                    img = FuncionarioApp.imagemExercicio(true, ex);
                                } else {
                                    img = FuncionarioApp.imagemExercicio(false, ex);
                                }

                                ((ImageView) child).setImage(img);
                            }
                            if (child instanceof Label) {
                                ((Label) child).setTextFill(FuncionarioApp.lblExercicioCor(exists));
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

    // Abertura do pop-up de sucesso
    public void showPopUpSucesso() {
        try {

            Parent root;
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample/PopUpSucesso/PopUpSucesso.fxml"));
            root = fxmlLoader.load();
            popUpSucessoController popUpController = fxmlLoader.getController();
            popUpController.controller = this;
            popUpController.titulo = "Parabéns!";
            popUpController.mensagem = "Você completou um exercício.";
            popUpController.initialize(null, null);

            FuncionarioApp.openModal("/resources/img/w!.png", root);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Abertura do pop-up de confirmação do cancelamento do exercício
    public void showConfirmacaoCancelarExercicio() throws IOException, InterruptedException {

        playPause(false);

        Parent root;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample/PopUpDelete/PopUpDelete.fxml"));
        root = fxmlLoader.load();
        popUpDeleteController popUpController = fxmlLoader.getController();
        popUpController.controller = this;
        popUpController.titulo = "Atenção!";
        popUpController.mensagem = "Ao cancelar o exercício, todo o progresso será perdido. Deseja realmente cancelar?";
        popUpController.initialize(null, null);

        FuncionarioApp.openModal("/resources/img/w!.png", root);
    }

    // Realiza uma ação de acordo com o parâmetro recebido
    public void confirmarCancelarExercicio(boolean cancelar) throws InterruptedException {
        if (cancelar) {
            resetDetails();
            goToHome();
        } else {
            playPause(true);
        }
    }

    public void setTimerExercicio() {

        if (timeline != null) {
            timeline.stop();
        }

        timeline = new Timeline();
        timelineInstrucoes = new Timeline();

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(
                new KeyFrame(Duration.seconds(1),
                        (EventHandler) event -> {
                            timeSeconds--;

                            timerDetails.setText(secondsToString(timeSeconds));
                            if (timeSeconds <= 0) {
                                setDoneEx();
                                resetDetails();
                                showPopUpSucesso();

                            }
                        }));

        timeline.playFromStart();

        final int[] aux = {0};

        timelineInstrucoes.setCycleCount(Timeline.INDEFINITE);
        timelineInstrucoes.getKeyFrames().add(
                new KeyFrame(Duration.seconds(5),
                        (EventHandler) event -> {
                            aux[0]++;
                            if (aux[0] == instrucoes.size()) {
                                aux[0] = 0;
                            }

                            instrucaoDetails.setText(instrucoes.get(aux[0]));
                        }));

        timelineInstrucoes.playFromStart();

    }

    // Passa o exercício executado para salvar no banco junto com a nova quantidade
    // de vezes que ainda é possível realizar exercícios
    private void setDoneEx() {
        FuncionarioApp.salvaExercicioFeito(exercicioExecutado, --qntExerciciosDisponivel);
        exercicioExecutado = null;
        btnImagePlay.setImage(imagePlay);
    }

    // Volta para o dashboard principal do funcionário
    public void goToHome() {
        disableButtons(false);
        loadConfig();
        setHomeVisible();
    }

    // Conversão de segundos para string
    private String secondsToString(int pTime) {
        return String.format("%1d:%02d", pTime / 60, pTime % 60);
    }

    // Mostra o intervalo selecionado pelo funcionário anteriormente
    public void getIntervaloExercicios() {

        String selectedInterval = FuncionarioApp.formataIntervaloExercicios(func);

        if (func.getIntervaloExercicios() != null) {
            GridPane botao = (GridPane) intervaloPane.lookup("#intervalo_" + selectedInterval);
            Label lblBotao = (Label) botao.lookup("#lblIntervalo_" + selectedInterval);

            changeSelectStyle(true, lblBotao, botao);

        }
    }

    // Muda e salva o intervalo de exercícios
    public void setIntervaloExercicios(GridPane newSelectedPane) {
        String oldIntervalo = FuncionarioApp.formataIntervaloExercicios(func);
        String newIntervalo = newSelectedPane.getId().split("_")[1];
        Label lblSelected;

        if (func.getIntervaloExercicios() != null) {
            GridPane botao = (GridPane) intervaloPane.lookup("#intervalo_" + oldIntervalo);
            lblSelected = (Label) botao.lookup("#lblIntervalo_" + oldIntervalo);

            changeSelectStyle(false, lblSelected, botao);
        }

        lblSelected = (Label) newSelectedPane.lookup("#lblIntervalo_" + newIntervalo);
        changeSelectStyle(true, lblSelected, newSelectedPane);

        FuncionarioApp.formataNovoIntervaloExercicios(newIntervalo, func);
    }

    // Mostra a duração do exercício selecionada pelo funcionário anteriormente
    public void getDuracaoExercicios() {

        String selectedDuracao = FuncionarioApp.formataDuracaoExercicios(func);

        if (!selectedDuracao.equals("0")) {
            GridPane botao = (GridPane) duracaoPane.lookup("#duracao_" + selectedDuracao);
            Label lblBotao = (Label) botao.lookup("#lblDuracao_" + selectedDuracao);

            changeSelectStyle(true, lblBotao, botao);
        }
    }

    // Muda a duração do exercício
    public void setDuracaoExercicios(GridPane newSelectedPane) {

        String oldDuracao = FuncionarioApp.formataDuracaoExercicios(func);
        String newDuracao = newSelectedPane.getId().split("_")[1];
        Label lblSelected;

        if (!oldDuracao.equals("0")) {
            GridPane botao = (GridPane) duracaoPane.lookup("#duracao_" + oldDuracao);
            lblSelected = (Label) botao.lookup("#lblDuracao_" + oldDuracao);

            changeSelectStyle(false, lblSelected, botao);
        }

        lblSelected = (Label) newSelectedPane.lookup("#lblDuracao_" + newDuracao);
        changeSelectStyle(true, lblSelected, newSelectedPane);

        FuncionarioApp.salvaDuracaoExercicio(newDuracao, func);
    }

    // Muda o estilo dos botões de seleção de duração, intervalo e exercício
    public void changeSelectStyle(Boolean selected, Label lblSelected, Pane paneSelected) {

        if (selected) {
            paneSelected.setStyle("-fx-background-color: #A3D3E4");
            lblSelected.setTextFill(Color.WHITE);
        } else {
            paneSelected.setStyle("-fx-background-color: #F6F6F6");
            lblSelected.setTextFill(Paint.valueOf("#071c35"));
        }
    }

    // Pega a lista de exercícios existentes no sistema
    public void getExerciciosEscolhidos() {
        func.setExercicios(FuncionarioApp.listExercicios(func));
    }

    // Mostra o horário formatado e validado
    public void getHorario() {

        horaInicial.setText(FuncionarioApp.formataHorario(func.getHoraInicio()));
        horaFinal.setText(FuncionarioApp.formataHorario(func.getHoraTermino()));
    }

    // Mostra o prêmio ou seu componente de vazio caso não exista
    public void getPremio() {
        premio.setText(FuncionarioApp.nomePremio());
        semPremio.setVisible(!empresa.isPossuiPremio());
    }

    // Pega a lista de instruções do exercício
    public void getInstrucoes(Exercicio exercicio) {
        instrucoes = FuncionarioApp.listInstrucoes(exercicio);
    }

    // Reseta o estado dos botões do menu lateral
    private void disableButtons(boolean disable) {
        Home.setDisable(disable);
        Config.setDisable(disable);
        Logout.setDisable(disable);
    }

    // Direciona para a tela de detalhes do exercício
    private void goToExercise() {
        configScreen.setVisible(false);
        exerciseScreen.setVisible(true);
        homeGrayScreen.setVisible(false);
        homeWhiteScreen.setVisible(false);

    }

    // Formata os campos da tela de detalhes do exercício
    private void iniciarExercicio(Exercicio exercicio) {
        disableButtons(true);
        nomeDetails.setText(exercicio.getNome());
        tempoDetails.setText(func.getDuracaoExercicios() + " min");
        Image imgEx = FuncionarioApp.imagemExercicio(true, exercicio);
        imgDetails.setImage(imgEx);
        timerDetails.setText(func.getDuracaoExercicios() + ":00");

        exercicioExecutado = exercicio;
        getInstrucoes(exercicio);
        instrucaoDetails.setText(instrucoes.get(0));

        try {
            AuditoriaTest.getInstance().StartThread("Initialize");
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }

        goToExercise();
    }

    // Método chamado pelo evento de click dos botões de exercícios do dia do dashboard principal
    private void clickEventExercicioDoDia(Exercicio ex) {
        disableButtons(true);
        nomeDetails.setText(ex.getNome());
        tempoDetails.setText(func.getDuracaoExercicios() + " min");
        Image imgEx = FuncionarioApp.imagemExercicio(true, ex);
        imgDetails.setImage(imgEx);
        timerDetails.setText(func.getDuracaoExercicios() + ":00");

        exercicioExecutado = ex;
        getInstrucoes(ex);
        instrucaoDetails.setText(instrucoes.get(0));

        try {
            AuditoriaTest.getInstance().StartThread("Exercise Details");
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }

        goToExercise();
    }

    // Lista os exercícios do dia no dashboard principal
    private void listExerciciosDoDia(List<Exercicio> exercicios) {
        int aux = 0;
        for (Node pane : exerciciosDoDia.getChildren()) {
            if (pane instanceof GridPane) {
                pane.setVisible(false);
            }
            if (aux < exercicios.size()) {

                if (pane instanceof GridPane) {
                    Exercicio ex = exercicios.get(aux);
                    pane.setId(pane.getId() + "_" + ex.getId());
                    pane.setVisible(true);
                    for (Node child : ((GridPane) pane).getChildren()) {

                        if (child instanceof ImageView) {

                            if (child.getId() != null && child.getId().contains("exDia")) {
                                Image img = FuncionarioApp.imagemExercicio(true, ex);
                                ((ImageView) child).setImage(img);
                            } else {
                                child.setPickOnBounds(true);
                                child.setOnMouseClicked((MouseEvent e) -> {
                                    clickEventExercicioDoDia(ex);
                                });
                            }
                        }
                        if (child instanceof Label) {
                            ((Label) child).setText(FuncionarioApp.formatDuracaoExercicioDetails(child, ex, func));
                        }
                    }
                    aux++;
                }
            }
        }
    }

    // Listagem dos exercícios do usuário no dashboard principal
    private void getExerciciosDoDia() {

        List<Exercicio> exercicios = FuncionarioApp.listExercicios(func);
        semExercicios.setVisible(exercicios.size() == 0);

        // Card de PRÓXIMO EXERCÍCIO
        if (exercicios.size() > 0) {
            Exercicio exercicio = exercicios.get(0);

            proxExercicio.setText(exercicio.getNome());
            Image img = FuncionarioApp.imagemExercicio(true, exercicio);
            imgProxEx.setImage(img);

            btnIniciar.setPickOnBounds(true);
            btnIniciar.setOnMouseClicked((MouseEvent e) -> {
                iniciarExercicio(exercicio);
            });

            listExerciciosDoDia(exercicios);
        }

    }

    // Reset dos timers após cancelar a realização do exercício
    private void resetDetails() {

        if (timeline != null && timelineInstrucoes != null) {
            timeline.stop();
            timelineInstrucoes.stop();
        }

        pause = false;

        timeSeconds = func.getDuracaoExercicios() * 60;
    }

    // Verifica se ainda é possível fazer exercícios de acordo com a quantidade
    // disponível na rotina do usuário
    private void checkAvailableEx() {
        Integer qnt = Rotina.getInstance().getQntDisponivelExercicios();
        if (qnt == null || (qnt != null && qnt == 0)) {

            String currentDate = String.valueOf(LocalDate.now());
            String lastDate = FuncionarioApp.getLastDateExerciseWasDone();

            if (!lastDate.equals(null)) {
                bloqExDoDia.setVisible(currentDate.equals(lastDate));
                bloqParabens.setVisible(currentDate.equals(lastDate));

                if (!currentDate.equals(lastDate)) {
                    FuncionarioApp.saveRotina(qntExDisponivel());
                    qntExerciciosDisponivel = qntExDisponivel();
                }
            }
        }
    }

    // Carrega as principais informações da tela
    private void loadConfig() {
        getDuracaoExercicios();
        getIntervaloExercicios();
        getExerciciosEscolhidos();
        listExerciciosConfig();
        getHorario();
        getPremio();
        getExerciciosDoDia();
        checkAvailableEx();
        getConquistasFavoritos(func.getId());
        rankingFuncionarios = listarUsuariosEmpresa();

        int posicaoRanking = Arrays.asList(rankingFuncionarios).indexOf(func.getId()) + 1;

        ranking.setText("Sua posição: " + posicaoRanking);
        timeSeconds = func.getDuracaoExercicios() * 60;
        nomeUsuario.setText(func.getNome());

    }

    // Controla o andamento do exercício
    private void playPause(boolean play) throws InterruptedException {
        if (play) {
            pause = true;
            setTimerExercicio();
            btnImagePlay.setImage(imagePause);
            AuditoriaTest.getInstance().StartThread("Play");
        } else {
            btnImagePlay.setImage(imagePlay);
            pause = false;

            if (timeline != null && timelineInstrucoes != null) {
                timeline.pause();
                timelineInstrucoes.pause();
            }

            AuditoriaTest.getInstance().StartThread("Pause");
        }
    }

    // Retorna a quantidade de exercícios disponível para realizar
    // de acordo com a rotina do usuário
    private Integer qntExDisponivel() {
        int total = 0;
        total = FuncionarioApp.qtdExercisesToDo(func);

        return total;
    }

    private void startEventExercicioEscolhido(GridPane gridPane) {
        gridPane.setOnMouseClicked((MouseEvent e) -> {
            getExercicioEscolhido(gridPane);
        });
    }

    private void startEventDuracaoExercicios(GridPane gridPane) {
        gridPane.setOnMouseClicked((MouseEvent e) -> {
            setDuracaoExercicios(gridPane);
        });
    }

    private void startEventIntervaloExercicios(GridPane gridPane) {
        gridPane.setOnMouseClicked((MouseEvent e) -> {
            setIntervaloExercicios(gridPane);
        });
    }

    private void setHomeVisible(){
        try {
            AuditoriaTest.getInstance().StartThread("Home");
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
        homeGrayScreen.setVisible(true);
        homeWhiteScreen.setVisible(true);
        exerciseScreen.setVisible(false);
        configScreen.setVisible(false);
    }

    private void goToConfig(){
        try {
            AuditoriaTest.getInstance().StartThread("Settings");
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }

        configScreen.setVisible(true);
        exerciseScreen.setVisible(false);
        homeGrayScreen.setVisible(false);
        homeWhiteScreen.setVisible(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        FuncionarioApp.startNotificationTimer(func);
        loadConfig();

        if (qntExerciciosDisponivel == 0) {
            Integer qnt = Rotina.getInstance().getQntDisponivelExercicios();
            qntExerciciosDisponivel = qnt == null ? 0 : qnt;
        }

        for (Exercicio exercicio : func.getExercicios()) {
            novosExerciciosEscolhidos.add(exercicio);
        }

        playGrid.setOnMouseClicked((MouseEvent e) -> {
            try {
                if (pause) {
                    playPause(false);

                } else {
                    playPause(true);
                }
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        });

        btnCancelar.setOnMouseClicked((MouseEvent e) -> {
            try {
                showConfirmacaoCancelarExercicio();
            } catch (IOException | InterruptedException ioException) {
                ioException.printStackTrace();
            }

        });

        btnConfigure.setOnMouseClicked((MouseEvent e) -> {
            goToConfig();

        });

        startEventExercicioEscolhido(exercicio1);
        startEventExercicioEscolhido(exercicio2);
        startEventExercicioEscolhido(exercicio3);
        startEventExercicioEscolhido(exercicio4);
        startEventExercicioEscolhido(exercicio5);
        startEventExercicioEscolhido(exercicio6);
        startEventExercicioEscolhido(exercicio7);
        startEventExercicioEscolhido(exercicio8);
        startEventExercicioEscolhido(exercicio9);
        startEventExercicioEscolhido(exercicio10);
        startEventExercicioEscolhido(exercicio11);
        startEventExercicioEscolhido(exercicio12);
        startEventExercicioEscolhido(exercicio13);
        startEventExercicioEscolhido(exercicio14);
        startEventExercicioEscolhido(exercicio15);
        startEventExercicioEscolhido(exercicio16);

        startEventDuracaoExercicios(duracao_1);
        startEventDuracaoExercicios(duracao_2);
        startEventDuracaoExercicios(duracao_3);
        startEventDuracaoExercicios(duracao_4);
        startEventDuracaoExercicios(duracao_5);
        startEventDuracaoExercicios(duracao_6);

        startEventIntervaloExercicios(intervalo_10000);
        startEventIntervaloExercicios(intervalo_20000);
        startEventIntervaloExercicios(intervalo_30000);
        startEventIntervaloExercicios(intervalo_13000);
        startEventIntervaloExercicios(intervalo_23000);
        startEventIntervaloExercicios(intervalo_3000);

        lembrete.setText(FuncionarioApp.setLembreteUsuario(func));

        semMotivacao.setVisible(empresa.getFraseMotivacional() == null || empresa.getFraseMotivacional().isEmpty());
        if (empresa.getFraseMotivacional() != null && !empresa.getFraseMotivacional().isEmpty()) {
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

        lembrete.setPickOnBounds(true);
        lembrete.setOnMouseClicked((MouseEvent e) -> {
            lembreteEdit.setText(func.getLembrete());
            lembreteEdit.setVisible(true);
            lembrete.setVisible(false);
        });

        Home.setPickOnBounds(true);
        Home.setOnMouseClicked((MouseEvent e) -> {
            loadConfig();
            setHomeVisible();
        });


        Config.setPickOnBounds(true);
        Config.setOnMouseClicked((MouseEvent e) -> {
            goToConfig();
        });

        Logout.setPickOnBounds(true);
        Logout.setOnMouseClicked((MouseEvent e) -> {
            try {
                NotificationApp.timeline.stop();
                novosExerciciosEscolhidos.clear();

                Parent root;
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample/Login/login.fxml"));
                root = fxmlLoader.load();
                FuncionarioApp.openModal("/resources/img/w!.png", root);

                Stage stage = (Stage) Logout.getScene().getWindow();
                stage.close();

                AuditoriaTest.getInstance().StartThread("Logout");
            } catch (InterruptedException | IOException ioException) {
                ioException.printStackTrace();
            }
        });


        iniciarConfig.setPickOnBounds(true);
        iniciarConfig.setOnMouseClicked((MouseEvent e) -> {

            Boolean valid;
            aviso.setVisible(false);
            avisoDuracao.setVisible(false);
            avisoIntervalo.setVisible(false);
            avisoHorario.setVisible(false);

            aviso.setVisible(FuncionarioApp.validateExerciciosEscolhidos(novosExerciciosEscolhidos));
            valid = FuncionarioApp.validateExerciciosEscolhidos(novosExerciciosEscolhidos);

            avisoIntervalo.setVisible(FuncionarioApp.validateIntervaloExercicios(func.getIntervaloExercicios()));
            valid = FuncionarioApp.validateIntervaloExercicios(func.getIntervaloExercicios());

            avisoDuracao.setVisible(FuncionarioApp.validateDuracaoExercicios(func.getDuracaoExercicios()));
            valid = FuncionarioApp.validateDuracaoExercicios(func.getDuracaoExercicios());

            valid = !FuncionarioApp.validateHorario(horaInicial, horaFinal);
            avisoHorario.setVisible(FuncionarioApp.validateHorario(horaInicial, horaFinal));
            if(!FuncionarioApp.validateHorario(horaInicial, horaFinal)){
                func.setHoraInicio(horaInicial.getText());
                func.setHoraTermino(horaFinal.getText());
            }

            if (valid) {

                FuncionarioApp.saveConfig(func, qntExDisponivel(), novosExerciciosEscolhidos);
                FuncionarioApp.startNotificationTimer(func);

                loadConfig();
                setHomeVisible();
                try {
                    AuditoriaTest.getInstance().StartThread("Initialize");
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }

            }
        });


    }
}