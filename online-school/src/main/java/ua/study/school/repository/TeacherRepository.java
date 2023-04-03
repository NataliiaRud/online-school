package ua.study.school.repository;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.study.school.configuration.ApplicationProperties;
import ua.study.school.models.Teacher;
import ua.study.school.utility.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TeacherRepository implements BaseRepository<Teacher>, InitializingBean {
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

    private Teacher readSTeacher(ResultSet rs) throws SQLException {
        return new Teacher(
                rs.getInt("id"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getInt("school_id"),
                rs.getString("phone"),
                rs.getString("email")
        );
    }

    ArrayList<Teacher> teachers = new ArrayList<>();

    public Integer getSize() {
        return teachers.size();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Teacher getByIndex(Integer indexToGet) {
        return null;
    }

    @Override
    public void add(Teacher teacher) {
        teachers.add(teacher);
    }

    @Override
    public void add(Integer id, Teacher teacher) {

    }

    @Override
    public Teacher getById(Integer id) {
        for (Teacher teacher : teachers) {
            if (teacher.getId() == id) {
                return teacher;
            }
        }
        return null;
    }

    @Override
    public List<Teacher> getAll() {
        return this.teachers;
    }

    @Override
    public void deleteById(Integer id) {
        int indexToDelete = -1;
        for (int i = 0; i < teachers.size(); i++) {
            if (teachers.get(i).getId() == id) {
                indexToDelete = i;
                break;
            }
        }

        if (indexToDelete != -1) {
            teachers.remove(indexToDelete);
        }
    }

    public List<Teacher> getTeachersStartingFromCharacter(String start) {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareCall("SELECT * FROM teacher WHERE UPPER(last_name) < ?")) {
            statement.setString(1, start);
            ResultSet rs = statement.executeQuery();

            List<Teacher> ret = new ArrayList<>();
            while (rs.next()) {
                ret.add(readSTeacher(rs));
            }
            return ret;
        } catch (SQLException e) {
            LOGGER.error("Error occurred while getting teachers", e);
        }

        throw new RuntimeException();
    }
}