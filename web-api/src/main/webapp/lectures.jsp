<%@ page contentType="text/html; charset=UTF-8"
%><%@ page import="ua.study.school.repository.LectureRepository,ua.study.school.models.Lecture,java.util.List" %><%
       LectureRepository lectureRepository = new LectureRepository();
       List<List<Object>> lecturesBefore2023 = lectureRepository.getLecturesAndAdditionalMaterials();
       List<Object> earliest = lectureRepository.getEarliestLecture();
       request.setAttribute("current", "lectures");
  %>
<%@include file="include/header.jsp" %>

<main>
  <section class="container section-part">
    <div class="container">
        <h1>Назва лекції та кількість додаткових матеріалів, яка вона містить, відсортовані за датою та які проодили до 2023 року.</h1>
        <ul class="list">
<%
    for (List<Object> list : lecturesBefore2023) {
%>
              <li>
                <%= list.get(0) %> <%= list.get(1) %>
              </li>
<%
    }
%>
        </ul>

        <h1>Лекція, яка стоворена раніше за всіх та має найбільшу кількість домашніх завданнів:</h1>
        id=<%= earliest.get(0) %>, course id=<%= earliest.get(1) %>,
        name=<%= earliest.get(2) %>, description=<%= earliest.get(3) %>, date=<%= earliest.get(4) %>

    </div>

  </section>
</main>

<%@include file="include/footer.jsp" %>