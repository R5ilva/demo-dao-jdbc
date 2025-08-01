package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.entity.EntidadeDominio;
import model.entity.Tutor;

public class TutorDaoJDBC implements IDao {

	private Connection conn;

	public TutorDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(EntidadeDominio obj) {
		PreparedStatement st = null;
		try {
			Tutor tutor = (Tutor)obj;
			
			st = conn.prepareStatement("insert into tutor "
					+ "(ttr_nome, ttr_cpf,ttr_nascimento) "
					+ "values "
					+ "(?, ? , ?)",
					Statement.RETURN_GENERATED_KEYS);
			
			
			st.setString(1,tutor.getNome());
			st.setString(2,tutor.getCpf());
			java.sql.Date dataSql = java.sql.Date.valueOf(tutor.getDataNascimento());
			st.setDate(3, dataSql);
			
			int rowsAffected = st.executeUpdate();
			if(rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					tutor.setId(id);
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
			Tutor tutor = (Tutor)obj;
			
			st = conn.prepareStatement("update tutor "
					+ "set ttr_nome = ?, ttr_cpf = ?, ttr_nascimento = ? "
					+ "where ttr_id = ? ",
					Statement.RETURN_GENERATED_KEYS);
			
			
			st.setString(1,tutor.getNome());
			st.setString(2,tutor.getCpf());
			java.sql.Date dataSql = java.sql.Date.valueOf(tutor.getDataNascimento());
			st.setDate(3, dataSql);
			st.setInt(4, tutor.getId());
			
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
			st = conn.prepareStatement("DELETE FROM tutor WHERE ttr_id = ?");
			
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
			st = conn.prepareStatement("Select tutor.* from tutor where ttr_id = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				Tutor tutor = instanciaTutor(rs);
				return tutor;
			}
			return null;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
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

	@Override
	public List<EntidadeDominio> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("Select tutor.* from tutor order by ttr_nome");
			
			rs = st.executeQuery();
			
			List<EntidadeDominio> list = new ArrayList<>();
			
			while (rs.next()) {
				
				Tutor tutor = instanciaTutor(rs);
				list.add(tutor);
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
