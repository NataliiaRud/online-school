package ua.study.school.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "student")
public class Student extends Person implements Serializable {
    @Serial
    private static final long serialVersionUID = 2167609860155503448L;

    private static int counter;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id", referencedColumnName = "id")
    private School school;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "student_course",
            joinColumns = { @JoinColumn(name = "student_id") },
            inverseJoinColumns = { @JoinColumn(name = "course_id") }
    )
    private Collection<Course> courses;

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
    @NotBlank(message = "student.first.name.blank.message")
    @Column(name = "first_name")
    public String getFirstName() {
        return super.getFirstName();
    }

    @Override
    public void setFirstName(String firstName) {
        super.setFirstName(firstName);
    }

    @Override
    @NotBlank(message = "student.last.name.blank.message")
    @Column(name = "last_name")
    public String getLastName() {
        return super.getLastName();
    }

    @Override
    public void setLastName(String lastName) {
        super.setLastName(lastName);
    }

    @Override
    @Pattern(regexp = "[0-9]{5,12}", message = "student.phone.invalid.message")
    @Column(name = "phone")
    public String getPhone() {
        return super.getPhone();
    }

    @Override
    public void setPhone(String phone) {
        super.setPhone(phone);
    }

    @Override
    @Email(message = "student.email.invalid.message")
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
