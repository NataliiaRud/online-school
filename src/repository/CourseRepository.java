package repository;

import exceptions.EntityNotFoundException;
import models.Course;
import utility.LogService;

import java.util.ArrayList;
import java.util.List;


public class CourseRepository implements BaseRepository<Course> {
    private static final LogService logService = new LogService(CourseRepository.class.getName());

    private ArrayList <Course> courses = new ArrayList<>();

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

    private void checkCourse(Integer id) {
        try {
            if (id < 1 || id > 3) {
                throw new EntityNotFoundException("Course with id = " + id + " doesn't exist");
            }
        } catch (EntityNotFoundException e) {
            logService.error("Course not found: " + id, e);
        }
    }

    @Override
    public Course getById(Integer id) {
        checkCourse(id);

        for (Course course : courses) {
            if (course.getId() == id) {
                return course;
            }
        }
        return null;
    }

    @Override
    public List<Course> getAll() {
        return this.courses;
    }


    @Override
    public void deleteById(Integer id) {
        checkCourse(id);

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
