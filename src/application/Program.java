package application;

import java.time.LocalDate;

import model.entities.Pet;
import model.entities.Tutor;

public class Program {

	public static void main(String[] args) {
		Tutor obj = new Tutor(1, "Rafael", "233.063.938-43",LocalDate.of(1995, 6, 25));
		
		Pet pet = new Pet(2, "Preto","Gato","Macho",1,obj);
		
		System.out.println(pet);

	}

}
