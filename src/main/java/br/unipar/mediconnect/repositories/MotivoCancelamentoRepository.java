package br.unipar.mediconnect.repositories;

import br.unipar.mediconnect.domain.MotivoCancelamento;
import br.unipar.mediconnect.infra.ConnectionFactory;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MotivoCancelamentoRepository {
    private static final String FIND_BY_ID = "SELECT * FROM motivo_cancelamento WHERE id=?";

    public MotivoCancelamento findById(int id) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            conn = new ConnectionFactory().getConnection();
            pstmt = conn.prepareStatement(FIND_BY_ID);

            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            if(rs.next()){
                var motivoCancelamento = new MotivoCancelamento();
                motivoCancelamento.setId(id);
                motivoCancelamento.setDescricao(rs.getString("descricao"));

                return motivoCancelamento;
            }

        }finally {
            if(conn != null) conn.close();
            if(rs != null) rs.close();
            if(pstmt != null) pstmt.close();
        }

        return null;
    }
}
