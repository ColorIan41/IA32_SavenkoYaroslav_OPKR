
<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Адміністраторська панель</title>
    </head>
    <body>
        <h1>Адміністраторська панель</h1>

        <h2>Мови</h2>
        <ul>
            <c:forEach var="language" items="${languages}">
                <li>${language.name}</li>
                </c:forEach>
        </ul>

        <h2>Слова</h2>
        <ul>
            <c:forEach var="word" items="${words}">
                <li>${word.word} (${word.languageId})</li>
                </c:forEach>
        </ul>

        <h2>Переклади</h2>
        <ul>
            <c:forEach var="translation" items="${translations}">
                <li>${translation.translation} (Word ID: ${translation.wordId}, Language ID: ${translation.languageId})</li>
                </c:forEach>
        </ul>
        <a href="editData.jsp">Редагувати дані (мови, слова, переклади)</a>

        <br>
        <a href="index.jsp">На головну</a>
    </body>
</html>