package ua.study.school.models;

import java.io.Serializable;

public class Students extends Person implements Serializable {
    private static final long serialVersionUID = 2167609860155503448L;

    private static int counter;

    public Students() {
        super(Role.STUDENT);
    }

    public Students(Integer id, String firstName, String lastName, Role role, int courseId, String phone, String email) {

        super(id, firstName, lastName, Role.STUDENT, courseId, phone, email);

        counter++;
    }


    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        Students.counter = counter;
    }

    @Override
    public String toString() {
        return "Students{"
                + getId()
                + ", " + getFirstName() + " " + getLastName()
                + "}";
    }
}
