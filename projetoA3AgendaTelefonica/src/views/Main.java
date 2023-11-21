package views;

import java.util.InputMismatchException;
import java.util.Scanner;

import controllers.ContatoController;
import repositories.ContatoRepository;

public class Main {

	private static void menu() {
		System.out.println("\n---MENU---");
		System.out.println("0) Sair");
		System.out.println("1) Cadastrar contato");
		System.out.println("2) Visualizar contatos");
		System.out.println("3) Editar telefone do contato");
		System.out.println("4) Editar nome do contato");
		System.out.println("5) Editar e-mail do contato");
		System.out.println("6) Excluir contato");
	}

	public static void main(String[] args) {
		Scanner console = new Scanner(System.in);
		int opcao;
		do {
			menu();
			System.out.print("Digite uma opção: ");

			try {
				String input = console.nextLine();
				opcao = Integer.parseInt(input);

				switch (opcao) {
				case 0:
					ContatoRepository.encerrarConexoes();
					System.out.println("Sistema fechado!");
					break;
				case 1:
					ContatoController.cadastrarContato();
					break;
				case 2:
					ContatoController.visualizarContatos();
					break;
				case 3:
					ContatoController.atualizarTelefoneContato();
					break;
				case 4:
					ContatoController.atualizarNomeContato();
					break;
				case 5:
					ContatoController.atualizarEmailContato();
					break;
				case 6:
					ContatoController.excluirContato();
					break;
				default:
					System.out.println("Opção inválida, escolha novamente.");
					break;
				}
			} catch (NumberFormatException e) {
				System.out.println("POr favor, insira um número válido");
				opcao = -1;
				console.nextLine();
			} catch (InputMismatchException e) {
				System.out.println("POr favor, insira um número válido");
				opcao = -1;
				console.nextLine();
			}
		} while (opcao != 0);

		console.close();
	}
}
