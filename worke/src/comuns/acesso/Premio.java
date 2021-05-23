package comuns.acesso;

import comuns.basis.Entidade;

import java.util.Date;

public class Premio extends Entidade{
    private String descricao;
    private Boolean finalizado;
    private Integer usuarioId;
    private Date dataFinal;
    private final static Premio INSTANCE = new Premio();
    public static Premio getInstance(){
        return INSTANCE;
    }

    public Premio(){}

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Boolean getFinalizado() {
        return finalizado;
    }

    public void setFinalizado(Boolean finalizado) {
        this.finalizado = finalizado;
    }

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }
}
