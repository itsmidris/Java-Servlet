import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/welcome")
public class WelcomeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        String username = (String) req.getAttribute("user");

        PrintWriter out = resp.getWriter();

        out.println("<html><body>");

        out.println("<h2>Welcome " + username + "</h2>");

        out.println("<p>This page was reached using FORWARD.</p>");

        out.println("<a href='logout'>Logout</a>");

        out.println("</body></html>");

        out.close();
    }
}
