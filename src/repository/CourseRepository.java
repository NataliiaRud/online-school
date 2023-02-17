package repository;

import models.Course;
import java.util.*;
import exceptions.EntityNotFoundException;


public class CourseRepository implements BaseRepository<Course>{
    ArrayList <Course> courses = new ArrayList<>();
    @Override
    public Integer getSize() {
        return courses.size();
    }
    @Override
    public boolean isEmpty() {
        return courses.isEmpty();
    }
    @Override
    public Course getByIndex(Integer indexToGet) {
        int index = indexToGet;
        return courses.get(index);
    }
    @Override
    public void add(Course course) {
        courses.add(course);
    }

    @Override
    public void add(Integer index, Course course) {
        courses.add(course);
    }
    @Override

    public Course getById(Integer id) {
        try {
            if (id <1 || id >3)
                throw new EntityNotFoundException("Lecture with id = " + id + " doesn't exist in repo");
        }
        catch(EntityNotFoundException e) {
            System.out.println(e);

        }
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getId() == id) {
                return courses.get(i);
            }
        }
        return null;
    }

    @Override
    public Course[] getAll() {
        Course[] ret = new Course[courses.size()];
        for (int i = 0; i < courses.size(); i++) {
            ret[i] = courses.get(i);
        }
        return ret;

    }
    @Override
    public void deleteById(Integer id) {
        try {
            if (id <1 || id >3)
                throw new EntityNotFoundException("Wrong course");
        }
        catch(EntityNotFoundException e) {
            System.out.println(e);

        }
        int indexToDelete = -1;
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getId() == id) {
                indexToDelete = i;
                break;
            }
        }

        if (indexToDelete != -1) {
            courses.remove(indexToDelete);
        }
    }




}
