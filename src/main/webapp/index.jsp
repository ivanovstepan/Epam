<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html >
    <title> Shop </title>
    <link rel="stylesheet" href="css/style.css">
<body>

<form method="post" action="/controller" class="login" accept-charset="utf-8">

    <p class="autoriz"> Авторизация </p>
    <input type="hidden" name="command" value="login">
    <p>
        <label>Логин:</label>
        <input type="text" name="login" value="" placeholder="name@example.com" >
    </p>
    <p>
        <label>Пароль:</label>
        <input type="password" name="password" value="" placeholder="password">
    </p>
    <div class="registration">
        <p>
            <label>Имя:</label>
            <input type="text" name="name" value="" placeholder="Иван">
        </p>
        <p>
            <label>Фамилия:</label>
            <input type="text" name="surname" value="" placeholder="Иванов">
        </p>
        <p>
            <label>E-mail:</label>
            <input type="text" name="email" value="" placeholder="name@example.com">
        </p>
    </div>
    <p class="login-submit">
        <button type="submit" class="login-button">Войти</button>
    </p>
    <p class="forgot-password">
        <a id="btn_register">Регистарция</a>
        <span>/</span>
        <a href="index.jsp">Забыли пароль?</a>

    </p>
</form>

</body>
    <script>
        var registerForm = document.querySelector('.registration');
        var registerBtn = document.querySelector('#btn_register');
        var inputCommand = document.querySelector('input[name="command"]');
        registerBtn.addEventListener('click', function(event) {
            registerForm.style.display = "block";
            inputCommand.value = "registration";
        });

    </script>
</html>
