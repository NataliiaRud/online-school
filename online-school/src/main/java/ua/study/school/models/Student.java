package ua.study.school.models;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "student")
public class Student extends Person implements Serializable {
    @Serial
    private static final long serialVersionUID = 2167609860155503448L;

    private static int counter;

    public Student() {
        super(Role.STUDENT);
    }

    public Student(Integer id, String firstName, String lastName, int schoolId, String phone, String email) {
        super(id, firstName, lastName, Role.STUDENT, schoolId, phone, email);

        counter++;
    }

    @Override
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return super.getId();
    }

    @Override
    public void setId(int id) {
        super.setId(id);
    }


    @Override
    @Column(name = "school_id")
    public int getSchoolId() {
        return super.getSchoolId();
    }

    @Override
    public void setSchoolId(int schoolId) {
        super.setSchoolId(schoolId);
    }

    @Override
    @Column(name = "first_name")
    public String getFirstName() {
        return super.getFirstName();
    }

    @Override
    public void setFirstName(String firstName) {
        super.setFirstName(firstName);
    }

    @Override
    @Column(name = "last_name")
    public String getLastName() {
        return super.getLastName();
    }

    @Override
    public void setLastName(String lastName) {
        super.setLastName(lastName);
    }

    @Override
    @Column(name = "phone")
    public String getPhone() {
        return super.getPhone();
    }

    @Override
    public void setPhone(String phone) {
        super.setPhone(phone);
    }

    @Override
    @Column(name = "email")
    public String getEmail() {
        return super.getEmail();
    }

    @Override
    public void setEmail(String email) {
        super.setEmail(email);
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
