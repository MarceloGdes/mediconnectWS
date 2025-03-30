package br.unipar.mediconnect.services;

import br.unipar.mediconnect.domain.Paciente;
import br.unipar.mediconnect.exceptions.BusinessException;
import br.unipar.mediconnect.repositories.PacienteRepository;

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
}
