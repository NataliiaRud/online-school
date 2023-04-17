package ua.study.school.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.study.school.models.HomeAssignment;

@Repository
public interface HomeAssignmentRepository<P> extends CrudRepository<HomeAssignment, Integer> {

}