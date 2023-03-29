<%@ page contentType="text/html; charset=UTF-8"
%><%@ page import="ua.study.school.repository.CourseRepository,ua.study.school.models.Course,java.util.List" %><%
     CourseRepository courseRepository = new CourseRepository();
     List<Course> courses = courseRepository.getAll();
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