package model.entity;

import java.util.Objects;

public class Medicamento extends EntidadeDominio {
	private Integer id;
	private String nome;
	
	public Medicamento() {}

	public Medicamento(Integer id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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
		Medicamento other = (Medicamento) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Medicamento [id=" + id + ", nome=" + nome + "]";
	}
}
