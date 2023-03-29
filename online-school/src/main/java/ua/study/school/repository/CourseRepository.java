package ua.study.school.repository;

import ua.study.school.InitializeDatabase;
import ua.study.school.PropertiesLoader;
import ua.study.school.exceptions.EntityNotFoundException;
import ua.study.school.models.Course;
import ua.study.school.utility.LogService;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class CourseRepository implements BaseRepository<Course> {
    private static final LogService logService = new LogService();

    private final String url;
    private final String user;
    private final String password;

    public CourseRepository() {
        Properties properties = PropertiesLoader.loadProperties();
        try {
            Class.forName(properties.getProperty("db.driver"));
        } catch (ClassNotFoundException e) {
            logService.error("", e);
            throw new RuntimeException(e);
        }
        url = properties.getProperty("db.url");
        user = properties.getProperty("db.user");
        password = properties.getProperty("db.password");
    }

    private Course readCourse(ResultSet rs) throws SQLException {
        return Course.createCourse(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getInt("school_id")
        );
    }

    @Override
    public Integer getSize() {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement("SELECT count(*) FROM course")) {
            ResultSet rs = statement.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException e) {
            logService.error("Error occurred while getting number of courses", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean isEmpty() {
        return getSize() == 0;
    }

    @Override
    public Course getByIndex(Integer indexToGet) {
        // index is not applicable for database
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void add(Course course) {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement("INSERT INTO course (school_id, name) VALUES (?, ?)")) {
            statement.setInt(1, course.getId());
            statement.setString(2, course.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            logService.error("Error occurred while adding course", e);
        }
    }

    @Override
    public void add(Integer id, Course course) {
        // index is not applicable for database
        throw new RuntimeException("Not implemented");
    }

    @Override
    public Course getById(Integer id) {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM course WHERE id = ?")) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return readCourse(rs);
            }
        } catch (SQLException e) {
            logService.error("Error occurred while getting course by id", e);
        }

        return null;
    }

    @Override
    public List<Course> getAll() {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             CallableStatement statement = connection.prepareCall("{call get_records_from_table('course')}")) {
            ResultSet rs = statement.executeQuery();

            List<Course> ret = new ArrayList<>();
            while (rs.next()) {
                ret.add(readCourse(rs));
            }
            return ret;
        } catch (SQLException e) {
            logService.error("Error occurred while getting all courses", e);
        }

        throw new RuntimeException();
    }

    @Override
    public void deleteById(Integer id) {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement("DELETE FROM course WHERE id = ?")) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            logService.error("Error occurred while deleting course by id", e);
        }
    }
}
