package ua.study.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.study.school.models.Teacher;
import ua.study.school.service.TeacherService;

import java.util.List;
import java.util.Map;

@RequestMapping("/teachers")
@Controller
public class TeachersController {
    @Autowired
    private TeacherService teacherService;

    @GetMapping
    public String teachers(Map<String, Object> model) {
        List<Teacher> teachers = teacherService.getTeachersStartingFromCharacter("N");

        model.put("teachers", teachers);

        return "teachers";
    }
}