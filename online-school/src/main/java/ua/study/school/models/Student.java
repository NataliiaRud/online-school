package ua.study.school.models;

import java.io.Serial;
import java.io.Serializable;

public class Student extends Person implements Serializable {
    @Serial
    private static final long serialVersionUID = 2167609860155503448L;

    private static int counter;

    public Student(Integer id, String firstName, String lastName, int schoolId, String phone, String email) {
        super(id, firstName, lastName, Role.STUDENT, schoolId, phone, email);

        counter++;
    }


    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        Student.counter = counter;
    }

    @Override
    public String toString() {
        return "Students{"
                + getId()
                + ", " + getFirstName() + " " + getLastName()
                + "}";
    }
}
