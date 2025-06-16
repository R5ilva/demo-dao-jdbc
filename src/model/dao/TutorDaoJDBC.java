package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import db.DB;
import db.DbException;
import model.entity.EntidadeDominio;
import model.entity.Tutor;

public class TutorDaoJDBC implements IDao{
	
	private Connection conn;
	
	public TutorDaoJDBC (Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(EntidadeDominio obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(EntidadeDominio obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public EntidadeDominio findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"Select tutor.* from tutor where tutor.id = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
			Tutor tutor = new Tutor();
			tutor.setId(rs.getInt("Id"));
			tutor.setNome(rs.getString("Nome"));
			tutor.setCpf(rs.getString("CPF"));
			java.sql.Date dataSql = rs.getDate("Nascimento");
			LocalDate dataNascimento = dataSql != null ? dataSql.toLocalDate() : null;
			tutor.setDataNascimento(dataNascimento);
			return tutor;
			}
			return null;
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
	}

	@Override
	public List<EntidadeDominio> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
