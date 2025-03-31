package br.unipar.mediconnect.domain;

import br.unipar.mediconnect.dto.ConsultaRequestInsertDto;

import java.time.LocalDateTime;

public class Consulta {
    private int id;
    private Paciente paciente;
    private Medico medico;
    private LocalDateTime dataHoraAgendamento;
    private boolean cancelada;

    public Consulta() {
    }

    public Consulta(ConsultaRequestInsertDto dto) {
        dataHoraAgendamento = dto.getDataHoraAgendamento();

        paciente = new Paciente();
        paciente.setId(dto.getPacienteId());

        medico = new Medico();
        medico.setId(dto.getMedicoId());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public LocalDateTime getDataHoraAgendamento() {
        return dataHoraAgendamento;
    }

    public void setDataHoraAgendamento(LocalDateTime dataHoraAgendamento) {
        this.dataHoraAgendamento = dataHoraAgendamento;
    }

    public boolean isCancelada() {
        return cancelada;
    }

    public void setCancelada(boolean cancelada) {
        this.cancelada = cancelada;
    }
}
