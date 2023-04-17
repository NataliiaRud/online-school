package ua.study.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.study.school.models.School;
import ua.study.school.repository.BaseService;
import ua.study.school.repository.SchoolRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class SchoolService implements BaseService<School> {
    @Autowired
    private SchoolRepository<School> schoolRepository;

    public List<School> getAll() {
        Iterable<School> iterable = schoolRepository.findAll();

        List<School> list = new ArrayList<>();
        for (School school : iterable) {
            list.add(school);
        }
        return list;
    }

    ArrayList<School> schools = new ArrayList<>();

    public Integer getSize() {
        return schools.size();
    }

    @Override
    public boolean isEmpty() {
        return getSize() == 0;
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
