package repository;

import models.Lecture;

public class LectureRepository implements BaseRepository<Lecture> {
    private GenericArray<Lecture> array = new GenericArray<Lecture>();

    public int getLecturesSize() {
        return array.size();
    }
    public boolean isEmpty() {
        return array.isEmpty();
    }

    public Lecture getByIndex(int indexToGet) {
        int index = indexToGet;
        return array.get(index);
    }

    @Override
    public void add(Lecture lecture) {
        array.add(lecture);
    }

    @Override
    public void add(int index, Lecture lecture) {
        array.add(lecture);
    }

    @Override
    public Lecture getById(int id) {
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i).getId() == id) {
                return array.get(i);
            }
        }
        return null;
    }

    @Override
    public Lecture[] getAll() {
        Lecture[] ret = new Lecture[array.size()];
        for (int i = 0; i < array.size(); i++) {
            ret[i] = array.get(i);
        }
        return ret;
    }

    @Override
    public void deleteById(int id) {
        int indexToDelete = -1;
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i).getId() == id) {
                indexToDelete = i;
                break;
            }
        }

        if (indexToDelete != -1) {
            array.remove(indexToDelete);
        }
    }
}