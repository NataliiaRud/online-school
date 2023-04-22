package ua.study.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.study.school.service.StudentsService;
import ua.study.school.models.Student;


import java.util.Comparator;
import java.util.List;
import java.util.Map;

@RequestMapping("/students")
@Controller
public class StudentsController {
    @Autowired
    private StudentsService studentsService;

    @GetMapping
    public String students(Map<String, Object> model) {
        List<Student> students = studentsService.getAll();
        students.sort(Comparator.comparing(Student::getLastName));

        model.put("students", students);

        return "students";
    }
}