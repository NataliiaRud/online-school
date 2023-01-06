package repository;
import models.Lecture;
public class LectureRepository {
    private Lecture[] lectures = new Lecture[0];
//    private Lecture[] newLectures = new Lecture[0];
    private int lastIndex = -1;
    public void addLecture(Lecture lecture) {
        lastIndex++;
        if (lastIndex >= lectures.length) {
            Lecture[] newLectures = new Lecture[3 * lectures.length / 2 + 1];
            System.arraycopy(lectures, 0, newLectures, 0, lectures.length);
            this.lectures = newLectures;
        }
        this.lectures[lastIndex] = lecture;
    }
    public Lecture getLecture(int lectureId) {
        for (int i = 0; i <= lastIndex; i++) {
            if (lectures[i].getId() == lectureId) {
                return lectures[i];
            }
        }
        return null;
    }
    public Lecture[] getAll() {
        return this.lectures;
    }
}