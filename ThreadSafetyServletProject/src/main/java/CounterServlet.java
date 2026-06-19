import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.atomic.AtomicInteger;

@WebServlet("/counter")
public class CounterServlet extends HttpServlet {

    private AtomicInteger counter = new AtomicInteger(0);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");

        PrintWriter out = resp.getWriter();

        int currentCount = counter.incrementAndGet();

        out.println("<html>");
        out.println("<body>");

        out.println("<h1>Thread Safety Example</h1>");
        out.println("<h2>Current Count : " + currentCount + "</h2>");

        out.println("<br>");

        out.println("<a href='counter'>Increase Again</a>");

        out.println("<br><br>");

        out.println("<a href='index.html'>Home</a>");

        out.println("</body>");
        out.println("</html>");
    }
}
