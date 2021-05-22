package comuns.acesso;

import comuns.basis.Entidade;

import java.util.Date;

public class Rotina extends Entidade {
    private Integer quantidadeExercicios;
    private Integer usuarioId;
    private Double duracaoExercicios;
    private Date dataCriacao;
    private Integer qntDisponivelExercicios;
    private final static Rotina INSTANCE = new Rotina();
    public static Rotina getInstance(){
        return INSTANCE;
    }

    public Rotina(){}

    public Integer getQuantidadeExercicios() {
        return quantidadeExercicios;
    }

    public void setQuantidadeExercicios(Integer quantidadeExercicios) {
        this.quantidadeExercicios = quantidadeExercicios;
    }

    public Double getDuracaoExercicios() {
        return duracaoExercicios;
    }

    public void setDuracaoExercicios(Double duracaoExercicios) {
        duracaoExercicios = duracaoExercicios;
    }

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Integer getQntDisponivelExercicios() {
        return qntDisponivelExercicios;
    }

    public void setQntDisponivelExercicios(Integer qntDisponivelExercicios) {
        this.qntDisponivelExercicios = qntDisponivelExercicios;
    }
}
