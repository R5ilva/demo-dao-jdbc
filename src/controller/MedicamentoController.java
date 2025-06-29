package controller;

import java.util.ArrayList;
import java.util.List;

import model.entity.EntidadeDominio;
import model.entity.Medicamento;

public class MedicamentoController extends Controller {
    
    public Medicamento cadastrarMedicamento(String nome) {
        try {
            Medicamento medicamento = new Medicamento(null, nome);
            getFacade().salvarMedicamento(medicamento);
            return medicamento;
        } catch (Exception e) {
            System.err.println("Erro ao cadastrar medicamento: " + e.getMessage());
            return null;
        }
    }
    
    public List<Medicamento> listarMedicamentos() {
    	List<EntidadeDominio> entidades = facade.listarTodosMedicamentos();
        List<Medicamento> medicamentos = new ArrayList<>();
        
        for (EntidadeDominio entidade : entidades) {
            if (entidade instanceof Medicamento) {
            	medicamentos.add((Medicamento) entidade);
            }
        }
        return medicamentos;
    }
    
    public Medicamento buscarMedicamentoPorId(Integer id) {
        return getFacade().buscarMedicamentoPorId(id);
    }
    
    public void atualizarMedicamento(Medicamento medicamento) {
        getFacade().salvarMedicamento(medicamento);
    }
    
    public void removerMedicamento(Integer id) {
        getFacade().excluirMedicamento(id);
    }
}