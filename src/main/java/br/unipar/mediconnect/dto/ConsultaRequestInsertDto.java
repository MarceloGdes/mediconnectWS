package br.unipar.mediconnect.dto;

import br.unipar.mediconnect.adapters.LocalDateTimeAdapter;
import br.unipar.mediconnect.domain.Medico;
import br.unipar.mediconnect.domain.Paciente;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.time.LocalDateTime;

public class ConsultaRequestInsertDto {
    private int pacienteId;
    private int medicoId;


    private LocalDateTime dataHoraAgendamento;

    public int getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(int pacienteId) {
        this.pacienteId = pacienteId;
    }

    public int getMedicoId() {
        return medicoId;
    }

    public void setMedicoId(int medicoId) {
        this.medicoId = medicoId;
    }

    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    public LocalDateTime getDataHoraAgendamento() {
        return dataHoraAgendamento;
    }

    public void setDataHoraAgendamento(LocalDateTime dataHoraAgendamento) {
        this.dataHoraAgendamento = dataHoraAgendamento;
    }
}
