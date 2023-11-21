package controllers;

import java.util.Scanner;

import entities.Contato;
import repositories.ContatoRepository;

public class ContatoController {
	static Scanner console = new Scanner(System.in);

	public static void cadastrarContato() {
		System.out.print("Nome: ");
		String nome = console.nextLine();
		System.out.print("E-mail: ");
		String email = console.nextLine();
		System.out.print("Telefone: ");
		String telefone = console.nextLine();

		Contato contato = new Contato(nome, email, telefone);

		if (ContatoRepository.contatoExiste(contato)) {
			System.out.println("Não foi possível cadastrar esse contato");
		} else if (ContatoRepository.cadastrarContato(contato)) {
			System.out.println("Contato cadastrado com sucesso!");
		}
	}
	
	public static void visualizarContatos() {
		System.out.println("\n" + ContatoRepository.visualizarContatos());
	}
	
	public static void atualizarTelefoneContato() {
		System.out.print("Nome a ser buscado: ");
		String nome = console.nextLine();
		System.out.print("Novo número de telefone: ");
		String novoTelefone = console.nextLine();
		
		if (ContatoRepository.atualizarTelefoneContato(nome, novoTelefone)) {
			System.out.println("Número de telefone alterado com sucesso!");
		}
	}
	
	public static void atualizarNomeContato() {
		System.out.print("Número de telefone a ser buscado: ");
		String telefone = console.nextLine();
		System.out.print("Novo nome do contato: ");
		String novoNome = console.nextLine();
		
		if (ContatoRepository.atualizarNomeContato(telefone, novoNome)) {
			System.out.println("Nome alterado com sucesso!");
		}
	}
	
	public static void atualizarEmailContato() {
		System.out.print("Nome a ser buscado: ");
		String nome = console.nextLine();
		System.out.print("Novo e-mail: ");
		String novoEmail = console.nextLine();
		
		if (ContatoRepository.atualizarEmailContato(nome, novoEmail)) {
			System.out.println("E-mail alterado com sucesso!");
		}
	}
	
	public static void excluirContato() {
		System.out.print("Nome a ser buscado: ");
		String nome = console.nextLine();
		
		if (ContatoRepository.excluirContato(nome)) {
			System.out.println("Contato excluído com sucesso!");
		}
	}
}
