<html>
<head>
<title>Nuevo Cliente</title>
<link rel="stylesheet" href="css/estilos.css">
</head>
<body>

<div class="container">
    <h2>Crear Cliente</h2>

    <form action="clientes" method="post">
        <input type="hidden" name="accion" value="insertar">

        <label>RUT:</label>
        <input type="text" name="rut" required>

        <label>Nombre:</label>
        <input type="text" name="nombre" required>

        <label>Apellido:</label>
        <input type="text" name="apellido" required>

        <label>Correo:</label>
        <input type="email" name="correo">

        <label>Teléfono:</label>
        <input type="text" name="telefono">

        <label>Dirección:</label>
        <input type="text" name="direccion">

        <button type="submit">Guardar Cliente</button>
    </form>

    <a href="clientes" class="link-volver">Volver</a>
</div>

</body>
</html>
