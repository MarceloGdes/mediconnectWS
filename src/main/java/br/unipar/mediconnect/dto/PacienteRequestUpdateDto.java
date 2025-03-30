package br.unipar.mediconnect.dto;

import br.unipar.mediconnect.domain.EnderecoPaciente;
import br.unipar.mediconnect.domain.Paciente;

public class PacienteRequestUpdateDto {
    private int id;
    private String nome;
    private String telefone;
    private EnderecoPaciente endereco;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public EnderecoPaciente getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoPaciente endereco) {
        this.endereco = endereco;
    }
}
