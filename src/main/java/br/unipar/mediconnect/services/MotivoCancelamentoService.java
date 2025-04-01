package br.unipar.mediconnect.services;


import br.unipar.mediconnect.domain.MotivoCancelamento;
import br.unipar.mediconnect.exceptions.BusinessException;
import br.unipar.mediconnect.repositories.MotivoCancelamentoRepository;

public class MotivoCancelamentoService {

    private final MotivoCancelamentoRepository repository;

    public MotivoCancelamentoService() {
        repository = new MotivoCancelamentoRepository();
    }

    public MotivoCancelamento findById(int id) throws BusinessException {
        try {
            var motivoCancelamento = repository.findById(id);

            if(motivoCancelamento != null) return motivoCancelamento;

            throw new BusinessException("Motivo do cancelamento não encontrado. Verifique o id informado.");

        }catch (BusinessException e) {
            throw e;

        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException("Erro ao buscar o motivo do cancelamento. Entre em contato com o suporte.");
        }
    }
}
