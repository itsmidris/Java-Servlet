Step-by-step
    1. Browser loads index.html
    
    You open:
    
    http://localhost:8080/MyApp/index.html
    
    Tomcat sends the HTML page to the browser.

    2. User fills the form
    <form action="login" method="post">
        <input type="text" name="username">
        <input type="submit">
    </form>
    
    Suppose the user enters:
    
    Imran
    
    and clicks Submit.

    3. Browser sends POST request
    
    Because:
    
    method="post"
    
    the browser sends:
    
    POST /MyApp/login
    
    along with:
    
    username=Imran
    4. Tomcat finds matching Servlet
    
    Tomcat sees:
    
    @WebServlet("/login")
    public class LoginServlet extends HttpServlet
    
    and thinks:
    
    "This request is for /login, so LoginServlet should handle it."

    5. Tomcat calls doPost()
    
    Because the request method is POST:
    
    POST /login
    
    Tomcat executes:
    
    doPost(request, response);
    
    If the form had:
    
    method="get"
    
    then Tomcat would call:
    
    doGet(request, response);
    
    instead.

    6. Servlet reads username
       String username =
       request.getParameter("username");
    
    This reads:
    
    username=Imran
    
    from the request.
    
    Now:
    
    username = "Imran";
    7. Servlet writes response
       out.println("<h1>Welcome " + username + "</h1>");
    
    creates:
    
    <h1>Welcome Imran</h1>
    8. Browser displays it
    
    Tomcat sends the response back.
    
    The browser shows:
    
    Welcome Imran
    Your understanding in one sentence
    
    index.html loads → 
    user submits form → 
    browser sends POST request to /login → 
    Tomcat finds LoginServlet → 
    Tomcat calls doPost() → 
    servlet reads username using request.getParameter() → 
    servlet writes response → browser displays the result.