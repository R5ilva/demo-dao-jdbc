package model.builder;

import java.time.LocalDate;
import java.time.LocalTime;

import model.entities.Consulta;
import model.entities.Pet;
import model.entities.Veterinario;

public class ConsultaBuilder implements IConsultaBuilder{
	
	private Consulta consulta = new Consulta();
	
	@Override
	public void buildId(int id) {
		consulta.setId(id);
		
	}
	
	@Override
	public void buildData(LocalDate data) {
		consulta.setData(data);
		
	}

	@Override
	public void buildHora(LocalTime time) {
		consulta.setHora(time);
		
	}

	@Override
	public void buildDiagnostico(String diagnostico) {
		consulta.setDiagnostico(diagnostico);
		
	}

	@Override
	public void buildPet(Pet pet) {
		consulta.setPet(pet);
		
	}

	@Override
	public void buildVeterinario(Veterinario veterinario) {
		consulta.setVeterionario(veterinario);
		
	}

	@Override
	public Consulta buildConsulta() {
		return consulta;
	}

}
