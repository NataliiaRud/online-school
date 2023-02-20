package repository;


import models.HomeAssignment;

import java.util.ArrayList;
import java.util.List;


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
    public List<HomeAssignment> getAll() {
        return this.homeAssignments;
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