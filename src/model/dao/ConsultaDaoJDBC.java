package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.entity.Consulta;
import model.entity.EntidadeDominio;
import model.entity.Pet;
import model.entity.Tutor;
import model.entity.Veterinario;

public class ConsultaDaoJDBC implements IDao {
    private Connection conn;
    
    public ConsultaDaoJDBC(Connection conn) {
        this.conn = conn;
    }
    
    @Override
    public void insert(EntidadeDominio obj) {
        PreparedStatement st = null;
        Consulta consulta = (Consulta) obj;
        
        try {
            st = conn.prepareStatement("INSERT INTO consulta "
                                     + "(con_data, con_hora, con_diagnostico, con_pet_id, con_vet_id) "
                                     + "VALUES "
                                     + "(?, ?, ?, ?, ?)",
                                     Statement.RETURN_GENERATED_KEYS);
            
            java.sql.Date dataSql = java.sql.Date.valueOf(consulta.getData());
            java.sql.Time timeSql = java.sql.Time.valueOf(consulta.getHora());
            
            st.setDate(1, dataSql);
            st.setTime(2, timeSql);
            st.setString(3, consulta.getDiagnostico());
            st.setInt(4, consulta.getPet().getId());
            st.setInt(5, consulta.getVeterionario().getId());
            
            int linhasAfetadas = st.executeUpdate();
            if (linhasAfetadas > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    consulta.setId(id);
                }
                DB.closeResultSet(rs);
            } else {
                throw new DbException("Erro inesperado!");
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
        Consulta consulta = (Consulta) obj;
        
        try {
            st = conn.prepareStatement("UPDATE consulta "
                                     + "SET con_data = ?, con_hora = ?, con_diagnostico = ?, con_pet_id = ?, con_vet_id = ? "
                                     + "WHERE con_id = ?");
            
            java.sql.Date dataSql = java.sql.Date.valueOf(consulta.getData());
            st.setDate(1, dataSql);
            java.sql.Time timeSql = java.sql.Time.valueOf(consulta.getHora());
            st.setTime(2, timeSql);
            st.setString(3, consulta.getDiagnostico());
            st.setInt(4, consulta.getPet().getId());
            st.setInt(5, consulta.getVeterionario().getId());
            st.setInt(6, consulta.getId());
            
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
            st = conn.prepareStatement("DELETE FROM consulta WHERE con_id = ?");
            
            st.setInt(1, id);
            
            st.executeUpdate();
        }
        catch(SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public EntidadeDominio findById(Integer id) {
    	  PreparedStatement st = null;
    	    ResultSet rs = null;
    	    try {
    	        st = conn.prepareStatement(
    	            "SELECT c.con_id, c.con_data, c.con_hora, c.con_diagnostico, " +
    	            "p.pet_id, p.pet_nome, p.pet_especie, p.pet_genero, p.pet_idade, " +
    	            "t.ttr_id, t.ttr_nome, t.ttr_cpf, t.ttr_nascimento, " +
    	            "v.vet_id, v.vet_nome, v.vet_crm, v.vet_especialidade " +
    	            "FROM consulta c " +
    	            "INNER JOIN pet p ON c.con_pet_id = p.pet_id " +
    	            "INNER JOIN tutor t ON p.pet_ttr_id = t.ttr_id " +
    	            "INNER JOIN veterinario v ON c.con_vet_id = v.vet_id " +
    	            "WHERE c.con_id = ?");
    	        
    	        st.setInt(1, id);
    	        rs = st.executeQuery();
    	        
    	        if (rs.next()) {
    	            Tutor tutor = instanciaTutor(rs); 
    	            

    	            Pet pet = instanciaPet(rs, tutor);
    	            
  
    	            Veterinario vet = instanciaVeterinario(rs);
    
    	            Consulta consulta = instanciaConsulta(rs, pet, vet);
    	            
    	            return consulta;
    	        }
    	        return null;
    	    } catch (SQLException e) {
    	        throw new DbException("Erro ao buscar consulta por ID: " + e.getMessage());
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
                "SELECT c.con_id, c.con_data, c.con_hora, c.con_diagnostico, " +
                "p.pet_id, p.pet_nome, p.pet_especie, p.pet_genero, p.pet_idade, " +
                "t.ttr_id, t.ttr_nome, t.ttr_cpf, t.ttr_nascimento, " +
                "v.vet_id, v.vet_nome, v.vet_crm, v.vet_especialidade " +
                "FROM consulta c " +
                "INNER JOIN pet p ON c.con_pet_id = p.pet_id " +
                "INNER JOIN tutor t ON p.pet_ttr_id = t.ttr_id " +
                "INNER JOIN veterinario v ON c.con_vet_id = v.vet_id " +
                "ORDER BY c.con_data DESC, c.con_hora DESC");
            
            rs = st.executeQuery();
            List<EntidadeDominio> consultas = new ArrayList<>();
            
            while (rs.next()) {
      
                Tutor tutor = instanciaTutor(rs);
                
         
                Pet pet = instanciaPet(rs, tutor);
                
        
                Veterinario vet = instanciaVeterinario(rs);
                
       
                Consulta consulta = instanciaConsulta(rs, pet, vet);
                
                consultas.add(consulta);
            }
            return consultas;
        } catch (SQLException e) {
            throw new DbException("Erro ao consultar todas as consultas: " + e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
    }

    private Tutor instanciaTutor(ResultSet rs) throws SQLException {
        Tutor tutor = new Tutor();
        tutor.setId(rs.getInt("ttr_id"));
        tutor.setNome(rs.getString("ttr_nome"));
        tutor.setCpf(rs.getString("ttr_cpf"));
        java.sql.Date dataSql = rs.getDate("ttr_nascimento");
        LocalDate dataNascimento = dataSql != null ? dataSql.toLocalDate() : null;
        tutor.setDataNascimento(dataNascimento);
        return tutor;
    }

    private Pet instanciaPet(ResultSet rs, Tutor tutor) throws SQLException {
        Pet pet = new Pet();
        pet.setId(rs.getInt("pet_id"));
        pet.setNome(rs.getString("pet_nome"));
        pet.setEspecie(rs.getString("pet_especie"));
        pet.setGenero(rs.getString("pet_genero"));
        pet.setIdade(rs.getInt("pet_idade"));
        pet.setTutor(tutor);
        return pet;
    }
    
    private Veterinario instanciaVeterinario(ResultSet rs) throws SQLException {
        Veterinario veterinario = new Veterinario();
        veterinario.setId(rs.getInt("vet_id"));
        veterinario.setNome(rs.getString("vet_nome"));
        veterinario.setCrm(rs.getString("vet_crm"));
        veterinario.setEspecialidade(rs.getString("vet_especialidade"));
        return veterinario;
    }
    
    private Consulta instanciaConsulta(ResultSet rs, Pet pet, Veterinario vet) throws SQLException {
        Consulta con = new Consulta();
        con.setId(rs.getInt("con_id"));
        
        java.sql.Date dataSql = rs.getDate("con_data");
        LocalDate data = dataSql != null ? dataSql.toLocalDate() : null;
        con.setData(data);
        
        java.sql.Time timeSql = rs.getTime("con_hora");
        LocalTime hora = timeSql != null ? timeSql.toLocalTime() : null;
        con.setHora(hora);
        
        con.setDiagnostico(rs.getString("con_diagnostico"));
        con.setPet(pet);
        con.setVeterionario(vet);
        return con;
    }
}
