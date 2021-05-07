package sample;

import DAO.acesso.UsuarioDAO;
import DAO.auditoria.AuditoriaTest;
import comuns.acesso.Empresa;
import comuns.acesso.Usuario;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
    private TableView usuariosTable;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        Empresa emp = Empresa.getInstance();
        nomeEmpresa.setText(emp.getNome());

        logout.setPickOnBounds(true);
        logout.setOnMouseClicked((MouseEvent e) -> {
            try {
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Login/login.fxml")));
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

            UsuarioDAO dao = new UsuarioDAO();
            ArrayList<Usuario> usuarios = dao.listar();
            ArrayList<Users> users = null;

            for(Usuario usuario : usuarios){
                users.add(new Users(usuario.getNome(), usuario.getEmail()));
            }

            nomeCol.setCellValueFactory(new PropertyValueFactory<Users, String>("nome"));
            emailCol.setCellValueFactory(new PropertyValueFactory<Users, String>("login"));

            usuariosTable.setItems((ObservableList) users);
            //usuariosTable.getColumns().addAll(nomeCol, emailCol)

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
                users.add(new Users(usuario.getNome(), usuario.getEmail()));
            }

            nomeCol.setCellValueFactory(new PropertyValueFactory<Users, String>("nome"));
            emailCol.setCellValueFactory(new PropertyValueFactory<Users, String>("email"));

            usuariosTable.setItems((ObservableList) users);


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
                            Parent root = FXMLLoader.load(getClass().getResource("/sample/PopUpCriarFuncionarios/PopUpCriarFuncionario.fxml"));

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
    public static class Users{
        private final SimpleStringProperty nome;
        private final SimpleStringProperty email;

        public Users(String nome, String email){
            this.nome = new SimpleStringProperty(nome);
            this.email = new SimpleStringProperty(email);
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
    }
}
