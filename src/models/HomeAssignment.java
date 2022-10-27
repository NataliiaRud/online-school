package models;

public class HomeAssignment {
    private static int id;
    private String homeAssignment;
    public static int homeAssignmentCounter;

    public HomeAssignment(int id, String homeAssignment) {
        this.id = id;
        this.homeAssignment = homeAssignment;
        homeAssignmentCounter++;
    }
}
