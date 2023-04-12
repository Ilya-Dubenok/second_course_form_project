package by.itacademy.servletproject.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;

@WebServlet(name = "AddTwoNums", urlPatterns = "/addTwoNumsServ" )
public class MyFirstServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        resp.setContentType("text/html; charset=UTF-8");

        PrintWriter writer = resp.getWriter();

        Enumeration<String> parameterNames = req.getParameterNames();

        ArrayList<Integer> nums = new ArrayList<>();

        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();

            try {
                nums.add(Integer.parseInt(req.getParameter(paramName)));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }




        }

        if (nums.size() == 0) {
            writer.write("Не введено ни одного целого числа");
        } else {
            int sum = 0;
            for (Integer num : nums) {
                sum += num;
            }
            writer.write(String.valueOf(sum));
        }
    }
}
