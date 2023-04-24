package ua.study.school.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@RequestMapping("/settings")
@Controller
public class SettingsController {
    @GetMapping
    public String settings(Map<String, Object> model) {
        return "settings";
    }
}