package br.com.adminclass.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.admclass.persistencia.entidade.Usuario;
import br.com.admclass.persistencia.jdbc.UsuarioDAO;

//http://localhost:8080/AdClass/usucontroller
@WebServlet(name = "/usucontroller")
public class UsuarioController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String acao = req.getParameter("acao");
		resp.setContentType("text/html");
		if (acao.equals("excluir")) {
			String id = req.getParameter("id");
			Usuario usu = new Usuario();

			if (id != null)
				usu.setId(Integer.parseInt(id));

			UsuarioDAO usuDAO = new UsuarioDAO();
			usuDAO.excluir(usu);
			resp.sendRedirect("controller.do?acao=listar");

		} else if (acao.equals("listar")) {
			UsuarioDAO usuDAO = new UsuarioDAO();

			List<Usuario> lista = usuDAO.pesquisaAll();

			req.setAttribute("lista", lista);
			RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/controleusu.jsp");
			dispatcher.forward(req, resp);
			
		}else if (acao.equals("alterar")) {
			UsuarioDAO usuDAO = new UsuarioDAO();

			String id = req.getParameter("id");
			Usuario usuario = usuDAO.pesquisaById(Integer.parseInt(id));
			req.setAttribute("usuario", usuario);
			
			RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/formusuario.jsp");
			dispatcher.forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		String nome = req.getParameter("nome");
		String login = req.getParameter("login");
		String senha = req.getParameter("senha");
		Usuario usu = new Usuario();

		if (id != null && id!="")
			usu.setId(Integer.parseInt(id));

		usu.setNome(nome);
		usu.setLogin(login);
		usu.setSenha(senha);

		UsuarioDAO usuDAO = new UsuarioDAO();
		usuDAO.salvar(usu);
		resp.sendRedirect("controller.do?acao=listar");
		// System.out.println("Salvou");
	}
}
