package br.unipar.mediconnect;

import br.unipar.mediconnect.domain.Medico;
import br.unipar.mediconnect.dto.MedicoRequestInsertDTO;
import br.unipar.mediconnect.exceptions.BusinessException;
import br.unipar.mediconnect.interfaces.MedicoWs;
import br.unipar.mediconnect.services.MedicoService;
import jakarta.jws.WebService;

@WebService(endpointInterface = "br.unipar.mediconnect.interfaces.MedicoWs")
public class MedicoWsImp implements MedicoWs {
    private final MedicoService medicoService = new MedicoService();
    @Override
    public Medico insert(MedicoRequestInsertDTO dto) throws BusinessException {
        Medico medico = new Medico(dto);

        return medicoService.insert(medico);
    }
}
