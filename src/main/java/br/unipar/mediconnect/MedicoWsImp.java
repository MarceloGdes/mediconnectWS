package br.unipar.mediconnect;

import br.unipar.mediconnect.domain.Medico;
import br.unipar.mediconnect.dto.MedicoRequestInsertDTO;
import br.unipar.mediconnect.interfaces.MedicoWs;
import jakarta.jws.WebService;

@WebService(endpointInterface = "br.unipar.mediconnect.interfaces.MedicoWs")
public class MedicoWsImp implements MedicoWs {

    @Override
    public Medico insert(MedicoRequestInsertDTO dto) {
        return null;
    }
}
