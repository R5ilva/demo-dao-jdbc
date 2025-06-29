package controller;

import java.util.ArrayList;
import java.util.List;

import model.entity.Consulta;
import model.entity.EntidadeDominio;
import model.entity.Prontuario;

public class ProntuarioController extends Controller {
    
    public Prontuario criarProntuario(Consulta consulta, String observacao, 
                                    String procedimento, String exames, String evolucao) {
        try {
            Prontuario prontuario = getFacade().criarProntuario(null, consulta, observacao, 
                                                               procedimento, exames, evolucao);
            getFacade().salvarProntuario(prontuario);
            return prontuario;
        } catch (Exception e) {
            System.err.println("Erro ao criar prontuário: " + e.getMessage());
            return null;
        }
    }
    
    public List<Prontuario> listarProntuarios() {
    	List<EntidadeDominio> entidades = facade.listarTodosProntuarios();
        List<Prontuario> prontuarios = new ArrayList<>();
        
        for (EntidadeDominio entidade : entidades) {
            if (entidade instanceof Prontuario) {
                prontuarios.add((Prontuario) entidade);
            }
        }
        return prontuarios;
    }
    
    public Prontuario buscarProntuarioPorId(Integer id) {
        return getFacade().buscarProntuarioPorId(id);
    }
    
    public void atualizarProntuario(Prontuario prontuario) {
        getFacade().salvarProntuario(prontuario);
    }
    
    public void removerProntuario(Integer id) {
        getFacade().excluirProntuario(id);
    }
}