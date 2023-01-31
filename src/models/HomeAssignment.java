package models;

public class HomeAssignment extends Base {

    private String assignment;
    private int lectureId;
    private String task;
    private static int counter;

    public HomeAssignment(int id, String assignment, int lectureId, String task) {
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
}
