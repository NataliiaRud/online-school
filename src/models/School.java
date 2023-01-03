package models;

public class School {
    private int id;
    private String name;
    public static int schoolCounter;

    public School(int id, String name) {
        this.id = id;
        this.name = name;
        schoolCounter++;
    }
    public int getSchoolId() {
        return this.id;
    }
}
