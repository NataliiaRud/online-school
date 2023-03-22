package ua.study.school.service;

import ua.study.school.models.AdditionalMaterial;
import ua.study.school.models.ResourceType;

import java.util.Scanner;

public class AddMaterialsService {
    public static AdditionalMaterial createAddMaterials1() {
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

