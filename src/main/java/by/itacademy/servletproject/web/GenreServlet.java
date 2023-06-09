package by.itacademy.servletproject.web;

import by.itacademy.servletproject.core.dto.GenreCreateDTO;
import by.itacademy.servletproject.core.dto.GenreDTO;
import by.itacademy.servletproject.daO.memory.GenreMemoryDao;
import by.itacademy.servletproject.service.GenreService;
import by.itacademy.servletproject.service.api.IGenreService;
import by.itacademy.servletproject.service.factory.GenreServiceFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = "/genre")
public class GenreServlet extends HttpServlet {

    private final IGenreService genreService;

    public GenreServlet() {
        this.genreService = GenreServiceFactory.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter writer = resp.getWriter();

        List<GenreDTO> genreDTOS = genreService.get();

        genreDTOS.forEach(x ->
                writer.write(x.getId() + " - " + x.getName() + "</br>"));

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");

        GenreCreateDTO dto = new GenreCreateDTO(name);
        this.genreService.save(dto);

    }
}
