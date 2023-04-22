package ua.study.school.controller;

import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.study.school.models.Course;
import ua.study.school.service.CourseService;

import java.util.Map;

@RequestMapping("/view-course")
@Controller
public class ViewCourse {
    @Autowired
    private CourseService courseService;

    @GetMapping
    public String course(@PathParam(value = "id") int id, Map<String, Object> model) {
        Course course = courseService.getById(id);

        model.put("course", course);

        return "view-course";
    }
}