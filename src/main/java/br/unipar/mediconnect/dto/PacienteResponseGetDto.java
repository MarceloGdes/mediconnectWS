package br.unipar.mediconnect.dto;

import br.unipar.mediconnect.domain.Paciente;

public class PacienteResponseGetDto {
    private String nome;
    private String email;
    private String cpf;

    public PacienteResponseGetDto(Paciente paciente) {
        nome = paciente.getNome();
        email = paciente.getEmail();
        cpf = paciente.getEmail();
    }

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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
