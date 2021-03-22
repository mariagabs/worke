package comuns.acesso;

import comuns.conteudo.Exercicio;

import java.util.List;

public class Funcionario extends Usuario implements IUsuario{

    public Funcionario(){
        tipo = TipoUsuario.FUNCIONARIO;
    }
    private TipoUsuario tipo;
    private List<Exercicio> exercicios;
    private String lembrete;
    private int ranking;
    private int qntTotalExercicios;
    private double qntHorasTotais;
    private double duracaoExercicios;
    private double intervaloExercicios;

    public List<Exercicio> getExercicios() {
        return exercicios;
    }

    public void setExercicios(List<Exercicio> exercicios) {
        this.exercicios = exercicios;
    }

    public String getLembrete() {
        return lembrete;
    }

    public void setLembrete(String lembrete) {
        this.lembrete = lembrete;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public int getQntTotalExercicios() {
        return qntTotalExercicios;
    }

    public void setQntTotalExercicios(int qntTotalExercicios) {
        this.qntTotalExercicios = qntTotalExercicios;
    }

    public double getQntHorasTotais() {
        return qntHorasTotais;
    }

    public void setQntHorasTotais(double qntHorasTotais) {
        this.qntHorasTotais = qntHorasTotais;
    }

    public double getDuracaoExercicios() {
        return duracaoExercicios;
    }

    public void setDuracaoExercicios(double duracaoExercicios) {
        this.duracaoExercicios = duracaoExercicios;
    }

    public double getIntervaloExercicios() {
        return intervaloExercicios;
    }

    public void setIntervaloExercicios(double intervaloExercicios) {
        this.intervaloExercicios = intervaloExercicios;
    }
}
