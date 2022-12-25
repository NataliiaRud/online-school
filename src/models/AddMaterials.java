package models;

public class AddMaterials {
    private static int id;
    private String materials;
    public static int addMaterialsCounter;

    public AddMaterials(int id, String materials) {
        AddMaterials.id = id;
        this.materials = materials;
        addMaterialsCounter++;
    }
    public int getAddMaterialsId() {
        return this.id;
    }
}
