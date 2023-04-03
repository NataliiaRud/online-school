package ua.study.school.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ua.study.school.configuration.ApplicationConfiguration;

public class Config {
    private volatile static ApplicationContext context;

    public static ApplicationContext get() {
        if (context == null) {
            synchronized (Config.class) {
                if (context == null) {
                    context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
                }
            }
        }

        return context;
    }
}