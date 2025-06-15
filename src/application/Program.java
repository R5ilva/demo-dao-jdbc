package application;

import java.time.LocalDate;

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
		System.out.println(med);

	}

}
