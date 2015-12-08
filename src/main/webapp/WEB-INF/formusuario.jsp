<!DOCTYPE html>
<%@page import="br.com.admclass.persistencia.entidade.Usuario"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<%
		Usuario u = (Usuario)request.getAttribute("usuario");
	%>
	<form action="controller.do" method="post">
	ID: <input type="number" name="id" value="<%=u.getId()%>"/>
	Nome: <input type="text" name="nome" value="<%=u.getNome()%>"/>
	Login: <input type="text" name="login" value="<%=u.getLogin()%>"/>
	Senha: <input type="password" name="senha" value="<%=u.getSenha()%>"/>
	<input type="submit" value="Salvar"/>
	
	
	</form>
</body>
</html>