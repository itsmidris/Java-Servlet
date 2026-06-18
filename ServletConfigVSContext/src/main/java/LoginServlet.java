import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {

    private String adminEmail;

    @Override
    public void init() throws ServletException {

        // ServletConfig
        ServletConfig config = getServletConfig();

        adminEmail =
                config.getInitParameter("adminEmail");

        System.out.println("Admin Email : " + adminEmail);
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws IOException {

        // ServletContext
        ServletContext context =
                getServletContext();

        String company =
                context.getInitParameter("company");

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        out.println("<h2>ServletConfig Example</h2>");
        out.println("Admin Email : " + adminEmail);

        out.println("<hr>");

        out.println("<h2>ServletContext Example</h2>");
        out.println("Company : " + company);
    }
}