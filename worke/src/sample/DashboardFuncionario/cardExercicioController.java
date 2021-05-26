package sample.DashboardFuncionario;

import comuns.acesso.Funcionario;
import comuns.conteudo.Exercicio;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class cardExercicioController {

    @FXML
    private ImageView imgEx;
    @FXML
    private Label nomeEx;
    @FXML
    private Label tempoEx;
    @FXML
    public ImageView clickEx;

    public void setExercicio(Exercicio exercicio, Funcionario funcionario){
        clickEx.setPickOnBounds(true);
        imgEx.setImage(new Image(getClass().getResource("/resources/img/" + exercicio.getImagem() + "White.png").toExternalForm()));
        nomeEx.setText(exercicio.getNome());
        tempoEx.setText(funcionario.getDuracaoExercicios() + " min");
    }
}
