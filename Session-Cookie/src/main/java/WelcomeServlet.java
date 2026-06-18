import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/welcome")
public class WelcomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        PrintWriter out = resp.getWriter();

        Cookie[] cookies = req.getCookies();

        String username = "Guest";

        if (cookies != null){
            for (Cookie cookie : cookies){
                if ("username".equals(cookie.getName())){
                    username = cookie.getValue();
                    break;
                }
            }
        }
        out.println("<h2>Welcome " + username + "</h2>");
    }
}
