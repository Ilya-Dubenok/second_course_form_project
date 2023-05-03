package by.itacademy.sideservlets.cookie;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet(urlPatterns = "/cookie")
public class CookieServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        Map<String, String[]> parameterMap = req.getParameterMap();

        PrintWriter writer = resp.getWriter();
        if (parameterMap.containsKey("firstName") && parameterMap.containsKey("lastName")) {

            String firstName = parameterMap.get("firstName")[0];
            String lastName = parameterMap.get("lastName")[0];

            Cookie cookie1 = new Cookie("firstName", firstName);
            Cookie cookie2 = new Cookie("lastName", lastName);
            resp.addCookie(cookie1);
            resp.addCookie(cookie2);

            writer.write("Hello " + firstName + " " + lastName);

        } else {

            Cookie[] cookies = req.getCookies();


            if (cookies == null
                    || !cookies[0].getName().equals("firstName")
                    || !cookies[1].getName().equals("lastName")) {

                resp.sendError(400);
            } else {

                writer.write("Hello " + cookies[0].getValue() + " " + cookies[1].getValue());

            }


        }

    }
}
