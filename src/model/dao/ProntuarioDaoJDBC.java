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
import model.entity.Pet;
import model.entity.Prontuario;
import model.entity.Tutor;
import model.entity.Veterinario;

public class ProntuarioDaoJDBC implements IDao {
	private Connection conn;

	public ProntuarioDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(EntidadeDominio obj) {
		PreparedStatement st = null;
		try {
			Prontuario prontuario = (Prontuario) obj;

			st = conn.prepareStatement(
					"INSERT INTO prontuario (pro_observacao, pro_procedimento, pro_exames, pro_evolucao, pro_con_id) "
							+ "VALUES (?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);

			st.setString(1, prontuario.getObservacao());
			st.setString(2, prontuario.getProcedimento());
			st.setString(3, prontuario.getExames());
			st.setString(4, prontuario.getEvolucao());
			st.setInt(5, prontuario.getConsulta().getId());

			int rowsAffected = st.executeUpdate();
			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					prontuario.setId(id);
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
			Prontuario prontuario = (Prontuario) obj;

			st = conn.prepareStatement(
					"UPDATE prontuario SET pro_observacao = ?, pro_procedimento = ?, pro_exames = ?, pro_evolucao = ?, pro_con_id = ? "
							+ "WHERE pro_id = ?");

			st.setString(1, prontuario.getObservacao());
			st.setString(2, prontuario.getProcedimento());
			st.setString(3, prontuario.getExames());
			st.setString(4, prontuario.getEvolucao());
			st.setInt(5, prontuario.getConsulta().getId());
			st.setInt(6, prontuario.getId());

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
			st = conn.prepareStatement("DELETE FROM prontuario WHERE pro_id = ?");
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
			st = conn.prepareStatement("SELECT p.pro_id, p.pro_observacoes, p.pro_procedimentos, "
					+ "p.pro_exames, p.pro_evolucao, p.pro_con_id, "
					+ "c.con_id, c.con_data, c.con_hora, c.con_diagnostico, "
					+ "pet.pet_id, pet.pet_nome, pet.pet_especie, pet.pet_genero, pet.pet_idade, "
					+ "t.ttr_id, t.ttr_nome, t.ttr_cpf, t.ttr_nascimento, "
					+ "vet.vet_id, vet.vet_nome, vet.vet_crm, vet.vet_especialidade " + "FROM prontuario p "
					+ "INNER JOIN consulta c ON p.pro_con_id = c.con_id "
					+ "INNER JOIN pet ON c.con_pet_id = pet.pet_id "
					+ "INNER JOIN tutor t ON pet.pet_ttr_id = t.ttr_id "
					+ "INNER JOIN veterinario vet ON c.con_vet_id = vet.vet_id " + "WHERE p.pro_id = ?");

			st.setInt(1, id);
			rs = st.executeQuery();

			if (rs.next()) {
				Tutor tutor = instanciaTutor(rs);
				Pet pet = instanciaPet(rs, tutor);
				Veterinario vet = instanciaVeterinario(rs);
				Consulta con = instanciaConsulta(rs, vet, pet);

				Prontuario prontuario = instanciaProntuario(rs, con);
				return prontuario;
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
			st = conn.prepareStatement("SELECT p.pro_id, p.pro_observacoes, p.pro_procedimentos, "
					+ "p.pro_exames, p.pro_evolucao, p.pro_con_id, "
					+ "c.con_id, c.con_data, c.con_hora, c.con_diagnostico, "
					+ "pet.pet_id, pet.pet_nome, pet.pet_especie, pet.pet_genero, pet.pet_idade, "
					+ "t.ttr_id, t.ttr_nome, t.ttr_cpf, t.ttr_nascimento, "
					+ "vet.vet_id, vet.vet_nome, vet.vet_crm, vet.vet_especialidade " + "FROM prontuario p "
					+ "INNER JOIN consulta c ON p.pro_con_id = c.con_id "
					+ "INNER JOIN pet ON c.con_pet_id = pet.pet_id "
					+ "INNER JOIN tutor t ON pet.pet_ttr_id = t.ttr_id "
					+ "INNER JOIN veterinario vet ON c.con_vet_id = vet.vet_id " + "ORDER BY p.pro_id");

			rs = st.executeQuery();
			List<EntidadeDominio> list = new ArrayList<>();

			while (rs.next()) {

				Tutor tutor = instanciaTutor(rs);

				Pet pet = instanciaPet(rs, tutor);

				Veterinario vet = instanciaVeterinario(rs);

				Consulta consulta = instanciaConsulta(rs, vet, pet);

				Prontuario prontuario = instanciaProntuario(rs, consulta);

				list.add(prontuario);
			}
			return list;
		} catch (SQLException e) {
			throw new DbException("Erro ao consultar prontuários: " + e.getMessage());
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

		java.sql.Date dataNascimento = rs.getDate("ttr_nascimento");
		tutor.setDataNascimento(dataNascimento != null ? dataNascimento.toLocalDate() : null);

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

	private Consulta instanciaConsulta(ResultSet rs, Veterinario vet, Pet pet) throws SQLException {
		Consulta consulta = new Consulta();
		consulta.setId(rs.getInt("con_id"));
		consulta.setData(rs.getDate("con_data").toLocalDate());
		consulta.setHora(rs.getTime("con_hora").toLocalTime());
		consulta.setDiagnostico(rs.getString("con_diagnostico"));
		consulta.setVeterionario(vet);
		consulta.setPet(pet);
		return consulta;
	}

	private Prontuario instanciaProntuario(ResultSet rs, Consulta consulta) throws SQLException {
		Prontuario prontuario = new Prontuario();
		prontuario.setId(rs.getInt("pro_id"));
		prontuario.setObservacao(rs.getString("pro_observacoes"));
		prontuario.setProcedimento(rs.getString("pro_procedimentos"));
		prontuario.setExames(rs.getString("pro_exames"));
		prontuario.setEvolucao(rs.getString("pro_evolucao"));
		prontuario.setConsulta(consulta);
		return prontuario;
	}
}
