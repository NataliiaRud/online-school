package models;

public class AddMaterials {
    private static int id;
    private String addMaterials;
    public static int addMaterialsCounter;

    public AddMaterials(int id, String addMaterials) {
        this.id = id;
        this.addMaterials = addMaterials;
        addMaterialsCounter++;
    }
}
