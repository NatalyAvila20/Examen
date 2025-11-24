<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Resultado de Transferencia</title>
    <style>
        body { font-family: Arial; background: #eef1f5; padding-top: 50px; }
        .card {
            background: white; padding: 30px; width: 40%;
            margin: auto; border-radius: 10px;
            text-align: center; box-shadow: 0 3px 10px rgba(0,0,0,0.2);
        }
        a {
            text-decoration: none; padding: 10px 20px;
            background: #0066cc; color: white; border-radius: 6px;
        }
        a:hover { background: #004999; }
    </style>
</head>
<body>

<div class="card">
    <h2>Resultado de la Operación</h2>
    <p>${mensaje}</p>

    <a href="movimientos.jsp">Volver</a>
</div>

</body>
</html>
