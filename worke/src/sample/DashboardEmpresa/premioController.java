package sample.DashboardEmpresa;

import comuns.acesso.Premio;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class premioController {
    @FXML
    private Label nPremio;
    @FXML
    private Label dataPremio;
    @FXML
    private Label vencedor;
    @FXML
    private Label nomePremio;

    public void setData(Premio premio, String nomeVencedor){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        nomePremio.setText(premio.getDescricao());
        nPremio.setText("Prêmio " + simpleDateFormat.format(premio.getDataFinal()));
        vencedor.setText(nomeVencedor);
    }
}
