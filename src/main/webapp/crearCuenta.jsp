<html>
<head>
    <title>Nueva Cuenta Corriente</title>
    <link rel="stylesheet" href="css/estilos.css">
</head>
<body>

<div class="container">
    <h2>Crear Cuenta Corriente</h2>

    <form action="cuentas" method="post">
        <input type="hidden" name="accion" value="insertar">

        RUT Cliente:
        <input type="text" name="rut" required>

        Monto Inicial:
        <input type="number" name="monto" required>

        Ejecutivo:
        <input type="text" name="ejecutivo" required>

        <button type="submit">Guardar Cuenta</button>
    </form>

    <a href="cuentas" class="link-volver">Volver</a>
</div>

</body>
</html>
