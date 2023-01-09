package models;

public class Teacher extends Person {
    private static int counter;

    public Teacher(int id, String firstName, String lastName, Role role, int courseId) {
        super(id, firstName, lastName, Role.TEACHER, courseId);
        counter++;
    }

    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        Teacher.counter = counter;
    }


}