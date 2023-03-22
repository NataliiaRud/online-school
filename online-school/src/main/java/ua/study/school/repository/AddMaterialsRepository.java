package ua.study.school.repository;

import ua.study.school.models.AdditionalMaterial;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AddMaterialsRepository implements BaseRepository<AdditionalMaterial> {
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
}