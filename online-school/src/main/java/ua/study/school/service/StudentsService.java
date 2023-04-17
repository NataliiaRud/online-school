package ua.study.school.service;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.study.school.models.Student;
import ua.study.school.repository.BaseService;
import ua.study.school.repository.StudentsRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentsService implements BaseService<Student> {
    @Autowired
    private StudentsRepository<Student> studentsRepository;

    public void add(Student student) {
        studentsRepository.save(student);
    }

    public List<Student> getAll() {
        Iterable<Student> iterable = studentsRepository.findAll();

        List<Student> list = new ArrayList<>();
        for (Student student : iterable) {
            list.add(student);
        }
        return list;
    }

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Integer getSize() {
        return (int) studentsRepository.count();
    }

    @Override
    public boolean isEmpty() {
        return getSize() == 0;
    }

    @Override
    public Student getByIndex(Integer indexToGet) {
        // index is not applicable for database
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void add(Integer id, Student student) {
        // index is not applicable for database
        throw new RuntimeException("Not implemented");
    }

    @Override
    public Student getById(Integer id) {
        Optional<Student> optional = studentsRepository.findById(id);
        return optional.orElse(null);
    }

    @Override
    public void deleteById(Integer id) {
        studentsRepository.deleteById(id);
    }
}