package models;

public class Teacher extends Person {
    private static int counter;

    public Teacher(Integer id, String firstName, String lastName, Role role, int courseId, String phone, String email) {
        super(id, firstName, lastName, Role.TEACHER, courseId, phone, email);
        counter++;
    }

    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        Teacher.counter = counter;
    }

    @Override
    public String toString() {
        return "Teacher{"
                +  getId()
                + ", " + getFirstName() + " " + getLastName()
                + "}";
    }


}