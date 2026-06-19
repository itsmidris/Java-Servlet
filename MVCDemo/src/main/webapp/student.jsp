<%@ page import="Student" %>

<%
Student s = (Student) request.getAttribute("student");
%>

<h2>Student Information</h2>

Name: <%= s.getName() %>
<br>
Age: <%= s.getAge() %>