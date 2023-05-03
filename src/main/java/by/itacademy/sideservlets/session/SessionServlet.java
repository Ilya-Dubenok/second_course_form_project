package by.itacademy.sideservlets.session;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet(urlPatterns = "/session")
public class SessionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        Map<String, String[]> parameterMap = req.getParameterMap();

        PrintWriter writer = resp.getWriter();

        if (parameterMap.containsKey("firstName") && parameterMap.containsKey("lastName")) {

            String firstName = parameterMap.get("firstName")[0];
            String lastName = parameterMap.get("lastName")[0];

            HttpSession session = req.getSession();
            session.setAttribute("firstName", firstName);
            session.setAttribute("lastName", lastName);

            writer.write("Hello " + firstName + " " + lastName);

        } else {

            HttpSession session = req.getSession(false);

            if (null == session
                    || null == session.getAttribute("firstName")
                    || null == session.getAttribute("lastName")) {

                resp.sendError(400);
            } else {

                writer.write("Hello " + session.getAttribute("firstName") + " " + session.getAttribute("lastName"));

            }


        }

    }
}
