package repository;


import models.Students;

import java.util.ArrayList;
import java.util.List;


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
        for (Students student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    @Override
    public List<Students> getAll() {
        return this.students;
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