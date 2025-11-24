<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Menú Principal - Cta Corriente</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f4f6f9;
            margin: 0;
            padding: 0;
        }
        .container {
            text-align: center;
            margin-top: 80px;
        }
        h2 {
            color: #333;
        }
        .card {
            margin: 20px auto;
            width: 300px;
            padding: 20px;
            background: white;
            border-radius: 10px;
            box-shadow: 0px 2px 8px rgba(0,0,0,0.2);
        }
        .menu-btn {
            display: block;
            margin: 15px auto;
            padding: 12px;
            width: 80%;
            background: #0066cc;
            color: white;
            border: none;
            border-radius: 6px;
            font-size: 16px;
            cursor: pointer;
            text-decoration: none;
        }
        .menu-btn:hover {
            background: #004999;
        }
    </style>
</head>
<body>

<div class="container">
    <h2>Bienvenido: ${nombre}</h2>
    <h3>Departamento: ${departamento}</h3>

    <div class="card">

        <!-- Acceden a los servlets o rutas que ya funcionan -->
        <a href="/CtaCorriente/clientes" class="menu-btn">Clientes</a>
        <a href="/CtaCorriente/cuentas" class="menu-btn">Cuentas Corrientes</a>

        <!-- ESTA ES LA RUTA QUE FUNCIONA SEGÚN ME INDICASTE -->
        <a href="/CtaCorriente/MovimientoServlet" class="menu-btn">Movimientos</a>

        <a href="/CtaCorriente/logout" class="menu-btn">Cerrar Sesión</a>
    </div>
</div>

</body>
</html>
