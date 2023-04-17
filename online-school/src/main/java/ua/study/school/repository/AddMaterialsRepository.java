package ua.study.school.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.study.school.models.AdditionalMaterial;

import java.util.*;

@Repository
public interface AddMaterialsRepository<P> extends CrudRepository<AdditionalMaterial, Integer> {
    @Query(value = "SELECT type, count(type) FROM additional_material GROUP BY type", nativeQuery = true)
    Collection<Object[]> getLecturesAndAdditionalMaterials();
}