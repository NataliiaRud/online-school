package ua.study.school.models;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "school")
public class School extends Base {
    private static int counter;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "school")
    private Collection<Course> courses;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "school")
    private Collection<Teacher> teachers;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "school")
    private Collection<Student> students;

    public School() {
        super(0);
    }

    public School(Integer id, String name) {
        super(id, name);
        counter++;
    }
    public School(Integer id) {
        super(id);
    }

    @Override
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return super.getId();
    }

    @Override
    @Column(name = "name")
    public String getName() {
        return super.getName();
    }

    @Override
    public void setId(int id) {
        super.setId(id);
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        School.counter = counter;
    }
}
