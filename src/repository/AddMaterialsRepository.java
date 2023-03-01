package repository;

import models.AddMaterials;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AddMaterialsRepository implements BaseRepository<AddMaterials> {
    private final ArrayList<AddMaterials> addMaterials = new ArrayList<>();
    private final Map<Integer, List<AddMaterials>> byLectureMap = new HashMap<>();

    public Integer getSize() {
        return addMaterials.size();
    }

    @Override
    public boolean isEmpty() {
        return addMaterials.isEmpty();
    }

    @Override
    public AddMaterials getByIndex(Integer indexToGet) {
        return indexToGet < addMaterials.size() ? addMaterials.get(indexToGet) : null;
    }

    @Override
    public void add(AddMaterials addMaterial) {
        addMaterials.add(addMaterial);

        List<AddMaterials> list = byLectureMap.computeIfAbsent(addMaterial.getLectureId(), k -> new ArrayList<>());
        list.add(addMaterial);
    }

    public List<AddMaterials> getByLectureId(int lectureId) {
        return byLectureMap.computeIfAbsent(lectureId, k -> new ArrayList<>());
    }

    @Override
    public void add(Integer id, AddMaterials addMaterial) {
        addMaterials.add(addMaterial);

        List<AddMaterials> list = byLectureMap.computeIfAbsent(addMaterial.getLectureId(), k -> new ArrayList<>());
        list.add(addMaterial);
    }

    @Override
    public AddMaterials getById(Integer id) {
        for (AddMaterials addMaterial : addMaterials) {
            if (addMaterial.getId() == id) {
                return addMaterial;
            }
        }
        return null;
    }

    @Override
    public List<AddMaterials> getAll() {
        return this.addMaterials;
    }

    @Override
    public void deleteById(Integer id) {
        addMaterials.removeIf(addMaterials -> addMaterials.getId() == id);

        for (Integer lectureId : byLectureMap.keySet()) {
            List<AddMaterials> list = byLectureMap.get(lectureId);
            list.removeIf(addMaterials -> addMaterials.getId() == id);
        }
    }
}