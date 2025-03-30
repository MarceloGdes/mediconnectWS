package br.unipar.mediconnect.repositories;

import br.unipar.mediconnect.domain.EnderecoPaciente;
import br.unipar.mediconnect.domain.Paciente;
import br.unipar.mediconnect.infra.ConnectionFactory;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PacienteRepository {
    private static final String INSERT =
            "INSERT INTO paciente(nome, email, telefone, cpf, logradouro, numero, complemento, bairro, cidade, uf, cep)" +
                    "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String SELECT_ALL = "SELECT nome, email, cpf FROM paciente ORDER BY nome";

    private static final String UPDATE = "UPDATE paciente " +
            "SET nome=?, telefone=?, logradouro=?, numero=?, complemento=?, bairro=?, cidade=?, uf=?, cep=? " +
            "WHERE id=?";

    private static final String FIND_BY_ID = "SELECT * FROM paciente WHERE id=?";

    private static final String UPDATE_STATUS = "UPDATE paciente SET ativo=? WHERE id=?";

    public Paciente insert(Paciente paciente) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = new ConnectionFactory().getConnection();
            pstmt = conn.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, paciente.getNome());
            pstmt.setString(2, paciente.getEmail());
            pstmt.setString(3, paciente.getTelefone());
            pstmt.setString(4, paciente.getCpf());
            pstmt.setString(5, paciente.getEndereco().getLogradouro());
            pstmt.setInt(6, paciente.getEndereco().getNum());
            pstmt.setString(7, paciente.getEndereco().getComplemento());
            pstmt.setString(8, paciente.getEndereco().getBairro());
            pstmt.setString(9, paciente.getEndereco().getCidade());
            pstmt.setString(10, paciente.getEndereco().getUf());
            pstmt.setString(11, paciente.getEndereco().getCep());

            pstmt.executeUpdate();

            rs = pstmt.getGeneratedKeys();
            rs.next();

            paciente.setId(rs.getInt(1));

        }finally {
            if(conn != null) conn.close();
            if(rs != null) rs.close();
            if(pstmt != null) pstmt.close();
        }

        return paciente;
    }

    public ArrayList<Paciente> selectAll() throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        var pacientes = new ArrayList<Paciente>();

        try {
            conn = new ConnectionFactory().getConnection();
            pstmt =  conn.prepareStatement(SELECT_ALL);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                var paciente  = new Paciente();

                paciente.setNome(rs.getString("nome"));
                paciente.setEmail(rs.getString("email"));
                paciente.setCpf(rs.getString("cpf"));

                pacientes.add(paciente);
            }

        }finally {
            if(conn != null) conn.close();
            if(rs != null) rs.close();
            if(pstmt != null) pstmt.close();
        }

        return pacientes;
    }

    public int update(Paciente paciente) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = new ConnectionFactory().getConnection();
            pstmt = conn.prepareStatement(UPDATE);

            pstmt.setString(1, paciente.getNome());
            pstmt.setString(2, paciente.getTelefone());
            pstmt.setString(3, paciente.getEndereco().getLogradouro());
            pstmt.setInt(4, paciente.getEndereco().getNum());
            pstmt.setString(5, paciente.getEndereco().getComplemento());
            pstmt.setString(6, paciente.getEndereco().getBairro());
            pstmt.setString(7, paciente.getEndereco().getCidade());
            pstmt.setString(8, paciente.getEndereco().getUf());
            pstmt.setString(9, paciente.getEndereco().getCep());
            pstmt.setInt(10, paciente.getId());

            return pstmt.executeUpdate();

        }finally {
            if(conn != null) conn.close();
            if(pstmt != null) pstmt.close();
        }
    }

    public int updateStatus(int id) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = new ConnectionFactory().getConnection();
            pstmt = conn.prepareStatement(UPDATE_STATUS);

            pstmt.setBoolean(1, false);
            pstmt.setInt(2, id);

            return pstmt.executeUpdate();

        }finally {
            if(conn != null) conn.close();
            if(pstmt != null) pstmt.close();
        }
    }

    public Paciente findById(int id) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            conn = new ConnectionFactory().getConnection();
            pstmt = conn.prepareStatement(FIND_BY_ID);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            if(rs.next()){
                var paciente = new Paciente();
                paciente.setId(id);
                paciente.setNome(rs.getString("nome"));
                paciente.setEmail(rs.getString("email"));
                paciente.setCpf(rs.getString("cpf"));
                paciente.setTelefone(rs.getString("telefone"));
                paciente.setStAtivo(rs.getBoolean("ativo"));

                var endereco = new EnderecoPaciente();
                endereco.setNum(rs.getInt("numero"));
                endereco.setBairro(rs.getString("bairro"));
                endereco.setComplemento(rs.getString("complemento"));
                endereco.setLogradouro(rs.getString("logradouro"));
                endereco.setCidade(rs.getString("cidade"));
                endereco.setUf(rs.getString("uf"));
                endereco.setCep(rs.getString("cep"));
                paciente.setEndereco(endereco);

                return paciente;
            }

        } finally {
            if(conn != null) conn.close();
            if(rs != null) rs.close();
            if(pstmt != null) pstmt.close();

        }

        return null;
    }
}
