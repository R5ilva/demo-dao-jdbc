package controller;

import model.entity.EntidadeDominio;
import model.entity.Tutor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TutorController extends Controller {
    
    public Tutor cadastrarTutor(String nome, String cpf, LocalDate dataNascimento) {
        try {
            Tutor tutor = new Tutor(null, nome, cpf, dataNascimento);
            facade.salvarTutor(tutor);
            return tutor;
        } catch (Exception e) {
            System.err.println("Erro ao cadastrar tutor: " + e.getMessage());
            return null;
        }
    }
    
    public List<Tutor> listarTutores() {
    	List<EntidadeDominio> entidades = facade.listarTodosTutores();
        List<Tutor> tutores = new ArrayList<>();
        
        for (EntidadeDominio entidade : entidades) {
            if (entidade instanceof Tutor) {
                tutores.add((Tutor) entidade);
            }
        }
        return tutores;
    }
    
    public Tutor buscarTutorPorId(Integer id) {
        return facade.buscarTutorPorId(id);
    }
    
    public void atualizarTutor(Tutor tutor) {
        facade.salvarTutor(tutor);
    }
    
    public void removerTutor(Integer id) {
        facade.excluirTutor(id);
    }
}