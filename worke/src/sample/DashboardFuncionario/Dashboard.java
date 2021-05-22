package sample.DashboardFuncionario;

import DAO.acesso.ExercicioDAO;
import DAO.acesso.FuncionarioDAO;
import DAO.acesso.PremioDAO;
import DAO.acesso.UsuarioDAO;
import DAO.auditoria.AuditoriaTest;
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
import java.net.URL;
import java.sql.Array;
import java.sql.Time;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
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


    private Funcionario func = Funcionario.getInstance();
    ExercicioDAO exercicioDAO = new ExercicioDAO();
    List<Exercicio> exercicioList = exercicioDAO.listar();
    UsuarioDAO dao = new UsuarioDAO();
    Empresa empresa = dao.listarDadosEmpresa();

    DecimalFormat df = new DecimalFormat("#");

    Image imagePause = new Image(getClass().getResource("/resources/img/simbolo-de-pausa.png").toExternalForm());
    Image imagePlay = new Image(getClass().getResource("/resources/img/botao-play-ponta-de-seta.png").toExternalForm());

    static boolean pause;

    private Timeline timeline;
    private Timeline timelineInstrucoes;

    private static int timeSeconds;

    private static int qntExerciciosDisponivel;

    private static Exercicio exercicioExecutado;

    public static List<String> instrucoes;

    private static List<Exercicio> novosExerciciosEscolhidos = new ArrayList<>();


    public LinkedHashMap<Integer, Integer> sortHashMapByValues(
            HashMap<Integer, Integer> passedMap) {
        List<Integer> mapKeys = new ArrayList<>(passedMap.keySet());
        List<Integer> mapValues = new ArrayList<>(passedMap.values());
        Collections.sort(mapValues);
        Collections.sort(mapKeys);

        LinkedHashMap<Integer, Integer> sortedMap =
                new LinkedHashMap<>();

        Iterator<Integer> valueIt = mapValues.iterator();
        while (valueIt.hasNext()) {
            Integer val = valueIt.next();
            Iterator<Integer> keyIt = mapKeys.iterator();

            while (keyIt.hasNext()) {
                Integer key = keyIt.next();
                Integer comp1 = passedMap.get(key);
                Integer comp2 = val;

                if (comp1.equals(comp2)) {
                    keyIt.remove();
                    sortedMap.put(key, val);
                    break;
                }
            }
        }
        return sortedMap;
    }

    public void getConquistasFavoritos() {
        ExercicioDAO exercicioDAO = new ExercicioDAO();
        ArrayList<ExercicioEscolhido> exercicioEscolhidosList = exercicioDAO.listarTotalExercicios();
        HashMap<Integer, ArrayList<ExercicioEscolhido>> mapExercicioId = new HashMap<Integer, ArrayList<ExercicioEscolhido>>();
        HashMap<Integer, Integer> mapExercicioIdQnt = new HashMap<Integer, Integer>();

        for (ExercicioEscolhido exercicioEscolhido : exercicioEscolhidosList) {
            if (!mapExercicioId.containsKey(exercicioEscolhido.getExercicioId())) {
                mapExercicioId.put(exercicioEscolhido.getExercicioId(), new ArrayList<ExercicioEscolhido>());
            }
            mapExercicioId.get(exercicioEscolhido.getExercicioId()).add(exercicioEscolhido);
        }
        for (Integer i = 1; i <= 17; i++) {
            if (mapExercicioId.containsKey(i)) {
                Integer qtd = 0;
                for (ExercicioEscolhido exercicioEscolhido : mapExercicioId.get(i)) {
                    qtd += exercicioEscolhido.getQntRealizado();
                    mapExercicioIdQnt.put(i, qtd);
                }
            }
        }
        LinkedHashMap<Integer, Integer> mapExercicioQtdOrdenado = sortHashMapByValues(mapExercicioIdQnt);
        LinkedHashMap<Integer, Integer> reverseSortedMap = new LinkedHashMap<>();

        //Use Comparator.reverseOrder() for reverse ordering
        mapExercicioQtdOrdenado.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));


        ArrayList<Integer> exerciciosRealizadosChaves = new ArrayList<>();
        Integer totalExerciciosRealizados = 0;
        for (Integer i : reverseSortedMap.keySet()) {
            exerciciosRealizadosChaves.add(i);
            if (reverseSortedMap.containsKey(i)){
                totalExerciciosRealizados += reverseSortedMap.get(i);
            }
        }

        qntExercicios.setText(String.valueOf(totalExerciciosRealizados));
        minutosTotal.setText(String.valueOf(exercicioDAO.consultarDuracaoTotalUsuario()));

        int aux = 0;
        for (Node node : favoritos.getChildren()) {
            if (node instanceof GridPane) {

                if (aux == 4) break;
                for (Node component : ((GridPane) node).getChildren()) {
                    int finalAux = aux;
                    List<Exercicio> exercicio = exercicioList.stream().filter(ex -> ex.getId() == exerciciosRealizadosChaves.get(finalAux)).collect(Collectors.toList());
                    if (component instanceof ImageView) {
                        Image img = new Image(getClass().getResource("/resources/img/" + exercicio.get(0).getImagem() + "White.png").toExternalForm());
                        ((ImageView) component).setImage(img);
                    } else if (component instanceof Label) {
                        if (component.getId().contains("qntFav")) {
                            ((Label) component).setText(String.valueOf(reverseSortedMap.get(exerciciosRealizadosChaves.get(finalAux))));
                        } else {
                            ((Label) component).setText(exercicio.get(0).getNome());
                        }
                    }
                }
                aux++;
            }

        }
    }

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

    public void listExerciciosDoDia() {
        try {

            int aux = 0;

            HashMap<Integer, Exercicio> exercicioHashMap = new HashMap<>();
            for (Exercicio exercicio : func.getExercicios()) {
                exercicioHashMap.put(exercicio.getId(), exercicio);
            }

            for (Node pane : exercicios.getChildren()) {
                if (aux < 16) {

                    if (pane instanceof GridPane) {
                        Exercicio ex = exercicioList.get(aux);

                        ((GridPane) pane).setId(pane.getId() + "_" + String.valueOf(ex.getId()));
                        Boolean exists = false;
                        for (Node child : ((GridPane) pane).getChildren()) {

                            if (child instanceof ImageView) {
                                Image img = null;
                                if (exercicioHashMap.containsKey(ex.getId())) {
                                    exists = true;
                                    ((GridPane) pane).setStyle("-fx-background-color: #A3D3E4");
                                    img = new Image(getClass().getResource("/resources/img/" + ex.getImagem() + "White.png").toExternalForm());
                                } else {
                                    img = new Image(getClass().getResource("/resources/img/" + ex.getImagem() + "Dark.png").toExternalForm());
                                }

                                ((ImageView) child).setImage(img);
                            }
                            if (child instanceof Label) {
                                if (exists) {
                                    ((Label) child).setTextFill(Color.WHITE);
                                } else {
                                    ((Label) child).setTextFill(Paint.valueOf("#0B1F38"));
                                }

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

    public void resetPopUpSucesso() {
        try {

            Parent root;
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample/PopUpSucesso/PopUpSucesso.fxml"));
            root = fxmlLoader.load();
            popUpSucessoController popUpController = fxmlLoader.getController();
            popUpController.controller = this;
            popUpController.titulo = "Parabéns!";
            popUpController.mensagem = "Você completou um exercício.";
            popUpController.initialize(null, null);
            Stage dialog = new Stage();
            dialog.getIcons().add(new Image(Main.class.getResourceAsStream("/resources/img/w!.png")));
            dialog.setScene(new Scene(root));
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
        Stage dialog = new Stage();
        dialog.getIcons().add(new Image(Main.class.getResourceAsStream("/resources/img/w!.png")));
        dialog.setScene(new Scene(root));
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.show();
    }

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

        // update timerLabel
        timeline = new Timeline();
        timelineInstrucoes = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(
                new KeyFrame(Duration.seconds(1),
                        new EventHandler() {
                            @Override
                            public void handle(Event event) {
                                timeSeconds--;

                                timerDetails.setText(secondsToString(timeSeconds));
                                if (timeSeconds <= 0) {
                                    resetDetails();
                                    resetPopUpSucesso();

                                }
                            }
                        }));

        timeline.playFromStart();

        final int[] aux = {0};

        timelineInstrucoes.setCycleCount(Timeline.INDEFINITE);
        timelineInstrucoes.getKeyFrames().add(
                new KeyFrame(Duration.seconds(5),
                        new EventHandler() {
                            @Override
                            public void handle(Event event) {
                                aux[0]++;
                                if (aux[0] == instrucoes.size()) {
                                    aux[0] = 0;
                                }

                                instrucaoDetails.setText(instrucoes.get(aux[0]));
                            }

                        }));

        timelineInstrucoes.playFromStart();

    }

    private void setDoneEx() {

        exercicioDAO.exercicioFeito(exercicioExecutado);
        exercicioExecutado = null;
        btnImagePlay.setImage(imagePlay);
    }

    public void goToHome() {
        loadConfig();

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

    private String secondsToString(int pTime) {
        return String.format("%1d:%02d", pTime / 60, pTime % 60);
    }

    public void getIntervaloExercicios() {

        String selectedInterval = String.valueOf(func.getIntervaloExercicios()).replace(":", "");
        if (selectedInterval.equals("003000")) {
            selectedInterval = selectedInterval.substring(2);
        } else {
            selectedInterval = selectedInterval.substring(1);
        }

        if (func.getIntervaloExercicios() != null) {
            GridPane botao = (GridPane) intervaloPane.lookup("#intervalo_" + selectedInterval);
            String teste = "#lblIntervalo_" + selectedInterval;
            Label lblBotao = (Label) botao.lookup(teste);

            //Muda para o estilo de botão selecionado
            changeSelectStyle(true, lblBotao, botao);

        }
    }

    public void setIntervaloExercicios(GridPane newSelectedPane) {
        String oldIntervalo = String.valueOf(func.getIntervaloExercicios()).replace(":", "");
        if (oldIntervalo.equals("003000")) {
            oldIntervalo = oldIntervalo.substring(2);
        } else {
            oldIntervalo = oldIntervalo.substring(1);
        }
        String newIntervalo = newSelectedPane.getId().split("_")[1];
        Label lblSelected;

        if (func.getIntervaloExercicios() != null) {
            GridPane botao = (GridPane) intervaloPane.lookup("#intervalo_" + oldIntervalo);
            lblSelected = (Label) botao.lookup("#lblIntervalo_" + oldIntervalo);

            //Muda para o estilo de botão não selecionado
            changeSelectStyle(false, lblSelected, botao);
        }

        lblSelected = (Label) newSelectedPane.lookup("#lblIntervalo_" + newIntervalo);

        //Muda para o estilo de botão selecionado
        changeSelectStyle(true, lblSelected, newSelectedPane);

        if (!newIntervalo.equals("3000")) {
            newIntervalo = "0" + newIntervalo;
        } else {
            newIntervalo = "00" + newIntervalo;
        }
        newIntervalo = newIntervalo.substring(0, 2) + ":" + newIntervalo.substring(2, 4) + ":00";
        Time selectedIntervalo = Time.valueOf(newIntervalo);
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

        int selectedDuracao = Integer.valueOf(newDuracao);
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

    public void getExerciciosEscolhidos() {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        func.setExercicios(usuarioDAO.consultarExerciciosEscolhidos(usuarioDAO.getLastRotina(func)));
    }

    public void getHorario() {

        if (func.getHoraInicio() == null) {
            horaInicial.setText("00:00");
        } else {
            horaInicial.setText(String.valueOf(func.getHoraInicio()));
        }

        if (func.getHoraTermino() == null) {
            horaFinal.setText("00:00");
        } else {
            horaFinal.setText(String.valueOf(func.getHoraTermino()));
        }
    }

    public void getPremio() {
        PremioDAO premioDAO = new PremioDAO();
        Premio nomePremio = premioDAO.consultar(empresa.getPremioId());
        premio.setText(nomePremio.getDescricao());

    }

    public void getInstrucoes(Exercicio exercicio) {

        instrucoes = exercicioDAO.listarInstrucoes(exercicio.getId());

    }

    public void getExerciciosDoDia() {

        List<Exercicio> exercicios = func.getExercicios();

        if (func.getExercicios().size() > 0) {
            Exercicio exercicio = func.getExercicios().get(0);

            proxExercicio.setText(exercicio.getNome());
            Image img = new Image(getClass().getResource("/resources/img/" + exercicio.getImagem() + "White.png").toExternalForm());
            imgProxEx.setImage(img);

            btnIniciar.setPickOnBounds(true);
            btnIniciar.setOnMouseClicked((MouseEvent e) -> {

                nomeDetails.setText(exercicio.getNome());
                tempoDetails.setText(func.getDuracaoExercicios() + " min");
                Image imgEx = new Image(getClass().getResource("/resources/img/" + exercicio.getImagem() + "White.png").toExternalForm());
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

                configScreen.setVisible(false);
                exerciseScreen.setVisible(true);
                homeGrayScreen.setVisible(false);
                homeWhiteScreen.setVisible(false);

            });
        }


        int aux = 0;
        for (Node pane : exerciciosDoDia.getChildren()) {
            if (pane instanceof GridPane) {
                ((GridPane) pane).setVisible(false);
            }
            if (aux < exercicios.size()) {

                if (pane instanceof GridPane) {
                    Exercicio ex = exercicios.get(aux);

                    ((GridPane) pane).setId(pane.getId() + "_" + String.valueOf(ex.getId()));
                    ((GridPane) pane).setVisible(true);
                    for (Node child : ((GridPane) pane).getChildren()) {

                        if (child instanceof ImageView) {

                            if (((ImageView) child).getId() != null && ((ImageView) child).getId().contains("exDia")) {
                                Image img = new Image(getClass().getResource("/resources/img/" + ex.getImagem() + "White.png").toExternalForm());
                                ((ImageView) child).setImage(img);
                            } else {

                                child.setPickOnBounds(true);
                                child.setOnMouseClicked((MouseEvent e) -> {

                                    nomeDetails.setText(ex.getNome());
                                    tempoDetails.setText(func.getDuracaoExercicios() + " min");
                                    Image imgEx = new Image(getClass().getResource("/resources/img/" + ex.getImagem() + "White.png").toExternalForm());
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
                                    exerciseScreen.setVisible(true);
                                    homeGrayScreen.setVisible(false);
                                    homeWhiteScreen.setVisible(false);
                                    configScreen.setVisible(false);
                                });
                            }
                        }
                        if (child instanceof Label) {

                            if (((Label) child).getId() != null && ((Label) child).getId().contains("nome")) {
                                ((Label) child).setText(ex.getNome());
                            } else {
                                ((Label) child).setText(String.valueOf(func.getDuracaoExercicios()).replace(".0", "") + " min");
                            }
                        }
                    }
                    aux++;
                }
            }
        }
    }

    public void resetDetails() {
        timeline.stop();
        timelineInstrucoes.stop();
        pause = false;
        qntExerciciosDisponivel--;
        setDoneEx();
        timeSeconds = func.getDuracaoExercicios() * 60;
    }

    public void checkAvailableEx() {
        if (qntExerciciosDisponivel < 0) {
            //pane que bloqueia acessar os exercicios
        }
    }

    public void loadConfig() {
        getDuracaoExercicios();
        getIntervaloExercicios();
        getExerciciosEscolhidos();
        listExerciciosDoDia();
        getHorario();
        getPremio();
        getExerciciosDoDia();
        checkAvailableEx();
        getConquistasFavoritos();

        timeSeconds = func.getDuracaoExercicios() * 60;
        nomeUsuario.setText(func.getNome());


        LocalTime horaInicio = LocalTime.parse(func.getHoraInicio());
        LocalTime horaFinal = LocalTime.parse(func.getHoraTermino());
        double horas = (double) ((func.getIntervaloExercicios().getHours() * 60) + func.getIntervaloExercicios().getMinutes()) / 60;

        qntExerciciosDisponivel = Math.round((int) ((int) (((java.time.Duration.between(horaInicio, horaFinal).toHours()) - 1)) / horas));
    }

    public void playPause(boolean play) throws InterruptedException {
        if (play) {
            pause = true;
            setTimerExercicio();
            btnImagePlay.setImage(imagePause);
            AuditoriaTest.getInstance().StartThread("Pause");
        } else {
            btnImagePlay.setImage(imagePlay);
            pause = false;
            timeline.pause();
            timelineInstrucoes.pause();
            AuditoriaTest.getInstance().StartThread("Play");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        loadConfig();
        for (Exercicio exercicio : func.getExercicios()) {
            novosExerciciosEscolhidos.add(exercicio);
        }

        FuncionarioDAO daoFunc = new FuncionarioDAO();

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

        intervalo_10000.setOnMouseClicked((MouseEvent e) -> {
            setIntervaloExercicios(intervalo_10000);
        });
        intervalo_20000.setOnMouseClicked((MouseEvent e) -> {
            setIntervaloExercicios(intervalo_20000);
        });
        intervalo_30000.setOnMouseClicked((MouseEvent e) -> {
            setIntervaloExercicios(intervalo_30000);
        });
        intervalo_13000.setOnMouseClicked((MouseEvent e) -> {
            setIntervaloExercicios(intervalo_13000);
        });
        intervalo_23000.setOnMouseClicked((MouseEvent e) -> {
            setIntervaloExercicios(intervalo_23000);
        });
        intervalo_3000.setOnMouseClicked((MouseEvent e) -> {
            setIntervaloExercicios(intervalo_3000);
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

            loadConfig();

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
            } catch (InterruptedException | IOException ioException) {
                ioException.printStackTrace();
            }
        });


        iniciarConfig.setPickOnBounds(true);
        iniciarConfig.setOnMouseClicked((MouseEvent e) -> {

            Boolean valid = true;
            aviso.setVisible(false);
            avisoDuracao.setVisible(false);
            avisoIntervalo.setVisible(false);
            avisoHorario.setVisible(false);

            if (novosExerciciosEscolhidos.size() == 0) {
                valid = false;
                aviso.setVisible(true);
            }
            if (func.getIntervaloExercicios() == null) {
                valid = false;
                avisoIntervalo.setVisible(true);
            }
            if (func.getDuracaoExercicios() == 0) {
                valid = false;
                avisoDuracao.setVisible(true);
            }

            if (horaInicial.getText().equals("00:00") || horaFinal.getText().equals("00:00")) {
                valid = false;
                avisoHorario.setVisible(true);
            } else {
                func.setHoraInicio(horaInicial.getText());
                func.setHoraTermino(horaFinal.getText());
            }

            if (valid) {
                boolean equalLists = func.getExercicios().size() == novosExerciciosEscolhidos.size() && func.getExercicios().containsAll(novosExerciciosEscolhidos) && novosExerciciosEscolhidos.containsAll(func.getExercicios());

                if (!equalLists) {
                    func.setExercicios(novosExerciciosEscolhidos);
                    exercicioDAO.escolherExercicio(func);

                }

                daoFunc.alterarFuncionario(func);
                loadConfig();
                try {
                    AuditoriaTest.getInstance().StartThread("Initialize");
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
                configScreen.setVisible(false);
                exerciseScreen.setVisible(false);
                homeGrayScreen.setVisible(true);
                homeWhiteScreen.setVisible(true);
            }
        });


    }
}
