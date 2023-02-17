package repository;


import models.Students;

import java.util.ArrayList;


public class StudentsRepository implements BaseRepository<Students> {
    ArrayList<Students> students = new ArrayList<>();

    public Integer getSize() {
        return students.size();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Students getByIndex(Integer indexToGet) {
        return null;
    }

    @Override
    public void add(Students student) {
        students.add(student);
    }

    @Override
    public void add(Integer id, Students student) {

    }

    @Override
    public Students getById(Integer id) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId() == id) {
                return students.get(i);
            }
        }
        return null;
    }

    @Override
    public Students[] getAll() {
        Students[] ret = new Students[students.size()];
        for (int i = 0; i < students.size(); i++) {
            ret[i] = students.get(i);
        }
        return ret;
    }

    @Override
    public void deleteById(Integer id) {
        int indexToDelete = -1;
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId() == id) {
                indexToDelete = i;
                break;
            }
        }

        if (indexToDelete != -1) {
            students.remove(indexToDelete);
        }
    }
}