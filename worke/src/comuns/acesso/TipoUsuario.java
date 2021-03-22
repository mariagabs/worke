package comuns.acesso;

public enum TipoUsuario {
    FUNCIONARIO("Funcionario"),
    EMPRESA("Empresa");

    private String descricao;

    TipoUsuario(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
