# Servlet Filters

## What is a Filter?

A Filter is a server-side component that intercepts requests and responses before they reach a Servlet or before the response reaches the client.

Think of a Filter as a security guard standing between the browser and the servlet.

```text
Browser
   ↓
Filter
   ↓
Servlet
   ↓
Response
```

---

## Why Use Filters?

Filters are commonly used for:

* Authentication
* Authorization
* Logging
* Request Validation
* Response Modification
* Data Compression
* Encryption

---

## Filter Lifecycle

A Filter has three lifecycle methods:

### init()

Called once when the filter is loaded.

```java
public void init(FilterConfig filterConfig) {
    System.out.println("Filter Initialized");
}
```

### doFilter()

Called for every request.

```java
public void doFilter(
        ServletRequest request,
        ServletResponse response,
        FilterChain chain)
```

This is the main method of a Filter.

### destroy()

Called when the server shuts down.

```java
public void destroy() {
    System.out.println("Filter Destroyed");
}
```

---

## FilterChain

The most important statement inside a filter is:

```java
chain.doFilter(request, response);
```

This line passes the request to the next Filter or Servlet.

Without this statement, the request stops at the Filter and never reaches the Servlet.

---

## Example Flow

```text
User submits form
        ↓
Filter executes
        ↓
chain.doFilter()
        ↓
Servlet executes
        ↓
Response generated
        ↓
Control returns to Filter
        ↓
Response sent to Browser
```

---

## Logging Filter Example

```java
@WebFilter("/login")
public class MyFilter implements Filter {

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        System.out.println("Request Entered Filter");

        chain.doFilter(request, response);

        System.out.println("Response Leaving Filter");
    }
}
```

---

## Authentication Filter Example

```java
@WebFilter("/home")
public class AuthFilter implements Filter {

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req =
                (HttpServletRequest) request;

        HttpSession session =
                req.getSession(false);

        if (session != null &&
                session.getAttribute("user") != null) {

            chain.doFilter(request, response);

        } else {

            ((HttpServletResponse) response)
                    .sendRedirect("index.html");
        }
    }
}
```

---

## Common Mistakes

### Wrong Annotation

Incorrect:

```java
@WebServlet("/home")
public class AuthFilter implements Filter
```

Correct:

```java
@WebFilter("/home")
public class AuthFilter implements Filter
```

---

### Missing Session Attribute

Filter checks:

```java
session.getAttribute("user")
```

But LoginServlet must store:

```java
session.setAttribute("user", username);
```

---

### Missing Target Servlet

If a Filter is mapped to:

```java
@WebFilter("/home")
```

Then a matching servlet must exist:

```java
@WebServlet("/home")
```

Otherwise Tomcat returns:

```text
404 Not Found
```

---

## Interview Definition

A Servlet Filter is a server-side component that intercepts incoming requests and outgoing responses. It is mainly used for authentication, authorization, logging, validation, and request/response processing.

---

## Memory Trick

```text
Filter = Security Guard of a Web Application
```

Request Flow:

```text
Browser
   ↓
Filter
   ↓
Servlet
   ↓
Response
```
