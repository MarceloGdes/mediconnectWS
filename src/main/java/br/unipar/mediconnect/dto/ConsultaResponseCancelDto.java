package br.unipar.mediconnect.dto;

import br.unipar.mediconnect.adapters.LocalDateTimeAdapter;
import br.unipar.mediconnect.domain.HistoricoCancelamentoConsulta;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.time.LocalDateTime;

public class ConsultaResponseCancelDto {
    private LocalDateTime dataHoraAgendamento;
    private String motivoCancelamento;

    public ConsultaResponseCancelDto(HistoricoCancelamentoConsulta histCancConsulta) {
        dataHoraAgendamento = histCancConsulta.getConsulta().getDataHoraAgendamento();
        motivoCancelamento = histCancConsulta.getMotivoCancelamento().getDescricao();
    }

    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    public LocalDateTime getDataHoraAgendamento() {
        return dataHoraAgendamento;
    }

    public void setDataHoraAgendamento(LocalDateTime dataHoraAgendamento) {
        this.dataHoraAgendamento = dataHoraAgendamento;
    }

    public String getMotivoCancelamento() {
        return motivoCancelamento;
    }

    public void setMotivoCancelamento(String motivoCancelamento) {
        this.motivoCancelamento = motivoCancelamento;
    }
}
