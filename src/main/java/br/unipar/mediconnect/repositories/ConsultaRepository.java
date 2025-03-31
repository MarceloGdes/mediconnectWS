package br.unipar.mediconnect.repositories;

import br.unipar.mediconnect.domain.Consulta;
import br.unipar.mediconnect.domain.Medico;
import br.unipar.mediconnect.domain.Paciente;
import br.unipar.mediconnect.infra.ConnectionFactory;

import javax.naming.NamingException;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ConsultaRepository {

    private static final String INSERT =
            "INSERT INTO consulta(paciente_id, medico_id, data_agendamento) " +
                    "VALUES(?, ?, ?)";

    //usado para verificar se já existe uma consulta agendada para um médico no mesmo intervalo de tempo que a nova consulta.
    private static final String SELECT_CONSULTAS_AGENDADAS_NO_PERIODO =
            "SELECT data_agendamento FROM consulta WHERE medico_id=? " + " AND cancelada = false " +
            "AND ((data_agendamento BETWEEN ? AND ?) OR " +
                    "(data_agendamento + INTERVAL '1 hour' BETWEEN ? AND ?))";


    private static final String SELECT_CONSULTAS_POR_PACIENTE =
            "SELECT * FROM consulta WHERE paciente_id=? " +
                    "AND CAST(data_agendamento AS DATE)=? " +
                    "AND cancelada = false";

    public Consulta insert(Consulta consulta) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = new ConnectionFactory().getConnection();
            pstmt = conn.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);

            pstmt.setInt(1, consulta.getPaciente().getId());
            pstmt.setInt(2, consulta.getMedico().getId());
            pstmt.setTimestamp(3, Timestamp.valueOf(consulta.getDataHoraAgendamento()));

            pstmt.executeUpdate();

            rs = pstmt.getGeneratedKeys();
            rs.next();

            consulta.setId(rs.getInt(1));

        }finally {
            if(conn != null) conn.close();
            if(rs != null) rs.close();
            if(pstmt != null) pstmt.close();
        }

        return consulta;
    }

    public ArrayList<LocalDateTime> selectConsultasAgendadasNoPeriodo(int medicoId, LocalDateTime dataAgendamento, LocalDateTime dataEncerramento) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        ArrayList<LocalDateTime> datasPreenchidas = new ArrayList<>();

        try {
            conn = new ConnectionFactory().getConnection();
            pstmt = conn.prepareStatement(SELECT_CONSULTAS_AGENDADAS_NO_PERIODO);

            pstmt.setInt(1, medicoId);
            pstmt.setTimestamp(2, Timestamp.valueOf(dataAgendamento));
            pstmt.setTimestamp(3, Timestamp.valueOf(dataEncerramento));
            pstmt.setTimestamp(4, Timestamp.valueOf(dataAgendamento));
            pstmt.setTimestamp(5, Timestamp.valueOf(dataEncerramento));

            rs = pstmt.executeQuery();

            while(rs.next()) {
                datasPreenchidas.add(rs.getTimestamp(1).toLocalDateTime());
            }

            return datasPreenchidas;

        }finally {
            if(conn != null) conn.close();
            if(rs != null) rs.close();
            if(pstmt != null) pstmt.close();
        }
    }

    public Consulta selectConsultasPorPacienteEDataAgendamento(int pacienteId, LocalDateTime dataAgendamento) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = new ConnectionFactory().getConnection();
            pstmt = conn.prepareStatement(SELECT_CONSULTAS_POR_PACIENTE);

            pstmt.setInt(1, pacienteId);
            pstmt.setTimestamp(2, Timestamp.valueOf(dataAgendamento));

            rs = pstmt.executeQuery();

            if(rs.next()) {
                var consulta = new Consulta();

                consulta.setId(rs.getInt("id"));
                consulta.setDataHoraAgendamento(rs.getTimestamp("data_agendamento").toLocalDateTime());
                consulta.setCancelada(rs.getBoolean("cancelada"));

                var paciente = new Paciente();
                paciente.setId(rs.getInt("paciente_id"));
                consulta.setPaciente(paciente);

                var medico = new Medico();
                medico.setId(rs.getInt("medico_id"));
                consulta.setMedico(medico);

                return consulta;
            }

        }finally {
            if(conn != null) conn.close();
            if(rs != null) rs.close();
            if(pstmt != null) pstmt.close();
        }

        return null;
    }
}
