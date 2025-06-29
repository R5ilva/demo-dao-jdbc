package model.dao;

import db.DB;

public class DaoFactory {
	public static IDao createPetDao() {
		return new PetDaoJDBC(DB.getConnection());
	}
	
	public static IDao createTutorDao() {
		return new TutorDaoJDBC(DB.getConnection());
	}
	
	public static IDao createVeterinarioDao() {
		return new VeterinarioDaoJDBC(DB.getConnection());
	}
	
	public static IDao createConsultaDao() {
		return new ConsultaDaoJDBC(DB.getConnection());
	}
	
	public static IDao createMedicamentoDao() {
		return new MedicamentoDaoJDBC(DB.getConnection());
	}
	
	public static IDao createMedicacaoDao() {
		return new MedicacaoDaoJDBC(DB.getConnection());
	}
	
	public static IDao createProntuarioDao() {
		return new ProntuarioDaoJDBC(DB.getConnection());
	}
	
}
