<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Bienvenido</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f5f6fa;
            padding: 30px;
        }

        .card {
            background: white;
            width: 500px;
            margin: auto;
            padding: 25px;
            border-radius: 12px;
            box-shadow: 0 4px 15px rgba(0,0,0,0.1);
            text-align: center;
        }

        h1 {
            color: #0E4D92;
        }

        .dato {
            font-size: 20px;
            margin-top: 10px;
        }
    </style>

</head>
<body>

<div class="card">
    <h1>¡Bienvenido Ejecutivo!</h1>

    <p class="dato"><strong>Nombre:</strong> ${nombre}</p>
    <p class="dato"><strong>Departamento:</strong> ${departamento}</p>

</div>

</body>
</html>
