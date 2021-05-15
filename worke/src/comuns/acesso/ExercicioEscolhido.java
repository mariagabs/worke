package comuns.acesso;

import comuns.basis.Entidade;

import java.util.Date;

public class ExercicioEscolhido extends Entidade {

    private Integer exercicioId;
    private Integer rotinaId;
    private Integer duracao;
    private Date dataExecucao;
    private final static ExercicioEscolhido INSTANCE = new ExercicioEscolhido();
    public static ExercicioEscolhido getInstance(){
        return INSTANCE;
    }

    public ExercicioEscolhido(){}

    public Integer getExercicioId() {
        return exercicioId;
    }

    public void setExercicioId(Integer exercicioId) {
        this.exercicioId = exercicioId;
    }

    public Integer getRotinaId() {
        return rotinaId;
    }

    public void setRotinaId(Integer rotinaId) {
        this.rotinaId = rotinaId;
    }

    public Integer getDuracao() {
        return duracao;
    }

    public void setDuracao(Integer duracao) {
        this.duracao = duracao;
    }

    public Date getDataExecucao() {
        return dataExecucao;
    }

    public void setDataExecucao(Date dataExecucao) {
        this.dataExecucao = dataExecucao;
    }
}
