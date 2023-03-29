package ua.study.school.repository;

import ua.study.school.PropertiesLoader;
import ua.study.school.exceptions.EntityNotFoundException;
import ua.study.school.models.Lecture;
import ua.study.school.models.School;
import ua.study.school.utility.LogService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class LectureRepository implements BaseRepository<Lecture>{
    private static final LogService logService = new LogService();

    private final String url;
    private final String user;
    private final String password;

    public LectureRepository() {
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

    ArrayList <Lecture> lectures = new ArrayList<>();


    @Override
    public Integer getSize() {
        return lectures.size();
    }
    @Override
    public boolean isEmpty() {
        return lectures.isEmpty();
    }
    @Override
    public Lecture getByIndex(Integer indexToGet) {
        int index = indexToGet;
        return lectures.get(index);
    }
    @Override
    public void add(Lecture lecture) {
        lectures.add(lecture);
    }

    @Override
    public void add(Integer index, Lecture lecture) {
        lectures.add(lecture);
    }

    private void checkLecture(Integer id) {
        try {
            if (id < 1 || id > 3) {
                throw new EntityNotFoundException("Lecture with id = " + id + " doesn't exist in repo");
            }
        } catch(EntityNotFoundException e) {
            logService.error("Lecture not found: " + id, e);
        }
    }

    @Override
    public Lecture getById(Integer id) {
        checkLecture(id);

        for (Lecture lecture : lectures) {
            if (lecture.getId() == id) {
                return lecture;
            }
        }
        return null;
    }


    @Override
    public List<Lecture> getAll() {
        return this.lectures;
    }


    public java.util.List<Lecture> findAll() {
        return lectures;
    }

    @Override
    public void deleteById(Integer id) {
        checkLecture(id);

        int indexToDelete = -1;
        for (int i = 0; i < lectures.size(); i++) {
            if (lectures.get(i).getId() == id) {
                indexToDelete = i;
                break;
            }
        }

        if (indexToDelete != -1) {
            lectures.remove(indexToDelete);
        }
    }

    public List<List<Object>> getLecturesAndAdditionalMaterials() {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareCall("SELECT l.name, count(a.id) as number_of_additional_materials FROM " +
                     "lecture l LEFT JOIN additional_material a ON l.id = a.lecture_id " +
                     "WHERE l.lecture_date < '2023-01-01 00:00:00' " +
                     "GROUP BY l.id " +
                     "ORDER BY l.lecture_date")) {
            ResultSet rs = statement.executeQuery();

            List<List<Object>> ret = new ArrayList<>();
            while (rs.next()) {
                List<Object> list = new ArrayList<>();
                list.add(rs.getString(1));
                list.add(rs.getInt(2));
                ret.add(list);
            }
            return ret;
        } catch (SQLException e) {
            logService.error("Error occurred while getting lectures before 2023", e);
        }

        throw new RuntimeException();
    }

    public List<Object> getEarliestLecture() {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareCall("SELECT b.id, b.course_id, b.name, b.description, b.lecture_date FROM (" +
                     "  SELECT l.*, count(a.id) as number_of_additional_materials FROM " +
                     "  lecture l LEFT JOIN additional_material a ON l.id = a.lecture_id " +
                     "  GROUP BY l.id " +
                     "  ORDER BY l.lecture_date, number_of_additional_materials DESC) b " +
                     "LIMIT 1")) {
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                List<Object> list = new ArrayList<>();
                list.add(rs.getInt(1));
                list.add(rs.getInt(2));
                list.add(rs.getString(3));
                list.add(rs.getString(4));
                list.add(rs.getDate(5));
                return list;
            }
            return null;
        } catch (SQLException e) {
            logService.error("Error occurred while getting lectures before 2023", e);
        }

        throw new RuntimeException();
    }
}