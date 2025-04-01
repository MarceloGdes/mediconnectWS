package br.unipar.mediconnect;

import br.unipar.mediconnect.domain.Consulta;
import br.unipar.mediconnect.domain.HistoricoCancelamentoConsulta;
import br.unipar.mediconnect.dto.ConsultaRequestCancelDto;
import br.unipar.mediconnect.dto.ConsultaRequestInsertDto;
import br.unipar.mediconnect.dto.ConsultaResponseCancelDto;
import br.unipar.mediconnect.dto.ConsultaResponseInsertDto;
import br.unipar.mediconnect.exceptions.BusinessException;
import br.unipar.mediconnect.interfaces.ConsultaWs;
import br.unipar.mediconnect.services.ConsultaService;
import jakarta.jws.WebService;

@WebService(endpointInterface = "br.unipar.mediconnect.interfaces.ConsultaWs")
public class ConsultaWsImp implements ConsultaWs {

    private ConsultaService consultaService = new ConsultaService();
    @Override
    public ConsultaResponseInsertDto agendar(ConsultaRequestInsertDto dto) throws BusinessException {
       var consulta = new Consulta(dto);

       return consultaService.agendar(consulta);
    }

    @Override
    public ConsultaResponseCancelDto cancelar(ConsultaRequestCancelDto dto) throws BusinessException {
        var histCancelamento = new HistoricoCancelamentoConsulta(dto);

        return consultaService.cancelarConsulta(histCancelamento);
    }
}
