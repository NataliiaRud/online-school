package ua.study.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.study.school.models.HomeAssignment;
import ua.study.school.repository.HomeAssignmentRepository;

import java.util.List;

@Service
public class HomeAssignmentService {
    @Autowired
    private HomeAssignmentRepository homeAssignmentRepository;

    public void add(HomeAssignment homeAssignment) {
        homeAssignmentRepository.add(homeAssignment);
    }

    public List<HomeAssignment> getByLectureId(int lectureId) {
        return homeAssignmentRepository.getByLectureId(lectureId);
    }

    public void deleteById(Integer id) {
        homeAssignmentRepository.deleteById(id);
    }

    public List<HomeAssignment> getAll() {
        return homeAssignmentRepository.getAll();
    }
}