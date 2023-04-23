package ua.study.school.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.study.school.models.School;
import ua.study.school.models.Student;
import ua.study.school.service.SchoolService;
import ua.study.school.service.StudentsService;

import java.io.IOException;
import java.util.*;

@RequestMapping("/edit-student")
@Controller
public class EditStudentController {
    @Autowired
    private SchoolService schoolService;

    @Autowired
    private StudentsService studentsService;

    @Autowired
    private Environment environment;

    @GetMapping
    public String displayForm(Map<String, Object> model) {
        return "edit-student";
    }

    @PostMapping
    public String createStudent(HttpServletRequest request, HttpServletResponse response, Map<String, Object> model) throws IOException {
        if (request.getParameter("user-first-name") != null) {
            String firstName = request.getParameter("user-first-name");
            String lastName = request.getParameter("user-last-name");
            String phone = request.getParameter("user-phone");
            String email = request.getParameter("user-email");

            List<School> schools = schoolService.getAll();
            if (schools.size() > 0) {
                schools.sort(Comparator.comparingInt(School::getId));

                Student student = new Student(0, firstName, lastName, schools.get(0).getId(), phone, email);

                ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
                Validator validator = factory.getValidator();

                Set<ConstraintViolation<Student>> violations = validator.validate(student);
                if (!violations.isEmpty()) {
                    List<String> errorMessages = new ArrayList<>();
                    for (ConstraintViolation<Student> violation : violations) {
                        errorMessages.add(environment.getProperty(violation.getMessageTemplate()));
                    }

                    model.put("errorMessages", errorMessages);
                    model.put("student", student);

                    return "edit-student";
                }

                studentsService.add(student);
            }
        }

        List<Student> students = studentsService.getAll();
        students.sort(Comparator.comparing(Student::getLastName));

        model.put("students", students);

        return "students";
    }
}