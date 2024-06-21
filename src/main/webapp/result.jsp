<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Результати перекладу</title>
</head>
<body>
    <h1>Результати перекладу</h1>
    <c:if test="${not empty translations}">
        <ul>
            <c:forEach var="translation" items="${translations}">
                <li>${translation}</li>
            </c:forEach>
        </ul>
    </c:if>
    <c:if test="${empty translations}">
        <p>Переклад не знайдено</p>
    </c:if>
    <a href="index.jsp">Повернутися</a>
</body>
</html>

