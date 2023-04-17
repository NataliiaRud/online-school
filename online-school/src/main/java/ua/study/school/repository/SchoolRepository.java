package ua.study.school.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.study.school.models.School;


@Repository
public interface SchoolRepository<P> extends CrudRepository<School, Integer> {

}
