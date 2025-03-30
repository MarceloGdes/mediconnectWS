package br.unipar.mediconnect.domain;

import br.unipar.mediconnect.dto.PacienteRequestInsertDto;

public class Paciente {
    private int id;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;
    private EnderecoPaciente endereco;
    private boolean stAtivo;

    public Paciente(PacienteRequestInsertDto dto) {
        nome = dto.getNome();
        cpf = dto.getCpf();
        email = dto.getEmail();
        telefone = dto.getTelefone();
        endereco = dto.getEndereco();
    }

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

    public boolean isStAtivo() {
        return stAtivo;
    }

    public void setStAtivo(boolean stAtivo) {
        this.stAtivo = stAtivo;
    }
}
