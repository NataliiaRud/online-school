package ua.study.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.study.school.models.HomeAssignment;
import ua.study.school.repository.BaseService;
import ua.study.school.repository.HomeAssignmentRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HomeAssignmentService implements BaseService<HomeAssignment> {
    @Autowired
    private HomeAssignmentRepository homeAssignmentRepository;

    private final ArrayList<HomeAssignment> homeAssignments = new ArrayList<>();
    private final Map<Integer, List<HomeAssignment>> byLectureMap = new HashMap<>();

    public Integer getSize() {
        return homeAssignments.size();
    }

    @Override
    public boolean isEmpty() {
        return homeAssignments.isEmpty();
    }

    @Override
    public HomeAssignment getByIndex(Integer indexToGet) {
        return indexToGet < homeAssignments.size() ? homeAssignments.get(indexToGet) : null;
    }

    @Override
    public void add(HomeAssignment homeAssignment) {
        homeAssignments.add(homeAssignment);

        List<HomeAssignment> list = byLectureMap.computeIfAbsent(homeAssignment.getLecture().getId(), k -> new ArrayList<>());
        list.add(homeAssignment);
    }

    public List<HomeAssignment> getByLectureId(int lectureId) {
        return byLectureMap.computeIfAbsent(lectureId, k -> new ArrayList<>());
    }

    @Override
    public void add(Integer id, HomeAssignment homeAssignment) {
        homeAssignments.add(homeAssignment);

        List<HomeAssignment> list = byLectureMap.computeIfAbsent(homeAssignment.getLecture().getId(), k -> new ArrayList<>());
        list.add(homeAssignment);
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
        homeAssignments.removeIf(homeAssignment -> homeAssignment.getId() == id);

        for (Integer lectureId : byLectureMap.keySet()) {
            List<HomeAssignment> list = byLectureMap.get(lectureId);
            list.removeIf(homeAssignment -> homeAssignment.getId() == id);
        }
    }
}