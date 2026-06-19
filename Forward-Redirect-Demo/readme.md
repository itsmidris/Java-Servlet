# Forward vs Redirect in Servlet

## Overview

Forward and Redirect are two ways to transfer control from one resource to another in a Java Servlet application.

They look similar to the user but work completely differently internally.

---

# 1. Request Forwarding

Forward transfers the request from one servlet/JSP to another resource **inside the server**.

### Syntax

```java
RequestDispatcher rd = request.getRequestDispatcher("/welcome");
rd.forward(request, response);
```

### Flow

```text
Browser
   |
   | Request
   v
LoginServlet
   |
   | forward()
   v
WelcomeServlet
   |
   v
Response
```

### Characteristics

* Same Request Object
* Same Response Object
* URL does NOT change
* Faster
* Request Attributes are preserved
* Happens internally on the server

### Example

```java
String username = request.getParameter("username");

request.setAttribute("user", username);

RequestDispatcher rd =
        request.getRequestDispatcher("/welcome");

rd.forward(request, response);
```

### Accessing Forwarded Data

```java
String username =
        (String) request.getAttribute("user");
```

---

# 2. Redirect

Redirect tells the browser to make a completely new request to another URL.

### Syntax

```java
response.sendRedirect("login.html");
```

### Flow

```text
Browser
   |
   | Request
   v
LogoutServlet
   |
   | sendRedirect()
   v
Browser
   |
   | New Request
   v
login.html
```

### Characteristics

* New Request Object
* New Response Object
* URL Changes
* Slightly Slower
* Request Attributes Lost
* Browser creates a new request

---

# Project Flow

```text
index.html
    |
    v
login.html
    |
    | POST
    v
LoginServlet
    |
    | Forward
    v
WelcomeServlet
    |
    | Click Logout
    v
LogoutServlet
    |
    | Redirect
    v
login.html
```

---

# Project Structure

```text
Forward-Redirect-Demo
│
├── src
│   └── main
│       ├── java
│       │   ├── LoginServlet.java
│       │   ├── WelcomeServlet.java
│       │   └── LogoutServlet.java
│       │
│       └── webapp
│           ├── index.html
│           ├── login.html
│           └── WEB-INF
│
└── pom.xml
```

---

# When to Use Forward?

Use Forward when:

* Passing data between servlets
* Moving from Servlet to JSP
* Internal navigation
* Login success pages
* MVC architecture

Example:

```java
RequestDispatcher rd =
        request.getRequestDispatcher("/dashboard");

rd.forward(request, response);
```

---

# When to Use Redirect?

Use Redirect when:

* Logout
* Redirecting to another application
* Redirecting to external URLs
* Avoiding form resubmission (PRG Pattern)

Example:

```java
response.sendRedirect("login.html");
```

---

# Forward vs Redirect Comparison

| Feature                    | Forward   | Redirect |
| -------------------------- | --------- | -------- |
| Performed By               | Server    | Browser  |
| Request Object             | Same      | New      |
| Response Object            | Same      | New      |
| URL Changes                | No        | Yes      |
| Speed                      | Faster    | Slower   |
| Request Attributes         | Preserved | Lost     |
| Extra Request              | No        | Yes      |
| Can Go Outside Application | No        | Yes      |

---

# Interview Questions

### What is Forward?

Forward transfers a request internally from one resource to another using the same request and response objects.

### What is Redirect?

Redirect sends a response to the browser instructing it to create a new request to another URL.

### Which is Faster?

Forward is faster because it does not create a new request.

### Does URL Change in Forward?

No.

### Does URL Change in Redirect?

Yes.

### Are Request Attributes Available After Redirect?

No, because a new request is created.

---

# Easy Trick to Remember

### Forward

```text
Server → Server
```

* Same Request
* Same Response
* URL Same

### Redirect

```text
Server → Browser → Server
```

* New Request
* URL Changes
* Request Data Lost

---

# One-Line Interview Answer

**Forward** uses the same request and response objects and does not change the URL.

**Redirect** creates a new request through the browser and changes the URL.
