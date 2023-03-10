package ua.study.school.models;

import java.io.Serializable;

public class HomeAssignment extends Base implements Serializable {
    private static final long serialVersionUID = 2281629061527663261L;

    private String assignment;
    private int lectureId;
    private String task;
    private static int counter;

    public HomeAssignment() {
        super(0);
    }

    public HomeAssignment(Integer id, String assignment, int lectureId, String task) {
        super(id);
        this.assignment = assignment;
        this.lectureId = lectureId;
        this.task = task;
        counter++;
    }

    public String getAssignment() {
        return assignment;
    }

    public void setAssignment(String assignment) {
        this.assignment = assignment;
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
                ", assignment='" + assignment + '\'' +
                ", lectureId=" + lectureId +
                ", task='" + task + '\'' +
                '}';
    }
}
