package br.unipar.mediconnect.services;

import br.unipar.mediconnect.domain.Medico;
import br.unipar.mediconnect.exceptions.BusinessException;
import br.unipar.mediconnect.repositories.MedicoRepository;

public class MedicoService {
    private MedicoRepository repository;
    private EspecialideService especialideService;
    public MedicoService() {
        repository = new MedicoRepository();
    }

    public Medico insert(Medico medico) throws BusinessException {
        validarMedico(medico);

        especialideService = new EspecialideService();
        var especialidade = especialideService.findById(medico.getEspecialidade().getId());
        medico.getEspecialidade().setDescricao(especialidade.getDescricao());

        try {
            return repository.insert(medico);

        }catch (Exception e){
            e.printStackTrace();
            throw new BusinessException("Erro ao inserir médico. Entre em contato com o suporte.");
        }
    }

    private void validarMedico(Medico medico) throws BusinessException {
        if(medico.getNome().isBlank()) throw new BusinessException("O nome do médico é obrigatório.");

        if (medico.getEmail().isBlank()) throw new BusinessException("O e-mail é obrigatório.");

        if (medico.getTelefone().isBlank()) throw new BusinessException("O telefone é obrigatório.");

        if (medico.getCrm().isBlank()) throw new BusinessException("O CRM é obrigatório.");

        if (medico.getEspecialidade().getId() <= 0) throw new BusinessException("A especialidade é obrigatória.");

        if (medico.getEndereco().getLogradouro().isBlank()) throw new BusinessException("O Logradouro é obrigatório.");

        if (medico.getEndereco().getBairro().isBlank()) throw new BusinessException("O Bairro é obrigatório.");

    }
}
