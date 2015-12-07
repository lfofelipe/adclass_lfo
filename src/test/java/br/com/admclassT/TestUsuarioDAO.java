package br.com.admclassT;

import java.util.List;

import br.com.admclass.persistencia.entidade.Usuario;
import br.com.admclass.persistencia.jdbc.UsuarioDAO;

public class TestUsuarioDAO {

	public static void main(String[] args) {

		// testSalvar();
		// testBuscaById(6);
		//testBuscaAll();
		testAutenticar();
	}

	private static void testBuscaById() {

		UsuarioDAO usuDAO = new UsuarioDAO();
		Usuario usu = usuDAO.pesquisaById(2);
		// System.out.print("ID: " + usu.getId() + " Nome: " + usu.getNome() + "
		// Login: " + usu.getLogin() + " Senha: "
		// + usu.getSenha());
		System.out.println(usu);

	}
	private static void testAutenticar(){
		Usuario usu = new Usuario();
		UsuarioDAO usuDAO = new UsuarioDAO();
		usu.setLogin("balihai");
		usu.setSenha("2913199ee");
		usu = usuDAO.Autenticar(usu);
		if (usu!=null)
			System.out.print("Usuario Logado com sucesso");
		else
			System.out.print("Erro de login!!");
	}
	

	private static void testBuscaAll() {
		Usuario usu = new Usuario();
		UsuarioDAO usuDAO = new UsuarioDAO();
		List<Usuario> lista = usuDAO.pesquisaAll();
		for (Usuario u : lista)
			System.out.println(u);

	}

	private static void testAlterar() {
		// Criando usuario
		Usuario usu = new Usuario();
		usu.setId(4);
		usu.setNome("JAOHAO123332");
		usu.setLogin("joaonarco");
		usu.setSenha("002994eeee");

		// Criando conexão
		UsuarioDAO usuDAO = new UsuarioDAO();
		usuDAO.alterar(usu);

		System.out.print("Cadastro efetuado com sucesso");
	}

	private static void testCadastrar() {
		// Criando usuario
		Usuario usu = new Usuario();
		usu.setNome("JAOHAO");
		usu.setLogin("joao");
		usu.setSenha("002994e");

		// Criando conexão
		UsuarioDAO usuDAO = new UsuarioDAO();
		usuDAO.cadastrar(usu);

		System.out.print("Cadastro alterado com sucesso");
	}

	private static void testExcluir() {
		// Criando usuario
		Usuario usu = new Usuario();
		usu.setId(1);
		// Criando conexão
		UsuarioDAO usuDAO = new UsuarioDAO();
		usuDAO.excluir(usu);

		System.out.print("Usuario excluido com sucesso");
	}

	private static void testSalvar() {
		Usuario usu = new Usuario();
		// usu.setId(5);
		usu.setNome("Anderson");
		usu.setLogin("70correr");
		usu.setSenha("maseu20pegar");
		UsuarioDAO usuDAO = new UsuarioDAO();
		usuDAO.salvar(usu);
		System.out.print("cadastro salvo com sucesso");

	}
}
