<%@ page contentType="text/html; charset=UTF-8"
%><%@ page import="ua.study.school.repository.StudentsRepository,
                   ua.study.school.models.Student,
                   ua.study.school.repository.SchoolRepository,
                   ua.study.school.models.School,
                   java.util.List" %><%
       StudentsRepository studentsRepository = new StudentsRepository();
       SchoolRepository schoolRepository = new SchoolRepository();
       if (request.getParameter("user-first-name") != null) {
           String firstName = request.getParameter("user-first-name");
           String lastName = request.getParameter("user-last-name");
           String phone = request.getParameter("user-phone");
           String email = request.getParameter("user-email");
           List<School> schools = schoolRepository.getAll();
           if (schools.size() > 0) {
               schools.sort((s1, s2) -> Integer.compare(s1.getId(), s2.getId()));
               Student student = new Student(0, firstName, lastName, schools.get(0).getId(), phone, email);
               studentsRepository.add(student);
           }
           response.sendRedirect("students.jsp");
           return;
       }
       request.setAttribute("current", "students");
%>
<%@include file="include/header.jsp" %>

      <main>
<section class="container section-part">
  <h2 class="title">Fill all the fields in the form with valid data</h2>
      <form class="modal-form" method="POST">
        <div class="modal-field">
          <label for="user-first-name" class="input-label">First Name</label>
          <div class="input-wrap">
            <input
              type="text"
              name="user-first-name"
              class="modal-input"
              id="user-name"
              required />
            <svg class="modal-input-icon" width="18" height="18">
              <use href="./images/symbol-defs.svg#icon-person"></use>
            </svg>
          </div>
        </div>

          <div class="modal-field">
          <label for="user-last-name" class="input-label">Last Name</label>
          <div class="input-wrap">
            <input
              type="text"
              name="user-last-name"
              class="modal-input"
              id="user-last-name"
              required />
            <svg class="modal-input-icon" width="18" height="18">
              <use href="./images/symbol-defs.svg#icon-person"></use>
            </svg>
          </div>
        </div>

        <div class="modal-field">
          <label for="user-phone" class="input-label">Phone</label>
          <div class="input-wrap">
            <input
              type="tel"
              name="user-phone"
              class="modal-input"
              id="tel"
              required />
            <svg class="modal-input-icon" width="18" height="18">
              <use href="./images/symbol-defs.svg#icon-phone-modal"></use>
            </svg>
          </div>
        </div>

        <div class="modal-field">
          <label for="user-email" class="input-label">Email</label>
          <div class="input-wrap">
            <input
              type="email"
              name="user-email"
              class="modal-input"
              id="email"
              required />
            <svg class="modal-input-icon" width="18" height="18">
              <use href="./images/symbol-defs.svg#icon-email"></use>
            </svg>
          </div>
        </div>
       <p class="title">Once you have filled all the fields, click the "Submit" button</p>
        <button class="check-button btn" type="submit">Submit</button>
      </form>
</section>

<%@include file="include/footer.jsp" %>