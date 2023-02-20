package repository;

import models.AddMaterials;
import java.util.*;

import java.util.ArrayList;


public class AddMaterialsRepository implements BaseRepository<AddMaterials> {
    ArrayList<AddMaterials> addMaterials = new ArrayList<>();

    public Integer getSize() {
        return addMaterials.size();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public AddMaterials getByIndex(Integer indexToGet) {
        return null;
    }

    @Override
    public void add(AddMaterials addMaterial) {
        addMaterials.add(addMaterial);
    }


    @Override
    public void add(Integer id, AddMaterials addMaterial) {
        addMaterials.add(addMaterial);
    }

    @Override
    public AddMaterials getById(Integer id) {
        for (int i = 0; i <addMaterials.size(); i++) {
            if (addMaterials.get(i).getId() == id) {
                return addMaterials.get(i);
            }
        }
        return null;
    }

    @Override
    public List<AddMaterials> getAll() {
        return this.addMaterials;
    }

    @Override
    public void deleteById(Integer id) {
        int indexToDelete = -1;
        for (int i = 0; i < addMaterials.size(); i++) {
            if (addMaterials.get(i).getId() == id) {
                indexToDelete = i;
                break;
            }
        }

        if (indexToDelete != -1) {
            addMaterials.remove(indexToDelete);
        }
    }
}