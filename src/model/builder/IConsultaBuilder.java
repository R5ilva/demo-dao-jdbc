package model.builder;

import java.time.LocalDate;
import java.time.LocalTime;

import model.entity.Consulta;
import model.entity.Pet;
import model.entity.Veterinario;

public interface IConsultaBuilder {
	public void buildId(Integer id);
	public void buildData(LocalDate data);
	public void buildHora(LocalTime time);
	public void buildDiagnostico(String diagnostico);
	public void buildPet(Pet pet);
	public void buildVeterinario(Veterinario veterinario);
	
	Consulta buildConsulta();
}
