package br.unipar.mediconnect.interfaces;

import br.unipar.mediconnect.domain.Medico;
import br.unipar.mediconnect.dto.MedicoRequestInsertDTO;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

@WebService
public interface MedicoWs {

    @WebMethod
    Medico insert(MedicoRequestInsertDTO dto);
}
