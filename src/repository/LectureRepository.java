package repository;
import models.Lecture;
public class LectureRepository {
    private Lecture[] lectures = new Lecture[0];
    private int lastIndex = -1;
    public void addLecture(Lecture lecture) {
        Lecture[] newLectures = new Lecture[3 * lectures.length / 2 + 1];
//        for (int i = 0; i < lectures.length; i++) {
//            newLectures[i] = lectures[i];
//        }
        System.arraycopy(lectures, 0, newLectures, 0, lectures.length);
        lastIndex++;
        newLectures[lastIndex] = lecture;
        this.lectures = newLectures;
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