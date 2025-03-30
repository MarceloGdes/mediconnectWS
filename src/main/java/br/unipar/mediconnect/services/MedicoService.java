package br.unipar.mediconnect.services;

import br.unipar.mediconnect.domain.Medico;
import br.unipar.mediconnect.dto.MedicoResponseGetDto;
import br.unipar.mediconnect.exceptions.BusinessException;
import br.unipar.mediconnect.repositories.MedicoRepository;

import java.util.ArrayList;

public class MedicoService {
    private MedicoRepository repository;
    private EspecialideService especialideService;
    public MedicoService() {
        repository = new MedicoRepository();
    }

    public Medico insert(Medico medico) throws BusinessException {
        validarMedicoInsert(medico);

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

    public ArrayList<MedicoResponseGetDto> getAll() throws BusinessException {
        especialideService = new EspecialideService();
        var medicosResponse = new ArrayList<MedicoResponseGetDto>();

        try {
            var medicos = repository.selectAll();

            for (Medico medico : medicos) {
                var descEspecialidade = especialideService.findById(medico.getEspecialidade().getId()).getDescricao();
                medico.getEspecialidade().setDescricao(descEspecialidade);

                medicosResponse.add(new MedicoResponseGetDto(medico));
            }

            return medicosResponse;

        }catch (Exception e){
            e.printStackTrace();
            throw new BusinessException("Um erro ocorreu ao buscar os médicos. Entre em contato com o suporte.");
        }
    }

    public void update(Medico medico) throws BusinessException {
        validarMedicoUpdate(medico);

        try {
            int linhasAtualizadas = repository.update(medico);

            if(linhasAtualizadas < 1)
                throw new BusinessException("Erro atualizar o médico. Entre em contato com o suporte.");

        }catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException("Erro atualizar o médico. Entre em contato com o suporte.");
        }
    }

    public void setStAtivoToFalse(int id) throws BusinessException {
        var medico = findById(id); // validar se o médico existe.

        if(!medico.isStAtivo()) throw new BusinessException("Médico já inativado/excluido do sistema");

        try {
            var linhasAlteradas = repository.updateStatus(id, false);

            if(linhasAlteradas < 1) throw new BusinessException("Ocorreu um erro ao excluir o médico. Solicite apoio ao suporte.");
        }catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException("Ocorreu um erro ao excluir o médico. Solicite apoio ao suporte.");
        }
    }

    public Medico findById(int id) throws BusinessException {
        try {
            var medico = repository.findById(id);

            if(medico != null) return medico;

            throw new BusinessException("Medico não encontrado. Verifique o id informado.");

        }catch (BusinessException e) {
            throw e;

        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException("Erro ao buscar o médico. Entre em contato com o suporte.");
        }
    }

    private void validarMedicoInsert(Medico medico) throws BusinessException {
        if(medico.getNome() == null || medico.getNome().isBlank())
            throw new BusinessException("O nome do médico é obrigatório.");

        if (medico.getEmail() == null || medico.getEmail().isBlank())
            throw new BusinessException("O e-mail é obrigatório.");

        if (medico.getTelefone() == null || medico.getTelefone().isBlank())
            throw new BusinessException("O telefone é obrigatório.");

        if (medico.getCrm() == null || medico.getCrm().isBlank())
            throw new BusinessException("O CRM é obrigatório.");

        if (medico.getEspecialidade().getId() <= 0)
            throw new BusinessException("A especialidade é obrigatória.");

        if (medico.getEndereco().getLogradouro() == null || medico.getEndereco().getLogradouro().isBlank())
            throw new BusinessException("O Logradouro é obrigatório.");

        if (medico.getEndereco().getBairro() == null || medico.getEndereco().getBairro().isBlank())
            throw new BusinessException("O Bairro é obrigatório.");

    }

    private void validarMedicoUpdate(Medico medico) throws BusinessException {

        findById(medico.getId()); //método verifica se o registro existe no banco. Caso não exista é lançada uma excesão.

        if(medico.getNome() == null || medico.getNome().isBlank())
            throw new BusinessException("O nome do médico é obrigatório.");

        if (medico.getTelefone() == null || medico.getTelefone().isBlank())
            throw new BusinessException("O telefone é obrigatório.");

        if (medico.getEndereco().getLogradouro() == null || medico.getEndereco().getLogradouro().isBlank())
            throw new BusinessException("O Logradouro é obrigatório.");

        if (medico.getEndereco().getBairro() == null || medico.getEndereco().getBairro().isBlank())
            throw new BusinessException("O Bairro é obrigatório.");
    }
}
