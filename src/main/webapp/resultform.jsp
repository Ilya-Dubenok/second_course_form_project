<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <title>Results</title>
</head>
<body>
    <c:forEach var="entry" items="${artistMap}">

        <c:out value="${entry.key.name} -> " />

            <c:out value="${entry.value}" />
        </br>

    </c:forEach>

</br>

<c:forEach var="entry" items="${genreMap}">

    <c:out value="${entry.key.name} -> " />

        <c:out value="${entry.value}" />
    </br>

</c:forEach>

</br>


<c:forEach var="entry" items="${aboutMap}">

    <c:out value="${entry.value} -> " />

        <c:out value="${entry.key}" />
    </br>

</c:forEach>

</body>
</html>