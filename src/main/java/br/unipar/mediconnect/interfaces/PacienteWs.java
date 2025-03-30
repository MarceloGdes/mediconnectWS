package br.unipar.mediconnect.interfaces;

import br.unipar.mediconnect.domain.Paciente;
import br.unipar.mediconnect.dto.PacienteRequestInsertDto;
import br.unipar.mediconnect.exceptions.BusinessException;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

@WebService
public interface PacienteWs {

    @WebMethod
    public Paciente insert (PacienteRequestInsertDto dto) throws BusinessException;
}
