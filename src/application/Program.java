package application;

import java.time.LocalDate;

import model.entities.Tutor;

public class Program {

	public static void main(String[] args) {
		Tutor obj = new Tutor(1, "Rafael", "233.063.938-43",LocalDate.of(1995, 6, 25));
		
		System.out.println(obj);

	}

}
