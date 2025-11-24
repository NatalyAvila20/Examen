<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Registro de Transferencias</title>
    <style>
        body { font-family: Arial; background: #f4f6f9; }
        .container {
            width: 40%; margin: 40px auto; background: white;
            padding: 25px; border-radius: 10px; box-shadow: 0 2px 8px rgba(0,0,0,0.2);
        }
        label { font-weight: bold; }
        input, select {
            width: 100%; padding: 8px; margin-bottom: 12px;
            border: 1px solid #ccc; border-radius: 5px;
        }
        button {
            width: 100%; padding: 12px; background: #0066cc;
            color: white; border: none; border-radius: 6px;
            cursor: pointer; font-size: 16px;
        }
        button:hover { background: #004999; }
    </style>
</head>
<body>

<div class="container">
    <h2>Realizar Transferencia</h2>

    <form action="MovimientoServlet" method="post">

        <label>RUT Cliente</label>
        <input type="text" name="rutCliente" required>

        <label>RUT Dueño de la Cuenta</label>
        <input type="text" name="rutDueno" required>

        <label>ID Cuenta</label>
        <input type="number" name="idCuenta" required>

        <label>Monto a Transferir</label>
        <input type="number" name="monto" required>

        <label>Cuenta Destino</label>
        <input type="text" name="cuentaDestino" required>

        <label>Tipo de Cuenta Destino</label>
        <select name="tipoCuenta" required>
            <option value="Cuenta Corriente">Cuenta Corriente</option>
            <option value="Cuenta Vista">Cuenta Vista</option>
            <option value="Cuenta RUT">Cuenta RUT</option>
            <option value="Ahorro">Cuenta de Ahorro</option>
        </select>

        <button type="submit">Registrar Transferencia</button>
    </form>
</div>

</body>
</html>
