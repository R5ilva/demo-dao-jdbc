package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.entity.Consulta;
import model.entity.EntidadeDominio;
import model.entity.Medicacao;
import model.entity.Medicamento;

public class MedicacaoDaoJDBC implements IDao {
    private Connection conn;
    
    public MedicacaoDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(EntidadeDominio obj) {
        PreparedStatement st = null;
        try {
            Medicacao medicacao = (Medicacao) obj;
            
            st = conn.prepareStatement(
                "INSERT INTO medicacao (mdc_prescricao, mdc_mdt_id, mdc_con_id) VALUES (?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS);
            
            st.setString(1, medicacao.getPrescricao());
            st.setInt(2, medicacao.getMedicamento().getId());
            st.setInt(3, medicacao.getConsulta().getId());
            
            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    medicacao.setId(id);
                }
                DB.closeResultSet(rs);
            } else {
                throw new DbException("Erro inesperado! Nenhuma linha foi afetada");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void update(EntidadeDominio obj) {
        PreparedStatement st = null;
        try {
            Medicacao medicacao = (Medicacao) obj;
            
            st = conn.prepareStatement(
                "UPDATE medicacao SET mdc_prescricao = ?, mdc_mdt_id = ?, ,mdc_con_id = ? WHERE mdc_id = ?");
            
            st.setString(1, medicacao.getPrescricao());
            st.setInt(2, medicacao.getMedicamento().getId());
            st.setInt(3, medicacao.getConsulta().getId());
            st.setInt(4, medicacao.getId());
            
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("DELETE FROM medicacao WHERE mdc_id = ?");
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public EntidadeDominio findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                "SELECT m.*, med.mdt_Nome , med.mdt_descricao, " +
                "c.con_data, c.con_hora, c.con_diagnostico " +
                "FROM medicacao m " +
                "INNER JOIN medicamento med ON m.mdc_mdt_id = med.mdt_id " +
                "INNER JOIN consulta c ON m.mdc_con_id = c.con_id " +
                "WHERE m.mdc_id = ?");
            
            st.setInt(1, id);
            rs = st.executeQuery();
            
            if (rs.next()) {
                Medicamento medicamento = instanciaMedicamento(rs);
                Consulta consulta = instanciaConsulta(rs);
                Medicacao medicacao = instanciaMedicacao(rs, medicamento, consulta);
                return medicacao;
            }
            return null;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
    }

    @Override
    public List<EntidadeDominio> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                "SELECT m.*, med.mdt_nome, med.mdt_Prescricao, " +
                "c.con_data, c.con_hora, c.con_diagnostico " +
                "FROM medicacao m " +
                "INNER JOIN medicamento med ON m.mdc_mdt_id = med.mdt_id " +
                "INNER JOIN consulta c ON m.mdc_con_id = c.con_id " +
                "ORDER BY m.mdc_id");
            
            rs = st.executeQuery();
            List<EntidadeDominio> list = new ArrayList<>();
            
            while (rs.next()) {
                Medicamento medicamento = instanciaMedicamento(rs);
                Consulta consulta = instanciaConsulta(rs);
                Medicacao medicacao = instanciaMedicacao(rs, medicamento, consulta);
                list.add(medicacao);
            }
            return list;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
    }

    private Medicamento instanciaMedicamento(ResultSet rs) throws SQLException {
        Medicamento medicamento = new Medicamento();
        medicamento.setId(rs.getInt("mdt_id"));
        medicamento.setNome(rs.getString("mdt_nome"));
        return medicamento;
    }

    private Consulta instanciaConsulta(ResultSet rs) throws SQLException {
        Consulta consulta = new Consulta();
        consulta.setId(rs.getInt("con_id"));
        consulta.setData(rs.getDate("con_data").toLocalDate());
        consulta.setHora(rs.getTime("con_hora").toLocalTime());
        consulta.setDiagnostico(rs.getString("con_diagnostico"));
        return consulta;
    }

    private Medicacao instanciaMedicacao(ResultSet rs, Medicamento medicamento, Consulta consulta) throws SQLException {
        Medicacao medicacao = new Medicacao();
        medicacao.setId(rs.getInt("mdc_id"));
        medicacao.setPrescricao(rs.getString("mdc_prescricao"));
        medicacao.setMedicamento(medicamento);
        medicacao.setConsulta(consulta);
        return medicacao;
    }
}