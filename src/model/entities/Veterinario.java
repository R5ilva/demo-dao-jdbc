package model.entities;

import java.util.Objects;

public class Veterinario {
	private Integer id;
	private String nome;
	private String crm;
	private String especialidade;
	
	public Veterinario() {}

	public Veterinario(Integer id, String nome, String crm, String especialidade) {
		super();
		this.id = id;
		this.nome = nome;
		this.crm = crm;
		this.especialidade = especialidade;
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
		Veterinario other = (Veterinario) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Veterinario [id=" + id + ", nome=" + nome + ", crm=" + crm + ", especialidade=" + especialidade + "]";
	}
}
