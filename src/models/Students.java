package models;

public class Students extends Person {
    private static int counter;

    public Students(int id, String firstName, String lastName, Role role, int courseId, String phone, String email) {
        super(id, firstName, lastName, Role.TEACHER, courseId, phone, email);
        counter++;
    }


    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        Students.counter = counter;
    }
}
