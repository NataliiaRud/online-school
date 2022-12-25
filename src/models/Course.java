package models;

public class Course {
    public int id;
    private String name;
    public static int courseCounter;

    public Course(int id, String name) {
        this.id = id;
        this.name = name;
        courseCounter++;
    }
    public int getId() {
        return id;
    }

    public int getCourseId() {
        return this.id;
    }

    public static Course createCourse(int id, String name) {
        return new Course(id, name);
    }
}
