package br.unipar.mediconnect.dto;

import br.unipar.mediconnect.domain.EnderecoPaciente;

public class PacienteRequestInsertDto {

    private String nome;
    private String email;
    private String telefone;
    private String cpf;
    private EnderecoPaciente endereco;

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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public EnderecoPaciente getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoPaciente endereco) {
        this.endereco = endereco;
    }
}
