package sample.DashboardEmpresa;

import DAO.acesso.UsuarioDAO;
import DAO.auditoria.AuditoriaTest;
import comuns.acesso.Empresa;
import comuns.acesso.Usuario;
import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import sample.PopUpCriarFuncionarios.PopUpCriarFuncionarioController;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        loadUsuarios(usuariosTable);

        pesquisarUsuario.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {

                loadUsuarios(usuariosTable);
            }
        });


        Empresa emp = Empresa.getInstance();
        nomeEmpresa.setText(emp.getNome());

        logout.setPickOnBounds(true);
        logout.setOnMouseClicked((MouseEvent e) -> {
            try {
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/sample/Login/login.fxml")));
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
            UsuarioDAO dao = new UsuarioDAO();
            ArrayList<Usuario> usuarios = dao.listar();
            usuariosTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            nomeCol.setCellFactory(nomeCol.getCellFactory());
            emailCol.setCellFactory(emailCol.getCellFactory());

            usuariosTable.setEditable(true);

            final ObservableList<Users> users =
                    FXCollections.observableArrayList();

            for(Usuario usuario : usuarios){
                users.add(new Users(usuario));
            }

            nomeCol.setCellValueFactory(new PropertyValueFactory<Users, String>("nome"));
            emailCol.setCellValueFactory(new PropertyValueFactory<Users, String>("email"));
            addButtonToTable();

            usuariosTable.setItems((ObservableList) users);
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


            loadUsuarios(usuariosTable);
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
                            Parent root = FXMLLoader.load(getClass().getResource("/sample/PopUpImpressao/PopUpImpressao.fxml"));

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
                            Parent root = null;
                            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample/PopUpCriarFuncionarios/PopUpCriarFuncionario.fxml"));
                            root = (Parent) fxmlLoader.load();
                            PopUpCriarFuncionarioController controller = fxmlLoader.getController();
                            controller.tableTeste = usuariosTable;
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

    private void addButtonToTable() {

        Callback<TableColumn<Users, Void>, TableCell<Users, Void>> cellFactory = new Callback<TableColumn<Users, Void>, TableCell<Users, Void>>() {
            @Override
            public TableCell<Users, Void> call(final TableColumn<Users, Void> param) {
                final TableCell<Users, Void> cell = new TableCell<Users, Void>() {

                    private final Button btnEditar = new Button();
                    private final Button btnExcluir = new Button();

                    {
                        Image image = null;
                        image = new Image(getClass().getResourceAsStream("/resources/img/edit.png"));
                        btnEditar.setStyle("-fx-background-color: transparent");
                        btnExcluir.setStyle("-fx-background-color: transparent");

                        btnEditar.setGraphic(new ImageView(image));
                        image = new Image(getClass().getResourceAsStream("/resources/img/delete.png"));
                        btnExcluir.setGraphic(new ImageView(image));

                        btnEditar.setOnAction((ActionEvent event) -> {
                            Users data = getTableView().getItems().get(getIndex());
                            edit(data);

                        });

                        btnExcluir.setOnAction((ActionEvent event) -> {
                            Users data = getTableView().getItems().get(getIndex());

                            UsuarioDAO dao = new UsuarioDAO();
                            dao.excluir(data.id.getValue().intValue());
                            loadUsuarios(usuariosTable);
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
                return cell;
            }
        };

        editCol.setCellFactory(cellFactory);

    }

    public void edit(Users user){
        Parent root = null;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample/PopUpCriarFuncionarios/PopUpCriarFuncionario.fxml"));
            root = (Parent) fxmlLoader.load();
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

    public void loadUsuarios(TableView table){
        table.getItems().clear();
        UsuarioDAO dao = new UsuarioDAO();
        ArrayList<Usuario> usuarios = new ArrayList<>();

        if(pesquisarUsuario.getText() == null || pesquisarUsuario.getText().isEmpty()){
            usuarios = dao.listar();
        }else{
            usuarios = dao.listarFiltro(pesquisarUsuario.getText());
        }

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        nomeCol.setCellFactory(nomeCol.getCellFactory());
        emailCol.setCellFactory(emailCol.getCellFactory());

        table.setEditable(false);

        final ObservableList<Users> users =
                FXCollections.observableArrayList();

        for(Usuario usuario : usuarios){
            users.add(new Users(usuario));
        }

        nomeCol.setCellValueFactory(new PropertyValueFactory<Users, String>("nome"));
        emailCol.setCellValueFactory(new PropertyValueFactory<Users, String>("email"));
        addButtonToTable();

        table.getItems().clear();
        table.setItems(users);
        table.refresh();

    }

    public static class Users{
        private final SimpleStringProperty nome;
        private final SimpleStringProperty email;
        private final SimpleIntegerProperty id;

        public Users(Usuario user){
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
