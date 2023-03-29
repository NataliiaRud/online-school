<%@ page contentType="text/html; charset=UTF-8"
%><%@ page import="ua.study.school.repository.StudentsRepository,ua.study.school.models.Student,java.util.List" %><%
       StudentsRepository studentsRepository = new StudentsRepository();
       List<Student> students = studentsRepository.getAll();
       students.sort((s1, s2) -> s1.getLastName().compareTo(s2.getLastName()));
       request.setAttribute("current", "students");
  %>
<%@include file="include/header.jsp" %>

<main>
  <section class="container section-part">
    <div class="container">
        <ul class="list">
<%
    for (Student student : students) {
%>
              <li>
                <%= student.getId() %>. <%= student.getFirstName() %> <%= student.getLastName() %> <%= student.getPhone() %> <%= student.getEmail() %>
              </li>
<%
    }
%>
        </ul>
    </div>

    <div><a href="edit-student.jsp">Створити обліковий запис студента</a></div>
  </section>
</main>

<%@include file="include/footer.jsp" %>