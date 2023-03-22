package ua.study.school;

import ua.study.school.utility.LogService;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {
    private static final LogService logService = new LogService();

    public static Properties loadProperties() {
        try (InputStream input = InitializeDatabase.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                logService.error("Cannot load config.properties");
                return null;
            }

            Properties properties = new Properties();
            properties.load(input);
            return properties;
        } catch (IOException ex) {
            logService.error("Error occurred while loading config.properties", ex);
        }

        return null;
    }
}
