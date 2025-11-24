<%@ page contentType="text/html; charset=UTF-8" %>

<html>
<head>
    <title>Editar Cuenta Corriente</title>
    <link rel="stylesheet" href="css/estilos.css">
</head>
<body>

<div class="container">
    <h2>Editar Cuenta Corriente</h2>

    <form action="cuentas" method="post">
        <input type="hidden" name="accion" value="actualizar">
        <input type="hidden" name="idCuenta" value="${idCuenta}">

        <label>RUT Cliente:</label>
        <input type="text" name="rut" value="${rut}" required>

        <label>Monto:</label>
        <input type="number" name="monto" value="${monto}" required>

        <label>Ejecutivo:</label>
        <input type="text" name="ejecutivo" value="${ejecutivo}" required>

        <button type="submit">Actualizar Cuenta</button>
    </form>

    <a href="cuentas" class="link-volver">Volver</a>
</div>

</body>
</html>
