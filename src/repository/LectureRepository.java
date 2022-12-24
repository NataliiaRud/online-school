package repository;

import models.Lecture;

import java.util.Arrays;

public class LectureRepository {
    private Lecture[] lectures;
    private int lastIndex = -1;

    public LectureRepository() {
        this.lectures = new Lecture[8];
    }

    public void addLecture(Lecture lecture) {
        lastIndex++;
        lectures[lastIndex] = lecture;
    }

    public Lecture getLecture(int lectureId) {
        for (int i = 0; i <= lastIndex; i++) {
            if (lectures[i].getLectureId() == lectureId) {
                return lectures[i];
            }
        }

        return null;
    }

    public Lecture[] getAllLectures() {
        return this.lectures;
    }
}
