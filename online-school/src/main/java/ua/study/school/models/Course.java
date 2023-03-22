package ua.study.school.models;

public class Course extends Base {
    private static int counter;

    private int schoolId;

    private Course(Integer id, String name, int schoolId) {
        super(id, name);
        this.schoolId = schoolId;
        counter++;
    }

    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        Course.counter = counter;
    }

    public static Course createCourse(int id, String name, int schoolId) {
        return new Course(id, name, schoolId);
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + getId() +
                ", name=" + getName() +
                ", schoolId=" + schoolId +
                '}';
    }
}
