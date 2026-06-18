import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        //Dummy Validation
        if ("admin".equals(username) && "1234".equals(password)){

            //session
            HttpSession session = req.getSession();
            session.setAttribute("username", username);

            //cookie
            Cookie cookie = new Cookie("username", username);
            cookie.setMaxAge(60*60); // 1 hour

            resp.addCookie(cookie);

            out.println("<h2>Login Successful</h2>");
            out.println("<a href='welcome'>Go To Welcome Page</a>");
            out.println("<br><br>");
            out.println("<a href='profile'>Go To Profile Page</a>");
        }else {
            out.println("<h2>Invalid Username or Password</h2>");
        }

        out.close();



    }
}
