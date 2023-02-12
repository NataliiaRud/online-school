package repository;

import models.Teacher;


public class TeacherRepository implements BaseRepository<Teacher> {
    private GenericArray<Teacher> array = new GenericArray<Teacher>();

    public int getLecturesSize() {
        return array.size();
    }

    @Override
    public void add(Teacher teacher) {
        array.add(teacher);
    }

    @Override
    public void add(Integer id, Teacher teacher) {

    }

    @Override
    public Teacher getById(Integer id) {
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i).getId() == id) {
                return array.get(i);
            }
        }
        return null;
    }

    @Override
    public Teacher[] getAll() {
        Teacher[] ret = new Teacher[array.size()];
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