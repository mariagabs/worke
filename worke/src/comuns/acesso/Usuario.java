package comuns.acesso;

import comuns.basis.Entidade;
import comuns.conteudo.Exercicio;

import java.util.List;

public class Usuario extends Entidade implements IUsuario{
    private String nome;
    private String email;
    private String senha;
    private boolean admEmpresa;


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isAdmEmpresa() {
        return admEmpresa;
    }

    public void setAdmEmpresa(boolean admEmpresa) {
        this.admEmpresa = admEmpresa;
    }


}
