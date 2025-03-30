package br.unipar.mediconnect.interfaces;

import br.unipar.mediconnect.domain.Paciente;
import br.unipar.mediconnect.dto.PacienteRequestInsertDto;
import br.unipar.mediconnect.dto.PacienteRequestUpdateDto;
import br.unipar.mediconnect.dto.PacienteResponseGetDto;
import br.unipar.mediconnect.exceptions.BusinessException;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

import java.util.ArrayList;

@WebService
public interface PacienteWs {

    @WebMethod
    public Paciente insert (PacienteRequestInsertDto dto) throws BusinessException;

    @WebMethod
    public ArrayList<PacienteResponseGetDto> getAll() throws BusinessException;

    @WebMethod
    public void update(PacienteRequestUpdateDto dto) throws BusinessException;
}
