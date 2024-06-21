<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="jakarta.servlet.ServletContext" %>
<%@ page import="model.*" %>
<%@ page import="service.*" %>
<%
    String sourceLanguageIdStr = request.getParameter("sourceLanguage");
    String targetLanguageIdStr = request.getParameter("targetLanguage");
    String wordText = request.getParameter("word");
    
    if (sourceLanguageIdStr != null && targetLanguageIdStr != null && wordText != null && !wordText.trim().isEmpty()) {
        int sourceLanguageId = Integer.parseInt(sourceLanguageIdStr);
        int targetLanguageId = Integer.parseInt(targetLanguageIdStr);

        ServletContext context = getServletContext();
        List<Translation> translations = DataStorage.getTranslations(context);
        List<Word> words = DataStorage.getWords(context);

        String translationResult = "Переклад не знайдено";

        for (Word word : words) {
            if (word.getLanguageId() == sourceLanguageId && word.getWord().equalsIgnoreCase(wordText)) {
                for (Translation translation : translations) {
                    if (translation.getWordId() == word.getId() && translation.getLanguageId() == targetLanguageId) {
                        translationResult = translation.getTranslation();
                        break;
                    }
                }
            }
        }

        request.setAttribute("translationResult", translationResult);
    } else {
        request.setAttribute("translationResult", "Будь ласка, виберіть мови та введіть слово.");
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Результат перекладу</title>
</head>
<body>
    <h1>Результат перекладу</h1>
    <p>${translationResult}</p>
    <a href="index.jsp">Назад</a>
</body>
</html>