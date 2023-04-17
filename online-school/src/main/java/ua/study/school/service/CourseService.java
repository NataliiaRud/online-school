package ua.study.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.study.school.models.Course;
import ua.study.school.repository.BaseService;
import ua.study.school.repository.CourseRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService implements BaseService<Course> {
    @Autowired
    private CourseRepository<Course> courseRepository;

    @Override
    public Integer getSize() {
        return (int) courseRepository.count();
    }

    @Override
    public boolean isEmpty() {
        return getSize() == 0;
    }

    @Override
    public Course getByIndex(Integer indexToGet) {
        // index is not applicable for database
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void add(Course course) {
        courseRepository.save(course);
    }

    @Override
    public void add(Integer id, Course course) {
        // index is not applicable for database
        throw new RuntimeException("Not implemented");
    }

    @Override
    public Course getById(Integer id) {
        Optional<Course> course = courseRepository.findById(id);
        return course.isPresent() ? course.get() : null;
    }

    @Override
    public List<Course> getAll() {
        Iterable<Course> iterable = courseRepository.findAll();

        List<Course> list = new ArrayList<>();
        for (Course course : iterable) {
            list.add(course);
        }

        return list;
    }

    @Override
    public void deleteById(Integer id) {
        courseRepository.deleteById(id);
    }
}
