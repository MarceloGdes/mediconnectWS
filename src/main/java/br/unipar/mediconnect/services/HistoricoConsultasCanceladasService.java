package br.unipar.mediconnect.services;

import br.unipar.mediconnect.domain.HistoricoCancelamentoConsulta;
import br.unipar.mediconnect.exceptions.BusinessException;
import br.unipar.mediconnect.repositories.HistoricoConsultaCanceladaRepository;

public class HistoricoConsultasCanceladasService {
    private final HistoricoConsultaCanceladaRepository repository;

    public HistoricoConsultasCanceladasService() {
        repository = new HistoricoConsultaCanceladaRepository();
    }

    public HistoricoCancelamentoConsulta insert(HistoricoCancelamentoConsulta histCancConsulta) throws BusinessException {

        try {
            return repository.insert(histCancConsulta);

        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException("Erro ao registrar o cancelamento da consulta. Entre em contato com o suporte.");

        }

    }
}
