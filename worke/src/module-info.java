module worke {
    requires javafx.fxml;
    requires javafx.controls;
    requires java.sql;
    requires javafx.graphics;
    requires TrayNotification;
    requires junit;

    exports sample;
    exports mask;
    exports test;


    opens sample to javafx.fxml;
    opens sample.Login to javafx.fxml;
    opens sample.CriarSenha to javafx.fxml;
    opens sample.DashboardEmpresa to javafx.fxml, javafx.base;
    opens sample.DashboardFuncionario to javafx.fxml;
    opens sample.PopUpCriarFuncionarios to javafx.fxml;
    opens sample.PopUpSucesso to javafx.fxml;
    opens sample.PopUpDelete to javafx.fxml;
}