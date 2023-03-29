<%@ page contentType="text/html; charset=UTF-8"
%><%@ page import="ua.study.school.repository.AddMaterialsRepository,java.util.List" %><%
       AddMaterialsRepository addMaterialsRepository = new AddMaterialsRepository();
       List<List<Object>> am = addMaterialsRepository.getLecturesAndAdditionalMaterials();
       request.setAttribute("current", "additional-materials");
  %>
<%@include file="include/header.jsp" %>

<main>
  <section class="container section-part">
    <div class="container">
        <h1>Кількість додаткових матеріалів по кожній категорії</h1>
        <ul class="list">
<%
    for (List<Object> list : am) {
%>
              <li>
                <%= list.get(0) %> <%= list.get(1) %>
              </li>
<%
    }
%>
        </ul>
    </div>

  </section>
</main>

<%@include file="include/footer.jsp" %>