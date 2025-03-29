package br.unipar.mediconnect;

import br.unipar.mediconnect.domain.Medico;
import br.unipar.mediconnect.dto.MedicoRequestInsertDTO;
import br.unipar.mediconnect.dto.MedicoRequestUpdateDto;
import br.unipar.mediconnect.dto.MedicoResponseGetDto;
import br.unipar.mediconnect.exceptions.BusinessException;
import br.unipar.mediconnect.interfaces.MedicoWs;
import br.unipar.mediconnect.services.MedicoService;
import jakarta.jws.WebService;

import java.util.ArrayList;

@WebService(endpointInterface = "br.unipar.mediconnect.interfaces.MedicoWs")
public class MedicoWsImp implements MedicoWs {
    private final MedicoService medicoService = new MedicoService();
    @Override
    public Medico insert(MedicoRequestInsertDTO dto) throws BusinessException {
        Medico medico = new Medico(dto);

        return medicoService.insert(medico);
    }

    @Override
    public ArrayList<MedicoResponseGetDto> getAll() throws BusinessException {
        return medicoService.getAll();
    }

    @Override
    public void update(MedicoRequestUpdateDto dto) throws BusinessException {
        var medico = new Medico(dto);
        medicoService.update(medico);
    }
}
