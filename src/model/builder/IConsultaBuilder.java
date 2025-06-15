package model.builder;

import java.time.LocalDate;
import java.time.LocalTime;

import model.entities.Consulta;
import model.entities.Pet;
import model.entities.Veterinario;

public interface IConsultaBuilder {
	public void buildId(int id);
	public void buildData(LocalDate data);
	public void buildHora(LocalTime time);
	public void buildDiagnostico(String diagnostico);
	public void buildPet(Pet pet);
	public void buildVeterinario(Veterinario veterinario);
	
	Consulta buildConsulta();
}
