package repository;
import models.AddMaterials;
public class AddMaterialsRepository {
    private AddMaterials[] addMaterialsArray = new AddMaterials[0];
    private int lastIndex = -1;
    public void addAddMaterials(AddMaterials addMaterials) {
        AddMaterials[] newAddMaterialsArray = new AddMaterials[3 * addMaterialsArray.length / 2 + 1];
        for (int i = 0; i < addMaterialsArray.length; i++) {
            newAddMaterialsArray[i] = addMaterialsArray[i];
        }
        lastIndex++;
        addMaterialsArray[lastIndex] = addMaterials;
        this.addMaterialsArray = newAddMaterialsArray;
    }
    public AddMaterials getAddMaterials(int addMaterialsId) {
        for (int i = 0; i <= lastIndex; i++) {
            if (addMaterialsArray[i].getAddMaterialsId() == addMaterialsId) {
                return addMaterialsArray[i];
            }
        }
        return null;
    }
    public AddMaterials[] getAllAddMaterialsId() {
        return this.addMaterialsArray;
    }
}
