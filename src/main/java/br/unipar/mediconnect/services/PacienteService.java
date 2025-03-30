package br.unipar.mediconnect.services;

import br.unipar.mediconnect.domain.Paciente;
import br.unipar.mediconnect.dto.PacienteResponseGetDto;
import br.unipar.mediconnect.exceptions.BusinessException;
import br.unipar.mediconnect.repositories.PacienteRepository;

import java.util.ArrayList;

public class PacienteService {

    private PacienteRepository repository;

    public PacienteService() {
        repository = new PacienteRepository();
    }

    public Paciente insert(Paciente paciente) throws BusinessException {
        validarPacienteInsert(paciente);

        try {
            return repository.insert(paciente);
        }catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException("Erro ao cadastrar o paciente. Entre em contato com o suporte.");
        }
    }

    public ArrayList<PacienteResponseGetDto> getAll() throws BusinessException {
        var pacientesResponse = new ArrayList<PacienteResponseGetDto>();

        try {
            var pacientes = repository.selectAll();

            pacientes.forEach(paciente -> {
                pacientesResponse.add(new PacienteResponseGetDto(paciente));
            });

            return pacientesResponse;

        }catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException("Ocorreu um erro ao buscar os pacientes. Entre em contato com o suporte");
        }

    }

    public void update(Paciente paciente) throws BusinessException {
        validarPacienteUpdate(paciente);

        try {
            int linhasAtualizadas = repository.update(paciente);

            if(linhasAtualizadas < 1)
                throw new BusinessException("Erro atualizar o paciente. Entre em contato com o suporte.");

        }catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException("Erro atualizar o paciente. Entre em contato com o suporte.");
        }
    }

    public void setStAtivoToFalse(int id) throws BusinessException {
        var paciente = findById(id);
        if(!paciente.isStAtivo()) throw new BusinessException("Paciente já inativado/excluido do sistema.");

        try {
            int linhasAlteradas = repository.updateStatus(id);

            if(linhasAlteradas < 1) throw new BusinessException("Ocorreu um erro ao excluir o paciente. Entre em contato com o suporte.");

        }catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException("Ocorreu um erro ao excluir o paciente. Entre em contato com o suporte.");
        }
    }

    public Paciente findById(int id) throws BusinessException {
        try {
            var paciente = repository.findById(id);

            if(paciente != null) return paciente;

            throw new BusinessException("Paciente não encontrado. Verifique o id informado.");

        }catch (BusinessException e) {
            throw e;

        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException("Erro ao buscar o paciente. Entre em contato com o suporte.");
        }
    }

    private void validarPacienteInsert(Paciente paciente) throws BusinessException {
        //Todo: colocar validação de valor nulo antes do isblank.
        if(paciente.getNome() ==  null || paciente.getNome().isBlank())
            throw new BusinessException("O nome do paciente é obrigatório.");

        if (paciente.getEmail() == null || paciente.getEmail().isBlank())
            throw new BusinessException("O e-mail é obrigatório.");

        if (paciente.getTelefone() == null || paciente.getTelefone().isBlank())
            throw new BusinessException("O telefone é obrigatório.");

        if (paciente.getCpf() == null || paciente.getCpf().isBlank())
            throw new BusinessException("O CPF é obrigatório.");

        if (paciente.getEndereco().getLogradouro() == null || paciente.getEndereco().getLogradouro().isBlank())
            throw new BusinessException("O Logradouro é obrigatório.");

        if (paciente.getEndereco().getBairro() == null || paciente.getEndereco().getBairro().isBlank())
            throw new BusinessException("O Bairro é obrigatório.");

        if (paciente.getEndereco().getCep() == null || paciente.getEndereco().getCep().isBlank())
            throw new BusinessException("O CEP é obrigatório.");

        if (paciente.getEndereco().getCidade() == null || paciente.getEndereco().getCidade().isBlank())
            throw new BusinessException("a Cidade é obrigatória.");

        if (paciente.getEndereco().getUf() == null || paciente.getEndereco().getUf().isBlank())
            throw new BusinessException("O UF é obrigatório.");

    }

    private void validarPacienteUpdate(Paciente paciente) throws BusinessException {
        findById(paciente.getId()); //Verifica se o paciente existe no banco

        if(paciente.getNome() ==  null || paciente.getNome().isBlank())
            throw new BusinessException("O nome do paciente é obrigatório.");

        if (paciente.getTelefone() == null || paciente.getTelefone().isBlank())
            throw new BusinessException("O telefone é obrigatório.");

        if (paciente.getEndereco().getLogradouro() == null || paciente.getEndereco().getLogradouro().isBlank())
            throw new BusinessException("O Logradouro é obrigatório.");

        if (paciente.getEndereco().getBairro() == null || paciente.getEndereco().getBairro().isBlank())
            throw new BusinessException("O Bairro é obrigatório.");

        if (paciente.getEndereco().getCep() == null || paciente.getEndereco().getCep().isBlank())
            throw new BusinessException("O CEP é obrigatório.");

        if (paciente.getEndereco().getCidade() == null || paciente.getEndereco().getCidade().isBlank())
            throw new BusinessException("a Cidade é obrigatória.");

        if (paciente.getEndereco().getUf() == null || paciente.getEndereco().getUf().isBlank())
            throw new BusinessException("O UF é obrigatório.");
    }
}
