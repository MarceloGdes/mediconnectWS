package br.unipar.mediconnect.services;

import br.unipar.mediconnect.domain.Consulta;
import br.unipar.mediconnect.domain.HistoricoCancelamentoConsulta;
import br.unipar.mediconnect.domain.Medico;
import br.unipar.mediconnect.domain.Paciente;
import br.unipar.mediconnect.dto.ConsultaResponseCancelDto;
import br.unipar.mediconnect.dto.ConsultaResponseInsertDto;
import br.unipar.mediconnect.exceptions.BusinessException;
import br.unipar.mediconnect.repositories.ConsultaRepository;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Random;

public class ConsultaService {

    private static final LocalTime HORA_ABERTURA = LocalTime.of(7, 0);
    private static final LocalTime HORA_FECHAMENTO = LocalTime.of(19, 0);
    private static final long DURACAO_CONSULTA_HORAS = 1;

    private final ConsultaRepository repository;
    private final MedicoService medicoService;
    private final PacienteService pacienteService;
    private final MotivoCancelamentoService motivoCancelamentoService;
    private final HistoricoConsultasCanceladasService historicoConsultasCanceladasService;

    public ConsultaService() {
        repository = new ConsultaRepository();
        pacienteService = new PacienteService();
        medicoService = new MedicoService();
        motivoCancelamentoService = new MotivoCancelamentoService();
        historicoConsultasCanceladasService = new HistoricoConsultasCanceladasService();
    }

    public ConsultaResponseInsertDto agendar(Consulta consulta) throws BusinessException {
        validarDataAgendamento(consulta.getDataHoraAgendamento());

        Paciente paciente;
        Medico medico;

        if(consulta.getMedico().getId() > 0) {
            medico = medicoService.findById(consulta.getMedico().getId());
            validarMedico(medico, consulta.getDataHoraAgendamento());

        }else {
            medico = selecionarMedicoDisponível(consulta.getDataHoraAgendamento());
            if(medico == null)
                throw new BusinessException("Nenhum médico diposnivel para a data e horário selecionado.");
        }

        paciente = pacienteService.findById(consulta.getPaciente().getId());
        validarPaciente(paciente, consulta.getDataHoraAgendamento());

        consulta.setMedico(medico);
        consulta.setPaciente(paciente);

        try{
            consulta = repository.insert(consulta);

            return new ConsultaResponseInsertDto(consulta);

        }catch (Exception e){
            e.printStackTrace();
            throw new BusinessException("Houve um erro ao agendar a consulta. Por gentileza, entre em contato com o suporte");
        }

    }

