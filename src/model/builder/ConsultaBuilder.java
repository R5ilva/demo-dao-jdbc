package model.builder;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

import model.entity.Consulta;
import model.entity.Pet;
import model.entity.Veterinario;

public class ConsultaBuilder implements IConsultaBuilder{
	
	private Consulta consulta = new Consulta();
	
	@Override
	public void buildId(Integer id) {
		consulta.setId(id);
		
	}
	
	@Override
	public void buildData(LocalDate data) {
	    Objects.requireNonNull(data, "Data da consulta não pode ser nula");
        consulta.setData(data);
        System.out.println("[BUILDER] Data definida: " + data); // Log adicional
		
	}

	@Override
	public void buildHora(LocalTime time) {
		 if(time == null) {
		        throw new IllegalArgumentException("Horário não pode ser nulo");
		    }
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
