package ua.study.school.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.study.school.service.CredentialsService;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;

@RequestMapping("/register")
@Controller
public class RegisterController {
    @Autowired
    private CredentialsService credentialsService;

    @GetMapping
    public String displayRegister(Map<String, Object> model) {
        return "register";
    }

    @PostMapping
    public void register(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String password2 = request.getParameter("password2");

        if (username == null || password == null || password2 == null
                || "".equals(username.trim()) || "".equals(password.trim()) || "".equals(password2.trim())
        ) {
            response.sendRedirect("/register?error=" + URLEncoder.encode("Параметри реєстрації не можуть бути пустими", "UTF-8"));
            return;
        }

        if (credentialsService.userExists(username)) {
            response.sendRedirect("/register?error=" + URLEncoder.encode("Користувач з таким username вже існує в системі", "UTF-8"));
            return;
        }

        if (!password.equals(password2)) {
            response.sendRedirect("/register?error=" + URLEncoder.encode("Паролі не співпадають", "UTF-8"));
            return;
        }

        credentialsService.createUser(username, password);

        response.sendRedirect("/registered");
    }
}