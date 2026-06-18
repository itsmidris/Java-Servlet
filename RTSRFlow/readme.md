Question
1. Explain the Request → Tomcat → Servlet → Response flow in Java Servlet.
2. Explain the lifecycle of a request in a Servlet application.
3. What happens when a user enters a URL in a browser and hits Enter?
4. How does Tomcat interact with a Servlet?
5. How does a Servlet process an HTTP request?

Answer:
User sends an HTTP request from the browser. 
Tomcat receives the request and creates HttpServletRequest and HttpServletResponse objects. 
Tomcat finds the appropriate Servlet and calls its doGet() or doPost() method. 
The Servlet processes the request, performs business logic, writes data to the response object, and returns control to Tomcat. 
Tomcat sends the HTTP response back to the browser, and the browser displays the result to the user.