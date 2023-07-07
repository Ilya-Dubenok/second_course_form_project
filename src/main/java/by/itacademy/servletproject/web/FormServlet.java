package by.itacademy.servletproject.web;

import by.itacademy.servletproject.core.BeansFactory;
import by.itacademy.servletproject.core.dto.StatisticDTO;
import by.itacademy.servletproject.core.dto.VoteCreateDTO;

import by.itacademy.servletproject.service.api.IArtistService;
import by.itacademy.servletproject.service.api.IGenreService;
import by.itacademy.servletproject.service.api.IVoteService;
import by.itacademy.servletproject.service.api.IVoteStatisticsService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@WebServlet(name = "FormServlet", urlPatterns = "/formserv")
public class FormServlet extends HttpServlet {


    private static final String ARTIST_PARAM_NAME = "artist";
    private static final String GENRE_PARAM_NAME = "genre";
    private static final String ABOUT_PARAM_NAME = "about";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");


    private final IGenreService genreService;
    private final IArtistService artistService;
    private final IVoteService voteService;
    private final IVoteStatisticsService voteStatisticsService;


    public FormServlet() {
        this.artistService = BeansFactory.getInstance().getBeanInstance(IArtistService.class);
        this.genreService = BeansFactory.getInstance().getBeanInstance(IGenreService.class);
        this.voteService = BeansFactory.getInstance().getBeanInstance(IVoteService.class);
        this.voteStatisticsService = BeansFactory.getInstance().getBeanInstance(IVoteStatisticsService.class);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        RequestDispatcher dispatcher = req.getRequestDispatcher("/voteform.jsp");
        req.setAttribute("artistList",artistService.get());

        req.setAttribute("genreList",genreService.get());

        dispatcher.forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp)
            throws ServletException, IOException {


        Map<String, String[]> parameterMap = req.getParameterMap();


        PrintWriter writer = resp.getWriter();

        String[] artistsRaw = parameterMap.get(ARTIST_PARAM_NAME);

        if (artistsRaw == null) {
            throw new IllegalArgumentException("Не передано ни одного артиста");
        }
        if (artistsRaw.length > 1) {
            throw new IllegalArgumentException("Слишком много артистов");
        }

        Integer artist = null;
        if (artistsRaw.length == 1) {

            artist = Integer.parseInt(artistsRaw[0]);
        }


        String[] genresRaw = parameterMap.get(GENRE_PARAM_NAME);
        if (genresRaw == null) {
            throw new IllegalArgumentException("Не передано ни одного жанра");
        }

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
        voteService.save(dto);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/resultform.jsp");
        StatisticDTO resultVote = voteStatisticsService.getTop();
        req.setAttribute("artistMap",resultVote.getArtistTop());
        req.setAttribute("genreMap",resultVote.getGenreTop());
        req.setAttribute("aboutMap", getFormattedTimeMap(resultVote.getAboutTop()));
        dispatcher.forward(req, resp);

        }


    private Map<String, String> getFormattedTimeMap(Map<LocalDateTime, String> map) {
        Map<String, String> res = new LinkedHashMap<>();
        map.forEach((key1, value) -> {
            String key = key1.format(formatter);
            res.put(key, value);

        });
        return res;
    }
}


