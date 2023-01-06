package repository;
import models.Course;

public class CourseRepository extends BaseRepository<Course> {
    private Course[] courses = new Course[0];

    private int lastIndex = -1;




    @Override
    public void add(Course course) {
        lastIndex++;
        if (lastIndex >= courses.length) {
            Course[] newCourses = new Course[3 * courses.length / 2 + 1];
            System.arraycopy(courses, 0, newCourses, 0, courses.length);
            this.courses = newCourses;
        }
        this.courses[lastIndex] = course;
    }

    @Override
    public Course getById(int id) {
        for (int i = 0; i <= lastIndex; i++) {
            if (courses[i].getId() == id) {
                return courses[i];
            }
        }
        return null;
    }

    @Override
    public Course[] getAll() {
        return this.courses;
    }

    @Override
    public void deleteById(int id) {
        for (int i = 0; i <= lastIndex; i++) {
            if (courses[i].getId() == id) {
                courses[i]=null;
            }
        }
    }
}