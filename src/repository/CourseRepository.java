package repository;

import models.Course;


public class CourseRepository implements BaseRepository<Course> {
    private GenericArray<Course> array = new GenericArray<Course>();

    public int getLecturesSize() {
        return array.size();
    }

    @Override
    public void add(Course course) {
        array.add(course);
    }

    @Override
    public void add(int id, Course course) {

    }

    @Override
    public Course getById(int id) {
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i).getId() == id) {
                return array.get(i);
            }
        }
        return null;
    }

    @Override
    public Course[] getAll() {
        Course[] ret = new Course[array.size()];
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