package by.itacademy.sideservlets.person_dto;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@WebServlet(urlPatterns = "/person")
public class StorageServlet extends HttpServlet {

    private static final String ATTRIBUTE_NAME = "person";

    private final PersonService personService = PersonService.getInstance();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        String storage = req.getHeader("storage");
        PrintWriter writer = resp.getWriter();

        if (storage != null && personService.getStoragesNames().contains(storage.toUpperCase())) {
            try {
                PersonDTO personDTO = personService.getPerson(
                        req.getCookies(),
                        req.getSession(false),
                        ATTRIBUTE_NAME,
                        storage
                );

                writer.write(personDTO.getFirstName() + " " + personDTO.getLastName() + " " + personDTO.getAge());
            } catch (IllegalArgumentException e) {

                resp.sendError(400, e.getMessage());

            }

        } else {
            resp.sendError(400, "Не задано правильное хранилище в заголовке");

        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        Map<String, String[]> parameterMap = req.getParameterMap();
        String storage = req.getHeader("storage");
        PrintWriter writer = resp.getWriter();

        if (parameterMap.containsKey("firstName")
                && parameterMap.containsKey("lastName")
                && parameterMap.containsKey("age")
                && (null != storage)
        ) {
            try {
                PersonDTO personDTO = personService.putPerson(new PersonCreateDTO(
                        parameterMap.get("firstName")[0],
                        parameterMap.get("lastName")[0],
                        parameterMap.get("age")[0],
                        storage
                ));

                if (storage.toUpperCase().equals(PersonMemoryDao.Storage.SESSION.name())) {
                    HttpSession session = req.getSession();
                    session.setAttribute(ATTRIBUTE_NAME, personDTO.getId());

                } else if (storage.toUpperCase().equals(PersonMemoryDao.Storage.COOKIE.name())) {
                    resp.addCookie(
                            new Cookie(ATTRIBUTE_NAME, String.valueOf(personDTO.getId()))
                    );

                }


                writer.write("Person was put in " + storage + " storage");

            } catch (IllegalArgumentException e) {
                resp.sendError(400, e.getMessage());
            }


        } else {
            resp.sendError(400, "неверно заданы параметры или хранилище");

        }
    }



}
