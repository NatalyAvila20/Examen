<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Ingreso Ejecutivo</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            background: linear-gradient(135deg, #0E4D92, #1E90FF);
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            color: #fff;
        }

        .login-box {
            background: rgba(255, 255, 255, 0.95);
            color: #333;
            padding: 30px;
            border-radius: 10px;
            width: 350px;
            text-align: center;
            box-shadow: 0px 4px 15px rgba(0,0,0,0.3);
        }

        input[type="text"],
        input[type="password"] {
            width: 90%;
            padding: 10px;
            margin-top: 10px;
            border-radius: 6px;
            border: 1px solid #aaa;
        }

        input[type="submit"] {
            margin-top: 20px;
            width: 100%;
            padding: 12px;
            background-color: #0E4D92;
            border: none;
            color: white;
            border-radius: 6px;
            font-size: 16px;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #003d73;
        }
    </style>

</head>
<body>

<div class="login-box">
    <h2>Ingreso Ejecutivo</h2>

    <!-- IMPORTANTE: agregar password e ID para Selenium -->
    <form action="login" method="post">
        <input type="text" placeholder="Ingrese su RUT" name="rut" required>
        <input type="password" placeholder="Ingrese su contraseña" name="password" required>

        <!-- ESTE ID ES NECESARIO PARA SELENIUM -->
        <input type="submit" id="btnLogin" value="Ingresar">
    </form>
</div>

</body>
</html>
