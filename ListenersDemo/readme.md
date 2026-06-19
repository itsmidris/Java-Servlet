# Servlet Listeners Notes

## What is a Listener?

A Listener is a special class that listens for events occurring in a Servlet application and executes code automatically when those events happen.

Examples:

* Application starts
* Application stops
* Session created
* Session destroyed
* Request received
* Request completed

---

# Why Use Listeners?

Listeners are used for:

* Logging
* Counting online users
* Session tracking
* Application startup tasks
* Audit monitoring

---

# Types of Important Listeners

## 1. ServletContextListener

Used for application-level events.

### Methods

```java
contextInitialized()
contextDestroyed()
```

### Purpose

* Runs when Tomcat starts the application.
* Runs when Tomcat shuts down the application.

### Example

```java
@WebListener
public class AppListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Application Started");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Application Stopped");
    }
}
```

### Output

```text
Application Started
Application Stopped
```

---

## 2. HttpSessionListener

Used for session events.

### Methods

```java
sessionCreated()
sessionDestroyed()
```

### Purpose

* Detect user login session creation.
* Detect logout or session expiration.

### Example

```java
@WebListener
public class SessionListener
        implements HttpSessionListener {

    private static int activeUsers = 0;

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        activeUsers++;
        System.out.println("Session Created");
        System.out.println("Users Online: " + activeUsers);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        activeUsers--;
        System.out.println("Session Destroyed");
        System.out.println("Users Online: " + activeUsers);
    }
}
```

### Output

```text
Session Created
Users Online: 1

Session Destroyed
Users Online: 0
```

---

## 3. ServletRequestListener

Used for request events.

### Methods

```java
requestInitialized()
requestDestroyed()
```

### Purpose

* Executes before request processing.
* Executes after response is sent.

### Example

```java
@WebListener
public class RequestListener
        implements ServletRequestListener {

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        System.out.println("Request Started");
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        System.out.println("Request Finished");
    }
}
```

### Output

```text
Request Started
Request Finished
```

---

# Complete Project Flow

## index.html

```html
<form action="login" method="post">
    <input type="text" name="username">
    <button type="submit">Login</button>
</form>
```

---

## LoginServlet

```java
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp)
            throws IOException {

        resp.setContentType("text/html");

        String username =
                req.getParameter("username");

        HttpSession session =
                req.getSession();

        session.setAttribute("user", username);

        PrintWriter out = resp.getWriter();

        out.println("<h2>Welcome " + username + "</h2>");
        out.println("<a href='logout'>Logout</a>");
    }
}
```

---

## LogoutServlet

```java
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws IOException {

        resp.setContentType("text/html");

        HttpSession session =
                req.getSession(false);

        if (session != null) {
            session.invalidate();
        }

        PrintWriter out = resp.getWriter();

        out.println("<h2>Logged Out Successfully</h2>");
    }
}
```

---

# Request Flow

```text
Browser
   ↓
index.html
   ↓
POST /login
   ↓
RequestListener
(Request Started)
   ↓
LoginServlet
   ↓
request.getSession()
   ↓
SessionListener
(Session Created)
   ↓
Response Sent
   ↓
RequestListener
(Request Finished)
```

---

# Logout Flow

```text
Browser
   ↓
GET /logout
   ↓
LogoutServlet
   ↓
session.invalidate()
   ↓
SessionListener
(Session Destroyed)
   ↓
Response Sent
```

---

# Listener vs Filter

| Feature               | Listener | Filter |
| --------------------- | -------- | ------ |
| Event Based           | Yes      | No     |
| Request Interception  | No       | Yes    |
| Response Modification | No       | Yes    |
| Session Tracking      | Yes      | No     |
| Authentication        | No       | Yes    |
| Logging               | Yes      | Yes    |

---

# Interview Questions

### What is a Listener?

A Listener is a class that listens for events in a Servlet application and performs actions automatically when those events occur.

---

### Which Listener is used when application starts?

```java
ServletContextListener
```

---

### Which Listener is used for session tracking?

```java
HttpSessionListener
```

---

### Which Listener executes for every request?

```java
ServletRequestListener
```

---

### Difference Between Filter and Listener?

Filter intercepts requests and responses.

Listener reacts to events such as application start, session creation, and request creation.

---

# Memory Trick

```text
ServletContextListener
    ↓
Application Start / Stop

HttpSessionListener
    ↓
Session Create / Destroy

ServletRequestListener
    ↓
Request Start / End
```

### Shortcut

```text
Context = Application

Session = User

Request = HTTP Request
```

---

# Important Notes

1. Use `@WebListener` to register listeners.
2. Use `@WebServlet` to register servlets.
3. Use `resp.setContentType("text/html")` when sending HTML response.
4. Session is created using:

```java
request.getSession();
```

5. Session is destroyed using:

```java
session.invalidate();
```

6. Listeners run automatically. No need to call them manually.
