# Servlet doGet() vs doPost()

## What is doGet()?

`doGet()` is executed when the browser sends a **GET request**.

Used for:

* Viewing data
* Searching data
* Fetching records
* Opening pages
* Contact Us page

Example:

```html
<a href="login">Contact Us</a>
```

Browser sends:

```http
GET /login
```

Servlet executes:

```java
doGet(HttpServletRequest req, HttpServletResponse res)
```

---

## What is doPost()?

`doPost()` is executed when the browser sends a **POST request**.

Used for:

* Login
* Registration
* Saving data
* Updating data
* Form submission

Example:

```html
<form action="login" method="post">
```

Browser sends:

```http
POST /login
```

Servlet executes:

```java
doPost(HttpServletRequest req, HttpServletResponse res)
```

---

## How Tomcat Decides?

Tomcat checks the HTTP Request Method.

Conceptually:

```java
if(requestMethod.equals("GET")) {
    doGet(req, res);
}
else if(requestMethod.equals("POST")) {
    doPost(req, res);
}
```

Tomcat performs this internally.

---

## Flow of doGet()

```text
User Clicks Link
        ↓
GET Request
        ↓
Tomcat
        ↓
doGet()
        ↓
Response
```

---

## Flow of doPost()

```text
User Fills Form
        ↓
Submit
        ↓
POST Request
        ↓
Tomcat
        ↓
doPost()
        ↓
Process Data
        ↓
Response
```

---

## Key Difference

| doGet()                        | doPost()                            |
| ------------------------------ | ----------------------------------- |
| Retrieves data                 | Sends data                          |
| Data appears in URL            | Data sent in request body           |
| Used for search/view pages     | Used for forms, login, registration |
| Less secure for sensitive data | More secure than GET                |

---

## Interview Answer

`doGet()` is used to retrieve or display data.

`doPost()` is used to send, process, or save data submitted by the client.
