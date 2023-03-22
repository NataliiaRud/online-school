package ua.study.school.models;

import java.io.Serial;
import java.io.Serializable;

public class HomeAssignment extends Base implements Serializable {
    @Serial
    private static final long serialVersionUID = 2281629061527663261L;

    private String name;
    private int lectureId;
    private String task;
    private static int counter;

    public HomeAssignment() {
        super(0);
    }

    public HomeAssignment(Integer id, String name, int lectureId, String task) {
        super(id);
        this.name = name;
        this.lectureId = lectureId;
        this.task = task;
        counter++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLectureId() {
        return lectureId;
    }

    public void setLectureId(int lectureId) {
        this.lectureId = lectureId;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
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
                ", lectureId=" + lectureId +
                ", task='" + task + '\'' +
                '}';
    }
}
