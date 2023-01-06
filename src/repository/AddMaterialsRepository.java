package repository;
import models.AddMaterials;
import models.Course;
import models.Lecture;

public class AddMaterialsRepository {
    private AddMaterials[] addMaterialsArray = new AddMaterials[0];
    private AddMaterials[] newAddMaterialsArray = new AddMaterials[0];
    private int lastIndex = -1;
    public void addAddMaterials(AddMaterials addMaterials) {
        lastIndex++;
        if (lastIndex >= addMaterialsArray.length) {
        AddMaterials[] newAddMaterialsArray = new AddMaterials[3 * addMaterialsArray.length / 2 + 1];
            System.arraycopy(addMaterialsArray, 0, newAddMaterialsArray, 0, addMaterialsArray.length);
//            this.addMaterialsArray = newAddMaterialsArray;
        }
        this.addMaterialsArray[lastIndex] = addMaterials;
    }

    public AddMaterials getAddMaterials(int addMaterialsId) {
        for (int i = 0; i <= lastIndex; i++) {
            if (addMaterialsArray[i].getId() == addMaterialsId) {
                return addMaterialsArray[i];
            }
        }
        return null;
    }
    public AddMaterials[] getAllAddMaterialsId() {
        return this.addMaterialsArray;
    }
}
