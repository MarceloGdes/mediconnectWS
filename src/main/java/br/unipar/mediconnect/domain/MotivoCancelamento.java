package br.unipar.mediconnect.domain;

public class MotivoCancelamento {
    private int id;
    private String descricao;

    public MotivoCancelamento() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
