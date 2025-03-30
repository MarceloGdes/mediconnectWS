package br.unipar.mediconnect;

import br.unipar.mediconnect.domain.Paciente;
import br.unipar.mediconnect.dto.PacienteRequestInsertDto;
import br.unipar.mediconnect.exceptions.BusinessException;
import br.unipar.mediconnect.interfaces.PacienteWs;
import br.unipar.mediconnect.services.PacienteService;
import jakarta.jws.WebService;

@WebService(endpointInterface = "br.unipar.mediconnect.interfaces.PacienteWs")
public class PacienteWsImp implements PacienteWs {
    private PacienteService service = new PacienteService();
    @Override
    public Paciente insert(PacienteRequestInsertDto dto) throws BusinessException {
        var paciente = new Paciente(dto);
        return service.insert(paciente);
    }
}
