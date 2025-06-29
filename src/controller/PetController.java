package controller;

import model.entity.EntidadeDominio;
import model.entity.Pet;
import model.entity.Tutor;

import java.util.ArrayList;
import java.util.List;

public class PetController extends Controller {
    
    public Pet cadastrarPet(String nome, String especie, String genero, int idade, Tutor tutor) {
        try {
            Pet pet = new Pet(null, nome, especie, genero, idade, tutor);
            facade.salvarPet(pet);
            return pet;
        } catch (Exception e) {
            System.err.println("Erro ao cadastrar pet: " + e.getMessage());
            return null;
        }
    }
    
    public List<Pet> listarPets() {
    	List<EntidadeDominio> entidades = facade.listarTodosPets();
        List<Pet> pets = new ArrayList<>();
        
        for (EntidadeDominio entidade : entidades) {
            if (entidade instanceof Pet) {
                pets.add((Pet) entidade);
            }
        }
        return pets;
    }
    
    public Pet buscarPetPorId(Integer id) {
        return facade.buscarPetPorId(id);
    }
    
    public void atualizarPet(Pet pet) {
        facade.salvarPet(pet);
    }
    
    public void removerPet(Integer id) {
        facade.excluirPet(id);
    }
}