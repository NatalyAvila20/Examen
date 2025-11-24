<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.platinum.cta.User" %>

<%
    User u = (User) request.getAttribute("usuario");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Editar Usuario</title>
</head>
<body>

<h2>Editar Usuario</h2>

<form action="UsuarioServlet" method="post">
    <input type="hidden" name="accion" value="actualizar">
    <input type="hidden" name="id" value="<%=u.getId()%>">

    Nombre Usuario:
    <input type="text" name="nombreUsuario" value="<%=u.getNombreUsuario()%>" required><br><br>

    Password:
    <input type="text" name="password" value="<%=u.getPassword()%>" required><br><br>

    <button type="submit">Actualizar</button>
</form>

<a href="UsuarioServlet?accion=listar">Volver</a>

</body>
</html>
