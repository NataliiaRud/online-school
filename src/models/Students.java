package models;

public class Students {
    private int id;
    private String firstName;
    private String lastName;
    public static int studentsCounter;

    public Students(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        studentsCounter++;
    }
    public String getName() {
        return this.lastName;
    }

    public int getStudentsId() {
        return this.id;
    }
}
