package by.itacademy.servletproject.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;


@WebServlet(name = "FormServlet", urlPatterns = "/formserv")
public class FormServlet extends HttpServlet {

    private String[] players = {"Updated", "Metallica", "Tarja", "AC_DC"};
    private String[] genres = {"Rock", "HardRock", "Metal", "POP", "RNB", "Blues", "Rap", "Classic", "Country", "Jazz"};

    private Map<String, Integer> playersVotes = new HashMap<>();
    private Map<String, Integer> genreVotes = new HashMap<>();
    private Map<LocalDateTime, String> descriptions = new HashMap<>();

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");

    {
        for (String player : players) {
            playersVotes.put(player, 0);
        }

        for (String genre : genres) {
            genreVotes.put(genre, 0);
        }
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        req.setAttribute("players", players);
        req.setAttribute("genres", genres);
        getServletContext().getRequestDispatcher("/form.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] votesForPlayers = req.getParameterValues("player");
        String[] votesForGenre = req.getParameterValues("genre");
        String description = req.getParameter("description");
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        if (mistakeInSomething(votesForPlayers, votesForGenre, description, writer)) {
            return;
        }

        putAllData(votesForGenre, votesForPlayers, description);

        printVotes(writer);


    }

    private void printVotes(PrintWriter writer) {

        writer.write("Рейтинг голосов:");

        playersVotes.entrySet().stream().sorted((o1, o2) -> o2.getValue() - o1.getValue()).forEach(
                E -> writer.write("<br>" + E.getKey() + " -> " + E.getValue()));

        writer.write("<br><br> Рейтинг жанров:");

        genreVotes.entrySet().stream().sorted((o1, o2) -> o2.getValue() - o1.getValue()).forEach(
                E -> writer.write("<br>" + E.getKey() + " -> " + E.getValue()));

        writer.write("<br><br> Описания с датой и временем поста:");

        descriptions.entrySet().stream().sorted((o1, o2) -> o2.getKey().compareTo(o1.getKey())).forEach(
                E -> writer.write("<br>" + E.getValue() + " -> " + E.getKey().format(formatter)));


    }

    private void putAllData(String[] votesForGenre, String[] votesForPlayers, String description) {
        for (String vote : votesForGenre) {
            genreVotes.compute(vote,(k1,v1)-> v1==null? 0: v1+1);
        }
        for (String vote : votesForPlayers) {
            playersVotes.put(vote, playersVotes.get(vote) + 1);
        }

        descriptions.put(LocalDateTime.now(), description);

    }

    private boolean mistakeInSomething(String[] votesForPlayers, String[] votesForGenre, String description, PrintWriter writer) {
        boolean flag = false;
        if (votesForPlayers == null || votesForPlayers.length > 1) {
            flag = true;
            writer.write("<p>Неправильно указано количество исполнителей</p>");
        }
        if (votesForGenre == null || votesForGenre.length < 3 || votesForGenre.length > 5) {
            flag = true;
            writer.write("<p>Накосячил с количеством жанром</p>");
        }
        if (description == null || description.isBlank()) {
            flag = true;
            writer.write("<p>Описание ничего не содержит</p>");
        }

        return flag;

    }
}
