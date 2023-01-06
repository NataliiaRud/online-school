package service;

import models.Lecture;
import repository.LectureRepository;

public class LectureService {
    private LectureRepository lectureRepository;

    public LectureService(LectureRepository lectureRepository) {
        this.lectureRepository = lectureRepository;
    }

    public void printLectureIds() {
        Lecture[] lectures;
        lectures = lectureRepository.getAll();

        for (int i = 0; i < lectures.length; i++) {
            Lecture lecture = lectures[i];

            if (lecture == null) {
                break;
            }

            if (i > 0) {
                System.out.print(" ");
            }
            System.out.print(lecture.getId());
        }
    }
}
