package ua.study.school.repository;

import ua.study.school.models.School;

import java.util.ArrayList;
import java.util.List;


public class SchoolRepository implements BaseRepository<School> {
    ArrayList<School> schools = new ArrayList<>();

    public Integer getSize() {
        return schools.size();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public School getByIndex(Integer indexToGet) {
        return null;
    }

    @Override
    public void add(School school) {
        schools.add(school);
    }

    @Override
    public void add(Integer id, School school) {

    }

    @Override
    public School getById(Integer id) {
        for (School school : schools) {
            if (school.getId() == id) {
                return school;
            }
        }
        return null;
    }

    @Override
    public List<School> getAll() {
        return this.schools;
    }

    @Override
    public void deleteById(Integer id) {
        int indexToDelete = -1;
        for (int i = 0; i < schools.size(); i++) {
            if (schools.get(i).getId() == id) {
                indexToDelete = i;
                break;
            }
        }

        if (indexToDelete != -1) {
            schools.remove(indexToDelete);
        }
    }
}
