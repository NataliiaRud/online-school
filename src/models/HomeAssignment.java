package models;

public class HomeAssignment extends Base {

    private String assignment;
    private static int counter;

    public HomeAssignment(int id, String assignment) {
        super(id);
        this.assignment = assignment;
        counter++;
    }

    public String getAssignment() {
        return assignment;
    }

    public void setAssignment(String assignment) {
        this.assignment = assignment;
    }

    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        HomeAssignment.counter = counter;
    }
}
