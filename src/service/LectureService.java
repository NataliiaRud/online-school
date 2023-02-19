package service;

import models.Base;
import models.Lecture;
import repository.LectureRepository;

import java.util.ArrayList;

public class LectureService {
    private LectureRepository lectureRepository;

    public LectureService(LectureRepository lectureRepository) {
        this.lectureRepository = lectureRepository;
    }

    public void printLectureIds() {

        ArrayList<Lecture> lectures = new ArrayList<>();
        lectures = (ArrayList<Lecture>) lectureRepository.getAll();

        for (int i = 0; i < lectures.size(); i++) {
            Base lecture = lectures.get(i);

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
