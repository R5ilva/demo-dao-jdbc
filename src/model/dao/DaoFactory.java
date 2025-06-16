package model.dao;

import db.DB;

public class DaoFactory {
	public static IDao createPetDao() {
		return new PetDaoJDBC();
	}
	
	public static IDao createTutorDao() {
		return new TutorDaoJDBC(DB.getConnection());
	}
	
	public static IDao createVeterinarioDao() {
		return new VeterinarioDaoJDBC();
	}
	
	public static IDao createConsultaDao() {
		return new ConsultaDaoJDBC();
	}
	
	public static IDao createMedicamentoDao() {
		return new MedicamentoDaoJDBC();
	}
	
	public static IDao createMedicacaoDao() {
		return new MedicacaoDaoJDBC();
	}
	
	public static IDao createProntuarioDao() {
		return new ProntuarioDaoJDBC();
	}
	
}
