package ua.study.school.repository;

import ua.study.school.PropertiesLoader;
import ua.study.school.models.AdditionalMaterial;
import ua.study.school.utility.LogService;

import java.sql.*;
import java.util.*;


public class AddMaterialsRepository implements BaseRepository<AdditionalMaterial> {
    private static final LogService logService = new LogService();

    private final String url;
    private final String user;
    private final String password;

    public AddMaterialsRepository() {
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
            logService.error("Error occurred while getting additional materials", e);
        }

        throw new RuntimeException();
    }
}