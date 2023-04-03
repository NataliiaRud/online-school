<%@ page contentType="text/html; charset=UTF-8"
%><%@ page import="ua.study.school.service.CourseService,
                   ua.study.school.models.Course,
                   ua.study.school.util.Config,
                   org.springframework.context.ApplicationContext" %><%
    ApplicationContext context = Config.get();
    CourseService courseService = context.getBean(CourseService.class);
    int courseId = Integer.parseInt(request.getParameter("id"));
    Course course = courseService.getById(courseId);
    request.setAttribute("current", "courses");
%>
<%@include file="include/header.jsp" %>

  <section class="container section-part">
    <div class="container">

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

  </section>
</main>

<%@include file="include/footer.jsp" %>