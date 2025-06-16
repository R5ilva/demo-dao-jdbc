package model.entity;

import java.time.LocalDate;
import java.util.Objects;

public class Tutor extends EntidadeDominio {
	
	private Integer id;
	private String nome;
	private String cpf;
	private LocalDate dataNascimento;
	
	public Tutor() {}

	public Tutor(Integer id, String nome, String cpf, LocalDate dataNascimento) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
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
		Tutor other = (Tutor) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Tutor [id=" + id + ","
				   + "nome=" + nome + ", "
				   + "cpf=" + cpf + ", "
				   + "dataNascimento=" + dataNascimento + "]";
	}
	
}
