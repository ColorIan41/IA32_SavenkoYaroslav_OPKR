<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Вхід в обліковий запис</title>
    </head>
    <body>
        <h1>Вхід в обліковий запис</h1>
        <form action="LoginServlet" method="post">
            <label for="username">Логін:</label>
            <input type="text" id="username" name="username" required>

            <label for="password">Пароль:</label>
            <input type="password" id="password" name="password" required>

            <input type="submit" value="Увійти">
        </form>
    </body>
</html>