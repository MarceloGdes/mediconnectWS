package br.unipar.mediconnect.repositories;

import br.unipar.mediconnect.domain.HistoricoCancelamentoConsulta;
import br.unipar.mediconnect.infra.ConnectionFactory;

import javax.naming.NamingException;
import java.sql.*;

public class HistoricoConsultaCanceladaRepository {

    private static final String INSERT =
            "INSERT INTO historico_cancelamento_consulta(consulta_id, motivo_cancelamento_id) " +
            "VALUES(?, ?)";

    public HistoricoCancelamentoConsulta insert(HistoricoCancelamentoConsulta histCancConsulta)
            throws SQLException, NamingException {

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = new ConnectionFactory().getConnection();
            pstmt = conn.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);

            pstmt.setInt(1, histCancConsulta.getConsulta().getId());
            pstmt.setInt(2, histCancConsulta.getMotivoCancelamento().getId());

            pstmt.executeUpdate();

            rs = pstmt.getGeneratedKeys();
            rs.next();

            histCancConsulta.setId(rs.getInt(1));

        }finally {
            if(conn != null) conn.close();
            if(rs != null) rs.close();
            if(pstmt != null) pstmt.close();
        }

        return histCancConsulta;
    }
}
