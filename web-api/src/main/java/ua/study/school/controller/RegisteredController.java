package ua.study.school.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@RequestMapping("/registered")
@Controller
public class RegisteredController {
    @GetMapping
    public String login(Map<String, Object> model) {
        return "registered";
    }
}
