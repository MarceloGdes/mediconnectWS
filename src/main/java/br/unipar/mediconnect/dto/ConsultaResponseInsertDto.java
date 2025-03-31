package br.unipar.mediconnect.dto;

import br.unipar.mediconnect.adapters.LocalDateTimeAdapter;
import br.unipar.mediconnect.domain.Consulta;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.time.LocalDateTime;

public class ConsultaResponseInsertDto {
    private int id;
    private PacienteResponseGetDto paciente;
    private MedicoResponseGetDto medico;
    private LocalDateTime dataHoraAgendamento;

    public ConsultaResponseInsertDto(Consulta consulta) {
        id = consulta.getId();
        paciente = new PacienteResponseGetDto(consulta.getPaciente());
        medico = new MedicoResponseGetDto(consulta.getMedico());
        dataHoraAgendamento = consulta.getDataHoraAgendamento();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PacienteResponseGetDto getPaciente() {
        return paciente;
    }

    public void setPaciente(PacienteResponseGetDto paciente) {
        this.paciente = paciente;
    }

    public MedicoResponseGetDto getMedico() {
        return medico;
    }

    public void setMedico(MedicoResponseGetDto medico) {
        this.medico = medico;
    }

    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    public LocalDateTime getDataHoraAgendamento() {
        return dataHoraAgendamento;
    }

    public void setDataHoraAgendamento(LocalDateTime dataHoraAgendamento) {
        this.dataHoraAgendamento = dataHoraAgendamento;
    }
}
