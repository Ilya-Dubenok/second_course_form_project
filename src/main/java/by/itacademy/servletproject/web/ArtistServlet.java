package by.itacademy.servletproject.web;

import by.itacademy.servletproject.core.BeansFactory;
import by.itacademy.servletproject.core.dto.ArtistCreateDTO;
import by.itacademy.servletproject.core.dto.ArtistDTO;
import by.itacademy.servletproject.service.api.IArtistService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


@WebServlet(urlPatterns = "/artist")
public class ArtistServlet extends HttpServlet {

    private final IArtistService artistService;

    public ArtistServlet() {
        this.artistService = BeansFactory.getInstance().
                getBeanInstance(IArtistService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        PrintWriter writer = resp.getWriter();

        List<ArtistDTO> artistDTOS = artistService.get();

        artistDTOS.forEach(x ->
                writer.write(x.getId() + " - " + x.getName() + "</br>"));

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");

        ArtistCreateDTO dto = new ArtistCreateDTO(name);
        this.artistService.save(dto);

    }

}
