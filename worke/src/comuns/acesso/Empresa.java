package comuns.acesso;

public class Empresa extends Usuario implements IUsuario{

    public Empresa(){
        tipo = TipoUsuario.EMPRESA;
        setAdmEmpresa(true);
    }
    private TipoUsuario tipo;
    private String fraseMotivacional;
    private boolean possuiPremio;
    private String nomePremio;
    private Integer premioId;
    private int qntFuncionarios;
    private int qntPremios;
    private double totalHorasExercicios;
    private int qntTotalExercicios;
    private double qntHorasDiarias;
    private int qntExerciciosDiario;
    private final static Empresa INSTANCE = new Empresa();

    public static Empresa getInstance(){
        return INSTANCE;
    }

    public String getFraseMotivacional() {
        return fraseMotivacional;
    }

    public void setFraseMotivacional(String fraseMotivacional) {
        this.fraseMotivacional = fraseMotivacional;
    }

    public boolean isPossuiPremio() {
        return possuiPremio;
    }

    public void setPossuiPremio(boolean premio) {
        this.possuiPremio = premio;
    }

    public String getNomePremio() {
        return nomePremio;
    }

    public void setNomePremio(String nomePremio) {
        this.nomePremio = nomePremio;
    }

    public int getQntFuncionarios() {
        return qntFuncionarios;
    }

    public void setQntFuncionarios(int qntFuncionarios) {
        this.qntFuncionarios = qntFuncionarios;
    }

    public int getQntPremios() {
        return qntPremios;
    }

    public void setQntPremios(int qntPremios) {
        this.qntPremios = qntPremios;
    }

    public double getTotalHorasExercicios() {
        return totalHorasExercicios;
    }

    public void setTotalHorasExercicios(double totalHorasExercicios) {
        this.totalHorasExercicios = totalHorasExercicios;
    }

    public int getQntTotalExercicios() {
        return qntTotalExercicios;
    }

    public void setQntTotalExercicios(int qntTotalExercicios) {
        this.qntTotalExercicios = qntTotalExercicios;
    }

    public double getQntHorasDiarias() {
        return qntHorasDiarias;
    }

    public void setQntHorasDiarias(double qntHorasDiarias) {
        this.qntHorasDiarias = qntHorasDiarias;
    }

    public int getQntExerciciosDiario() {
        return qntExerciciosDiario;
    }

    public void setQntExerciciosDiario(int qntExerciciosDiario) {
        this.qntExerciciosDiario = qntExerciciosDiario;
    }

    public Integer getPremioId() {
        return premioId;
    }

    public void setPremioId(Integer premioId) {
        this.premioId = premioId;
    }
}
