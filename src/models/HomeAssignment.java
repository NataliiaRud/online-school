package models;

public class HomeAssignment {
    private int id;
    private String assignment;
    public static int homeAssignmentCounter;

    public HomeAssignment(int id, String assignment) {
        this.id = id;
        this.assignment = assignment;
        homeAssignmentCounter++;
    }
    public int getHomeAssignmentId() {
        return this.id;
    }
}
