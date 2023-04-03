package ua.study.school.repository;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.study.school.configuration.ApplicationProperties;
import ua.study.school.models.AdditionalMaterial;
import ua.study.school.utility.Logger;

import java.sql.*;
import java.util.*;

@Repository
public class AddMaterialsRepository implements BaseRepository<AdditionalMaterial>, InitializingBean {
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

    private final ArrayList<AdditionalMaterial> addMaterials = new ArrayList<>();
    private final Map<Integer, List<AdditionalMaterial>> byLectureMap = new HashMap<>();

    public Integer getSize() {
        return addMaterials.size();
    }

    @Override
    public boolean isEmpty() {
        return addMaterials.isEmpty();
    }

    @Override
    public AdditionalMaterial getByIndex(Integer indexToGet) {
        return indexToGet < addMaterials.size() ? addMaterials.get(indexToGet) : null;
    }

    @Override
    public void add(AdditionalMaterial addMaterial) {
        addMaterials.add(addMaterial);

        List<AdditionalMaterial> list = byLectureMap.computeIfAbsent(addMaterial.getLectureId(), k -> new ArrayList<>());
        list.add(addMaterial);
    }

    public List<AdditionalMaterial> getByLectureId(int lectureId) {
        return byLectureMap.computeIfAbsent(lectureId, k -> new ArrayList<>());
    }

    @Override
    public void add(Integer id, AdditionalMaterial addMaterial) {
        addMaterials.add(addMaterial);

        List<AdditionalMaterial> list = byLectureMap.computeIfAbsent(addMaterial.getLectureId(), k -> new ArrayList<>());
        list.add(addMaterial);
    }

    @Override
    public AdditionalMaterial getById(Integer id) {
        for (AdditionalMaterial addMaterial : addMaterials) {
            if (addMaterial.getId() == id) {
                return addMaterial;
            }
        }
        return null;
    }

    @Override
    public List<AdditionalMaterial> getAll() {
        return this.addMaterials;
    }

    @Override
    public void deleteById(Integer id) {
        addMaterials.removeIf(addMaterials -> addMaterials.getId() == id);

        for (Integer lectureId : byLectureMap.keySet()) {
            List<AdditionalMaterial> list = byLectureMap.get(lectureId);
            list.removeIf(addMaterials -> addMaterials.getId() == id);
        }
    }

    public List<List<Object>> getLecturesAndAdditionalMaterials() {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareCall("SELECT type, count(type) FROM additional_material GROUP BY type")) {
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
            LOGGER.error("Error occurred while getting additional materials", e);
        }

        throw new RuntimeException();
    }
}