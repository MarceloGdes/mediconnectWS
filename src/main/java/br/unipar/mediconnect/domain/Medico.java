package br.unipar.mediconnect.domain;

import br.unipar.mediconnect.dto.MedicoRequestInsertDTO;
import br.unipar.mediconnect.dto.MedicoRequestUpdateDto;

public class Medico {
    private int id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;
    private Especialidade especialidade;
    private Endereco endereco;

    public Medico(MedicoRequestInsertDTO dto) {
        nome = dto.getNome();
        email = dto.getEmail();
        telefone = dto.getTelefone();
        crm = dto.getCrm();
        especialidade = new Especialidade();
        especialidade.setId(dto.getEspecialidadeId());
        endereco = dto.getEndereco();
    }

    public Medico(MedicoRequestUpdateDto dto) {
        id = dto.getId();
        nome = dto.getNome();
        telefone = dto.getTelefone();
        endereco = dto.getEndereco();
    }


    public Medico() {
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

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(Especialidade especialidade) {
        this.especialidade = especialidade;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
