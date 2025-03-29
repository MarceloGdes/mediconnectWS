package br.unipar.mediconnect.repositories;

import br.unipar.mediconnect.domain.Medico;
import br.unipar.mediconnect.infra.ConnectionFactory;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MedicoRepository {
    private static final String INSERT =
            "INSERT INTO medico(nome, email, telefone, crm, especialidade_id, logradouro, numero, complemento, bairro)" +
                    "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public Medico insert(Medico medico) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = new ConnectionFactory().getConnection();
            pstmt = conn.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, medico.getNome());
            pstmt.setString(2, medico.getEmail());
            pstmt.setString(3, medico.getTelefone());
            pstmt.setString(4, medico.getCrm());
            pstmt.setInt(5, medico.getEspecialidade().getId());
            pstmt.setString(6, medico.getEndereco().getLogradouro());
            pstmt.setInt(7, medico.getEndereco().getNum());
            pstmt.setString(8, medico.getEndereco().getComplemento());
            pstmt.setString(9, medico.getEndereco().getBairro());

            pstmt.executeUpdate();

            rs = pstmt.getGeneratedKeys();
            rs.next();

            medico.setId(rs.getInt(1));

        }finally {
            if(conn != null) conn.close();
            if(rs != null) rs.close();
            if(pstmt != null) pstmt.close();
        }

        return medico;
    }

}
