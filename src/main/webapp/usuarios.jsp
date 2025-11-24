<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*, com.platinum.cta.User" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Usuarios</title>
</head>
<body>

<h2>Listado de Usuarios</h2>

<a href="UsuarioServlet?accion=crear">Crear Usuario</a>

<table border="1" cellpadding="10">
    <tr>
        <th>ID</th>
        <th>Nombre Usuario</th>
        <th>Password</th>
        <th>Acciones</th>
    </tr>

    <%
        List<User> lista = (List<User>) request.getAttribute("usuarios");
        for (User u : lista) {
    %>

    <tr>
        <td><%= u.getId() %></td>
        <td><%= u.getNombreUsuario() %></td>
        <td><%= u.getPassword() %></td>
        <td>
            <a href="UsuarioServlet?accion=editar&id=<%=u.getId()%>">Editar</a>
            |
            <a href="UsuarioServlet?accion=eliminar&id=<%=u.getId()%>"
               onclick="return confirm('¿Eliminar usuario?');">Eliminar</a>
        </td>
    </tr>

    <% } %>

</table>

</body>
</html>
