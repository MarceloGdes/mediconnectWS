package br.unipar.mediconnect.dto;

public class ConsultaRequestCancelDto {
    private int consultaId;
    private int motivoId;

    public int getConsultaId() {
        return consultaId;
    }

    public void setConsultaId(int consultaId) {
        this.consultaId = consultaId;
    }

    public int getMotivoId() {
        return motivoId;
    }

    public void setMotivoId(int motivoId) {
        this.motivoId = motivoId;
    }
}
