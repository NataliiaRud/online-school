package ua.study.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.study.school.models.Course;
import ua.study.school.repository.CourseRepository;

import java.util.List;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    public Course getById(Integer id) {
        return courseRepository.getById(id);
    }

    public Integer getSize() {
        return courseRepository.getSize();
    }

    public List<Course> getAll() {
        return courseRepository.getAll();
    }
}
