<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Clientes</title>
    <link rel="stylesheet" href="css/estilos.css">
</head>
<body>

<div class="table-container">
    <h2>Listado de Clientes</h2>

    <a href="clientes?accion=nuevo" class="btn">+ Crear nuevo cliente</a>
    <br><br>

    <table>
        <tr>
            <th>ID</th>
            <th>RUT</th>
            <th>Nombre</th>
            <th>Apellido</th>
            <th>Correo</th>
            <th>Teléfono</th>
            <th>Acciones</th>
        </tr>

        <%
            java.util.List<String[]> lista =
                    (java.util.List<String[]>) request.getAttribute("listaClientes");

            if (lista != null) {
                for (String[] c : lista) {
        %>
        <tr>
            <td><%= c[0] %></td>
            <td><%= c[1] %></td>
            <td><%= c[2] %></td>
            <td><%= c[3] %></td>
            <td><%= c[4] %></td>
            <td><%= c[5] %></td>
            <td>
                <a class="btn-edit" href="clientes?accion=editar&id=<%= c[0] %>">Editar</a> |
                <a class="btn-delete" href="clientes?accion=eliminar&id=<%= c[0] %>">Eliminar</a>
            </td>
        </tr>
        <%  }
           }
        %>
    </table>

    <a href="home.jsp" class="link-volver">Volver al menú</a>
</div>

</body>
</html>
