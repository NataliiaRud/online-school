package ua.study.school.repository;


import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.study.school.configuration.ApplicationProperties;
import ua.study.school.models.Student;
import ua.study.school.utility.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentsRepository implements BaseRepository<Student>, InitializingBean {
    private static final Logger LOGGER = new Logger();

    @Autowired
    private ApplicationProperties properties;

    private String url;
    private String user;
    private String password;

    @Override
    public void afterPropertiesSet() throws Exception {
        try {
            Class.forName(properties.getDriver());
        } catch (ClassNotFoundException e) {
            LOGGER.error("", e);
            throw new RuntimeException(e);
        }
        url = properties.getUrl();
        user = properties.getUser();
        password = properties.getPassword();
    }

    private Student readStudent(ResultSet rs) throws SQLException {
        return new Student(
                rs.getInt("id"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getInt("school_id"),
                rs.getString("phone"),
                rs.getString("email")
        );
    }

    @Override
    public Integer getSize() {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement("SELECT count(*) FROM student")) {
            ResultSet rs = statement.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException e) {
            LOGGER.error("Error occurred while getting students count", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean isEmpty() {
        return getSize() == 0;
    }

    @Override
    public Student getByIndex(Integer indexToGet) {
        // index is not applicable for database
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void add(Student student) {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO student (school_id, first_name, last_name, phone, email) VALUES (?, ?, ?, ?, ?)")) {
            statement.setInt(1, student.getSchoolId());
            statement.setString(2, student.getFirstName());
            statement.setString(3, student.getLastName());
            statement.setString(4, student.getPhone());
            statement.setString(5, student.getEmail());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Error occurred while adding student", e);
        }
    }

    @Override
    public void add(Integer id, Student student) {
        // index is not applicable for database
        throw new RuntimeException("Not implemented");
    }

    @Override
    public Student getById(Integer id) {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM student WHERE id = ?")) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return readStudent(rs);
            }
        } catch (SQLException e) {
            LOGGER.error("Error occurred while getting a student by id", e);
        }

        return null;
    }

    @Override
    public List<Student> getAll() {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             CallableStatement statement = connection.prepareCall("{call get_records_from_table('student')}")) {
            ResultSet rs = statement.executeQuery();

            List<Student> ret = new ArrayList<>();
            while (rs.next()) {
                ret.add(readStudent(rs));
            }
            return ret;
        } catch (SQLException e) {
            LOGGER.error("Error occurred while getting all students", e);
        }

        throw new RuntimeException();
    }

    @Override
    public void deleteById(Integer id) {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement("DELETE FROM student WHERE id = ?")) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Error occurred while deleting student by id", e);
        }
    }
}