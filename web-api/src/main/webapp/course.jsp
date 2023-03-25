<%@ page contentType="text/html; charset=UTF-8"
%><%@ page import="ua.study.school.repository.CourseRepository,ua.study.school.models.Course" %><%
    CourseRepository courseRepository = new CourseRepository();
    int courseId = Integer.parseInt(request.getParameter("id"));
    Course course = courseRepository.getById(courseId);
%>
<html>
<head><title>Перегляд курсу</title></head>
<body>
<%
    if (course != null) {
%>
<h1>Перегляд курсу <%= course.getName() %></h1>
<p>ID курсу: <%= course.getId() %></p>
<p>Назва курсу: <%= course.getName() %></p>
<%
    } else {
%>
<h1>Курс не знайдено</h1>
<%
    }
%>

</body>
</html>