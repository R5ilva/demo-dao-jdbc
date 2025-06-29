package controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import model.entity.Consulta;
import model.entity.EntidadeDominio;
import model.entity.Pet;
import model.entity.Veterinario;

public class ConsultaController extends Controller {
    
    public Consulta agendarConsulta(LocalDate data, LocalTime hora, String diagnostico, 
                                  Pet pet, Veterinario veterinario) {
    	try {
            if(data == null) {
                throw new IllegalArgumentException("Data da consulta não pode ser nula");
            }
            if(hora == null) {
                throw new IllegalArgumentException("Horário da consulta não pode ser nulo");
            }
            if(pet == null || veterinario == null) {
                throw new IllegalArgumentException("Pet e Veterinário são obrigatórios");
            }
            
            Consulta consulta = facade.criarConsulta(null, data, hora, diagnostico, pet, veterinario);
            facade.salvarConsulta(consulta);
            return consulta;
        } catch (Exception e) {
            System.err.println("Erro ao agendar consulta: " + e.getMessage());
            return null;
        }
    }
    
    public List<Consulta> listarConsultas() {
    	List<EntidadeDominio> entidades = facade.listarTodasConsultas();
        List<Consulta> consultas = new ArrayList<>();
        
        for (EntidadeDominio entidade : entidades) {
            if (entidade instanceof Consulta) {
                consultas.add((Consulta) entidade);
            }
        }
        return consultas;
    }
    
    public Consulta buscarConsultaPorId(Integer id) {
        return facade.buscarConsultaPorId(id);
    }
    
    public void atualizarConsulta(Consulta consulta) {
        facade.salvarConsulta(consulta);
    }
    
    public void removerConsulta(Integer id) {
        facade.excluirConsulta(id);
    }
}