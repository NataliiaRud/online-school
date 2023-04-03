package ua.study.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.study.school.models.AdditionalMaterial;
import ua.study.school.models.ResourceType;
import ua.study.school.repository.AddMaterialsRepository;

import java.util.List;
import java.util.Scanner;

@Service
public class AddMaterialsService {
    @Autowired
    private AddMaterialsRepository addMaterialsRepository;

    public void add(AdditionalMaterial addMaterial) {
        addMaterialsRepository.add(addMaterial);
    }

    public List<AdditionalMaterial> getAll() {
        return addMaterialsRepository.getAll();
    }

    public List<AdditionalMaterial> getByLectureId(int lectureId) {
        return addMaterialsRepository.getByLectureId(lectureId);
    }

    public void deleteById(Integer id) {
        addMaterialsRepository.deleteById(id);
    }

    public List<List<Object>> getLecturesAndAdditionalMaterials() {
        return addMaterialsRepository.getLecturesAndAdditionalMaterials();
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
}

