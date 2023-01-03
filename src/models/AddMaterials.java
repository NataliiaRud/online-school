package models;

public class AddMaterials {
    private int id;
    private String materials;
    public static int addMaterialsCounter;

    public AddMaterials(int id, String materials) {
        this.id = id;
        this.materials = materials;
        addMaterialsCounter++;
    }
    public int getAddMaterialsId() {
        return this.id;
    }
}
