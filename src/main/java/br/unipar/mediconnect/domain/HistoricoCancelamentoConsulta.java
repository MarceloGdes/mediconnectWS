package br.unipar.mediconnect.domain;

import br.unipar.mediconnect.dto.ConsultaRequestCancelDto;

public class HistoricoCancelamentoConsulta {
    private int id;
    private Consulta consulta;
    private MotivoCancelamento motivoCancelamento;

    public HistoricoCancelamentoConsulta() {
    }

    public HistoricoCancelamentoConsulta(ConsultaRequestCancelDto dto) {
        consulta = new Consulta();
        consulta.setId(dto.getConsultaId());

        motivoCancelamento = new MotivoCancelamento();
        motivoCancelamento.setId(dto.getMotivoId());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }

    public MotivoCancelamento getMotivoCancelamento() {
        return motivoCancelamento;
    }

    public void setMotivoCancelamento(MotivoCancelamento motivoCancelamento) {
        this.motivoCancelamento = motivoCancelamento;
    }
}
