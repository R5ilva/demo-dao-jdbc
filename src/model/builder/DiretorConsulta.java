package model.builder;

import java.time.LocalDate;
import java.time.LocalTime;

import model.entity.Pet;
import model.entity.Veterinario;

public class DiretorConsulta {
	private IConsultaBuilder consultaBuilder;
	
	public DiretorConsulta(IConsultaBuilder consultaBuilder) {
		this.consultaBuilder = consultaBuilder;
	}
	
	public void buildConsulta(Integer id, LocalDate data, LocalTime time, String diagnostico, Pet pet, Veterinario veterinario) {
		consultaBuilder.buildId(id);
		consultaBuilder.buildData(data);
		consultaBuilder.buildHora(time);
		consultaBuilder.buildDiagnostico(diagnostico);
		consultaBuilder.buildPet(pet);
		consultaBuilder.buildVeterinario(veterinario);	
	}
	
}
