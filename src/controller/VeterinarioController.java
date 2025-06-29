package controller;

import java.util.ArrayList;
import java.util.List;

import model.entity.EntidadeDominio;
import model.entity.Veterinario;

public class VeterinarioController extends Controller {
    
    public Veterinario cadastrarVeterinario(String nome, String crm, String especialidade) {
        try {
            Veterinario vet = new Veterinario(null, nome, crm, especialidade);
            facade.salvarVeterinario(vet);
            return vet;
        } catch (Exception e) {
            System.err.println("Erro ao cadastrar veterinário: " + e.getMessage());
            return null;
        }
    }
    
    public List<Veterinario> listarVeterinarios() {
    	List<EntidadeDominio> entidades = facade.listarTodosVeterinarios();
        List<Veterinario> veterinarios = new ArrayList<>();
        
        for (EntidadeDominio entidade : entidades) {
            if (entidade instanceof Veterinario) {
                veterinarios.add((Veterinario) entidade);
            }
        }
        return veterinarios;
    }
    
    public Veterinario buscarVeterinarioPorId(Integer id) {
        return facade.buscarVeterinarioPorId(id);
    }
    
    public void atualizarVeterinario(Veterinario vet) {
        facade.salvarVeterinario(vet);
    }
    
    public void removerVeterinario(Integer id) {
        facade.excluirVeterinario(id);
    }
}