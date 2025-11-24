<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Crear Usuario</title>
</head>
<body>

<h2>Crear Usuario</h2>

<form action="UsuarioServlet" method="post">
    <input type="hidden" name="accion" value="guardar">

    Nombre Usuario:
    <input type="text" name="nombreUsuario" required><br><br>

    Password:
    <input type="password" name="password" required><br><br>

    <button type="submit">Guardar</button>
</form>

<a href="UsuarioServlet?accion=listar">Volver</a>

</body>
</html>
