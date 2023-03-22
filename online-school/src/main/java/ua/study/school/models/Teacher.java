package ua.study.school.models;

import java.io.Serial;
import java.io.Serializable;

public class Teacher extends Person implements Serializable {
    @Serial
    private static final long serialVersionUID = 8837729487055331441L;

    private static int counter;

    public Teacher(Integer id, String firstName, String lastName, int courseId, String phone, String email) {
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