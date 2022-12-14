package models;

public class Course extends Base {
    private static int counter;
    public Course(int id, String name) {
        super(id, name);
        counter++;
    }

    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        Course.counter = counter;
    }

    public static Course createCourse(int id, String name) {
        return new Course(id, name);
    }
}
