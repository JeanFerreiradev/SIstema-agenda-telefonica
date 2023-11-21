package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.Conexao;
import entities.Contato;

public class ContatoRepository {
	static Connection conn = Conexao.getConexao();
	static PreparedStatement ps;
	static ResultSet rs;

	public static boolean cadastrarContato(Contato contato) {
		String url = "INSERT INTO contato (nome, email, telefone) VALUES (?, ?, ?)";
		ps = null;

		try {
			ps = conn.prepareStatement(url);
			ps.setString(1, contato.getNome());
			ps.setString(2, contato.getEmail());
			ps.setString(3, contato.getTelefone());
			ps.execute();

			return true;
		} catch (SQLException e) {
			System.out.println("Erro ao cadastrar novo contato / " + e.getMessage());
			return false;
		}
	}

	public static boolean contatoExiste(Contato contato) {
		String url = "SELECT telefone FROM contato";
		ps = null;
		rs = null;

		try {
			ps = conn.prepareStatement(url);
			
			rs = ps.executeQuery();

			if (rs.next()) {
				String telefone = rs.getString("telefone");

				if (telefone.equals(contato.getTelefone())) {
					System.out.println("Contato já existe!");
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		} catch (SQLException e) {
			System.out.println("Erro ao verificar se contato já existe / " + e.getMessage());
			return true;
		}
	}

	public static List<Contato> visualizarContatos() {
		String url = "SELECT nome, email, telefone FROM contato";
		ps = null;
		rs = null;
		List<Contato> contatos = new ArrayList<>();

		try {
			ps = conn.prepareStatement(url);
			rs = ps.executeQuery();

			while (rs.next()) {
				String nome = rs.getString("nome");
				String email = rs.getString("email");
				String telefone = rs.getString("telefone");
				Contato contato = new Contato(nome, email, telefone);
				contatos.add(contato);
			}
			if (contatos.isEmpty()) {
				System.out.println("Nenhum contato foi cadastrado ainda!");
				return null;
			} else {
				return contatos;
			}
		} catch (SQLException e) {
			System.out.println("Erro ao buscar lista de contatos / " + e.getMessage());
			return null;
		}
	}

	public static boolean atualizarTelefoneContato(String nome, String novoTelefone) {
		String url = "UPDATE contato SET telefone = ? WHERE nome = ?";
		ps = null;

		try {
			ps = conn.prepareStatement(url);
			ps.setString(1, novoTelefone);
			ps.setString(2, nome);

			int rowsAffected = ps.executeUpdate();
			if (rowsAffected > 0) {
				return true;
			} else {
				System.out.println("Contato não encontrado!");
				return false;
			}
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar telefone do contato!");
			return false;
		}
	}

	public static boolean atualizarNomeContato(String telefone, String novoNome) {
		String url = "UPDATE contato SET nome = ? WHERE telefone = ?";
		ps = null;

		try {
			ps = conn.prepareStatement(url);
			ps.setString(1, novoNome);
			ps.setString(2, telefone);

			int rowsAffected = ps.executeUpdate();
			if (rowsAffected > 0) {
				return true;
			} else {
				System.out.println("Contato não encontrado!");
				return false;
			}
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar nome do contato!");
			return false;
		}
	}

	public static boolean atualizarEmailContato(String nome, String novoEmail) {
		String url = "UPDATE contato SET email = ? WHERE nome = ?";
		ps = null;

		try {
			ps = conn.prepareStatement(url);
			ps.setString(1, novoEmail);
			ps.setString(2, nome);

			int rowsAffected = ps.executeUpdate();
			if (rowsAffected > 0) {
				return true;
			} else {
				System.out.println("Contato não encontrado!");
				return false;
			}
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar e-mail do contato!");
			return false;
		}
	}

	public static boolean excluirContato(String nome) {
		String url = "DELETE FROM contato WHERE nome = ?";
		ps = null;

		try {
			ps = conn.prepareStatement(url);
			ps.setString(1, nome);

			int rowsAffected = ps.executeUpdate();
			if (rowsAffected > 0) {
				return true;
			} else {
				System.out.println("Contato não encontrado!");
				return false;
			}
		} catch (SQLException e) {
			System.out.println("Erro ao excluir contato / " + e.getMessage());
			return false;
		}
	}
	
	public static boolean encerrarConexoes() {
		try {
			if (conn != null)
				conn.close();
			if (ps != null)
				ps.close();
			if (rs != null)
				rs.close();
			return true;
		} catch (SQLException e) {
			System.out.println("Erro ao encerrar conexões / " + e.getMessage());
			return false;
		}
	}
}
