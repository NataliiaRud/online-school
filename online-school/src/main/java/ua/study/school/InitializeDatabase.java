package ua.study.school;

import ua.study.school.utility.LogService;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class InitializeDatabase {
    private static final LogService logService = new LogService();

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Properties properties = PropertiesLoader.loadProperties();

        Class.forName(properties.getProperty("db.driver"));

        Connection connection = null;
        Statement statement = null;

        try {
            connection = DriverManager.getConnection(properties.getProperty("db.url"),
                    properties.getProperty("db.user"),
                    properties.getProperty("db.password"));

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
            logService.error("Error occurred while initializing H2 database", e);
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
