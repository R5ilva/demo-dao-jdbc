package application;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

import model.builder.ConsultaBuilder;
import model.builder.DiretorConsulta;
import model.builder.DiretorProntuario;
import model.builder.ProntuarioBuilder;
import model.dao.DaoFactory;
import model.dao.IDao;
import model.dao.TutorDaoJDBC;
import model.entity.Consulta;
import model.entity.EntidadeDominio;
import model.entity.Medicacao;
import model.entity.Medicamento;
import model.entity.Pet;
import model.entity.Prontuario;
import model.entity.Tutor;
import model.entity.Veterinario;

public class Program {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		Tutor obj = new Tutor(1, "Rafael", "233.063.938-43",LocalDate.of(1995, 6, 25));
		
		Pet pet = new Pet(2, "Preto","Gato","Macho",1,obj);
		
		Veterinario vet = new Veterinario(2,"Pedro","CRM12354","Cirurgiao");
		
		Medicamento med = new Medicamento(1, "Antibiotico");
		
		Consulta con = new Consulta(2, LocalDate.of(2025, 6, 14), LocalTime.of(14, 00), "Infeccao de ouvido", pet, vet);
		
		Medicacao  mdc = new Medicacao(2,"Anti-inflamatorio por 10 dias, 1x ao dia",med,con);
		
		Prontuario pro = new Prontuario(1,con,"Animal apresentou coceira e vermelhidao no ouvido direito","Limpeza do ouvido, aplicacao de medicamento topico", "Exame otoscopico","Melhora apos 7 dias");
		
		ConsultaBuilder cnb = new ConsultaBuilder();
		DiretorConsulta dca = new DiretorConsulta(cnb);
		dca.buildConsulta(2, LocalDate.of(2025, 6, 14), LocalTime.of(16, 50), "Teste Build", pet, vet);
		
		con = cnb.buildConsulta();
		
		ProntuarioBuilder construtorProntuario = new ProntuarioBuilder();
		DiretorProntuario diretorProntuario = new DiretorProntuario(construtorProntuario);
		diretorProntuario.buildProntuario(2, con, "Teste Obs", "Teste Proc", "Teste Exam", "Teste Evo");
		
		pro = construtorProntuario.buildProntuario();
		
		IDao petDao = DaoFactory.createPetDao();
		
		IDao tutorDao = DaoFactory.createTutorDao();
		EntidadeDominio tutor =  tutorDao.findById(2);
		
		System.out.println("\n === Teste findAll ====");
		
		List<EntidadeDominio> list = tutorDao.findAll();
		for (EntidadeDominio tut:list) {
			System.out.println(tut);
		}
		
		/*System.out.println(tutor);
		Tutor tu = new Tutor(null,"Rafael","1234657878",LocalDate.of(1989, 11, 8));
		tutorDao.insert(tu);
		System.out.println("Inserted! New id ="+ tu.getId());*/
		
		/*tutor = tutorDao.findById(6);
		((Tutor) tutor).setNome("Roberto");
		tutorDao.update(tutor);
		System.out.println("Update completed");*/
		
		System.out.println("Informe o id do tutor");
		int id = sc.nextInt();
		tutorDao.deleteById(id);
		System.out.println("Delete Completo");
		sc.close();
	}

}
