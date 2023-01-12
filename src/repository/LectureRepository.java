package repository;
import models.Base;
import models.Lecture;

public class LectureRepository implements BaseRepository<Lecture>  {
    private int lastIndex = -1;

    private Lecture[] lectures = new Lecture[0];
private final GenericRepository<Lecture> lectureGenericRepository = new GenericRepository<Lecture>(lectures);


    public int getLecturesSize() {
      return lectureGenericRepository.size();
    }


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
        int indexToDelete = -1;
        for (int i = 0; i < lectures.length; i++) {

            if (lectures[i].getId() == id) {
                indexToDelete = i;
                break;
            }

            }
        if (indexToDelete == -1) {
            return;
        }
        for (int i = indexToDelete; i < lectures.length-1; i++) {

            lectures[i] = lectures[i+1];
            lectures[i+1]=null;
            if (lectures[i] != null) {
                lectures[i].setId((lectures[i].getId()) - 1);
            }


        }

    }

}