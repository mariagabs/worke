package comuns.acesso;

import comuns.conteudo.Exercicio;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class Funcionario extends Usuario implements IUsuario{

    public Funcionario(){
        tipo = TipoUsuario.FUNCIONARIO;
        setAdmEmpresa(false);
    }
    private TipoUsuario tipo;
    private List<Exercicio> exercicios = new ArrayList<Exercicio>();
    private String lembrete;
    private int ranking;
    private int qntTotalExercicios;
    private double qntHorasTotais;
    private int duracaoExercicios;
    private Time intervaloExercicios;
    private final static Funcionario INSTANCE = new Funcionario();
    private Funcionario funcionario;
    private String horaInicio;
    private String horaTermino;

    public static Funcionario getInstance() {
        return INSTANCE;
    }

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

    public int getDuracaoExercicios() {
        return duracaoExercicios;
    }

    public void setDuracaoExercicios(int duracaoExercicios) {
        this.duracaoExercicios = duracaoExercicios;
    }

    public Time getIntervaloExercicios() {
        return intervaloExercicios;
    }

    public void setIntervaloExercicios(Time intervaloExercicios) {
        this.intervaloExercicios = intervaloExercicios;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraTermino() {
        return horaTermino;
    }

    public void setHoraTermino(String horaTermino) {
        this.horaTermino = horaTermino;
    }
}
