
<%

String [] players = (String[])request.getAttribute("players");
String [] genres = (String[])request.getAttribute("genres");

%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

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
                for(int i = 0; i < players.length; i++)
                {
                %>
                <input type="radio" name="player" value=<%= players[i]%>><%=players[i]%>
                <%
                }
            %>
            <br><br><br>

            <p>ВЫБЕРИ ОТ 3 ДО 5 ЖАНРОВ</p>

            <%
                        for(int i = 0; i < genres.length; i++)
                        {
                        %>
                        <input type="checkbox" name="genre" value=<%= genres[i]%>><%=genres[i]%>
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