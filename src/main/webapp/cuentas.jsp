<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Cuentas Corrientes</title>
    <link rel="stylesheet" href="css/estilos.css">
</head>
<body>

<div class="table-container">
    <h2>Listado de Cuentas Corrientes</h2>

    <a href="cuentas?accion=nuevo" class="btn">+ Crear nueva cuenta</a>
    <br><br>

    <table>
        <tr>
            <th>ID</th>
            <th>RUT Cliente</th>
            <th>Monto</th>
            <th>Ejecutivo</th>
            <th>Acciones</th>
        </tr>

        <%
            java.util.List<String[]> lista = 
                (java.util.List<String[]>) request.getAttribute("listaCuentas");

            if (lista != null) {
                for (String[] c : lista) {
        %>
        <tr>
            <td><%= c[0] %></td>
            <td><%= c[1] %></td>
            <td>$ <%= c[2] %></td>
            <td><%= c[3] %></td>
            <td>
                <a class="btn-edit" href="cuentas?accion=editar&id=<%= c[0] %>">Editar</a> |
                <a class="btn-delete" href="cuentas?accion=eliminar&id=<%= c[0] %>">Eliminar</a>
            </td>
        </tr>
        <%      } } %>
    </table>

    <a href="home.jsp" class="link-volver">Volver al menú</a>
</div>

</body>
</html>
