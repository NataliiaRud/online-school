package ua.study.school.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.study.school.models.School;
import ua.study.school.models.Student;
import ua.study.school.service.SchoolService;
import ua.study.school.service.StudentsService;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@RequestMapping("/edit-student")
@Controller
public class EditStudentController {
    @Autowired
    private SchoolService schoolService;

    @Autowired
    private StudentsService studentsService;

    @GetMapping
    public String displayForm(Map<String, Object> model) {
        return "edit-student";
    }

    @PostMapping
    public void createStudent(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (request.getParameter("user-first-name") != null) {
            String firstName = request.getParameter("user-first-name");
            String lastName = request.getParameter("user-last-name");
            String phone = request.getParameter("user-phone");
            String email = request.getParameter("user-email");

            List<School> schools = schoolService.getAll();
            if (schools.size() > 0) {
                schools.sort(Comparator.comparingInt(School::getId));

                Student student = new Student(0, firstName, lastName, schools.get(0).getId(), phone, email);
                studentsService.add(student);
            }
        }

        response.sendRedirect("/students");
    }
}