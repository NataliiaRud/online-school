package repository;

import models.AddMaterials;
import models.Course;


public class AddMaterialsRepository implements BaseRepository<AddMaterials> {
    private GenericArray<AddMaterials> array = new GenericArray<AddMaterials>();

    public int getLecturesSize() {
        return array.size();
    }

    @Override
    public void add(AddMaterials addMaterials) {
        array.add(addMaterials);
    }

    @Override
    public void add(int id, AddMaterials addMaterials) {

    }

    @Override
    public AddMaterials getById(int id) {
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i).getId() == id) {
                return array.get(i);
            }
        }
        return null;
    }

    @Override
    public AddMaterials[] getAll() {
        AddMaterials[] ret = new AddMaterials[array.size()];
        for (int i = 0; i < array.size(); i++) {
            ret[i] = array.get(i);
        }
        return ret;
    }

    @Override
    public void deleteById(int id) {
        int indexToDelete = -1;
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i).getId() == id) {
                indexToDelete = i;
                break;
            }
        }

        if (indexToDelete != -1) {
            array.remove(indexToDelete);
        }
    }
}