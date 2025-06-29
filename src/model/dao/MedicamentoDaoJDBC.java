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
import model.entity.Medicamento;

public class MedicamentoDaoJDBC implements IDao{
	private Connection conn;
	
	public MedicamentoDaoJDBC (Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(EntidadeDominio obj) {
		PreparedStatement st = null;
		try {
			Medicamento medicamento = (Medicamento)obj;
			
			st = conn.prepareStatement("insert into medicamento "
					+ "(mdt_nome) "
					+ "values "
					+ "(?)",
					Statement.RETURN_GENERATED_KEYS);
			
			
			st.setString(1,medicamento.getNome());
			
			int rowsAffected = st.executeUpdate();
			if(rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					medicamento.setId(id);
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
			Medicamento medicamento = (Medicamento)obj;
			
			st = conn.prepareStatement("update medicamento "
					+ "set mdt_nome = ? "
					+ "where mdt_id = ? ",
					Statement.RETURN_GENERATED_KEYS);
			
			
			st.setString(1,medicamento.getNome());
			st.setInt(2, medicamento.getId());
			
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
			st = conn.prepareStatement("DELETE FROM medicamento WHERE mdt_id = ?");
			
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
			st = conn.prepareStatement("Select medicamento.* from medicamento where mdt_id = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				Medicamento medicamento = instanciaMedicamento(rs);
				return medicamento;
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
			st = conn.prepareStatement("Select medicamento.* from medicamento order by mdt_nome");
			
			rs = st.executeQuery();
			
			List<EntidadeDominio> list = new ArrayList<>();
			
			while (rs.next()) {
				
				Medicamento medicamento = instanciaMedicamento(rs);
				list.add(medicamento);
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
		medicamento.setId(rs.getInt("mdt_Id"));
		medicamento.setNome(rs.getString("mdt_nome"));
		return medicamento;
	}

}
