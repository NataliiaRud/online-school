package repository;

import models.Lecture;
import java.util.*;
import exceptions.EntityNotFoundException;

public class LectureRepository implements BaseRepository<Lecture>{
    ArrayList <Lecture> lectures = new ArrayList<>();


    @Override
    public Integer getSize() {
        return lectures.size();
    }
    @Override
    public boolean isEmpty() {
        return lectures.isEmpty();
    }
    @Override
    public Lecture getByIndex(Integer indexToGet) {
        int index = indexToGet;
        return lectures.get(index);
    }
    @Override
    public void add(Lecture lecture) {
        lectures.add(lecture);
}

    @Override
    public void add(Integer index, Lecture lecture) {
        lectures.add(lecture);
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
        for (int i = 0; i < lectures.size(); i++) {
            if (lectures.get(i).getId() == id) {
                return lectures.get(i);
            }
        }
        return null;
    }


    @Override
    public List<Lecture> getAll() {
        return this.lectures;
    }


    public java.util.List<Lecture> findAll() {
        return lectures;
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
        for (int i = 0; i < lectures.size(); i++) {
            if (lectures.get(i).getId() == id) {
                indexToDelete = i;
                break;
            }
        }

        if (indexToDelete != -1) {
            lectures.remove(indexToDelete);
        }
    }




}