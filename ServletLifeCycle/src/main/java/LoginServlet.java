import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    public LoginServlet(){
        System.out.println("Constructor() Called");
    }

    @Override
    public void init() throws ServletException {
        System.out.println("init() Called");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doGet() Called");
    }

    @Override
    public void destroy() {
        System.out.println("destroy() called");
    }
}
