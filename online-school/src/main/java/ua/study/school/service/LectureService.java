package ua.study.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.study.school.exceptions.EntityNotFoundException;
import ua.study.school.models.Base;
import ua.study.school.models.Lecture;
import ua.study.school.repository.BaseService;
import ua.study.school.repository.LectureRepository;
import ua.study.school.utility.Logger;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class LectureService implements BaseService<Lecture> {
    private final LectureRepository<Lecture> lectureRepository;

    @Autowired
    public LectureService(LectureRepository<Lecture> lectureRepository) {
        this.lectureRepository = lectureRepository;
    }

    public void printLectureIds() {
        List<Lecture> lectures = new ArrayList<>();
        Iterable<Lecture> iterable = lectureRepository.findAll();
        for (Lecture lecture : iterable) {
            lectures.add(lecture);
        }

        for (int i = 0; i < lectures.size(); i++) {
            Optional<Base> lecture = Optional.ofNullable(lectures.get(i));

            if (lecture.isEmpty()) {
                break;
            }

            if (i > 0) {
                System.out.print(" ");
            }
            System.out.print(lecture.get().getId());
        }
    }

    private static final Logger LOGGER = new Logger();

    ArrayList<Lecture> lectures = new ArrayList<>();


    @Override
    public Integer getSize() {
        return lectures.size();
    }
    @Override
    public boolean isEmpty() {
        return lectures.isEmpty();
    }
    @Override
    public Lecture getByIndex(Integer indexToGet) {
        int index = indexToGet;
        return lectures.get(index);
    }
    @Override
    public void add(Lecture lecture) {
        lectures.add(lecture);
    }

    @Override
    public void add(Integer index, Lecture lecture) {
        lectures.add(lecture);
    }

    private void checkLecture(Integer id) {
        try {
            if (id < 1 || id > 3) {
                throw new EntityNotFoundException("Lecture with id = " + id + " doesn't exist in repo");
            }
        } catch(EntityNotFoundException e) {
            LOGGER.error("Lecture not found: " + id, e);
        }
    }

    @Override
    public Lecture getById(Integer id) {
        checkLecture(id);

        for (Lecture lecture : lectures) {
            if (lecture.getId() == id) {
                return lecture;
            }
        }
        return null;
    }


    @Override
    public List<Lecture> getAll() {
        return this.lectures;
    }


    public java.util.List<Lecture> findAll() {
        return lectures;
    }

    @Override
    public void deleteById(Integer id) {
        checkLecture(id);

        int indexToDelete = -1;
        for (int i = 0; i < lectures.size(); i++) {
            if (lectures.get(i).getId() == id) {
                indexToDelete = i;
                break;
            }
        }

        if (indexToDelete != -1) {
            lectures.remove(indexToDelete);
        }
    }

    public List<List<Object>> getLecturesAndAdditionalMaterials() {
        Collection<Object[]> resultList = lectureRepository.getLecturesAndAdditionalMaterials();

        List<List<Object>> ret = new ArrayList<>();
        for (Object[] objects : resultList) {
            List<Object> list = new ArrayList<>();
            list.add(objects[0]);
            list.add(objects[1]);
            ret.add(list);
        }
        return ret;
    }

    public List<Object> getEarliestLecture() {
        Collection<Object[]> resultList = lectureRepository.getEarliestLecture();

        if (!resultList.isEmpty()) {
            Object[] objects = resultList.iterator().next();
            List<Object> list = new ArrayList<>();
            list.add(objects[0]);
            list.add(objects[1]);
            list.add(objects[2]);
            list.add(objects[3]);
            list.add(objects[4]);
            return list;
        }
        return null;
    }
}