package by.itacademy.servletproject.web;

import by.itacademy.servletproject.business.VoteProcessor;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;



@WebServlet(name = "FormServlet", urlPatterns = "/formserv")
public class FormServlet extends HttpServlet {

    private VoteProcessor voteProcessor;



    public FormServlet() {
        voteProcessor = new VoteProcessor();
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        req.setAttribute("playersList", voteProcessor.getPlayers());
        req.setAttribute("genresList", voteProcessor.getGenres());
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

        writer.write("Рейтинг исполнителей:");
        voteProcessor.getVotesForPlayersSorted().forEach(
                (K,V)->writer.write("<br>" +K.getName() + " -> " + V)
        );


        writer.write("<br><br> Рейтинг жанров:");

        voteProcessor.getVotesForGenresSorted().forEach(
                (K,V)->writer.write("<br>" +K.getName() + " -> " + V)
        );

        writer.write("<br><br> Описания с датой и временем поста:");

        voteProcessor.getDescriptionsSorted().forEach(
                (K,V)->writer.write("<br>" + V + " -> " + K)
        );


    }

    private void putAllData(String[] votesForGenre, String[] votesForPlayers, String description) {

            voteProcessor.incrementVotesForGenres(votesForGenre);
            voteProcessor.incrementVotesForPlayers(votesForPlayers);
            voteProcessor.putDescription(description);
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
