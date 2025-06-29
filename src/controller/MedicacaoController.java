package controller;

import java.util.ArrayList;
import java.util.List;

import model.entity.Consulta;
import model.entity.EntidadeDominio;
import model.entity.Medicacao;
import model.entity.Medicamento;

public class MedicacaoController extends Controller {
    
    public Medicacao cadastrarMedicacao(String prescricao, Medicamento medicamento, Consulta consulta) {
        try {
            Medicacao medicacao = new Medicacao();
            medicacao.setPrescricao(prescricao);
            medicacao.setMedicamento(medicamento);
            medicacao.setConsulta(consulta);
            
            getFacade().salvarMedicacao(medicacao);
            return medicacao;
        } catch (Exception e) {
            System.err.println("Erro ao cadastrar medicação: " + e.getMessage());
            return null;
        }
    }
    
    public List<Medicacao> listarMedicacoes() {
    	List<EntidadeDominio> entidades = facade.listarTodasMedicacoes();
        List<Medicacao> medicacoes = new ArrayList<>();
        
        for (EntidadeDominio entidade : entidades) {
            if (entidade instanceof Medicacao) {
                medicacoes.add((Medicacao) entidade);
            }
        }
        return medicacoes;
    }
    
    public Medicacao buscarMedicacaoPorId(Integer id) {
        return getFacade().buscarMedicacaoPorId(id);
    }
    
    
    public void atualizarMedicacao(Medicacao medicacao) {
        getFacade().salvarMedicacao(medicacao);
    }
    
    public void removerMedicacao(Integer id) {
        getFacade().excluirMedicacao(id);
    }
}