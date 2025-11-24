<%@ page contentType="text/html; charset=UTF-8" %>

<html>
<head>
    <title>Editar Cliente</title>
    <link rel="stylesheet" href="css/estilos.css">
</head>
<body>

<div class="container">
    <h2>Editar Cliente</h2>

    <form action="clientes" method="post">
        <input type="hidden" name="accion" value="actualizar">
        <input type="hidden" name="id" value="${id}">

        <label>RUT:</label>
        <input type="text" name="rut" value="${rut}" required>

        <label>Nombre:</label>
        <input type="text" name="nombre" value="${nombre}" required>

        <label>Apellido:</label>
        <input type="text" name="apellido" value="${apellido}" required>

        <label>Correo:</label>
        <input type="email" name="correo" value="${correo}">

        <label>Teléfono:</label>
        <input type="text" name="telefono" value="${telefono}">

        <label>Dirección:</label>
        <input type="text" name="direccion" value="${direccion}">

        <button type="submit">Actualizar Cliente</button>
    </form>

    <a href="clientes" class="link-volver">Volver</a>
</div>

</body>
</html>
