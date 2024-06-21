<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ page import="jakarta.servlet.ServletContext" %>
<%@ page import="java.util.List" %>
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
    <title>Редагування даних</title>
    <style>
        .tab {
            display: none;
        }

        .tab.active {
            display: block;
        }

        .tabs {
            cursor: pointer;
        }
    </style>
    <script>
        function showTab(tabId) {
            var tabs = document.getElementsByClassName('tab');
            for (var i = 0; i < tabs.length; i++) {
                tabs[i].classList.remove('active');
            }
            document.getElementById(tabId).classList.add('active');
        }
    </script>
</head>
<body>
    <h1>Редагування даних</h1>
    <div class="tabs">
        <button onclick="showTab('languagesTab')">Мови</button>
        <button onclick="showTab('wordsTab')">Слова</button>
        <button onclick="showTab('translationsTab')">Переклади</button>
    </div>
    
    <div id="languagesTab" class="tab active">
        <h2>Мови</h2>
        <form action="EditLanguagesServlet" method="post">
            <label for="newLanguage">Нова мова:</label>
            <input type="text" id="newLanguage" name="newLanguage">
            <input type="submit" value="Додати мову">
        </form>
        <ul>
            <c:forEach var="language" items="${languages}">
                <li>${language.name} <a href="EditLanguagesServlet?action=delete&id=${language.id}">Видалити</a></li>
            </c:forEach>
        </ul>
    </div>
    
    <div id="wordsTab" class="tab">
        <h2>Слова</h2>
        <form action="EditWordsServlet" method="post">
            <label for="newWord">Нове слово:</label>
            <input type="text" id="newWord" name="newWord">
            
            <label for="languageId">Мова:</label>
            <select id="languageId" name="languageId">
                <c:forEach var="language" items="${languages}">
                    <option value="${language.id}">${language.name}</option>
                </c:forEach>
            </select>
            
            <input type="submit" value="Додати слово">
        </form>
        <ul>
            <c:forEach var="word" items="${words}">
                <li>${word.word} (Language ID: ${word.languageId}) <a href="EditWordsServlet?action=delete&id=${word.id}">Видалити</a></li>
            </c:forEach>
        </ul>
    </div>
    
    <div id="translationsTab" class="tab">
        <h2>Переклади</h2>
        <form action="EditTranslationsServlet" method="post">
            <label for="wordId">Слово:</label>
            <select id="wordId" name="wordId">
                <c:forEach var="word" items="${words}">
                    <option value="${word.id}">${word.word}</option>
                </c:forEach>
            </select>
            
            <label for="languageId">Мова перекладу:</label>
            <select id="languageId" name="languageId">
                <c:forEach var="language" items="${languages}">
                    <option value="${language.id}">${language.name}</option>
                </c:forEach>
            </select>
            
            <label for="translation">Переклад:</label>
            <input type="text" id="translation" name="translation">
            
            <input type="submit" value="Додати переклад">
        </form>
        <ul>
            <c:forEach var="translation" items="${translations}">
                <li>${translation.translation} (Word ID: ${translation.wordId}, Language ID: ${translation.languageId}) <a href="EditTranslationsServlet?action=delete&id=${translation.id}">Видалити</a></li>
            </c:forEach>
        </ul>
    </div>
    
    <a href="AdminServlet">Назад до панелі</a>
</body>
</html>