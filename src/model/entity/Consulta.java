package model.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class Consulta extends EntidadeDominio {
	private Integer id;
	private LocalDate data;
	private LocalTime hora;
	private String diagnostico;
	
	private Pet pet;
	private Veterinario veterionario;
	
	public Consulta() {}

	public Consulta(Integer id, LocalDate data, LocalTime hora, String diagnostico, Pet pet, Veterinario veterionario) {
		super();
		this.id = id;
		this.data = data;
		this.hora = hora;
		this.diagnostico = diagnostico;
		this.pet = pet;
		this.veterionario = veterionario;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public LocalTime getHora() {
		return hora;
	}

	public void setHora(LocalTime hora) {
		this.hora = hora;
	}

	public String getDiagnostico() {
		return diagnostico;
	}

	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}

	public Pet getPet() {
		return pet;
	}

	public void setPet(Pet pet) {
		this.pet = pet;
	}

	public Veterinario getVeterionario() {
		return veterionario;
	}

	public void setVeterionario(Veterinario veterionario) {
		this.veterionario = veterionario;
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
		Consulta other = (Consulta) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Consulta [id=" + id + ", data=" + data + ", hora=" + hora + ", diagnostico=" + diagnostico + ", pet="
				+ pet + ", veterionario=" + veterionario + "]";
	}
}
