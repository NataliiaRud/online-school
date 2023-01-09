package repository;
import models.HomeAssignment;

public class HomeAssignmentRepository implements BaseRepository<HomeAssignment> {
    private HomeAssignment[] homeAssignments = new HomeAssignment[0];

    private int lastIndex = -1;




    @Override
    public void add(HomeAssignment homeAssignment) {
        lastIndex++;
        if (lastIndex >= homeAssignments.length) {
            HomeAssignment[] newHomeAssignments = new HomeAssignment[3 * homeAssignments.length / 2 + 1];
            System.arraycopy(homeAssignments, 0, newHomeAssignments, 0, homeAssignments.length);
            this.homeAssignments = newHomeAssignments;
        }
        this.homeAssignments[lastIndex] = homeAssignment;
    }

    @Override
    public HomeAssignment getById(int id) {
        for (int i = 0; i <= lastIndex; i++) {
            if (homeAssignments[i].getId() == id) {
                return homeAssignments[i];
            }
        }
        return null;
    }

    @Override
    public HomeAssignment[] getAll() {
        return this.homeAssignments;
    }

    @Override
    public void deleteById(int id) {
        for (int i = 0; i <= lastIndex; i++) {
            if (homeAssignments[i].getId() == id) {
                homeAssignments[i]=null;
            }
        }
    }
}