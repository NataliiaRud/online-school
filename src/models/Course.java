package models;

public class Course {
    public static int id;
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

}
