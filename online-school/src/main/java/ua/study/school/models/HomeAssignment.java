package ua.study.school.models;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "home_assignment")
public class HomeAssignment extends Base implements Serializable {
    @Serial
    private static final long serialVersionUID = 2281629061527663261L;

    private String name;
    private String task;
    private static int counter;

    private Lecture lecture;

    public HomeAssignment() {
        super(0);
        this.lecture = new Lecture();
    }

    public HomeAssignment(Integer id, String name, int lectureId, String task) {
        super(id);
        this.name = name;
        this.lecture = new Lecture();
        this.lecture.setId(lectureId);
        this.task = task;
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

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "task")
    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lecture_id", referencedColumnName = "id")
    public Lecture getLecture() {
        return lecture;
    }

    public void setLecture(Lecture lecture) {
        this.lecture = lecture;
    }


    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        HomeAssignment.counter = counter;
    }

    @Override
    public String toString() {
        return "HomeAssignment{" +
                "id='" + getId() + '\'' +
                ", name='" + name + '\'' +
                ", lectureId=" + lecture.getId() +
                ", task='" + task + '\'' +
                '}';
    }
}
