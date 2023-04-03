package ua.study.school.repository;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.study.school.configuration.ApplicationProperties;
import ua.study.school.models.School;
import ua.study.school.utility.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SchoolRepository implements BaseRepository<School>, InitializingBean {
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

    private School readSchool(ResultSet rs) throws SQLException {
        return new School(
                rs.getInt("id"),
                rs.getString("name")
        );
    }

    ArrayList<School> schools = new ArrayList<>();

    public Integer getSize() {
        return schools.size();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public School getByIndex(Integer indexToGet) {
        return null;
    }

    @Override
    public void add(School school) {
        schools.add(school);
    }

    @Override
    public void add(Integer id, School school) {

    }

    @Override
    public School getById(Integer id) {
        for (School school : schools) {
            if (school.getId() == id) {
                return school;
            }
        }
        return null;
    }

    @Override
    public List<School> getAll() {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             CallableStatement statement = connection.prepareCall("{call get_records_from_table('school')}")) {
            ResultSet rs = statement.executeQuery();

            List<School> ret = new ArrayList<>();
            while (rs.next()) {
                ret.add(readSchool(rs));
            }
            return ret;
        } catch (SQLException e) {
            LOGGER.error("Error occurred while getting all schools", e);
        }

        throw new RuntimeException();
    }

    @Override
    public void deleteById(Integer id) {
        int indexToDelete = -1;
        for (int i = 0; i < schools.size(); i++) {
            if (schools.get(i).getId() == id) {
                indexToDelete = i;
                break;
            }
        }

        if (indexToDelete != -1) {
            schools.remove(indexToDelete);
        }
    }
}
