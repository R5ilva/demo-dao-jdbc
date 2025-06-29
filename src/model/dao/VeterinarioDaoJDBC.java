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
import model.entity.EntidadeDominio;
import model.entity.Veterinario;

public class VeterinarioDaoJDBC implements IDao {
	
	private Connection conn;
	
	public VeterinarioDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(EntidadeDominio obj) {
		PreparedStatement st = null;
		try {
			Veterinario veterinario = (Veterinario)obj;
			
			st = conn.prepareStatement("insert into veterinario "
					+ "(vet_nome, vet_crm, vet_especialidade) "
					+ "values "
					+ "(?, ? , ?)",
					Statement.RETURN_GENERATED_KEYS);
			
			
			st.setString(1,veterinario.getNome());
			st.setString(2,veterinario.getCrm());
			st.setString(3,veterinario.getEspecialidade());
			
			int rowsAffected = st.executeUpdate();
			if(rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					veterinario.setId(id);
				}
				DB.closeResultSet(rs);
			}
			else {
				throw new DbException("Erro inesperado! Nenhuma linha foi afetada");
			}
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}

		
	}

	@Override
	public void update(EntidadeDominio obj) {
		PreparedStatement st = null;
		try {
			Veterinario veterinario = (Veterinario)obj;
			
			st = conn.prepareStatement("update veterinario "
					+ "set vet_nome = ?, vet_crm = ?, vet_especialidade = ? "
					+ "where vet_id = ? ");
			
			
			st.setString(1,veterinario.getNome());
			st.setString(2,veterinario.getCrm());
			st.setString(3,veterinario.getEspecialidade());
			st.setInt(4, veterinario.getId());
			
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
	public void deleteById(Integer id) {
		PreparedStatement  st = null;
		try {
			st = conn.prepareStatement("DELETE FROM veterinario WHERE vet_id = ?");
			
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
			st = conn.prepareStatement("Select veterinario.* from veterinario where vet_id = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				Veterinario veterinario = instanciaVeterinario(rs);
				return veterinario;
			}
			return null;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
	}

	private Veterinario instanciaVeterinario(ResultSet rs) throws SQLException {
		Veterinario veterinario = new Veterinario();
		veterinario.setId(rs.getInt("vet_id"));
		veterinario.setNome(rs.getString("vet_nome"));
		veterinario.setCrm(rs.getString("vet_crm"));
		veterinario.setEspecialidade(rs.getString("vet_especialidade"));
		return veterinario;
	}

	@Override
	public List<EntidadeDominio> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("Select veterinario.* from veterinario order by vet_nome");
			
			rs = st.executeQuery();
			
			List<EntidadeDominio> list = new ArrayList<>();
			
			while (rs.next()) {
				
				Veterinario veterinario = instanciaVeterinario(rs);
				list.add(veterinario);
			}
			return list;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
	}

}
