package models;

public class Teacher {
    private static int id;
    private String firstName;
    private String lastName;

    public static int teacherCounter;


    public Teacher(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        teacherCounter++;
    }

}

