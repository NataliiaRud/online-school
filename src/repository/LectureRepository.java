package repository;
import models.Lecture;

public class LectureRepository extends BaseRepository<Lecture> {
    private Lecture[] lectures = new Lecture[0];

    private int lastIndex = -1;


    @Override
    public void add(Lecture lecture) {
        lastIndex++;
        if (lastIndex >= lectures.length) {
            Lecture[] newLectures = new Lecture[3 * lectures.length / 2 + 1];
            System.arraycopy(lectures, 0, newLectures, 0, lectures.length);
            this.lectures = newLectures;
        }
        this.lectures[lastIndex] = lecture;
    }

    @Override
    public Lecture getById(int id) {
        for (int i = 0; i <= lastIndex; i++) {
            if (lectures[i].getId() == id) {
                return lectures[i];
            }
        }
        return null;
    }

    @Override
    public Lecture[] getAll() {
        return this.lectures;
    }

    @Override
    public void deleteById(int id) {
        for (int i = 0; i <= lastIndex; i++) {
            if (lectures[i].getId() == id) {
                lectures[i]=null;
            }
        }
    }
}