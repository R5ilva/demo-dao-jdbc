package model.facade;

import model.builder.DiretorConsulta;
import model.builder.DiretorProntuario;
import model.builder.IConsultaBuilder;
import model.builder.IProntuarioBuilder;
import model.builder.ConsultaBuilder;
import model.builder.ProntuarioBuilder;
import model.dao.*;
import model.entity.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class SistemaVeterinarioFacade {

    private IDao tutorDao;
    private IDao petDao;
    private IDao veterinarioDao;
    private IDao consultaDao;
    private IDao medicamentoDao;
    private IDao medicacaoDao;
    private IDao prontuarioDao;
    
    private IProntuarioBuilder prontuarioBuilder;
    private DiretorConsulta diretorConsulta;
    private DiretorProntuario diretorProntuario;
    
    public SistemaVeterinarioFacade() {

        this.tutorDao = DaoFactory.createTutorDao();
        this.petDao = DaoFactory.createPetDao();
        this.veterinarioDao = DaoFactory.createVeterinarioDao();
        this.consultaDao = DaoFactory.createConsultaDao();
        this.medicamentoDao = DaoFactory.createMedicamentoDao();
        this.medicacaoDao = DaoFactory.createMedicacaoDao();
        this.prontuarioDao = DaoFactory.createProntuarioDao();
        

        IConsultaBuilder consultaBuilder = new ConsultaBuilder();
        this.diretorConsulta = new DiretorConsulta(consultaBuilder);
        
        IProntuarioBuilder prontuarioBuilder = new ProntuarioBuilder();
        this.diretorProntuario = new DiretorProntuario(prontuarioBuilder);
        
        this.prontuarioBuilder = new ProntuarioBuilder();
    }
    

    public void salvarTutor(Tutor tutor) {
        if (tutor.getId() == null) {
            tutorDao.insert(tutor);
        } else {
            tutorDao.update(tutor);
        }
    }
    
    public Tutor buscarTutorPorId(Integer id) {
        return (Tutor) tutorDao.findById(id);
    }
    
    public List<EntidadeDominio> listarTodosTutores() {
        return tutorDao.findAll();
    }
    
    public void excluirTutor(Integer id) {
        tutorDao.deleteById(id);
    }
    

    public void salvarPet(Pet pet) {
        if (pet.getId() == null) {
            petDao.insert(pet);
        } else {
            petDao.update(pet);
        }
    }
    
    public Pet buscarPetPorId(Integer id) {
        return (Pet) petDao.findById(id);
    }
    
    public List<EntidadeDominio> listarTodosPets() {
        return petDao.findAll();
    }
    
    public void excluirPet(Integer id) {
        petDao.deleteById(id);
    }
    

    public void salvarVeterinario(Veterinario veterinario) {
        if (veterinario.getId() == null) {
            veterinarioDao.insert(veterinario);
        } else {
            veterinarioDao.update(veterinario);
        }
    }
    
    public Veterinario buscarVeterinarioPorId(Integer id) {
        return (Veterinario) veterinarioDao.findById(id);
    }
    
    public List<EntidadeDominio> listarTodosVeterinarios() {
        return veterinarioDao.findAll();
    }
    
    public void excluirVeterinario(Integer id) {
        veterinarioDao.deleteById(id);
    }
    

    public Consulta criarConsulta(Integer id, LocalDate data, LocalTime hora, String diagnostico, 
                                Pet pet, Veterinario veterinario) {
    	 System.out.println("[FACADE] Criando consulta com data: " + data);
        Consulta consulta = new Consulta();
        diretorConsulta.buildConsulta(id, data, hora, diagnostico, pet, veterinario);
        return consulta;
    }
    
    public void salvarConsulta(Consulta consulta) {
        if (consulta.getId() == null) {
            consultaDao.insert(consulta);
        } else {
            consultaDao.update(consulta);
        }
    }
    
    public Consulta buscarConsultaPorId(Integer id) {
        return (Consulta) consultaDao.findById(id);
    }
    
    public List<EntidadeDominio> listarTodasConsultas() {
        return consultaDao.findAll();
    }
    
    public void excluirConsulta(Integer id) {
        consultaDao.deleteById(id);
    }
    

    public void salvarMedicamento(Medicamento medicamento) {
        if (medicamento.getId() == null) {
            medicamentoDao.insert(medicamento);
        } else {
            medicamentoDao.update(medicamento);
        }
    }
    
    public Medicamento buscarMedicamentoPorId(Integer id) {
        return (Medicamento) medicamentoDao.findById(id);
    }
    
    public List<EntidadeDominio> listarTodosMedicamentos() {
        return medicamentoDao.findAll();
    }
    
    public void excluirMedicamento(Integer id) {
        medicamentoDao.deleteById(id);
    }
    

    public void salvarMedicacao(Medicacao medicacao) {
        if (medicacao.getId() == null) {
            medicacaoDao.insert(medicacao);
        } else {
            medicacaoDao.update(medicacao);
        }
    }
    
    public Medicacao buscarMedicacaoPorId(Integer id) {
        return (Medicacao) medicacaoDao.findById(id);
    }
    
    public List<EntidadeDominio> listarTodasMedicacoes() {
        return medicacaoDao.findAll();
    }
    
    public void excluirMedicacao(Integer id) {
        medicacaoDao.deleteById(id);
    }
    

    public Prontuario criarProntuario(Integer id, Consulta consulta, String observacao, 
                                    String procedimento, String exames, String evolucao) {
        diretorProntuario.buildProntuario(id, consulta, observacao, procedimento, exames, evolucao);
        return prontuarioBuilder.buildProntuario();
    }
    
    public void salvarProntuario(Prontuario prontuario) {
        if (prontuario.getId() == null) {
            prontuarioDao.insert(prontuario);
        } else {
            prontuarioDao.update(prontuario);
        }
    }
    
    public Prontuario buscarProntuarioPorId(Integer id) {
        return (Prontuario) prontuarioDao.findById(id);
    }
    
    public List<EntidadeDominio> listarTodosProntuarios() {
        return prontuarioDao.findAll();
    }
    
    public void excluirProntuario(Integer id) {
        prontuarioDao.deleteById(id);
    }
}