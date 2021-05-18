package comuns.acesso;

import comuns.basis.Entidade;

public class Premio extends Entidade{
    private String descricao;
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
}