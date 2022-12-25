package repository;
import models.HomeAssignment;
public class HomeAssignmentRepository {
    private HomeAssignment[] homeAssignments = new HomeAssignment[0];
    private int lastIndex = -1;
    public void addHomeAssignment(HomeAssignment homeAssignment) {
        HomeAssignment[] newHomeAssignments = new HomeAssignment[3 * homeAssignments.length / 2 + 1];
        for (int i = 0; i < homeAssignments.length; i++) {
            newHomeAssignments[i] = homeAssignments[i];
        }
        lastIndex++;
        newHomeAssignments[lastIndex] = homeAssignment;
        this.homeAssignments = newHomeAssignments;
    }
    public HomeAssignment getHomeAssignment(int homeAssignmentId) {
        for (int i = 0; i <= lastIndex; i++) {
            if (homeAssignments[i].getHomeAssignmentId() == homeAssignmentId) {
                return homeAssignments[i];
            }
        }
        return null;
    }
    public HomeAssignment[] getAllHomeAssignments() {
        return this.homeAssignments;
    }
}
