package ua.study.school.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.study.school.models.Student;

@Repository
public interface StudentsRepository<P> extends CrudRepository<Student, Integer> {

}