<%@ page contentType="text/html; charset=UTF-8"
%><%@ page import="ua.study.school.service.CourseService,
                   ua.study.school.models.Course,
                   ua.study.school.util.Config,
                   org.springframework.context.ApplicationContext,
                   java.util.List" %><%
    ApplicationContext context = Config.get();
    CourseService courseService = context.getBean(CourseService.class);
    List<Course> courses = courseService.getAll();
    request.setAttribute("current", "courses");
%>
<%@include file="include/header.jsp" %>

<main>
  <section class="container section-part">
    <div class="container">
        <ul class="list">
<%
    for (Course course : courses) {
%>
              <li>
                <a class="link" href="course.jsp?id=<%= course.getId() %>"><%= course.getId() %>. <%= course.getName() %></a>
              </li>
<%
    }
%>
        </ul>
    </div>
  </section>
</main>

<%@include file="include/footer.jsp" %>