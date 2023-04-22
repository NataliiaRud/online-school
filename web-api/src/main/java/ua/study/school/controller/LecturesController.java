package ua.study.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.study.school.service.LectureService;

import java.util.List;
import java.util.Map;

@RequestMapping("/lectures")
@Controller
public class LecturesController {
    @Autowired
    private LectureService lectureService;

    @GetMapping
    public String lectures(Map<String, Object> model) {
        List<List<Object>> lecturesBefore2023 = lectureService.getLecturesAndAdditionalMaterials();
        List<Object> earliest = lectureService.getEarliestLecture();

        model.put("lecturesBefore2023", lecturesBefore2023);
        model.put("earliest", earliest);

        return "lectures";
    }
}