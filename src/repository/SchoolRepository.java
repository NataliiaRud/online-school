package repository;

import models.School;


public class SchoolRepository implements BaseRepository<School> {
    private GenericArray<School> array = new GenericArray<School>();

    public int getLecturesSize() {
        return array.size();
    }

    @Override
    public void add(School school) {
        array.add(school);
    }

    @Override
    public void add(Integer id, School school) {

    }

    @Override
    public School getById(Integer id) {
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i).getId() == id) {
                return array.get(i);
            }
        }
        return null;
    }

    @Override
    public School[] getAll() {
        School[] ret = new School[array.size()];
        for (int i = 0; i < array.size(); i++) {
            ret[i] = array.get(i);
        }
        return ret;
    }

    @Override
    public void deleteById(Integer id) {
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
