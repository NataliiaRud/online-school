package repository;
import models.HomeAssignment;
import models.Lecture;

public class HomeAssignmentRepository {
    private HomeAssignment[] homeAssignments = new HomeAssignment[0];
    private HomeAssignment[] newHomeAssignments = new HomeAssignment[0];
    private int lastIndex = -1;
    public void addHomeAssignment(HomeAssignment homeAssignment) {
        lastIndex++;
        if (lastIndex >= homeAssignments.length) {
        HomeAssignment[] newHomeAssignments = new HomeAssignment[3 * homeAssignments.length / 2 + 1];
            System.arraycopy(homeAssignments, 0, newHomeAssignments, 0, homeAssignments.length);
//            this.homeAssignments = newHomeAssignments;
        }
        this.homeAssignments[lastIndex] = homeAssignment;
    }

    public HomeAssignment getHomeAssignment(int homeAssignmentId) {
        for (int i = 0; i <= lastIndex; i++) {
            if (homeAssignments[i].getId() == homeAssignmentId) {
                return homeAssignments[i];
            }
        }
        return null;
    }
    public HomeAssignment[] getAllHomeAssignments() {
        return this.homeAssignments;
    }
}
