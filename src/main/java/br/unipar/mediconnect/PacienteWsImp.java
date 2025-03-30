package br.unipar.mediconnect;

import br.unipar.mediconnect.domain.Paciente;
import br.unipar.mediconnect.dto.PacienteRequestInsertDto;
import br.unipar.mediconnect.dto.PacienteRequestUpdateDto;
import br.unipar.mediconnect.dto.PacienteResponseGetDto;
import br.unipar.mediconnect.exceptions.BusinessException;
import br.unipar.mediconnect.interfaces.PacienteWs;
import br.unipar.mediconnect.services.PacienteService;
import jakarta.jws.WebService;

import java.util.ArrayList;

@WebService(endpointInterface = "br.unipar.mediconnect.interfaces.PacienteWs")
public class PacienteWsImp implements PacienteWs {
    private final PacienteService service = new PacienteService();
    @Override
    public Paciente insert(PacienteRequestInsertDto dto) throws BusinessException {
        var paciente = new Paciente(dto);
        return service.insert(paciente);
    }
    @Override
    public ArrayList<PacienteResponseGetDto> getAll() throws BusinessException {
        return service.getAll();
    }

    @Override
    public void update(PacienteRequestUpdateDto dto) throws BusinessException {
        var paciente = new Paciente(dto);
        service.update(paciente);
    }

    @Override
    public void remove(int id) throws BusinessException {
        service.setStAtivoToFalse(id);
    }
}
