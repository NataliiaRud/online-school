package ua.study.school.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.study.school.models.Lecture;

import java.util.Collection;


@Repository
public interface LectureRepository<P> extends CrudRepository<Lecture, Integer> {
    @Query(value = "SELECT l.name, count(a.id) as number_of_additional_materials FROM " +
            "lecture l LEFT JOIN additional_material a ON l.id = a.lecture_id " +
            "WHERE l.lecture_date < '2023-01-01 00:00:00' " +
            "GROUP BY l.id " +
            "ORDER BY l.lecture_date", nativeQuery = true)
    Collection<Object[]> getLecturesAndAdditionalMaterials();

    @Query(value = "SELECT b.id, b.course_id, b.name, b.description, b.lecture_date FROM (" +
            "  SELECT l.*, count(a.id) as number_of_additional_materials FROM " +
            "  lecture l LEFT JOIN additional_material a ON l.id = a.lecture_id " +
            "  GROUP BY l.id " +
            "  ORDER BY l.lecture_date, number_of_additional_materials DESC) b " +
            "LIMIT 1", nativeQuery = true)
    Collection<Object[]> getEarliestLecture();
}