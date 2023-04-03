package ua.study.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.study.school.models.Teacher;
import ua.study.school.repository.TeacherRepository;

import java.util.List;

@Service
public class TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;

    public void add(Teacher teacher) {
        teacherRepository.add(teacher);
    }

    public List<Teacher> getAll() {
        return teacherRepository.getAll();
    }

    public List<Teacher> getTeachersStartingFromCharacter(String start) {
        return teacherRepository.getTeachersStartingFromCharacter(start);
    }
}