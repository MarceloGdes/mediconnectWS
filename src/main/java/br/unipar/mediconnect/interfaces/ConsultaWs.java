package br.unipar.mediconnect.interfaces;

import br.unipar.mediconnect.dto.ConsultaRequestCancelDto;
import br.unipar.mediconnect.dto.ConsultaRequestInsertDto;
import br.unipar.mediconnect.dto.ConsultaResponseCancelDto;
import br.unipar.mediconnect.dto.ConsultaResponseInsertDto;
import br.unipar.mediconnect.exceptions.BusinessException;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

@WebService
public interface ConsultaWs {

    @WebMethod
    ConsultaResponseInsertDto agendar(ConsultaRequestInsertDto consulta) throws BusinessException;

    @WebMethod
    ConsultaResponseCancelDto cancelar(ConsultaRequestCancelDto dto) throws BusinessException;
}
