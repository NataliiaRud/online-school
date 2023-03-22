package ua.study.school.repository;


import ua.study.school.models.Student;

import java.util.ArrayList;
import java.util.List;


public class StudentsRepository implements BaseRepository<Student> {
    ArrayList<Student> students = new ArrayList<>();

    public Integer getSize() {
        return students.size();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Student getByIndex(Integer indexToGet) {
        return null;
    }

    @Override
    public void add(Student student) {
        students.add(student);
    }

    @Override
    public void add(Integer id, Student student) {

    }

    @Override
    public Student getById(Integer id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    @Override
    public List<Student> getAll() {
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