package sample.DashboardEmpresa;

import DAO.acesso.EmpresaDAO;
import DAO.acesso.PremioDAO;
import DAO.acesso.UsuarioDAO;
import DAO.auditoria.AuditoriaTest;
import application.EmpresaApp;
import application.NotificationApp;
import comuns.acesso.Empresa;
import comuns.acesso.Usuario;
import comuns.acesso.Premio;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import sample.Main;
import sample.PopUpCriarFuncionarios.PopUpCriarFuncionarioController;
import sample.PopUpDelete.popUpDeleteController;
import sample.PopUpSucesso.popUpSucessoController;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class DashboardEmpresaController implements Initializable {
    @FXML
    private Button btnSalvarConfig;
    @FXML
    private Button btnCriarUsuario;
    @FXML
    private Button btnFinalizar;
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
    @FXML
    private Label data;
    @FXML
    private Label hora;
    @FXML
    private Label nomeEmpresa;
    @FXML
    private TableColumn<Users, String> nomeCol;
    @FXML
    private TableColumn<Users, String> emailCol;
    @FXML
    private TableColumn<Users, Void> editCol;
    @FXML
    public TableView usuariosTable;
    @FXML
    private TextField pesquisarUsuario;
    @FXML
    private TextArea fraseMotivacional;
    @FXML
    private TextArea premio;
    @FXML
    private CheckBox naoPossuiPremio;
    @FXML
    public Label qntFuncionariosTotal;
    @FXML
    private Label qntExerciciosTotal;
    @FXML
    private Label qntMinutosTotal;
    @FXML
    private Label qntPremiosTotal;
    @FXML
    private Label qntHorasDia;
    @FXML
    private Label qntExerciciosDia;
    @FXML
    private Label premioErro;
    @FXML
    private BarChart barChart;
    @FXML
    private Pane vazioVerificacao;
    @FXML
    private Pane vazioMaisRealizados;
    @FXML
    private GridPane rankingPane;
    @FXML
    private GridPane chartEx;
    @FXML
    private Pane semRanking;
    @FXML
    private ScrollPane premiosScroll;
    @FXML
    private GridPane premiosGrid;

    private static LinkedHashMap<Integer, Integer> usuariosEmpresa;

    private static HashMap<Integer, String> usuarioIdNome;

    private static Integer[] usuarioIdArray;

    private static Users funcionariosEmpresa;

    // Lista os prêmios antigos finalizados
    public void setHistoricoPremios() throws IOException {
        premiosGrid.getChildren().clear();
        List<Premio> premioList = EmpresaApp.listarPremios();
        int column = 0;
        int row = 1;
        for (Premio premio : premioList) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("premio.fxml"));
            VBox premioBox = fxmlLoader.load();
            premioController premioController = fxmlLoader.getController();
            Usuario user = EmpresaApp.consultarUsuarioId(premio.getUsuarioId());
            premioController.setData(premio, user.getNome());

            if (column == 3) {
                column = 0;
                ++row;
            }

            premiosGrid.add(premioBox, column++, row);
            GridPane.setMargin(premioBox, new Insets(10));
        }
    }

    // Mostra o relógio e data do dashboard principal da empresa
    public void setDateTime() {

        String pattern = "dd/MM/yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        data.setText(simpleDateFormat.format(new Date()));

        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
            String currentTime = LocalTime.now().format(dtf);
            hora.setText(currentTime);

        }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    // Lista o ranking top 3 no dashboard principal da empresa
    private void setRanking() {
        usuarioIdNome = EmpresaApp.mapFuncionarioIdNome(usuariosEmpresa);
        usuarioIdArray = EmpresaApp.listarUsuariosEmpresa(usuariosEmpresa);

        semRanking.setVisible(usuarioIdArray.length == 0);
        if (usuarioIdArray.length > 0) {
            int aux = 0;
            for (Node node : rankingPane.getChildren()) {
                if (aux == usuarioIdArray.length) break;
                if (node instanceof Pane) {

                    node.setVisible(true);

                    for (Node component : ((Pane) node).getChildren()) {

                        if (component instanceof Label) {

                            if (component.getId().contains("ranking")) {
                                ((Label) component).setText(usuarioIdNome.get(usuarioIdArray[aux]));
                                aux++;
                            }
                        }

                    }

                }

            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        premiosScroll.setPickOnBounds(false);

        vazioVerificacao.setVisible(!EmpresaApp.existsDataChartVerificacao());
        if (EmpresaApp.existsDataChartVerificacao()) {
            chartEx.add(EmpresaApp.createChart(), 1, 1);
        }

        vazioMaisRealizados.setVisible(EmpresaApp.calcTotalExerciciosExEscolhido().size() == 0);
        if (EmpresaApp.calcTotalExerciciosExEscolhido().size() > 0) {
            barChart.getData().add(EmpresaApp.createBarChart());
            barChart.setLegendSide(Side.TOP);
        }

        homeCarregarDados();
        fraseMotivacional.setTextFormatter(new TextFormatter<String>(change ->
                change.getControlNewText().length() <= 45 ? change : null));

        try {
            setHistoricoPremios();
        } catch (IOException e) {
            e.printStackTrace();
        }

        pesquisarUsuario.textProperty().addListener((observableValue, oldValue, newValue) -> loadUsuarios(usuariosTable));
        naoPossuiPremio.selectedProperty().addListener((observableValue, oldValue, newValue) -> premio.setDisable(naoPossuiPremio.isSelected()));


        Empresa emp = Empresa.getInstance();
        nomeEmpresa.setText(emp.getNome());

        logout.setPickOnBounds(true);
        logout.setOnMouseClicked((MouseEvent e) -> {
            try {

                NotificationApp.timeline.stop();

                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/sample/Login/login.fxml")));

                Stage stage = new Stage();
                stage.getIcons().add(new Image(Main.class.getResourceAsStream("/resources/img/w!.png")));
                stage.setResizable(false);
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
        btnUsuarios.setOnMouseClicked((MouseEvent e) -> goToUsers());

        btnHistorico.setPickOnBounds(true);
        btnHistorico.setOnMouseClicked((MouseEvent e) -> {
            try {
                setHistoricoPremios();
                goToHistorico();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            try {
                AuditoriaTest.getInstance().StartThread("History");
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        });

        home.setPickOnBounds(true);
        home.setOnMouseClicked((MouseEvent e) -> {
            try {
                qntFuncionariosTotal.setText(String.valueOf(EmpresaApp.totalFuncionarios(usuariosEmpresa)));
                premio.setText(Empresa.getInstance().getNomePremio());
                AuditoriaTest.getInstance().StartThread("Home");
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
            homeCarregarDados();
            UserPane.setVisible(false);
            PremioPane.setVisible(false);
            DashboardPane.setVisible(true);
            ConfigPane.setVisible(false);
        });

        historico.setPickOnBounds(true);
        historico.setOnMouseClicked((MouseEvent e) -> {
            try {
                setHistoricoPremios();
                goToHistorico();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            try {
                AuditoriaTest.getInstance().StartThread("History");
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }

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


            loadUsuarios(usuariosTable);
        });

        config.setPickOnBounds(true);
        config.setOnMouseClicked((MouseEvent e) -> {

            premioErro.setVisible(false);
            loadConfig();
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
                actionEvent -> saveConfig()
        );
        btnCriarUsuario.setOnAction(
                actionEvent -> createUser()
        );
        btnFinalizar.setOnAction(
                actionEvent -> finishPremio()
        );
    }

    // Visibilidade da tela de histórico de prêmios
    private void goToHistorico(){
        UserPane.setVisible(false);
        PremioPane.setVisible(true);
        DashboardPane.setVisible(false);
        ConfigPane.setVisible(false);
    }

    // Salva o prêmio finalizado no banco
    private void finishPremio(){
        try {
            if (Empresa.getInstance().isPossuiPremio()) {
                popUpSucessoMensagem("Finalizado!", usuarioIdNome.get(usuarioIdArray[0]) + " venceu essa rodada. Parabéns!");
                EmpresaApp.finalizarPremio(usuarioIdArray[0]);
                premio.setText(null);

                qntPremiosTotal.setText(String.valueOf(EmpresaApp.totalPremios()));
                AuditoriaTest auditoria = new AuditoriaTest();
                auditoria.StartThread("Finalize prize");
            }

        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }

    // Chama o pop-up de criar usuário
    private void createUser() {
        try {
            Parent root;
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample/PopUpCriarFuncionarios/PopUpCriarFuncionario.fxml"));
            root = fxmlLoader.load();
            PopUpCriarFuncionarioController controller = fxmlLoader.getController();
            controller.tableTeste = usuariosTable;
            Stage dialog = new Stage();
            dialog.getIcons().add(new Image(Main.class.getResourceAsStream("/resources/img/w!.png")));
            dialog.setScene(new Scene(root));
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.show();

            AuditoriaTest auditoria = new AuditoriaTest();
            auditoria.StartThread("Open Pop-Up New User");

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Visibilidade e carregamento da tela de listagem de usuários da empresa
    private void goToUsers() {
        try {
            AuditoriaTest.getInstance().StartThread("Users");
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }

        UserPane.setVisible(true);
        PremioPane.setVisible(false);
        DashboardPane.setVisible(false);
        ConfigPane.setVisible(false);
        UsuarioDAO dao = new UsuarioDAO();
        ArrayList<Usuario> usuarios = dao.listar();
        usuariosTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        nomeCol.setCellFactory(nomeCol.getCellFactory());
        emailCol.setCellFactory(emailCol.getCellFactory());

        usuariosTable.setEditable(true);

        final ObservableList<Users> users =
                FXCollections.observableArrayList();

        for (Usuario usuario : usuarios) {
            users.add(new Users(usuario));
        }

        nomeCol.setCellValueFactory(new PropertyValueFactory<>("nome"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        addButtonToTable();

        usuariosTable.setItems(users);
    }

    // Salva as configurações da empresa
    private void saveConfig() {

        Empresa emp1 = Empresa.getInstance();
        PremioDAO premioDAO = new PremioDAO();

        emp1.setFraseMotivacional(fraseMotivacional.getText() == null ? "" : fraseMotivacional.getText());
        emp1.setPossuiPremio(!naoPossuiPremio.isSelected());
        premioErro.setVisible(EmpresaApp.isInvalid(naoPossuiPremio.isSelected(), premio.getText()));

        if (naoPossuiPremio.isSelected()) {
            emp1.setPremioId(null);
        } else if (!naoPossuiPremio.isSelected() && premio.getText().length() > 0) {
            emp1.setNomePremio(premio.getText());
            emp1.setPremioId(premioDAO.inserirPremio(premio.getText()));
            emp1.setPossuiPremio(true);
        }

        if (!EmpresaApp.isInvalid(naoPossuiPremio.isSelected(), premio.getText())) {
            EmpresaApp.saveConfigEmpresa(emp1);

            try {
                popUpSucessoMensagem("Configurações salvas!", "Os dados das configurações foram atualizados com sucesso!");
                AuditoriaTest.getInstance().StartThread("Save Settings");
            } catch (InterruptedException | IOException interruptedException) {
                interruptedException.printStackTrace();
            }
            voltarHomePopUpSucesso();
        }
    }

    // Carrega as informações da tela de configurações
    private void loadConfig() {
        Empresa emp = Empresa.getInstance();
        fraseMotivacional.setText(emp.getFraseMotivacional());
        naoPossuiPremio.setSelected(!emp.isPossuiPremio());
        premio.setText(emp.isPossuiPremio() ? emp.getNomePremio() : null);
    }

    // Carrega os botões da tabela de listagem de usuários
    private void addButtonToTable() {

        Callback<TableColumn<Users, Void>, TableCell<Users, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Users, Void> call(final TableColumn<Users, Void> param) {
                final TableCell<Users, Void> cell = new TableCell<>() {

                    private final Button btnEditar = new Button();
                    private final Button btnExcluir = new Button();

                    {
                        Image image;
                        image = new Image(getClass().getResourceAsStream("/resources/img/edit.png"));
                        btnEditar.setStyle("-fx-background-color: transparent; -fx-cursor: hand");
                        btnExcluir.setStyle("-fx-background-color: transparent; -fx-cursor: hand");

                        btnEditar.setGraphic(new ImageView(image));
                        image = new Image(getClass().getResourceAsStream("/resources/img/delete.png"));
                        btnExcluir.setGraphic(new ImageView(image));

                        btnEditar.setOnAction((ActionEvent event) -> {
                            Users data = getTableView().getItems().get(getIndex());
                            edit(data);

                        });

                        btnExcluir.setOnAction((ActionEvent event) -> {
                            funcionariosEmpresa = getTableView().getItems().get(getIndex());
                            try {
                                confimacaoExclusaoUsuario("Atenção!", "Deseja realmente excluir o usuário?");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            HBox pane = new HBox(btnEditar, btnExcluir);
                            setGraphic(pane);
                        }
                    }
                };
                cell.setStyle("-fx-background-color: #FFFFFF; -fx-border-color: #C7C7C7; -fx-padding: 0 0 0 125");
                return cell;
            }
        };

        editCol.setCellFactory(cellFactory);

    }

    // Chama o pop-up de edição de usuário
    public void edit(Users user) {
        Parent root = null;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample/PopUpCriarFuncionarios/PopUpCriarFuncionario.fxml"));
            root = fxmlLoader.load();
            PopUpCriarFuncionarioController controller = fxmlLoader.getController();
            Usuario userEdit = new Usuario();
            userEdit.setId(user.id.getValue().intValue());
            userEdit.setEmail(user.getEmail());
            userEdit.setNome(user.getNome());
            controller.setUser(userEdit);
            controller.tableTeste = usuariosTable;
            controller.initialize(null, null);

        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage dialog = new Stage();
        dialog.setScene(new Scene(root));
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.show();

        System.out.println("selectedData: " + user.id.getValue().intValue());
    }

    // Carrega a tabela de listagem de usuários
    public void loadUsuarios(TableView table) {
        table.getItems().clear();
        UsuarioDAO dao = new UsuarioDAO();
        ArrayList<Usuario> usuarios;

        if (pesquisarUsuario.getText() == null || pesquisarUsuario.getText().isEmpty()) {
            usuarios = dao.listar();
        } else {
            usuarios = dao.listarFiltro(pesquisarUsuario.getText());
        }

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        nomeCol.setCellFactory(nomeCol.getCellFactory());
        emailCol.setCellFactory(emailCol.getCellFactory());

        table.setEditable(false);

        final ObservableList<Users> users =
                FXCollections.observableArrayList();

        for (Usuario usuario : usuarios) {
            users.add(new Users(usuario));
        }

        nomeCol.setCellValueFactory(new PropertyValueFactory<>("nome"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        addButtonToTable();

        table.getItems().clear();
        table.setItems(users);
        table.refresh();

    }

    // Carrega o pop-up de confirmação de exclusão do usuário
    public void confimacaoExclusaoUsuario(String titulo, String mensagem) throws IOException {
        Parent root;
        FXMLLoader fxmlLoader = new FXMLLoader(EmpresaApp.class.getResource("/sample/PopUpDelete/PopUpDelete.fxml"));
        root = fxmlLoader.load();
        popUpDeleteController popUpController = fxmlLoader.getController();
        popUpController.controllerEmpresa = this;
        popUpController.titulo = titulo;
        popUpController.mensagem = mensagem;
        popUpController.initialize(null, null);
        Stage dialog = new Stage();
        dialog.getIcons().add(new Image(Main.class.getResourceAsStream("/resources/img/w!.png")));
        dialog.setScene(new Scene(root));
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.show();
    }

    // Carrega o pop-up de sucesso ao criar o usuário
    public static void popUpSucessoMensagem(String titulo, String mensagem) throws IOException {
        Parent root;
        FXMLLoader fxmlLoader = new FXMLLoader(DashboardEmpresaController.class.getResource("/sample/PopUpSucesso/PopUpSucesso.fxml"));
        root = fxmlLoader.load();
        popUpSucessoController popUpController = fxmlLoader.getController();
        popUpController.titulo = titulo;
        popUpController.mensagem = mensagem;
        popUpController.initialize(null, null);
        Stage dialog = new Stage();
        dialog.getIcons().add(new Image(Main.class.getResourceAsStream("/resources/img/w!.png")));
        dialog.setScene(new Scene(root));
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.show();
    }

    // Exclusão do usuário
    public void confirmarCancelarUsuario(boolean ok) throws InterruptedException {
        if (ok) {
            EmpresaApp.excluirUsuario(funcionariosEmpresa.id.getValue().intValue());
            EmpresaDAO empresaDAO = new EmpresaDAO();
            qntFuncionariosTotal.setText(String.valueOf(empresaDAO.listarFuncionarios().size()));
            loadUsuarios(usuariosTable);
        }
    }

    // Carrega as informações do dashboard principal da empresa
    public void homeCarregarDados() {
        setDateTime();
        String currentDate = String.valueOf(LocalDate.now());
        usuariosEmpresa = EmpresaApp.mapExercicioIdQuantidade();
        setRanking();
        HashMap<Integer, Integer> exercicioIdQntFeita = EmpresaApp.calcTotalExerciciosExEscolhido();

        EmpresaDAO empresaDAO = new EmpresaDAO();
        qntFuncionariosTotal.setText(String.valueOf(empresaDAO.listarFuncionarios().size()));
        qntExerciciosTotal.setText(String.valueOf(EmpresaApp.totalExerciciosTodosFuncionarios(usuariosEmpresa)));
        qntMinutosTotal.setText(EmpresaApp.convertToHours(EmpresaApp.totalMinutos()));
        qntPremiosTotal.setText(String.valueOf(EmpresaApp.totalPremios()));
        qntHorasDia.setText(String.valueOf(EmpresaApp.totalMinutos(currentDate)));
        qntExerciciosDia.setText(String.valueOf(EmpresaApp.totalExerciciosTodosFuncionarios(currentDate)));

        loadUsuarios(usuariosTable);
    }

    // Retorna para o dashboard
    public void voltarHomePopUpSucesso() {

        UserPane.setVisible(false);
        PremioPane.setVisible(false);
        DashboardPane.setVisible(true);
        ConfigPane.setVisible(false);
        premioErro.setVisible(false);
    }

    // Classe auxiliar para o controle da tabela de usuários da empresa
    public static class Users {
        private final SimpleStringProperty nome;
        private final SimpleStringProperty email;
        private final SimpleIntegerProperty id;

        public Users(Usuario user) {
            this.nome = new SimpleStringProperty(user.getNome());
            this.email = new SimpleStringProperty(user.getEmail());
            this.id = new SimpleIntegerProperty(user.getId());
        }

        public void setEmail(String email) {
            this.nome.set(email);
        }

        public String getEmail() {
            return email.get();
        }

        public void setNome(String nome) {
            this.nome.set(nome);
        }

        public String getNome() {
            return nome.get();
        }

        public void setId(Integer id) {
            this.id.set(id);
        }

        public int getId() {
            return id.get();
        }
    }
}
