package repository;
import models.Course;
import models.Lecture;

public class CourseRepository {
    private Course[] courses = new Course[0];
    private Course[] newCourses = new Course[0];
    private int lastIndex = -1;
    public void addCourse(Course course) {
        lastIndex++;
        if (lastIndex >= courses.length) {
        Course[] newCourses = new Course[3 * courses.length / 2 + 1];
            System.arraycopy(courses, 0, newCourses, 0, courses.length);
            this.courses = newCourses;
        }
        this.courses[lastIndex] = course;
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