package ua.study.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.study.school.models.Teacher;
import ua.study.school.repository.BaseService;
import ua.study.school.repository.TeacherRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherService implements BaseService<Teacher> {
    @Autowired
    private TeacherRepository<Teacher> teacherRepository;

    public void add(Teacher teacher) {
        teacherRepository.save(teacher);
    }

    public List<Teacher> getAll() {
        Iterable<Teacher> iterable = teacherRepository.findAll();

        List<Teacher> list = new ArrayList<>();
        for (Teacher teacher : iterable) {
            list.add(teacher);
        }
        return list;
    }

    public List<Teacher> getTeachersStartingFromCharacter(String start) {
        return teacherRepository.getTeachersStartingFromCharacter(start);
    }

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