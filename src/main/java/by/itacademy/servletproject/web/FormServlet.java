package by.itacademy.servletproject.web;

import by.itacademy.servletproject.core.dto.VoteCreateDTO;

import by.itacademy.servletproject.service.api.IArtistService;
import by.itacademy.servletproject.service.api.IGenreService;
import by.itacademy.servletproject.service.factory.ArtistServiceFactory;
import by.itacademy.servletproject.service.factory.GenreServiceFactory;
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


    private static final String ARTIST_PARAM_NAME = "artist";
    private static final String GENRE_PARAM_NAME = "genre";
    private static final String ABOUT_PARAM_NAME = "about";


    private final IGenreService genreService;
    private final IArtistService artistService;


    public FormServlet() {
        this.artistService = ArtistServiceFactory.getInstance();
        this.genreService = GenreServiceFactory.getInstance();
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
            writer.write("\t\t\t\t<p><input type=\"radio\" name=\"" + ARTIST_PARAM_NAME + "\" value=\"" + artist.getId() + "\"> " + artist.getName() + "</p>\n");
        }
        writer.write("\t\t\t</label>\n" +
                "\t\t\t<label> Выберите жанр\n");
        List<GenreDTO> genres = genreService.get();
        for (GenreDTO genre : genres) {
            writer.write("\t\t\t\t<p><input type=\"checkbox\" name=\"" + GENRE_PARAM_NAME + "\" value=\"" + genre.getId() + "\"> " + genre.getName() + "</p>\n");
        }
        writer.write("\t\t\t</label>\n" +
                "\t\t\t<label>Информация\n" +
                "\t\t\t\t<textarea name=\"" + ARTIST_PARAM_NAME + "\"></textarea>\n" +
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

        String[] artistsRaw = parameterMap.get(ARTIST_PARAM_NAME);

        if (artistsRaw.length > 1) {
            throw new IllegalArgumentException("Слишком много артистов");
        }

        Integer artist = null;
        if (artistsRaw.length == 1) {

            artist = Integer.parseInt(artistsRaw[0]);
        }


        String[] genresRaw = parameterMap.get(GENRE_PARAM_NAME);

        Integer[] genres = new Integer[genresRaw.length];

        for (int i = 0; i < genresRaw.length; i++) {
            genres[i] = Integer.parseInt(genresRaw[i]);
        }


        String[] aboutRaw = parameterMap.get(ABOUT_PARAM_NAME);

        if (aboutRaw.length > 1) {
            throw new IllegalArgumentException("Слишком много о себе");
        }

        String about = null;

        if (aboutRaw.length == 1) {
            about = aboutRaw[0];
        }

        VoteCreateDTO dto = new VoteCreateDTO(artist, genres, about);


    }
}


