package br.com.admclass.persistencia.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.admclass.persistencia.entidade.Usuario;

public class UsuarioDAO {
	private Connection con = ConexaoFactory.getConnection();

	public Usuario Autenticar(Usuario usu) {
		String sql = "Select * from usuario where login=? and senha=md5(?)";
		try (PreparedStatement preparador = con.prepareStatement(sql)) {
			preparador.setString(1, usu.getLogin());
			preparador.setString(2, usu.getSenha());
			ResultSet resultadoSet = preparador.executeQuery();
			//posicionando o resultadoSet na primeira posição
			if (resultadoSet.next()) {
				Usuario usuRes = new Usuario();
				usuRes.setId(resultadoSet.getInt("id"));
				usuRes.setNome(resultadoSet.getString("nome"));
				usuRes.setLogin(resultadoSet.getString("login"));
				usuRes.setSenha(resultadoSet.getString("senha"));
				return usuRes;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Cria um novo usuário (não é necessário passar o ID)
	 * 
	 * @param usu
	 *            Informações do usuário
	 */
	public void cadastrar(Usuario usu) {

		String sql = "insert into usuario (nome, login, senha) values (?,?,md5(?))";
		try {
			// Criando um statment
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, usu.getNome());
			preparador.setString(2, usu.getLogin());
			preparador.setString(3, usu.getSenha());
			// executando o preparador
			preparador.execute();
			// fechando o preparador
			preparador.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Altera o usuário de acordo com o ID passado
	 * 
	 * @param usu
	 *            São passadas as informações para alterar o registro
	 */
	public void alterar(Usuario usu) {
		String sql = "update usuario set nome=?, login=?, senha=md5(?) where id=?";
		try (PreparedStatement preparador = con.prepareStatement(sql)) {
			preparador.setString(1, usu.getNome());
			preparador.setString(2, usu.getLogin());
			preparador.setString(3, usu.getSenha());
			preparador.setInt(4, usu.getId());
			// executando o preparador
			preparador.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Exclúi um registro de acordo com o ID do usuário
	 * 
	 * @param usu
	 *            É necessário o ID do funcionario
	 */
	public void excluir(Usuario usu) {
		String sql = "delete from usuario where id=?";
		try (PreparedStatement preparador = con.prepareStatement(sql)) {
			preparador.setInt(1, usu.getId());
			// executando o preparador
			preparador.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Cria um novo Usuário se não for encontrado o ID, se encontrar, o usuário
	 * é alterado
	 * 
	 * @param usuario
	 *            é necessário passar um objeto do tipo Usuário (ID) para saber
	 *            se é necessário criar um novo registro
	 */
	public void salvar(Usuario usuario) {
		if (usuario.getId() != null) {
			// Criando conexão
			alterar(usuario);
		} else {
			cadastrar(usuario);
		}
	}

	/**
	 * Realiza uma busca utilizando o numero id do usuário
	 * 
	 * @param id
	 *            Código único do usuário
	 * @return Retorna um objeto do tipo usuário se encontrar o ID, senão
	 *         retorna null
	 */
	public Usuario pesquisaById(Integer id) {

		String sql = "select * from usuario where id=?";

		try (PreparedStatement preparador = con.prepareStatement(sql)) {

			preparador.setInt(1, id);
			ResultSet resultado = preparador.executeQuery();
			// Posicionando o cursor no primeiro registro
			if (resultado.next()) {
				Usuario usuarioRet = new Usuario();
				usuarioRet.setId(resultado.getInt("id"));
				usuarioRet.setNome(resultado.getString("nome"));
				usuarioRet.setLogin(resultado.getString("login"));
				usuarioRet.setSenha(resultado.getString("senha"));
				return usuarioRet;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Método para buscar todos os registros da tabela usuario
	 * 
	 * @return Retorna uma lista com os dados dos usuarios se houver dados, caso
	 *         contrário retorna uma lista de usuario vazia
	 */
	public List<Usuario> pesquisaAll() {

		String sql = "select * from usuario ";
		List<Usuario> lista = new ArrayList<Usuario>();
		try (PreparedStatement preparador = con.prepareStatement(sql)) {

			ResultSet resultado = preparador.executeQuery();
			// Posicionando o cursor no primeiro registro
			while (resultado.next()) {
				Usuario usuario = new Usuario();
				usuario.setId(resultado.getInt("id"));
				usuario.setNome(resultado.getString("nome"));
				usuario.setLogin(resultado.getString("login"));
				usuario.setSenha(resultado.getString("senha"));
				// Adicionando usuario na lista
				lista.add(usuario);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}

}
