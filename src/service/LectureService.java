package service;

import models.Base;
import models.Lecture;
import repository.LectureRepository;

import java.util.ArrayList;
import java.util.Optional;

public class LectureService {
    private LectureRepository lectureRepository;

    public LectureService(LectureRepository lectureRepository) {
        this.lectureRepository = lectureRepository;
    }

    public void printLectureIds() {
        ArrayList<Lecture> lectures;
        lectures = (ArrayList<Lecture>) lectureRepository.getAll();

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
}
