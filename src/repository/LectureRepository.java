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
    public void add(Integer index, Lecture lecture) {
        array.add(lecture);
    }




    @Override
    public Lecture getById(Integer id) {
        try {
            if (id <1 || id >3)
                throw new EntityNotFoundException("Lecture with id = " + id + " doesn't exist in repo");
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

    public java.util.List<Lecture> findAll() {
        return array.findAll();
    }

    @Override
    public void deleteById(Integer id) {
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