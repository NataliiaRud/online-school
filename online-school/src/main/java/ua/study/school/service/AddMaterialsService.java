package ua.study.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.study.school.models.AdditionalMaterial;
import ua.study.school.models.ResourceType;
import ua.study.school.repository.AddMaterialsRepository;
import ua.study.school.repository.BaseService;

import java.util.*;

@Service
public class AddMaterialsService implements BaseService<AdditionalMaterial> {
    @Autowired
    private AddMaterialsRepository<AdditionalMaterial> addMaterialsRepository;

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

    public AdditionalMaterial createAddMaterials1() {
        System.out.println("To create an AddMaterials object, choose a category, type the proper number (1, 2 or 3):");
        AdditionalMaterial additionalMaterial;
        Scanner scanner1 = new Scanner(System.in);
        int categoryNumber = scanner1.nextInt();
        try {
            switch (categoryNumber) {
                case 1:
                    additionalMaterial = AdditionalMaterial.createAddMaterials(1, "chapter1", "description 1", 2, ResourceType.URL);
                    System.out.println("You have created an AddMaterials object, type " + categoryNumber);
                    break;
                case 2:
                    additionalMaterial = AdditionalMaterial.createAddMaterials(2, "chapter1", "description 2", 1, ResourceType.VIDEO);
                    System.out.println("You have created an AddMaterials object, type " + categoryNumber);
                    break;
                case 3:
                    additionalMaterial = AdditionalMaterial.createAddMaterials(3, "chapter1", "description 3",3, ResourceType.BOOK);
                    System.out.println("You have created an AddMaterials object, type " + categoryNumber);
                    break;
                default:
                    throw new IllegalArgumentException("test exception");
            }
            return additionalMaterial;
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        }
    }

    public List<List<Object>> getLecturesAndAdditionalMaterials() {
        Collection<Object[]> collection = addMaterialsRepository.getLecturesAndAdditionalMaterials();

        List<List<Object>> ret = new ArrayList<>();
        for (Object[] objects : collection) {
            List<Object> list = new ArrayList<>();
            for (Object object : objects) {
                list.add(object);
            }
            ret.add(list);
        }

        return ret;
    }
}

