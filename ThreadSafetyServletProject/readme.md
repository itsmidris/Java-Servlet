# Thread Safety in Servlet

## Definition

Thread Safety means ensuring that multiple users can access a servlet simultaneously without causing data corruption or unexpected results.

Since Tomcat creates only **one servlet object** and multiple request threads use that same object, shared data must be handled carefully.

---

## How Servlet Works

```text
Tomcat Starts
     |
     v
Creates ONE Servlet Object
     |
     v
init()
     |
     v
User1 --> Thread1 --> doGet()/doPost()
User2 --> Thread2 --> doGet()/doPost()
User3 --> Thread3 --> doGet()/doPost()
```

One Servlet Object → Many Threads

---

## Race Condition

### Unsafe Code

```java
private int count = 0;

protected void doGet(...) {
    count++;
}
```

### Problem

Suppose:

```java
count = 5;
```

Thread-1 reads 5

Thread-2 reads 5

Both increment:

```java
5 + 1 = 6
```

Expected:

```java
7
```

Actual:

```java
6
```

This problem is called a **Race Condition**.

---

## Thread-Safe Approaches

### 1. Local Variables (Best Practice)

```java
protected void doGet(...) {
    int count = 0;
    count++;
}
```

Why Safe?

* Stored in thread stack memory.
* Each request gets its own copy.
* No sharing between users.

---

### 2. synchronized Method

```java
protected synchronized void doGet(...) {
    count++;
}
```

Advantages:

* Safe

Disadvantages:

* Slow
* Other threads must wait

---

### 3. AtomicInteger (Recommended)

```java
private AtomicInteger count =
        new AtomicInteger(0);

protected void doGet(...) {
    int value = count.incrementAndGet();
}
```

Advantages:

* Thread Safe
* Better Performance
* No Race Condition

---

## Safe vs Unsafe Variables

| Variable Type     | Thread Safe |
| ----------------- | ----------- |
| Local Variable    | Yes         |
| Method Parameter  | Yes         |
| Instance Variable | No          |
| Static Variable   | No          |
| AtomicInteger     | Yes         |

---

## Interview Questions

### Q1. Why are Servlets not thread-safe by default?

Because a single servlet object is shared by multiple request threads. Shared instance variables can be modified simultaneously.

---

### Q2. What is a Race Condition?

A situation where multiple threads access and modify shared data at the same time, producing incorrect results.

---

### Q3. How can you make a Servlet thread-safe?

1. Use local variables.
2. Use synchronized blocks/methods.
3. Use thread-safe classes like AtomicInteger.

---

## Complete Example

### HTML

```html
<form action="counter" method="get">
    <button type="submit">
        Increase Counter
    </button>
</form>
```

### Servlet

```java
@WebServlet("/counter")
public class CounterServlet extends HttpServlet {

    private AtomicInteger counter =
            new AtomicInteger(0);

    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {

        int count =
                counter.incrementAndGet();

        response.getWriter()
                .println("Count = " + count);
    }
}
```

---

## Quick Revision

```text
Servlet = One Object
Users = Many Threads

Instance Variable = Unsafe
Local Variable = Safe
AtomicInteger = Safe

Race Condition = Incorrect Result
```

---

## 1-Line Interview Answer

Thread Safety in Servlet means protecting shared data from concurrent access by multiple request threads using techniques such as local variables, synchronization, or AtomicInteger.
