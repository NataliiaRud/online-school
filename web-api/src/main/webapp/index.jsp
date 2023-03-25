<%@ page contentType="text/html; charset=UTF-8"
%><%@ page import="ua.study.school.repository.CourseRepository,ua.study.school.models.Course,java.util.List" %><%
    CourseRepository courseRepository = new CourseRepository();
    List<Course> courses = courseRepository.getAll();
%>
<html>
<head><title>Перелік курсів онлайн школи</title></head>
<body>
<h1>Перелік курсів онлайн школи</h1>
<table style="border: 1px solid black;">
  <tr><th>ID</th><th>Назва курсу</th></tr>
  <%
      for (Course course : courses) {
  %>
  <tr>
      <td><%= course.getId() %></td>
      <td><a href="course.jsp?id=<%= course.getId() %>"><%= course.getName() %></a></td>
  </tr>
  <%
      }
  %>
</table>
</body>
</html>