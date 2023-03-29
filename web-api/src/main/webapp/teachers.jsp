<%@ page contentType="text/html; charset=UTF-8"
%><%@ page import="ua.study.school.repository.TeacherRepository,ua.study.school.models.Teacher,java.util.List" %><%
       TeacherRepository teacherRepository = new TeacherRepository();
       List<Teacher> teachers = teacherRepository.getTeachersStartingFromCharacter("N");
       request.setAttribute("current", "teachers");
  %>
<%@include file="include/header.jsp" %>

<main>
  <section class="container section-part">
    <div class="container">
        <h1>Перелік вчителів чиє призвище починається з літер що стоять до літери N</h1>
        <ul class="list">
<%
    for (Teacher teacher : teachers) {
%>
              <li>
                <%= teacher.getId() %>. <%= teacher.getFirstName() %> <%= teacher.getLastName() %> <%= teacher.getPhone() %> <%= teacher.getEmail() %>
              </li>
<%
    }
%>
        </ul>
    </div>
  </section>
</main>

<%@include file="include/footer.jsp" %>