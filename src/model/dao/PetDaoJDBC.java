package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.entity.EntidadeDominio;
import model.entity.Pet;
import model.entity.Tutor;

public class PetDaoJDBC implements IDao {
	private Connection conn;

	public PetDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(EntidadeDominio obj) {
		PreparedStatement st = null;
		Pet pet = (Pet) obj;
		try {
			st = conn.prepareStatement(
					"INSERT INTO pet " + "(pet_nome, pet_especie, pet_genero, pet_idade, pet_ttr_id) " + "VALUES " + "(?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);

			st.setString(1, pet.getNome());
			st.setString(2, pet.getEspecie());
			st.setString(3, pet.getGenero());
			st.setInt(4, pet.getIdade());
			st.setInt(5, pet.getTutor().getId());

			int linhasAfetadas = st.executeUpdate();
			if (linhasAfetadas > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					pet.setId(id);
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
		Pet pet = (Pet) obj;
		try {
			st = conn.prepareStatement(
					"UPDATE pet  SET pet_nome =?, pet_especie = ?, pet_genero = ?, pet_idade = ?, pet_ttr_id = ? WHERE pet_id =?");

			st.setString(1, pet.getNome());
			st.setString(2, pet.getEspecie());
			st.setString(3, pet.getGenero());
			st.setInt(4, pet.getIdade());
			st.setInt(5, pet.getTutor().getId());
			st.setInt(6, pet.getId());

			st.executeUpdate();
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);

		}

	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement  st = null;
		try {
			st = conn.prepareStatement("DELETE FROM pet WHERE pet_id = ?");
			
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
					"SELECT pet_id, pet_nome, pet_especie, pet_genero, pet_idade, "
							+ "ttr_id, ttr_nome, ttr_cpf, ttr_nascimento "
							+ "FROM pet INNER JOIN tutor ON pet_ttr_id = ttr_id " + "WHERE pet_id = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {

				Tutor tut = instaciaTutor(rs);
				Pet pet = instanciaPet(rs, tut);
				return pet;
			}
			return null;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
	}

	private Tutor instaciaTutor(ResultSet rs) throws SQLException {
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

	@Override
	public List<EntidadeDominio> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT pet_id, pet_nome, pet_especie, pet_genero, pet_idade, "
							+ "ttr_id, ttr_nome, ttr_CPF, ttr_nascimento "
							+ "FROM pet INNER JOIN tutor ON pet_ttr_id = ttr_id ORDER BY pet_nome");

			rs = st.executeQuery();

			List<EntidadeDominio> list = new ArrayList<>();
			Map<Integer, Tutor> map = new HashMap<>();

			while (rs.next()) {
				Tutor tut = map.get(rs.getInt("ttr_id"));

				if (tut == null) {
					tut = instaciaTutor(rs);
					map.put(rs.getInt("ttr_id"), tut);
				}

				Pet pet = instanciaPet(rs, tut);
				list.add(pet);
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
