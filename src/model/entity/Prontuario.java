package model.entity;

import java.util.Objects;

public class Prontuario extends EntidadeDominio {
	private Integer id;
	private Consulta consulta;
	private String observacao;
	private String procedimento;
	private String exames;
	private String evolucao;

	public Prontuario() {
	}

	public Prontuario(Integer id, Consulta consulta, String observacao, String procedimento, String exames,
			String evolucao) {
		super();
		this.id = id;
		this.consulta = consulta;
		this.observacao = observacao;
		this.procedimento = procedimento;
		this.exames = exames;
		this.evolucao = evolucao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Consulta getConsulta() {
		return consulta;
	}

	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}
	
	

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getProcedimento() {
		return procedimento;
	}

	public void setProcedimento(String procedimento) {
		this.procedimento = procedimento;
	}

	public String getExames() {
		return exames;
	}

	public void setExames(String exames) {
		this.exames = exames;
	}

	public String getEvolucao() {
		return evolucao;
	}

	public void setEvolucao(String evolucao) {
		this.evolucao = evolucao;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Prontuario other = (Prontuario) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Prontuario [id=" + id + ", consulta=" + consulta + ", observacao=" + observacao + ", procedimento="
				+ procedimento + ", exames=" + exames + ", evolucao=" + evolucao + "]";
	}

	

}
