package ua.study.school.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.study.school.models.Teacher;

import java.util.List;

@Repository
public interface TeacherRepository<P> extends CrudRepository<Teacher, Integer> {
    @Query(value = "FROM Teacher WHERE UPPER(lastName) < :character")
    public List<Teacher> getTeachersStartingFromCharacter(@Param("character") String start);
}