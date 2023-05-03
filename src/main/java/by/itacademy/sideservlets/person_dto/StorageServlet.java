package by.itacademy.sideservlets.person_dto;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@WebServlet(urlPatterns = "/person")
public class StorageServlet extends HttpServlet {

    private static final String ATTRIBUTE_NAME = "person";


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        String storage = req.getHeader("storage");
        PrintWriter writer = resp.getWriter();

        if ("cookie".equals(storage)) {
            Cookie[] cookies = req.getCookies();
            if (null == cookies) {
                resp.sendError(400, "У тебя нет печенек");
                return;
            }

            Optional<Cookie> personCookie = Arrays.stream(cookies)
                    .filter(x -> x.getName().equals(ATTRIBUTE_NAME))
                    .findAny();

            if (personCookie.isEmpty()) {
                resp.sendError(400,"в твоей печеньше нет нужного параметра");
                return;
            }

            int id;
            try {
                id = Integer.parseInt(personCookie.get().getValue());
            } catch (NumberFormatException e) {
                resp.sendError(400, "В твоей куке ошибка");
                return;
            }

            PersonDTO personDTO = Storage.COOKIE.getPerson(id);

            if (null != personDTO) {
                writer.write("Имя: " + personDTO.getFirstName() + " Фамилия:" + personDTO.getLastName() + " Возраст" + personDTO.getAge());
            } else {
                resp.sendError(400, "Такого человека нет в базе");
            }

        } else if ("session".equals(storage)) {
            HttpSession session = req.getSession(false);
            if (null == session) {
                resp.sendError(400, " ты пришел без сессии ");
                return;
            }
            int id = (int) session.getAttribute(ATTRIBUTE_NAME);
            PersonDTO personDTO = Storage.SESSION.getPerson(id);
            writer.write("Имя: " + personDTO.getFirstName() + " Фамилия:" + personDTO.getLastName() + " Возраст" + personDTO.getAge());


        } else {

            resp.sendError(400, "ты пришел без указания хранилища");
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
                && ("cookie".equals(storage) || "session".equals(storage))
        ) {


            String firstName = parameterMap.get("firstName")[0];
            String lastName = parameterMap.get("lastName")[0];
            int age;
            try {

                age = Integer.parseInt(parameterMap.get("age")[0]);
            } catch (NumberFormatException e) {
                resp.sendError(400, "в твоей печеньке ошибка в данных");
                return;

            }

            PersonDTO personDTO = new PersonDTO(firstName, lastName, age);

            if ("cookie".equals(storage)) {
                Storage.COOKIE.putPerson(personDTO.getId(), personDTO);
                resp.addCookie(new Cookie(ATTRIBUTE_NAME, String.valueOf(personDTO.getId())));

            } else {
                Storage.SESSION.putPerson(personDTO.getId(), personDTO);
                HttpSession session = req.getSession();
                session.setAttribute(ATTRIBUTE_NAME, personDTO.getId());
            }

            writer.write("person was put in " + storage + " storage");

        } else {
            resp.sendError(400, "неверно заданы параметры или хранилище");

        }
    }


    enum Storage {
        COOKIE(new HashMap<>()), SESSION(new HashMap<>());

        private Map<Integer, PersonDTO> storage;

        Storage(Map<Integer, PersonDTO> storage) {
            this.storage = storage;
        }

        public PersonDTO getPerson(int id) {
            return storage.get(id);
        }

        public void putPerson(Integer key, PersonDTO value) {
            this.storage.put(key, value);
        }


    }
}
