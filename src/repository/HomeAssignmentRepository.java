package repository;


import models.HomeAssignment;

import java.util.ArrayList;


public class HomeAssignmentRepository implements BaseRepository<HomeAssignment> {
    ArrayList<HomeAssignment> homeAssignments = new ArrayList<>();

    public Integer getSize() {
        return homeAssignments.size();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public HomeAssignment getByIndex(Integer indexToGet) {
        return null;
    }

    @Override
    public void add(HomeAssignment homeAssignment) {
        {
            homeAssignments.add(homeAssignment);
        }
    }

    @Override
    public void add(Integer id, HomeAssignment homeAssignment) {
        homeAssignments.add(homeAssignment);
    }

    @Override
    public HomeAssignment getById(Integer id) {
        for (int i = 0; i < homeAssignments.size(); i++) {
            if (homeAssignments.get(i).getId() == id) {
                return homeAssignments.get(i);
            }
        }
        return null;
    }

    @Override
    public HomeAssignment[] getAll() {
        HomeAssignment[] ret = new HomeAssignment[homeAssignments.size()];
        for (int i = 0; i < homeAssignments.size(); i++) {
            ret[i] = homeAssignments.get(i);
        }
        return ret;
    }

    @Override
    public void deleteById(Integer id) {
        int indexToDelete = -1;
        for (int i = 0; i < homeAssignments.size(); i++) {
            if (homeAssignments.get(i).getId() == id) {
                indexToDelete = i;
                break;
            }
        }

        if (indexToDelete != -1) {
            homeAssignments.remove(indexToDelete);
        }
    }
}