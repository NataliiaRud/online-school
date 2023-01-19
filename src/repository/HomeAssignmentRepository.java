package repository;

import models.HomeAssignment;


public class HomeAssignmentRepository implements BaseRepository<HomeAssignment> {
    private GenericArray<HomeAssignment> array = new GenericArray<HomeAssignment>();

    public int getLecturesSize() {
        return array.size();
    }

    @Override
    public void add(HomeAssignment homeAssignment) {
        array.add(homeAssignment);
    }

    @Override
    public void add(int id, HomeAssignment homeAssignment) {

    }

    @Override
    public HomeAssignment getById(int id) {
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i).getId() == id) {
                return array.get(i);
            }
        }
        return null;
    }

    @Override
    public HomeAssignment[] getAll() {
        HomeAssignment[] ret = new HomeAssignment[array.size()];
        for (int i = 0; i < array.size(); i++) {
            ret[i] = array.get(i);
        }
        return ret;
    }

    @Override
    public void deleteById(int id) {
        int indexToDelete = -1;
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i).getId() == id) {
                indexToDelete = i;
                break;
            }
        }

        if (indexToDelete != -1) {
            array.remove(indexToDelete);
        }
    }
}