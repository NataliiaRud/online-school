package models;

public class School {
    private static int id;
    private String name;
    public static int schoolCounter;

    public School(int id, String name) {
        this.id = id;
        this.name = name;
        schoolCounter++;
    }
}
