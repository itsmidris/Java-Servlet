import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        out.println("<h2>Welcome</h2>");
        out.println("<p>You can contact us on 8459032652<p>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String name = req.getParameter("name");
        String course = req.getParameter("course");

        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        out.println("<h2>Student Details: </h2>");
        out.println("<p>Student Name: " +name + "<p>");
        out.println("<p>Student Course: " +course + "<p>");

    }
}
