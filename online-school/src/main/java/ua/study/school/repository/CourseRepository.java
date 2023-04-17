package ua.study.school.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.study.school.models.Course;

@Repository
public interface CourseRepository<P> extends CrudRepository<Course, Integer> {

}
