package ua.study.school.models;

import jakarta.persistence.*;

@Entity
@Table(name = "course")
public class Course extends Base {
    private static int counter;

    private int schoolId;

    public Course() {
        super(0);
    }

    private Course(Integer id, String name, int schoolId) {
        super(id, name);
        this.schoolId = schoolId;
        counter++;
    }

    @Override
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return super.getId();
    }

    @Override
    @Column(name = "name")
    public String getName() {
        return super.getName();
    }

    @Column(name = "school_id")
    public int getSchoolId() {
        return schoolId;
    }

    @Override
    public void setId(int id) {
        super.setId(id);
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    public void setSchoolId(int schoolId) {
        this.schoolId = schoolId;
    }

    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        Course.counter = counter;
    }

    public static Course createCourse(int id, String name, int schoolId) {
        return new Course(id, name, schoolId);
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + getId() +
                ", name=" + getName() +
                ", schoolId=" + schoolId +
                '}';
    }
}
