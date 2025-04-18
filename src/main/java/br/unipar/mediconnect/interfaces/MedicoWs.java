package br.unipar.mediconnect.interfaces;

import br.unipar.mediconnect.domain.Medico;
import br.unipar.mediconnect.dto.MedicoRequestInsertDTO;
import br.unipar.mediconnect.dto.MedicoRequestUpdateDto;
import br.unipar.mediconnect.dto.MedicoResponseGetDto;
import br.unipar.mediconnect.exceptions.BusinessException;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

import java.util.ArrayList;

@WebService
public interface MedicoWs {

    @WebMethod
    Medico insert(MedicoRequestInsertDTO dto) throws BusinessException;

    @WebMethod
    ArrayList<MedicoResponseGetDto> getAll() throws BusinessException;

    @WebMethod
    void update(MedicoRequestUpdateDto medico) throws BusinessException;

    @WebMethod
    void remove(int id) throws BusinessException;
}
