package br.unipar.mediconnect.repositories;

import br.unipar.mediconnect.domain.Especialidade;
import br.unipar.mediconnect.domain.Medico;
import br.unipar.mediconnect.infra.ConnectionFactory;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicoRepository {
    private static final String INSERT =
            "INSERT INTO medico(nome, email, telefone, crm, especialidade_id, logradouro, numero, complemento, bairro)" +
                    "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String SELECT_ALL = "SELECT nome, email, crm, especialidade_id FROM medico ORDER BY nome";

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

    public ArrayList<Medico> selectAll() throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        var medicos = new ArrayList<Medico>();

        try {
            conn = new ConnectionFactory().getConnection();
            pstmt = conn.prepareStatement(SELECT_ALL);
            rs = pstmt.executeQuery();

            while (rs.next()){
                var medico = new Medico();
                medico.setNome(rs.getString("nome"));
                medico.setEmail(rs.getString("email"));
                medico.setCrm(rs.getString("crm"));
                medico.setEspecialidade(new Especialidade());
                medico.getEspecialidade().setId(rs.getInt("especialidade_id"));

                medicos.add(medico);
            }

        }finally {
            if(conn != null) conn.close();
            if(rs != null) rs.close();
            if(pstmt != null) pstmt.close();
        }

        return medicos;
    }
}
