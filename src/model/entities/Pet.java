package model.entities;

import java.util.Objects;

public class Pet {
	private Integer id;
	private String nome;
	private String especie;
	private String genero;
	private int idade;
	
	private Tutor tutor;
	
	public Pet (){}
	
	public Pet(Integer id, String nome, String especie, String genero, int idade, Tutor tutor) {
		this.id = id;
		this.nome = nome;
		this.especie = especie;
		this.genero = genero;
		this.idade = idade;
		this.tutor = tutor;
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

	public String getEspecie() {
		return especie;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public Tutor getTutor() {
		return tutor;
	}

	public void setTutor(Tutor tutor) {
		this.tutor = tutor;
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
		Pet other = (Pet) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Pet [id=" + id + ", nome=" + nome + ", especie=" + especie + ", genero=" + genero + ", idade=" + idade
				+ ", tutor=" + tutor + "]";
	}
}
