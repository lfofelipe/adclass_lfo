package br.com.adminclass.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.admclass.persistencia.entidade.Usuario;
import br.com.admclass.persistencia.jdbc.UsuarioDAO;
//http://localhost:8080/AdClass/usucontroller
@WebServlet(name="/usucontroller")
public class UsuarioController extends HttpServlet {


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		String nome = req.getParameter("nome");
		String login = req.getParameter("login");
		String senha = req.getParameter("senha");
		Usuario usu = new Usuario();
		usu.setNome(nome);
		usu.setLogin(login);
		usu.setSenha(senha);
		UsuarioDAO usuDAO = new UsuarioDAO();
		usuDAO.salvar(usu);
		System.out.println("Salvou");
	}
}
