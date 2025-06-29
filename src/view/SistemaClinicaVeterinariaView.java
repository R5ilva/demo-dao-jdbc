package view;

import controller.*;
import model.entity.*;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

public class SistemaClinicaVeterinariaView {
    private Scanner entrada;
    private TutorController tutorController;
    private PetController petController;
    private VeterinarioController veterinarioController;
    private ConsultaController consultaController;
    private ProntuarioController prontuarioController;
    
    public SistemaClinicaVeterinariaView() {
        this.entrada = new Scanner(System.in);
        this.tutorController = new TutorController();
        this.petController = new PetController();
        this.veterinarioController = new VeterinarioController();
        this.consultaController = new ConsultaController();
        this.prontuarioController = new ProntuarioController();
    }
    
    public void iniciar() {
        boolean executando = true;
        
        while(executando) {
            System.out.println("----<Sistema Clinica Veterinária>----");
            System.out.println("1 - Tutor");
            System.out.println("2 - Pet");
            System.out.println("3 - Veterinário");
            System.out.println("4 - Consulta");
            System.out.println("5 - Prontuário");
            System.out.println("6 - Sair");
            System.out.print("Opção: ");
            
            int opcao = lerInteiro();
            
            switch(opcao) {
                case 1:
                    menuTutor();
                    break;
                case 2:
                    menuPet();
                    break;
                case 3:
                    menuVeterinario();
                    break;
                case 4:
                    menuConsulta();
                    break;
                case 5:
                    menuProntuario();
                    break;
                case 6:
                    executando = false;
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
        
        System.out.println("Sistema encerrado.");
    }
    
    private void menuTutor() {
        boolean voltar = false;
        
        while(!voltar) {
            System.out.println("\n----<Menu Tutor>----");
            System.out.println("1 - Cadastrar Tutor");
            System.out.println("2 - Listar Tutores");
            System.out.println("3 - Buscar Tutor por ID");
            System.out.println("4 - Atualizar Tutor");
            System.out.println("5 - Remover Tutor");
            System.out.println("6 - Voltar");
            System.out.print("Opção: ");
            
            int opcao = lerInteiro();
            
            switch(opcao) {
                case 1:
                    cadastrarTutor();
                    break;
                case 2:
                    listarTutores();
                    break;
                case 3:
                    buscarTutorPorId();
                    break;
                case 4:
                    atualizarTutor();
                    break;
                case 5:
                    removerTutor();
                    break;
                case 6:
                    voltar = true;
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
    
    private void cadastrarTutor() {
        System.out.println("\n----<Cadastro de Tutor>----");
        System.out.print("Nome: ");
        String nome = entrada.nextLine();
        
        System.out.print("CPF: ");
        String cpf = entrada.nextLine();
        
        System.out.println("Data de Nascimento (dd/mm/aaaa):");
        System.out.print("Dia: ");
        int dia = lerInteiro();
        System.out.print("Mês: ");
        int mes = lerInteiro();
        System.out.print("Ano: ");
        int ano = lerInteiro();
        
        LocalDate dataNascimento = LocalDate.of(ano, mes, dia);
        
        Tutor tutor = tutorController.cadastrarTutor(nome, cpf, dataNascimento);
        
        if(tutor != null) {
            System.out.println("Tutor cadastrado com sucesso! ID: " + tutor.getId());
        } else {
            System.out.println("Erro ao cadastrar tutor.");
        }
    }
    
    private void listarTutores() {
        System.out.println("\n----<Lista de Tutores>----");
        List<Tutor> tutores = tutorController.listarTutores();
        
        if(tutores.isEmpty()) {
            System.out.println("Nenhum tutor cadastrado.");
        } else {
            for(Tutor tutor : tutores) {
                System.out.println("ID: " + tutor.getId());
                System.out.println("Nome: " + tutor.getNome());
                System.out.println("CPF: " + tutor.getCpf());
                System.out.println("Data Nascimento: " + tutor.getDataNascimento());
                System.out.println("-----------------------");
            }
        }
    }
    
    private void buscarTutorPorId() {
        System.out.print("\nDigite o ID do tutor: ");
        int id = lerInteiro();
        
        Tutor tutor = tutorController.buscarTutorPorId(id);
        
        if(tutor != null) {
            System.out.println("\n----<Tutor Encontrado>----");
            System.out.println("ID: " + tutor.getId());
            System.out.println("Nome: " + tutor.getNome());
            System.out.println("CPF: " + tutor.getCpf());
            System.out.println("Data Nascimento: " + tutor.getDataNascimento());
        } else {
            System.out.println("Tutor não encontrado.");
        }
    }
    
    private void atualizarTutor() {
        System.out.print("\nDigite o ID do tutor a ser atualizado: ");
        int id = lerInteiro();
        
        Tutor tutor = tutorController.buscarTutorPorId(id);
        
        if(tutor != null) {
            System.out.println("\n----<Atualizar Tutor>----");
            System.out.println("Deixe em branco para manter o valor atual.");
            
            System.out.print("Nome atual: " + tutor.getNome() + "\nNovo nome: ");
            String nome = entrada.nextLine();
            if(!nome.isEmpty()) tutor.setNome(nome);
            
            System.out.print("CPF atual: " + tutor.getCpf() + "\nNovo CPF: ");
            String cpf = entrada.nextLine();
            if(!cpf.isEmpty()) tutor.setCpf(cpf);
            
            System.out.println("Data de Nascimento atual: " + tutor.getDataNascimento());
            System.out.println("Deseja alterar? (S/N)");
            String opcao = entrada.nextLine();
            
            if(opcao.equalsIgnoreCase("S")) {
                System.out.println("Nova data (dd/mm/aaaa):");
                System.out.print("Dia: ");
                int dia = lerInteiro();
                System.out.print("Mês: ");
                int mes = lerInteiro();
                System.out.print("Ano: ");
                int ano = lerInteiro();
                
                tutor.setDataNascimento(LocalDate.of(ano, mes, dia));
            }
            
            tutorController.atualizarTutor(tutor);
            System.out.println("Tutor atualizado com sucesso!");
        } else {
            System.out.println("Tutor não encontrado.");
        }
    }
    
    private void removerTutor() {
        System.out.print("\nDigite o ID do tutor a ser removido: ");
        int id = lerInteiro();
        
        tutorController.removerTutor(id);
        System.out.println("Tutor removido com sucesso!");
    }
    


    private void menuPet() {
        boolean voltar = false;
        
        while(!voltar) {
            System.out.println("\n----<Menu Pet>----");
            System.out.println("1 - Cadastrar Pet");
            System.out.println("2 - Listar Pets");
            System.out.println("3 - Buscar Pet por ID");
            System.out.println("4 - Atualizar Pet");
            System.out.println("5 - Remover Pet");
            System.out.println("6 - Voltar");
            System.out.print("Opção: ");
            
            int opcao = lerInteiro();
            
            switch(opcao) {
                case 1:
                    cadastrarPet();
                    break;
                case 2:
                    listarPets();
                    break;
                case 3:
                    buscarPetPorId();
                    break;
                case 4:
                    atualizarPet();
                    break;
                case 5:
                    removerPet();
                    break;
                case 6:
                    voltar = true;
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
    
    private void cadastrarPet() {
        System.out.println("\n----<Cadastro de Pet>----");
        
 
        listarTutores();
        System.out.print("ID do Tutor: ");
        int tutorId = lerInteiro();
        Tutor tutor = tutorController.buscarTutorPorId(tutorId);
        
        if(tutor == null) {
            System.out.println("Tutor não encontrado!");
            return;
        }
        
        System.out.print("Nome do Pet: ");
        String nome = entrada.nextLine();
        
        System.out.print("Espécie: ");
        String especie = entrada.nextLine();
        
        System.out.print("Gênero: ");
        String genero = entrada.nextLine();
        
        System.out.print("Idade: ");
        int idade = lerInteiro();
        
        Pet pet = petController.cadastrarPet(nome, especie, genero, idade, tutor);
        
        if(pet != null) {
            System.out.println("Pet cadastrado com sucesso! ID: " + pet.getId());
        } else {
            System.out.println("Erro ao cadastrar pet.");
        }
    }
    
    private void listarPets() {
        System.out.println("\n----<Lista de Pets>----");
        List<Pet> pets = petController.listarPets();
        
        if(pets.isEmpty()) {
            System.out.println("Nenhum pet cadastrado.");
        } else {
            for(Pet pet : pets) {
                System.out.println("ID: " + pet.getId());
                System.out.println("Nome: " + pet.getNome());
                System.out.println("Espécie: " + pet.getEspecie());
                System.out.println("Gênero: " + pet.getGenero());
                System.out.println("Idade: " + pet.getIdade());
                System.out.println("Tutor: " + pet.getTutor().getNome());
                System.out.println("-----------------------");
            }
        }
    }
    
    private void buscarPetPorId() {
        System.out.print("\nDigite o ID do pet: ");
        int id = lerInteiro();
        
        Pet pet = petController.buscarPetPorId(id);
        
        if(pet != null) {
            System.out.println("\n----<Pet Encontrado>----");
            System.out.println("ID: " + pet.getId());
            System.out.println("Nome: " + pet.getNome());
            System.out.println("Espécie: " + pet.getEspecie());
            System.out.println("Gênero: " + pet.getGenero());
            System.out.println("Idade: " + pet.getIdade());
            System.out.println("Tutor: " + pet.getTutor().getNome());
        } else {
            System.out.println("Pet não encontrado.");
        }
    }
    
    private void atualizarPet() {
        System.out.print("\nDigite o ID do pet a ser atualizado: ");
        int id = lerInteiro();
        
        Pet pet = petController.buscarPetPorId(id);
        
        if(pet != null) {
            System.out.println("\n----<Atualizar Pet>----");
            System.out.println("Deixe em branco para manter o valor atual.");
            
            System.out.print("Nome atual: " + pet.getNome() + "\nNovo nome: ");
            String nome = entrada.nextLine();
            if(!nome.isEmpty()) pet.setNome(nome);
            
            System.out.print("Espécie atual: " + pet.getEspecie() + "\nNova espécie: ");
            String especie = entrada.nextLine();
            if(!especie.isEmpty()) pet.setEspecie(especie);
            
            System.out.print("Gênero atual: " + pet.getGenero() + "\nNovo gênero: ");
            String genero = entrada.nextLine();
            if(!genero.isEmpty()) pet.setGenero(genero);
            
            System.out.print("Idade atual: " + pet.getIdade() + "\nNova idade: ");
            String idadeStr = entrada.nextLine();
            if(!idadeStr.isEmpty()) pet.setIdade(Integer.parseInt(idadeStr));
            
            petController.atualizarPet(pet);
            System.out.println("Pet atualizado com sucesso!");
        } else {
            System.out.println("Pet não encontrado.");
        }
    }
    
    private void removerPet() {
        System.out.print("\nDigite o ID do pet a ser removido: ");
        int id = lerInteiro();
        
        petController.removerPet(id);
        System.out.println("Pet removido com sucesso!");
    }

    private void menuVeterinario() {
        boolean voltar = false;
        
        while(!voltar) {
            System.out.println("\n----<Menu Veterinário>----");
            System.out.println("1 - Cadastrar Veterinário");
            System.out.println("2 - Listar Veterinários");
            System.out.println("3 - Buscar Veterinário por ID");
            System.out.println("4 - Atualizar Veterinário");
            System.out.println("5 - Remover Veterinário");
            System.out.println("6 - Voltar");
            System.out.print("Opção: ");
            
            int opcao = lerInteiro();
            
            switch(opcao) {
                case 1:
                    cadastrarVeterinario();
                    break;
                case 2:
                    listarVeterinarios();
                    break;
                case 3:
                    buscarVeterinarioPorId();
                    break;
                case 4:
                    atualizarVeterinario();
                    break;
                case 5:
                    removerVeterinario();
                    break;
                case 6:
                    voltar = true;
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
    
    private void cadastrarVeterinario() {
        System.out.println("\n----<Cadastro de Veterinário>----");
        System.out.print("Nome: ");
        String nome = entrada.nextLine();
        
        System.out.print("CRM: ");
        String crm = entrada.nextLine();
        
        System.out.print("Especialidade: ");
        String especialidade = entrada.nextLine();
        
        Veterinario vet = veterinarioController.cadastrarVeterinario(nome, crm, especialidade);
        
        if(vet != null) {
            System.out.println("Veterinário cadastrado com sucesso! ID: " + vet.getId());
        } else {
            System.out.println("Erro ao cadastrar veterinário.");
        }
    }
    
    private void listarVeterinarios() {
        System.out.println("\n----<Lista de Veterinários>----");
        List<Veterinario> veterinarios = veterinarioController.listarVeterinarios();
        
        if(veterinarios.isEmpty()) {
            System.out.println("Nenhum veterinário cadastrado.");
        } else {
            for(Veterinario vet : veterinarios) {
                System.out.println("ID: " + vet.getId());
                System.out.println("Nome: " + vet.getNome());
                System.out.println("CRM: " + vet.getCrm());
                System.out.println("Especialidade: " + vet.getEspecialidade());
                System.out.println("-----------------------");
            }
        }
    }
    
    private void buscarVeterinarioPorId() {
        System.out.print("\nDigite o ID do veterinário: ");
        int id = lerInteiro();
        
        Veterinario vet = veterinarioController.buscarVeterinarioPorId(id);
        
        if(vet != null) {
            System.out.println("\n----<Veterinário Encontrado>----");
            System.out.println("ID: " + vet.getId());
            System.out.println("Nome: " + vet.getNome());
            System.out.println("CRM: " + vet.getCrm());
            System.out.println("Especialidade: " + vet.getEspecialidade());
        } else {
            System.out.println("Veterinário não encontrado.");
        }
    }
    
    private void atualizarVeterinario() {
        System.out.print("\nDigite o ID do veterinário a ser atualizado: ");
        int id = lerInteiro();
        
        Veterinario vet = veterinarioController.buscarVeterinarioPorId(id);
        
        if(vet != null) {
            System.out.println("\n----<Atualizar Veterinário>----");
            System.out.println("Deixe em branco para manter o valor atual.");
            
            System.out.print("Nome atual: " + vet.getNome() + "\nNovo nome: ");
            String nome = entrada.nextLine();
            if(!nome.isEmpty()) vet.setNome(nome);
            
            System.out.print("CRM atual: " + vet.getCrm() + "\nNovo CRM: ");
            String crm = entrada.nextLine();
            if(!crm.isEmpty()) vet.setCrm(crm);
            
            System.out.print("Especialidade atual: " + vet.getEspecialidade() + "\nNova especialidade: ");
            String especialidade = entrada.nextLine();
            if(!especialidade.isEmpty()) vet.setEspecialidade(especialidade);
            
            veterinarioController.atualizarVeterinario(vet);
            System.out.println("Veterinário atualizado com sucesso!");
        } else {
            System.out.println("Veterinário não encontrado.");
        }
    }
    
    private void removerVeterinario() {
        System.out.print("\nDigite o ID do veterinário a ser removido: ");
        int id = lerInteiro();
        
        veterinarioController.removerVeterinario(id);
        System.out.println("Veterinário removido com sucesso!");
    }

    private void menuConsulta() {
        boolean voltar = false;
        
        while(!voltar) {
            System.out.println("\n----<Menu Consulta>----");
            System.out.println("1 - Agendar Consulta");
            System.out.println("2 - Listar Consultas");
            System.out.println("3 - Buscar Consulta por ID");
            System.out.println("4 - Atualizar Consulta");
            System.out.println("5 - Remover Consulta");
            System.out.println("6 - Voltar");
            System.out.print("Opção: ");
            
            int opcao = lerInteiro();
            
            switch(opcao) {
                case 1:
                    agendarConsulta();
                    break;
                case 2:
                    listarConsultas();
                    break;
                case 3:
                    buscarConsultaPorId();
                    break;
                case 4:
                    atualizarConsulta();
                    break;
                case 5:
                    removerConsulta();
                    break;
                case 6:
                    voltar = true;
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
    
    private void agendarConsulta() {
        System.out.println("\n----<Agendar Consulta>----");
        
        try {

            listarPets();
            System.out.print("ID do Pet: ");
            int petId = lerInteiro();
            Pet pet = petController.buscarPetPorId(petId);
            if(pet == null) throw new Exception("Pet não encontrado!");


            listarVeterinarios();
            System.out.print("ID do Veterinário: ");
            int vetId = lerInteiro();
            Veterinario vet = veterinarioController.buscarVeterinarioPorId(vetId);
            if(vet == null) throw new Exception("Veterinário não encontrado!");


            LocalDate data = null;
          
                try {
                    System.out.println("Data da consulta (dd/mm/aaaa):");
                    System.out.print("Dia: ");
                    int dia = lerInteiro();
                    System.out.print("Mês: ");
                    int mes = lerInteiro();
                    System.out.print("Ano: ");
                    int ano = lerInteiro();
                    
                  data = LocalDate.of(ano, mes, dia);
                    
         
                } catch (DateTimeException e) {
                    System.out.println("Data inválida! Por favor, digite novamente.");
                }
            

            LocalTime horario = null;
            while(horario == null) {
                try {
                    System.out.println("Horário da consulta (hh:mm):");
                    System.out.print("Hora (8-18): ");
                    int hora = lerInteiro();
                    System.out.print("Minuto: ");
                    int minuto = lerInteiro();
                    horario = LocalTime.of(hora, minuto);
                    
                    if(hora < 8 || hora > 18) {
                        System.out.println("Horário deve ser entre 08:00 e 18:00");
                        horario = null;
                    }
                } catch (DateTimeException e) {
                    System.out.println("Horário inválido! " + e.getMessage());
                }
            }

            System.out.print("Diagnóstico inicial: ");
            String diagnostico = entrada.nextLine();

            Consulta consulta = consultaController.agendarConsulta(data, horario, diagnostico, pet, vet);
            
            if(consulta != null) {
                System.out.println("Consulta agendada com sucesso! ID: " + consulta.getId());
            } else {
                System.out.println("Erro ao agendar consulta.");
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
    
    private void listarConsultas() {
        System.out.println("\n----<Lista de Consultas>----");
        List<Consulta> consultas = consultaController.listarConsultas();
        
        if(consultas.isEmpty()) {
            System.out.println("Nenhuma consulta agendada.");
        } else {
            for(Consulta cons : consultas) {
                System.out.println("ID: " + cons.getId());
                System.out.println("Data: " + cons.getData());
                System.out.println("Hora: " + cons.getHora());
                System.out.println("Pet: " + cons.getPet().getNome());
                System.out.println("Tutor: " + cons.getPet().getTutor().getNome());
                System.out.println("Veterinário: " + cons.getVeterionario().getNome());
                System.out.println("Diagnóstico: " + cons.getDiagnostico());
                System.out.println("-----------------------");
            }
        }
    }
    
    private void buscarConsultaPorId() {
        System.out.print("\nDigite o ID da consulta: ");
        int id = lerInteiro();
        
        Consulta cons = consultaController.buscarConsultaPorId(id);
        
        if(cons != null) {
            System.out.println("\n----<Consulta Encontrada>----");
            System.out.println("ID: " + cons.getId());
            System.out.println("Data: " + cons.getData());
            System.out.println("Hora: " + cons.getHora());
            System.out.println("Pet: " + cons.getPet().getNome());
            System.out.println("Tutor: " + cons.getPet().getTutor().getNome());
            System.out.println("Veterinário: " + cons.getVeterionario().getNome());
            System.out.println("Diagnóstico: " + cons.getDiagnostico());
        } else {
            System.out.println("Consulta não encontrada.");
        }
    }
    
    private void atualizarConsulta() {
        System.out.print("\nDigite o ID da consulta a ser atualizada: ");
        int id = lerInteiro();
        
        Consulta cons = consultaController.buscarConsultaPorId(id);
        
        if(cons != null) {
            System.out.println("\n----<Atualizar Consulta>----");
            System.out.println("Deixe em branco para manter o valor atual.");
            
            System.out.println("Data atual: " + cons.getData());
            System.out.println("Deseja alterar? (S/N)");
            String opcao = entrada.nextLine();
            
            if(opcao.equalsIgnoreCase("S")) {
                System.out.println("Nova data (dd/mm/aaaa):");
                System.out.print("Dia: ");
                int dia = lerInteiro();
                System.out.print("Mês: ");
                int mes = lerInteiro();
                System.out.print("Ano: ");
                int ano = lerInteiro();
                cons.setData(LocalDate.of(ano, mes, dia));
            }
            
            System.out.println("Hora atual: " + cons.getHora());
            System.out.println("Deseja alterar? (S/N)");
            opcao = entrada.nextLine();
            
            if(opcao.equalsIgnoreCase("S")) {
                System.out.println("Novo horário (hh:mm):");
                System.out.print("Hora: ");
                int hora = lerInteiro();
                System.out.print("Minuto: ");
                int minuto = lerInteiro();
                cons.setHora(LocalTime.of(hora, minuto));
            }
            
            System.out.print("Diagnóstico atual: " + cons.getDiagnostico() + "\nNovo diagnóstico: ");
            String diagnostico = entrada.nextLine();
            if(!diagnostico.isEmpty()) cons.setDiagnostico(diagnostico);
            
            consultaController.atualizarConsulta(cons);
            System.out.println("Consulta atualizada com sucesso!");
        } else {
            System.out.println("Consulta não encontrada.");
        }
    }
    
    private void removerConsulta() {
        System.out.print("\nDigite o ID da consulta a ser removida: ");
        int id = lerInteiro();
        
        consultaController.removerConsulta(id);
        System.out.println("Consulta removida com sucesso!");
    }

    private void menuProntuario() {
        boolean voltar = false;
        
        while(!voltar) {
            System.out.println("\n----<Menu Prontuário>----");
            System.out.println("1 - Criar Prontuário");
            System.out.println("2 - Listar Prontuários");
            System.out.println("3 - Buscar Prontuário por ID");
            System.out.println("4 - Atualizar Prontuário");
            System.out.println("5 - Remover Prontuário");
            System.out.println("6 - Voltar");
            System.out.print("Opção: ");
            
            int opcao = lerInteiro();
            
            switch(opcao) {
                case 1:
                    criarProntuario();
                    break;
                case 2:
                    listarProntuarios();
                    break;
                case 3:
                    buscarProntuarioPorId();
                    break;
                case 4:
                    atualizarProntuario();
                    break;
                case 5:
                    removerProntuario();
                    break;
                case 6:
                    voltar = true;
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
    
    private void criarProntuario() {
        System.out.println("\n----<Criar Prontuário>----");
        
        listarConsultas();
        System.out.print("ID da Consulta: ");
        int consultaId = lerInteiro();
        Consulta consulta = consultaController.buscarConsultaPorId(consultaId);
        
        if(consulta == null) {
            System.out.println("Consulta não encontrada!");
            return;
        }
        
        System.out.print("Observações: ");
        String observacao = entrada.nextLine();
        
        System.out.print("Procedimentos realizados: ");
        String procedimento = entrada.nextLine();
        
        System.out.print("Exames solicitados: ");
        String exames = entrada.nextLine();
        
        System.out.print("Evolução: ");
        String evolucao = entrada.nextLine();
        
        Prontuario prontuario = prontuarioController.criarProntuario(consulta, observacao, procedimento, exames, evolucao);
        
        if(prontuario != null) {
            System.out.println("Prontuário criado com sucesso! ID: " + prontuario.getId());
        } else {
            System.out.println("Erro ao criar prontuário.");
        }
    }
    
    private void listarProntuarios() {
        System.out.println("\n----<Lista de Prontuários>----");
        List<Prontuario> prontuarios = prontuarioController.listarProntuarios();
        
        if(prontuarios.isEmpty()) {
            System.out.println("Nenhum prontuário cadastrado.");
        } else {
            for(Prontuario pront : prontuarios) {
                System.out.println("ID: " + pront.getId());
                System.out.println("Consulta ID: " + pront.getConsulta().getId());
                System.out.println("Data Consulta: " + pront.getConsulta().getData());
                System.out.println("Pet: " + pront.getConsulta().getPet().getNome());
                System.out.println("Veterinário: " + pront.getConsulta().getVeterionario().getNome());
                System.out.println("Observações: " + pront.getObservacao());
                System.out.println("-----------------------");
            }
        }
    }
    
    private void buscarProntuarioPorId() {
        System.out.print("\nDigite o ID do prontuário: ");
        int id = lerInteiro();
        
        Prontuario pront = prontuarioController.buscarProntuarioPorId(id);
        
        if(pront != null) {
            System.out.println("\n----<Prontuário Encontrado>----");
            System.out.println("ID: " + pront.getId());
            System.out.println("Consulta ID: " + pront.getConsulta().getId());
            System.out.println("Data Consulta: " + pront.getConsulta().getData());
            System.out.println("Pet: " + pront.getConsulta().getPet().getNome());
            System.out.println("Tutor: " + pront.getConsulta().getPet().getTutor().getNome());
            System.out.println("Veterinário: " + pront.getConsulta().getVeterionario().getNome());
            System.out.println("Observações: " + pront.getObservacao());
            System.out.println("Procedimentos: " + pront.getProcedimento());
            System.out.println("Exames: " + pront.getExames());
            System.out.println("Evolução: " + pront.getEvolucao());
        } else {
            System.out.println("Prontuário não encontrado.");
        }
    }
    
    private void atualizarProntuario() {
        System.out.print("\nDigite o ID do prontuário a ser atualizado: ");
        int id = lerInteiro();
        
        Prontuario pront = prontuarioController.buscarProntuarioPorId(id);
        
        if(pront != null) {
            System.out.println("\n----<Atualizar Prontuário>----");
            System.out.println("Deixe em branco para manter o valor atual.");
            
            System.out.print("Observações atuais: " + pront.getObservacao() + "\nNovas observações: ");
            String observacao = entrada.nextLine();
            if(!observacao.isEmpty()) pront.setObservacao(observacao);
            
            System.out.print("Procedimentos atuais: " + pront.getProcedimento() + "\nNovos procedimentos: ");
            String procedimento = entrada.nextLine();
            if(!procedimento.isEmpty()) pront.setProcedimento(procedimento);
            
            System.out.print("Exames atuais: " + pront.getExames() + "\nNovos exames: ");
            String exames = entrada.nextLine();
            if(!exames.isEmpty()) pront.setExames(exames);
            
            System.out.print("Evolução atual: " + pront.getEvolucao() + "\nNova evolução: ");
            String evolucao = entrada.nextLine();
            if(!evolucao.isEmpty()) pront.setEvolucao(evolucao);
            
            prontuarioController.atualizarProntuario(pront);
            System.out.println("Prontuário atualizado com sucesso!");
        } else {
            System.out.println("Prontuário não encontrado.");
        }
    }
    
    private void removerProntuario() {
        System.out.print("\nDigite o ID do prontuário a ser removido: ");
        int id = lerInteiro();
        
        prontuarioController.removerProntuario(id);
        System.out.println("Prontuário removido com sucesso!");
    }
    
    private int lerInteiro() {
        while(true) {
            try {
                return Integer.parseInt(entrada.nextLine());
            } catch(NumberFormatException e) {
                System.out.print("Valor inválido! Digite um número: ");
            }
        }
    }

    
    public static void main(String[] args) {
        SistemaClinicaVeterinariaView view = new SistemaClinicaVeterinariaView();
        view.iniciar();
    }
}