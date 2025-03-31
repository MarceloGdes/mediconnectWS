package br.unipar.mediconnect.dto;

import br.unipar.mediconnect.domain.Medico;

public class MedicoResponseGetDto {
    private String nome;
    private String email;
    private String crm;
    private String especialidade;

    public MedicoResponseGetDto() {
    }

    public MedicoResponseGetDto(Medico medico) {
        nome = medico.getNome();
        email = medico.getEmail();
        crm = medico.getCrm();
        especialidade = medico.getEspecialidade().getDescricao();
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


    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }
}
