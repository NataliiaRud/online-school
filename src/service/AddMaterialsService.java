package service;

import models.AddMaterials;
import models.ResourceType;

import java.util.Scanner;

public class AddMaterialsService {
    public static AddMaterials createAddMaterials1() {
        System.out.println("To create an AddMaterials object, choose a category, type the proper number (1, 2 or 3):");
        AddMaterials addMaterials;
        Scanner scanner1 = new Scanner(System.in);
        int categoryNumber = scanner1.nextInt();
        try {
            switch (categoryNumber) {
                case 1:
                    addMaterials = AddMaterials.createAddMaterials(1, "chapter1", 2, ResourceType.URL);
                    System.out.println("You have created an AddMaterials object, type " + categoryNumber);
                    break;
                case 2:
                    addMaterials = AddMaterials.createAddMaterials(2, "chapter1", 1, ResourceType.VIDEO);
                    System.out.println("You have created an AddMaterials object, type " + categoryNumber);
                    break;
                case 3:
                    addMaterials = AddMaterials.createAddMaterials(3, "chapter1", 3, ResourceType.BOOK);
                    System.out.println("You have created an AddMaterials object, type " + categoryNumber);
                    break;
                default:
                    throw new IllegalArgumentException("test exception");
            }
            return addMaterials;
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        }
    }
}

