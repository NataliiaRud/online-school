package ua.study.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.study.school.models.Base;
import ua.study.school.models.Lecture;
import ua.study.school.repository.LectureRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LectureService {
    private final LectureRepository lectureRepository;

    @Autowired
    public LectureService(LectureRepository lectureRepository) {
        this.lectureRepository = lectureRepository;
    }

    public void printLectureIds() {
        List<Lecture> lectures;
        lectures = lectureRepository.getAll();

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

    public void add(Lecture lecture) {
        lectureRepository.add(lecture);
    }

    public Integer getSize() {
        return lectureRepository.getSize();
    }

    public List<Lecture> getAll() {
        return lectureRepository.getAll();
    }

    public java.util.List<Lecture> findAll() {
        return lectureRepository.findAll();
    }

    public List<List<Object>> getLecturesAndAdditionalMaterials() {
        return lectureRepository.getLecturesAndAdditionalMaterials();
    }

    public List<Object> getEarliestLecture() {
        return lectureRepository.getEarliestLecture();
    }
}