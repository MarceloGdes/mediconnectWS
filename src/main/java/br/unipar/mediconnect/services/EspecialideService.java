package br.unipar.mediconnect.services;

import br.unipar.mediconnect.domain.Especialidade;
import br.unipar.mediconnect.exceptions.BusinessException;
import br.unipar.mediconnect.repositories.EspecialidadeRepository;

import javax.naming.NamingException;
import java.sql.SQLException;

public class EspecialideService {

    private EspecialidadeRepository repository;

    public EspecialideService() {
        repository = new EspecialidadeRepository();
    }

    public Especialidade findById(int id) throws BusinessException {
        try {
            var especialidade = repository.findById(id);

            if(especialidade != null) return especialidade;

            throw new BusinessException("Especialidade não encontrada. Verifique o id informado.");

        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException("Erro ao buscar a especialidade. Entre em contato com o suporte.");
        }
    }
}
