package br.com.adminclass.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.admclass.persistencia.entidade.Usuario;
import br.com.admclass.persistencia.jdbc.UsuarioDAO;


public class AutenticadorController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession sessao = req.getSession(false);
		if (sessao!=null){
			sessao.invalidate();
		}
		
		resp.sendRedirect("login.html");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String login = req.getParameter("login");
		String senha = req.getParameter("senha");
		Usuario usuario = new Usuario();

		usuario.setLogin(login);
		usuario.setSenha(senha);
		UsuarioDAO usuDAO = new UsuarioDAO();
		Usuario usuAutenticado = usuDAO.Autenticar(usuario);
		
		if (usuAutenticado!=null){
			HttpSession sessao = req.getSession();
			sessao.setAttribute("usuAutenticado", usuAutenticado);
			sessao.setMaxInactiveInterval(60*10);
			
			req.getRequestDispatcher("WEB-INF/index.jsp").forward(req, resp);
		}else {
			resp.getWriter().println("<script>window.alert('Usuario ou senha incorreta!'); location.href='login.html';</script>");
		}
			
	}

}