    private ArrayList<LocalDateTime> consultarDatasPreenchidas(int medicoId, LocalDateTime dataAgendamento) throws BusinessException {
        try {
            return repository.selectConsultasAgendadasNoPeriodo(
                    medicoId, dataAgendamento, dataAgendamento.plusHours(DURACAO_CONSULTA_HORAS));

        }catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException("Houve um erro ao verificar a disponíbilidade do médico. Por gentileza, entre em contato com o suporte");
        }
    }

    private Consulta consultarAgendamentosNoPeriodoPorPaciente(int pacienteId, LocalDateTime dataAgendamento) throws BusinessException {
        try {
            return repository.selectConsultasPorPacienteEDataAgendamento(pacienteId, dataAgendamento);

        }catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException("Houve um erro ao verificar a disponíbilidade do médico. Por gentileza, entre em contato com o suporte");
        }
    }

    private void validarDataAgendamento(LocalDateTime dataAgendamento) throws BusinessException {
        try {

            if(dataAgendamento.getDayOfWeek() == DayOfWeek.SUNDAY)
                throw new BusinessException("A clinica não abre aos Domingos. Por gentileza, escolha outra data.");

            if(dataAgendamento.isBefore(LocalDateTime.now()))
                throw new BusinessException("Você selecionou uma data ou horário retroativo. Verifique.");

            if(dataAgendamento.toLocalTime().isBefore(HORA_ABERTURA) || dataAgendamento.toLocalTime().isAfter(HORA_FECHAMENTO))
                throw new BusinessException("Você selecionou um horário incompativel. " +
                        "Selecione um horario disponível entre as 07:00 às 19:00.");

            if(!dataAgendamento.isAfter(LocalDateTime.now().plusMinutes(30)))
                throw new BusinessException("O agendamento precisa ser feito com 30 minutos de antecedencia.");


        }catch (BusinessException e){
            throw e;

        }catch (Exception e){
            e.printStackTrace();
            throw new BusinessException("Houve um erro ao verificar a data de agendamento inserida. " +
                    "Por gentileza, entre em contato com o suporte");
        }

    }

    private void validarMedico(Medico medico, LocalDateTime dataAgendamento) throws BusinessException {
        if(!medico.isStAtivo())
            throw new BusinessException("Não é possível agendar consultas com médicos inativos.");

        var listaDatasPreenchidas = consultarDatasPreenchidas(medico.getId(), dataAgendamento);

        if(!listaDatasPreenchidas.isEmpty()) {
            throw new BusinessException("Você selecionou uma data e horario em que o médico não vai estar disponível" +
                    " devido a uma consulta já agendada ou irá conflitar com uma consulta em andamento. As consultas tem " +
                    "Agende um hórario diferente!");
        }
    }

    private void validarPaciente(Paciente paciente, LocalDateTime dataAgendamento) throws BusinessException {
        if(!paciente.isStAtivo())
            throw new BusinessException("Não é possível agendar consultas para pacientes inativos.");

        Consulta consultaAgendada = consultarAgendamentosNoPeriodoPorPaciente(paciente.getId(), dataAgendamento);
        if(consultaAgendada != null)
            throw new BusinessException("O paciente já tem uma consulta agendada na data informada.");

    }

    private Medico selecionarMedicoDisponível(LocalDateTime dataAgendamento) throws BusinessException {
        Random random = new Random();

        ArrayList<Medico> medicos = medicoService.getAllMedicos();

        for (int i = 0; i < medicos.size(); i++) {
            Medico medico = medicos.get(random.nextInt(medicos.size()));

            if (medico.isStAtivo()) {
                if (consultarDatasPreenchidas(medico.getId(), dataAgendamento).isEmpty())
                    return medico;
            }
        }

        return null;
    }

    public ConsultaResponseCancelDto cancelarConsulta(HistoricoCancelamentoConsulta histCancConsulta) throws BusinessException {
        var consulta = findById(histCancConsulta.getConsulta().getId());
        if(consulta.isCancelada())
            throw new BusinessException("A consulta já está cancelada");

        if(LocalDateTime.now().plusHours(24).isAfter(consulta.getDataHoraAgendamento()))
            throw new BusinessException("A consulta não pode ser cancelada, devido o prazo de 24 horas de antecedencia.");

        try {
            //O find by id já valida se o motivo existe. Além de que id, por ser um int, não pode ser nulo.
            var motivoCancelamento = motivoCancelamentoService.findById(histCancConsulta.getMotivoCancelamento().getId());

            histCancConsulta.setConsulta(consulta);

            histCancConsulta.setMotivoCancelamento(motivoCancelamento);
            var linhasAlteradas = repository.updateSetConsultaCancelada(consulta.getId());

            if(linhasAlteradas > 0) {
                var historicoCancelamento = historicoConsultasCanceladasService.insert(histCancConsulta);
                return new ConsultaResponseCancelDto(histCancConsulta);

            }else {
                throw new BusinessException("Erro ao cancelar a consulta. Entre em contato com o suporte.");
            }

        }catch (BusinessException e) {
            throw e;

        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException("Erro ao cancelar a consulta. Entre em contato com o suporte.");
        }
    }

    public Consulta findById(int id) throws BusinessException {
        try {
            var consulta = repository.findById(id);

            if(consulta != null) return consulta;

            throw new BusinessException("Consulta não encontrada. Verifique o id informado.");

        }catch (BusinessException e) {
            throw e;

        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException("Erro ao buscar a consulta. Entre em contato com o suporte.");
        }
    }
}
