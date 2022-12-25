package repository;
import models.Course;
public class CourseRepository {
    private Course[] courses = new Course[0];
    private int lastIndex = -1;
    public void addCourse(Course course) {
        Course[] newCourses = new Course[3 * courses.length / 2 + 1];
        for (int i = 0; i < courses.length; i++) {
            newCourses[i] = courses[i];
        }
        lastIndex++;
        newCourses[lastIndex] = course;
        this.courses = newCourses;
    }
    public Course getCourse(int courseId) {
        for (int i = 0; i <= lastIndex; i++) {
            if (courses[i].getCourseId() == courseId) {
                return courses[i];
            }
        }
        return null;
    }
    public Course[] getAllCourses() {
        return this.courses;
    }
}