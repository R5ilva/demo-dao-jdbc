package application;

import java.time.LocalDate;
import java.time.LocalTime;

import model.entities.Consulta;
import model.entities.Medicacao;
import model.entities.Medicamento;
import model.entities.Pet;
import model.entities.Tutor;
import model.entities.Veterinario;

public class Program {

	public static void main(String[] args) {
		Tutor obj = new Tutor(1, "Rafael", "233.063.938-43",LocalDate.of(1995, 6, 25));
		
		Pet pet = new Pet(2, "Preto","Gato","Macho",1,obj);
		
		Veterinario vet = new Veterinario(2,"Pedro","CRM12354","Cirurgiao");
		
		Medicamento med = new Medicamento(1, "Antibiotico");
		
		Consulta con = new Consulta(2, LocalDate.of(2025, 6, 14), LocalTime.of(14, 00), "Infeccao de ouvido", pet, vet);
		
		Medicacao  mdc = new Medicacao(2,"Anti-inflamatorio por 10 dias, 1x ao dia",med,con);
		
		System.out.println(mdc);

	}

}
