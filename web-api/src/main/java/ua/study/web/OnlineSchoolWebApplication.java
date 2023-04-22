package ua.study.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Import;
import ua.study.school.configuration.ApplicationConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@Import({ ApplicationConfiguration.class})
public class OnlineSchoolWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(OnlineSchoolWebApplication.class, args);
    }
}