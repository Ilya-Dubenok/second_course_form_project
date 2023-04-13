<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="by.itacademy.servletproject.business.VoteProcessor"%>
<%@page import="java.util.*"%>
<%@page import="by.itacademy.servletproject.dto.IDTO"%>

<%

List<IDTO> playersList = (List<IDTO>)request.getAttribute("playersList");
List<IDTO> genresList = (List<IDTO>)request.getAttribute("genresList");

%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>First JSP App</title>
    </head>
    <body>

        <form name="loginForm" method="post" action="formserv">

            <p>ВЫБЕРИ ЛЮБИМОГО ИСПОЛНИТЕЛЯ </p>
            <%
                for(int i = 0; i < playersList.size(); i++)
                {
                %>
                <input type="radio" name="player" value=<%= playersList.get(i).getId()%>><%=playersList.get(i).getName()%>
                <%
                }
            %>
            <br><br><br>

            <p>ВЫБЕРИ ОТ 3 ДО 5 ЖАНРОВ</p>

            <%
                        for(int i = 0; i < genresList.size(); i++)
                        {
                        %>
                        <input type="checkbox" name="genre" value=<%= genresList.get(i).getId()%>><%=genresList.get(i).getName()%>
                        <%
                        }
            %>

            <br><br><br>


            <p>ТВОЕ ОПИСАНИЕ</p>
            <textarea rows="5" cols="30" name="description"></textarea>



            <input type="Submit" value="Submit">

        </form>

    </body>

</html>