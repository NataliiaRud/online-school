package repository;

import models.Lecture;
import exceptions.EntityNotFoundException;

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
        try {
            if (id <1 || id >3)
                throw new EntityNotFoundException("Wrong lecture");
        }
        catch(EntityNotFoundException e) {
            System.out.println(e);

        }
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
        try {
            if (id <1 || id >3)
                throw new EntityNotFoundException("Wrong lecture");
        }
        catch(EntityNotFoundException e) {
            System.out.println(e);

        }
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