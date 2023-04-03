package ua.study.school;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ua.study.school.configuration.ApplicationConfiguration;
import ua.study.school.configuration.ApplicationProperties;
import ua.study.school.utility.Logger;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;

public class InitializeDatabase {
    private static final Logger LOGGER = new Logger();

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
        ApplicationProperties properties = context.getBean(ApplicationProperties.class);

        Class.forName(properties.getDriver());

        Connection connection = null;
        Statement statement = null;

        try {
            connection = DriverManager.getConnection(properties.getUrl(),
                    properties.getUser(),
                    properties.getPassword());

            statement = connection.createStatement();

            String schema;
            try (InputStream inputStream = InitializeDatabase.class.getClassLoader().getResourceAsStream("schema.sql")) {
                ByteArrayOutputStream result = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                for (int length; (length = inputStream.read(buffer)) != -1; ) {
                    result.write(buffer, 0, length);
                }
                schema = result.toString("UTF-8");
            }

            String data;
            try (InputStream inputStream = InitializeDatabase.class.getClassLoader().getResourceAsStream("data.sql")) {
                ByteArrayOutputStream result = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                for (int length; (length = inputStream.read(buffer)) != -1; ) {
                    result.write(buffer, 0, length);
                }
                data = result.toString("UTF-8");
            }

            String sql = schema + "\r\n" + data;
            statement.execute(sql);
        } catch (SQLException | IOException e) {
            LOGGER.error("Error occurred while initializing H2 database", e);
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }

            if (connection != null) {
                connection.close();
            }
        }

    }
}
