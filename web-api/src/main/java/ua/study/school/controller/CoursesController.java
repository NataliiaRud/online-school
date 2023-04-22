package ua.study.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.study.school.models.Course;
import ua.study.school.service.CourseService;

import java.util.List;
import java.util.Map;

@RequestMapping("/course")
@Controller
public class CoursesController {
    @Autowired
    private CourseService courseService;

    @GetMapping
    public String course(Map<String, Object> model) {
        List<Course> courses = courseService.getAll();

        model.put("courses", courses);

        return "courses";
    }
}