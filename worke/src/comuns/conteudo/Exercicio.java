package comuns.conteudo;

import comuns.basis.Entidade;

import java.util.List;

public class Exercicio extends Entidade {
    private String nome;
    private String descricao;
    private List<String> instrucoes;
    private String imagem;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<String> getInstrucoes() {
        return instrucoes;
    }

    public void setInstrucoes(List<String> instrucoes) {
        this.instrucoes = instrucoes;
    }


    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
}
