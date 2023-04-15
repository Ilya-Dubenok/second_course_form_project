package by.itacademy.servletproject.web;

import by.itacademy.servletproject.daO.memory.ArtistMemoryDao;
import by.itacademy.servletproject.daO.memory.GenreMemoryDao;
import by.itacademy.servletproject.service.ArtistService;
import by.itacademy.servletproject.service.GenreService;
import by.itacademy.servletproject.service.api.IArtistService;
import by.itacademy.servletproject.service.api.IGenreService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import by.itacademy.servletproject.core.dto.ArtistDTO;
import by.itacademy.servletproject.core.dto.GenreDTO;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@WebServlet(name = "FormServlet", urlPatterns = "/formserv")
public class FormServlet extends HttpServlet {


    private final IGenreService genreService;
    private final IArtistService artistService;


    public FormServlet() {
        this.artistService = new ArtistService(new ArtistMemoryDao());
        this.genreService = new GenreService(new GenreMemoryDao());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        PrintWriter writer = resp.getWriter();

        writer.write("<!DOCTYPE html>\n" +
                "<html>\n" +
                "\t<head>\n" +
                "\t\t<meta charset=\"utf-8\">\n" +
                "\t\t<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                "\t\t<title>Голосование</title>\n" +
                "\t</head>\n" +
                "\t<body>\n" +
                "\t\t<form action=\"formserv\" method=\"POST\">\n" +
                "\t\t\t<label> Выберите артиста\n");
        List<ArtistDTO> artists = artistService.get();
        for (ArtistDTO artist : artists) {
            writer.write("\t\t\t\t<p><input type=\"radio\" name=\"artist\" value=\"" + artist.getId() + "\"> " + artist.getName() + "</p>\n");
        }
        writer.write("\t\t\t</label>\n" +
                "\t\t\t<label> Выберите жанр\n");
        List<GenreDTO> genres = genreService.get();
        for (GenreDTO genre : genres) {
            writer.write("\t\t\t\t<p><input type=\"checkbox\" name=\"genre\" value=\"" + genre.getId() + "\"> " + genre.getName() + "</p>\n");
        }
        writer.write("\t\t\t</label>\n" +
                "\t\t\t<label>Информация\n" +
                "\t\t\t\t<textarea name=\"about\"></textarea>\n" +
                "\t\t\t</label>\n" +
                "\t\t\t<p><input type=\"submit\" value=\"Отправить\"></p>\n" +
                "\t\t</form>\n" +
                "\t</body>\n" +
                "</html>");
    }

    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        Map<String, String[]> parameterMap = req.getParameterMap();

        PrintWriter writer = resp.getWriter();

        parameterMap.forEach((k, v) -> {
            writer.write(k + ": [");
            writer.write(String.join(", ", v));
            writer.write("]</br>");
        });
    }
}


