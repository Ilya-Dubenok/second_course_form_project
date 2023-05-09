<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <title>Vote Form</title>
</head>
<body>
    <form action="formserv" method="POST">
        <p>Выбери исполнителя</p>
    </br>

    <c:forEach var="artist" items="${artistList}">
        <input type ="radio" name="artist" value=${artist.id}>
        <c:out value="${artist.name}" />
    </br>

</c:forEach>

<p>Выбери жанры от 3 до 5</p>
</br>

<c:forEach var="genre" items="${genreList}">
    <input type ="checkbox" name="genre" value=${genre.id}>
    <c:out value="${genre.name}" />
</br>

</c:forEach>

<p>ТВОЕ ОПИСАНИЕ</p>
<textarea rows="5" cols="30" name="about"></textarea>



<input type="Submit" value="Submit">

</form>
</body>
</html>