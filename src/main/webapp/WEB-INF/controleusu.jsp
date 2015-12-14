<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="br.com.admclass.persistencia.entidade.Usuario"%>
<%@page import="java.util.List"%>
<%@page import="br.com.adminclass.controller.UsuarioController"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>painel do usuario</title>
<script type="text/javascript">
	function confirmaExclusao(id) {
		if (window.confirm('tem certeza que deseja realizar a Exclusão?'))
			location.href = "controller.do?id=" + id + "&acao=excluir";
	}
</script>
</head>
<body>
	<%@include file="menu.jsp"%>
	<%
		List<Usuario> lista = (List<Usuario>) request.getAttribute("lista");
	%>
	<table border=1>
		<tr>
			<th>ID</th>
			<th>Nome</th>
			<th>Login</th>
			<th>Ação</th>
		</tr>
		<%
			for (Usuario u : lista) {
		%>
		<tr>
			<td><%=u.getId()%></td>
			<td><%=u.getNome()%></td>
			<td><%=u.getLogin()%></td>

			<td><a href="javascript:confirmaExclusao(<%=u.getId()%>)">
					Excluir </a> | <a href="controller.do?acao=alterar&id=<%=u.getId()%>">Alterar</a></td>
		</tr>
		<%
			}
		%>
	</table>
</body>
</html>