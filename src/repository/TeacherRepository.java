package repository;

import models.Teacher;

import java.util.ArrayList;
import java.util.List;


public class TeacherRepository implements BaseRepository<Teacher> {

    ArrayList<Teacher> teachers = new ArrayList<>();

    public Integer getSize() {
        return teachers.size();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Teacher getByIndex(Integer indexToGet) {
        return null;
    }

    @Override
    public void add(Teacher teacher) {
        teachers.add(teacher);
    }

    @Override
    public void add(Integer id, Teacher teacher) {

    }

    @Override
    public Teacher getById(Integer id) {
        for (Teacher teacher : teachers) {
            if (teacher.getId() == id) {
                return teacher;
            }
        }
        return null;
    }

    @Override
    public List<Teacher> getAll() {
        return this.teachers;
    }

    @Override
    public void deleteById(Integer id) {
        int indexToDelete = -1;
        for (int i = 0; i < teachers.size(); i++) {
            if (teachers.get(i).getId() == id) {
                indexToDelete = i;
                break;
            }
        }

        if (indexToDelete != -1) {
            teachers.remove(indexToDelete);
        }
    }
}