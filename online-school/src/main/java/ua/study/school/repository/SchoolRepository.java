package ua.study.school.repository;

import ua.study.school.PropertiesLoader;
import ua.study.school.models.School;
import ua.study.school.models.Student;
import ua.study.school.utility.LogService;

import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Properties;


public class SchoolRepository implements BaseRepository<School> {
    private static final LogService logService = new LogService();

    private final String url;
    private final String user;
    private final String password;

    public SchoolRepository() {
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
            logService.error("Error occurred while getting all schools", e);
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
