package ua.study.school.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:config.properties")
@ComponentScan("ua.study.school")
public class ApplicationConfiguration {
    @Autowired
    private Environment env;

    @Bean
    public ApplicationProperties applicationProperties() {
        ApplicationProperties applicationProperties = new ApplicationProperties();

        applicationProperties.setDriver(env.getProperty("db.driver"));
        applicationProperties.setPassword(env.getProperty("db.password"));
        applicationProperties.setUrl(env.getProperty("db.url"));
        applicationProperties.setUser(env.getProperty("db.user"));

        return applicationProperties;
    }
}
