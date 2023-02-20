package repository;

import models.AddMaterials;
import java.util.*;

import java.util.ArrayList;


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
        for (int i = 0; i <addMaterials.size(); i++) {
            if (addMaterials.get(i).getId() == id) {
                return addMaterials.get(i);
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