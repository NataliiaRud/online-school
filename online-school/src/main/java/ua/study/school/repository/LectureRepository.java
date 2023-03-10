package ua.study.school.repository;

import ua.study.school.exceptions.EntityNotFoundException;
import ua.study.school.models.Lecture;
import ua.study.school.utility.LogService;

import java.util.ArrayList;
import java.util.List;

public class LectureRepository implements BaseRepository<Lecture>{
    private static final LogService logService = new LogService(LectureRepository.class.getName());

    ArrayList <Lecture> lectures = new ArrayList<>();


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
            logService.error("Lecture not found: " + id, e);
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




}