package ua.study.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.study.school.models.Student;
import ua.study.school.repository.StudentsRepository;

import java.util.List;

@Service
public class StudentsService {
    @Autowired
    private StudentsRepository studentsRepository;

    public void add(Student student) {
        studentsRepository.add(student);
    }

    public List<Student> getAll() {
        return studentsRepository.getAll();
    }
}