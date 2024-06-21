<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="jakarta.servlet.ServletContext" %>
<%@ page import="model.*" %>
<%@ page import="service.*" %>
<%
    ServletContext context = getServletContext();
    List<Language> languages = DataStorage.getLanguages(context);
    List<Word> words = DataStorage.getWords(context);
    List<Translation> translations = DataStorage.getTranslations(context);
    request.setAttribute("languages", languages);
    request.setAttribute("words", words);
    request.setAttribute("translations", translations);
%>
<!DOCTYPE html>
<html>
    <head>
        <title>Онлайн Словник</title>
    </head>
    <body>
        <h1>Онлайн Словник</h1>
        <form action="translate.jsp" method="get">
            <label for="sourceLanguage">Виберіть мову джерела:</label>
            <select id="sourceLanguage" name="sourceLanguage">
                <c:forEach var="language" items="${languages}">
                    <option value="${language.id}">${language.name}</option>
                </c:forEach>
            </select>

            <label for="targetLanguage">Виберіть мову перекладу:</label>
            <select id="targetLanguage" name="targetLanguage">
                <c:forEach var="language" items="${languages}">
                    <option value="${language.id}">${language.name}</option>
                </c:forEach>
            </select>

            <label for="word">Слово:</label>
            <input type="text" id="word" name="word">

            <input type="submit" value="Перекласти">
        </form>
        <br>
        <a href="login.jsp">Адміністраторська панель</a>
    </body>
</html>