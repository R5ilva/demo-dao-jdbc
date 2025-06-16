package model.entity;

import java.util.Objects;

public class Medicacao extends EntidadeDominio {
	private Integer id;
	private String prescricao;
	private Medicamento medicamento;
	private Consulta consulta;
	
	public Medicacao() {}

	public Medicacao(Integer id, String prescricao, Medicamento medicamento, Consulta consulta) {
		super();
		this.id = id;
		this.prescricao = prescricao;
		this.medicamento = medicamento;
		this.consulta = consulta;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPrescricao() {
		return prescricao;
	}

	public void setPrescricao(String prescricao) {
		this.prescricao = prescricao;
	}

	public Medicamento getMedicamento() {
		return medicamento;
	}

	public void setMedicamento(Medicamento medicamento) {
		this.medicamento = medicamento;
	}

	public Consulta getConsulta() {
		return consulta;
	}

	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
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
		Medicacao other = (Medicacao) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Medicacao [id=" + id + ", prescricao=" + prescricao + ", medicamento=" + medicamento + ", consulta="
				+ consulta + "]";
	}
}
