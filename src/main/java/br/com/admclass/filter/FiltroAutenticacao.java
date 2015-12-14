package br.com.admclass.filter;

import java.io.IOException;

//import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//import javax.servlet.annotation.WebFilter;

//@WebFilter(dispatcherTypes={DispatcherType.REQUEST}, urlPatterns="/*")
public class FiltroAutenticacao implements Filter{

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		HttpSession sessao = req.getSession(false);
		
		if (sessao!=null ||req.getRequestURI().contains("login.html") || req.getRequestURI().contains("autenticador.do")){
			chain.doFilter(request,response);
		}else{
			resp.sendRedirect("login.html");
		}

	}


	@Override
	public void destroy() {

		
	}

}
