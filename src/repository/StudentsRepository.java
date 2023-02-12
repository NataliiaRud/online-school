package repository;

import models.Students;


public class StudentsRepository implements BaseRepository<Students> {
    private GenericArray<Students> array = new GenericArray<Students>();

    public int getLecturesSize() {
        return array.size();
    }

    @Override
    public void add(Students students) {
        array.add(students);
    }

    @Override
    public void add(Integer id, Students students) {

    }

    @Override
    public Students getById(Integer id) {
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i).getId() == id) {
                return array.get(i);
            }
        }
        return null;
    }

    @Override
    public Students[] getAll() {
        Students[] ret = new Students[array.size()];
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