package repository;
import models.AddMaterials;

public class AddMaterialsRepository implements BaseRepository<AddMaterials> {
    private AddMaterials[] addMaterialsArray = new AddMaterials[0];

    private int lastIndex = -1;



    @Override
    public void add(AddMaterials addMaterials) {
        lastIndex++;
        if (lastIndex >= addMaterialsArray.length) {
            AddMaterials[] newAddMaterialsArray = new AddMaterials[3 * addMaterialsArray.length / 2 + 1];
            System.arraycopy(addMaterialsArray, 0, newAddMaterialsArray, 0, addMaterialsArray.length);
            this.addMaterialsArray = newAddMaterialsArray;
        }
        this.addMaterialsArray[lastIndex] = addMaterials;
    }

    @Override
    public AddMaterials getById(int id) {
        for (int i = 0; i <= lastIndex; i++) {
            if (addMaterialsArray[i].getId() == id) {
                return addMaterialsArray[i];
            }
        }
        return null;
    }

    @Override
    public AddMaterials[] getAll() {
        return this.addMaterialsArray;
    }

    @Override
    public void deleteById(int id) {
        for (int i = 0; i < lastIndex; i++) {
            if (addMaterialsArray[i].getId() == id) {
                addMaterialsArray[i]=null;
            }
        }
    }
}
